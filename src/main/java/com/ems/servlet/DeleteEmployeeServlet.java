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
 * Servlet implementation class DeleteEmployeeServlet
 */
@WebServlet("/DeleteEmployeeServlet")
public class DeleteEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session == null||session.getAttribute("admin") == null){
			response.sendRedirect("index.jsp");
			return;
		}
		
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			EmployeeDao employeeDao = new EmployeeDao();
			boolean isDeleted = employeeDao.deleteEmployee(id);
			
			if(isDeleted) {
				response.sendRedirect("ViewEmployeeServlet?message=Employee deleted successfully!&messageType=success");
			}else {
				response.sendRedirect("ViewEmployeeServlet?message=Some error occured&messageType=success");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
