package com.optiploy.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.optiploy.dao.JobDao;
import com.optiploy.model.Job;

/**
 * @author "Jim Daniel"
 *
 */
public class JobDaoImpl extends HibernateDaoSupport implements JobDao
{

	public Object findById(int id)
	{	
		List list = getHibernateTemplate().find("from Job where id=?",id);
		return (Job) list.get(0);
	}
	
	public void insert(Object object)
	{
		getHibernateTemplate().save((Job)object);		
	}

	public void remove(Object object)
	{
		getHibernateTemplate().delete((Job)object);		
	}
	
	public void update(Object object)
	{
		getHibernateTemplate().update((Job)object);		
	}
	
	public Job loadJobByJobName(String name) 
    {    	
		List list = getHibernateTemplate().find("from Job where name=?", name);
		return (Job) list.get(0);      
    }


}
