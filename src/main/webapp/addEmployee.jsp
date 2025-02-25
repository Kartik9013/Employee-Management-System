<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Employee</title>
    <!-- <link rel="stylesheet" href="assets/css/addEmployee.css"> -->
</head>
<%
session = request.getSession(false);
if(session == null||session.getAttribute("admin") == null){
	response.sendRedirect("index.jsp");
	return;
}
%>
<body>
	<!-- Navigation Bar -->
    <nav>
		<a href="ViewEmployeeServlet">view</a><br>
		<a href="updateAdmin.jsp">update Profile</a><br>
		<a href="addAdmin.jsp">New Admin Enrollment</a><br>
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
        <h2>Add New Employee</h2>
        <form action="AddEmployeeServlet" method="POST">
            <div class="form-group">
                <label for="name">Employee Name:</label>
                <input type="text" id="name" name="name" required placeholder="Enter Employee's Name">
            </div>
            <div class="form-group">
                <label for="fathersName">Father's Name:</label>
                <input type="text" id="fathersName" name="fathersName" required placeholder="Enter Father's Name">
            </div>
            <div class="form-group">
                <label for="dob">Date of Birth:</label>
                <input type="date" id="dob" name="dob" required>
            </div>
            <div class="form-group">
                <label for="salary">Salary:</label>
                <input type="number" id="salary" name="salary" required placeholder="Enter Employee's Salary" step="0.01">
            </div>
            <div class="form-group">
                <label for="designation">Designation:</label>
                <input type="text" id="designation" name="designation" required placeholder="Enter Employee's Designation">
            </div>
            <div class="form-group">
                <label for="address">Address:</label>
                <textarea id="address" name="address" required placeholder="Enter Employee's Address"></textarea>
            </div>
            <div class="form-group">
                <label for="phone">Phone:</label>
                <input type="text" id="phone" name="phone" required placeholder="Enter Employee's Phone Number">
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required placeholder="Enter Employee's Email">
            </div>
            <div class="form-group">
                <label for="highestEducation">Highest Education:</label>
                <input type="text" id="highestEducation" name="highestEducation" required placeholder="Enter Employee's Highest Education">
            </div>
            <div class="form-group">
                <button type="submit">Add Employee</button>
            </div>
        </form>
        <a href="dashboard.jsp">Back to Dashboard</a>
    </div>
</body>
</html>
