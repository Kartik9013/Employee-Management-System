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
 * Servlet implementation class AddAdminServlet
 */
@WebServlet("/AddAdminServlet")
public class AddAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession(false);
		if(session == null||session.getAttribute("admin") == null){
			response.sendRedirect("index.jsp");
			return;
		}
		
		
		String username = request.getParameter("username");
		String fullname = request.getParameter("fullname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		Admin admin = new Admin(fullname, password, email, username);
		
		AdminDao adminDao = new AdminDao();
		
		boolean isAdded = adminDao.addAdmin(admin);
		
		String rediretUrl;
		if(isAdded) {
			rediretUrl = "addAdmin.jsp?message=Admin Added Successfully!&messageType=success";
		}else {
			rediretUrl = "addAdmin.jsp?message=Failed to add New Admin. Please try again.&messageType=error";
		}
		
		response.sendRedirect(rediretUrl);
	}

}
