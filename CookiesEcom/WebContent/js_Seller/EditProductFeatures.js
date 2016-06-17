$(function() {	
	
	/*********** Basic Features ************/
	
	$('form#form1').submit(function(event) {
		
		event.preventDefault();
		
		$('#msg1').empty();
		
		var r = confirm("Alert: Do You Really Want To Edit This Basic Product!");
		
		if (r == true) {  
		
				var formData = new FormData($(this)[0]);
				
				$.ajax({
					url: 'EditBasicProduct',
					type: 'POST',
					data: formData,
					async: false,
				    cache: false,
				    contentType: false,
				    processData: false,
				    dataType: 'json',
				    success: function (data) {
				    	
				    	$('#msg1').empty();
				    	
				    	if (data.success)				    	
				    		$('#msg1').append(data.success);
				    	
				    	if (data.failed)				    	
				    		$('#msg1').append(data.failed);
				    	
				    },
				    error: function() {
				  		$('#msg1').empty();
				  		$('#msg1').append('Basic Features Not Updated');
				  	}
				});
		
		} // if close
		
		
		return false;
	});
	
	/******** Product Advance Features ************/
	
	$('form#form2').submit(function(event) {
		
		event.preventDefault();
		
		$('#wholeSaleMessage').empty();
		
		var r = confirm("Alert: Do You Really Want To Add Whole Sale Offer for this Product!");
		
		if (r == true) {  
		
				var formData = new FormData($(this)[0]);
				
				$.ajax({
					url: 'EditProductAdvanceFeatures',
					type: 'POST',
					data: formData,
					async: false,
				    cache: false,
				    contentType: false,
				    processData: false,
				    dataType: 'json',
				    success: function (data) {
				    	
				    	$('#wholeSaleMessage').empty();
				    	
				    	if (data.status)				    	
				    		$('#wholeSaleMessage').append(data.status);			    	
				    	
				    },
				    error: function() {
				  		$('#wholeSaleMessage').empty();
				  		$('#wholeSaleMessage').append('Some error occurred! Please try again.');
				  	}
				});
		
		} // if close
		
		
		return false;
	});
	
	
	$('form#form3').submit(function(event) {
		
		event.preventDefault();
		
		$('#searchKeywordMessage').empty();
		
		var r = confirm("Alert: Do You Really Want To Add Product Search Keyword for this Product!");
		
		if (r == true) {  
		
				var formData = new FormData($(this)[0]);
				
				$.ajax({
					url: 'EditProductSearchKeyword',
					type: 'POST',
					data: formData,
					async: false,
				    cache: false,
				    contentType: false,
				    processData: false,
				    dataType: 'json',
				    success: function (data) {
				    	
				    	$('#searchKeywordMessage').empty();
				    	
				    	if (data.status)				    	
				    		$('#searchKeywordMessage').append(data.status);			    	
				    	
				    },
				    error: function() {
				  		$('#searchKeywordMessage').empty();
				  		$('#searchKeywordMessage').append('Some error occurred! Please try again.');
				  	}
				});
		
		} // if close
		
		
		return false;
	});
	
	
	
	
});