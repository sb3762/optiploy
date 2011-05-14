package com.optiploy.service;

import com.optiploy.model.Application;

/**
 * @author "Jim Daniel"
 *
 */
public interface ApplicationService
{
	Object findById(int id);
    void insert(Object object);
    void update(Object object);
    void remove(Object object);
    
    public Application getApplicationByApplicationName(String name);

}
