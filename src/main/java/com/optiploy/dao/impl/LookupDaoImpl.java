package com.optiploy.dao.impl;

import java.util.List;

import com.optiploy.dao.LookupDao;
import com.optiploy.model.Application;
import com.optiploy.model.Environment;
import com.optiploy.model.Function;
import com.optiploy.model.Job;
import com.optiploy.model.Module;
import com.optiploy.model.Progress;
import com.optiploy.model.Release;
import com.optiploy.model.Role;
import com.optiploy.model.User;

/**
 * Hibernate implementation of LookupDao.
 */
public class LookupDaoImpl extends UniversalDaoImpl implements LookupDao 
{

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<Job> getJobs() 
    {
        logger.debug("Retrieving all job names...");

        return getHibernateTemplate().find("from Job order by name");
    }  
    
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<Role> getRoles() 
    {
        logger.debug("Retrieving all role names...");

        return getHibernateTemplate().find("from Role order by name");
    } 
    
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<Module> getModules() 
    {
        logger.debug("Retrieving all module names...");

        return getHibernateTemplate().find("from Module order by name");
    } 
    
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<Release> getReleases() 
    {
        logger.debug("Retrieving all release names...");

        return getHibernateTemplate().find("from Release order by name");
    }
    
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<Application> getApplications() 
    {
        logger.debug("Retrieving all application names...");

        return getHibernateTemplate().find("from Application order by name");
    } 
    
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<Environment> getEnvironments() 
    {
        logger.debug("Retrieving all environment names...");

        return getHibernateTemplate().find("from Environment order by name");
    }
    
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<Function> getFunctions() 
    {
        logger.debug("Retrieving all function names...");

        return getHibernateTemplate().find("from Function order by name");
    }
    
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<Progress> getProgresses() 
    {
        logger.debug("Retrieving all progress names...");

        return getHibernateTemplate().find("from Progress order by name");
    }
    
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
	public List<User> getUsers()
	{
		logger.debug("Retrieving all users...");
		
		return getHibernateTemplate().find("from User order by username");
	}

}
