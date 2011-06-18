package com.optiploy.web.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public final class RequestUtil 
{
	private static Logger logger = Logger.getLogger(RequestUtil.class);

    private RequestUtil() 
    {
    	
    }

    public static void setCookie(HttpServletResponse response, String name, String value, String path) 
    {
        if (logger.isDebugEnabled())         
        	logger.debug("Setting cookie '" + name + "' on path '" + path + "'");
      
        Cookie cookie = new Cookie(name, value);
        cookie.setSecure(false);
        cookie.setPath(path);
        cookie.setMaxAge(3600 * 24 * 30); // 30 days

        response.addCookie(cookie);
    }

    public static Cookie getCookie(HttpServletRequest request, String name) 
    {
        Cookie[] cookies = request.getCookies();
        Cookie returnCookie = null;

        if (cookies == null) 
        {
            return returnCookie;
        }

        for (Cookie thisCookie : cookies) 
        {
            if (thisCookie.getName().equals(name)) 
            {                
                if (!thisCookie.getValue().equals("")) 
                {
                    returnCookie = thisCookie;

                    break;
                }
            }
        }

        return returnCookie;
    }

    public static void deleteCookie(HttpServletResponse response, Cookie cookie, String path) 
    {
        if (cookie != null) 
        {
            cookie.setMaxAge(0);
            cookie.setPath(path);
            response.addCookie(cookie);
        }
    }

    public static String getAppURL(HttpServletRequest request) 
    {
        StringBuffer url = new StringBuffer();
        int port = request.getServerPort();
        
        if (port < 0) 
        {
            port = 80;
        }
        
        String scheme = request.getScheme();
        url.append(scheme);
        url.append("://");
        url.append(request.getServerName());
        
        if ((scheme.equals("http") && (port != 80)) || (scheme.equals("https") && (port != 443))) 
        {
            url.append(':');
            url.append(port);
        }
        
        url.append(request.getContextPath());
        
        return url.toString();
    }
}
