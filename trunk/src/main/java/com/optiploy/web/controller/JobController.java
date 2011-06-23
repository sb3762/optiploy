package com.optiploy.web.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.optiploy.constants.Constants;
import com.optiploy.model.Job;
import com.optiploy.service.JobService;
import com.optiploy.web.listener.StartupListener;


public class JobController extends BaseFormController
{
	private static Logger logger = Logger.getLogger(JobController.class);
	
	private String mode;	
	private JobService jobService;
	private Locale locale;
	
	public JobController()
	{
		setCommandClass(Job.class);
		setCommandName("job");		
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
				
		Job job = (Job) command;
		locale = request.getLocale(); 
		StartupListener.setupContext(request.getSession().getServletContext());
		
		if (request.getParameter(Constants.MODE_DELETE) != null) 
		{	
			jobService.remove(job);

			saveMessage(request, getText("job.deleted", job.getName(), locale));
			
			return new ModelAndView(getSuccessView());
        } 
		else 
		{		
			if(mode.equalsIgnoreCase(Constants.MODE_UPDATE))
			{					
				jobService.update(job);
	
				saveMessage(request, getText("job.saved", job.getName(), locale));
				
				return new ModelAndView(getSuccessView());
			}
			else if(mode.equalsIgnoreCase(Constants.MODE_ADD))
			{					
				jobService.insert(job);
				
				saveMessage(request, getText("job.added", job.getName(), locale));
				
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
		Job job = (Job) super.formBackingObject(request);
		
		if(request.getParameter(Constants.MODE) != null)
		{	
			mode = request.getParameter(Constants.MODE);
		}	
					
		if(mode.equalsIgnoreCase(Constants.MODE_ADD))
		{
			job = new Job();						
		}
		else if(mode.equalsIgnoreCase(Constants.MODE_UPDATE) || mode.equalsIgnoreCase(Constants.MODE_DETAIL))
		{
			job = (Job) jobService.findById(Integer.parseInt(request.getParameter("id")));
		}		
		      	
		return job;
	}
	
	protected void onBind(HttpServletRequest request, Object command)throws Exception 
	{
		if (request.getParameter(Constants.MODE_DELETE) != null) 
		{
            super.setValidateOnBinding(false);
        } 
		else 
		{
            super.setValidateOnBinding(true);
        }        
    }	
		
	@Required
	public void setJobService(JobService jobService)
	{
		this.jobService = jobService;
	}
	
}
