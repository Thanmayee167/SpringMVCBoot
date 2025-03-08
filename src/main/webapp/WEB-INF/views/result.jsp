<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

    <h1>Welcome ${name}</h1>
	Result is : ${alien}
	<br>
	<form action="${pageContext.request.contextPath}/home" method="post">
        			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        			<input type="submit" value="HOME" />
    </form>
</body>
</html>