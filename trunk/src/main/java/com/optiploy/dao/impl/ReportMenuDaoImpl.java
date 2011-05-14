package com.optiploy.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.optiploy.dao.ReportMenuDao;
import com.optiploy.model.ReportMenu;

/**
 * @author "Jim Daniel"
 *
 */
public class ReportMenuDaoImpl extends HibernateDaoSupport implements ReportMenuDao
{

	public Object findById(int id)
	{	
		List list = getHibernateTemplate().find("from ReportMenu where id=?",id);
		return (ReportMenu) list.get(0);
	}
	
	public void insert(Object object)
	{
		getHibernateTemplate().save((ReportMenu)object);		
	}

	public void remove(Object object)
	{
		getHibernateTemplate().delete((ReportMenu)object);		
	}
	
	public void update(Object object)
	{
		getHibernateTemplate().update((ReportMenu)object);		
	}
	
	public ReportMenu loadReportMenuByReportMenuName(String name) 
    {    	
		List list = getHibernateTemplate().find("from ReportMenu where name=?", name);
		return (ReportMenu) list.get(0);      
    }


}
