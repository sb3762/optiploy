package com.optiploy.application;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.optiploy.constants.Constants;
import com.optiploy.exception.DataNotFoundException;
import com.optiploy.exception.OptiployException;
import com.optiploy.model.Agent;
import com.optiploy.model.Instance;
import com.optiploy.model.Log;
import com.optiploy.model.Property;
import com.optiploy.packet.Packet;
import com.optiploy.service.AgentService;
import com.optiploy.service.InstanceService;
import com.optiploy.service.LogService;
import com.optiploy.service.PropertyService;

public class BuildApplication
{	
	private static Logger logger = Logger.getLogger(BuildApplication.class);
	
	private PropertyService propertyService;
	private AgentService agentService;
	private InstanceService instanceService;
	private LogService logService;
	private BuildMonitor buildMonitor;
	
	private Agent agent;
	
	public BuildApplication()
	{
		
	}	
	
	public boolean startBuild(int jobId, HashMap<String, String> parameterMap) throws DataNotFoundException, OptiployException
    {	
		logger.debug("Starting Build | Job ID: " + jobId);
		
		Log log = new Log();
		
		boolean buildStarted = false;
			
		List<Instance> instances = instanceService.getReadyInstances();
		
		logger.debug("Ready instances: " + instances.size());
				
    	Property propertyVersion = propertyService.getPropertyByPropertyName(Constants.VERSION);
		Property propertyServletURL = propertyService.getPropertyByPropertyName(Constants.SERVLET_URL);
		Property propertyBuildMonitorInterval = propertyService.getPropertyByPropertyName(Constants.BUILD_MONITOR_INTERVAL);
		Property propertyLocalTest = propertyService.getPropertyByPropertyName(Constants.ADMIN_LOCAL_TEST);
				
		String version = propertyVersion.getValue();
		String servletUrl = propertyServletURL.getValue();
		long buildMonitorInterval = Long.parseLong(propertyBuildMonitorInterval.getValue());
	
		
		for (int i = 0; !buildStarted && i < instances.size(); i++)
		{				
        	Socket socket = null;

            try
            {
            	agent = (Agent) agentService.findById(instances.get(i).getAgentId()); 
            	
            	logger.debug("Agent: " + agent.getName() + " | Instance: " + instances.get(i).getPort());
            	
            	if(propertyLocalTest.getValue().equals("true"))
            		socket = new Socket("127.0.0.1", instances.get(i).getPort());
            	else
            		socket = new Socket(agent.getAddress(), instances.get(i).getPort());
            	
            	Packet request = new Packet();            	
            	
            	request.setRequestType(Constants.REQUEST_TYPE_START_BUILD);
            	request.setVersion(version);
            	request.setJobId(jobId);
            	request.setParameters(parameterMap);
            	request.setParameter(Constants.SERVLET_URL, servletUrl);
            	
            	ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

                logger.debug("Sending request packet: " + request.getRequestType());
                out.writeObject(request);

                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                Packet response = (Packet) in.readObject();
                log = logService.findById(response.getLogId());
                logger.debug("Received response packet | Success: " + response.getParameter(Constants.BUILD_STATUS_SUCCESS) +
                									 " | Reason: " + response.getParameter(Constants.BUILD_STATUS_REASON));
                                
	            if (version.equals(response.getVersion()))
	            {
	                boolean success = response.getParameter(Constants.BUILD_STATUS_SUCCESS) == null
	                        ? false
	                        : ((Boolean) response.getParameter(Constants.BUILD_STATUS_SUCCESS)).booleanValue();               
	                
	                if (success)
	                {  
	                	
	                	
	                	log.setBuildMessage("SERVER: Agent ID "
	                            + agent.getId()
	                            + " (v"
	                            + version
	                            + ") successfully started build.");
	                	
	                	logService.update(log);
	                	              
	                	buildStarted = true;	                	
	                	
	                	try
						{ 
	                		buildMonitor = new BuildMonitor();
	                		
	                		buildMonitor.setLog(log);
		                	buildMonitor.setInterval(buildMonitorInterval);
		                	buildMonitor.start(); 
							buildMonitor.join();
						} 
	                	catch (InterruptedException e)
						{
	                		log.setBuildMessage("SERVER: Agent ID "
									+ instances.get(i).getAgentId()
									+ " build was interrupted");
	                		
	                		logService.update(log);
	                		
	                		logger.error("BuildApplication Interrupted: " + e);
	                		
							return false;
						}
	                	catch(IllegalThreadStateException e)
	                	{
	                		log.setBuildMessage("SERVER: Agent ID "
									+ instances.get(i).getAgentId()
									+ " IllegalThreadStateException");
	                		
	                		logService.update(log);
	                		
	                		logger.error("IllegalThreadStateException: " + e);
	                		
	                		return false;
	                	}
	                		 
	                		return true;
	                }	
	                else
	                {
						String reason = (String) response.getParameter(Constants.BUILD_STATUS_REASON);
						
						log.setBuildMessage("SERVER: Agent ID "
								+ instances.get(i).getAgentId()
								+ " could not start due to reason: "
								+ reason);
						
						logService.update(log);
						
	                	if(((Boolean) response.getParameter(Constants.AGENT_STATUS_LOWDISK)).booleanValue())
	                	{	
	                		agent.setStatus(Constants.AGENT_STATUS_LOWDISK);
	                		log.setBuildMessage("The following agent has lowdisk: " + agent.getName());	  
	                		
	                		logService.update(log);
	                	}	                	        
	                }
	            }	
	            else
	            {
	            	log.setBuildMessage("SERVER: Version returned from Agent ID "
	                        + agent.getId()
	                        + " was not correct: "
	                        + response.getVersion());
	            	agent.setStatus(Constants.AGENT_STATUS_INCOMPATIBLE);
	            	log.setBuildMessage("The following agent is incompatible: " + agent.getName());	  
	            	
	            	logService.update(log);
	            }
	        }	
	        catch (IOException e)
	        {
	        	log.setBuildMessage("SERVER: IOException caught trying to connect to Agent ID "
	                    + agent.getId()
	                    + ": "
	                    + e.getMessage());
	        	agent.setStatus(Constants.AGENT_STATUS_DOWN);
	        	log.setBuildMessage("The following agent is down: " + agent.getName());	
	        	
	        	logService.insert(log);
	        }	
	        catch (ClassNotFoundException e)
	        {
	        	log.setBuildMessage("SERVER: ClassNotFoundException caught trying to connect to Agent ID "
	                    + agent.getId()
	                    + ": "
	                    + e.getMessage());
	        	agent.setStatus(Constants.AGENT_STATUS_INCOMPATIBLE);
	        	log.setBuildMessage("The following agent is incompatible: " + agent.getName());
	        	
	        	logService.insert(log);
	        }	
	        finally
	        {
	            try
	            {	            	
	                if (socket != null)
	                    socket.close();
	                
	                	buildMonitor.interrupt();
	                
	                	agentService.update(agent);     
	            }
	
	            catch (IOException e)
	            {
	            	log.setBuildMessage("SERVER: IOException caught trying to close connection to Agent ID "
	                                + agent.getId()
	                                + ": "
	                                + e.getMessage());
	            }
	        }
	    }	
		
	    if (!buildStarted)
	    {
	        String message = "Could not successfully connect to any agents to perform build";
	
	        if (instances.size() == 0)
	        {
	            message = "Please wait for an available agent and retry build!";
	        }
	        	        
	        log.setSuccess(false);
	        log.setComplete(new Date());
	        log.setBuildMessage((log.getBuildMessage() == null ? "" : log.getBuildMessage() + "\n")
	                + "SERVER: Canceling build, "
	                + message);
	        
	        logService.insert(log);
	    }
	    
	    return false;		
    }	
	
	@Required
	public void setAgentService(AgentService agentService)
	{
		this.agentService = agentService;
	}
		
	@Required
	public void setPropertyService(PropertyService propertyService)
	{
		this.propertyService = propertyService;
	}
	
	@Required
	public void setInstanceService(InstanceService instanceService)
	{
		this.instanceService = instanceService;
	}
	
	@Required
	public void setLogService(LogService logService)
	{
		this.logService = logService;
	}
	
	@Required
	public void setBuildMonitor(BuildMonitor buildMonitor)
	{
		this.buildMonitor = buildMonitor;
	}

}
