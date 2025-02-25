package com.ems.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;

import com.ems.dao.EmployeeDao;
import com.ems.model.Employee;

/**
 * Servlet implementation class AddEmployeeServlet
 */
@WebServlet("/AddEmployeeServlet")
public class AddEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		if(session == null||session.getAttribute("admin") == null){
			response.sendRedirect("index.jsp");
			return;
		}
		
		String name = request.getParameter("name");
	     String fathersName = request.getParameter("fathersName");
	     LocalDate dob = LocalDate.parse(request.getParameter("dob"));
	     Double salary = Double.parseDouble(request.getParameter("salary"));
	     String designation = request.getParameter("designation");
	     String address = request.getParameter("address");
	     String phone = request.getParameter("phone");
	     String email = request.getParameter("email");
	     String highestEducation = request.getParameter("highestEducation");
	     
	     Employee employee = new Employee(name, fathersName, dob, salary, designation, 
	    		 address, phone, email, highestEducation);
	     
	     EmployeeDao employeeDao = new EmployeeDao();
	     boolean isAdded = employeeDao.addEmployee(employee);
	     
	     String redirectUrl;
	     if(isAdded) {
	    	 redirectUrl = "addEmployee.jsp?message=Employee Added Successfully!&messageType=success";
	     }else {
			redirectUrl = "addEmployee.jsp?message=Failed to add employee. Please try again.&messageType=error";
		}
	     response.sendRedirect(redirectUrl);
	}

}
