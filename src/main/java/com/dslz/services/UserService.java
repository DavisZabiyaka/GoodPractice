package com.dslz.services;

import java.util.List;

import com.dslz.beans.User;
import com.dslz.daos.MySQLUserDAO;
import com.dslz.daos.UserDAO;

public class UserService {
	
	private UserDAO myUserDAO;
	
	public UserService() {
		this(new MySQLUserDAO());
	}
	
	public UserService(UserDAO myUserDAO) {
		this.myUserDAO = myUserDAO;
	}
	
	public List<User> findAllUsers() {
		return myUserDAO.findAllUsers();
	}
	
	public boolean createUser(User user) {
		return myUserDAO.createUser(user);
	}
	
	public User findUserById(Integer id) {
		return myUserDAO.findUserById(id);
	}

}
