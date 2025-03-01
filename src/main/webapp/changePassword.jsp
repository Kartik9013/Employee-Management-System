<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employee Password Change -- EMS</title>
</head>
<body>
	<h2>Change Password</h2>

    <% 
        session = request.getSession(false);
        if (session == null || session.getAttribute("userType") == null || !session.getAttribute("userType").equals("employee")) {
            response.sendRedirect("index.jsp?error=unauthorized");
            return;
        }
        
        String message = request.getParameter("message");
        if (message != null) { 
    %>
        <p><%= message %></p>
    <% } %>

    <form action="ChangePasswordServlet" method="post">
        <label>Current Password:</label>
        <input type="password" name="currentPassword" required><br>

        <label>New Password:</label>
        <input type="password" name="newPassword" required><br>

        <label>Confirm New Password:</label>
        <input type="password" name="confirmPassword" required><br>

        <button type="submit">Change Password</button>
    </form>

    <a href="employeeProfile.jsp">Back to Profile</a>

</body>
</html>