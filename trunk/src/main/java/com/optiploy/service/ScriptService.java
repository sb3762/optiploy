package com.optiploy.service;

import com.optiploy.model.Script;

/**
 * @author "Jim Daniel"
 *
 */
public interface ScriptService
{
	Object findById(int id);
    void insert(Object object);
    void update(Object object);
    void remove(Object object);
    
    public Script getScriptByScriptName(String name);

}
