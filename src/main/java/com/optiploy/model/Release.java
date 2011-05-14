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
 * This class is used to represent available releases in the database.
 */
@Entity
@Table(name="RELEASES")
@NamedQueries ({
    @NamedQuery(
        name = "findReleaseByName",
        query = "select r from Release r where r.name = :name "
        )
})
public class Release extends BaseObject implements Serializable, GrantedAuthority
{
	private static final long	serialVersionUID	= -6167564093810903594L;
	
	private int id;
	private String name;
	private String description;

    public Release()
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
	
	@Column(length=50,unique=true,nullable=false)
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	@Column(length=64)
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
