package com.dslz.daos;

import java.util.List;

import com.dslz.beans.User;

public interface UserDAO {
	
	List<User> findAllUsers();
	User findUserById(Integer id);
	User findUserByName();
	boolean createUser(User user);
	boolean updateUser(User user);
	boolean deleteUser(User user);
	
	
}
