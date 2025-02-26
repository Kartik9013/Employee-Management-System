<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profile -- EMS</title>
</head>
<%
session = request.getSession(false);

if (session == null || session.getAttribute("userType") == null) {
    response.sendRedirect("index.jsp?error=unauthorized");
    return;
}

String userType = (String) session.getAttribute("userType");
String username = (String) session.getAttribute("username");
	
Integer userId = null;
if("employee".equals(userType) && session.getAttribute("employeeId") != null){
	userId = (Integer) session.getAttribute("employeeId");
}

String dashboardUrl = userType.equals("admin") ? "admin_dashboard.jsp" : "user_dashboard.jsp";
	
String successMessage = request.getParameter("success");
String errorMessage = request.getParameter("error");
	
%>
<body>
	<div class="profile-container">
        <h2>Profile Details</h2>
        
         <%-- Display Success Message --%>
        <% if (successMessage != null) { %>
            <p class="success"><%= successMessage %></p>
        <% } %>

        <%-- Display Error Message --%>
        <% if (errorMessage != null) { %>
            <p class="error"><%= errorMessage %></p>
        <% } %>
        
        <p><strong>Username:</strong> <%= username %></p>
        <% if (userType.equals("employee")) { %>
            <p><strong>Employee ID:</strong> <%= userId %></p>
        <% } %>
        <p><strong>Role:</strong> <%= userType %></p>

        <a href="edit_profile.jsp">Edit Profile</a>
        <a href="<%= dashboardUrl %>">Back to Dashboard</a>
    </div>
</body>
</html>