package com.optiploy.dao;

import org.springframework.transaction.annotation.Transactional;

import com.optiploy.model.Release;


/**
 * @author "Jim Daniel"
 *
 */
public interface ReleaseDao
{
	Object findById(int id);
    void insert(Object object);
    void update(Object object);
    void remove(Object object);
    
    @Transactional
    Release loadReleaseByReleaseName(String name);
    
}
