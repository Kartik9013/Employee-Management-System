package com.ems.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.ems.model.Admin;
import com.ems.util.DBConnection;

public class AdminDao {
	
	public Admin validateAdmin(String username, String password) {
		Admin admin = new Admin();
		try (Connection conn = DBConnection.getConnection()){
			String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				admin.setFullname(rs.getString("fullname"));
				admin.setEmail(rs.getString("email"));
				admin.setPassword(rs.getString("password"));
				admin.setUsername(rs.getString("username"));
			}else {
				admin = null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return admin;
	}
	
	public boolean addAdmin(Admin admin) {
		try (Connection conn = DBConnection.getConnection()){
			String sql = "INSERT INTO admin (username, fullname, email, password) VALUES (?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, admin.getUsername());
			pstmt.setString(2, admin.getFullname());
			pstmt.setString(3, admin.getEmail());
			pstmt.setString(4, admin.getPassword());
			
			int result = pstmt.executeUpdate();
			return result>0;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean upadteAdminPassword(String username, String oldPassword, String newPassword) {
		
		try (Connection conn = DBConnection.getConnection()){
			String sql = "UPDATE admin SET password=? WHERE username=? AND password=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newPassword);
			pstmt.setString(2, username);
			pstmt.setString(3, oldPassword);
			
			int rows = pstmt.executeUpdate();
			
			return rows>0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
}
