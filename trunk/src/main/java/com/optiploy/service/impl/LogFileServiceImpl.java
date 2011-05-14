package com.optiploy.service.impl;

import com.optiploy.dao.LogFileDao;
import com.optiploy.model.LogFile;
import com.optiploy.service.LogFileService;

/**
 * @author "Jim Daniel"
 *
 */
public class LogFileServiceImpl implements LogFileService
{
	LogFileDao logFileDao;

	public LogFile findById(int id)
	{
		return (LogFile)logFileDao.findById(id);
	}

	public void insert(Object object)
	{
		logFileDao.insert((LogFile)object);		
	}
	
	public LogFile merge(Object object)
	{
		return (LogFile)logFileDao.merge((LogFile)object);	
	}

	public void remove(Object object)
	{
		logFileDao.remove((LogFile)object);		
	}

	public void update(Object object)
	{
		logFileDao.update((LogFile)object);		
	}
	
	public void setLogFileDao(LogFileDao logFileDao) 
	{
        this.logFileDao = logFileDao;
    }

}
