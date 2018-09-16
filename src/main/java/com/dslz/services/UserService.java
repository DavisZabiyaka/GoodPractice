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

}
