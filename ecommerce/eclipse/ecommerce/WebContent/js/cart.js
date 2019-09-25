var data;
function getItems(){
    $.getJSON("services/cart/list", function(result){
    	data = result.data; 
    	$('#div_items').empty();
    	var total = 0;
    	for(var row=0; row<data.length; row=row+1){
    		var product_id = data[row].product_id;
    		var name = data[row].product_name;
    		var pricing = data[row].price;
    		var short_description = data[row].product_description;
    		var icon = data[row].product_icon;
    		var quantity = data[row].quantity;
    		
    		total = total + pricing * quantity;		
    		
    		var dp = '';
			dp += '<div id="row_' + product_id + '" class="row">';
    		dp += '<div class="col-12 col-sm-12 col-md-2 text-center">';
    		dp += '<img class="img-responsive" src="images/' + icon + '" alt="prewiew" width="120" height="80">';
    		dp += '</div>';
    		dp += '<div class="col-12 text-sm-center col-sm-12 text-md-left col-md-6">';
    		dp += '<h4 class="product-name"><strong>' + name + '</strong></h4>';
    		dp += '<h4>';
    		dp += '<small>' + short_description + '</small>';
    		dp += '</h4>';
    		dp += '</div>';
    		dp += '<div class="col-12 col-sm-12 text-sm-center col-md-4 text-md-right row">';
    		dp += '<div class="col-3 col-sm-3 col-md-6 text-md-right" style="padding-top: 5px">';
    		dp += '<h6><strong>' + pricing.toFixed(2) + ' <span class="text-muted">x</span></strong></h6>';
    		dp += '</div>';
    		dp += '<div class="col-4 col-sm-4 col-md-4">';
    		dp += '<div class="quantity">';
    		dp += '<input type="button" value="+" class="plus">';
    		dp += '<input type="number" step="1" max="99" min="1" value="' + quantity + '" title="Qty" class="qty" size="4">';
            dp += '<input type="button" value="-" class="minus">';
            dp += '</div>';
            dp += '</div>';
            dp += '<div class="col-2 col-sm-2 col-md-2 text-right">';
            dp += '<a class="nav-link" href="javascript:removeItem(' + product_id + ');">';
            dp += '<button type="button" class="btn btn-outline-danger btn-xs">';
            dp += '<i class="fa fa-trash" aria-hidden="true"></i>';
            dp += '</button>';
            dp += '</a>';
            dp += '</div>';
            dp += '</div>';
            dp += '</div>';
            dp += '<hr>';
               		
    		$("#div_items").append(dp);       	
    	}
    	
    	$("#total").empty();
		$("#total").append("$" + total.toFixed(2));
		
    	var ub = '';
        ub += '<div class="pull-right">';
        ub += '<a href="javascript:updateCart();" class="btn btn-outline-secondary pull-right">';
        ub += 'Update shopping cart';
        ub += '</a>';
        ub += '</div>';
    	$("#div_items").append(ub);
    });  
}

function removeItem(product_id){
	for(var row=0; row<data.length; row=row+1){
		if(data[row].product_id == product_id){
			data.splice(row, 1);
		}
	}
	$.getJSON("services/cart/del/" + product_id, function(result){
		getItems();
		updateItemsCount();
	});
}

function updateCart(){
	var allListElements = $(".qty").get();
	var text = '';
	var separator = '';
	for(var i=0; i<allListElements.length; i=i+1){
		var product_id = data[i].product_id;
		var quantity = allListElements[i].value;
		text += separator + '{"product_id":' + product_id + ',"quantity":' + quantity + '}';
		separator = ',';
	}
	
	text = '{"data":[' + text + ']}';
	var posting = $.post("services/cart/update/", text);
			 
	// Put the results in a div
	posting.done(function(result) {
		var total = 0;
		for(var i=0; i<allListElements.length; i=i+1){
			var id = data[i].product_id;
			var pricing = data[i].price;
			var quantity = allListElements[i].value;
			total = total + pricing * quantity;	
		}
		$('#total').empty();
		$("#total").append("$" + total.toFixed(2));
	});
	  
}

function checkout(){
	$.getJSON("services/cart/checkout", function(result){
		$("#checkout").addClass("disabled");
		updateItemsCount();
	});
}
