package com.optiploy.service.impl;

import com.optiploy.dao.ReportMenuDao;
import com.optiploy.model.ReportMenu;
import com.optiploy.service.ReportMenuService;

/**
 * @author "Jim Daniel"
 *
 */
public class ReportMenuServiceImpl implements ReportMenuService
{
	ReportMenuDao reportMenuDao;

	public Object findById(int id)
	{
		return reportMenuDao.findById(id);
	}

	public void insert(Object object)
	{
		reportMenuDao.insert((ReportMenu)object);		
	}

	public void remove(Object object)
	{
		reportMenuDao.remove((ReportMenu)object);		
	}

	public void update(Object object)
	{
		reportMenuDao.update((ReportMenu)object);		
	}
	
	public void setReportMenuDao(ReportMenuDao reportMenuDao) 
	{
        this.reportMenuDao = reportMenuDao;
    }
	
	public ReportMenu getReportMenuByReportMenuName(String name)
	{
		return reportMenuDao.loadReportMenuByReportMenuName(name);
	}

}
