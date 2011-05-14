package com.optiploy.service;

import java.util.List;

import com.optiploy.model.Rule;

/**
 * @author "Jim Daniel"
 *
 */
public interface RuleService
{
	Object findById(int id);
    void insert(Object object);
    void update(Object object);
    void remove(Object object);
    
    public Rule getRuleByRuleName(String name);
    
    public List getAllRules();

}
