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
 * This class is used to represent available reports in the database.
 */
@Entity
@Table(name="REPORT")
@NamedQueries ({
    @NamedQuery(
        name = "findReportByName",
        query = "select r from Report r where r.name = :name "
        )
})
public class Report extends BaseObject implements Serializable, GrantedAuthority
{
	private int id;
	private String name;
	private String description;
	private String sqlQuery;
    private Integer reportMenuId;
    private Set<Role> roles = new HashSet<Role>();
    
    /**
     * Default constructor - creates a new instance with no values set.
     */
    public Report()
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
	
	@Column(length=100, nullable=false)
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

	@Column(length=1000, nullable=false)
	public String getSqlQuery()
	{
		return sqlQuery;
	}

	public void setSqlQuery(String sqlQuery)
	{
		this.sqlQuery = sqlQuery;
	}

	@Column
	public Integer getReportMenuId()
	{
		return reportMenuId;
	}

	public void setReportMenuId(Integer reportMenuId)
	{
		this.reportMenuId = reportMenuId;
	}
    
	@ManyToMany(fetch = FetchType.EAGER) 
    @JoinTable(
            name="REPORT_ROLE",
            joinColumns = { @JoinColumn( name="report_id") },
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
        List<LabelValue> reportRoles = new ArrayList<LabelValue>();

        if (this.roles != null) 
        {
            for (Role role : roles) 
            {
                // convert the report's roles to LabelValue Objects
                reportRoles.add(new LabelValue(role.getName(), role.getName()));
            }
        }

        return reportRoles;
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
