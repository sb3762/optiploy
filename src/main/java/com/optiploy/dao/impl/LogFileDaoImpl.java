package com.optiploy.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.optiploy.dao.LogFileDao;
import com.optiploy.model.LogFile;

/**
 * @author "Jim Daniel"
 *
 */
public class LogFileDaoImpl extends HibernateDaoSupport implements LogFileDao
{

	public Object findById(int id)
	{	
		List list = getHibernateTemplate().find("from LogFile where id=?",id);
		return (LogFile) list.get(0);
	}
	
	public void insert(Object object)
	{
		getHibernateTemplate().save((LogFile)object);		
	}
	
	public LogFile merge(Object object)
	{
		return (LogFile)getHibernateTemplate().merge((LogFile)object);		
	}

	public void remove(Object object)
	{
		getHibernateTemplate().delete((LogFile)object);		
	}
	
	public void update(Object object)
	{
		getHibernateTemplate().update((LogFile)object);	
	}

	public Object findByLogId(int logId)
	{
		List list = getHibernateTemplate().find("from LogFile where logId=?",logId);
		return (LogFile) list.get(0);
	}
	

}
