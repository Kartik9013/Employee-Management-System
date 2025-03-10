<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User DashBoard -- EMS</title>
</head>
<%
	session = request.getSession(false);
	String userType = (String)session.getAttribute("userType");
	String username = (String)session.getAttribute("username");
	if(session == null || !userType.equals("employee") || userType==null){
		response.sendRedirect("index.jsp?error=unauthorized");
		return;
	}
	
%>
<body>
	<div class="dashboard-container">
        <h2>Welcome, <%= username %> (Employee)</h2>

        <div class="dashboard-grid">
            <a href="markAttendance.jsp" class="dashboard-card">Mark Attendance</a><br>
    		<a href="ViewAttendanceServlet" class="dashboard-card">View Attendance</a><br>
    		<a href="employeeProfile.jsp" class="dashboard-card">Profile</a><br>
    		<a href="viewSalary.jsp" class="dashboard-card">View Salary</a><br>
    		<a href="LogoutServlet" class="dashboard-card">Logout</a>
        </div>
    </div>
</body>
</html>