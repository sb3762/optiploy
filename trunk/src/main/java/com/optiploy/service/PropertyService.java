package com.optiploy.service;

import com.optiploy.model.Property;

/**
 * @author "Jim Daniel"
 *
 */
public interface PropertyService
{
	Object findById(int id);
    void insert(Object object);
    void update(Object object);
    void remove(Object object);
    
    public Property getPropertyByPropertyName(String name);

}
