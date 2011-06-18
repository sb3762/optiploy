package com.optiploy.dao;

import org.springframework.transaction.annotation.Transactional;

import com.optiploy.model.Role;

/**
 * @author "Jim Daniel"
 *
 */
public interface RoleDao
{
	Object findById(int id);
    void insert(Object object);
    void update(Object object);
    void remove(Object object);
    
    @Transactional
    Role loadRoleByName(String name);

}
