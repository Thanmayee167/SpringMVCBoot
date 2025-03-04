<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Aliens List</title>
</head>
<body>
    <h2>Aliens List</h2>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
        </tr>
        <c:forEach items="${result}" var="alien">
            <tr>
                <td>${alien.aid}</td>
                <td>${alien.aname}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>