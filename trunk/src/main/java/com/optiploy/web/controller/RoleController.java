package com.optiploy.web.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.optiploy.model.Role;
import com.optiploy.service.RoleService;
import com.optiploy.web.listener.StartupListener;


public class RoleController extends BaseFormController
{
	private static Logger logger = Logger.getLogger(RoleController.class);
	
	private String mode;	
	private RoleService roleService;
	private Locale locale;
	
	public RoleController()
	{
		setCommandClass(Role.class);
		setCommandName("role");		
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
				
		Role role = (Role) command;
		locale = request.getLocale(); 
		
		if (request.getParameter("delete") != null) 
		{				
			roleService.remove(role);
						
			StartupListener.setupContext(request.getSession().getServletContext());

			saveMessage(request, getText("role.deleted", role.getName(), locale));
			
			return new ModelAndView(getSuccessView());
        } 
		else 
		{
		
			if(mode.equalsIgnoreCase("update"))
			{					
				roleService.update(role);
				
				StartupListener.setupContext(request.getSession().getServletContext());
	
				saveMessage(request, getText("role.saved", role.getName(), locale));
				
				return new ModelAndView(getSuccessView());
			}
			else if(mode.equalsIgnoreCase("add"))
			{					
				roleService.insert(role);				
				
				StartupListener.setupContext(request.getSession().getServletContext());
				
				saveMessage(request, getText("role.added", role.getName(), locale));
				
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
		Role role = (Role) super.formBackingObject(request);
		
		if(request.getParameter("mode") != null)
		{	
			mode = request.getParameter("mode");
		}	
					
		if(mode.equalsIgnoreCase("add"))
		{
			role = new Role();						
		}
		else if(mode.equalsIgnoreCase("update"))
		{
			role = (Role) roleService.findById(Integer.parseInt(request.getParameter("id")));
		}
		
		      	
		return role;
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
	public void setRoleService(RoleService roleService)
	{
		this.roleService = roleService;
	}
	
}
