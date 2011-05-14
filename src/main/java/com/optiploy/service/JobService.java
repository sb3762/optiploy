package com.optiploy.service;

import com.optiploy.model.Job;

/**
 * @author "Jim Daniel"
 *
 */
public interface JobService
{
	Job findById(int id);
    void insert(Object object);
    void update(Object object);
    void remove(Object object);
    
    public Job getJobByJobName(String name);

}
