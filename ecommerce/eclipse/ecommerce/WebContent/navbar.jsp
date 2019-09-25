<%
	String username = (String)session.getAttribute("username");
	if (username == null){
		username = "";
	}
%>
<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
	<div class="container">
		<a class="navbar-brand" href="#">Start Bootstrap</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarResponsive" aria-controls="navbarResponsive"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item active"><a class="nav-link" href="./">Home
						<span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item"><a class="nav-link" href="#">About</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Services</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="cart.jsp">
						<button type="button" class="btn btn-primary">
							Shopping Cart <span id="shopping_cart" class="badge badge-light">0</span>
						</button>
				</a></li>
				<% if(username.length() == 0){ %>
				<li class="nav-item"><a class="nav-link" href="login.jsp">
						<button type="button" class="btn btn-success">Login</button>
				</a></li>
				<% } %>
				<% if(username.length() > 0){ %>
				<li class="nav-item">
					<div class="btn-group nav-link" role="group">
						<button id="btnGroupDrop1" type="button"
							class="btn btn-primary dropdown-toggle" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false">
							<%= username %>
						</button>
						<div class="dropdown-menu" aria-labelledby="btnGroupDrop1">
							<a class="dropdown-item" href="purchase.jsp">Purchases</a> <a
								class="dropdown-item" href="Logout">Logout</a>
						</div>
					</div>
				</li>
				<% } %>

			</ul>
		</div>
	</div>
</nav>
