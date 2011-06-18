package com.optiploy.web.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.optiploy.model.Progress;
import com.optiploy.service.ProgressService;
import com.optiploy.web.listener.StartupListener;


public class ProgressController extends BaseFormController
{
	private static Logger logger = Logger.getLogger(ProgressController.class);
	
	private String mode;	
	private ProgressService progressService;
	private Locale locale;
	
	public ProgressController()
	{
		setCommandClass(Progress.class);
		setCommandName("progress");		
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
				
		Progress progress = (Progress) command;
		locale = request.getLocale(); 
		
		if (request.getParameter("delete") != null) 
		{	
			progressService.remove(progress);
			
			StartupListener.setupContext(request.getSession().getServletContext());

			saveMessage(request, getText("progress.deleted", progress.getName(), locale));
			
			return new ModelAndView(getSuccessView());
        } 
		else 
		{
		
			if(mode.equalsIgnoreCase("update"))
			{	
				progressService.update(progress);
				
				StartupListener.setupContext(request.getSession().getServletContext());
	
				saveMessage(request, getText("progress.saved", progress.getName(), locale));
				
				return new ModelAndView(getSuccessView());
			}
			else if(mode.equalsIgnoreCase("add"))
			{	
				progressService.insert(progress);
				
				StartupListener.setupContext(request.getSession().getServletContext());
				
				saveMessage(request, getText("progress.added", progress.getName(), locale));
				
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
		Progress progress = (Progress) super.formBackingObject(request);
		
		if(request.getParameter("mode") != null)
		{	
			mode = request.getParameter("mode");
		}	
					
		if(mode.equalsIgnoreCase("add"))
		{
			progress = new Progress();						
		}
		else if(mode.equalsIgnoreCase("update"))
		{
			progress = (Progress) progressService.findById(Integer.parseInt(request.getParameter("id")));
		}		
		      	
		return progress;
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
	public void setProgressService(ProgressService progressService)
	{
		this.progressService = progressService;
	}
	
}
