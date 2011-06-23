package com.optiploy.web.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.optiploy.constants.Constants;
import com.optiploy.model.Script;
import com.optiploy.service.ScriptService;
import com.optiploy.web.listener.StartupListener;


public class ScriptController extends BaseFormController
{
	private static Logger logger = Logger.getLogger(ScriptController.class);
	
	private String mode;	
	private ScriptService scriptService;
	private Locale locale;
	
	public ScriptController()
	{
		setCommandClass(Script.class);
		setCommandName("script");		
	}
	
	public ModelAndView processFormSubmission(HttpServletRequest request,
	         HttpServletResponse response,
	         Object command,
	         BindException errors)throws Exception 
	{	
		locale = request.getLocale();
		
		if (request.getParameter(Constants.MODE_CANCEL) != null) 
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
				
		Script script = (Script) command;
		locale = request.getLocale(); 
		StartupListener.setupContext(request.getSession().getServletContext());
		
		if (request.getParameter(Constants.MODE_DELETE) != null) 
		{	
			scriptService.remove(script);

			saveMessage(request, getText("script.deleted", script.getName(), locale));
			
			return new ModelAndView(getSuccessView());
        } 
		else 
		{
		
			if(mode.equalsIgnoreCase(Constants.MODE_UPDATE))
			{	
				script.setVersion(script.getVersion() + 1);
				
				scriptService.update(script);				
	
				saveMessage(request, getText("script.saved", script.getName(), locale));
				
				return new ModelAndView(getSuccessView());
			}
			else if(mode.equalsIgnoreCase(Constants.MODE_ADD))
			{	
				script.setVersion(1);
				
				scriptService.insert(script);
				
				saveMessage(request, getText("script.added", script.getName(), locale));
				
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
		Script script = (Script) super.formBackingObject(request);
		
		if(request.getParameter(Constants.MODE) != null)
		{	
			mode = request.getParameter(Constants.MODE);
		}	
					
		if(mode.equalsIgnoreCase(Constants.MODE_ADD))
		{
			script = new Script();						
		}
		else if(mode.equalsIgnoreCase(Constants.MODE_UPDATE))
		{
			script = (Script) scriptService.findById(Integer.parseInt(request.getParameter("id")));
		}		
		      	
		return script;
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
	public void setScriptService(ScriptService scriptService)
	{
		this.scriptService = scriptService;
	}
	
}
