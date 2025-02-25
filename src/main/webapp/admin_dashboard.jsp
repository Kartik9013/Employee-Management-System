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

        <nav>
            <ul>
                <li><a href="ViewEmployeeServlet">View Employees</a></li>
                <li><a href="addEmployee.jsp">Add Employee</a></li>
                <li><a href="updateAdmin.jsp">Update Profile</a></li>
                <li><a href="LogoutServlet">Logout</a></li>
            </ul>
        </nav>
    </div>
</body>
</html>