package com.ems.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


import com.ems.dao.UserDao;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		UserDao userDao = new UserDao();
		String userType = userDao.authenticateUser(username, password);
		
		if(userType!=null) {
			HttpSession session = request.getSession();
			session.setAttribute("userType", userType);
			session.setAttribute("username", username);
			
			if(userType.equals("admin")) {
				response.sendRedirect("admin_dashboard.jsp");
			}else if (userType.equals("employee")) {
				session.setAttribute("employeeId", userDao.getEmployeeId(username));
				session.setAttribute("username", userDao.getEmployeeName(username));
				response.sendRedirect("user_dashboard.jsp");
			}
			return;
		}
		response.sendRedirect("index.jsp?error=invalid");
	}

}
