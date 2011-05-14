package com.optiploy.service.impl;

import java.util.List;

import com.optiploy.dao.RuleDao;
import com.optiploy.model.Rule;
import com.optiploy.service.RuleService;

/**
 * @author "Jim Daniel"
 *
 */
public class RuleServiceImpl implements RuleService
{
	RuleDao ruleDao;

	public Object findById(int id)
	{
		return ruleDao.findById(id);
	}

	public void insert(Object object)
	{
		ruleDao.insert((Rule)object);		
	}

	public void remove(Object object)
	{
		ruleDao.remove((Rule)object);		
	}

	public void update(Object object)
	{
		ruleDao.update((Rule)object);		
	}
	
	public void setRuleDao(RuleDao ruleDao) 
	{
        this.ruleDao = ruleDao;
    }
	
	public Rule getRuleByRuleName(String name)
	{
		return ruleDao.loadRuleByRuleName(name);
	}

	public List<Rule> getAllRules()
	{
		return ruleDao.loadAllRules();
	}

}
