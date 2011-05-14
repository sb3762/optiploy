package com.optiploy.service;

import com.optiploy.model.Progress;

/**
 * @author "Jim Daniel"
 *
 */
public interface ProgressService
{
	Object findById(int id);
    void insert(Object object);
    void update(Object object);
    void remove(Object object);
    
    public Progress getProgressByProgressName(String name);

}
