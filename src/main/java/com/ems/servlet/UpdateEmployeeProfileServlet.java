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
 * Servlet implementation class UpdateEmployeeProfileServlet
 */
@WebServlet("/UpdateEmployeeProfileServlet")
public class UpdateEmployeeProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session == null || session.getAttribute("userType") == null || !session.getAttribute("userType").equals("employee")) {
			response.sendRedirect("index.jsp?error=unauthorized");
			return;
		}
		
		int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String fathersName = request.getParameter("fathersName");
        LocalDate dob = LocalDate.parse(request.getParameter("dob"));
        String designation = request.getParameter("designation");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String highestEducation = request.getParameter("highestEducation");
        
        
        Employee employee = new Employee(id, name, fathersName, dob, null, designation, address, phone, email, highestEducation);
        EmployeeDao employeeDao = new EmployeeDao();
        boolean isUpdated = employeeDao.updateEmployee(employee);
        
        if(isUpdated) {
        	session.setAttribute("employeeData", employee);
        	response.sendRedirect("employeeProfile.jsp?message=Profile Updated Successfully");
        }else {
			response.sendRedirect("editEmployeeProfile.jsp?error=Update Failed");
		}
	}

}
