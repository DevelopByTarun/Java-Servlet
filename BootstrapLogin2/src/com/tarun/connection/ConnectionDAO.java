package com.tarun.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public interface ConnectionDAO {
	/*
	 * create method for read property file
	 */
	public static ResourceBundle readPropertyFile() {
		ResourceBundle rb = ResourceBundle.getBundle("db");
		return rb;
	}
	/*
	 * create connection method
	 */
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		ResourceBundle rb = readPropertyFile();
		Class.forName(rb.getString("drivername"));
		Connection con = DriverManager.getConnection(rb.getString("url"), rb.getString("userid"), "");
		return con;
	}
}
