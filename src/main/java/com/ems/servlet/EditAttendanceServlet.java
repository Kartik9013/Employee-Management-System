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
import com.ems.model.Attendance;
import com.ems.util.DBConnection;

/**
 * Servlet implementation class EditAttendanceServlet
 */
@WebServlet("/EditAttendanceServlet")
public class EditAttendanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userType") == null || !session.getAttribute("userType").equals("admin")) {
            response.sendRedirect("index.jsp?error=unauthorized");
            return;
        }
        
        int attendanceId = Integer.parseInt(request.getParameter("id"));
        try {
			AttendanceDao attendanceDao = new AttendanceDao(DBConnection.getConnection());
			Attendance attendance = attendanceDao.getAttendanceById(attendanceId);
			
			if(attendance!=null) {
				request.setAttribute("attendanceRecord", attendance);
				request.getRequestDispatcher("editAttendance.jsp").forward(request, response);
			}else {
	            response.sendRedirect("ManageAttendanceServlet?error=AttendanceNotFound");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
