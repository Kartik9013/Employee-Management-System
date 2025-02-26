package com.ems.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.ems.dao.AdminDao;
import com.ems.model.Admin;

/**
 * Servlet implementation class UpdateAdminServlet
 */
@WebServlet("/UpdateAdminServlet")
public class UpdateAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("userType") == null) {
		    response.sendRedirect("index.jsp?error=unauthorized");
		    return;
		}
		
		Admin admin = (Admin) session.getAttribute("admin");
		String username = admin.getUsername();
		
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		String confirmPassword = request.getParameter("confirmPassword");
		
		if(!newPassword.equals(confirmPassword)) {
			response.sendRedirect("updateAdmin.jsp?message=Passwords do not match!&messageType=error");
			return;
		}
		
		AdminDao adminDao = new AdminDao();
		boolean isUpdated = adminDao.upadteAdminPassword(username, oldPassword, newPassword);
		
		if(isUpdated) {
			response.sendRedirect("updateAdmin.jsp?message=Password Updated Successfully!&messageType=sucess");
		}else {
			response.sendRedirect("updateAdmin.jsp?message=Old Password is incorrect!&messageType=error");
		}
		
	}

}
