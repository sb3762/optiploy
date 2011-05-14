package com.optiploy.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.springframework.security.core.GrantedAuthority;

/**
 * This class is used to represent available agents in the database.
 */
@Entity
@Table(name="AGENT")
@NamedQueries ({
    @NamedQuery(
        name = "findAgentByName",
        query = "select a from Agent a where a.name = :name "
        )
})
public class Agent extends BaseObject implements Serializable, GrantedAuthority
{
	private int id;
	private String name;
	private String description;
	private int instances;
	private int priority;
	private String status;
    private String address;   
    private String version;
    private int port;
    private int action;
    
    /**
     * Default constructor - creates a new instance with no values set.
     */
    public Agent()
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
	
	@Column(length=20, nullable=false)
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	@Column(length=64, nullable=false)
    public String getDescription() 
    {
        return this.description;
    } 
	
	public void setDescription(String description) 
    {
        this.description = description;
    }

	public void setInstances(int instances)
	{
		this.instances = instances;
	}

	@Column(nullable=false)
	public int getInstances()
	{
		return instances;
	}
	
	@Column(nullable=false)
	public int getPriority()
	{
		return priority;
	}
	
	public void setPriority(int priority)
	{
		this.priority = priority;
	}
	
	public void setStatus(String status)
	{
		this.status = status;
	}

	@Column(length=10, nullable=false)
	public String getStatus()
	{
		return status;
	}	
	
	@Column(length=100, nullable=false)
	public String getAddress()
	{
		return address;
	}
	
	public void setAddress(String address)
	{
		this.address = address;
	}
			
	@Column(length=10, nullable=false)
	public String getVersion()
	{
		return version;
	}
	
	public void setVersion(String version)
	{
		this.version = version;
	}
	
	@Column(nullable=false)
	public int getPort()
	{
		return port;
	}
	
	public void setPort(int port)
	{
		this.port = port;
	}
    
	@Transient
    public String getAuthority() 
    {
        return getName();
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
