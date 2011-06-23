package com.optiploy.manager;

import java.util.List;

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
 * Business Service Interface to talk to persistence layer and
 * retrieve values for drop-down choice lists.
 */
public interface LookupManager extends UniversalManager 
{
    /**
     * Retrieves all possible jobs from persistence layer
     * @return List of LabelValue objects
     */
    List<LabelValue> getAllJobs(); 
    
    /**
     * Retrieves all possible roles from persistence layer
     * @return List of LabelValue objects
     */
    List<LabelValue> getAllRoles();
    
    /**
     * Retrieves all possible releases from persistence layer
     * @return List of LabelValue objects
     */
    List<LabelValue> getAllReleases();
    
    /**
     * Retrieves all possible applications from persistence layer
     * @return List of LabelValue objects
     */
    List<LabelValue> getAllApplications();
    
    /**
     * Retrieves all possible agents from persistence layer
     * @return List of LabelValue objects
     */
    List<LabelValue> getAllAgents();
    
    /**
     * Retrieves all possible environments from persistence layer
     * @return List of LabelValue objects
     */
    List<LabelValue> getAllEnvironments();
    
    /**
     * Retrieves all possible functions from persistence layer
     * @return List of LabelValue objects
     */
    List<LabelValue> getAllFunctions();
    
    /**
     * Retrieves all possible progresses from persistence layer
     * @return List of LabelValue objects
     */
    List<LabelValue> getAllProgresses();
    
    /**
     * Retrieves all possible modules from persistence layer
     * @return List of LabelValue objects
     */
    List<LabelValue> getAllModules();
    
    /**
     * Retrieves all possible scripts from persistence layer
     * @return List of LabelValue objects
     */
    List<LabelValue> getAllScripts();
        
    /**
     * Retrieves all possible users from persistence layer
     * @return List of User objects
     */
    List<User> getAllUsersList();
    
    /**
     * Retrieves all possible jobs from persistence layer
     * @return List of Agent objects
     */
    List<Agent> getAllAgentsList(); 
    
    /**
     * Retrieves all possible jobs from persistence layer
     * @return List of Job objects
     */
    List<Job> getAllJobsList(); 
    
    /**
     * Retrieves all possible roles from persistence layer
     * @return List of Role objects
     */
    List<Role> getAllRolesList();  
    
    /**
     * Retrieves all possible releases from persistence layer
     * @return List of Release objects
     */
    List<Release> getAllReleasesList(); 
    
    /**
     * Retrieves all possible applications from persistence layer
     * @return List of Application objects
     */
    List<Application> getAllApplicationList();
    
    /**
     * Retrieves all possible environments from persistence layer
     * @return List of Environment objects
     */
    List<Environment> getAllEnvironmentList();
    
    /**
     * Retrieves all possible functions from persistence layer
     * @return List of Function objects
     */
    List<Function> getAllFunctionList();
    
    /**
     * Retrieves all possible progress from persistence layer
     * @return List of Progress objects
     */
    List<Progress> getAllProgressList();
    
    /**
     * Retrieves all possible modules from persistence layer
     * @return List of Module objects
     */
    List<Module> getAllModuleList();
    
    /**
     * Retrieves all possible scripts from persistence layer
     * @return List of Script objects
     */
    List<Script> getAllScriptList();
        
    
}
