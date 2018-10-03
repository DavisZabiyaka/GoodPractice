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
					System.out.println("\n=====Closing Connection Object For Db Transaction=====\n");
					connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		mySQLDAOFactory.printDBStatus();
		return users;
	}

	@Override
	public User findUserById(Integer id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		User user = new User();;
		MySQLDAOFactory mySQLDAOFactory = new MySQLDAOFactory();
		try {
			DataSource dataSource = mySQLDAOFactory.setUpPool();
			mySQLDAOFactory.printDBStatus();
			
			System.out.println("\n=====Making A New Connection Object For Db Transaction=====\n");
            connection = dataSource.getConnection();
            mySQLDAOFactory.printDBStatus();
            
            preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE user_id = ?;");
            preparedStatement.setInt(1, id);
            
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.first()) {
            	return null;
            }
            user.setId(resultSet.getInt(1));
            user.setFullname(resultSet.getString(2));
            user.setEmail(resultSet.getString(3));
            user.setPassword(resultSet.getString(4));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					System.out.println("\n=====Closing Connection Object For Db Transaction=====\n");
					connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		mySQLDAOFactory.printDBStatus();
		return user;
	}

	@Override
	public User findUserByName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean createUser(User user) {
		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement1 = null;
		PreparedStatement preparedStatement2 = null;
		MySQLDAOFactory mySQLDAOFactory = new MySQLDAOFactory();
		try {
			int noOfCurrentUsers = 0;
			DataSource dataSource = mySQLDAOFactory.setUpPool();
			mySQLDAOFactory.printDBStatus();
			
			System.out.println("\n=====Making A New Connection Object For Db Transaction=====\n");
            connection = dataSource.getConnection();
            mySQLDAOFactory.printDBStatus();
            
            preparedStatement1 = connection.prepareStatement("SELECT COUNT(*) FROM users;");
            resultSet = preparedStatement1.executeQuery();
            
            while (resultSet.next()) {
            	noOfCurrentUsers = resultSet.getInt(1);
            }
            System.out.println("Number of current users: " + noOfCurrentUsers);
            
            preparedStatement2 = connection.prepareStatement("INSERT INTO users (user_id, fullname, email, password) VALUES (?,?,?,?)");
            preparedStatement2.setInt(1, noOfCurrentUsers + 1);
            preparedStatement2.setString(2, user.getFullname());
            preparedStatement2.setString(3, user.getEmail());
            preparedStatement2.setString(4, user.getPassword());
            preparedStatement2.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (preparedStatement1 != null)
					preparedStatement1.close();
				if (preparedStatement2 != null)
					preparedStatement2.close();
				if (connection != null) {
					System.out.println("\n=====Closing Connection Object For Db Transaction=====\n");
					connection.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		mySQLDAOFactory.printDBStatus();
		return true;
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
