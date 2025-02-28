<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login page -- EMS</title>
    <link rel="stylesheet" type="text/css" href="../assets/css/style.css">
</head>
<body>
    <div class="login-container">
        <h2>Login</h2>
        <%-- Display error message if login fails --%>
        <% if(request.getParameter("error") != null) { %>
            <p class="error">Invalid username or password. Try again.</p>
        <% } %>

        <form action="LoginServlet" method="post">
            <label>username or Email:</label>
            <input type="text" name="username" required>
            
            <label>Password:</label>
            <input type="password" name="password" required>
            
            <button type="submit">Login</button>
            <p>New Employee? <a href="register.jsp">Register Here</a></p>
            
        </form>
    </div>
</body>
</html>
