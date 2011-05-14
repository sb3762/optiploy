package com.optiploy.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.optiploy.dao.ScriptDao;
import com.optiploy.model.Script;

/**
 * @author "Jim Daniel"
 *
 */
public class ScriptDaoImpl extends HibernateDaoSupport implements ScriptDao
{

	public Object findById(int id)
	{	
		List list = getHibernateTemplate().find("from Script where id=?",id);
		return (Script) list.get(0);
	}
	
	public void insert(Object object)
	{
		getHibernateTemplate().save((Script)object);		
	}

	public void remove(Object object)
	{
		getHibernateTemplate().delete((Script)object);		
	}
	
	public void update(Object object)
	{
		getHibernateTemplate().update((Script)object);		
	}
	
	public Script loadScriptByScriptName(String name) 
    {    	
		List list = getHibernateTemplate().find("from Script where name=?", name);
		return (Script) list.get(0);      
    }


}
