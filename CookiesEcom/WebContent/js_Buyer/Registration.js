

/**********getUser_Id From Registration Page**********/

function getUser_Id(user_id) { 
	
	document.getElementById('getUser_Id').innerHTML="<img src='images/loader.gif'>";    
	
	$.ajax({
		
		type: "POST",
		url: "CheckUserId",
		data: "user_id="+user_id,
		dataType: "html",
		
		success: function(response) {
			
			document.getElementById('getUser_Id').innerHTML=response;		  
		},
		
		error: function(e) {			
			document.getElementById('getUser_Id').innerHTML="<font color='red'>Technical Error... Please Try Again.</font>";
        }
	});
 }