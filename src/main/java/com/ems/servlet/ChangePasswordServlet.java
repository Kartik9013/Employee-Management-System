package com.ems.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.ems.dao.EmployeeDao;

/**
 * Servlet implementation class ChangePasswordServlet
 */
@WebServlet("/ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userType") == null || !session.getAttribute("userType").equals("employee")) {
            response.sendRedirect("index.jsp?error=unauthorized");
            return;
        }
        
        int employeeId = (int) session.getAttribute("employeeId");
        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");
        
        
        if(!newPassword.equals(confirmPassword)) {
        	response.sendRedirect("changePassword.jsp?message=Password do not match");
        	return;
        }
        
        //Validate Old Password
        EmployeeDao employeeDao = new EmployeeDao();
        boolean isValidPassword = employeeDao.validateEmployeePassword(employeeId, currentPassword);
        
        if(!isValidPassword) {
        	response.sendRedirect("changePassword.jsp?message=Current password is incorrect");
        	return;
        }
        
        boolean isUpdated = employeeDao.updateEmployeePassword(employeeId, newPassword);
        
        if(isUpdated) {
            response.sendRedirect("employeeProfile.jsp?message=Password changed successfully");
        }else {
            response.sendRedirect("changePassword.jsp?message=Password change failed. Try again.");
		}
        
	}

}
