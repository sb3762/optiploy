package com.optiploy.model;

import java.io.File;
import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * This class is used to represent available logs in the database.
 */
@Entity
@Table(name="LOG_FILE")
public class LogFile
{
	private int id;
	private int logId;
	private Blob logFile;
	
	public LogFile()
	{
		
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	@Id  @GeneratedValue(strategy=GenerationType.AUTO)
	public int getId()
	{
		return id;
	}
	
	public void setLogId(int logId)
	{
		this.logId = logId;
	}
	
	@Column
	public int getLogId()
	{
		return logId;
	}
	
	public void setLogFile(Blob logFile)
	{
		this.logFile = logFile;
	}
	
	@Lob
	public Blob getLogFile()
	{
		return logFile;
	}

}
