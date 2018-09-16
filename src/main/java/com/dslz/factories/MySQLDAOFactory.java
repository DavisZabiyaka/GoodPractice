package com.dslz.factories;


import javax.sql.DataSource;

import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.impl.GenericObjectPool;

import com.dslz.services.UserService;

public class MySQLDAOFactory extends DAOFactory {
	
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String DB_URL = "jdbc:mysql://localhost:3306/practiceusersdb";
	
	private static final String JDBC_USERNAME = System.getenv("JDBC_USERNAME");
	private static final String JDBC_PASSWORD = System.getenv("JDBC_PASSWORD");
	
	private static GenericObjectPool gPool = null;
	
	@Override
	public DataSource setUpPool() throws ClassNotFoundException {
		Class.forName(DRIVER);
		
		/**
		 * Creates an instance of GenericObjectPool that holds our pool of Connections Object
		 */
		gPool = new GenericObjectPool();
		gPool.setMaxActive(5);
		
		/**
		 * Creates a ConnectionFactory Object which will be used by the pool to create the Connection Object
		 */
		ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(DB_URL, JDBC_USERNAME, JDBC_PASSWORD);
		
		/**
		 * Creates a PoolableConnectionFactory that will wraps the Connection object created by the ConnectionFactory
		 * to add object pooling functionality 
		 */
		@SuppressWarnings("unused")
		PoolableConnectionFactory pcf = new PoolableConnectionFactory(connectionFactory, gPool, null, null, false, true);
		return new PoolingDataSource(gPool);
	}
	
	@Override
	public GenericObjectPool getConnectionPool() {
		return gPool;
	}
	
	/**
	 * Method used to print the connection pool status
	 */
	@Override
	public void printDBStatus() {
		System.out.println("Max.: " + getConnectionPool().getMaxActive() + "; Active: " + getConnectionPool().getNumActive()
							+ "; Idle: " + getConnectionPool().getNumIdle());
	}

	@Override
	public UserService getUserService() {
		// TODO Auto-generated method stub
		return new UserService();
	}

}
