<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.ems.model.Attendance" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manage Attendance - EMS</title>
</head>
<body>
    <h2>All Employee Attendance Records</h2>

    <% 
        session = request.getSession(false);
        if (session == null || session.getAttribute("userType") == null || !session.getAttribute("userType").equals("admin")) {
            response.sendRedirect("index.jsp?error=unauthorized");
            return;
        }
		@SuppressWarnings("unchecked")
        List<Attendance> attendanceList = (List<Attendance>) request.getAttribute("attendanceRecords");
    %>

    <table border="1">
        <tr>
            <th>Employee ID</th>
            <th>Employee Name</th>
            <th>Date</th>
            <th>Status</th>
            <th>Actions</th>
        </tr>
        <% for (Attendance att : attendanceList) { %>
            <tr>
                <td><%= att.getEmployeeId() %></td>
                <td><%= att.getEmployeeName() %>
                <td><%= att.getDate() %></td>
                <td><%= att.getStatus() %></td>
                <td>
                    <a href="EditAttendanceServlet?id=<%= att.getId() %>">Edit</a>
                </td>
            </tr>
        <% } %>
    </table>

    <a href="admin_dashboard.jsp">Back to Dashboard</a>
</body>
</html>
