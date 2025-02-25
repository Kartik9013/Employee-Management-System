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
 * Servlet implementation class editEmployeeServlet
 */
@WebServlet("/EditEmployeeServlet")
public class editEmployeeServlet extends HttpServlet {
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
		
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			String fathersName = request.getParameter("fathersName");
			LocalDate dob = LocalDate.parse(request.getParameter("dob"));
			double salary = Double.parseDouble(request.getParameter("salary"));
			String designation = request.getParameter("designation");
			String address = request.getParameter("address");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			String highestEducation = request.getParameter("highestEducation");
			
			Employee employee = new Employee(id, name, fathersName, dob, salary, designation, address, phone, email, highestEducation);
			EmployeeDao employeeDao = new EmployeeDao();
			boolean updated = employeeDao.updateEmployee(employee);
			
			if (updated) {
                response.sendRedirect("ViewEmployeeServlet?message=Employee updated successfully!&messageType=success");
            } else {
                response.sendRedirect("editEmployee.jsp?id=" + id + "&message=Failed to update employee.&messageType=error");
            }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.sendRedirect("editEmployee.jsp?message=Something went wrong!&messageType=error");
		}
		
	}

}
