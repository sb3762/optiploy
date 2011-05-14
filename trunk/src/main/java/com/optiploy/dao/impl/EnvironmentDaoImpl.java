package com.optiploy.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.optiploy.dao.EnvironmentDao;
import com.optiploy.model.Environment;

/**
 * @author "Jim Daniel"
 *
 */
public class EnvironmentDaoImpl extends HibernateDaoSupport implements EnvironmentDao
{

	public Object findById(int id)
	{	
		List list = getHibernateTemplate().find("from Environment where id=?",id);
		return (Environment) list.get(0);
	}
	
	public void insert(Object object)
	{
		getHibernateTemplate().save((Environment)object);		
	}

	public void remove(Object object)
	{
		getHibernateTemplate().delete((Environment)object);		
	}
	
	public void update(Object object)
	{
		getHibernateTemplate().update((Environment)object);		
	}
	
	public Environment loadEnvironmentByEnvironmentName(String name) 
    {    	
		List list = getHibernateTemplate().find("from Environment where name=?", name);
		return (Environment) list.get(0);      
    }


}
