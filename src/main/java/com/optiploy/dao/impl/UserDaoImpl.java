/**
 * 
 */
package com.optiploy.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Table;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.optiploy.dao.UserDao;
import com.optiploy.model.User;

/**
 * @author "Jim Daniel"
 *
 */
public class UserDaoImpl extends HibernateDaoSupport implements UserDao, UserDetailsService
{
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
    {    	
        List users = getHibernateTemplate().find("from User where username=?", username);
        
        if (users == null || users.isEmpty()) 
        {
            throw new UsernameNotFoundException("user '" + username + "' not found...");
        } 
        else 
        {
            return (UserDetails) users.get(0);
        }
    }

      public String getUserPassword(String username) 
    {
        SimpleJdbcTemplate jdbcTemplate =
                new SimpleJdbcTemplate(SessionFactoryUtils.getDataSource(getSessionFactory()));
        
        Table table = AnnotationUtils.findAnnotation(User.class, Table.class);
        
        return jdbcTemplate.queryForObject(
                "select password from " + table.name() + " where username=?", String.class, username);

    }

	public Object findById(int id)
	{	
		List list=getHibernateTemplate().find("from User where id=?",id);
		return (User) list.get(0);
	}
	
	public void insert(Object object)
	{
		getHibernateTemplate().save((User)object);		
	}
	
	public void saveOrUpdate(Object object)
	{
		getHibernateTemplate().saveOrUpdate((User)object);		
	}
	
	public Object merge(Object object)
	{
		return getHibernateTemplate().merge((User)object);		
	}

	public void remove(Object object)
	{
		getHibernateTemplate().delete((User)object);		
	}

	public void update(Object object)
	{
		getHibernateTemplate().update((User)object);		
	}

	public List<String> allUsernames()
    {
		List<User> users = getHibernateTemplate().find("from User");
		
		List usernames = new ArrayList();
		
		for (int i=0; i< users.size(); i++)
		{			
			usernames.add(users.get(i).getUsername());
	    }
	    
	    return usernames;
    }	

}
