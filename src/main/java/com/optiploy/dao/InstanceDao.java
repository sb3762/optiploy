package com.optiploy.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author "Jim Daniel"
 *
 */
public interface InstanceDao
{
	Object findById(int id);
    void insert(Object object);
    Object merge(Object object);
    void update(Object object);
    void remove(Object object);    
    
    @Transactional
    List loadReadyInstances();
    
    @Transactional
    void deleteAllAgentInstances(int agentId, String agentName);
    
}
