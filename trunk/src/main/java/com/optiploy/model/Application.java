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
 * This class is used to represent available applications in the database.
 */
@Entity
@Table(name="APPLICATION")
@NamedQueries ({
    @NamedQuery(
        name = "findApplicationByName",
        query = "select a from Application a where a.name = :name "
        )
})
public class Application extends BaseObject implements Serializable, GrantedAuthority
{
	private int id;
	private String name;
	private String description;
	private int action;
    
    /**
     * Default constructor - creates a new instance with no values set.
     */
    public Application()
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
