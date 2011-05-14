package com.optiploy.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.optiploy.dao.ReleaseDao;
import com.optiploy.model.Release;

/**
 * @author "Jim Daniel"
 *
 */
public class ReleaseDaoImpl extends HibernateDaoSupport implements ReleaseDao
{

	public Object findById(int id)
	{	
		List list = getHibernateTemplate().find("from Release where id=?",id);
		return (Release) list.get(0);
	}
	
	public void insert(Object object)
	{
		getHibernateTemplate().save((Release)object);		
	}

	public void remove(Object object)
	{
		getHibernateTemplate().delete((Release)object);		
	}
	
	public void update(Object object)
	{
		getHibernateTemplate().update((Release)object);		
	}
	
	public Release loadReleaseByReleaseName(String name) 
    {    	
		List list = getHibernateTemplate().find("from Release where name=?", name);
		return (Release) list.get(0);      
    }


}
