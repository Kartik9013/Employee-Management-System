package com.ems.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import com.ems.dao.EmployeeDao;
import com.ems.model.Employee;

/**
 * Servlet implementation class ViewEmployeeServlet
 */
@WebServlet("/ViewEmployeeServlet")
public class ViewEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("userType") == null) {
		    response.sendRedirect("index.jsp?error=unauthorized");
		    return;
		}
		
		EmployeeDao employeeDao = new EmployeeDao();
		List<Employee> employees = employeeDao.getAllEmployees();
		
		request.setAttribute("employees", employees);
		request.getRequestDispatcher("viewEmployee.jsp").forward(request, response);
		
	}

}
