package com.optiploy.dao;

import org.springframework.transaction.annotation.Transactional;

import com.optiploy.model.Property;

/**
 * @author "Jim Daniel"
 *
 */
public interface PropertyDao
{
	Object findById(int id);
    void insert(Object object);
    void update(Object object);
    void remove(Object object);
    
    @Transactional
    Property loadPropertyByPropertyName(String name);
    
}
