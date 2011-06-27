package com.optiploy.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.optiploy.model.Agent;

/**
 * @author "Jim Daniel"
 *
 */
public interface AgentDao
{
	Agent findById(int id);
    void insert(Object object);
    Agent merge(Object object);
    void update(Object object);
    void remove(Object object);
    
    @Transactional
    Agent loadAgentByAgentName(String name);
    
    @Transactional
    List loadRunningAgents();
    
}
