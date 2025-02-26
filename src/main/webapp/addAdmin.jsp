<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>New Admin Registration</title>
</head>
<%
session = request.getSession(false);

if (session == null || session.getAttribute("userType") == null) {
    response.sendRedirect("index.jsp?error=unauthorized");
    return;
}

%>
<body>
	<nav>
		<a href="addEmployee.jsp">Add</a><br>
		<a href="ViewEmployeeServlet">view</a><br>
		<a href="updateAdmin.jsp">update Profile</a><br>
		<a href="LogoutServlet">Log Out</a><br>
	</nav>
    
    <!-- Displaying Success or Error Message -->
    <%
        String message = request.getParameter("message");
        String messageType = request.getParameter("messageType");
        if (message != null) {
    %>
        <div class="<%= messageType %>">
            <%= message %>
        </div>
    <%
        }
    %>
    
    <div class="container">
    	<h2>Add New Admin</h2>
    	<form action="AddAdminServlet" method="post">
    		<label for="username">Enter UserName : </label>
    		<input type="text" name="username" id="username" required>
    		
    		<label for="fullname">Enter FullName : </label>
    		<input type="text" name="fullname" id="fullname" required>
    		
    		<label for="email">Enter Email : </label>
    		<input type="email" name="email" id="email" required>
    		
    		<label for="password">Create Password : </label>
    		<input type="password" name="password" id="password" required>
    		
    		<button type="submit">Add Admin</button>
    	</form>
    </div>
</body>
</html>