package com.dslz.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import com.dslz.beans.User;
import com.dslz.factories.DAOFactory;
import com.dslz.services.UserService;

public class Application {
	
	public static Logger myLogger = LoggerFactory.getLogger(Application.class);
	
	public static void main(String[] args) {
		DAOFactory daoFactory = DAOFactory.getDAOFactory(3);
		UserService userService = daoFactory.getUserService();
		//userService.createUser(new User(1, "Bobert Grasure", "GrasureB@fakeemail.com", "Milkyway"));
		userService.findAllUsers();
	}

}
