package com.optiploy.service.impl;

import com.optiploy.dao.AttributeDao;
import com.optiploy.model.Attribute;
import com.optiploy.service.AttributeService;

/**
 * @author "Jim Daniel"
 *
 */
public class AttributeServiceImpl implements AttributeService
{
	AttributeDao attributeDao;

	public Object findById(int id)
	{
		return attributeDao.findById(id);
	}

	public void insert(Object object)
	{
		attributeDao.insert((Attribute)object);		
	}
	
	public Attribute merge(Object object)
	{
		return (Attribute) attributeDao.merge((Attribute)object);		
	}

	public void remove(Object object)
	{
		attributeDao.remove((Attribute)object);		
	}

	public void update(Object object)
	{
		attributeDao.update((Attribute)object);		
	}
	
	public void setAttributeDao(AttributeDao attributeDao) 
	{
        this.attributeDao = attributeDao;
    }
	
	public Attribute getAttributeByAttributeName(String name)
	{
		return attributeDao.loadAttributeByAttributeName(name);
	}
}
