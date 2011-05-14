package com.optiploy.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.optiploy.dao.FunctionDao;
import com.optiploy.model.Function;

/**
 * @author "Jim Daniel"
 *
 */
public class FunctionDaoImpl extends HibernateDaoSupport implements FunctionDao
{

	public Object findById(int id)
	{	
		List list = getHibernateTemplate().find("from Function where id=?",id);
		return (Function) list.get(0);
	}
	
	public void insert(Object object)
	{
		getHibernateTemplate().save((Function)object);		
	}

	public void remove(Object object)
	{
		getHibernateTemplate().delete((Function)object);		
	}
	
	public void update(Object object)
	{
		getHibernateTemplate().update((Function)object);		
	}
	
	public Function loadFunctionByFunctionName(String name) 
    {    	
		List list = getHibernateTemplate().find("from Function where name=?", name);
		return (Function) list.get(0);      
    }


}
