package com.bmpl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.bmpl.connection.*;
import com.bmpl.dto.*;

public class LoginDAO {
	
	/*
	 * create method for validate
	 */
	public static boolean validate(LoginDTO loginDTO) throws ClassNotFoundException, SQLException {
		boolean status = false;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		final String query = "select username, password from login where username=? and password=?";
		try {
			connection = ConnectionDAO.getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, loginDTO.getUsername());
			pstmt.setString(2, loginDTO.getPassword());
			rs = pstmt.executeQuery();
			status = rs.next();
		}
		finally {
			if(rs != null) {
				rs.close();
			}
			if(pstmt != null) {
				pstmt.close();
			}
			if(connection != null) {
				connection.close();
			}
		}
		return status;
	}
}
