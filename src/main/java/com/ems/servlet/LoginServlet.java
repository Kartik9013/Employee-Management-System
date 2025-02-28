package com.ems.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.ems.dao.EmployeeDao;
import com.ems.dao.UserDao;
import com.ems.model.Employee;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usernameOrEmail = request.getParameter("username");
		String password = request.getParameter("password");
		
		UserDao userDao = new UserDao();
		String userType = userDao.authenticateUser(usernameOrEmail, password);
		EmployeeDao employeeDao = new EmployeeDao();
		
		if(userType!=null) {
			HttpSession session = request.getSession();
			session.setAttribute("userType", userType);
			session.setAttribute("username", usernameOrEmail);
			
			if(userType.equals("admin")) {
				response.sendRedirect("admin_dashboard.jsp");
			}else if (userType.equals("employee")) {
				
				Employee employee = employeeDao.getEmployeeByEmail(usernameOrEmail,password);
				if(employee!=null) {
					session.setAttribute("employeeId", employee.getId());
					session.setAttribute("username", employee.getName());
					response.sendRedirect("user_dashboard.jsp");
				}else {
					response.sendRedirect("index.jsp?error=invalid");
				}
			}
			return;
		}
		response.sendRedirect("index.jsp?error=invalid");
	}

}