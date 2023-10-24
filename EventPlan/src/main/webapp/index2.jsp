<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>After Login</title>
</head>
<body>
	<a href="login.jsp">LOG OUT</a>
	<button><%=session.getAttribute("username") %></button>
</body>
</html>