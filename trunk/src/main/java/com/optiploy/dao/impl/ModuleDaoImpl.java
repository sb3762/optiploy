package com.optiploy.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.optiploy.dao.ModuleDao;
import com.optiploy.model.Module;

/**
 * @author "Jim Daniel"
 *
 */
public class ModuleDaoImpl extends HibernateDaoSupport implements ModuleDao
{

	public Object findById(int id)
	{	
		List list = getHibernateTemplate().find("from Module where id=?",id);
		return (Module) list.get(0);
	}
	
	public void insert(Object object)
	{
		getHibernateTemplate().save((Module)object);		
	}

	public void remove(Object object)
	{
		getHibernateTemplate().delete((Module)object);		
	}
	
	public void update(Object object)
	{
		getHibernateTemplate().update((Module)object);		
	}
	
	public Module loadModuleByModuleName(String name) 
    {    	
		List list = getHibernateTemplate().find("from Module where name=?", name);
		return (Module) list.get(0);      
    }


}
