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
 * Servlet implementation class ManageAttendanceServlet
 */
@WebServlet("/ManageAttendanceServlet")
public class ManageAttendanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userType") == null || !session.getAttribute("userType").equals("admin")) {
            response.sendRedirect("index.jsp?error=unauthorized");
            return;
        }
        
        try {
			AttendanceDao attendanceDao = new AttendanceDao(DBConnection.getConnection());
			List<Attendance> attendanceRecords = attendanceDao.getAllAttendanceRecords();
			
			request.setAttribute("attendanceRecords", attendanceRecords);
			request.getRequestDispatcher("manageAttendance.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
