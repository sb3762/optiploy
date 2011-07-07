package com.optiploy.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.jdom.Element;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.optiploy.constants.Constants;
import com.optiploy.exception.DataNotFoundException;
import com.optiploy.exception.ValidationException;
import com.optiploy.model.Attribute;
import com.optiploy.model.Property;
import com.optiploy.model.Script;
import com.optiploy.property.OptiployProperties;
import com.optiploy.service.PropertyService;

public class GeneralUtil
{
	private static Logger logger = Logger.getLogger(GeneralUtil.class);
	
	private static ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext-agent.xml");
	
	private static PropertyService propertyService = (PropertyService)applicationContext.getBean("propertyService");
	private static OptiployProperties optiployProperties = (OptiployProperties)applicationContext.getBean("optiployProperties");
	
	private static Property minDiskSpace = propertyService.getPropertyByPropertyName(Constants.AGENT_MIN_DISK_SPACE);
	
	public static String getTimeDate()
	{	
		Calendar dateTime = Calendar.getInstance();	
		
		SimpleDateFormat formatter = new SimpleDateFormat ("hh:mm:ss a z MM/dd/yyyy");
			
		return formatter.format(dateTime.getTime());
	}
	
	public static boolean enoughSpace()
	{
		boolean returnval = false;
			
		try
		{
			if(isWindows())
			{
				if(getFreeSpaceOnWindows() > Long.parseLong(minDiskSpace.getValue()))
				{
					logger.debug("Sufficient disk space. Proceeding with build.");
					returnval = true;
				}					
			}
			else if(isUnix())
			{
				returnval = true;
			}
				
		}
		catch (NumberFormatException e)
		{
			logger.error("Number Format Exception calculating disk space on agent",e);
		} 
		catch (Exception e)
		{
			logger.error("Error calculating disk space on agent",e);
		} 
	
			return returnval;			
	
	}
	
	private static long getFreeSpaceOnWindows() throws Exception
	{
		String path = optiployProperties.getPropertyValue(Constants.AGENT_BUILD_DIRECTORY);
				
		long bytesFree = -1;

		File script = new File(System.getProperty("java.io.tmpdir"),"script.bat");
		PrintWriter writer = new PrintWriter(new FileWriter(script, false));
		writer.println("dir \"" + path + "\"");
		writer.close();

		// Get the output from running the .bat file
		Process p = Runtime.getRuntime().exec(script.getAbsolutePath());
	
		InputStream reader = new BufferedInputStream(p.getInputStream());
	
		StringBuffer buffer = new StringBuffer();
		for (; ; )
		{
			int c = reader.read();
		
				if (c == -1)
				break;
			
				buffer.append( (char) c);
		}
	
		String outputText = buffer.toString();
		reader.close();

		// Parse the output text for the bytes free info
		StringTokenizer tokenizer = new StringTokenizer(outputText, "\n");
	
		while (tokenizer.hasMoreTokens())
		{
			String line = tokenizer.nextToken().trim();
		
			// See if line contains the bytes free information
			if (line.endsWith("bytes free"))
			{
				tokenizer = new StringTokenizer(line, " ");
				tokenizer.nextToken();
				tokenizer.nextToken();
				bytesFree = Long.parseLong(tokenizer.nextToken().replaceAll(",", ""));
			}
		}
			logger.debug("Free bytes on this agent: " + bytesFree);
			
			return bytesFree;
	}
	
	public static Object addSingleObjectToArray(Object array, Object toAdd, boolean atBegin)
    {
        if (array == null)
        {
            throw new IllegalArgumentException("Cannot add an element to " + "a null array.");
        } else if (!array.getClass().isArray())
        {
            throw new IllegalArgumentException("Cannot add an element to " + "an object that is not an array.");
        }

        int length = Array.getLength(array);
        Object result = Array.newInstance(array.getClass().getComponentType(), length + 1);
        System.arraycopy(array, 0, result, atBegin ? 1 : 0, length);
        Array.set(result, atBegin ? 0 : length, toAdd);
        return result;
    }
	
	public static String replace(String src, String oldstr, String newstr)
    {
        StringBuffer buff = new StringBuffer(src);
        int cursor = 0;
        int i;

        while ((i = buff.toString().indexOf(oldstr, cursor)) > 0)
        {
            buff.replace(i, i + oldstr.length(), newstr);
            cursor = i + newstr.length();
        }

        return buff.toString();
    }
	
	public static void pipeInputStream(InputStream in, OutputStream out, int buffSize) throws IOException
    {
        pipeInputStream(in, out, buffSize, false);
    }

    public static void pipeInputStream(InputStream in, OutputStream out, int buffSize, boolean close)throws IOException
    {
        int read;
        byte[] buff = new byte[buffSize];
        while ((read = in.read(buff)) > 0)
        {
            out.write(buff, 0, read);
        }

        if (close)
        {
            in.close();
            out.close();
        }
    }
    
    public static byte[] getBytesFromFile(File file) throws IOException 
    {
        InputStream is = new FileInputStream(file);
        
	    // Get the size of the file
	    long length = file.length();
	
	    if (length > Integer.MAX_VALUE) 
	    {
	        // File is too large
	    }
	
	    // Create the byte array to hold the data
	    byte[] bytes = new byte[(int)length];
	
	    // Read in the bytes
	    int offset = 0;
	    int numRead = 0;
	    while (offset < bytes.length && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) 
	    {
	        offset += numRead;
	    }
	
	    // Ensure all the bytes have been read in
	    if (offset < bytes.length) 
	    {
	        throw new IOException("Could not completely read file "+file.getName());
	    }
	
	    // Close the input stream and return bytes
	    is.close();
	    
	    return bytes;
    }
    
    public static void loopThroughHashmap(HashMap hashmap, String level)
    {
    	Collection keys = hashmap.keySet();
        Collection values = hashmap.values();
        
        Iterator itKeys = keys.iterator();
        Iterator itVals = values.iterator();
        
        if(level == null)
        	level = "";
        
        while(itKeys.hasNext())
        {
        	if(level.equals("info"))
        		logger.info(itKeys.next() + ": " + itVals.next());
        	else if(level.equals("warn"))
        		logger.warn(itKeys.next() + ": " + itVals.next());        	
        	else if(level.equals("error"))
        		logger.error(itKeys.next() + ": " + itVals.next());
        	else if(level.equals("fatal"))
        		logger.fatal(itKeys.next() + ": " + itVals.next());
        	else
        		logger.debug(itKeys.next() + ": " + itVals.next());
        }    	
    }
    
    public static String replaceAttributes(Script script, Map attributeValues) throws ValidationException, DataNotFoundException
	{
    	Attribute attribute;
    	
		String result = script.getContent();
		
		Set attributes = script.getAttributes();
		
		Iterator it = attributes.iterator();
		
		
		if (script.getFileName().toLowerCase().endsWith(".xml"))
		{
		    try
		    {
		        Element elem = XmlHelper.getRootElement(result);
		        String dyn = XmlHelper.getDynamicXML(elem, attributeValues);
		        if (dyn != null)
		            result = dyn;
		    }
		
		    catch (Exception e)
		    {
		        e.printStackTrace();
		    }
		}
		
		while(it.hasNext())
		{	
			
			attribute = (Attribute)it.next();
					
		    if (attribute.getMapKey() == null || attribute.getReplaceText() == null)
		        throw new ValidationException("Attribute " + attribute.getName() + " is required");
		    
		    if(attributeValues.containsKey(attribute.getMapKey()))		
		    	result = replace(result, attribute.getReplaceText(), (String)attributeValues.get(attribute.getMapKey()));
		}
		
		return result;
	}
    
    public static int[] openPorts(int startPortRange, int stopPortRange, int numberOfPorts)
    {
    	logger.debug("Checking for " + numberOfPorts + " open ports");
    	
    	int ports[] = new int[numberOfPorts];
    	
    	int portCounter = 0;
    	
    	for(int i=startPortRange;i<=stopPortRange;i++)
    	{
    		try
    		{
    			logger.debug("Checking port: " + i);
    			
    			Socket socket = new Socket("127.0.0.1",i);
    			
    			socket.close();
    		}
    		catch(EOFException e)
    		{
    			// Do nothing, go on to next port
    		}
    		catch(IOException e)
    		{
    			logger.debug("Open port: " + i);
    			
    			ports[portCounter] = i;
    			
    			portCounter++;
    		}    		
    		
    		if(portCounter >= numberOfPorts)
    			break;
    		
    	}
    	
    	return ports;    	
    }
    
	public static boolean isWindows()
    {
	    if(System.getProperty("os.name").contains("Windows"))		
	   		 return true;
	    else if(System.getProperty("os.name").contains("windows"))		
	   		return true;
	    else
	   	 	return false;
    }
    public static boolean isUnix()
    {
	    if(System.getProperty("os.name").contains("Unix"))		
	   		return true;
	    else if(System.getProperty("os.name").contains("unix"))		
	   		return true;
	    else if(System.getProperty("os.name").contains("Linux"))		
	   		return true;
	    else if(System.getProperty("os.name").contains("linux"))		
	   		return true;
	    else
	   	 	return false;
     }
	

}
