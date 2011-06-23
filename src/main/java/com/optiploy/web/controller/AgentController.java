package com.optiploy.web.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.optiploy.constants.Constants;
import com.optiploy.model.Agent;
import com.optiploy.service.AgentService;
import com.optiploy.web.listener.StartupListener;


public class AgentController extends BaseFormController
{
	private static Logger logger = Logger.getLogger(AgentController.class);
	
	private String mode;	
	private AgentService agentService;
	private Locale locale;
	
	public AgentController()
	{
		setCommandClass(Agent.class);
		setCommandName("agent");		
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
				
		Agent agent = (Agent) command;
		locale = request.getLocale(); 
		StartupListener.setupContext(request.getSession().getServletContext());
		
		if (request.getParameter(Constants.MODE_DELETE) != null) 
		{	
			agentService.remove(agent);

			saveMessage(request, getText("agent.deleted", agent.getName(), locale));
			
			return new ModelAndView(getSuccessView());
        } 
		else 
		{
		
			if(mode.equalsIgnoreCase(Constants.MODE_UPDATE))
			{					
				agentService.update(agent);				
	
				saveMessage(request, getText("agent.saved", agent.getName(), locale));
				
				return new ModelAndView(getSuccessView());
			}
			else if(mode.equalsIgnoreCase(Constants.MODE_ADD))
			{	
				agentService.insert(agent);
				
				saveMessage(request, getText("agent.added", agent.getName(), locale));
				
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
		Agent agent = (Agent) super.formBackingObject(request);
		
		if(request.getParameter(Constants.MODE) != null)
		{	
			mode = request.getParameter(Constants.MODE);
		}	
					
		if(mode.equalsIgnoreCase(Constants.MODE_ADD))
		{
			agent = new Agent();						
		}
		else if(mode.equalsIgnoreCase(Constants.MODE_UPDATE))
		{
			agent = (Agent) agentService.findById(Integer.parseInt(request.getParameter("id")));
		}		
		      	
		return agent;
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
	public void setAgentService(AgentService agentService)
	{
		this.agentService = agentService;
	}
	
}
