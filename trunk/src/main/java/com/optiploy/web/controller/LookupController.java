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
	private String mode;
	
	public ModelAndView handleRequest(HttpServletRequest request,
			 						   HttpServletResponse response)throws Exception 
    {		
		SecurityContext ctx = SecurityContextHolder.getContext();
		User user = (User) ctx.getAuthentication().getPrincipal();
		
		if(request.getParameter("mode") != null)
		{	
			 mode = request.getParameter("mode");
		}
		else
		{
			 return new ModelAndView("home", "home", "");
		}
		 
		if(mode.equalsIgnoreCase("user"))
		{
			return new ModelAndView(Constants.USER_LIST, Constants.USER_LIST, lookupManager.getAllUsersList());
		}
		else if(mode.equalsIgnoreCase("role"))
		{
			return new ModelAndView(Constants.ROLE_LIST, Constants.ROLE_LIST, lookupManager.getAllRolesList());
		}
		else if(mode.equalsIgnoreCase("release"))
		{
			return new ModelAndView(Constants.RELEASE_LIST, Constants.RELEASE_LIST, lookupManager.getAllReleasesList());
		}
		else if(mode.equalsIgnoreCase("application"))
		{
			return new ModelAndView(Constants.APPLICATION_LIST, Constants.APPLICATION_LIST, lookupManager.getAllApplicationList());
		}
		else if(mode.equalsIgnoreCase("environment"))
		{
			return new ModelAndView(Constants.ENVIRONMENT_LIST, Constants.ENVIRONMENT_LIST, lookupManager.getAllEnvironmentList());
		}
		else if(mode.equalsIgnoreCase("function"))
		{
			return new ModelAndView(Constants.FUNCTION_LIST, Constants.FUNCTION_LIST, lookupManager.getAllFunctionList());
		}
		else if(mode.equalsIgnoreCase("module"))
		{
			return new ModelAndView(Constants.MODULE_LIST, Constants.MODULE_LIST, lookupManager.getAllModuleList());
		}
		else if(mode.equalsIgnoreCase("progress"))
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
