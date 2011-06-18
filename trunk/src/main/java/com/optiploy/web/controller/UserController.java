package com.optiploy.web.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
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
import com.optiploy.web.util.RequestUtil;

public class UserController extends BaseFormController
{
	private static Logger logger = Logger.getLogger(UserController.class);
	
	private String mode;
	private UserService userService;
	private RoleService roleService;
	private MailSender mailSender;
	private SimpleMailMessage mailMessage;
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
            BindException errors)throws UserExistsException
    {
				
		User user = (User) command;
		Locale locale = request.getLocale();
		
		
		if (request.getParameter(Constants.MODE_DELETE) != null) 
		{
			userService.remove(user);
			
			saveMessage(request, getText("user.deleted", user.getUsername(), locale));
			
			return new ModelAndView(getSuccessView());
		}
		else
		{
			if(mode.equalsIgnoreCase(Constants.MODE_PROFILE))
			{			
				userService.saveUser(user);	
				
				saveMessage(request, getText("user.saved", user.getUsername(), locale));
				
				return new ModelAndView(getSuccessView());
			}
			else if(mode.equalsIgnoreCase(Constants.MODE_UPDATE))
			{
				if (request.isUserInRole(Constants.ADMIN_ROLE)) 
				{						
	                String[] userRoles = request.getParameterValues("userRoles");
	                
	                if (userRoles != null) 
	                {
	                    user.getRoles().clear();
	                    
	                    for (String roleName : userRoles) 
	                    {
	                    	user.addRole(roleService.getRoleByName(roleName));	                        		
	                    }
	                }	                
	            }
				
				userService.saveUser(user);	
				
				saveMessage(request, getText("user.updated.byAdmin", user.getUsername(), locale));	
				
				return new ModelAndView(getSuccessView());
			}
			else if(mode.equalsIgnoreCase(Constants.MODE_ADD))
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
		                    	user.addRole(roleService.getRoleByName(roleName));                     		
		                    }
		                }		                
		            }
					
					userService.addUser(user);
					
					SimpleMailMessage message = new SimpleMailMessage(mailMessage);
		            
		            message.setTo(user.getEmail());
		            message.setSubject(getText("signup.email.subject",user.getUsername(),locale));
		            message.setText(getText("newuser.email.message",locale) + 
		            						"\n\n " + RequestUtil.getAppURL(request) + "/login.html" +
		            						"\n\n Username: " + user.getUsername() +
		            		                "\n\n Password Hint: " + user.getPasswordHint());
		            
		            mailSender.send(message);
					
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
		
		if(request.getParameter(Constants.MODE) != null)
		{	
			mode = request.getParameter(Constants.MODE);
		}						
		if(mode.equalsIgnoreCase(Constants.MODE_ADD))
		{
			user = new User();
		}
		else if(mode.equalsIgnoreCase(Constants.MODE_UPDATE))
		{
			user = (User) userService.findById(Integer.parseInt(request.getParameter("id")));
			user.setConfirmPassword(user.getPassword());
		}
		else if(mode.equalsIgnoreCase(Constants.MODE_PROFILE))
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
	public void setUserService(UserService userService)
	{
		this.userService = userService;
	}
	
	@Required
	public void setRoleService(RoleService roleService)
	{
		this.roleService = roleService;
	}
	
	@Required
	public void setMailSender(MailSender mailSender)
	{
		this.mailSender = mailSender;
	}
	
	@Required
	public void setMailMessage(SimpleMailMessage mailMessage)
	{
		this.mailMessage = mailMessage;
	}
	
	


}
