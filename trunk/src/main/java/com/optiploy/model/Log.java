package com.optiploy.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This class is used to represent available logs in the database.
 */
@Entity
@Table(name="LOG")
public class Log
{
	private int id;
	private String label;
    private Date start;
    private Date complete;
    private int userId;
    private int jobId;
    private Integer agentId;
    private boolean success;
    private StringBuffer buildMessage = new StringBuffer();    
	private StringBuffer buildProgress = new StringBuffer();
	
	public Log()
	{
		
	}
	
	@Id  @GeneratedValue(strategy=GenerationType.AUTO)
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	@Column(length=100)
	public String getLabel()
	{
		return label;
	}
	
	public void setLabel(String label)
	{
		this.label = label;
	}
	
	@Column
	public Date getStart()
	{
		return start;
	}
	
	public void setStart(Date start)
	{
		this.start = start;
	}
	
	@Column
	public Date getComplete()
	{
		return complete;
	}
	
	public void setComplete(Date complete)
	{
		this.complete = complete;
	}
	
	@Column
	public int getUserId()
	{
		return userId;
	}
	
	public void setUserId(int userId)
	{
		this.userId = userId;
	}
	
	@Column
	public int getJobId()
	{
		return jobId;
	}
	
	public void setJobId(int jobId)
	{
		this.jobId = jobId;
	}
	
	@Column
	public Integer getAgentId()
	{
		return agentId;
	}
	
	public void setAgentId(Integer agentId)
	{
		this.agentId = agentId;
	}
	
	@Column
	public boolean isSuccess()
	{
		return success;
	}
	
	public void setSuccess(boolean success)
	{
		this.success = success;
	}
	
	@Column(length=1000)
	public String getBuildMessage()
	{
		return buildMessage.toString();
	}
	
	public void setBuildMessage(String buildMessage)
	{ 
		this.buildMessage.append(buildMessage + " ");
	}
	
	@Column(length=1000)
	public String getBuildProgress()
	{
		return buildProgress.toString();
	}
	
	public void setBuildProgress(String buildProgress)
	{
		this.buildProgress.append(buildProgress + " ");
	}

}
