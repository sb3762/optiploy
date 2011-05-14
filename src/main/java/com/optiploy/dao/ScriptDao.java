package com.optiploy.dao;

import org.springframework.transaction.annotation.Transactional;

import com.optiploy.model.Script;

/**
 * @author "Jim Daniel"
 *
 */
public interface ScriptDao
{
	Object findById(int id);
    void insert(Object object);
    void update(Object object);
    void remove(Object object);
    
    @Transactional
    Script loadScriptByScriptName(String name);
    
}
