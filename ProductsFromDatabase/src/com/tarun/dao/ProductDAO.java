package com.tarun.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.tarun.connection.*;
import com.tarun.dto.*;

public class ProductDAO {
	/*
	 * get products by criteria
	 */
	public ArrayList<ProductDTO> getProductsByCriteria(String status, String search) throws ClassNotFoundException, SQLException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ProductDTO> productList = new ArrayList<>();
		final String SQL = "select id, name, descr, image, price from products where status=? and price >= ?";
		try {
			connection = ConnectionDAO.getConnection();
			pstmt = connection.prepareStatement(SQL);
			pstmt.setString(1, status);
			pstmt.setDouble(2, Double.parseDouble(search));
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ProductDTO productDTO = new ProductDTO();
				productDTO.setId(rs.getInt(1));
				productDTO.setName(rs.getString(2));
				productDTO.setDesc(rs.getString(3));
				productDTO.setImage(rs.getString(4));
				productDTO.setPrice(rs.getDouble(5));
				productList.add(productDTO);
			}
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
		return productList;
	}
	/*
	 * get products
	 */
	public ArrayList<ProductDTO> getProducts(String status) throws ClassNotFoundException, SQLException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ProductDTO> productList = new ArrayList<>();
		final String SQL = "select id, name, descr, image, price from products where status = ?";
		try {
			connection = ConnectionDAO.getConnection();
			pstmt = connection.prepareStatement(SQL);
			pstmt.setString(1, status);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ProductDTO productDTO = new ProductDTO();
				productDTO.setId(rs.getInt(1));
				productDTO.setName(rs.getString(2));
				productDTO.setDesc(rs.getString(3));
				productDTO.setImage(rs.getString(4));
				productDTO.setPrice(rs.getDouble(5));
				productList.add(productDTO);
			}
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
		return productList;
	}
}
