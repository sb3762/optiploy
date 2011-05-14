package com.optiploy.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.optiploy.dao.RuleTypeDao;
import com.optiploy.model.RuleType;

/**
 * @author "Jim Daniel"
 *
 */
public class RuleTypeDaoImpl extends HibernateDaoSupport implements RuleTypeDao
{

	public Object findById(int id)
	{	
		List list = getHibernateTemplate().find("from RuleType where id=?",id);
		return (RuleType) list.get(0);
	}
	
	public void insert(Object object)
	{
		getHibernateTemplate().save((RuleType)object);		
	}

	public void remove(Object object)
	{
		getHibernateTemplate().delete((RuleType)object);		
	}
	
	public void update(Object object)
	{
		getHibernateTemplate().update((RuleType)object);		
	}
	
	public RuleType loadRuleTypeByRuleTypeName(String name) 
    {    	
		List list = getHibernateTemplate().find("from RuleType where name=?", name);
		return (RuleType) list.get(0);      
    }


}
