<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Success Page</title>
</head>
<body>

	<h2>Name is : ${stuform.stuname }</h2>
	<h2>Student Email Id is : ${stuform.stuemail }</h2>
	<h2>Student Roll No is : ${stuform.sturoll }</h2>
	
	<h2>Student DOB is :  <fmt:formatDate type="date" value="${stuform.studob}" />  </h2>
<%-- <h2>Student DOB is :  <fmt:formatDate type="date" value="${stuForm.dob}" pattern="dd/MM/yyyy" />  </h2> --%>
	
	<h2>
		Courses are : <br>
		<c:forEach var="course" items="${stuform.courses}">
			<h3>${course}</h3>
		</c:forEach>
	</h2>
	
	<h2>Student gender is : ${stuform.gender }</h2>
	<h2>Student type is : ${stuform.type }</h2>
	<hr>
	<h2>Address Street is : ${stuform.address.street }</h2>
	<h2>Address City is : ${stuform.address.city }</h2>



</body>
</html>



