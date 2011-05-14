package com.optiploy.service.impl;

import com.optiploy.dao.ProgressDao;
import com.optiploy.model.Progress;
import com.optiploy.service.ProgressService;

/**
 * @author "Jim Daniel"
 *
 */
public class ProgressServiceImpl implements ProgressService
{
	ProgressDao progressDao;

	public Progress findById(int id)
	{
		return (Progress)progressDao.findById(id);
	}

	public void insert(Object object)
	{
		progressDao.insert((Progress)object);		
	}

	public void remove(Object object)
	{
		progressDao.remove((Progress)object);		
	}

	public void update(Object object)
	{
		progressDao.update((Progress)object);		
	}
	
	public void setProgressDao(ProgressDao progressDao) 
	{
        this.progressDao = progressDao;
    }
	
	public Progress getProgressByProgressName(String name)
	{
		return progressDao.loadProgressByProgressName(name);
	}

}
