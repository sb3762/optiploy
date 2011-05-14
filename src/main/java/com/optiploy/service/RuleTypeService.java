package com.optiploy.service;

import com.optiploy.model.RuleType;

/**
 * @author "Jim Daniel"
 *
 */
public interface RuleTypeService
{
	Object findById(int id);
    void insert(Object object);
    void update(Object object);
    void remove(Object object);
    
    public RuleType getRuleTypeByRuleTypeName(String name);

}
