package com.optiploy.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.optiploy.constants.Constants;

public class BaseAbstractController extends AbstractController 
{
	protected String cancelView;
	
	@SuppressWarnings("unchecked")
    public void saveError(HttpServletRequest request, String error) 
	{
        List errors = (List) request.getSession().getAttribute("errors");
        
        if (errors == null) 
        {
            errors = new ArrayList();
        }
        
        errors.add(error);
        request.getSession().setAttribute("errors", errors);
    }
    
    @SuppressWarnings("unchecked")
    public void saveMessage(HttpServletRequest request, String msg) 
    {
        List messages = (List) request.getSession().getAttribute(Constants.MESSAGES_KEY);

        if (messages == null) 
        {
            messages = new ArrayList();
        }

        messages.add(msg);
        request.getSession().setAttribute(Constants.MESSAGES_KEY, messages);
    }
        
    public String getText(String msgKey, String arg, Locale locale) 
    {
        return getText(msgKey, new Object[] { arg }, locale);
    }

   
    public String getText(String msgKey, Object[] args, Locale locale) 
    {
        return getMessageSourceAccessor().getMessage(msgKey, args, locale);
    }
    
    

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception
	{
		return null;
	}   

}
