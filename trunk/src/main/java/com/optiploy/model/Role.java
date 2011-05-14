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
 * This class is used to represent available roles in the database.
 */
@Entity
@Table(name="ROLE")
@NamedQueries ({
    @NamedQuery(
        name = "findRoleByName",
        query = "select r from Role r where r.name = :name "
        )
})
public class Role extends BaseObject implements Serializable, GrantedAuthority 
{
    private static final long serialVersionUID = 6324705500106349851L;
    
	private int id;
    private String name;
    private String description;

    public Role() 
    {
    	
    }

    public Role(final String name) 
    {
        this.name = name;
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

    @Column(length=20,unique=true,nullable=false)
    public String getName() 
    {
        return this.name;
    } 

    public void setName(String name) 
    {
        this.name = name;
    }
    
    @Column(length=64)
    public String getDescription() 
    {
        return this.description;
    }

    public void setDescription(String description) 
    {
        this.description = description;
    }
    
    /**
     * @see org.springframework.security.GrantedAuthority#getAuthority()
     * @return the name property (getAuthority required by Acegi's GrantedAuthority interface)
     */
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
