$(function() {
	
	var availableTags = [];
	
	$("#tags").keyup(function() {
		
		  var keyword = $(this).val();  
		  
		  //alert(keyword);
		  
		  $.getJSON('searchByKeyWord', { keyWord: keyword }, function(array) {				
			  
			  var availableTags = array;	
			  
			  $( "#tags" ).autocomplete({
			      source: availableTags
			   });
		
		  });  	
	});   
	
	
	/*$("form#autocomplete").submit(function(event) {  	
		
		event.preventDefault();  

		var formData = new FormData($(this)[0]);   
		
		$.ajax({
			url: 'searchProductsWithKeyWords',
			data: formData,
			type: 'POST',
			async: false,
			cache: false,
			contentType: false,
			processData: false,
			success: function(data) { 
				$('#showIconImage').removeAttr('src');
				var productId = $('#img1').val(); 
				window.setTimeout(function() {  
					window.location.reload(true);
					$('#showIconImage').attr('src', 'IconImageFromProduct?productId='+productId);
				}, 0);
			}			
		});
		
		return false;
	});*/
    
    
});