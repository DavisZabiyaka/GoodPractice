package com.dslz.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.dslz.beans.User;
import com.dslz.factories.MySQLDAOFactory;

public class MySQLUserDAO implements UserDAO {

	@Override
	public List<User> findAllUsers() {
		ResultSet resultSet = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		MySQLDAOFactory mySQLDAOFactory = new MySQLDAOFactory();
		List<User> users = new ArrayList<>();
		try {
			DataSource dataSource = mySQLDAOFactory.setUpPool();
			mySQLDAOFactory.printDBStatus();
			
			/**
			 * Perform Database Operation
			 */
            System.out.println("\n=====Making A New Connection Object For Db Transaction=====\n");
            connection = dataSource.getConnection();
            mySQLDAOFactory.printDBStatus();
            
            preparedStatement = connection.prepareStatement("SELECT * FROM users;");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
            	users.add(
            			new User(
            					resultSet.getInt("user_id"),
            					resultSet.getString("fullname"),
            					resultSet.getString("email"),
            					resultSet.getString("password")
            					)
            			);
            }
            for (User user : users) {
            	System.out.println(user);
            }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		mySQLDAOFactory.printDBStatus();
		return users;
	}

	@Override
	public User findUserById() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findUserByName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

}
