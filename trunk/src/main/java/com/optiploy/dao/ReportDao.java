package com.optiploy.dao;

import org.springframework.transaction.annotation.Transactional;

import com.optiploy.model.Report;

/**
 * @author "Jim Daniel"
 *
 */
public interface ReportDao
{
	Object findById(int id);
    void insert(Object object);
    void update(Object object);
    void remove(Object object);
    
    @Transactional
    Report loadReportByReportName(String name);
    
}
