
/********** Check Customer UserId From DataBase In Registration Page **********/

function CheckCustomerUserIdFromDB(user_id) { 
	
	document.getElementById('getUser_Id').innerHTML="<img src = 'images/loader1.gif'>";    
	
    
  
	
	$.ajax({
		
		type     : "POST",
		url      : "CheckCustomerUserIdFromDB",
		data     :  "user_id="+user_id,
		dataType : "html", 
		
		success  : function(response) {
			       
			       document.getElementById('CheckCustomerUserIdFromDB').innerHTML = response;		  
		},
		
		error    : function(e) {	
			        
			       document.getElementById('CheckCustomerUserIdFromDB').innerHTML = "<font color='red'>Technical Error... Please Try Again.</font>";
        }
		
	});
 }

