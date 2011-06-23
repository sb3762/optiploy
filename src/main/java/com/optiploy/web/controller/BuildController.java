package com.optiploy.web.controller;

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

import com.optiploy.application.BuildApplication;
import com.optiploy.exception.DataNotFoundException;
import com.optiploy.exception.OptiployException;
import com.optiploy.web.listener.StartupListener;


public class BuildController extends AbstractController
{

private static Logger logger = Logger.getLogger(BuildController.class);
	
	private BuildApplication buildApplication;
	private Locale locale;
	
	
	public BuildController()
	{
		
	}
	
	@SuppressWarnings("unchecked")
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)throws UsernameNotFoundException
	{		
		StartupListener.setupContext(request.getSession().getServletContext());	
		
		locale = request.getLocale();
		
		boolean buildSuccess = false;
				
		HashMap<String, String> parameterMap = new HashMap<String, String>();
		
		Enumeration<String> enumeration = request.getParameterNames();
		
		while(enumeration.hasMoreElements())
		{
			String key = (String)enumeration.nextElement();
			String value = request.getParameter(key);
			
			parameterMap.put(key, value);			
		}
		
		if(parameterMap.containsKey("jobId"))
		{
			int jobId = Integer.parseInt((String) parameterMap.get("jobId"));
			
			parameterMap.remove("jobId");
						
			try
			{
				buildSuccess = buildApplication.startBuild(jobId, parameterMap);
			} 
			catch (DataNotFoundException e)
			{
				logger.error("DataNotFoundException: " + e);
			} 
			catch (OptiployException e)
			{
				logger.error("OptiployException: " + e);
			}
			
		}		
		
		if(buildSuccess)		
			return new ModelAndView("buildSuccess","buildSuccess",null);				
		else
			return new ModelAndView("buildFailure","buildFailure",null);
				
	}	
		
	@Required
	public void setBuildApplication(BuildApplication buildApplication)
	{
		this.buildApplication = buildApplication;
	}	
}
