package com.optiploy.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.optiploy.dao.LookupDao;
import com.optiploy.manager.LookupManager;
import com.optiploy.model.Agent;
import com.optiploy.model.Application;
import com.optiploy.model.Environment;
import com.optiploy.model.Function;
import com.optiploy.model.Job;
import com.optiploy.model.LabelValue;
import com.optiploy.model.Module;
import com.optiploy.model.Progress;
import com.optiploy.model.Release;
import com.optiploy.model.Role;
import com.optiploy.model.Script;
import com.optiploy.model.User;


/**
 * Implementation of LookupManager interface to talk to the persistence layer.
 *
 */
public class LookupManagerImpl extends UniversalManagerImpl implements LookupManager 
{
	private static Logger logger = Logger.getLogger(LookupManagerImpl.class);
	
    private LookupDao dao;

    /**
     * Method that allows setting the DAO to talk to the data store with.
     * @param dao the dao implementation
     */
    public void setLookupDao(LookupDao dao) 
    {
        super.dao = dao;
        this.dao = dao;
    }
    
    /**
     * {@inheritDoc}
     */
    public List<LabelValue> getAllAgents() 
    {
        List<Agent> agents = dao.getAgents();
        List<LabelValue> list = new ArrayList<LabelValue>();

        for (Agent agent1 : agents) 
        {
            list.add(new LabelValue(agent1.getName(), agent1.getName()));
        }

        return list;
    }

    /**
     * {@inheritDoc}
     */
    public List<LabelValue> getAllJobs() 
    {
        List<Job> jobs = dao.getJobs();
        List<LabelValue> list = new ArrayList<LabelValue>();

        for (Job job1 : jobs) 
        {
            list.add(new LabelValue(job1.getName(), job1.getName()));
        }

        return list;
    }
    
    /**
     * {@inheritDoc}
     */
    public List<LabelValue> getAllRoles() 
    {
        List<Role> roles = dao.getRoles();
        List<LabelValue> list = new ArrayList<LabelValue>();

        for (Role role1 : roles) 
        {
            list.add(new LabelValue(role1.getName(), role1.getName()));
        }

        return list;
    }
    
    /**
     * {@inheritDoc}
     */
    public List<LabelValue> getAllApplications() 
    {
        List<Application> applications = dao.getApplications();
        List<LabelValue> list = new ArrayList<LabelValue>();

        for (Application application1 : applications) 
        {
            list.add(new LabelValue(application1.getName(), application1.getName()));
        }

        return list;
    }
    
    /**
     * {@inheritDoc}
     */
    public List<LabelValue> getAllEnvironments() 
    {
        List<Environment> environments = dao.getEnvironments();
        List<LabelValue> list = new ArrayList<LabelValue>();

        for (Environment environment1 : environments) 
        {
            list.add(new LabelValue(environment1.getName(), environment1.getName()));
        }

        return list;
    }
    
    /**
     * {@inheritDoc}
     */
    public List<LabelValue> getAllFunctions() 
    {
        List<Function> functions = dao.getFunctions();
        List<LabelValue> list = new ArrayList<LabelValue>();

        for (Function function1 : functions) 
        {
            list.add(new LabelValue(function1.getName(), function1.getName()));
        }

        return list;
    }
    
    /**
     * {@inheritDoc}
     */
    public List<LabelValue> getAllReleases() 
    {
        List<Release> releases = dao.getReleases();
        List<LabelValue> list = new ArrayList<LabelValue>();

        for (Release release1 : releases) 
        {
            list.add(new LabelValue(release1.getName(), release1.getName()));
        }

        return list;
    }
    
    /**
     * {@inheritDoc}
     */
    public List<LabelValue> getAllModules() 
    {
        List<Module> modules = dao.getModules();
        List<LabelValue> list = new ArrayList<LabelValue>();

        for (Module module1 : modules) 
        {
            list.add(new LabelValue(module1.getName(), module1.getName()));
        }

        return list;
    }
    
    /**
     * {@inheritDoc}
     */
    public List<LabelValue> getAllProgresses() 
    {
        List<Progress> progresses = dao.getProgresses();
        List<LabelValue> list = new ArrayList<LabelValue>();

        for (Progress progress1 : progresses) 
        {
            list.add(new LabelValue(progress1.getName(), progress1.getName()));
        }

        return list;
    }
    
    /**
     * {@inheritDoc}
     */
    public List<LabelValue> getAllScripts() 
    {
        List<Script> scripts = dao.getScripts();
        List<LabelValue> list = new ArrayList<LabelValue>();

        for (Script script1 : scripts) 
        {
            list.add(new LabelValue(script1.getName(), script1.getName()));
        }

        return list;
    }
        
        
   /**
    * {@inheritDoc}
    */
	public List<User> getAllUsersList()
	{       
        return dao.getUsers();
	}
	
	/**
     * {@inheritDoc}
     */
	public List<Agent> getAllAgentsList()
	{
		return dao.getAgents();
	}
	
	/**
     * {@inheritDoc}
     */
	public List<Job> getAllJobsList()
	{
		return dao.getJobs();
	}
	
	/**
     * {@inheritDoc}
     */
	public List<Role> getAllRolesList()
	{
		return dao.getRoles();
	}
	
	/**
     * {@inheritDoc}
     */
	public List<Release> getAllReleasesList()
	{
		return dao.getReleases();
	}
	
	/**
     * {@inheritDoc}
     */
	public List<Application> getAllApplicationList()
	{
		return dao.getApplications();
	}
	
	/**
     * {@inheritDoc}
     */
	public List<Environment> getAllEnvironmentList()
	{
		return dao.getEnvironments();
	}
	
	/**
     * {@inheritDoc}
     */
	public List<Function> getAllFunctionList()
	{
		return dao.getFunctions();
	}
	
	/**
     * {@inheritDoc}
     */
	public List<Module> getAllModuleList()
	{
		return dao.getModules();
	}
	
	/**
     * {@inheritDoc}
     */
	public List<Progress> getAllProgressList()
	{
		return dao.getProgresses();
	}
	
	/**
     * {@inheritDoc}
     */
	public List<Script> getAllScriptList()
	{
		return dao.getScripts();
	}
	
		
}
