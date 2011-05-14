package com.optiploy.packet;

import java.io.Serializable;
import java.util.HashMap;

public class Packet implements Serializable
{

	private static final long serialVersionUID = -512575698461196902L;
	
	private String version;
    private String requestType;
    private HashMap parameters = new HashMap();
    private int logId;
    private int jobId;

    public Object getParameter(Object key)
    {
        return parameters.get(key);
    }

    public void setParameter(Object key, Object value)
    {
        parameters.put(key, value);
    }

    public HashMap getParameters()
    {
        return this.parameters;
    }

    public void setParameters(HashMap buildParameters)
    {
        this.parameters = buildParameters;
    }

    public String getRequestType()
    {
        return this.requestType;
    }

    public void setRequestType(String requestType)
    {
        this.requestType = requestType;
    }

    public String getVersion()
    {
        return this.version;
    }

    public void setVersion(String version)
    {
        this.version = version;
    }

	public void setLogId(int logId)
	{
		this.logId = logId;
	}

	public int getLogId()
	{
		return logId;
	}  
	
	public void setJobId(int jobId)
	{
		this.jobId = jobId;
	}

	public int getJobId()
	{
		return jobId;
	} 
   
}
