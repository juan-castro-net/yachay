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

function updateItemsCount(){
	$.getJSON("services/cart/items", function(json) {
		var items = json.items;
		$("#shopping_cart").text(items);
	});
}
