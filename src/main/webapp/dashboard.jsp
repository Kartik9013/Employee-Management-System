<%@page import="com.ems.model.Admin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DashBoard page -- EMS</title>
</head>
<%
	session = request.getSession(false);
	if(session == null||session.getAttribute("admin") == null){
		response.sendRedirect("index.jsp");
		return;
	}
	
	Admin admin = new Admin();
	admin = (Admin)session.getAttribute("admin");

%>
<body>
	<h2>Welcome <%= admin.getFullname() %></h2>
	<nav>
		<a href="addEmployee.jsp">Add</a><br>
		<a href="ViewEmployeeServlet">view</a><br>
		<a href="updateAdmin.jsp">update Profile</a><br>
		<a href="addAdmin.jsp">New Admin Enrollment</a><br>
		<a href="LogoutServlet">Log Out</a><br>
	</nav>
</body>
</html>