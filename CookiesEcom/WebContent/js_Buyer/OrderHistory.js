$(function() {
	
	
	$('.cancelButton').click(function() {
		
		var root         = $(this).closest('.root'); 
		var orderTableId = root.find('.orderTableId').html(); //alert(orderTableId);
		
		var r = confirm("Alert: Do You Really Want To Cancel The Item!");
		
		if (r == true) {  	
			
			var keyValue = {orderTableId: orderTableId};
				
			$.getJSON('CancelOrderFromCustomer', keyValue, function(data) {
				
				if (data.status) {
					
					root.find('.cancel').html('<a href="#"> Cancelled   </a>');
					alert("Your Item Has Been Cancelled!");
				}
				
			});
		
		} // if close
		
	});
	
	
	
});