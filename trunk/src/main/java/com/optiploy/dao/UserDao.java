package com.optiploy.dao;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author "Jim Daniel"
 *
 */
public interface UserDao
{
	Object findById(int id);	
    void insert(Object object);
    void saveOrUpdate(Object object);
    Object merge(Object object);
    void update(Object object);
    void remove(Object object);
    
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    String getUserPassword(String username);
    
    @Transactional
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
    
    @Transactional
    List<String> allUsernames();
}
