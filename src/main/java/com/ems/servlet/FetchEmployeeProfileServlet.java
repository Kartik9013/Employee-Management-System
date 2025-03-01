package com.ems.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.ems.dao.EmployeeDao;
import com.ems.model.Employee;

/**
 * Servlet implementation class FetchEmployeeProfileServlet
 */
@WebServlet("/fetchEmployeeProfileServlet")
public class FetchEmployeeProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session == null || session.getAttribute("userType") == null || !session.getAttribute("userType").equals("employee")) {
			response.sendRedirect("index.jsp?error=unauthorized");
			return;
		}
		
		int employeeId = (int) session.getAttribute("employeeId");
		EmployeeDao employeeDao = new EmployeeDao();
		Employee employee = employeeDao.getEmployeeById(employeeId);
		
		if(employee != null) {
			session.setAttribute("employeeData", employee);
			response.sendRedirect("employeeProfile.jsp");
		}else {
			response.sendRedirect("user_dashboard.jsp?error=Profile not found!");
		}
	}

}
