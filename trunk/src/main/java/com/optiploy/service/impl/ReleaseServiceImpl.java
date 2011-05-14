package com.optiploy.service.impl;

import com.optiploy.dao.ReleaseDao;
import com.optiploy.model.Release;
import com.optiploy.service.ReleaseService;

/**
 * @author "Jim Daniel"
 *
 */
public class ReleaseServiceImpl implements ReleaseService
{
	ReleaseDao releaseDao;

	public Object findById(int id)
	{
		return releaseDao.findById(id);
	}

	public void insert(Object object)
	{
		releaseDao.insert((Release)object);		
	}

	public void remove(Object object)
	{
		releaseDao.remove((Release)object);		
	}

	public void update(Object object)
	{
		releaseDao.update((Release)object);		
	}
	
	public void setReleaseDao(ReleaseDao releaseDao) 
	{
        this.releaseDao = releaseDao;
    }
	
	public Release getReleaseByReleaseName(String name)
	{
		return releaseDao.loadReleaseByReleaseName(name);
	}

}
