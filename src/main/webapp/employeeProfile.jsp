<%@page import="com.ems.model.Employee"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employee Profile -- EMS</title>
</head>
<% 
        session = request.getSession(false);
        if (session == null || session.getAttribute("userType") == null || !session.getAttribute("userType").equals("employee")) {
            response.sendRedirect("index.jsp?error=unauthorized");
            return;
        }

        Employee employee = (Employee) session.getAttribute("employeeData"); // Profile details from session
        if (employee == null) {
            response.sendRedirect("fetchEmployeeProfileServlet");
            return;
        }
%>
<body>
	<h2>Employee Profile</h2>
	<%
	    String message = request.getParameter("message");
        if (message != null) { 
    %>
        <p><%= message %></p>
    <% } %>
	
	<p><strong>Name:</strong> <%= employee.getName() %></p>
    <p><strong>Father's Name:</strong> <%= employee.getFathersName() %></p>
    <p><strong>Date of Birth:</strong> <%= employee.getDob() %></p>
    <p><strong>Designation:</strong> <%= employee.getDesignation() %></p>
    <p><strong>Address:</strong> <%= employee.getAddress() %></p>
    <p><strong>Phone:</strong> <%= employee.getPhone() %></p>
    <p><strong>Email:</strong> <%= employee.getEmail() %></p>
    <p><strong>Highest Education:</strong> <%= employee.getHighestEducation() %></p>

    <a href="editEmployeeProfile.jsp">Edit Profile</a> | 
    <a href="changePassword.jsp">Change Password</a> |
    <a href="user_dashboard.jsp">Back to Dashboard</a>

</body>
</html>