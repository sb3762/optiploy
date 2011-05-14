package com.optiploy.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.optiploy.exception.UserExistsException;
import com.optiploy.model.User;

/**
 * @author "Jim Daniel"
 *
 */
public interface UserService
{
	Object findById(int id);
    void insert(Object object);
    void update(Object object);
    void remove(Object object);
    
    public void addUser(User user) throws UserExistsException;
	
	public void saveUser(User user);
	
	public User getUserByUsername(String username) throws UsernameNotFoundException;

}
