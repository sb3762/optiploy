package com.optiploy.model;

public class Build
{

	private int jobId;
	private int logId;
	private String jobName;
	private String jobDecription;
	private String processingAgent;
	private String startTimestamp;
	private String completeTimestamp;
	private String timeToCompletion;
	private String buildMessages;
	private boolean success;
	
	public int getJobId()
	{
		return this.jobId;
	}
	public void setJobId(int jobId)
	{
		this.jobId = jobId;
	}
	public void setLogId(int logId)
	{
		this.logId = logId;
	}
	public int getLogId()
	{
		return logId;
	}
	public String getJobName()
	{
		return this.jobName;
	}
	public void setJobName(String jobName)
	{
		this.jobName = jobName;
	}
	public String getJobDecription()
	{
		return this.jobDecription;
	}
	public void setJobDecription(String jobDecription)
	{
		this.jobDecription = jobDecription;
	}
	public String getProcessingAgent()
	{
		return this.processingAgent;
	}
	public void setProcessingAgent(String processingAgent)
	{
		this.processingAgent = processingAgent;
	}
	public String getStartTimestamp()
	{
		return this.startTimestamp;
	}
	public void setStartTimestamp(String startTimestamp)
	{
		this.startTimestamp = startTimestamp;
	}
	public String getCompleteTimestamp()
	{
		return this.completeTimestamp;
	}
	public void setCompleteTimestamp(String completeTimestamp)
	{
		this.completeTimestamp = completeTimestamp;
	}
	public String getTimeToCompletion()
	{
		return this.timeToCompletion;
	}
	public void setTimeToCompletion(String timeToCompletion)
	{
		this.timeToCompletion = timeToCompletion;
	}
	public void setBuildMessages(String buildMessages)
	{
		this.buildMessages = buildMessages;
	}
	public String getBuildMessages()
	{
		return buildMessages;
	}
	public boolean isSuccess()
	{
		return this.success;
	}
	public void setSuccess(boolean success)
	{
		this.success = success;
	}	
}
