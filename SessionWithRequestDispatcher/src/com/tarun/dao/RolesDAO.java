package com.tarun.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.tarun.connection.ConnectionDAO;
import com.tarun.dto.RolesDTO;

public class RolesDAO {
	/*
	 * method for getRoles
	 */
	public ArrayList<RolesDTO> getRoles() throws SQLException, ClassNotFoundException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<RolesDTO> rolesList = new ArrayList<>();
		final String SQL = "select name, descr from roles";
		try {
			con = ConnectionDAO.getConnection();
			pstmt = con.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				RolesDTO rolesDTO = new RolesDTO();
				rolesDTO.setName(rs.getString(1));
				rolesDTO.setDescr(rs.getString(2));
				rolesList.add(rolesDTO);
			}
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
		return rolesList;
	}
}
