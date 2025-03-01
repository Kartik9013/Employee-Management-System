<%@page import="com.ems.model.Employee"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Employee Profile -- EMS</title>
</head>
<%
	session = request.getSession(false);
	if(session == null || session.getAttribute("userType") == null || !session.getAttribute("userType").equals("employee")) {
		response.sendRedirect("index.jsp?error=unauthorized");
		return;
	}
	
	Employee employee = (Employee)session.getAttribute("employeeData");


%>
<body>
	<h2>Edit Profile</h2>

    <form action="UpdateEmployeeProfileServlet" method="post">
        <input type="hidden" name="id" value="<%= employee.getId() %>">

        <label>Name:</label>
        <input type="text" name="name" value="<%= employee.getName() %>" required><br>

        <label>Father's Name:</label>
        <input type="text" name="fathersName" value="<%= employee.getFathersName() %>" required><br>

        <label>Date of Birth:</label>
        <input type="date" name="dob" value="<%= employee.getDob() %>" required><br>

        <label>Designation:</label>
        <input type="text" name="designation" value="<%= employee.getDesignation() %>" required><br>

        <label>Address:</label>
        <input type="text" name="address" value="<%= employee.getAddress() %>" required><br>

        <label>Phone:</label>
        <input type="text" name="phone" value="<%= employee.getPhone() %>" required><br>

        <label>Email:</label>
        <input type="email" name="email" value="<%= employee.getEmail() %>" required><br>

        <label>Highest Education:</label>
        <input type="text" name="highestEducation" value="<%= employee.getHighestEducation() %>" required><br>

        <button type="submit">Update Profile</button>
    </form>
    <a href="employeeProfile.jsp">Back to Profile</a>
</body>
</html>