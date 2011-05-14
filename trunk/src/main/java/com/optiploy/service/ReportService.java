package com.optiploy.service;

import com.optiploy.model.Report;

/**
 * @author "Jim Daniel"
 *
 */
public interface ReportService
{
	Object findById(int id);
    void insert(Object object);
    void update(Object object);
    void remove(Object object);
    
    public Report getReportByReportName(String name);

}
