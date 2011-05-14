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
 * This class is used to represent available rules in the database.
 */
@Entity
@Table(name="RULE")
@NamedQueries ({
    @NamedQuery(
        name = "findRuleByName",
        query = "select r from Rule r where r.name = :name "
        )
})
public class Rule
{
	private int id;
	private int ruleTypeId;
	private String name;
	private String description;
    private String value;
    private String message;    
    private boolean global;
    
    /**
     * Default constructor - creates a new instance with no values set.
     */
    public Rule()
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

	@Column(length=30, nullable=false)
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
	public int getRuleType()
	{
		return ruleTypeId;
	}

	public void setRuleType(int ruleTypeId)
	{
		this.ruleTypeId = ruleTypeId;
	}

	@Column(length=500, nullable=false)
	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

	@Column(length=500, nullable=false)
	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	@Column(nullable=false)
	public boolean isGlobal()
	{
		return global;
	}

	public void setGlobal(boolean global)
	{
		this.global = global;
	}
    
    

}
