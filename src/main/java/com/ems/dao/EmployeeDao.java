package com.ems.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import com.ems.model.Employee;
import com.ems.util.DBConnection;


public class EmployeeDao {
	
	public boolean addEmployee(Employee employee) {
		try (Connection conn = DBConnection.getConnection()){
			String sql =  "INSERT INTO employees (name, fathersName, dob, salary, designation, address, phone, email, highestEducation, password) "
	                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, employee.getName());
			pstmt.setString(2, employee.getFathersName());
	        pstmt.setDate(3, java.sql.Date.valueOf(employee.getDob()));  // Convert LocalDate to SQL Date
	        pstmt.setDouble(4, employee.getSalary());
	        pstmt.setString(5, employee.getDesignation());
	        pstmt.setString(6, employee.getAddress());
	        pstmt.setString(7, employee.getPhone());
	        pstmt.setString(8, employee.getEmail());
	        pstmt.setString(9, employee.getHighestEducation());
	        pstmt.setString(10, employee.getPassword());
			
	        int result = pstmt.executeUpdate();
	        return result>0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		
	}
	
	public List<Employee> getAllEmployees(){
		List<Employee> list = new ArrayList<Employee>();
		
		try(Connection conn = DBConnection.getConnection()) {
			String sql = "SELECT * FROM employees";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Employee employee = new Employee();
				employee.setId(rs.getInt("id"));
				employee.setName(rs.getString("name"));
				employee.setFathersName(rs.getString("fathersname"));
				employee.setDob(rs.getDate("dob").toLocalDate());
				employee.setSalary(rs.getDouble("salary"));
				employee.setDesignation(rs.getString("designation"));
				employee.setAddress(rs.getString("address"));
				employee.setPhone(rs.getString("phone"));
				employee.setEmail(rs.getString("email"));
				employee.setHighestEducation(rs.getString("highestEducation"));
				list.add(employee);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	
	public Employee getEmployeeById(int id) {
		Employee employee = null;
		try(Connection conn = DBConnection.getConnection()) {
			String sql = "SELECT * FROM employees WHERE id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				employee = new Employee(
						rs.getInt("id"), 
						rs.getString("name"), 
						rs.getString("fathersname"), 
						rs.getDate("dob").toLocalDate(),
						rs.getDouble("salary"), 
						rs.getString("designation"), 
						rs.getString("address"), 
						rs.getString("phone"), 
						rs.getString("email"), 
						rs.getString("highestEducation"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return employee;
	}
	
	public boolean updateEmployee(Employee employee) {
		boolean updated = false;
		
		try (Connection conn = DBConnection.getConnection()){
			String sql = "UPDATE employees SET name=?, fathersname=?, dob=?,"
					+ " designation=?, address=?, phone=?, email=?, highestEducation=?"
					+ " WHERE id=?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, employee.getName());
	        pstmt.setString(2, employee.getFathersName());
	        pstmt.setDate(3, java.sql.Date.valueOf(employee.getDob()));
	        pstmt.setString(4, employee.getDesignation());
	        pstmt.setString(5, employee.getAddress());
	        pstmt.setString(6, employee.getPhone());
	        pstmt.setString(7, employee.getEmail());
	        pstmt.setString(8, employee.getHighestEducation());
	        pstmt.setInt(9, employee.getId());
	        
	        int rows = pstmt.executeUpdate();
	        
	        updated = rows > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return updated;
	}
	
	public boolean deleteEmployee(int id) {
		try(Connection conn = DBConnection.getConnection()) {
			String sql = "DELETE FROM employees WHERE id=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			int rows = pstmt.executeUpdate();
			
			return rows>0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	
	public Employee getEmployeeByEmail(String email,String password) {
	    try (Connection conn = DBConnection.getConnection()) {
	        String query = "SELECT * FROM employees WHERE email=? AND password = ?";
	        PreparedStatement pstmt = conn.prepareStatement(query);
	        pstmt.setString(1, email);
	        pstmt.setString(2, password);
	        ResultSet rs = pstmt.executeQuery();

	        if (rs.next()) {
	            return new Employee(
	                rs.getInt("id"),
	                rs.getString("name"),
	                rs.getString("fathersname"),
	                rs.getDate("dob").toLocalDate(),
	                rs.getDouble("salary"),
	                rs.getString("designation"),
	                rs.getString("address"),
	                rs.getString("phone"),
	                rs.getString("email"),
	                rs.getString("highestEducation"),
	                rs.getString("password")
	            );
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	public boolean validateEmployeePassword(int id,String currentPassword) {
		try (Connection conn = DBConnection.getConnection()){
			String sql = "SELECT * FROM employees WHERE id = ? AND password = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setString(2, currentPassword);
			
			ResultSet rs = pstmt.executeQuery();
			
			return rs.next();
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return false;
	}
	
	public boolean updateEmployeePassword(int id, String newPassword) {
		try (Connection conn = DBConnection.getConnection()){
			String sql = "UPDATE employees SET password = ? WHERE id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newPassword);
			pstmt.setInt(2, id);
			
			int rowsUpdated = pstmt.executeUpdate();
			
			return rowsUpdated>0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	
	
}
