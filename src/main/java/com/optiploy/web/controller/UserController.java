package com.optiploy.web.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.optiploy.constants.Constants;
import com.optiploy.exception.UserExistsException;
import com.optiploy.model.User;
import com.optiploy.service.RoleService;
import com.optiploy.service.UserService;

public class UserController extends BaseFormController
{
	private static Logger logger = Logger.getLogger(UserController.class);
	
	private String mode;
	private UserService userService;
	private RoleService roleService;
	private Locale locale;
	
	public UserController()
	{
		setCommandClass(User.class);
		setCommandName("user");		
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
            BindException errors)throws UserExistsException
    {
				
		User user = (User) command;
		Locale locale = request.getLocale();
		
		if (request.getParameter("delete") != null) 
		{			
			userService.remove(user);
			
			saveMessage(request, getText("user.deleted", user.getUsername(), locale));
			
			return new ModelAndView(getSuccessView());
		}
		else
		{
			if(mode.equalsIgnoreCase("profile"))
			{			
				userService.saveUser(user);	
				
				saveMessage(request, getText("user.saved", user.getUsername(), locale));
				
				return new ModelAndView(getSuccessView());
			}
			else if(mode.equalsIgnoreCase("update"))
			{
				if (request.isUserInRole(Constants.ADMIN_ROLE)) 
				{						
	                String[] userRoles = request.getParameterValues("userRoles");
	                
	                if (userRoles != null) 
	                {
	                    user.getRoles().clear();
	                    
	                    for (String roleName : userRoles) 
	                    {
	                    	user.addRole(roleService.getRoleByRoleName(roleName));	                        		
	                    }
	                }	                
	            }
								
				userService.saveUser(user);	
								
				saveMessage(request, getText("user.updated.byAdmin", user.getUsername(), locale));	
				
				return new ModelAndView(getSuccessView());
			}
			else if(mode.equalsIgnoreCase("add"))
			{
				try
				{
					if (request.isUserInRole(Constants.ADMIN_ROLE)) 
					{						
		                String[] userRoles = request.getParameterValues("userRoles");
		                
		                if (userRoles != null) 
		                {
		                    user.getRoles().clear();
		                    
		                    for (String roleName : userRoles) 
		                    {
		                    	user.addRole(roleService.getRoleByRoleName(roleName));                     		
		                    }
		                }		                
		            }
										
					userService.addUser(user);
										
					saveMessage(request, getText("user.added", user.getUsername(), locale));
					
					return new ModelAndView(getSuccessView());
				}
				catch(UserExistsException uee)
				{
					saveError(request,getText("errors.existing.user",user.getUsername(),locale));				
				}			
				
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
		User user = (User) super.formBackingObject(request);
		
		if(request.getParameter("mode") != null)
		{	
			mode = request.getParameter("mode");
		}						
		if(mode.equalsIgnoreCase("add"))
		{
			user = new User();
		}
		else if(mode.equalsIgnoreCase("update"))
		{
			user = (User) userService.findById(Integer.parseInt(request.getParameter("id")));
			user.setConfirmPassword(user.getPassword());
		}
		else if(mode.equalsIgnoreCase("profile"))
		{
			SecurityContext ctx = SecurityContextHolder.getContext();
			Authentication auth = ctx.getAuthentication();
							
			user = (User) auth.getPrincipal();	
			user.setConfirmPassword(user.getPassword());
		}
		      	
		return user;
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
	public void setUserService(UserService userService)
	{
		this.userService = userService;
	}
	
	@Required
	public void setRoleService(RoleService roleService)
	{
		this.roleService = roleService;
	}

}
