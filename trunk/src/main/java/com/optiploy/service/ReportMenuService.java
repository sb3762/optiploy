package com.optiploy.service;

import com.optiploy.model.ReportMenu;

/**
 * @author "Jim Daniel"
 *
 */
public interface ReportMenuService
{
	Object findById(int id);
    void insert(Object object);
    void update(Object object);
    void remove(Object object);
    
    public ReportMenu getReportMenuByReportMenuName(String name);

}
