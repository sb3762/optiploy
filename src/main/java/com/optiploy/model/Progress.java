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
 * This class is used to represent available applications in the database.
 */
@Entity
@Table(name="PROGRESS")
@NamedQueries ({
    @NamedQuery(
        name = "findProgressByName",
        query = "select p from Progress p where p.name = :name "
        )
})
public class Progress
{
	private int id;
	private String name;
	private String description;
	private String parameter;
    
    /**
     * Default constructor - creates a new instance with no values set.
     */
    public Progress()
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

	@Column(length=100, nullable=false)
	public String getParameter()
	{
		return parameter;
	}

	public void setParameter(String parameter)
	{
		this.parameter = parameter;
	}
    
}
