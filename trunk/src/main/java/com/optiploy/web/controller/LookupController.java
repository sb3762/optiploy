package com.optiploy.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.optiploy.constants.Constants;
import com.optiploy.manager.LookupManager;
import com.optiploy.model.User;

public class LookupController implements Controller
{
	private static Logger logger = Logger.getLogger(LookupController.class);
		
	private LookupManager lookupManager;
	private String method;
	
	public ModelAndView handleRequest(HttpServletRequest request,
			 						   HttpServletResponse response)throws Exception 
    {		
		SecurityContext ctx = SecurityContextHolder.getContext();
		User user = (User) ctx.getAuthentication().getPrincipal();
		
		if(request.getParameter("method") != null)
		{	
			 method = request.getParameter("method");
		}
		else
		{
			 return new ModelAndView("home", "home", "");
		}
		 
		if(method.equalsIgnoreCase("user"))
		{
			return new ModelAndView(Constants.USER_LIST, Constants.USER_LIST, lookupManager.getAllUsersList());
		}
		else if(method.equalsIgnoreCase("role"))
		{
			return new ModelAndView(Constants.ROLE_LIST, Constants.ROLE_LIST, lookupManager.getAllRolesList());
		}
		else if(method.equalsIgnoreCase("release"))
		{
			return new ModelAndView(Constants.RELEASE_LIST, Constants.RELEASE_LIST, lookupManager.getAllReleasesList());
		}
		else if(method.equalsIgnoreCase("application"))
		{
			return new ModelAndView(Constants.APPLICATION_LIST, Constants.APPLICATION_LIST, lookupManager.getAllApplicationList());
		}
		else if(method.equalsIgnoreCase("environment"))
		{
			return new ModelAndView(Constants.ENVIRONMENT_LIST, Constants.ENVIRONMENT_LIST, lookupManager.getAllEnvironmentList());
		}
		else if(method.equalsIgnoreCase("function"))
		{
			return new ModelAndView(Constants.FUNCTION_LIST, Constants.FUNCTION_LIST, lookupManager.getAllFunctionList());
		}
		else if(method.equalsIgnoreCase("module"))
		{
			return new ModelAndView(Constants.MODULE_LIST, Constants.MODULE_LIST, lookupManager.getAllModuleList());
		}
		else if(method.equalsIgnoreCase("progress"))
		{
			return new ModelAndView(Constants.PROGRESS_LIST, Constants.PROGRESS_LIST, lookupManager.getAllProgressList());
		}		
			
			return new ModelAndView("home", "home", "");
		 
    }
	
	public void setLookupManager(LookupManager lookupManager)
	{
		this.lookupManager = lookupManager;
	}
}
