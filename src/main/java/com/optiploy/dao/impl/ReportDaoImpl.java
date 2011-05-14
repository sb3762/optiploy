package com.optiploy.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.optiploy.dao.ReportDao;
import com.optiploy.model.Report;

/**
 * @author "Jim Daniel"
 *
 */
public class ReportDaoImpl extends HibernateDaoSupport implements ReportDao
{

	public Object findById(int id)
	{	
		List list = getHibernateTemplate().find("from Report where id=?",id);
		return (Report) list.get(0);
	}
	
	public void insert(Object object)
	{
		getHibernateTemplate().save((Report)object);		
	}

	public void remove(Object object)
	{
		getHibernateTemplate().delete((Report)object);		
	}
	
	public void update(Object object)
	{
		getHibernateTemplate().update((Report)object);		
	}
	
	public Report loadReportByReportName(String name) 
    {    	
		List list = getHibernateTemplate().find("from Report where name=?", name);
		return (Report) list.get(0);      
    }


}
