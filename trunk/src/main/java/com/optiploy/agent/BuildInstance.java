package com.optiploy.agent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.optiploy.constants.Constants;
import com.optiploy.exception.DataNotFoundException;
import com.optiploy.exception.InstanceBusyException;
import com.optiploy.exception.InstanceDirectoryException;
import com.optiploy.exception.LowDiskException;
import com.optiploy.exception.OptiployException;
import com.optiploy.exception.ValidationException;
import com.optiploy.model.Agent;
import com.optiploy.model.Instance;
import com.optiploy.model.Job;
import com.optiploy.model.Log;
import com.optiploy.model.LogFile;
import com.optiploy.model.Progress;
import com.optiploy.model.Property;
import com.optiploy.model.Rule;
import com.optiploy.model.Script;
import com.optiploy.packet.Packet;
import com.optiploy.property.OptiployProperties;
import com.optiploy.service.AgentService;
import com.optiploy.service.InstanceService;
import com.optiploy.service.JobService;
import com.optiploy.service.LogFileService;
import com.optiploy.service.LogService;
import com.optiploy.service.ProgressService;
import com.optiploy.service.PropertyService;
import com.optiploy.service.RuleService;
import com.optiploy.util.GeneralUtil;

public class BuildInstance extends Thread
{
	private static Logger logger = Logger.getLogger(BuildInstance.class);	
	
	private ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext-agent.xml");
	
	private PropertyService propertyService = (PropertyService)applicationContext.getBean("propertyService");
	private AgentService agentService = (AgentService)applicationContext.getBean("agentService");
	private InstanceService instanceService = (InstanceService)applicationContext.getBean("instanceService");
	private JobService jobService = (JobService)applicationContext.getBean("jobService");
	private LogService logService = (LogService)applicationContext.getBean("logService");
	private LogFileService logFileService = (LogFileService)applicationContext.getBean("logFileService");
	private ProgressService progressService = (ProgressService)applicationContext.getBean("progressService");
	private RuleService ruleService = (RuleService)applicationContext.getBean("ruleService");
	private OptiployProperties optiployProperties = (OptiployProperties)applicationContext.getBean("optiployProperties");
	private GeneralUtil generalUtil = (GeneralUtil)applicationContext.getBean("generalUtil");
	
	private Property propertyVersion = propertyService.getPropertyByPropertyName(Constants.VERSION);
	private Property propertyServletURL = propertyService.getPropertyByPropertyName(Constants.SERVLET_URL);
	private Property propertyCleanup = propertyService.getPropertyByPropertyName(Constants.AGENT_CLEANUP_FILES);
	private Property propertyBuildTimeout = propertyService.getPropertyByPropertyName(Constants.AGENT_BUILD_TIMEOUT);
	private Property propertyMakeFileWindows = propertyService.getPropertyByPropertyName(Constants.AGENT_MAKE_FILE_WINDOWS);
	private Property propertyMakeFileUnix = propertyService.getPropertyByPropertyName(Constants.AGENT_MAKE_FILE_UNIX);	
	
	int logId;
	int jobId;
	private Log log;
	private Agent agent;
	private Instance instance;
	private String instancePath;
    private String progressValue;
	private Job job;
	private Progress progress;
	private Rule rule;	

    private Socket socket = null;
    
	private Map attributeMap;
    private String buildMessage = new String();
    private String makeFileName = new String();
    private Date lastLine;
    private boolean stopBuild = false;
    private File makeFile = null;
    private File rawLogFile = null;
    private File zipLogFile = null;	  
    
    private String directory = optiployProperties.getPropertyValue(Constants.AGENT_BUILD_DIRECTORY) + "/";
    

    protected BuildInstance(Instance instance, Agent agent)
    {
        this.instance = instance;
        this.agent = agent;
    }
    
   public void setStatus(String status)
   {
    	instance.setStatus(status);
    	instanceService.update(instance);    	
   }
    
    public void run()
    {            
    	String serverVersion = propertyVersion.getValue();
    	String agentVersion = agent.getVersion();
        boolean shutdown = false;
        boolean hardStop = false;        
               
        logger.info("Instance waiting for connection on port " + instance.getPort());
        
        try
        {
        	ServerSocket server = new ServerSocket(instance.getPort());
        	
        	while (!shutdown)
        	{
	        
	        	Socket socket = server.accept();
	        	
	            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
	            Packet request = (Packet) in.readObject();
	            
	            logger.debug("Received request packet: " + request.getRequestType());
	            
	            log = new Log();            
	            log = logService.merge(log);
	            logId = log.getId();            
	            jobId = request.getJobId();            
	            attributeMap = request.getParameters();
	            
	            Packet response = new Packet();
	            response.setLogId(logId);
	            response.setJobId(jobId);
	            response.setRequestType(request.getRequestType());
	            response.setVersion(agentVersion);
	                           
	            
	            if (serverVersion.equals(agentVersion))
	            {
	            	if (Constants.REQUEST_TYPE_START_BUILD.equals(request.getRequestType()))
	                {
	            		try
	                    {
	                        startBuild();
	                        response.setParameter(Constants.BUILD_STATUS_SUCCESS, Boolean.TRUE);
	                        response.setParameter(Constants.BUILD_STATUS_REASON, "Build was successful");
	                        response.setParameter(Constants.AGENT_STATUS_LOWDISK, Boolean.FALSE);                        
	                    }             		
	                    catch (InstanceBusyException e)
	                    {
	                        response.setParameter(Constants.BUILD_STATUS_SUCCESS, Boolean.FALSE);
	                        response.setParameter(Constants.BUILD_STATUS_REASON, "Cannot start build, instance busy");
							response.setParameter(Constants.AGENT_STATUS_LOWDISK, Boolean.FALSE);
	                    }
	                    catch (InstanceDirectoryException e)
	                    {
	                        response.setParameter(Constants.BUILD_STATUS_SUCCESS, Boolean.FALSE);
	                        response.setParameter(Constants.BUILD_STATUS_REASON, "Could create instance directory");
							response.setParameter(Constants.AGENT_STATUS_LOWDISK, Boolean.FALSE);
	                    }
	                    catch (LowDiskException e)
	                    {
	                        response.setParameter(Constants.BUILD_STATUS_SUCCESS, Boolean.FALSE);
	                        response.setParameter(Constants.BUILD_STATUS_REASON, "Low disk space, cannot start a build");
							response.setParameter(Constants.AGENT_STATUS_LOWDISK, Boolean.TRUE);
	                    }                    
						catch (DataNotFoundException e)
						{
							response.setParameter(Constants.BUILD_STATUS_SUCCESS, Boolean.FALSE);
							response.setParameter(Constants.BUILD_STATUS_REASON, "Invalid logId: " + logId);
							response.setParameter(Constants.AGENT_STATUS_LOWDISK, Boolean.FALSE);
						} 
						catch (OptiployException e)
						{
							response.setParameter(Constants.BUILD_STATUS_SUCCESS, Boolean.FALSE);
	                        response.setParameter(Constants.BUILD_STATUS_REASON, "Cannot start build, fatal error");
							response.setParameter(Constants.AGENT_STATUS_LOWDISK, Boolean.FALSE);
						}
						
	                }
	            	else if (Constants.REQUEST_TYPE_BUILD_STATUS.equals(request.getRequestType()))
	                {
	            		String status = instance.getStatus();
	
	                    if (status == null)
	                    {
	                        status = "NO STATUS";
	                    }
	
	                    response.setParameter(Constants.BUILD_STATUS, status);
	                }
	
	                else if (Constants.REQUEST_TYPE_STOP_BUILD.equals(request.getRequestType()))
	                {
	                    stopBuild();
	                    response.setParameter(Constants.BUILD_STATUS_SUCCESS, Boolean.TRUE);
	                }
	
	                else if (Constants.REQUEST_TYPE_SHUTDOWN.equals(request.getRequestType()))
	                {
	                    shutdown = true;
	                    hardStop = "yes".equalsIgnoreCase((String) request.getParameter(Constants.AGENT_HARD_STOP))
	                            || "true".equalsIgnoreCase((String) request.getParameter(Constants.AGENT_HARD_STOP));
	                    response.setParameter(Constants.BUILD_STATUS_SUCCESS, Boolean.TRUE);
	                }
	
	                else
	                {
	                    response.setParameter(Constants.BUILD_STATUS_SUCCESS, Boolean.FALSE);
	                    response.setParameter(Constants.BUILD_STATUS_REASON, "Invalid requestType: " + request.getRequestType());
	                }
	            }	
	            else
	            {
	                response.setParameter(Constants.BUILD_STATUS_SUCCESS, Boolean.FALSE);
	                response.setParameter(Constants.BUILD_STATUS_REASON, "Incorrect version");
	            }
	            
	            logger.debug("Sending response packet."); 
	            generalUtil.loopThroughHashmap(response.getParameters(), null);            
	            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
	            out.writeObject(response);
	        }
        }	
        catch (IOException e)
		{
        	logger.error("IOException", e);
		} 
        catch (ClassNotFoundException e)
		{
        	logger.error("ClassNotFoundException", e);
		}	
        finally
        {
            try
            {
                socket.close();
            }

            catch (IOException e)
            {
                logger.error("Caught exception", e);
            }
        }

        if (shutdown)
        {
            try
            {
                logger.warn("Received message to terminate server, exiting system.");
                updateBuildStatus(Constants.AGENT_STATUS_SHUTDOWN, "Received message to terminate server, exiting system.");                
            }

            catch (Exception e)
            {
                e.printStackTrace();
            }

            while (!hardStop)
                ;

            System.exit(0);
        }        
    }
    
    public void startBuild() throws InstanceBusyException, InstanceDirectoryException, OptiployException, LowDiskException, DataNotFoundException
	{    
    	updateBuildStatus(Constants.BUILD_STATUS_STARTING, "AGENT: Build starting...");		
    	
    	instancePath = directory + instance.getId();
    	
    	logger.debug("Instance Path: " + instancePath);
		
    	
		if(!GeneralUtil.enoughSpace())
		{
			throw new LowDiskException("Low disk space, cannot start a build");	
		} 
		
	    try
	    {
	        rawLogFile = new File(optiployProperties.getPropertyValue(Constants.AGENT_LOG_DIRECTORY), logId + ".log");
	        zipLogFile = new File(optiployProperties.getPropertyValue(Constants.AGENT_LOG_DIRECTORY), logId + ".zip");
	        
	        logger.debug("Raw Log File Path: " + rawLogFile.getAbsolutePath());
	        logger.debug("Zip Log File Path: " + zipLogFile.getAbsolutePath());
	
	        // Initialize the build scripts
	        outputScripts();
			
	        // Execute the job
	        executeJob();
	
	        // Archive the log file
	        LogFile logFile = new LogFile();
	        logFile.setLogId(logId);
	        logFile.setLogFile(generalUtil.getBytesFromFile(zipLogFile));
	        logFileService.insert(logFile);
	    } 
	    catch (ValidationException e)
		{
	    	logger.error("ValidationException", e);
		} 
	    catch (IOException e)
		{
	    	logger.error("IOException", e);
		} 
	    catch (InterruptedException e)
		{
	    	logger.error("InterruptedException", e);
		}
	    
	    try
	    {		
	        if (!"false".equalsIgnoreCase(propertyCleanup.getValue()))
	        {
	            // Clear all the files
	            clearBuildDirectory(new File(instancePath));
	        }
	
	        // Finish up by setting the build status
	        updateBuildStatus(Constants.BUILD_STATUS_COMPLETE, "AGENT: " + buildMessage);
	    }
	
	    catch (Exception e)
	    {
	        logger.error("Error in build", e);
	
	        // Finish up by setting the build status
	        updateBuildStatus(Constants.BUILD_STATUS_ERROR, "AGENT: " + e.getClass().getName() + ": " + e.getMessage());
	    }
	
	    stopBuild = true;
	    sendCompleteMessage();
	}
    
    private void executeJob() throws IOException, DataNotFoundException, InterruptedException
    { 		
    	log.setAgentId(agent.getId());
    	log.setStart(new Date());
    	log.setJobId(job.getId());
    	logService.update(log);
    	
    	job = jobService.findById(jobId); 
    	
    	logger.info("Job Name: " + job.getName());
				
		//if(GeneralUtil.isWindows())
			makeFileName = propertyMakeFileWindows.getValue();
		//else if(GeneralUtil.isUnix())
			//makeFileName = propertyMakeFileUnix.getValue();
					
		makeFile = new File(instancePath, makeFileName);
		
        Runtime rt = Runtime.getRuntime();
        Process buildProc = rt.exec(makeFile.getAbsolutePath());

        // Start listening for the standard error
        StringBuffer stdErr = new StringBuffer();
        StreamReaderThread stdErrReader = new StreamReaderThread(buildProc.getErrorStream(), stdErr);
        stdErrReader.start();

        // Create input stream for standard out
        BufferedReader in = new BufferedReader(new InputStreamReader(buildProc.getInputStream()));

        // Start the timeout thread
        new AgentTimoutThread().start();

        // Create the output stream for the log file
        PrintWriter logOut = new PrintWriter(new FileOutputStream(rawLogFile));

        lastLine = new Date();
        String line = null;        

		updateBuildStatus(Constants.BUILD_STATUS_RUNNING, "AGENT: Build is running");        
        
		if(job.getProgressId() != null)
		{	
			progress = (Progress)progressService.findById(Integer.parseInt(job.getProgressId()));
			progressValue = progress.getParameter();
		}	
						
        while ((line = in.readLine()) != null && !stopBuild)
        {
            lastLine = new Date();
            logOut.println(line);
            logOut.flush();
            
			if(!progressValue.equals("") && line.indexOf((String) progressValue) != -1)
			{				
					String[] progressMessage = new String[3];
					progressMessage = line.split(progressValue);
										
					log.setBuildProgress(progressMessage[1].trim());
                	logService.update(log);
                	
					logger.debug("Progress Attribute: " + progressMessage[1].trim());					
			}
			
			Iterator<Rule> it = ruleService.getAllRules().iterator();
			
            while(it.hasNext())
            {
            	rule = (Rule)it.next();
            	
                if (rule.getRuleType() == 1 && line.indexOf((String) rule.getValue()) != -1)
                {
                    buildMessage = rule.getMessage();
                    stopBuild();
                } 
                else if (rule.getRuleType() == 2 && line.matches((String) rule.getValue()))
                {
                	buildMessage = rule.getMessage();
                    stopBuild();
                } 
                else if (rule.getRuleType() == 3 && line.startsWith((String) rule.getValue()))
                {
                	buildMessage = rule.getMessage();
                    stopBuild();
                } 
                else if (rule.getRuleType() == 4 && line.endsWith((String) rule.getValue()))
                {
                	buildMessage = rule.getMessage();
                    stopBuild();
                }
            }                       
        }

        if (!stopBuild)
        {
            stdErrReader.join();
            logOut.println(stdErr);
            logOut.flush();


            if (stdErr.indexOf("BUILD FAILED") == -1)
            {
                buildMessage = "Build was successfully completed.";
            }

            else
            {
                buildMessage = "Build failed. See build log for details.";
            }
        }

        buildProc.destroy();
        logOut.flush();
        logOut.close();

        FileInputStream logIn = new FileInputStream(rawLogFile);
        ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipLogFile));
        zipOut.putNextEntry(new ZipEntry("output.txt"));
        GeneralUtil.pipeInputStream(logIn, zipOut, 512, true);
    }
    
    private void stopBuild()
    {
        if (buildMessage == null)
        {
            buildMessage = "Stopping the build...";
        }
	        logger.warn("Stopping the build...");
	        stopBuild = true;
    }
    
    private void updateBuildStatus(String status, String message)
    {	
    	logger.debug("Update Build Status | Status: " + status + " Message: " + message);
    	    	
        try
        {
            if (message != null)
            {            	
            	log.setBuildMessage(message);
            	logService.update(log);
            }
            
            if(Constants.AGENT_STATUS_SHUTDOWN.equals(status))
            {
            	agent.setStatus(Constants.AGENT_STATUS_SHUTDOWN);
                agentService.update(agent);
            }
            else if(Constants.AGENT_STATUS_LOWDISK.equals(status))
            {
            	agent.setStatus(Constants.AGENT_STATUS_LOWDISK);
            	agentService.update(agent);
            }
            else if(Constants.BUILD_STATUS_STARTING.equalsIgnoreCase(status))
            {
            	instance.setStatus(Constants.BUILD_STATUS_STARTING);
                instanceService.update(instance);
            }
            else if(Constants.BUILD_STATUS_RUNNING.equalsIgnoreCase(status))
            {
            	instance.setStatus(Constants.INSTANCE_STATUS_RUNNING);
                instanceService.update(instance);
            }          
            else if (Constants.BUILD_STATUS_COMPLETE.equals(status))
            {
            	instance.setStatus(Constants.INSTANCE_STATUS_READY);
                instanceService.update(instance);
                
                log.setSuccess(true);
            	log.setComplete(new Date());
            	logService.update(log);
            }
            else if(Constants.BUILD_STATUS_ERROR.equals(status))
            {
            	instance.setStatus(Constants.INSTANCE_STATUS_READY);
                instanceService.update(instance);
                
                log.setSuccess(false);
            	log.setComplete(new Date());
            	logService.update(log);
            }   
        }
        catch (Exception e)
        {
            logger.error("Could not update build status", e);
        }        
    }
    
    private void outputScripts() throws DataNotFoundException, InstanceDirectoryException, ValidationException, IOException
    {   
    	String label = (String) attributeMap.get(Constants.BUILD_LABEL);
        File file = null;
        File path = new File(instancePath);   
        
        logger.debug("Making directory: " + instancePath); 
        
        boolean success = (new File(instancePath)).mkdir();
        
        logger.debug("Directory Created: " + instancePath);
        
        if(success)
        {
        	job = (Job) jobService.findById(jobId);
        	
        	Script script;
            
            Iterator<Script> it = job.getScripts().iterator();

            while(it.hasNext())
            {
            	script = (Script)it.next();
            	file = new File(instancePath, script.getFileName());
                FileOutputStream out = new FileOutputStream(file);
                out.write(script.getContent().getBytes());
                out.flush();
                out.close();
                
                logger.debug("Script output: " + generalUtil.replaceAttributes(script, attributeMap));
            }
           
            if (label != null)
            {
                file = new File(instancePath, "version.txt");
                FileOutputStream out = new FileOutputStream(file);
                out.write(label.getBytes());
                out.flush();
                out.close();
            }
        }
        else
        	throw new InstanceDirectoryException("Could create instance directory: " + instancePath);
                
    }
    
    private boolean clearBuildDirectory(File path)
    {    	
    	if(path.exists())
    	{	
    		File[] files = path.listFiles();
    		
    		for(int i=0; i < files.length; i++)
    		{
    			if(files[i].isDirectory())
    			{
    				clearBuildDirectory(files[i]);
    			}
    			else
    			{
    				files[i].delete();
    			}
    		}
    	}
    	
    	return(path.delete());    	
    }   
    
	private void sendCompleteMessage()
    {
        String url = propertyServletURL.getValue() + "?" + Constants.BUILD_LOG_ID + "=" + logId;
        //HttpClient client = new HttpClient();
        //GetMethod method = new GetMethod(url);
        boolean complete = false;
        Exception e = null;
        int i = 0;

        for (; i < 10 && !complete; i++)
        {
            try
            {
                //client.executeMethod(method);
                complete = true;
            }

            catch (Exception e1)
            {
                e = e1;
            }
        }

        if (!complete)
        {
            logger.error("Could not send build complete message", e);
        }

        else if (i > 1)
        {
            logger.warn("There was a problem sending the message, it took " + i + " attempts to send.", e);
        }

        else
        {
            logger.debug("The build message was sent successfully with no issues.");
        }
    }  
		
	private class StreamReaderThread extends Thread
    {
        private StringBuffer mOut;

        private InputStreamReader mIn;

        public StreamReaderThread(InputStream in, StringBuffer out)
        {
            mOut = out;
            mIn = new InputStreamReader(in);
        }

        public void run()
        {
            int ch;

            try
            {
                while (-1 != (ch = mIn.read()))
                    mOut.append((char) ch);
            }

            catch (Exception e)
            {
                mOut.append("\nRead error:" + e.getMessage());
            }
        }
    }
	
	private class AgentTimoutThread extends Thread
    {
        public void run()
        {
            long timeout = Long.parseLong(propertyBuildTimeout.getValue());

            try
            {
                while (!stopBuild)
                {
                    if (new Date().getTime() - lastLine.getTime() > timeout)
                    {
                        buildMessage = "Build has timed out!";
                        stopBuild();
                    }

                    else
                    {
                        sleep(timeout / 2);
                    }
                }
            }

            catch (Exception e)
            {
                try
                {
                	log.setBuildMessage("AGENT: Exception... " + e.getMessage());
                	logService.update(log);
                }

                catch (Exception e1)
                {
                    logger.fatal("Caught exception trying to append error message!!", e1);
                }
            }
        }
    }
	
    
    
}    
