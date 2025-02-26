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
 * Servlet implementation class UpdateProfileServlet
 */
@WebServlet("/UpdateProfileServlet")
public class UpdateProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userType = (String)session.getAttribute("userType");
		String username = request.getParameter("username");
		String newPassword = request.getParameter("newPassword");
		
		if(userType == null) {
			response.sendRedirect("index.jsp?error=unauthorized");
			return;
		}
		
		UserDao userDao = new UserDao();
		boolean updateStatus = false;
		
		if(userType.equals("admin")) {
			updateStatus = userDao.updateAdminProfile((String)session.getAttribute("username"), username, newPassword);
		}else {
			updateStatus = userDao.updateEmployeeProfile((int) session.getAttribute("employeeId"), username, newPassword);
		}
		
		if(updateStatus) {
			session.setAttribute("username", username);
			response.sendRedirect("profile.jsp?success=updated");
		}else {
			response.sendRedirect("profile.jsp?error=updateFailed");
		}
		
	}

}
