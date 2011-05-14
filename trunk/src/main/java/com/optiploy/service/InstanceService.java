package com.optiploy.service;

import java.util.List;

import com.optiploy.model.Instance;

/**
 * @author "Jim Daniel"
 *
 */
public interface InstanceService
{
	Object findById(int id);
    void insert(Object object);
    Instance merge(Object object);
    void update(Object object);
    void remove(Object object);
        
    public List<Instance> getReadyInstances();
    
    public void deleteAllInstances(int agentId, String agentName);

}
