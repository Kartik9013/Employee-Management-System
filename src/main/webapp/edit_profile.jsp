<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Profile -- EMS</title>
</head>
<%
session = request.getSession(false);

if (session == null || session.getAttribute("userType") == null) {
    response.sendRedirect("index.jsp?error=unauthorized");
    return;
}
	String userType = (String)session.getAttribute("userType");
	String username = (String)session.getAttribute("username");
%>
<body>
	<div class="profile-container">
        <h2>Edit Profile</h2>
        <form action="UpdateProfileServlet" method="post">
            <label>Username:</label>
            <input type="text" name="username" value="<%= username %>" required>

            <label>New Password:</label>
            <input type="password" name="newPassword" placeholder="Enter new password">

            <button type="submit">Update</button>
        </form>

        <a href="profile.jsp">Cancel</a>
    </div>
</body>
</html>