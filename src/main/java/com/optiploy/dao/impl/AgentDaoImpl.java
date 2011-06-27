package com.optiploy.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.optiploy.constants.Constants;
import com.optiploy.dao.AgentDao;
import com.optiploy.model.Agent;

/**
 * @author "Jim Daniel"
 *
 */
public class AgentDaoImpl extends HibernateDaoSupport implements AgentDao
{

	public Agent findById(int id)
	{	
		List list = getHibernateTemplate().find("from Agent where id=?",id);
		return (Agent) list.get(0);
	}
	
	public void insert(Object object)
	{
		getHibernateTemplate().save((Agent)object);		
	}
	
	public Agent merge(Object object)
	{
		return (Agent)getHibernateTemplate().merge((Agent)object);		
	}

	public void remove(Object object)
	{
		getHibernateTemplate().delete((Agent)object);		
	}
	
	public void update(Object object)
	{
		getHibernateTemplate().update((Agent)object);		
	}
	
	public Agent loadAgentByAgentName(String name) 
    {    	
		List list = getHibernateTemplate().find("from Agent where name=?", name);
		return (Agent) list.get(0);      
    }

	public List loadRunningAgents()
	{
		List list = getHibernateTemplate().find("from Agent where status=?", Constants.AGENT_STATUS_RUNNING);
		return list;
	}

	

}
