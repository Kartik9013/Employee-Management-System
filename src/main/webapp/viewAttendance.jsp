<%@page import="com.ems.model.Attendance"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Attendance (Employees) -- EMS</title>
</head>
<%
session = request.getSession(false);
if (session == null || session.getAttribute("userType") == null || !session.getAttribute("userType").equals("employee")) {
    response.sendRedirect("index.jsp?error=unauthorized");
    return;
}
@SuppressWarnings("unchecked")
List<Attendance> attendanceList = (List<Attendance>) request.getAttribute("attendanceRecords");


%>
<body>
	<h2>Your Attendance Records</h2>

    <table border="1">
        <tr>
            <th>Date</th>
            <th>Status</th>
        </tr>
        <% for (Attendance att : attendanceList) { %>
            <tr>
                <td><%= att.getDate() %></td>
                <td><%= att.getStatus() %></td>
            </tr>
        <% } %>
    </table>

    <a href="user_dashboard.jsp">Back to Dashboard</a>
</body>
</html>