package com.optiploy.service.impl;

import com.optiploy.dao.ModuleDao;
import com.optiploy.model.Module;
import com.optiploy.service.ModuleService;

/**
 * @author "Jim Daniel"
 *
 */
public class ModuleServiceImpl implements ModuleService
{
	ModuleDao moduleDao;

	public Object findById(int id)
	{
		return moduleDao.findById(id);
	}

	public void insert(Object object)
	{
		moduleDao.insert((Module)object);		
	}

	public void remove(Object object)
	{
		moduleDao.remove((Module)object);		
	}

	public void update(Object object)
	{
		moduleDao.update((Module)object);		
	}
	
	public void setModuleDao(ModuleDao moduleDao) 
	{
        this.moduleDao = moduleDao;
    }
	
	public Module getModuleByModuleName(String name)
	{
		return moduleDao.loadModuleByModuleName(name);
	}

}
