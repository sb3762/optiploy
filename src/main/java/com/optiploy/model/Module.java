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
 * This class is used to represent available modules in the database.
 */
@Entity
@Table(name="MODULE")
@NamedQueries ({
    @NamedQuery(
        name = "findModuleByName",
        query = "select m from Module m where m.name = :name "
        )
})
public class Module
{
	private int id;
	private String name;
	private String description;
    
    /**
     * Default constructor - creates a new instance with no values set.
     */
    public Module()
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

}
