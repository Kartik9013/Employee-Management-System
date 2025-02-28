<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>New Employee Registration -- EMS</title>
</head>
<body>
	<h2>Employee Registration</h2>

    <!-- Display success or error message -->
    <% if (request.getParameter("message") != null) { %>
        <p class="<%= request.getParameter("messageType") %>"><%= request.getParameter("message") %></p>
    <% } %>

    <form action="EmployeeRegistrationServlet" method="post">
        <label>Name:</label>
        <input type="text" name="name" required><br>

        <label>Father's Name:</label>
        <input type="text" name="fathersName" required><br>

        <label>Date of Birth:</label>
        <input type="date" name="dob" required><br>

        <label>Salary:</label>
        <input type="number" name="salary" step="0.01" required><br>

        <label>Designation:</label>
        <input type="text" name="designation" required><br>

        <label>Address:</label>
        <input type="text" name="address" required><br>

        <label>Phone:</label>
        <input type="text" name="phone" required><br>

        <label>Email:</label>
        <input type="email" name="email" required><br>

        <label>Highest Education:</label>
        <input type="text" name="highestEducation" required><br>

        <label>Password:</label>
        <input type="password" name="password" required><br>

        <button type="submit">Register</button>
    </form>

    <a href="index.jsp">Back to Login</a>
</body>
</html>