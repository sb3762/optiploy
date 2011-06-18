/**
 * 
 */
package com.optiploy.service.impl;

import com.optiploy.dao.RoleDao;
import com.optiploy.model.Role;
import com.optiploy.service.RoleService;

/**
 * @author "Jim Daniel"
 *
 */
public class RoleServiceImpl implements RoleService
{
	RoleDao roleDao;

	public Object findById(int id)
	{
		return roleDao.findById(id);
	}

	public void insert(Object object)
	{
		roleDao.insert((Role)object);		
	}

	public void remove(Object object)
	{
		roleDao.remove((Role)object);		
	}

	public void update(Object object)
	{
		roleDao.update((Role)object);		
	}
	
	public void setRoleDao(RoleDao roleDao) 
	{
        this.roleDao = roleDao;
    }
	
	public Role getRoleByName(String name)
	{
		return roleDao.loadRoleByName(name);
	}

}
