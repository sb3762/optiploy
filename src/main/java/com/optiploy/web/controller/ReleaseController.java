package com.optiploy.web.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.optiploy.model.Release;
import com.optiploy.service.ReleaseService;
import com.optiploy.web.listener.StartupListener;


public class ReleaseController extends BaseFormController
{
	private static Logger logger = Logger.getLogger(ReleaseController.class);
	
	private String mode;	
	private ReleaseService releaseService;
	private Locale locale;
	
	public ReleaseController()
	{
		setCommandClass(Release.class);
		setCommandName("release");		
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
				
		Release release = (Release) command;
		locale = request.getLocale(); 
		
		if (request.getParameter("delete") != null) 
		{	
			releaseService.remove(release);
			
			StartupListener.setupContext(request.getSession().getServletContext());

			saveMessage(request, getText("release.deleted", release.getName(), locale));
			
			return new ModelAndView(getSuccessView());
        } 
		else 
		{
		
			if(mode.equalsIgnoreCase("update"))
			{	
				releaseService.update(release);
				
				StartupListener.setupContext(request.getSession().getServletContext());
	
				saveMessage(request, getText("release.saved", release.getName(), locale));
				
				return new ModelAndView(getSuccessView());
			}
			else if(mode.equalsIgnoreCase("add"))
			{	
				releaseService.insert(release);
				
				StartupListener.setupContext(request.getSession().getServletContext());
				
				saveMessage(request, getText("release.added", release.getName(), locale));
				
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
		Release release = (Release) super.formBackingObject(request);
		
		if(request.getParameter("mode") != null)
		{	
			mode = request.getParameter("mode");
		}	
					
		if(mode.equalsIgnoreCase("add"))
		{
			release = new Release();						
		}
		else if(mode.equalsIgnoreCase("update"))
		{
			release = (Release) releaseService.findById(Integer.parseInt(request.getParameter("id")));
		}		
		      	
		return release;
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
	public void setReleaseService(ReleaseService releaseService)
	{
		this.releaseService = releaseService;
	}
	
}
