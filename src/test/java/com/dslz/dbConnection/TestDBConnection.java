package com.dslz.dbConnection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.dslz.beans.User;
import com.dslz.daos.MySQLUserDAO;
import com.dslz.factories.MySQLDAOFactory;
import com.dslz.services.UserService;

@RunWith(MockitoJUnitRunner.class)
public class TestDBConnection {
	
	@Mock
	private MySQLDAOFactory mySQLDAOFactoryMock;
	
	@Mock
	private DataSource dataSourceMock;
	
	@Mock
	private Connection connectionMock;
	
	@Mock
	private UserService userServiceMock;
	
	@Mock
	private PreparedStatement preparedStatementMock;
	
	@Mock
	private ResultSet resultSetMock;
	
	@Before
	public void setUp() throws Exception {
		when(mySQLDAOFactoryMock.setUpPool()).thenReturn(dataSourceMock);
		when(dataSourceMock.getConnection()).thenReturn(connectionMock);
		when(mySQLDAOFactoryMock.getUserService()).thenReturn(userServiceMock);
	}
	
	@Test
	public void checkNotNullTest() {
		assertNotNull(dataSourceMock);
		assertNotNull(connectionMock);
		assertNotNull(preparedStatementMock);
		assertNotNull(userServiceMock);
		assertNotNull(resultSetMock);
	}
	
	@Test
	public void createAndRetrieveUserTest() throws ClassNotFoundException, SQLException {
		
		User user = new User(1, "Ryan Choma", "ChomaR@fakeEmail.com", "panama123home");
		
		when(userServiceMock.createUser(user)).thenReturn(true);
		assertTrue(userServiceMock.createUser(user));
		
		MySQLUserDAO mySQLUserDAO = new MySQLUserDAO();
		mySQLUserDAO.createUser(user);
		
		User user2 = mySQLUserDAO.findUserById(1);
		assertEquals(user, user2);
	}
	
	@Ignore
	@Test
	public void createAndRetrieveUserRollBackTest() {
		
		User user = new User(1, "Ryan Choma", "ChomaR@fakeEmail.com", "panama123home");
		
		when(userServiceMock.createUser(user)).thenReturn(true);
		assertTrue(userServiceMock.createUser(user));
		
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
            connection.setAutoCommit(false);
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
            
            User user2 = new MySQLUserDAO().findUserById(1);
            assertEquals(user, user2);
            //TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            
		} catch (Exception e) {
			e.printStackTrace();
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
	}
}
