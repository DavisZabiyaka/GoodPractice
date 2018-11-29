package com.dslz.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dslz.beans.User;
import com.dslz.daos.MySQLUserDAO;
import com.dslz.repositories.UserRepository;
import com.dslz.services.UserRepositoryService;

public class CrudRepositoryApplication {
	
	@Autowired
	private static UserRepositoryService userRepositoryService;
	
	public static void setUserRepositoryService(UserRepositoryService userRepositoryService) {
		CrudRepositoryApplication.userRepositoryService = userRepositoryService;
	}
	
	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("hibernate5cfg.xml");
		System.out.println(userRepositoryService);
	}
	
	/*private static UserRepository userRepository;
	
	@Autowired
	public static void setUserRepository(UserRepository userRepository) {
		CrudRepositoryApplication.userRepository = userRepository;
	}*/
	
	/*public List<User> findAll() {
		List<User> users = new ArrayList<>();
		userRepository.findAll().forEach(e -> users.add(e));
		return users;
	}*/

}
