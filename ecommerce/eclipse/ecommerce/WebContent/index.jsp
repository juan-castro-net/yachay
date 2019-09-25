<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	String category_id = request.getParameter("id");
	if (category_id == null){
		category_id = "1";	
	}	
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport"
		content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="description" content="">
	<meta name="author" content="">
	
	<title>Shop Homepage - Start Bootstrap Template</title>
	
	<!-- Bootstrap core CSS -->
	<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	
	<!-- Custom styles for this template -->
	<link href="css/shop-homepage.css" rel="stylesheet">
</head>
<body>
	<!-- Navigation -->
	<jsp:include page="navbar.jsp"></jsp:include>

	<!-- Page Content -->
	<div class="container">
		<div class="row">
			<div class="col-lg-3">
				<h1 class="my-4">Shop Name</h1>
				<div class="list-group" id="div_categories"></div>
			</div>
			<!-- /.col-lg-3 -->

			<div class="col-lg-9">
				<!-- Navigation -->
				<jsp:include page="carousel.jsp"></jsp:include>
	
				<div class="row" id="div_products"></div>
				<!-- /.row -->
			</div>
			<!-- /.col-lg-9 -->

		</div>
		<!-- /.row -->
	</div>
	<!-- /.container -->
	
	<!-- Footer -->
	<jsp:include page="footer.jsp"></jsp:include>
	
	<!-- Bootstrap core JavaScript -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="js/ecommerce.js"></script>
	<script src="js/index.js"></script>

	<script>
	  	$(document).ready(function(){
	  		var category_id = <%= category_id %>;
			getCategories(category_id);
			getProducts(category_id);
			updateItemsCount();
		});
	</script>

</body>
</html>