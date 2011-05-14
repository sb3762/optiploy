package com.optiploy.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.springframework.security.core.GrantedAuthority;


/**
 * This class is used to represent available jobs in the database.
 */
@Entity
@Table(name="JOB")
@NamedQueries ({
    @NamedQuery(
        name = "findJobByName",
        query = "select j from Job j where j.name = :name "
        )
})
public class Job extends BaseObject implements Serializable, GrantedAuthority
{
	private int id;
	private int releaseId;
	private String progressId;
	private String name;
	private String description;		
	private Set<Script> scripts = new HashSet<Script>();
	private Set<Role> roles = new HashSet<Role>();
    
    /**
     * Default constructor - creates a new instance with no values set.
     */
    public Job()
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
	
	@Column(length=100, nullable=false, unique=true)
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	@Column(length=1000, nullable=false)
	public String getDescription()
	{
		return description;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}

	@Column
	public int getReleaseId()
	{
		return releaseId;
	}

	public void setReleaseId(int releaseId)
	{
		this.releaseId = releaseId;
	}

	public String getProgressId()
	{
		return progressId;
	}

	public void setProgressId(String progressId)
	{
		this.progressId = progressId;
	}
    
	@ManyToMany(fetch = FetchType.EAGER) 
    @JoinTable(
            name="JOB_SCRIPT",
            joinColumns = { @JoinColumn( name="job_id") },
            inverseJoinColumns = @JoinColumn( name="script_id")
    )    
    public Set<Script> getScripts() 
    {
        return scripts;
    }
	
	public void setScripts(Set<Script> scripts) 
	{
	    this.scripts = scripts;
	}
    
    @Transient
    public List<LabelValue> getScriptList() 
    {
        List<LabelValue> userScripts = new ArrayList<LabelValue>();

        if (this.scripts != null) 
        {
            for (Script script : scripts) 
            {
                // convert the user's scripts to LabelValue Objects
                userScripts.add(new LabelValue(script.getName(), script.getName()));
            }
        }

        return userScripts;
    }  
    
    public void addScript(Script script) 
    {
        getScripts().add(script);
    }
    
    @ManyToMany(fetch = FetchType.EAGER) 
    @JoinTable(
            name="JOB_ROLE",
            joinColumns = { @JoinColumn( name="job_id") },
            inverseJoinColumns = @JoinColumn( name="role_id")
    )    
    public Set<Role> getRoles() 
    {
        return roles;
    }
	
	public void setRoles(Set<Role> roles) 
	{
	    this.roles = roles;
	}
    
    @Transient
    public List<LabelValue> getRoleList() 
    {
        List<LabelValue> jobRoles = new ArrayList<LabelValue>();

        if (this.roles != null) 
        {
            for (Role role : roles) 
            {
                // convert the job's roles to LabelValue Objects
                jobRoles.add(new LabelValue(role.getName(), role.getName()));
            }
        }

        return jobRoles;
    }  
    
    public void addRole(Role role) 
    {
        getRoles().add(role);
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
