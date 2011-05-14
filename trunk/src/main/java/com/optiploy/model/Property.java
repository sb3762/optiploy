package com.optiploy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * This class is used to represent available agents in the database.
 */
@Entity
@Table(name="PROPERTY")
@NamedQueries ({
    @NamedQuery(
        name = "findPropertyByName",
        query = "select p from Property p where p.name = :name "
        )  
})
public class Property 
{
	private int id;
	private String name;
	private String value;
	private String description;
	    
    /**
     * Default constructor - creates a new instance with no values set.
     */
    public Property()
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
	
	@Column(length=250, nullable=false)
	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
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

	public boolean equals(Object o) 
    {
        if (this == o) 
        {
            return true;
        }
        
        if (!(o instanceof Property)) 
        {
            return false;
        }

        final Property property = (Property) o;

        return !(name != null ? !name.equals(property.name) : property.name != null);

    }

    public int hashCode() 
    {
        return (name != null ? name.hashCode() : 0);
    }

    public String toString() 
    {
    	StringBuffer buff = new StringBuffer();
    	
    	buff.append(this.name);
    	
        return buff.toString();    	
    }

    public int compareTo(Object o) 
    {
        return (equals(o) ? 0 : -1);
    }
   
}
