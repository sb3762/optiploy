package com.optiploy.service;

import com.optiploy.model.AttributeType;

/**
 * @author "Jim Daniel"
 *
 */
public interface AttributeTypeService
{
	Object findById(int id);
    void insert(Object object);
    AttributeType merge(Object object);
    void update(Object object);
    void remove(Object object);
    
    public AttributeType getAttributeTypeByAttributeTypeName(String name);
    
}
