package com.optiploy.model;

import java.util.HashSet;
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

/**
 * This class is used to represent available scripts in the database.
 */
@Entity
@Table(name="SCRIPT")
@NamedQueries ({
    @NamedQuery(
        name = "findScriptByName",
        query = "select s from Script s where s.name = :name "
        )
})
public class Script
{
	private int id;
	private int version;
	private int environmentId;
	private int applicationId;
    private int functionId;
	private String name;
	private String description;
	private String fileName;
	private String content;
	private Set<Attribute> attributes = new HashSet<Attribute>();
	    
    /**
     * Default constructor - creates a new instance with no values set.
     */
    public Script()
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
	public int getEnvironmentId()
	{
		return environmentId;
	}

	public void setEnvironmentId(int environmentId)
	{
		this.environmentId = environmentId;
	}

	@Column
	public int getApplicationId()
	{
		return applicationId;
	}

	public void setApplicationId(int applicationId)
	{
		this.applicationId = applicationId;
	}

	@Column
	public int getFunctionId()
	{
		return functionId;
	}

	public void setFunctionId(int functionId)
	{
		this.functionId = functionId;
	}

	@Column(length=1000, nullable=false)
	public String getFileName()
	{
		return fileName;
	}

	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	@Column(length=5000, nullable=false)
	public String getContent()
	{
		return content;
	}
	
	@ManyToMany(fetch = FetchType.EAGER) 
    @JoinTable(
            name="SCRIPT_ATTRIBUTE",
            joinColumns = { @JoinColumn( name="script_id") },
            inverseJoinColumns = @JoinColumn( name="attribute_id")
    )    
    public Set<Attribute> getAttributes() 
    {
        return attributes;
    }
	
	public void setAttributes(Set<Attribute> attributes) 
	{
	    this.attributes = attributes;
	}

	public void setVersion(int version)
	{
		this.version = version;
	}

	@Column
	public int getVersion()
	{
		return version;
	}	
}
