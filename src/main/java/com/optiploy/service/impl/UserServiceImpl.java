/**
 * 
 */
package com.optiploy.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.optiploy.dao.UserDao;
import com.optiploy.exception.UserExistsException;
import com.optiploy.model.User;
import com.optiploy.service.UserService;

/**
 * @author "Jim Daniel"
 *
 */
public class UserServiceImpl implements UserService
{		
	private UserDao userDao;
	private PasswordEncoder passwordEncoder;	

	private static Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	public void saveUser(User user)
	{      	
     
		try
		{
        	logger.info("Updating user: " + user.getUsername());
        	
        	if(!userDao.getUserPassword(user.getUsername()).equals(user.getPassword()))
        	{	
        		user.setPassword(passwordEncoder.encodePassword(user.getPassword(), null));
        	}	
        	
        	update(user);
        	
        }  
        catch (Exception e) 
        {
           
        }
	}
	
	public void addUser(User user) throws UserExistsException
	{      	
     
		try
		{
        	logger.info("Adding user: " + user.getUsername());        	
        		
        	user.setPassword(passwordEncoder.encodePassword(user.getPassword(), null));        
        	
        	insert(user);
        	
        }  
        catch (DataIntegrityViolationException e) 
        {        	
            throw new UserExistsException("User '" + user.getUsername() + "' already exists!");
        }        
	}
	
	public User getUserByUsername(String username) throws UsernameNotFoundException 
	{
        return (User) userDao.loadUserByUsername(username);
    }

    public List<String> getUsernames()
    {
	    return userDao.allUsernames();
    }

	
	public Object findById(int id)
	{
		return userDao.findById(id);
	}
	
	public void insert(Object object)
	{
		userDao.insert((User)object);		
	}
	
	public void saveOrUpdate(Object object)
	{
		userDao.saveOrUpdate((User)object);		
	}
	
	public Object merge(Object object)
	{
		return userDao.merge((User)object);		
	}
	
	public void remove(Object object)
	{
		userDao.remove((User)object);		
	}


	public void update(Object object)
	{
		userDao.update((User)object);		
	}
	
	public void setUserDao(UserDao userDao) 
	{
        this.userDao = userDao;
    }
	
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) 
	{
        this.passwordEncoder = passwordEncoder;
    }

}
