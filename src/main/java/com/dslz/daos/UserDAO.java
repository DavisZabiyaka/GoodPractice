package com.dslz.daos;

import java.util.List;

import com.dslz.beans.User;

public interface UserDAO {
	
	List<User> findAllUsers();
	User findUserById();
	User findUserByName();
	boolean insertUser(User user);
	boolean updateUser(User user);
	boolean deleteUser(User user);
	
}
