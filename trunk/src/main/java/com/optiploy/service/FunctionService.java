package com.optiploy.service;

import com.optiploy.model.Function;

/**
 * @author "Jim Daniel"
 *
 */
public interface FunctionService
{
	Object findById(int id);
    void insert(Object object);
    void update(Object object);
    void remove(Object object);
    
    public Function getFunctionByFunctionName(String name);

}
