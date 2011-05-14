package com.optiploy.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.optiploy.dao.AttributeTypeDao;
import com.optiploy.model.AttributeType;

/**
 * @author "Jim Daniel"
 *
 */
public class AttributeTypeDaoImpl extends HibernateDaoSupport implements AttributeTypeDao
{

	public Object findById(int id)
	{	
		List list = getHibernateTemplate().find("from AttributeType where id=?",id);
		return (AttributeType) list.get(0);
	}
	
	public void insert(Object object)
	{
		getHibernateTemplate().save((AttributeType)object);		
	}
	
	public AttributeType merge(Object object)
	{
		return (AttributeType)getHibernateTemplate().merge((AttributeType)object);		
	}

	public void remove(Object object)
	{
		getHibernateTemplate().delete((AttributeType)object);		
	}
	
	public void update(Object object)
	{
		getHibernateTemplate().update((AttributeType)object);		
	}
	
	public AttributeType loadAttributeTypeByAttributeTypeName(String name) 
    {    	
		List list = getHibernateTemplate().find("from AttributeType where name=?", name);
		return (AttributeType) list.get(0);      
    }	

}
