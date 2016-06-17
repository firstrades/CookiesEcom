$(function() {
	
	/******************************* Select Category ******************************************************/
	
	/*$("#category").change(function() {
		var option = $(this).find('option:selected').val();		
		$.ajax({
				url: 'ProductCategoryList',
				type: 'POST',
				data: {value: option},
				success: function(response) {					
					$('#subCategory').find('option').remove();
					$('#subCategory').append(response);
				}
		});		
	});*/
	
	
	
	/******************************* Submit Form ***********************************************************/
	
	
	$("form#data").submit(function(event) { 
		
			//disable the default form submission
		  	event.preventDefault();
		  	
		  	$('#msg').empty();
		  	
		  	if ( $('#category').val() === 'null' || $('#subCategory').val() === 'null' ) {
		  		
		  		alert('You have not Selected any Category.');
		  		return false;
		  	}
		
			var r = confirm("Alert: Do You Really Want To Add This Product!");
			
			if (r == true) {  
		 
				  //grab all form data  
				  var formData = new FormData($(this)[0]);   
				 
				  $.ajax({
				    url: 'CreateProduct',
				    type: 'POST',
				    data: formData,
				    async: false,
				    cache: false,
				    contentType: false,
				    processData: false,
				    success: function (returndata) {
				    	$('#msg').empty();
				    	$('#msg').append(returndata);
				    },
				  	error: function() {
				  		$('#msg').empty();
				  		$('#msg').append("Product Not Added.");
				  	}
				  });
			} 
		 
		  return false;
		
	
	});
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*************************** Discount - Calculation  *****************************************************************/	
	
	$('#ListPrice').keyup(function(e) {  
		
		var listPrice = $(this).val();		
		var numeric = $.isNumeric( listPrice );  
		var salePriceToAdmin  = $('#salePriceToAdmin').val();			
		var discount = ((parseInt(listPrice) - parseInt(salePriceToAdmin)) / parseInt(listPrice)) * 100;
		
		if (numeric == true) {
		
			$(this).css({"background-color": "#9EE69E"});
			$('#discount').val(discount);
			
		} else {
			
			$(this).css({"background-color": "#E89D9D"});
		}
	});
	
	
	
	/*************************** Profit Margin  And Sale Price to Admin - Calculation *****************************************************************/	
	
	$('#profitMarginPercentage').keyup(function(e) { 
		
		var profitMarginPercentage = $(this).val();		
		var numeric = $.isNumeric( profitMarginPercentage );  
		var manufacturingCost  = $('#manufacturingCost').val();
		var salePriceToAdmin = parseInt(manufacturingCost) + (parseInt(manufacturingCost) * (parseInt(profitMarginPercentage) / 100));
		var profitMargin = parseInt(manufacturingCost) * (parseInt(profitMarginPercentage) / 100);
		
		
		if (numeric == true) {
		
			$(this).css({"background-color": "#9EE69E"});
			$('#profitMargin').val(profitMargin);
			$('#salePriceToAdmin').val(salePriceToAdmin);
			
		} else {
			
			$(this).css({"background-color": "#E89D9D"});
		}
	});
	
	
	/*************************************************************************************/
	/**********************************  Basic Features  *********************************/
	/*************************************************************************************/
	
	
	$('#manufacturingCost1').keyup(function(e) {  
		
		var manufacturingCost1      = parseInt( $(this).val() );
		var profitMarginPercentage1 = parseInt( $('#profitMarginPercentage1').val() );
		var markupPercentage1       = parseInt( $('#markupPercentage1').val() );
		var listPrice1              = parseInt( $('#listPrice1').val() );
		
		var numeric = $.isNumeric( manufacturingCost1 );  
		
		var profitMargin         = Math.ceil( manufacturingCost1 * ( profitMarginPercentage1 / 100 ) );
		var salePriceToAdmin1    = manufacturingCost1 + profitMargin;
		var salePriceToCustomer1 = Math.ceil( salePriceToAdmin1 * ( 1 + markupPercentage1 / 100 ) );
		var discount1            = ( ( listPrice1 - salePriceToAdmin1 ) / listPrice1 ) * 100;
		
		
		if (numeric == true) {
		
			$(this).css({"background-color": "#9EE69E"});
			
			$('#profitMargin1').val(profitMargin);
			$('#salePriceToAdmin1').val(salePriceToAdmin1);
			$('#salePriceToCustomer1').val(salePriceToCustomer1);
			$('#discount1').val(Math.floor(discount1));
			
		} else {
			
			$(this).css({"background-color": "#E89D9D"});
		}
	});
	
	
	$('#profitMarginPercentage1').keyup(function(e) {  
		
		var profitMarginPercentage1 = parseInt( $(this).val() );
		var manufacturingCost1      = parseInt( $('#manufacturingCost1').val() );
		var markupPercentage1       = parseInt( $('#markupPercentage1').val() );
		var listPrice1              = parseInt( $('#listPrice1').val() );
		
		var numeric = $.isNumeric( profitMarginPercentage1 );  
		
		var profitMargin         = Math.ceil( manufacturingCost1 * ( profitMarginPercentage1 / 100 ) );
		var salePriceToAdmin1    = manufacturingCost1 + profitMargin;
		var salePriceToCustomer1 = Math.ceil( salePriceToAdmin1 * ( 1 + markupPercentage1 / 100 ) );
		var discount1            = ( ( listPrice1 - salePriceToAdmin1 ) / listPrice1 ) * 100;
		
		
		if (numeric == true) {
		
			$(this).css({"background-color": "#9EE69E"});
			
			$('#profitMargin1').val(profitMargin);
			$('#salePriceToAdmin1').val(salePriceToAdmin1);
			$('#salePriceToCustomer1').val(salePriceToCustomer1);
			$('#discount1').val(Math.floor(discount1));
			
		} else {
			
			$(this).css({"background-color": "#E89D9D"});
		}
	});
	
	$('#listPrice1').keyup(function(e) {  
		
		var listPrice1 = parseInt( $(this).val() );
		var manufacturingCost1      = parseInt( $('#manufacturingCost1').val()      );
		var markupPercentage1       = parseInt( $('#markupPercentage1').val()       );
		var profitMarginPercentage1 = parseInt( $('#profitMarginPercentage1').val() );
		
		var numeric = $.isNumeric( listPrice1 ); 
		
		var profitMargin         = Math.ceil( manufacturingCost1 * ( profitMarginPercentage1 / 100 ) );
		var salePriceToAdmin1    = manufacturingCost1 + profitMargin;
		var salePriceToCustomer1 = Math.ceil( salePriceToAdmin1 * ( 1 + markupPercentage1 / 100 ) );
		var discount1            = ( ( listPrice1 - salePriceToAdmin1 ) / listPrice1 ) * 100;
		
		if (numeric == true) {
			
			$(this).css({"background-color": "#9EE69E"});
			
			$('#discount1').val(Math.floor(discount1));
			
		} else {
			
			$(this).css({"background-color": "#E89D9D"});
		}
		
	});
	
});


/**********************************    Angular   *************************************/

var admin = angular.module('ProductAdd', []);

admin.controller('ProductController', function($scope, $http, $window) { 
	
	$http.post('RetrieveCategoryList', {}).success(function(items) {		
		$scope.categoryItems = items;
	});
	
	
	$scope.getSubCategories = function() { 
		
		$scope.addSubCategoryMessage = null;
		$scope.deleteSubCategoryMessage = null;
		
		//$window.alert($scope.category2.id);
		
		var keyValue = JSON.stringify({categoryId: $scope.category2.id});
		
		$http.post('RetrieveSubCategoryList', keyValue, {headers: {'Content-Type': 'application/json'} }).success(function(items) {			
			$scope.subCategoryItems = items;
		});
	};
	
	$scope.writeCategory = function() {		
		
		$scope.categoryItem = $scope.category2.category;
	};
	
	$scope.writeSubCategory = function() {
		//$window.alert();
		$scope.subCategoryItem = $scope.deleteSubCategory.subSategory;
	};
	
	
});

