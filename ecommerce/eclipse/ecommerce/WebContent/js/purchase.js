var data;
function getPurchases() {
	$.getJSON("services/purchase/list", function(result) {
		data = result.data;
		for (var row = 0; row < data.length; row = row + 1) {
			var id = data[row].id;
			var name = data[row].created_at_text;
			var item_class = "list-group-item";
			$("#div_purchases").append(
					"<a href='javascript:getItems(" + id
							+ ");' id='purchase_" + id + "' class='"
							+ item_class + "'>" + name + "</a>");
		}
	});
}

function getItems(purchase_id) {
	$('.list-group-item').removeClass('active').addClass('');
	$("#purchase_" + purchase_id).addClass('active');
	
	$.getJSON(
	"services/purchase/items/" + purchase_id,
	function(result) {
		data = result.data;
		$('#div_items').empty();
		var total = 0;
		for (var row = 0; row < data.length; row = row + 1) {
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
			dp += '<h4 class="product-name"><strong>'
					+ name + '</strong></h4>';
			dp += '<h4>';
			dp += '<small>' + short_description
					+ '</small>';
			dp += '</h4>';
			dp += '</div>';
			
			dp += '<div class="col-12 col-sm-12 text-sm-center col-md-4 text-md-right row">';
			dp += '<div class="col-12 col-sm-12 col-md-12 text-md-right" style="padding-top: 5px">';
			dp += '<h6><strong>'
					+ pricing.toFixed(2)
					+ ' <span class="text-muted">x</span> ' + quantity + ' = '
					+ (pricing * quantity).toFixed(2) + '</strong></h6>';
			dp += '</div>';
								
			dp += '</div>';
			dp += '</div>';
			dp += '<hr>';

			$("#div_items").append(dp);
		}

		$("#total").empty();
		$("#total").append("$" + total.toFixed(2));
		
	});
}
