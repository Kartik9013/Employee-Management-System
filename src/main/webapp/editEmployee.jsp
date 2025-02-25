<%@page import="com.ems.dao.EmployeeDao"%>
<%@ page import="java.util.*, java.time.LocalDate" %>
<%@ page import="com.ems.model.Employee" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%
session = request.getSession(false);
if(session == null||session.getAttribute("admin") == null){
	response.sendRedirect("index.jsp");
	return;
}

int id = Integer.parseInt(request.getParameter("id"));
EmployeeDao employeeDao = new EmployeeDao();
Employee employee = employeeDao.getEmployeeById(id);

%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Employee</title>
    <link rel="stylesheet" href="assets/css/style.css"> <!-- Link to external CSS -->
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 50%;
            margin: 50px auto;
            background: #fff;
            padding: 20px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            transition: transform 0.3s;
        }
        .container:hover {
            transform: scale(1.02);
        }
        h2 {
            text-align: center;
            color: #333;
        }
        label {
            font-weight: bold;
            margin-top: 10px;
            display: block;
        }
        input, select {
            width: 100%;
            padding: 8px;
            margin: 5px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
        }
        .btn-container {
            display: flex;
            justify-content: space-between;
            margin-top: 20px;
        }
        .btn {
            background: #007bff;
            color: #fff;
            padding: 10px 15px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
            transition: background 0.3s;
        }
        .btn:hover {
            background: #0056b3;
        }
        .btn-danger {
            background: #dc3545;
        }
        .btn-danger:hover {
            background: #a71d2a;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>Update Employee</h2>
    <form action="EditEmployeeServlet" method="post">
        <input type="hidden" name="id" value="<%= employee.getId() %>">

        <label for="name">Full Name:</label>
        <input type="text" id="name" name="name" value="<%= employee.getName() %>" required>

        <label for="fathersName">Father's Name:</label>
        <input type="text" id="fathersName" name="fathersName" value="<%= employee.getFathersName() %>" required>

        <label for="dob">Date of Birth:</label>
        <input type="date" id="dob" name="dob" value="<%= employee.getDob() %>" required>

        <label for="salary">Salary:</label>
        <input type="number" id="salary" name="salary" value="<%= employee.getSalary() %>" step="0.01" required>

        <label for="designation">Designation:</label>
        <input type="text" id="designation" name="designation" value="<%= employee.getDesignation() %>" required>

        <label for="address">Address:</label>
        <input type="text" id="address" name="address" value="<%= employee.getAddress() %>" required>

        <label for="phone">Phone:</label>
        <input type="text" id="phone" name="phone" value="<%= employee.getPhone() %>" required>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" value="<%= employee.getEmail() %>" required>

        <label for="highestEducation">Highest Education:</label>
        <input type="text" id="highestEducation" name="highestEducation" value="<%= employee.getHighestEducation() %>" required>

        <div class="btn-container">
            <button type="submit" class="btn">Update</button>
            <a href="viewEmployees.jsp" class="btn btn-danger">Cancel</a>
        </div>
    </form>
</div>

</body>
</html>
