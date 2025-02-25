package com.ems.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import com.ems.util.DBConnection;

public class UserDao {
	public String authenticateUser(String username, String password) {
		try (Connection conn = DBConnection.getConnection()){
			String sqlAdmin = "SELECT * FROM admin WHERE username = ? AND password = ?";
			PreparedStatement pstmtAdmin = conn.prepareStatement(sqlAdmin);
			pstmtAdmin.setString(1, username);
			pstmtAdmin.setString(2, password);
			
			ResultSet rsAdmin = pstmtAdmin.executeQuery();
			
			if(rsAdmin.next()) {
				return "admin"; // Admin found
			}
			
			String sqlEmp = "SELECT * FROM admin WHERE username = ? AND password = ?";
			PreparedStatement pstmtEmp = conn.prepareStatement(sqlEmp);
			pstmtEmp.setString(1, username);
			pstmtEmp.setString(2, password);
			
			ResultSet rsEmp = pstmtEmp.executeQuery();
			
			if(rsEmp.next()) {
				return "employee"; // Employee Found
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	public int getEmployeeId(String username) {
		try (Connection conn = DBConnection.getConnection() ){
			String sql = "SELECT id FROM employees WHERE username = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("id");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return -1;
	}
	
	public String getEmployeeName(String username) {
		try (Connection conn = DBConnection.getConnection() ){
			String sql = "SELECT name FROM employees WHERE username = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getString("name");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
}
