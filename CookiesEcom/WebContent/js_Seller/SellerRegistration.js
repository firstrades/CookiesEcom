/******************************** Seller Registration Submit Form ********************************/
$(function() { 		  		
	
	  $('#button').click(function() {
		  
				/*var First_name = $.trim($("#first_name").val()); alert ("MMM"+First_name);
				var Last_name = null; 

			 	var checkbox = $("#checkbox");  
				alert(checkbox.val());
	  		    if (checkbox.val() == 'on') {   
	  		    	Last_name  = $.trim($("#last_name").val());
	  		    }
	  		  else {
	  			Last_name  = $.trim($("#first_name").val());
	  			
	  		  }
	  		    
		  		  var data = {fName1: First_name, lName1: Last_name};
		  		  alert(First_name+":::"+Last_name);*/

		       var checkbox = document.querySelectorAll('input[name = "' + checkbox + '"]:checked'), values = [];
		           Array.prototype.forEach.call(checkbox, function(el) {
		           values.push(el.value);
		  });
		      
		        var count = $("[type='checkbox']:checked").length;
		        
		        
			    var User_Id        = $.trim($("#user_id")     .val()); 
			    var Password       = $.trim($("#password")    .val());
			    var First_Name1     = $.trim($("#first_name1").val());
			    var Last_Name1      = $.trim($("#last_name1") .val());
			    var Gender         = $.trim($("#sex")         .val());
			    var Company1        = $.trim($("#company1")   .val());
			    var Mobile_Number1 = $.trim($("#mobile1")     .val());
			    var Mobile_Number2 = $.trim($("#mobile2")     .val());
			    var Email1         = $.trim($("#email1")      .val());
			    var Email2         = $.trim($("#email2")      .val());
			    var Landphone1     = $.trim($("#landphone1")  .val());
			    var Landphone2     = $.trim($("#landphone2")  .val());
			    var Fax1           = $.trim($("#fax1")        .val());
			    var Fax2           = $.trim($("#fax2")        .val());
			    var Address_Line1  = $.trim($("#address1")    .val());
			    var Address_Line2  = $.trim($("#address2")    .val());
			    var City           = $.trim($("#city")        .val());
			    var State          = $.trim($("#state1")      .val());
			    var Pin            = $.trim($("#pin")         .val());
			    var Country        = $.trim($("#country1")    .val());
			    var Pancard        = $.trim($("#pancard")     .val());
			    var VoterId        = $.trim($("#voterId")     .val());
			    
			//	var First_name = $.trim($("#first_name").val());
			//	var Last_name = null;
				
				var First_Name2    = null;
				var Last_Name2     = null;
				var Company2       = null;
				var Mobile_Number3 = null;
				var Address_Line3  = null;
			    var Address_Line4  = null;
				var City2          = null;
				var Pin2           = null;
				var State2         = null;
				var Country2       = null;
				var Email3         = null;
				   
				   
	  		  	var checkbox = $("#checkbox");  
	  		     if (count == 1) { 
	  		   
	  		//    if (checkbox.val() == 'on') {    		 
	                
	  		    	First_Name2     = $.trim($("#first_name2").val());
	  		    	Last_Name2      = $.trim($("#last_name2") .val());
	  		    	Company2        = $.trim($("#company2")   .val());
	  		    	Mobile_Number3  = $.trim($("#mobile3")    .val());
	  		    	Address_Line3   = $.trim($("#address3")   .val());
	  		    	Address_Line4   = $.trim($("#address4")   .val());
	  		    	City2           = $.trim($("#city2")      .val());
	  		    	Pin2            = $.trim($("#pin2")       .val());
	  		    	State2          = $.trim($("#state2")     .val());
	  		    	Country2        = $.trim($("#country2")   .val());
	  		    	Email3          = $.trim($("#email3")     .val());
	  		    	
	  		    //	 Last_name  = $.trim($("#last_name").val());
	
	  		    }
		  			
		  		else {
		  			
		  			First_Name2     = $.trim($("#first_name1").val());
	  		    	Last_Name2      = $.trim($("#last_name1") .val());
	  		    	Company2        = $.trim($("#company1")   .val());
	  		    	Mobile_Number3  = $.trim($("#mobile1")    .val());
	  		    	Address_Line3   = $.trim($("#address1")   .val());
	  		    	Address_Line4   = $.trim($("#address1")   .val());
	  		    	City2           = $.trim($("#city")       .val());
	  		    	Pin2            = $.trim($("#pin")        .val());
	  		    	State2          = $.trim($("#state1")     .val());
	  		    	Country2        = $.trim($("#country1")   .val());
	  		    	Email3          = $.trim($("#email1")     .val());
	  		    	
		  		// Last_name  = $.trim($("#first_name").val());

		  		}
		  		    
		  		alert (" USER_ID "+User_Id+" PASSWORD "+Password+" FIRST_NAME1 "+First_Name1+" LAST_NAME1 "+Last_Name1+" COMPANY1 "+Company1+
		  				" FIRST_NAME2 "+First_Name2+" LAST_NAME2 "+Last_Name2+" COMPANY2 "+Company2);
	  		    
		  		var data1 = {userId: User_Id, paSSworD: Password, fName1: First_Name1, lName1: Last_Name1, seX: Gender, companY1: Company1,
		  				    mobileNumber1: Mobile_Number1, mobileNumber2: Mobile_Number2, emaiL1: Email1, emaiL2: Email2, landPhonE1: Landphone1,
		  				    landPhonE2: Landphone2, faX1: Fax1, faX2: Fax2, addressLine1: Address_Line1, addressLine2: Address_Line2, citY1: City, 
		  				    statE1: State, piN1: Pin, countrY1: Country, pancarD: Pancard, voterID: VoterId,
		  				    
		  				    fName2: First_Name2, lName2: Last_Name2, companY2: Company2, mobileNumber3: Mobile_Number3, addressLine3: Address_Line3, 
		  				    addressLine4: Address_Line4, citY2: City2, piN2: Pin2, statE2: State2, countrY2: Country2, emaiL3: Email3};
		  		
		  	//	var data = {fNam: First_name };
		  		
		  		$.ajax({
		  			
		  			type     : "POST",
		  			url      : "RegisterSeller",
		  			data     : data1,
		  			dataType : "html",
		  			success  : function(response) {
		  				document.getElementById('button').innerHTML=response;
		  			},
		  			error: function(e) {
		  				document.getElementById('button').innerHTML="<font color='red'>Technical Error.Try again..</font>";
		  	        }
		  		});

	  	});
	
		
		$('#checkbox').change(function() {
			// alert("demo");
			var display = $('#aux').css('display');  
			
			if (display == 'none')
				$('#aux').css('display', 'block'); 
			else
				$('#aux').css('display', 'none'); 
		});
	
   });