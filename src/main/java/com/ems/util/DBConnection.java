package com.ems.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/employee_management";
	private static final String USER = "root";
	private static final String PASSWORD = "Kartik@456";
	
	public static Connection getConnection() throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return DriverManager.getConnection(URL,USER,PASSWORD);
	}
}
