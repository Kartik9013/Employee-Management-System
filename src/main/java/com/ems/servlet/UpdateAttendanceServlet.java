package com.ems.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;


import com.ems.dao.AttendanceDao;
import com.ems.util.DBConnection;

/**
 * Servlet implementation class UpdateEmployeeServlet
 */
@WebServlet("/UpdateAttendanceServlet")
public class UpdateAttendanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userType") == null || !session.getAttribute("userType").equals("admin")) {
            response.sendRedirect("index.jsp?error=unauthorized");
            return;
        }
        
        int attendanceId = Integer.parseInt(request.getParameter("id"));
        String status = request.getParameter("status");
        
        try {
			AttendanceDao attendanceDao = new AttendanceDao(DBConnection.getConnection());
			boolean isUpdated = attendanceDao.updateAttendanceStatus(attendanceId, status);
			
			if(isUpdated) {
				response.sendRedirect("ManageAttendanceServlet?message=Attendance Updated Successfully.");
			}else {
				response.sendRedirect("editAttendance.jsp?id=" + attendanceId + "&error=UpdateFailed");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}

}
