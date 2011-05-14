package com.optiploy.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.optiploy.dao.LogDao;
import com.optiploy.model.Log;

/**
 * @author "Jim Daniel"
 *
 */
public class LogDaoImpl extends HibernateDaoSupport implements LogDao
{

	public Object findById(int id)
	{	
		List list = getHibernateTemplate().find("from Log where id=?",id);
		return (Log) list.get(0);
	}
	
	public void insert(Object object)
	{
		getHibernateTemplate().save((Log)object);		
	}
	
	public Log merge(Object object)
	{
		return (Log)getHibernateTemplate().merge((Log)object);		
	}

	public void remove(Object object)
	{
		getHibernateTemplate().delete((Log)object);		
	}
	
	public void update(Object object)
	{
		getHibernateTemplate().update((Log)object);	
	}
	

}
