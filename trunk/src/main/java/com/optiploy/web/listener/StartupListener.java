package com.optiploy.web.listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.RememberMeAuthenticationProvider;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.optiploy.constants.Constants;
import com.optiploy.manager.LookupManager;

/**
 * <p>StartupListener class used to initialize and database settings
 * and populate any application-wide drop-downs.
 * <p/>
 * <p>Keep in mind that this listener is executed outside of OpenSessionInViewFilter,
 * so if you're using Hibernate you'll have to explicitly initialize all loaded data at the
 * GenericDao or service level to avoid LazyInitializationException. Hibernate.initialize() works
 * well for doing this. 
 */
public class StartupListener implements ServletContextListener 
{
	private static Logger logger = Logger.getLogger(StartupListener.class);

    @SuppressWarnings({"unchecked"})
    public void contextInitialized(ServletContextEvent event) 
    {
        logger.info("Initializing context...");

        ServletContext context = event.getServletContext();

        // Orion starts Servlets before Listeners, so check if the config
        // object already exists
        Map<String, Object> config = (HashMap<String, Object>) context.getAttribute(Constants.CONFIG);

        if (config == null) 
        {
            config = new HashMap<String, Object>();
        }
        
        if (context.getInitParameter(Constants.CSS_THEME) != null) 
        {
            config.put(Constants.CSS_THEME, context.getInitParameter(Constants.CSS_THEME));
        }

        ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
        
        PasswordEncoder passwordEncoder = null;
        
        try 
        {
            ProviderManager provider = (ProviderManager) ctx.getBean("_authenticationManager");
            
            for(Object o : provider.getProviders()) 
            {
                AuthenticationProvider p = (AuthenticationProvider) o;
                
                if (p instanceof RememberMeAuthenticationProvider) 
                {
                    config.put("rememberMeEnabled", Boolean.TRUE);
                } 
                else if (ctx.getBean("passwordEncoder") != null) 
                {
                    passwordEncoder = (PasswordEncoder) ctx.getBean("passwordEncoder");
                }
            }
        } 
        catch (NoSuchBeanDefinitionException n) 
        {
            logger.debug("authenticationManager bean not found, assuming test and ignoring...");            
        }

        context.setAttribute(Constants.CONFIG, config);

        // output the retrieved values for the Init and Context Parameters
        if (logger.isDebugEnabled()) 
        {
            logger.debug("Remember Me Enabled? " + config.get("rememberMeEnabled"));
            
            if (passwordEncoder != null) 
            {
                logger.debug("Password Encoder: " + passwordEncoder.getClass().getName());
            }
            
            logger.debug("Populating drop-downs...");
        }

        setupContext(context);
    }

    /**
     * This method uses the LookupManager to lookup available roles from the data layer.
     * @param context The servlet context
     */
    
    public static void setupContext(ServletContext context) 
    {
        ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
        LookupManager mgr = (LookupManager) ctx.getBean("lookupManager");
        
        // set profile
        context.setAttribute("profile", "profile1");

        // get list of possible roles
        context.setAttribute(Constants.AVAILABLE_ROLES, mgr.getAllRoles());
        
        // get list of possible releases
        context.setAttribute(Constants.AVAILABLE_RELEASES, mgr.getAllReleases());
        
        // get list of possible applications
        context.setAttribute(Constants.AVAILABLE_APPLICATIONS, mgr.getAllApplications());
                
        // get list of possible functions
        context.setAttribute(Constants.AVAILABLE_FUNCTIONS, mgr.getAllFunctions());
        
        // get list of possible modules
        context.setAttribute(Constants.AVAILABLE_MODULES, mgr.getAllModules());
        
        // get list of possible progresses
        context.setAttribute(Constants.AVAILABLE_PROGRESSES, mgr.getAllProgresses());
         
        
        logger.debug("Drop-down initialization complete [OK]");
    }
	
    /**
     * Shutdown servlet context (currently a no-op method).
     *
     * @param servletContextEvent The servlet context event
     */
    public void contextDestroyed(ServletContextEvent servletContextEvent) 
    {
        //LogFactory.release(Thread.currentThread().getContextClassLoader());
        //Commented out the above call to avoid warning when SLF4J in classpath.
        //WARN: The method class org.apache.commons.logging.impl.SLF4JLogFactory#release() was invoked.
        //WARN: Please see http://www.slf4j.org/codes.html for an explanation.
    }
}
