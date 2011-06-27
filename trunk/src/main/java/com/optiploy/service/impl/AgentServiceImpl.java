package com.optiploy.service.impl;

import java.util.List;

import com.optiploy.dao.AgentDao;
import com.optiploy.model.Agent;
import com.optiploy.service.AgentService;

/**
 * @author "Jim Daniel"
 *
 */
public class AgentServiceImpl implements AgentService
{
	AgentDao agentDao;

	public Agent findById(int id)
	{
		return agentDao.findById(id);
	}

	public void insert(Object object)
	{
		agentDao.insert((Agent)object);		
	}
	
	public Agent merge(Object object)
	{
		return (Agent) agentDao.merge((Agent)object);		
	}

	public void remove(Object object)
	{
		agentDao.remove((Agent)object);		
	}

	public void update(Object object)
	{
		agentDao.update((Agent)object);		
	}
	
	public void setAgentDao(AgentDao agentDao) 
	{
        this.agentDao = agentDao;
    }
	
	public Agent getAgentByAgentName(String name)
	{
		return agentDao.loadAgentByAgentName(name);
	}

	public List getRunningAgents()
	{
		return agentDao.loadRunningAgents();
	}

}
