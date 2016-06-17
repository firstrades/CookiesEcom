$(function() { 
	
	var stock1 = $('#stock').html();
	var stock  = parseInt(stock1);
	
	if (stock == 0) {
		
		$('#buyNow').on("click", function (e) {
	        e.preventDefault();	        
	    });
		$('#buyNow').removeClass('btn_form').addClass('btn_default_sd');
		
		
		$('#addToCart').on("click", function (e) {
	        e.preventDefault();	        
	    });
		$('#addToCart').removeClass('btn_form').addClass('btn_default_sd')
		$('#addToCart a').removeAttr("style");
		
	} // if	
	
	
	$('.checkBeforeSubmit').click(function(event) {    
		
		var noneBlock = $('#sizeBlock').css('display');		
		
		if (noneBlock === 'block') {   	
			
			var status = false;
			
			$('.checkStyleJ').each(function() {  // check if style contains background-color
				
				var color = $(this).css('background-color');   //alert(color);
				
				if(color === 'rgb(255, 165, 0)') {  // space is must in 'rgb'					
					status = true;	//break; not required as there is no else statement
				}				
				
			});	
			
			if (status == false)   { 
				event.preventDefault();
			}	
			
		} // main if
		
	});
	
	
	$('.size').click(function() { 		
		
		$('.size').each(function() { 
			
			$(this).css('background-color', 'white');
		});
		
		$(this).css('background-color', 'orange');
		
		
	});	
	
	$('#location').click(function() {
		
		var pincode = $('#pincode').val();
		$('#loader').css('display', 'block');
		$('#right').css('display', 'none');
		$('#cross').css('display', 'none');
		$('#msgLocation').css('display', 'none');
		$('#noNet').css('display', 'none');
		$('#error').css('display', 'none');
		
		$.getJSON('CheckPincode', {pincode: pincode} , function(data) {	
			
			if(data.status && !data.noNet && !data.error) {				
				$('#right').css('display', 'block');
				$('#cross').css('display', 'none');
				$('#loader').css('display', 'none');
			} else if (data.noNet && !data.error) {				
				$('#noNet').css('display', 'block');
				$('#right').css('display', 'none');
				$('#loader').css('display', 'none');
				$('#cross').css('display', 'block');
			} else if (data.error) {
				$('#error').css('display', 'block');
				$('#right').css('display', 'none');
				$('#loader').css('display', 'none');
				$('#cross').css('display', 'block');
			} else {				
				$('#cross').css('display', 'block');
				$('#right').css('display', 'none');
				$('#loader').css('display', 'none');
			}
			if (!data.notANumber) {
				$('#msgLocation').css('display', 'block');
			}
		});
		
	});
	
	$('#pincode').keyup(function() {
		
		$('#right').css('display', 'none');
		$('#cross').css('display', 'none');
		$('#msgLocation').css('display', 'none');
		$('#noNet').css('display', 'none');
	});
	
	
	
	
	
});





var application = angular.module('ProductDetailsApplication', []);

/********************** AdminPanel.js *********************************/
application.controller('ProductDetailsController', function($scope, $http, $window) {   	
	
	$scope.setSize = function(size) { 
		//$window.alert(size);
		$scope.size = size;
	};
	
	
});













	