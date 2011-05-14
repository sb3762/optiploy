package com.optiploy.dao;


/**
 * @author "Jim Daniel"
 *
 */
public interface LogFileDao
{
	Object findById(int id);
    void insert(Object object);
    Object merge(Object object);
    void update(Object object);
    void remove(Object object);
    
}
