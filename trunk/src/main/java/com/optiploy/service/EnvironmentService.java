package com.optiploy.service;

import com.optiploy.model.Environment;

/**
 * @author "Jim Daniel"
 *
 */
public interface EnvironmentService
{
	Object findById(int id);
    void insert(Object object);
    void update(Object object);
    void remove(Object object);
    
    public Environment getEnvironmentByEnvironmentName(String name);

}
