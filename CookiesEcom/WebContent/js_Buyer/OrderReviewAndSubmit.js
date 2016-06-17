$(function() {
	
	/******************************************************/
	
	var addressNotExists = $('#addressNotExists').html();
	if (addressNotExists == 'true') {
		
		$('#enterAddress').css('display', 'block');
		
		$('#changeLogin').attr('disabled','disabled');
		
		$('#orderSummary').css('display', 'block');
		$('#orderList').css('display', 'none');
		$('#orderSummary').fadeTo(0, 0.4);	
		
		$('#payment').fadeTo(0, 0.4);	
		$("#toPayment").attr('disabled','disabled');
				
		
	} else {
		
		$('#deliverySummary').fadeTo(0, 0.4);
	}
	
	/****************************************************/
	
	$('#loginSummary').fadeTo(0, 0.4);	
	
	$("#toPayment").removeAttr('disabled');
	$('#changeAddress').removeAttr('disabled');
	
	$('#continueInAddress').click(function() {
		
		$('#changeLogin').removeAttr('disabled');
		
		$('#deliveryAddress').css('display', 'none');
		$('#deliverySummary').css('display', 'block');
		
		$('#orderSummary').css('display', 'none');
		$('#orderList').css('display', 'block');
		
		$('#payment').fadeTo(0, 1);
		$("#toPayment").removeAttr('disabled');
	});
	
	$('#changeAddress').click(function() {
		
		/************* Get Delivery Address From Database ************/
		var userId = $('#userId').html();  
		
		$.getJSON( "GetDeliveryAddressCustomer", { userId: userId } )
		  .done(function( data ) {
		    
			  $('input[name=fName]')   .val(data.firstName);
			  $('input[name=lName]')   .val(data.lastName );
			  $('input[name=pincode]') .val(data.pin      );
			  $('textarea#address')    .val(data.address  );
			  $('textarea#address1')   .val(data.address1 );
			  $('input[name=city]')    .val(data.city     );
			  //$('input[name=state]')   .val(data.state    );
			  $('input[name=contact]') .val(data.contact  );	
			  $('input[name=contact]') .val(data.contact  );
			  $('input[name=contact]') .val(data.contact  );
			  
		  })
		  .fail(function() {
		   
		});
		
		
		
		/*************************************************************/
		
		$('#changeLogin').attr('disabled','disabled');
		
		$('#deliverySummary').css('display', 'none');
		$('#deliveryAddress').css('display', 'block');
		
		$('#orderSummary').css('display', 'block');
		$('#orderList').css('display', 'none');
		
		$('#payment').fadeTo(0, 0.4);
		$("#toPayment").attr('disabled','disabled');
	});
	
	$('#changeLogin').click(function() {
		
		$('#loginSummary').css('display', 'none');
		$('#loginChange').css('display', 'block');
		
		$('#orderSummary').css('display', 'block');
		$('#orderList').css('display', 'none');
		$('#orderSummary').fadeTo(0, 0.4);		
		
		$('#deliverySummary').css('display', 'block');
		$('#deliveryAddress').css('display', 'none');
		$('#deliverySummary').fadeTo(0, 0.4);
		$('#changeAddress').attr('disabled','disabled');
		
		$('#payment').fadeTo(0, 0.4);
		$("#toPayment").attr('disabled','disabled');
	});
	
	$('#continueLogin').click(function() {
		
		$('#loginSummary').css('display', 'block');
		$('#loginChange').css('display', 'none');
		$('#loginSummary').fadeTo(0, 0.4);
		
		$('#orderSummary').css('display', 'none');
		$('#orderList').css('display', 'block');
		
		$('#deliverySummary').css('display', 'block');
		$('#deliveryAddress').css('display', 'none');
		$('#changeAddress').removeAttr('disabled');
		
		$('#payment').fadeTo(0, 1);
		$("#toPayment").removeAttr('disabled');
	});
	
	
	
	
/******************************* Edit Address - Submit Form ***********************************************************/
	
	
	$("form#editAddress").submit(function(event) { 		
			
		  event.preventDefault();	 
		   
		  var formData = new FormData($(this)[0]);   
		 
		  $.ajax({
		    url: 'EditDeliveryAddress',
		    type: 'POST',
		    data: formData,
		    async: false,
		    cache: false,
		    contentType: false,
		    processData: false,
		    success: function (data) {	    	
		    	
		    	$("#fullName")    .html(data.fName + " " + data.lName  ); 
		    	$("#fullAddress") .html(data.address                   );  
		    	$("#fullAddress1").html(data.address1                  );
		    	$("#pinCity")     .html(data.pincode + ", " + data.city);  
		    	$("#fullState")   .html(data.state                     );  
		    	$("#fullContact") .html(data.contact                   );
		    	
		    	$("#dialog1").fadeOut(100);
		    	
		    	$('#name1').html(data.fName + " " + data.lName);
		    	$('#contact1').html(data.contact);
		    	$('#address1').html(data.address + " " + data.address1 + " " + data.city + " - " + data.pincode + ", " + data.state);
		    	
		    },
		  	error: function() {
		  		
		  	}
		  });			 
		 
		  return false;		
	
	});
	
	
/******************************* New Address - Submit Form ***********************************************************/
	
	
	$("form#newAddress").submit(function(event) { 		
			
		  event.preventDefault();	 
		   
		  var formData = new FormData($(this)[0]);   
		 
		  $.ajax({
		    url: 'NewDeliveryAddress',
		    type: 'POST',
		    data: formData,
		    async: false,
		    cache: false,
		    contentType: false,
		    processData: false,
		    success: function (data) {	    	
		    	
		    	//Place in Edit Adress
		    	$("#fullName")   .html(data.fName + " " + data.lName); 
		    	$("#fullAddress").html(data.address);  
		    	$("#fullAddress1").html(data.address1);
		    	$("#pinCity")    .html(data.pincode + ", " + data.city);  
		    	$("#fullState")  .html(data.state);  
		    	$("#fullContact").html(data.contact);
		    	//Place in Summary Address
		    	$('#name1').html(data.fName + " " + data.lName);
		    	$('#contact1').html(data.contact);
		    	$('#address1').html(data.address+' '+data.address1+' '+data.pincode + ", " + data.city+', '+data.state);
		    	
		    	$('#enterAddress')   .css('display', 'none' );
		    	$('#deliveryAddress').css('display', 'block');
		    	
		    },
		  	error: function() {
		  		
		  	}
		  });			 
		 
		  return false;		
	
	});
});










