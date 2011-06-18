package com.optiploy.web.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.optiploy.model.Module;
import com.optiploy.service.ModuleService;
import com.optiploy.web.listener.StartupListener;


public class ModuleController extends BaseFormController
{
	private static Logger logger = Logger.getLogger(ModuleController.class);
	
	private String mode;	
	private ModuleService moduleService;
	private Locale locale;
	
	public ModuleController()
	{
		setCommandClass(Module.class);
		setCommandName("module");		
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
				
		Module module = (Module) command;
		locale = request.getLocale(); 
		
		if (request.getParameter("delete") != null) 
		{	
			moduleService.remove(module);
			
			StartupListener.setupContext(request.getSession().getServletContext());

			saveMessage(request, getText("module.deleted", module.getName(), locale));
			
			return new ModelAndView(getSuccessView());
        } 
		else 
		{
		
			if(mode.equalsIgnoreCase("update"))
			{	
				moduleService.update(module);
				
				StartupListener.setupContext(request.getSession().getServletContext());
	
				saveMessage(request, getText("module.saved", module.getName(), locale));
				
				return new ModelAndView(getSuccessView());
			}
			else if(mode.equalsIgnoreCase("add"))
			{	
				moduleService.insert(module);				
				
				StartupListener.setupContext(request.getSession().getServletContext());
				
				saveMessage(request, getText("module.added", module.getName(), locale));
				
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
		Module module = (Module) super.formBackingObject(request);
		
		if(request.getParameter("mode") != null)
		{	
			mode = request.getParameter("mode");
		}	
					
		if(mode.equalsIgnoreCase("add"))
		{
			module = new Module();						
		}
		else if(mode.equalsIgnoreCase("update"))
		{
			module = (Module) moduleService.findById(Integer.parseInt(request.getParameter("id")));
		}		
		      	
		return module;
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
	public void setModuleService(ModuleService moduleService)
	{
		this.moduleService = moduleService;
	}
	
}
