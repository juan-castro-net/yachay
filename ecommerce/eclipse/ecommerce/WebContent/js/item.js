function getCategories(){
    $.getJSON("services/category/list", function(result){
    	data = result.data;   	
    	for(var row=0; row<data.length; row=row+1){
    		var id = data[row].id;
    		var name = data[row].name;	
    		var item_class = "list-group-item";
			$("#div_categories").append("<a href='index.jsp?id=" + id + "' id='category_" + id + "' class='" + item_class + "'>" + name + "</a>");
    	}
    });
}

function getProductById(product_id){
    $.getJSON("services/product/" + product_id, function(result){
    	data = result.data; 
    	for(var row=0; row<data.length; row=row+1){
    		var id = data[row].id;
    		var category_id = data[row].category_id;
    		var name = data[row].name;
    		var pricing = data[row].pricing;
    		var short_description = data[row].short_description;
    		var long_description = data[row].long_description;
    		var icon = data[row].icon;
    		var media = data[row].media;
    		
    		$("#product_image").attr('src','images/' + icon);   
    		$("#product_name").text(name);
    		$("#product_price").text('$ ' + pricing.toFixed(2));
    		$("#product_description").text(long_description);
    		
    		$('#product_rating').rateit('value', media.toFixed(1));
    		$('#starts').text(media.toFixed(1) + " starts");
    		
	        $('.list-group-item').removeClass('active').addClass('');
        	$("#category_" + category_id).addClass('active');
    	}
    });
    
}

function getReviews(product_id){
    $.getJSON("services/review/list/" + product_id, function(result){
    	data = result.data;   
    	$('#div_reviews').empty();
    	for(var row=0; row<data.length; row=row+1){
    		var id = data[row].id;
    		var username = data[row].username;
    		var comment = data[row].comment;	
    		var created_at = data[row].created_at;
    		var created_at_text = data[row].created_at_text;
    		
    		var review = '';
    		review += '<hr>';
    		review += '<p>' + comment + '</p>';
    		review += '<small class="text-muted">Posted by ' + username + ' on ' + created_at_text + '</small>';         
    
    		$("#div_reviews").append(review);
    	}
    });
}
 
function addReview(product_id){
	var rating = $('#rating').rateit('value');
	var comment = document.getElementById("comment").value;
	
	$.ajax({
		'url':'services/review/add',
		'method':'POST',
		'dataType': 'json',
		'contentType': 'application/json',
		'data':JSON.stringify({
			"product_id":product_id, 
			"rating":rating, 
			"comment":comment
		}),
		'success': function(data){
			$('#rating').rateit('value', 0.0);
			$('#rating_value').text('0.0'); 
        	document.getElementById("comment").value = "";
        	$('#collapseComment').collapse('hide');
        	
        	getReviews(product_id);
        	getProductById(product_id); 
        	
        	$('#div_review_response').empty();
        	var at = '<div class="alert alert-success" role="alert">'; 
        	at += '<button type="button" class="close" data-dismiss="alert" aria-label="Close">';
			at += '<span aria-hidden="true">&times;</span></button>';
			at += '<strong>Success!</strong> ' + data.message + '!';
			at += '</div>';
			$('#div_review_response').append(at);
        	
			//$('.collapse').collapse();
			//alert(data.message);
			window.setTimeout(function() {
		    	$(".alert").fadeTo(500, 0).slideUp(500, function(){
		        	$(this).remove(); 
		      	});
		  	}, 4000);
		},
        'failure': function(errMsg) {
            alert(errMsg);
        }
	});
}

function configureRating(){
	$("#rating").bind('rated', function (event, value) { 
		$('#rating_value').text(value.toFixed(1)); 
	});
	
	$("#rating").bind('reset', function () { 
		$('#rating_value').text('0.0'); 
	});
	
	$("#rating").bind('over', function (event, value) { 
		if (value != null){
			$('#rating_value').text(value.toFixed(1));
		}
		else {
			$('#rating_value').text($('#rating').rateit('value').toFixed(1));
		}
	});    	
}
