package com.optiploy.service.impl;

import com.optiploy.dao.RuleTypeDao;
import com.optiploy.model.RuleType;
import com.optiploy.service.RuleTypeService;

/**
 * @author "Jim Daniel"
 *
 */
public class RuleTypeServiceImpl implements RuleTypeService
{
	RuleTypeDao ruleTypeDao;

	public Object findById(int id)
	{
		return ruleTypeDao.findById(id);
	}

	public void insert(Object object)
	{
		ruleTypeDao.insert((RuleType)object);		
	}

	public void remove(Object object)
	{
		ruleTypeDao.remove((RuleType)object);		
	}

	public void update(Object object)
	{
		ruleTypeDao.update((RuleType)object);		
	}
	
	public void setRuleTypeDao(RuleTypeDao ruleTypeDao) 
	{
        this.ruleTypeDao = ruleTypeDao;
    }
	
	public RuleType getRuleTypeByRuleTypeName(String name)
	{
		return ruleTypeDao.loadRuleTypeByRuleTypeName(name);
	}

}
