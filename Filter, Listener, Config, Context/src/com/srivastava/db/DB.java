package com.srivastava.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.srivastava.dto.MasterDTO;
import com.srivastava.dto.ProductDTO;

public class DB {
	private Connection getConnection() throws ClassNotFoundException, SQLException, NamingException{
		//ResourceBundle rb = ResourceBundle.getBundle("db");
		//Class.forName(rb.getString("drivername"));
		//Connection con = DriverManager.getConnection(rb.getString("url"),rb.getString("userid"),"");
		Context initContext  = new InitialContext();
		Context envContext  = (Context)initContext.lookup("java:/comp/env");
		DataSource dataSource = (DataSource)envContext.lookup("jdbc/onlineshop");
		Connection con = dataSource.getConnection();
		return con;
	}
	
	
	public ArrayList<MasterDTO> getRoles() throws ClassNotFoundException, SQLException, NamingException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MasterDTO> masterList = new ArrayList<>();
		final String SQL = "select name,descr from common_masters where status='Y'";
		try{
			con = getConnection();
			pstmt = con.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			while(rs.next()){
				MasterDTO masterDTO =new MasterDTO(rs.getString("name"),rs.getString("descr"));
				masterList.add(masterDTO);
			}
		}
		finally{
			if(rs!=null){
				rs.close();
			}
			if(pstmt!=null){
				pstmt.close();
			}
			if(con!=null){
				con.close();
			}
		}
		return masterList;
	}
	
	public ArrayList<ProductDTO> getProductsByCriteria(String status, String search) throws SQLException, ClassNotFoundException, NamingException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ProductDTO> productList = new ArrayList<>();
		final String SQL = "select id, name, descr, price, status, image from product where status=? and price>=?";
		try{
			con = getConnection();
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, status);
			pstmt.setDouble(2, Double.parseDouble(search));
			rs = pstmt.executeQuery();
			while(rs.next()){
				ProductDTO product = new ProductDTO();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setDescr(rs.getString("descr"));
				product.setPrice(rs.getDouble("price"));
				product.setStatus(rs.getString("status"));
				product.setImage(rs.getString("image"));
				productList.add(product);
			}
			
		}
		finally{
			if(rs!=null){
				rs.close();
			}
			if(pstmt!=null){
				pstmt.close();
			}
			if(con!=null){
				con.close();
			}
		}
		return productList;
	}
	
	public ArrayList<ProductDTO> getProducts(String status) throws ClassNotFoundException, SQLException, NamingException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ProductDTO> productList = new ArrayList<>();
		final String SQL = "select id, name, descr, price, status, image from product where status=?";
		try{
			con = getConnection();
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, status);
			rs = pstmt.executeQuery();
			while(rs.next()){
				ProductDTO product = new ProductDTO();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setDescr(rs.getString("descr"));
				product.setPrice(rs.getDouble("price"));
				product.setStatus(rs.getString("status"));
				product.setImage(rs.getString("image"));
				productList.add(product);
			}
			
		}
		finally{
			if(rs!=null){
				rs.close();
			}
			if(pstmt!=null){
				pstmt.close();
			}
			if(con!=null){
				con.close();
			}
		}
		return productList;
	}
}
