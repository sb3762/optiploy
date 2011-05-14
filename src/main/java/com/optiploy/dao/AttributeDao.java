package com.optiploy.dao;

import org.springframework.transaction.annotation.Transactional;

import com.optiploy.model.Attribute;

/**
 * @author "Jim Daniel"
 *
 */
public interface AttributeDao
{
	Object findById(int id);
    void insert(Object object);
    Object merge(Object object);
    void update(Object object);
    void remove(Object object);
    
    @Transactional
    Attribute loadAttributeByAttributeName(String name);
        
}
