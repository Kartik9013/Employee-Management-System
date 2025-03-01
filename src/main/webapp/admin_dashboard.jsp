<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>admin DashBoard -- EMS</title>
</head>
<%
	session = request.getSession(false);
	String userType = (String)session.getAttribute("userType");
	String username = (String)session.getAttribute("username");
	if(session == null || !userType.equals("admin") || userType==null){
		response.sendRedirect("index.jsp?error=unauthorized");
		return;
	}
	
%>
<body>
	<div class="dashboard-container">
        <h2>Welcome, <%= username %> (Admin)</h2>

        <div class="dashboard-grid">
            <a href="ViewEmployeeServlet" class="dashboard-card">View Employees</a>
            <a href="profile.jsp" class="dashboard-card">Profile</a>
            <a href="addEmployee.jsp" class="dashboard-card">Add Employee</a>
            <a href="ManageAttendanceServlet">Manage Attendance</a><br>
            <a href="updateAdmin.jsp" class="dashboard-card">Change Password</a>
            <a href="LogoutServlet" class="dashboard-card logout">Logout</a>
        </div>
     </div>   
</body>
</html>