package com.optiploy.service;

import com.optiploy.model.Role;

/**
 * @author "Jim Daniel"
 *
 */
public interface RoleService
{
	Object findById(int id);
    void insert(Object object);
    void update(Object object);
    void remove(Object object);
    
    public Role getRoleByRoleName(String name);

}
