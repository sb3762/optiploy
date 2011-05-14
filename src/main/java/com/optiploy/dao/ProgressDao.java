package com.optiploy.dao;

import org.springframework.transaction.annotation.Transactional;

import com.optiploy.model.Progress;

/**
 * @author "Jim Daniel"
 *
 */
public interface ProgressDao
{
	Object findById(int id);
    void insert(Object object);
    void update(Object object);
    void remove(Object object);
    
    @Transactional
    Progress loadProgressByProgressName(String name);
    
}
