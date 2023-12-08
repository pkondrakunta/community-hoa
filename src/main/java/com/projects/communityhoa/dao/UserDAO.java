package com.projects.communityhoa.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.projects.communityhoa.model.User;

@Component
public interface UserDAO {
	public void save(User u);

	public void update(User u);

	public void delete(User u);

	public User getUserByUsername(String username);

	public List<User> getAllUsers();
	
}
