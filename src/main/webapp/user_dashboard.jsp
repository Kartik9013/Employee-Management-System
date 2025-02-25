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

        <nav>
            <ul>
                <li><a href="viewProfile.jsp">View Profile</a></li>
                <li><a href="viewSalary.jsp">View Salary</a></li>
                <li><a href="LogoutServlet">Logout</a></li>
            </ul>
        </nav>
    </div>
</body>
</html>