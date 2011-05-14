package com.optiploy.service;

import com.optiploy.model.Module;

/**
 * @author "Jim Daniel"
 *
 */
public interface ModuleService
{
	Object findById(int id);
    void insert(Object object);
    void update(Object object);
    void remove(Object object);
    
    public Module getModuleByModuleName(String name);

}
