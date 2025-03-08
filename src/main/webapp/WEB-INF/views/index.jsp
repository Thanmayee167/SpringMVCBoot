<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<!DOCTYPE html>
	<html>

	<head>
		<meta charset="ISO-8859-1">
		<title>Home Page</title>
	</head>

	<body>
		<h1>Welcome to My Home</h1>
		<form action="add">
			Enter 1st number : <input type="text" name="num1"><br>
			Enter 2nd number : <input type="text" name="num2"><br>
			<input type="submit">
		</form>
		<hr>
		<form action="addAlien">
			Enter your id : <input type="text" name="aid"><br>
			Enter your name : <input type="text" name="aname"><br>
			<input type="submit">
		</form>
		<hr>
		<form action="getAlienByID" method="get">
			Enter your id : <input type="text" name="aid"><br>
			<input type="submit">
		</form>
		<hr>
		<form action="getAlienByName" method="get">
			Enter your name : <input type="text" name="aname"><br>
			<input type="submit">
		</form>
		<form action="${pageContext.request.contextPath}/logout" method="post">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<input type="submit" value="Logout" />
		</form>
	</body>

	</html>