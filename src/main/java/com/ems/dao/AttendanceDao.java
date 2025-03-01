package com.ems.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ems.model.Attendance;


public class AttendanceDao {
	private Connection conn;
	
	public AttendanceDao(Connection conn) {
		this.conn = conn;
	}


	// Check if the Attendance is marked or not
	public boolean isAttendanceMarked(int employeeId,LocalDate date) {
		try {
			String sql = "SELECT id FROM attendance WHERE employee_id=? AND date=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, employeeId);
			pstmt.setDate(2, java.sql.Date.valueOf(date));
			
			ResultSet rs = pstmt.executeQuery();
			
			return rs.next();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	
	// Mark Attendance
	public boolean markAttendance(int employeeId, LocalDate date, String status) {
		try {
			String sql = "INSERT INTO attendance (employee_id, date, status) VALUES (?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, employeeId);
			pstmt.setDate(2, java.sql.Date.valueOf(date));
			pstmt.setString(3, status);
			
			int rowsInserted = pstmt.executeUpdate();
			
			return rowsInserted>0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	
	// Get attendance list for a specific employee
    public List<Attendance> getAttendanceByEmployeeId(int employeeId) {
        List<Attendance> attendanceList = new ArrayList<>();
        try {
        	String sql = "SELECT a.id, a.employee_id, e.name, a.date, a.status " +
                    "FROM attendance a " +
                    "JOIN employees e ON a.employee_id = e.id " +
                    "WHERE a.employee_id = ? " +
                    "ORDER BY a.date DESC";
        	PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, employeeId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Attendance attendance = new Attendance(
                    rs.getInt("id"),
                    rs.getInt("employee_id"),
                    rs.getString("name"), // Employee Name
                    rs.getDate("date").toLocalDate(),
                    rs.getString("status")
                );
                attendanceList.add(attendance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return attendanceList;
    }
    
 // Get all attendance records (For Admin View)
    public List<Attendance> getAllAttendanceRecords() {
        List<Attendance> attendanceList = new ArrayList<>();
        try {
        	String sql = "SELECT a.id, a.employee_id, e.name, a.date, a.status " +
                    "FROM attendance a " +
                    "JOIN employees e ON a.employee_id = e.id " +
                    "ORDER BY a.date DESC";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Attendance attendance = new Attendance(
                		rs.getInt("id"),
                        rs.getInt("employee_id"),
                        rs.getString("name"), // Employee Name
                        rs.getDate("date").toLocalDate(),
                        rs.getString("status")
                );
                attendanceList.add(attendance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return attendanceList;
    }
    
    public Attendance getAttendanceById(int attendanceId) {
    	Attendance attendance = null;
    	try {
    		String sql = "SELECT a.id, a.employee_id, e.name, a.date, a.status " +
                    "FROM attendance a " +
                    "JOIN employees e ON a.employee_id = e.id " +
                    "WHERE a.id = ?";
    		PreparedStatement pstmt = conn.prepareStatement(sql);
    		pstmt.setInt(1, attendanceId);
    		
    		ResultSet rs = pstmt.executeQuery();
    		
    		if(rs.next()) {
    			attendance = new Attendance(
    	                rs.getInt("id"),
    	                rs.getInt("employee_id"),
    	                rs.getString("name"), // Employee Name
    	                rs.getDate("date").toLocalDate(),
    	                rs.getString("status")
    	            );
    		}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	return attendance;
    }
    
    public boolean updateAttendanceStatus(int attendanceId,String status) {
    	try {
            String sql = "UPDATE attendance SET status = ? WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, status);
            pstmt.setInt(2, attendanceId);

            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
    	return false;
    }
}
