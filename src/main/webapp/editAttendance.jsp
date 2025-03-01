<%@page import="com.ems.model.Attendance"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Attendance -- EMS</title>
</head>
<body>
	<h2>Edit Attendance</h2>

    <% 
        session = request.getSession(false);
        if (session == null || session.getAttribute("userType") == null || !session.getAttribute("userType").equals("admin")) {
            response.sendRedirect("index.jsp?error=unauthorized");
            return;
        }

        Attendance attendance = (Attendance) request.getAttribute("attendanceRecord");
        if (attendance == null) {
            response.sendRedirect("ManageAttendanceServlet?error=AttendanceNotFound");
            return;
        }
    %>

    <form action="UpdateAttendanceServlet" method="post">
        <input type="hidden" name="id" value="<%= attendance.getId() %>">
        <input type="hidden" name="employeeId" value="<%= attendance.getEmployeeId() %>">

        <p><strong>Employee ID:</strong> <%= attendance.getEmployeeId() %></p>
        <p><strong>Employee Name:</strong> <%= attendance.getEmployeeName() %></p>
        <p><strong>Date:</strong> <%= attendance.getDate() %></p>

        <label>Attendance Status:</label>
        <select name="status">
            <option value="Present" <%= attendance.getStatus().equals("Present") ? "selected" : "" %>>Present</option>
            <option value="Absent" <%= attendance.getStatus().equals("Absent") ? "selected" : "" %>>Absent</option>
            <option value="Leave" <%= attendance.getStatus().equals("Leave") ? "selected" : "" %>>Leave</option>
        </select>

        <button type="submit">Update</button>
    </form>

    <a href="ManageAttendanceServlet">Back to Attendance Management</a>

</body>
</html>