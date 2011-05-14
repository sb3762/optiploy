package com.optiploy.dao;

import org.springframework.transaction.annotation.Transactional;

import com.optiploy.model.Job;

/**
 * @author "Jim Daniel"
 *
 */
public interface JobDao
{
	Object findById(int id);
    void insert(Object object);
    void update(Object object);
    void remove(Object object);
    
    @Transactional
    Job loadJobByJobName(String name);
    
}
