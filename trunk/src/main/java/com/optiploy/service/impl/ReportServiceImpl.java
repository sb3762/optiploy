package com.optiploy.service.impl;

import com.optiploy.dao.ReportDao;
import com.optiploy.model.Report;
import com.optiploy.service.ReportService;

/**
 * @author "Jim Daniel"
 *
 */
public class ReportServiceImpl implements ReportService
{
	ReportDao reportDao;

	public Object findById(int id)
	{
		return reportDao.findById(id);
	}

	public void insert(Object object)
	{
		reportDao.insert((Report)object);		
	}

	public void remove(Object object)
	{
		reportDao.remove((Report)object);		
	}

	public void update(Object object)
	{
		reportDao.update((Report)object);		
	}
	
	public void setReportDao(ReportDao reportDao) 
	{
        this.reportDao = reportDao;
    }
	
	public Report getReportByReportName(String name)
	{
		return reportDao.loadReportByReportName(name);
	}

}
