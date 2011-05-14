package com.optiploy.service.impl;

import com.optiploy.dao.ScriptDao;
import com.optiploy.model.Script;
import com.optiploy.service.ScriptService;

/**
 * @author "Jim Daniel"
 *
 */
public class ScriptServiceImpl implements ScriptService
{
	ScriptDao scriptDao;

	public Object findById(int id)
	{
		return scriptDao.findById(id);
	}

	public void insert(Object object)
	{
		scriptDao.insert((Script)object);		
	}

	public void remove(Object object)
	{
		scriptDao.remove((Script)object);		
	}

	public void update(Object object)
	{
		scriptDao.update((Script)object);		
	}
	
	public void setScriptDao(ScriptDao scriptDao) 
	{
        this.scriptDao = scriptDao;
    }
	
	public Script getScriptByScriptName(String name)
	{
		return scriptDao.loadScriptByScriptName(name);
	}

}
