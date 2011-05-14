package com.optiploy.dao;

import org.springframework.transaction.annotation.Transactional;

import com.optiploy.model.Module;

/**
 * @author "Jim Daniel"
 *
 */
public interface ModuleDao
{
	Object findById(int id);
    void insert(Object object);
    void update(Object object);
    void remove(Object object);
    
    @Transactional
    Module loadModuleByModuleName(String name);
    
}
