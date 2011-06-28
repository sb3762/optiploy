package com.optiploy.service;

import com.optiploy.model.LogFile;

/**
 * @author "Jim Daniel"
 *
 */
public interface LogFileService
{
	LogFile findById(int id);
    void insert(Object object);
    LogFile merge(Object object);
    void update(Object object);
    void remove(Object object);
    
    LogFile findByLogId(int logId);
}
