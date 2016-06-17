$(function() {   
	
		
	/*************************** Images ****************************/
	
	$("form#iconImage").submit(function(event) {  	
		
		event.preventDefault();  

		var formData = new FormData($(this)[0]);   
		
		$.ajax({
			url: 'EditIconImage',
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
	});
	
	$("form#image1").submit(function(event) {  	 
		
		event.preventDefault();  

		var formData = new FormData($(this)[0]);   
		
		$.ajax({
			url: 'EditImage1',
			data: formData,
			type: 'POST',
			async: false,
			cache: false,
			contentType: false,
			processData: false,
			success: function(data) {
				$('#showImage1').removeAttr('src');
				var productId = $('#img3').val(); 
				window.setTimeout(function() {
					window.location.reload(true);
					$('#showImage1').attr('src', 'Image1FromProduct?productId='+productId);
				}, 0);
			}			
		});
		
		return false;
	});
	
	$("form#image2").submit(function(event) {  	 
		
		event.preventDefault();  

		var formData = new FormData($(this)[0]);   
		
		$.ajax({
			url: 'EditImage2',
			data: formData,
			type: 'POST',
			async: false,
			cache: false,
			contentType: false,
			processData: false,
			success: function(data) {
				$('#showImage2').removeAttr('src');
				var productId = $('#img5').val(); 
				window.setTimeout(function() {
					window.location.reload(true);
					$('#showImage2').attr('src', 'Image2FromProduct?productId='+productId);
				}, 0);
			}			
		});
		
		return false;
	});
	
	/*****************************************************************/
	
});



	
	
