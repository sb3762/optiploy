package com.optiploy.web.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.optiploy.model.Application;
import com.optiploy.service.ApplicationService;
import com.optiploy.web.listener.StartupListener;


public class ApplicationController extends BaseFormController
{
	private static Logger logger = Logger.getLogger(ApplicationController.class);
	
	private String method;	
	private ApplicationService applicationService;
	private Locale locale;
	
	public ApplicationController()
	{
		setCommandClass(Application.class);
		setCommandName("application");		
	}
	
	public ModelAndView processFormSubmission(HttpServletRequest request,
	         HttpServletResponse response,
	         Object command,
	         BindException errors)throws Exception 
	{	
		locale = request.getLocale();
		
		if (request.getParameter("cancel") != null) 
		{
			saveMessage(request, getText("errors.cancel","", locale));
			
			return new ModelAndView(getCancelView());
		}		
		
		return super.processFormSubmission(request, response, command, errors);
	}
	
	public ModelAndView onSubmit(HttpServletRequest request,
            HttpServletResponse response,
            Object command,
            BindException errors)throws Exception
    {
				
		Application application = (Application) command;
		locale = request.getLocale(); 
		
		if (request.getParameter("delete") != null) 
		{	
			applicationService.remove(application);
			
			StartupListener.setupContext(request.getSession().getServletContext());

			saveMessage(request, getText("application.deleted", application.getName(), locale));
			
			return new ModelAndView(getSuccessView());
        } 
		else 
		{
		
			if(method.equalsIgnoreCase("update"))
			{	
				applicationService.update(application);
				
				StartupListener.setupContext(request.getSession().getServletContext());
	
				saveMessage(request, getText("application.saved", application.getName(), locale));
				
				return new ModelAndView(getSuccessView());
			}
			else if(method.equalsIgnoreCase("add"))
			{	
				applicationService.insert(application);
				
				StartupListener.setupContext(request.getSession().getServletContext());
				
				saveMessage(request, getText("application.added", application.getName(), locale));
				
				return new ModelAndView(getSuccessView());
			}
			else
			{
				logger.error("Method is null or not known value");
			}					
			
		}
		
		return null;	
		
	}
	
		
	protected Object formBackingObject(HttpServletRequest request)throws Exception
	{
		Application application = (Application) super.formBackingObject(request);
		
		if(request.getParameter("method") != null)
		{	
			method = request.getParameter("method");
		}	
					
		if(method.equalsIgnoreCase("add"))
		{
			application = new Application();						
		}
		else if(method.equalsIgnoreCase("update"))
		{
			application = (Application) applicationService.findById(Integer.parseInt(request.getParameter("id")));
		}		
		      	
		return application;
	}
	
	protected void onBind(HttpServletRequest request, Object command)throws Exception 
	{
		if (request.getParameter("delete") != null) 
		{
            super.setValidateOnBinding(false);
        } 
		else 
		{
            super.setValidateOnBinding(true);
        }        
    }	
		
	@Required
	public void setApplicationService(ApplicationService applicationService)
	{
		this.applicationService = applicationService;
	}
	
}
