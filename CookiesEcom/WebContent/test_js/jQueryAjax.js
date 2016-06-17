
	/******************************** Seller Registration Submit Form ********************************/
$(function() {
	
	  $('#button').click(function() {
		  
			var First_name = $.trim($("#first_name").val());
			
		    var Last_name = null;
			
		  		  var Checkbox = $.trim($(this).val());

		  		    if (Checkbox == true) {
		  		    	
		  		    	 Last_name  = $.trim($("#last_name").val());

		  		    }
		  			

		  		else{
		  			
		  			 Last_name  = $.trim($("#first_name").val());
 
		  		}
		  		    
		  		alert (" first_name "+First_name+" last_name "+Last_name);

  });
	
	$.ajax({
		
		type     : "POST",
		url      : "User",
		data     : "checkbox="+Checkbox+"&first_name="+First_name+"&last_name="+Last_name,
		dataType : "html",
		success  : function(response) {
			document.getElementById('button').innerHTML=response;
		},
		error: function(e) {
			document.getElementById('button').innerHTML="<font color='red'>Technical Error.Try again..</font>";
        }
	});
});