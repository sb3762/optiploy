package com.optiploy.service.impl;

import com.optiploy.dao.PropertyDao;
import com.optiploy.model.Property;
import com.optiploy.service.PropertyService;

/**
 * @author "Jim Daniel"
 *
 */
public class PropertyServiceImpl implements PropertyService
{
	PropertyDao propertyDao;

	public Object findById(int id)
	{
		return propertyDao.findById(id);
	}

	public void insert(Object object)
	{
		propertyDao.insert((Property)object);		
	}

	public void remove(Object object)
	{
		propertyDao.remove((Property)object);		
	}

	public void update(Object object)
	{
		propertyDao.update((Property)object);		
	}
	
	public void setPropertyDao(PropertyDao propertyDao) 
	{
        this.propertyDao = propertyDao;
    }
	
	public Property getPropertyByPropertyName(String name)
	{
		return propertyDao.loadPropertyByPropertyName(name);
	}

}
