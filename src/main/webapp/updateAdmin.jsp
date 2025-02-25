<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.ems.model.Admin" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Update Admin</title>
    <link rel="stylesheet" href="assets/css/style.css">
</head>
<%
    session = request.getSession(false);
    Admin admin = (Admin) session.getAttribute("admin");

    if (admin == null) {
        response.sendRedirect("index.jsp");
        return;
    }

    String message = request.getParameter("message");
    String messageType = request.getParameter("messageType");
%>
<body>



	<nav>
		<a href="addEmployee.jsp">Add</a><br>
		<a href="ViewEmployeeServlet">view</a><br>
		<a href="addAdmin.jsp">New Admin Enrollment</a><br>
		<a href="LogoutServlet">Log Out</a><br>
	</nav>

<h2>Update Admin Profile</h2>

<% if (message != null) { %>
    <div class="<%= messageType %>"><%= message %></div>
<% } %>

<form action="UpdateAdminServlet" method="post">
    <input type="hidden" name="username" value="<%= admin.getUsername() %>">

    <label>Old Password:</label>
    <input type="password" name="oldPassword" required>

    <label>New Password:</label>
    <input type="password" name="newPassword" required>

    <label>Confirm New Password:</label>
    <input type="password" name="confirmPassword" required>

    <button type="submit">Update Password</button>
</form>

</body>
</html>
