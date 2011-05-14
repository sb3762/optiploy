package com.optiploy.dao;

import org.springframework.transaction.annotation.Transactional;

import com.optiploy.model.RuleType;

/**
 * @author "Jim Daniel"
 *
 */
public interface RuleTypeDao
{
	Object findById(int id);
    void insert(Object object);
    void update(Object object);
    void remove(Object object);
    
    @Transactional
    RuleType loadRuleTypeByRuleTypeName(String name);
    
}
