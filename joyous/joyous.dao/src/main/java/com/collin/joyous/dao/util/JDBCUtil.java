package com.collin.joyous.dao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLRecoverableException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;

public class JDBCUtil {

	private static org.slf4j.Logger logger = LoggerFactory.getLogger(JDBCUtil.class);
	private static String DRIVER_NAME = null;
	private static String URL = null;
	private static String USER = null;
	private static String PASSWORD = null;
	
	public static void init(String driverName,String url, String userName, String password) throws RuntimeException {
		if(StringUtils.isBlank(driverName) 
				||StringUtils.isBlank(url)
				||StringUtils.isBlank(userName)
				||StringUtils.isBlank(password)){
			throw new RuntimeException("driver Name, url, userName, password error.");
		}
		DRIVER_NAME = driverName;
		URL = url;
		USER = userName;
		PASSWORD = password;
	}
	
	public static Connection createConnection() throws RuntimeException{
		
		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Driver name error.");
		}
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLRecoverableException e) {
			throw new RuntimeException("db connection error.",e);
		} catch (SQLException e) {
			throw new RuntimeException("Url,user name, password error.",e);
		}
		if(conn == null){
			throw new RuntimeException("Url,user name, password error.");
		}
		return conn;
	}
	
	public static void closeConnection(Connection conn){
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("close connection error");
			}
			conn = null;
		}
	}

	public static PreparedStatement createPreparedStatement(Connection conn, String sql) throws RuntimeException{
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(sql);
		} catch (SQLException e) {
			throw new RuntimeException("create preparedStatement error.");
		}
		return pst;
	}

	
}
