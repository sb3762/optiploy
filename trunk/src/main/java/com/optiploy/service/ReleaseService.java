package com.optiploy.service;

import com.optiploy.model.Release;

/**
 * @author "Jim Daniel"
 *
 */
public interface ReleaseService
{
	Object findById(int id);
    void insert(Object object);
    void update(Object object);
    void remove(Object object);
    
    public Release getReleaseByReleaseName(String name);

}
