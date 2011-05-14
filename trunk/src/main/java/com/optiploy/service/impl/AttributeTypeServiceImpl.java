package com.optiploy.service.impl;

import com.optiploy.dao.AttributeTypeDao;
import com.optiploy.model.AttributeType;
import com.optiploy.service.AttributeTypeService;

/**
 * @author "Jim Daniel"
 *
 */
public class AttributeTypeServiceImpl implements AttributeTypeService
{
	AttributeTypeDao attributeTypeDao;

	public Object findById(int id)
	{
		return attributeTypeDao.findById(id);
	}

	public void insert(Object object)
	{
		attributeTypeDao.insert((AttributeType)object);		
	}
	
	public AttributeType merge(Object object)
	{
		return (AttributeType) attributeTypeDao.merge((AttributeType)object);		
	}

	public void remove(Object object)
	{
		attributeTypeDao.remove((AttributeType)object);		
	}

	public void update(Object object)
	{
		attributeTypeDao.update((AttributeType)object);		
	}
	
	public void setAttributeTypeDao(AttributeTypeDao attributeTypeDao) 
	{
        this.attributeTypeDao = attributeTypeDao;
    }
	
	public AttributeType getAttributeTypeByAttributeTypeName(String name)
	{
		return attributeTypeDao.loadAttributeTypeByAttributeTypeName(name);
	}
}
