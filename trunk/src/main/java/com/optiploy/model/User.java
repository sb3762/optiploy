/**
 * 
 */
package com.optiploy.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
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
import org.springframework.security.core.userdetails.UserDetails;


/**
 * @author "Jim Daniel"
 *
 */

@Entity
@Table (name="USER")
@NamedQueries ({
    @NamedQuery(
        name = "findUserByName",
        query = "select u from User u where u.username = :username "
        )
})
public class User extends BaseObject implements Serializable, UserDetails
{
    private static final long serialVersionUID = -3429267142974525446L;
    
	private int id;
	private String username;
	private String password;
	private String confirmPassword;
	private String passwordHint;
	private String firstName;
    private String lastName;
    private String email;
    private Set<Role> roles = new HashSet<Role>();
    private boolean enabled;
    private boolean accountExpired;
    private boolean accountLocked;
    private boolean credentialsExpired;
	
	public User()
	{
		
	}	

	//@Id (generate = GeneratorType.AUTO)
    @Id  @GeneratedValue(strategy=GenerationType.AUTO)
	public int getId()
	{
		return this.id;
	}    

	public void setId(int id)
	{
		this.id = id;
	}
	
	@Column(nullable=false,length=50,unique=true)
	public String getUsername()
	{
		return this.username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	@Column (length=100)
	public String getPassword()
	{
		return this.password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}
	
	@Column(name="password_hint")
	public String getPasswordHint()
	{
		return passwordHint;
	}

	public void setPasswordHint(String passwordHint)
	{
		this.passwordHint = passwordHint;
	}

	@Column(name="first_name",nullable=false,length=50)
	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	@Column(name="last_name",nullable=false,length=50)
	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	@Column(nullable=false,unique=true)
	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}
	
	@ManyToMany(fetch = FetchType.EAGER) 
    @JoinTable(
            name="USER_ROLE",
            joinColumns = { @JoinColumn( name="user_id") },
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
        List<LabelValue> userRoles = new ArrayList<LabelValue>();

        if (this.roles != null) 
        {
            for (Role role : roles) 
            {
                // convert the user's roles to LabelValue Objects
                userRoles.add(new LabelValue(role.getName(), role.getName()));
            }
        }

        return userRoles;
    }  
    
    public void addRole(Role role) 
    {
        getRoles().add(role);
    }
    
    

	@Column(name="account_enabled",nullable=false)
	public boolean isEnabled()
	{
		return enabled;
	}

	public void setEnabled(boolean enabled)
	{
		this.enabled = enabled;
	}

	@Column(name="account_expired",nullable=false)
	public boolean isAccountExpired()
	{
		return accountExpired;
	}

	public void setAccountExpired(boolean accountExpired)
	{
		this.accountExpired = accountExpired;
	}
	
	@Column(name="account_locked",nullable=false)
	public boolean isAccountLocked()
	{
		return accountLocked;
	}

	public void setAccountLocked(boolean accountLocked)
	{
		this.accountLocked = accountLocked;
	}		

	 @Column(name="credentials_expired",nullable=false)
	 public boolean isCredentialsExpired() 
	 {
	     return credentialsExpired;
	 }
	
	 public void setCredentialsExpired(boolean credentialsExpired) 
	 {
		 this.credentialsExpired = credentialsExpired;
	 } 
	 
	 @Transient
	 public String getConfirmPassword()
	 {
		 return confirmPassword;
	 }
	 	 
	 public void setConfirmPassword(String confirmPassword)
	 {
		 this.confirmPassword = confirmPassword;
	 }
	
	 @Transient
     public String getFullName() 
	 {
         return firstName + ' ' + lastName;
     }
          
     @Transient
     public Set<GrantedAuthority> getAuthorities() 
     {
         Set<GrantedAuthority> authorities = new LinkedHashSet<GrantedAuthority>();
         authorities.addAll(roles);
         
         return authorities;
     }
	 
	 @Transient
	 public boolean isAccountNonExpired()
	 {
		 return !isAccountExpired();
	 }
	 
	 @Transient
	 public boolean isAccountNonLocked()
	 {
		 return !isAccountLocked();
	 }
	 
	 @Transient
     public boolean isCredentialsNonExpired() 
     {
         return !credentialsExpired;
     }
	 
	 @Override
	    public boolean equals(Object o)
	    {		
			if (this == o) 
			{
	            return true;
	        }
			
	        if (!(o instanceof User)) 
	        {
	            return false;
	        }

	        final User user1 = (User) o;

	        return this.hashCode() == user1.hashCode();
	    }

		@Override
	    public int hashCode()
	    {
			return new HashCodeBuilder().append(getId()).toHashCode();
	    }

}
