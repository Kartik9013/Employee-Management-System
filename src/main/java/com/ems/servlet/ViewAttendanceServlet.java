package com.ems.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.ems.dao.AttendanceDao;
import com.ems.model.Attendance;
import com.ems.util.DBConnection;

/**
 * Servlet implementation class ViewAttendanceServlet
 */
@WebServlet("/ViewAttendanceServlet")
public class ViewAttendanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userType") == null || !session.getAttribute("userType").equals("employee")) {
            response.sendRedirect("index.jsp?error=unauthorized");
            return;
        }
        
        int employeeId = (int) session.getAttribute("employeeId");
        try {
			AttendanceDao attendanceDao = new AttendanceDao(DBConnection.getConnection());
			List<Attendance> attendanceRecords = attendanceDao.getAttendanceByEmployeeId(employeeId);
			
			request.setAttribute("attendanceRecords", attendanceRecords);
			request.getRequestDispatcher("viewAttendance.jsp").forward(request, response);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}

}
