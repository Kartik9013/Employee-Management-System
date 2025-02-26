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
	if(session == null || !userType.equals("emplpoyee") || userType==null){
		response.sendRedirect("index.jsp?error=unauthorized");
		return;
	}
	
%>
<body>
	<div class="dashboard-container">
        <h2>Welcome, <%= username %> (Employee)</h2>

        <div class="dashboard-grid">
            <a href="profile.jsp" class="dashboard-card">View Profile</a>
            <a href="viewSalary.jsp" class="dashboard-card">View Salary</a>
            <a href="LogoutServlet" class="dashboard-card logout">Logout</a>
        </div>
    </div>
</body>
</html>