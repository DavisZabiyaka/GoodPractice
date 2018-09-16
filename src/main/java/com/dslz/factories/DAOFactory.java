package com.dslz.factories;

import javax.sql.DataSource;

import org.apache.commons.pool.impl.GenericObjectPool;

import com.dslz.services.UserService;

public abstract class DAOFactory {
	
	public static final int CLOUDSCAPE = 0;
	public static final int SYBASE = 1;
	public static final int ORACLE = 2;
	public static final int MYSQL = 3;
	
	public abstract UserService getUserService();
	
	public abstract DataSource setUpPool() throws ClassNotFoundException;
	public abstract GenericObjectPool getConnectionPool();
	public abstract void printDBStatus();
	
	public static DAOFactory getDAOFactory(int whichFactory) {
		switch (whichFactory) {
			case CLOUDSCAPE:
				return null;
			case SYBASE:
				return null;
			case ORACLE:
				return null;
			case MYSQL:
				return new MySQLDAOFactory();
			default:
				return null;
		}
	}

}
