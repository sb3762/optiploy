package com.optiploy.web.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.optiploy.web.listener.StartupListener;


public class ReloadController extends BaseAbstractController 
{	
	private static Logger logger = Logger.getLogger(ReloadController.class);

    public ModelAndView handleRequestInternal(HttpServletRequest request,HttpServletResponse response)throws Exception 
    {
    	Locale locale = request.getLocale();
    	
    	try 
		{
	        StartupListener.setupContext(request.getSession().getServletContext());	
	        	        
	        saveMessage(request, getText("reload.succeeded", "", locale));
		}
    	catch(Exception e)
    	{
            saveError(request,getText("reload.failed","",locale));
    	}
            
            
        return new ModelAndView("home","home",null);
        
    }
    
}
