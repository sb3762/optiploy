package com.optiploy.service;

import com.optiploy.model.Attribute;

/**
 * @author "Jim Daniel"
 *
 */
public interface AttributeService
{
	Object findById(int id);
    void insert(Object object);
    Attribute merge(Object object);
    void update(Object object);
    void remove(Object object);
    
    public Attribute getAttributeByAttributeName(String name);
    
}
