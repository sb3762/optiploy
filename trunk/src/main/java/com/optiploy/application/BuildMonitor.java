package com.optiploy.application;

import org.apache.log4j.Logger;

import com.optiploy.model.Log;
import com.optiploy.service.LogService;


public class BuildMonitor extends Thread
{
	private static Logger logger = Logger.getLogger(BuildMonitor.class);
	
	protected static boolean monitor = true;
	private Log log;
	private long interval;
	private LogService logService;
	
	public BuildMonitor()
	{
		
	}	
	
	public void run()
	{			
		while (monitor)
        {
			try
            {			
				monitor = log.getComplete() == null;
            
            	if(monitor)
            	{
            		logger.debug("BuildMonitor sleeping: " + interval);
            		sleep(interval);
            	} 
            	else
            	{
            		logger.debug("BuildMonitor stopping");
            	}
            	
            }
            catch(Exception e)
            {
            	logger.error("Exception in BuildMonitor: " + e);
            }                    
            
        }
	}
	public void setLog(Log log)
	{
		this.log = log;
	}
	public void setInterval(long interval)
	{
		this.interval = interval;
	}

}
