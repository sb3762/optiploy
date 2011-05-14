package com.optiploy.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.optiploy.model.Rule;

/**
 * @author "Jim Daniel"
 *
 */
public interface RuleDao
{
	Object findById(int id);
    void insert(Object object);
    void update(Object object);
    void remove(Object object);
    
    @Transactional
    Rule loadRuleByRuleName(String name);
    
    @Transactional
    List loadAllRules();
    
}
