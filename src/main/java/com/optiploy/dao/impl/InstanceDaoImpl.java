package com.optiploy.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.optiploy.constants.Constants;
import com.optiploy.dao.InstanceDao;
import com.optiploy.model.Instance;

/**
 * @author "Jim Daniel"
 *
 */
public class InstanceDaoImpl extends HibernateDaoSupport implements InstanceDao
{
	private static Logger logger = Logger.getLogger(InstanceDaoImpl.class);
	
	public Object findById(int id)
	{	
		List list = getHibernateTemplate().find("from Instance where id=?",id);
		return (Instance) list.get(0);
	}
	
	public void insert(Object object)
	{
		getHibernateTemplate().save((Instance)object);		
	}
	
	public Instance merge(Object object)
	{
		return (Instance)getHibernateTemplate().merge((Instance)object);		
	}

	public void remove(Object object)
	{
		getHibernateTemplate().delete((Instance)object);		
	}
	
	public void update(Object object)
	{
		getHibernateTemplate().update((Instance)object);		
	}
	

	public List<Instance> loadReadyInstances()
	{		
		List<Instance> list = getHibernateTemplate().find("from Instance where status=? order by priority", Constants.INSTANCE_STATUS_READY);
		
		return list;		
	}
	
	public void deleteAllAgentInstances(int agentId)
	{
		List<Instance> list = getHibernateTemplate().find("from Instance where agentID=?", agentId);
		getHibernateTemplate().deleteAll(list);		
	}

	

}
