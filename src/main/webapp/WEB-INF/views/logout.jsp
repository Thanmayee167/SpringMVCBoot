<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	Logout home <br>
	<form action="${pageContext.request.contextPath}/home" method="post">
    			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    			<input type="submit" value="HOME" />
    </form>
</body>
</html>