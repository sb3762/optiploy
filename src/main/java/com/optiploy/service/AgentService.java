package com.optiploy.service;

import java.util.List;

import com.optiploy.model.Agent;

/**
 * @author "Jim Daniel"
 *
 */
public interface AgentService
{
	Agent findById(int id);
    void insert(Object object);
    Agent merge(Object object);
    void update(Object object);
    void remove(Object object);
    
    public Agent getAgentByAgentName(String name);
    
    public List getRunningAgents();

}
