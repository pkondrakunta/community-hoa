package com.projects.communityhoa.service;

import java.time.LocalDateTime;
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User getUserByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAllUsers() {
        return this.userDAO.getAllUsers();
	}

}
