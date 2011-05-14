package com.optiploy.dao;

import org.springframework.transaction.annotation.Transactional;

import com.optiploy.model.ReportMenu;

/**
 * @author "Jim Daniel"
 *
 */
public interface ReportMenuDao
{
	Object findById(int id);
    void insert(Object object);
    void update(Object object);
    void remove(Object object);
    
    @Transactional
    ReportMenu loadReportMenuByReportMenuName(String name);
    
}
