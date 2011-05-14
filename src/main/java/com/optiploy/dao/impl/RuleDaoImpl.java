package com.optiploy.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.optiploy.dao.RuleDao;
import com.optiploy.model.Rule;

/**
 * @author "Jim Daniel"
 *
 */
public class RuleDaoImpl extends HibernateDaoSupport implements RuleDao
{

	public Object findById(int id)
	{	
		List list = getHibernateTemplate().find("from Rule where id=?",id);
		return (Rule) list.get(0);
	}
	
	public void insert(Object object)
	{
		getHibernateTemplate().save((Rule)object);		
	}

	public void remove(Object object)
	{
		getHibernateTemplate().delete((Rule)object);		
	}
	
	public void update(Object object)
	{
		getHibernateTemplate().update((Rule)object);		
	}
	
	public Rule loadRuleByRuleName(String name) 
    {    	
		List list = getHibernateTemplate().find("from Rule where name=?", name);
		return (Rule) list.get(0);      
    }

	public List loadAllRules()
	{
		List<Rule> list = getHibernateTemplate().find("from Rule");
		
		return list;
	}


}
