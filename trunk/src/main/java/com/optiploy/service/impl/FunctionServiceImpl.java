package com.optiploy.service.impl;

import com.optiploy.dao.FunctionDao;
import com.optiploy.model.Function;
import com.optiploy.service.FunctionService;

/**
 * @author "Jim Daniel"
 *
 */
public class FunctionServiceImpl implements FunctionService
{
	FunctionDao functionDao;

	public Object findById(int id)
	{
		return functionDao.findById(id);
	}

	public void insert(Object object)
	{
		functionDao.insert((Function)object);		
	}

	public void remove(Object object)
	{
		functionDao.remove((Function)object);		
	}

	public void update(Object object)
	{
		functionDao.update((Function)object);		
	}
	
	public void setFunctionDao(FunctionDao functionDao) 
	{
        this.functionDao = functionDao;
    }
	
	public Function getFunctionByFunctionName(String name)
	{
		return functionDao.loadFunctionByFunctionName(name);
	}

}
