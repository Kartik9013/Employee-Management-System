package com.ems.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import com.ems.dao.AttendanceDao;
import com.ems.util.DBConnection;

/**
 * Servlet implementation class MarkAttendanceServlet
 */
@WebServlet("/MarkAttendanceServlet")
public class MarkAttendanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userType") == null || !session.getAttribute("userType").equals("employee")) {
            response.sendRedirect("index.jsp?error=unauthorized");
            return;
        }
        
        int employeeId =(int) session.getAttribute("employeeId");
        String status = request.getParameter("status");
        LocalDate date = LocalDate.now();
        
        AttendanceDao attendanceDao;
		try {
			attendanceDao = new AttendanceDao(DBConnection.getConnection());
			boolean alreadyMarked = attendanceDao.isAttendanceMarked(employeeId, date);
			
			if(alreadyMarked) {
				response.sendRedirect("markAttendance.jsp?message=You have already marked attendance today.");
	            return;
			}
			
			boolean isMarked = attendanceDao.markAttendance(employeeId, date, status);
			if(isMarked) {
	            response.sendRedirect("markAttendance.jsp?message=Attendance marked successfully.");
			}else {
	            response.sendRedirect("markAttendance.jsp?message=Failed to mark attendance. Try again.");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
	}

}
