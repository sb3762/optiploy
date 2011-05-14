package com.optiploy.service.impl;

import com.optiploy.dao.ApplicationDao;
import com.optiploy.model.Application;
import com.optiploy.service.ApplicationService;

/**
 * @author "Jim Daniel"
 *
 */
public class ApplicationServiceImpl implements ApplicationService
{
	ApplicationDao applicationDao;

	public Object findById(int id)
	{
		return applicationDao.findById(id);
	}

	public void insert(Object object)
	{
		applicationDao.insert((Application)object);		
	}

	public void remove(Object object)
	{
		applicationDao.remove((Application)object);		
	}

	public void update(Object object)
	{
		applicationDao.update((Application)object);		
	}
	
	public void setApplicationDao(ApplicationDao applicationDao) 
	{
        this.applicationDao = applicationDao;
    }
	
	public Application getApplicationByApplicationName(String name)
	{
		return applicationDao.loadApplicationByApplicationName(name);
	}

}
