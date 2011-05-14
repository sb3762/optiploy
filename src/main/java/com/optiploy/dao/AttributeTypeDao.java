package com.optiploy.dao;

import org.springframework.transaction.annotation.Transactional;

import com.optiploy.model.AttributeType;

/**
 * @author "Jim Daniel"
 *
 */
public interface AttributeTypeDao
{
	Object findById(int id);
    void insert(Object object);
    Object merge(Object object);
    void update(Object object);
    void remove(Object object);
    
    @Transactional
    AttributeType loadAttributeTypeByAttributeTypeName(String name);
        
}
