package com.optiploy.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.optiploy.dao.RoleDao;
import com.optiploy.model.Role;

/**
 * @author "Jim Daniel"
 *
 */
public class RoleDaoImpl extends HibernateDaoSupport implements RoleDao
{

	public Object findById(int id)
	{	
		List list = getHibernateTemplate().find("from Role where id=?",id);
		return (Role) list.get(0);
	}
	
	public void insert(Object object)
	{
		getHibernateTemplate().save((Role)object);		
	}

	public void remove(Object object)
	{
		getHibernateTemplate().delete((Role)object);		
	}
	
	public void update(Object object)
	{
		getHibernateTemplate().update((Role)object);		
	}
	
	public Role loadRoleByRoleName(String name) 
    {    	
		List list = getHibernateTemplate().find("from Role where name=?", name);
		return (Role) list.get(0);      
    }

}
