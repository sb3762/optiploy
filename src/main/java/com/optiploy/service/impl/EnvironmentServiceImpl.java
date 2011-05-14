package com.optiploy.service.impl;

import com.optiploy.dao.EnvironmentDao;
import com.optiploy.model.Environment;
import com.optiploy.service.EnvironmentService;

/**
 * @author "Jim Daniel"
 *
 */
public class EnvironmentServiceImpl implements EnvironmentService
{
	EnvironmentDao environmentDao;

	public Object findById(int id)
	{
		return environmentDao.findById(id);
	}

	public void insert(Object object)
	{
		environmentDao.insert((Environment)object);		
	}

	public void remove(Object object)
	{
		environmentDao.remove((Environment)object);		
	}

	public void update(Object object)
	{
		environmentDao.update((Environment)object);		
	}
	
	public void setEnvironmentDao(EnvironmentDao environmentDao) 
	{
        this.environmentDao = environmentDao;
    }
	
	public Environment getEnvironmentByEnvironmentName(String name)
	{
		return environmentDao.loadEnvironmentByEnvironmentName(name);
	}

}
