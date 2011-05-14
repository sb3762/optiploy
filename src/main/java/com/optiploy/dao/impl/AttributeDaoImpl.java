package com.optiploy.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.optiploy.dao.AttributeDao;
import com.optiploy.model.Attribute;

/**
 * @author "Jim Daniel"
 *
 */
public class AttributeDaoImpl extends HibernateDaoSupport implements AttributeDao
{

	public Object findById(int id)
	{	
		List list = getHibernateTemplate().find("from Attribute where id=?",id);
		return (Attribute) list.get(0);
	}
	
	public void insert(Object object)
	{
		getHibernateTemplate().save((Attribute)object);		
	}
	
	public Attribute merge(Object object)
	{
		return (Attribute)getHibernateTemplate().merge((Attribute)object);		
	}

	public void remove(Object object)
	{
		getHibernateTemplate().delete((Attribute)object);		
	}
	
	public void update(Object object)
	{
		getHibernateTemplate().update((Attribute)object);		
	}
	
	public Attribute loadAttributeByAttributeName(String name) 
    {    	
		List list = getHibernateTemplate().find("from Attribute where name=?", name);
		return (Attribute) list.get(0);      
    }	

}
