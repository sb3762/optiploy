package com.optiploy.dao;

import java.util.List;

import com.optiploy.model.Agent;
import com.optiploy.model.Application;
import com.optiploy.model.Environment;
import com.optiploy.model.Function;
import com.optiploy.model.Job;
import com.optiploy.model.Module;
import com.optiploy.model.Progress;
import com.optiploy.model.Release;
import com.optiploy.model.Role;
import com.optiploy.model.Script;
import com.optiploy.model.User;

/**
 * Lookup Data Access Object (GenericDao) interface.  This is used to lookup values in
 * the database (i.e. for drop-downs).
 */
public interface LookupDao extends UniversalDao 
{
	/**
     * Returns all Agents ordered by name
     * @return populated list of jobs
     */
    List<Agent> getAgents();    
    
    /**
     * Returns all Jobs ordered by name
     * @return populated list of jobs
     */
    List<Job> getJobs();
    
    /**
     * Returns all Roles ordered by name
     * @return populated list of roles
     */
    List<Role> getRoles();
    
    /**
     * Returns all Releases ordered by name
     * @return populated list of releases
     */
    List<Release> getReleases();
    
    /**
     * Returns all Modules ordered by name
     * @return populated list of modules
     */
    List<Module> getModules();
    
    /**
     * Returns all Applications ordered by name
     * @return populated list of applications
     */
    List<Application> getApplications();
    
    /**
     * Returns all Environments ordered by name
     * @return populated list of environments
     */
    List<Environment> getEnvironments();
    
    /**
     * Returns all Functions ordered by name
     * @return populated list of functions
     */
    List<Function> getFunctions();
    
    /**
     * Returns all Progesses ordered by name
     * @return populated list of progesses
     */
    List<Progress> getProgresses();
    
    /**
     * Returns all Scripts ordered by name
     * @return populated list of scripts
     */
    List<Script> getScripts();
            
    /**
     * Returns all Users ordered by name
     * @return populated list of users
     */
    List<User> getUsers();
}
