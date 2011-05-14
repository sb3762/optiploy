package com.optiploy.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.springframework.security.core.GrantedAuthority;


/**
 * This class is used to represent attributes in the database.
 */
@Entity
@Table(name="ATTRIBUTE")
public class Attribute extends BaseObject implements Serializable, GrantedAuthority
{
	private int id;
	private String name;
	private String label;
	private int typeId;
	private String mapKey;
	private String replaceText;
	private boolean confirm;
	
    
    /**
     * Default constructor - creates a new instance with no values set.
     */
    public Attribute()
    {
    	
    }
    
    @Id  @GeneratedValue(strategy=GenerationType.AUTO)
	public int getId()
	{
		return this.id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	@Column(length=50, nullable=false)
	public String getLabel()
	{
		return this.label;
	}
	
	public void setLabel(String label)
	{
		this.label = label;
	}

	@Column(length=50, nullable=false, unique=true)
	public String getName()
	{
		return this.name;
	}


	public void setName(String name)
	{
		this.name = name;
	}

	@Column(nullable=false)
	public int getTypeId()
	{
		return this.typeId;
	}

	public void setTypeId(int typeId)
	{
		this.typeId = typeId;
	}

	@Column(length=50, nullable=false, unique=true)
	public String getMapKey()
	{
		return this.mapKey;
	}


	public void setMapKey(String mapKey)
	{
		this.mapKey = mapKey;
	}

	@Column(length=50, nullable=false)
	public String getReplaceText()
	{
		return this.replaceText;
	}

	public void setReplaceText(String replaceText)
	{
		this.replaceText = replaceText;
	}

	public boolean isConfirm()
	{
		return this.confirm;
	}

	@Column
	public void setConfirm(boolean confirm)
	{
		this.confirm = confirm;
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
