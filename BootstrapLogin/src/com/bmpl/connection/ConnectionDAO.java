package com.bmpl.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;


public interface ConnectionDAO {
	
	/*
	 * create method to read property file
	 */
	public static ResourceBundle readPropertyFile() {
		ResourceBundle rb = ResourceBundle.getBundle("db");
		return rb;
	}
	
	/*
	 * create method for make database connection
	 */
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		ResourceBundle rb = readPropertyFile();
		Class.forName(rb.getString("drivername"));
		Connection con = DriverManager.getConnection(rb.getString("url"),rb.getString("userid"),"");
		return con;
	}
}
