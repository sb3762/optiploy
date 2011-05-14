package com.optiploy.web.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.servlet.ModelAndView;

import com.optiploy.model.User;
import com.optiploy.service.UserService;

public class PasswordHintController extends BaseAbstractController
{
	private MailSender mailSender;
	private SimpleMailMessage mailMessage;
	private UserService userService;	

	private static Logger logger = Logger.getLogger(PasswordHintController.class);
		
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
	{		
		String username = request.getParameter("username");
		
		Locale locale = request.getLocale();
		
		if (username == null || username.equalsIgnoreCase("")) 
		{
            saveError(request,getText("login.passwordHint.error.username","", locale));
            return new ModelAndView("login");
        }
		
		User user = userService.getUserByUsername(username);
		
		try 
		{           
            SimpleMailMessage message = new SimpleMailMessage(mailMessage);
            
            message.setTo(user.getEmail());
            message.setSubject(getText("login.passwordHint.subject",user.getUsername(),locale));
            message.setText(getText("login.passwordHint.email",user.getUsername(),locale) + user.getPasswordHint());
            
            mailSender.send(message);
            
            saveMessage(request, getText("login.passwordHint.sent",user.getUsername(), locale));
            
		}
		catch(UsernameNotFoundException e)
		{
			logger.info(e.getMessage());
            saveError(request,getText("login.passwordHint.error",user.getUsername(), locale));
		}		
		
		return new ModelAndView("login","login",null);
	}	
	
	public void setMailSender(MailSender mailSender)
	{
		this.mailSender = mailSender;
	}
	
	public void setMailMessage(SimpleMailMessage mailMessage)
	{
		this.mailMessage = mailMessage;
	}
	
	public void setUserService(UserService userService)
	{
		this.userService = userService;
	}

}
