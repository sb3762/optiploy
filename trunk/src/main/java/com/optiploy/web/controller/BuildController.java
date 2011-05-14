package com.optiploy.web.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.optiploy.application.BuildApplication;
import com.optiploy.constants.Constants;
import com.optiploy.exception.DataNotFoundException;
import com.optiploy.exception.OptiployException;
import com.optiploy.model.Agent;
import com.optiploy.model.Property;
import com.optiploy.model.Role;
import com.optiploy.model.User;
import com.optiploy.service.AgentService;
import com.optiploy.service.LogService;
import com.optiploy.service.PropertyService;
import com.optiploy.service.RoleService;
import com.optiploy.service.UserService;
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
				boolean buildSuccess = buildApplication.startBuild(jobId, parameterMap);
			} 
			catch (DataNotFoundException e)
			{
				e.printStackTrace();
			} 
			catch (OptiployException e)
			{
				e.printStackTrace();
			}
			
		}		
		
		return new ModelAndView("login","login",null);
				
	}	
		
	@Required
	public void setBuildApplication(BuildApplication buildApplication)
	{
		this.buildApplication = buildApplication;
	}	
}
