<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
	<link href="css/cart.css" rel="stylesheet">
	<link
		href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
		rel="stylesheet" id="bootstrap-css">
	<script
		src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
	<!------ Include the above in your HEAD tag ---------->

	<script src="https://use.fontawesome.com/c560c025cf.js"></script>

	<br />
	<div class="container">
		<div class="row">
		
		<div class="col-lg-3">
			<h1 class="my-4">Purchases</h1>
			<div class="list-group" id="div_purchases"></div>
		</div>
		<!-- /.col-lg-3 -->

		<div class="col-lg-9">
			<div class="card shopping-cart">
				<div class="card-header bg-dark text-light">
					<i class="fa fa-shopping-cart" aria-hidden="true"></i> Shopping
					cart 
					<div class="clearfix"></div>
				</div>
				<div id="div_items" class="card-body">
				</div>
				<div class="card-footer">
					<div class="coupon col-md-5 col-sm-5 no-padding-left pull-left">
						<div class="pull-right" style="margin: 10px">
							Coupon code: <b id="coupon"></b>
						</div>
					</div>
					<div class="pull-right" style="margin: 10px">
						<div class="pull-right" style="margin: 5px">
							Total price: <b id="total">$0.00</b>
						</div>
					</div>
				</div>
			</div>

		</div>
		<!-- /.col-lg-9 -->
		
		</div>
		<!-- /.row -->
		
	</div>
	<!-- /.container -->
	
	<br/>
	<br/>
	
	
	<!-- Footer -->
	<jsp:include page="footer.jsp"></jsp:include>
	

	<!-- Bootstrap core JavaScript -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="js/ecommerce.js"></script>
	<script src="js/purchase.js"></script>
	
	<script>
		$(document).ready(function() {
			getPurchases();
			getItems(0);
			updateItemsCount();
		});
	</script>

</body>
</html>