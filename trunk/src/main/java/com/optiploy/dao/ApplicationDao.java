package com.optiploy.dao;

import org.springframework.transaction.annotation.Transactional;

import com.optiploy.model.Application;


/**
 * @author "Jim Daniel"
 *
 */
public interface ApplicationDao
{
	Object findById(int id);
    void insert(Object object);
    void update(Object object);
    void remove(Object object);
    
    @Transactional
    Application loadApplicationByApplicationName(String name);
    
}
