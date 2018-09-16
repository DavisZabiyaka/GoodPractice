package com.dslz.application;

import com.dslz.factories.DAOFactory;
import com.dslz.services.UserService;

public class Application {
	
	public static void main(String[] args) {
		DAOFactory daoFactory = DAOFactory.getDAOFactory(3);
		UserService userService = daoFactory.getUserService();
		userService.findAllUsers();
	}

}
