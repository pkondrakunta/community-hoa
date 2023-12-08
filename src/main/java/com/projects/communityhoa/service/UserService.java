/**
 *
 * @author Pragnya Kondrakunta
 */

package com.projects.communityhoa.service;


import java.util.List;

import org.springframework.stereotype.Component;

import com.projects.communityhoa.model.Member;
import com.projects.communityhoa.model.User;

@Component
public interface UserService {
	
    public void save(User user);
    public void update(User user);
    public void delete(User user);
    public User getUserByUsername(String username);
    public List<User> getAllUsers();

}
