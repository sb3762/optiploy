package com.optiploy.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.optiploy.dao.ApplicationDao;
import com.optiploy.model.Application;

/**
 * @author "Jim Daniel"
 *
 */
public class ApplicationDaoImpl extends HibernateDaoSupport implements ApplicationDao
{

	public Object findById(int id)
	{	
		List list = getHibernateTemplate().find("from Application where id=?",id);
		return (Application) list.get(0);
	}
	
	public void insert(Object object)
	{
		getHibernateTemplate().save((Application)object);		
	}

	public void remove(Object object)
	{
		getHibernateTemplate().delete((Application)object);		
	}
	
	public void update(Object object)
	{
		getHibernateTemplate().update((Application)object);		
	}
	
	public Application loadApplicationByApplicationName(String name) 
    {    	
		List list = getHibernateTemplate().find("from Application where name=?", name);
		return (Application) list.get(0);      
    }


}
