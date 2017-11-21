package com.tarun.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tarun.connection.ConnectionDAO;
import com.tarun.dto.LoginDTO;

public class LoginDAO {
	/*
	 * method for login
	 */
	public boolean validate(LoginDTO loginDTO) throws ClassNotFoundException, SQLException {
		boolean status = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		final String SQL = "select userid, password from login where userid=? and password=?";
		try {
			con = ConnectionDAO.getConnection();
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, loginDTO.getUserid());
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
			if(con != null) {
				con.close();
			}
		}
		return status;
	}
}
