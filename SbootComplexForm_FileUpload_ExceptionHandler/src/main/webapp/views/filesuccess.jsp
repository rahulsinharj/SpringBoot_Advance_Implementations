<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>File Success</title>
</head>
<body>
	<h1>${msg}</h1>

	<hr>
	<c:forEach var="singlefile" items="${filename}">
		
		<h3>${singlefile}</h3>
		<img src="static/image/${singlefile}">
	
	</c:forEach>
	
	
</body>
</html>