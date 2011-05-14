package com.optiploy.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.optiploy.dao.UniversalDao;

/**
 * This class serves as the a class that can CRUD any object witout any
 * Spring configuration. The only downside is it does require casting
 * from Object to the object class.
 */
public class UniversalDaoImpl extends HibernateDaoSupport implements UniversalDao 
{
    /**
     * Log variable for all child classes.
     */
	protected static Logger logger = Logger.getLogger(UniversalDaoImpl.class);
    
    /**
     * {@inheritDoc}
     */
    public Object save(Object o) 
    {
        return getHibernateTemplate().merge(o);
    }

    /**
     * {@inheritDoc}
     */
    public Object get(Class clazz, Serializable id) 
    {
        Object o = getHibernateTemplate().get(clazz, id);

        if (o == null) 
        {
            throw new ObjectRetrievalFailureException(clazz, id);
        }

        return o;
    }

    /**
     * {@inheritDoc}
     */
    public List getAll(Class clazz) 
    {
        return getHibernateTemplate().loadAll(clazz);
    }

    /**
     * {@inheritDoc}
     */
    public void remove(Class clazz, Serializable id) 
    {
        getHibernateTemplate().delete(get(clazz, id));
    }
}
