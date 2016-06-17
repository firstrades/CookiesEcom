function deleteProduct(element) {
	
	var r = confirm("Alert: Do You Really Want To Delete This Product!");
	
	var parent = $(element).closest('.row'); 
	var productId = parent.find('.productId').val(); 
	var category = parent.find('.category').val();
	var subCategory = parent.find('.subCategory').val();
	
	if (r == true) {		
		
		var keyValue = {productId: productId, category: category, subCategory: subCategory};
		
		$.getJSON('DeleteProduct', keyValue, function(data) {
			
			if (data.success) {
				
				parent.remove();
			}
			
		});
	
	}  // if close
	
	else 
		
		return false;
	

}