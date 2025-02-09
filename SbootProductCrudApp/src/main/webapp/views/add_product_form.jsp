<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	  pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="./base.jsp"%>
</head>
<body  id="mybg">


<div class="container mt-3">

	<div class="row">

		<div class="col-md-10 mx-auto">

			<h1 class="text-center mb-3">Fill the product detail</h1>

			<form action="handle-product" method="post">
				<div class="form-group">
					<label for="name">Product Name</label> 
					<input type="text" class="form-control" id="name" aria-describedby="emailHelp" name="name" placeholder="Enter Product Name" required="true">
				</div>

				<div class="form-group">

					<label for="description">Product Description</label>
					<textarea class="form-control" name="description" id="description" rows="5" placeholder="Enter Product Description" required="true"></textarea>
				</div>

				<div class="form-group">
					<label for="price">Product Price</label> 
					<input type="text" placeholder="Enter Product Price" name="price" class="form-control" id="price" required="true">
				</div>

				<div class="container text-center">

					<a href="/home" class="btn btn-outline-danger">Back</a>			<!-- // also, href="${pageContext.request.contextPath }/home" will work fine, because springboot is directing accessing localhost:8080/home , without adding any project name in between   -->

					<button type="submit" class="btn btn-success">Add</button>
					
					
				</div>
			</form>

		</div>	
	</div>
</div>




</body>
</html>