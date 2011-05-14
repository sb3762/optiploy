package com.optiploy.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * This class is used to represent available intances in the database.
 */
@Entity
@Table(name="INSTANCE")
public class Instance extends BaseObject implements Serializable
{
	private int id;
	private int agentId;
	private int port;
	private String status;
	private int priority;
	
	@Id  @GeneratedValue(strategy=GenerationType.AUTO)
	public int getId()
	{
		return this.id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	@Column(nullable=false)
	public int getAgentId()
	{
		return this.agentId;
	}
	
	public void setAgentId(int agentId)
	{
		this.agentId = agentId;
	}
	
	public void setPort(int port)
	{
		this.port = port;
	}

	@Column(nullable=false)
	public int getPort()
	{
		return port;
	}
	
	@Column(length=20, nullable=false)
	public String getStatus()
	{
		return this.status;
	}
	
	public void setStatus(String status)
	{
		this.status = status;
	}

	public void setPriority(int priority)
	{
		this.priority = priority;
	}

	@Column(nullable=false)
	public int getPriority()
	{
		return priority;
	}
    
    @Override
    public boolean equals(Object o)
    {		
		if (this == o) 
		{
            return true;
        }
		
        if (!(o instanceof Role)) 
        {
            return false;
        }

        final Role role1 = (Role) o;

        return this.hashCode() == role1.hashCode();
    }

	@Override
    public int hashCode()
    {
		return new HashCodeBuilder().append(getId()).toHashCode();
    }
}
