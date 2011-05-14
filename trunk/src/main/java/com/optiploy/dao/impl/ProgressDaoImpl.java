package com.optiploy.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.optiploy.dao.ProgressDao;
import com.optiploy.model.Progress;

/**
 * @author "Jim Daniel"
 *
 */
public class ProgressDaoImpl extends HibernateDaoSupport implements ProgressDao
{

	public Object findById(int id)
	{	
		List list = getHibernateTemplate().find("from Progress where id=?",id);
		return (Progress) list.get(0);
	}
	
	public void insert(Object object)
	{
		getHibernateTemplate().save((Progress)object);		
	}

	public void remove(Object object)
	{
		getHibernateTemplate().delete((Progress)object);		
	}
	
	public void update(Object object)
	{
		getHibernateTemplate().update((Progress)object);		
	}
	
	public Progress loadProgressByProgressName(String name) 
    {    	
		List list = getHibernateTemplate().find("from Progress where name=?", name);
		return (Progress) list.get(0);      
    }


}
