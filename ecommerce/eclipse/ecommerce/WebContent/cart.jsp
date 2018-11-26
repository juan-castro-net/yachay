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
	
	<link href="css/cart.css" rel="stylesheet">
</head>

<body>
	<!-- Navigation -->
	<jsp:include page="navbar.jsp"></jsp:include>

	<!-- Page Content -->
	<script src="https://use.fontawesome.com/c560c025cf.js"></script>	
	<br/>
	<div class="container">
	   <div class="card shopping-cart">
	            <div class="card-header bg-dark text-light">
	                <i class="fa fa-shopping-cart" aria-hidden="true"></i>
	                Shopping cart
	                <a href="./" class="btn btn-outline-info btn-sm pull-right">Continue shopping</a>
	                <div class="clearfix"></div>
	            </div>
	            <div id="div_items" class="card-body">        
	                <div class="pull-right">
	                    <a href="" class="btn btn-outline-secondary pull-right">
	                        Update shopping cart
	                    </a>
	                </div>
	            </div>
	            <div class="card-footer">
	                <div class="coupon col-md-5 col-sm-5 no-padding-left pull-left">
	                    <div class="row">
	                        <div class="col-6">
	                            <input type="text" class="form-control" placeholder="cupone code">
	                        </div>
	                        <div class="col-6">
	                            <input type="submit" class="btn btn-default" value="Use cupone">
	                        </div>
	                    </div>
	                </div>
	                <div class="pull-right" style="margin: 10px">
	                    <a href="javascript:checkout();" id="checkout" class="btn btn-success pull-right">Checkout</a>
	                    <div class="pull-right" style="margin: 5px">
	                        Total price: <b id="total">$0.00</b>
	                    </div>
	                </div>
	            </div>
	        </div>
	</div>
	
	<br/>
	<br/>
		
	<!-- Footer -->
	<jsp:include page="footer.jsp"></jsp:include>

	<!-- Bootstrap core JavaScript -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="js/ecommerce.js"></script>
	<script src="js/cart.js"></script>
	
	<script>
	$(document).ready(function(){
		getItems();
		updateItemsCount();
	});
	</script>

</body>
</html>