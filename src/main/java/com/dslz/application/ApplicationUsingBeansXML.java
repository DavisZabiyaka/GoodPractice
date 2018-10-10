package com.dslz.application;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dslz.beans.User;
import com.dslz.factories.DAOFactory;
import com.dslz.factories.MySQLDAOFactory;

public class ApplicationUsingBeansXML {
	
	public static void main(String[] args) {
		
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("factory-beans.xml")) {
			DAOFactory daoFactory = context.getBean("mySQLDAOFactory", MySQLDAOFactory.class);
			daoFactory.getUserService().createUser(new User(100, "Frasure Mayer", "FrasureMy@fakeemail.com", "susunok112"));
			daoFactory.getUserService().findAllUsers();
		}
		
	}

}
