package com.optiploy.service.impl;

import com.optiploy.dao.JobDao;
import com.optiploy.model.Job;
import com.optiploy.service.JobService;

/**
 * @author "Jim Daniel"
 *
 */
public class JobServiceImpl implements JobService
{
	JobDao jobDao;

	public Job findById(int id)
	{
		return (Job)jobDao.findById(id);
	}

	public void insert(Object object)
	{
		jobDao.insert((Job)object);		
	}

	public void remove(Object object)
	{
		jobDao.remove((Job)object);		
	}

	public void update(Object object)
	{
		jobDao.update((Job)object);		
	}
	
	public void setJobDao(JobDao jobDao) 
	{
        this.jobDao = jobDao;
    }
	
	public Job getJobByJobName(String name)
	{
		return jobDao.loadJobByJobName(name);
	}

}
