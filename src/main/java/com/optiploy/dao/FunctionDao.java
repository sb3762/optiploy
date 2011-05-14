package com.optiploy.dao;

import org.springframework.transaction.annotation.Transactional;

import com.optiploy.model.Function;


/**
 * @author "Jim Daniel"
 *
 */
public interface FunctionDao
{
	Object findById(int id);
    void insert(Object object);
    void update(Object object);
    void remove(Object object);
    
    @Transactional
    Function loadFunctionByFunctionName(String name);
    
}
