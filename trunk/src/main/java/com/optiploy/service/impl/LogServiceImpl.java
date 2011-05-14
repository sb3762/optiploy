package com.optiploy.service.impl;

import com.optiploy.dao.LogDao;
import com.optiploy.model.Log;
import com.optiploy.service.LogService;

/**
 * @author "Jim Daniel"
 *
 */
public class LogServiceImpl implements LogService
{
	LogDao logDao;

	public Log findById(int id)
	{
		return (Log)logDao.findById(id);
	}

	public void insert(Object object)
	{
		logDao.insert((Log)object);		
	}
	
	public Log merge(Object object)
	{
		return (Log)logDao.merge((Log)object);	
	}

	public void remove(Object object)
	{
		logDao.remove((Log)object);		
	}

	public void update(Object object)
	{
		logDao.update((Log)object);		
	}
	
	public void setLogDao(LogDao logDao) 
	{
        this.logDao = logDao;
    }

}
