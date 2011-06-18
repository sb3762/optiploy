package com.optiploy.web.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.optiploy.model.Environment;
import com.optiploy.service.EnvironmentService;
import com.optiploy.web.listener.StartupListener;


public class EnvironmentController extends BaseFormController
{
	private static Logger logger = Logger.getLogger(EnvironmentController.class);
	
	private String mode;	
	private EnvironmentService environmentService;
	private Locale locale;
	
	public EnvironmentController()
	{
		setCommandClass(Environment.class);
		setCommandName("environment");		
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
				
		Environment environment = (Environment) command;
		locale = request.getLocale(); 
		
		if (request.getParameter("delete") != null) 
		{	
			environmentService.remove(environment);
			
			StartupListener.setupContext(request.getSession().getServletContext());

			saveMessage(request, getText("environment.deleted", environment.getName(), locale));
			
			return new ModelAndView(getSuccessView());
        } 
		else 
		{
		
			if(mode.equalsIgnoreCase("update"))
			{	
				environmentService.update(environment);
				
				StartupListener.setupContext(request.getSession().getServletContext());
	
				saveMessage(request, getText("environment.saved", environment.getName(), locale));
				
				return new ModelAndView(getSuccessView());
			}
			else if(mode.equalsIgnoreCase("add"))
			{	
				environmentService.insert(environment);
				
				StartupListener.setupContext(request.getSession().getServletContext());
				
				saveMessage(request, getText("environment.added", environment.getName(), locale));
				
				return new ModelAndView(getSuccessView());
			}
			else
			{
				logger.error("Mode is null or not known value");
			}					
			
		}
		
		return null;	
		
	}
	
		
	protected Object formBackingObject(HttpServletRequest request)throws Exception
	{
		Environment environment = (Environment) super.formBackingObject(request);
		
		if(request.getParameter("mode") != null)
		{	
			mode = request.getParameter("mode");
		}	
					
		if(mode.equalsIgnoreCase("add"))
		{
			environment = new Environment();						
		}
		else if(mode.equalsIgnoreCase("update"))
		{
			environment = (Environment) environmentService.findById(Integer.parseInt(request.getParameter("id")));
		}		
		      	
		return environment;
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
	public void setEnvironmentService(EnvironmentService environmentService)
	{
		this.environmentService = environmentService;
	}
	
}
