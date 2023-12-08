package com.projects.communityhoa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projects.communityhoa.dao.UserDAO;
import com.projects.communityhoa.model.User;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAO userDAO;

	@Override
	public void save(User user) {
		this.userDAO.save(user);
	}

	@Override
	public void update(User user) {
		this.userDAO.update(user);
	}

	@Override
	public void delete(User user) {
		this.userDAO.delete(user);
	}

	@Override
	public User getUserByUsername(String username) {
		return this.userDAO.getUserByUsername(username);
	}

	@Override
	public List<User> getAllUsers() {
		return this.userDAO.getAllUsers();
	}

}
