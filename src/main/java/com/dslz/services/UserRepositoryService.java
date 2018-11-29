package com.dslz.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dslz.beans.User;
import com.dslz.repositories.UserRepository;

@Service
public class UserRepositoryService {
	
	private UserRepository userRepository;
	
	public List<User> findAllUsers() {
		System.out.println(userRepository.findAll());
		return (List<User>) userRepository.findAll();
	}

}
