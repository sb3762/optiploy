package com.optiploy.dao;

import org.springframework.transaction.annotation.Transactional;

import com.optiploy.model.Environment;


/**
 * @author "Jim Daniel"
 *
 */
public interface EnvironmentDao
{
	Object findById(int id);
    void insert(Object object);
    void update(Object object);
    void remove(Object object);
    
    @Transactional
    Environment loadEnvironmentByEnvironmentName(String name);
    
}
