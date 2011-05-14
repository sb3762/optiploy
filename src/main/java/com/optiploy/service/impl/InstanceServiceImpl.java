package com.optiploy.service.impl;

import java.util.List;

import com.optiploy.dao.InstanceDao;
import com.optiploy.model.Instance;
import com.optiploy.service.InstanceService;

/**
 * @author "Jim Daniel"
 *
 */
public class InstanceServiceImpl implements InstanceService
{
	InstanceDao instanceDao;

	public Object findById(int id)
	{
		return instanceDao.findById(id);
	}

	public void insert(Object object)
	{
		instanceDao.insert((Instance)object);		
	}
	
	public Instance merge(Object object)
	{
		return (Instance) instanceDao.merge((Instance)object);		
	}

	public void remove(Object object)
	{
		instanceDao.remove((Instance)object);		
	}

	public void update(Object object)
	{
		instanceDao.update((Instance)object);		
	}
	
	public void setInstanceDao(InstanceDao instanceDao) 
	{
        this.instanceDao = instanceDao;
    }

	public List getReadyInstances()
	{
		return instanceDao.loadReadyInstances();
	}

	public void deleteAllInstances(int agentId)
	{
		instanceDao.deleteAllAgentInstances(agentId);		
	}

}
