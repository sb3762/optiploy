package com.optiploy.web.controller;

import java.io.ByteArrayOutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.optiploy.model.LogFile;
import com.optiploy.service.LogFileService;
import com.optiploy.util.GeneralUtil;
import com.optiploy.web.listener.StartupListener;


public class LogFileController extends AbstractController
{

private static Logger logger = Logger.getLogger(LogFileController.class);
	
	private LogFileService logFileService;	
	private LogFile logFile;
	
	private Locale locale;
	
	@SuppressWarnings("unchecked")
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)throws UsernameNotFoundException
	{	
		StartupListener.setupContext(request.getSession().getServletContext());	
		
		locale = request.getLocale();
		
		HashMap<String, String> parameterMap = new HashMap<String, String>();
		
		Enumeration<String> enumeration = request.getParameterNames();
		
		while(enumeration.hasMoreElements())
		{
			String key = (String)enumeration.nextElement();
			String value = request.getParameter(key);
			
			parameterMap.put(key, value);			
		}				
			
		if(parameterMap.containsKey("logId"))
		{
			try
			{
				logFile = logFileService.findByLogId(Integer.parseInt(parameterMap.get("logId")));
				
				response.setContentType("data");
		        response.setHeader("content-disposition", "attachment; filename=" + parameterMap.get("logId") + ".zip");		        
		        
		        GeneralUtil.pipeInputStream(logFile.getLogFile().getBinaryStream(), response.getOutputStream(), response.getBufferSize());				
				
			} 
			catch (Exception e)
			{
				logger.error("Exception: " + e);
			}
		}	
		
		return null;
	}
		
	@Required
	public void setLogFileService(LogFileService logFileService)
	{
		this.logFileService = logFileService;
	}	
}
