package com.optiploy.web.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.optiploy.constants.Constants;
import com.optiploy.model.Function;
import com.optiploy.service.FunctionService;
import com.optiploy.web.listener.StartupListener;


public class FunctionController extends BaseFormController
{
	private static Logger logger = Logger.getLogger(FunctionController.class);
	
	private String mode;	
	private FunctionService functionService;
	private Locale locale;
	
	public FunctionController()
	{
		setCommandClass(Function.class);
		setCommandName("function");		
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
				
		Function function = (Function) command;
		locale = request.getLocale(); 
		StartupListener.setupContext(request.getSession().getServletContext());
		
		if (request.getParameter(Constants.MODE_DELETE) != null) 
		{	
			functionService.remove(function);			

			saveMessage(request, getText("function.deleted", function.getName(), locale));
			
			return new ModelAndView(getSuccessView());
        } 
		else 
		{		
			if(mode.equalsIgnoreCase(Constants.MODE_UPDATE))
			{	
				functionService.update(function);
	
				saveMessage(request, getText("function.saved", function.getName(), locale));
				
				return new ModelAndView(getSuccessView());
			}
			else if(mode.equalsIgnoreCase(Constants.MODE_ADD))
			{	
				functionService.insert(function);
				
				saveMessage(request, getText("function.added", function.getName(), locale));
				
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
		Function function = (Function) super.formBackingObject(request);
		
		if(request.getParameter(Constants.MODE) != null)
		{	
			mode = request.getParameter(Constants.MODE);
		}	
					
		if(mode.equalsIgnoreCase(Constants.MODE_ADD))
		{
			function = new Function();						
		}
		else if(mode.equalsIgnoreCase(Constants.MODE_UPDATE))
		{
			function = (Function) functionService.findById(Integer.parseInt(request.getParameter("id")));
		}		
		      	
		return function;
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
	public void setFunctionService(FunctionService functionService)
	{
		this.functionService = functionService;
	}
	
}
