package com.optiploy.manager.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;

import com.optiploy.dao.UniversalDao;
import com.optiploy.manager.UniversalManager;

/**
 * Base class for Business Services - use this class for utility methods and
 * generic CRUD methods.
 */
public class UniversalManagerImpl implements UniversalManager 
{
    /**
     * Log4j instance for all child classes. 
     */
	private static Logger logger = Logger.getLogger(UniversalManagerImpl.class);

    /**
     * UniversalDao instance, ready to charge forward and persist to the database
     */
    protected UniversalDao dao;
 
    public void setDao(UniversalDao dao) 
    {
        this.dao = dao;
    }

    /**
     * {@inheritDoc}
     */
    public Object get(Class clazz, Serializable id) 
    {
        return dao.get(clazz, id);
    }

    /**
     * {@inheritDoc}
     */
    public List getAll(Class clazz) 
    {
        return dao.getAll(clazz);
    }

    /**
     * {@inheritDoc}
     */
    public void remove(Class clazz, Serializable id) 
    {
        dao.remove(clazz, id);
    }

    /**
     * {@inheritDoc}
     */
    public Object save(Object o) 
    {
        return dao.save(o);
    }
}
