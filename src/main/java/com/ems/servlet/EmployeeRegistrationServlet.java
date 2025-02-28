package com.ems.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

import com.ems.dao.EmployeeDao;
import com.ems.model.Employee;

/**
 * Servlet implementation class EmployeeRegistrationServlet
 */
@WebServlet("/EmployeeRegistrationServlet")
public class EmployeeRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
        String fathersName = request.getParameter("fathersName");
        LocalDate dob = LocalDate.parse(request.getParameter("dob"));
        Double salary = Double.parseDouble(request.getParameter("salary"));
        String designation = request.getParameter("designation");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String highestEducation = request.getParameter("highestEducation");
        String password = request.getParameter("password");
        
        Employee employee = new Employee(name, fathersName, dob, salary, designation, address, phone, email, highestEducation, password);
        
        EmployeeDao employeeDao = new EmployeeDao();
        boolean isRegistered = employeeDao.addEmployee(employee);
        
        if(isRegistered) {
        	response.sendRedirect("register.jsp?message=Registration Successful! You can now login.&messageType=success");
        }else {
        	response.sendRedirect("register.jsp?message=Registration Failed!&messageType=error");
		}
	}

}
