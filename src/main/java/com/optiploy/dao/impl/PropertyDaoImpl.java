package com.optiploy.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.optiploy.dao.PropertyDao;
import com.optiploy.model.Property;

/**
 * @author "Jim Daniel"
 *
 */
public class PropertyDaoImpl extends HibernateDaoSupport implements PropertyDao
{

	public Object findById(int id)
	{	
		List list = getHibernateTemplate().find("from Property where id=?",id);
		return (Property) list.get(0);
	}
	
	public void insert(Object object)
	{
		getHibernateTemplate().save((Property)object);		
	}

	public void remove(Object object)
	{
		getHibernateTemplate().delete((Property)object);		
	}
	
	public void update(Object object)
	{
		getHibernateTemplate().update((Property)object);		
	}
	
	public Property loadPropertyByPropertyName(String name) 
    {    	
		List list = getHibernateTemplate().find("from Property where name=?", name);
		return (Property) list.get(0);      
    }


}
