/*
function getCategories(category_id){
    $.getJSON("services/category/list", function(result){
    	data = result.data;   	
    	for(var row=0; row<data.length; row=row+1){
    		var id = data[row].id;
    		var name = data[row].name;
    		var item_class = "list-group-item";
    		if(id == category_id){
    			item_class = "list-group-item active";	
    		}
    		$("#div_categories").append("<a href='javascript:getProducts(" + id + ");' id='category_" + id + "' class='" + item_class + "'>" + name + "</a>");
    	}
    });
}
*/

function getProducts(category_id){
	
	$('.list-group-item').removeClass('active').addClass('');
	$("#category_" + category_id).addClass('active');
	
    $.getJSON("services/product/list/" + category_id, function(result){
    	data = result.data; 
    	$('#div_products').empty();
    	for(var row=0; row<data.length; row=row+1){
    		var id = data[row].id;
    		var name = data[row].name;
    		var pricing = data[row].pricing;
    		var short_description = data[row].short_description;
    		var icon = data[row].icon;
    		
    		var dp = '';
    		dp += '<div class="col-lg-4 col-md-6 mb-4">';
    		dp += '<div class="card h-100">';
    		dp += '<a href="#"><img class="card-img-top" src="images/' + icon + '" alt=""></a>';
    		dp += '<div class="card-body">';
    		dp += '<h4 class="card-title">';
    		dp += '<a href="item.jsp?id=' + id + '">' + name + '</a>';
    		dp += '</h4>';
    		dp += '<h5>$' + pricing.toFixed(2) + '</h5>';
    		dp += '<p class="card-text">' + short_description + '</p>';
    		//dp += '<button type="button" class="btn btn-primary btn-sm">Buy</button>';
    		dp += '<a href="javascript:addToCart(' + id + ');" class="btn btn-info" role="button">Buy</a>';
    		dp += '</div>';
    		dp += '<div class="card-footer">';
    		dp += '<small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>';
    		dp += '</div>';
    		dp += '</div>';
    		dp += '</div>';
    		
    		$("#div_products").append(dp);       	
    	}
    });  
}

function addToCart(product_id){
	$.getJSON("services/cart/add/" + product_id, function(json) {
		var items = json.items;
		$("#shopping_cart").text(items);
	});
}

/*
function updateItemsCount(){
	$.getJSON("services/cart/items", function(json) {
		var items = json.items;
		$("#shopping_cart").text(items);
	});
}
*/