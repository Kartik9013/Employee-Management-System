<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mark Attendance -- EMS</title>
</head>
<%
	session = request.getSession(false);
	if (session == null || session.getAttribute("userType") == null || !session.getAttribute("userType").equals("employee")) {
    	response.sendRedirect("index.jsp?error=unauthorized");
    	return;
	}
%>
<body>
	<h2>Mark Attendance</h2>

    <% 
        String message = request.getParameter("message");
        if (message != null) { 
    %>
        <p><%= message %></p>
    <% } %>

    <form action="MarkAttendanceServlet" method="post">
        <label>Attendance Status:</label>
        <select name="status">
            <option value="Present">Present</option>
            <option value="Leave">Leave</option>
        </select>
        <button type="submit">Submit</button>
    </form>

    <a href="user_dashboard.jsp">Back to Dashboard</a>
</body>
</html>