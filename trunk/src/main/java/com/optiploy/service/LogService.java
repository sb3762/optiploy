package com.optiploy.service;

import com.optiploy.model.Log;

/**
 * @author "Jim Daniel"
 *
 */
public interface LogService
{
	Log findById(int id);
    void insert(Object object);
    Log merge(Object object);
    void update(Object object);
    void remove(Object object);
}
