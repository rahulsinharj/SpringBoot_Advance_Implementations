<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">

<title>Home Page</title>
</head>
<body>
<div class="container">

	<h1 class="text-center m-4">Addition of two Numbers :</h1>

	<div class="card mx-auto mt-5 bg-info" style="width: 35%;">
		<div class="card-body py-5">

			<h3 class="text-center text-white" style="text-transform: uppercase;">My Search</h3>

			<form action="addresult" class="mt-3">				

				<div class="form-group">
					<input type="text" name="num1" placeholder="Enter your First Number" class="form-control" /> <br>
					<input type="text" name="num2" placeholder="Enter your Second Number" class="form-control" /> <br>
				<h3> Sum Value is = <small class="text-white bg-success" >${addans} </small> </h3>
				</div>	
				<br>
				<div class="container text-center">
					<button class="btn btn btn-danger">Add</button>
				</div>
				
			</form>
		</div>
	</div>
</div>



</body>
</html>