var admin = angular.module('admin', []);

/********************** AdminPanel.js *********************************/

admin.controller('ViewController', function($scope, $http, $window) {  
	
	
	$scope.dashboard = true;	
	
	$scope.approveProductList = function() { 
		
		//$window.location.reload();
		
		$scope.hideAll();
		$scope.productApproval = true;
		$scope.productApprovalNoData = true;
		
		$http.post('RetrieveProductForApproval', {}).success(function(data) {
			
			//$window.alert(data.items);			
			
			if (data.items != '') {
				$scope.items = data.items; 
			}
			else {
				$scope.noData = 'No Products for approval.';
				$scope.productApprovalNoData = false;
			}
		});
		
		
	};	
	
	$scope.statusForBookedProducts = function() { 		
		
		$scope.hideAll();
		$scope.bookedProductsStatus = true;
		
		$http.post('RetrieveOrderedItemsForAdmin', {}).success(function(data) {
			
			$scope.bookedProducts = data.items;			
			
		});
		
		
		
	};	
	
	$scope.getPinCommission = function() { 		
		
		$scope.hideAll();
		$scope.editFranchise = true;
		
		$http.post('RetrievePinCommission', {}).success(function(data) {
			
			$scope.pinCommissions = data.items;
		});
		
		
	};	
	
	
	$scope.newSellerApproval = function() {
		
		$scope.hideAll();
		$scope.approveSeller = true;
		
		$http.post('RetrieveAllSellerForApprovalRegistration', {}).success(function(data) {
			
			$scope.items = data.items;
		});
	};
	
	$scope.alterCategory = function() { //$window.alert('j');
		
		$scope.hideAll();
		$scope.addACategory = true;
		
		$http.post('RetrieveCategoryList', {}).success(function(items) {
			
			$scope.categoryItems = items;
		});
	};
	
	
	$scope.deleteCategoryMethod = function() { 
		
		$scope.deleteCategoryMessage = null;
		$scope.categoryMessage = null;
		
		//$window.alert($scope.deleteCategory.category);
		
		var r = $window.confirm("Alert: Do You Want To Delete this category!");
		
		if (r == true) { 
		
			var keyValue = JSON.stringify({id: $scope.deleteCategory.id});
			
			$http.post('deleteCategory', keyValue, {headers: {'Content-Type': 'application/json'} }).success( function(data) {			
					
				$scope.deleteCategoryMessage = data.message;	
				
				if (data.array)
					$scope.categoryItems = data.array;
				
			});	
		
		}//if
	};
	
	$scope.addCategoryMethod = function() { 
		
		$scope.deleteCategoryMessage = null;
		$scope.categoryMessage = null;
		
		var r = $window.confirm("Alert: Do You Want To Add This Category!");
		
		if (r == true) { 
		
			var keyValue = JSON.stringify({category: $scope.category});
			
			$http.post('AddCategory', keyValue, {headers: {'Content-Type': 'application/json'} }).success( function(data) {			
					
				$scope.categoryMessage = data.message;	
				
				if (data.array)
					$scope.categoryItems = data.array;
				
			});	
		
		}//if
	};
	
	$scope.alterSubCategory = function() { 
		
		$scope.addSubCategoryMessage = null;
		$scope.deleteSubCategoryMessage = null;
		
		$scope.hideAll();
		$scope.addSubCategory = true;		
		
		$http.post('RetrieveCategoryList', {}).success(function(items) {			
			$scope.categoryItems = items;
		});
	};
	
	$scope.getSubCategories = function() { 
		
		$scope.addSubCategoryMessage = null;
		$scope.deleteSubCategoryMessage = null;
		
		//$window.alert($scope.category2.id);
		
		var keyValue = JSON.stringify({categoryId: $scope.category2.id});
		
		$http.post('RetrieveSubCategoryList', keyValue, {headers: {'Content-Type': 'application/json'} }).success(function(items) {			
			$scope.subCategoryItems = items;
		});
	};
	
	$scope.deleteSubCategoryMethod = function() {
		
		$scope.addSubCategoryMessage = null;
		$scope.deleteSubCategoryMessage = null;
		
		//$window.alert($scope.deleteSubCategory.id);
		var r = $window.confirm("Alert: Do You Want To Delete This SubCategory!");
		
		if (r == true) {
		
			var keyValue = JSON.stringify({categoryId: $scope.category2.id, subCategoryId: $scope.deleteSubCategory.id});
			
			$http.post('deleteASubCategory', keyValue, {headers: {'Content-Type': 'application/json'} }).success(function(items) {			
				$scope.subCategoryItems = items;
				$scope.deleteSubCategoryMessage = 'Sub Category deleted.';
			});
		
		}//if
		
	};
	
	$scope.setNullToStrings = function() {
		$scope.addSubCategoryMessage = null;
		$scope.deleteSubCategoryMessage = null;
	}
	
	$scope.addSubCategoryMethod = function() {
		
		$scope.addSubCategoryMessage = null;
		$scope.deleteSubCategoryMessage = null;
		
		//$window.alert($scope.category1.id + ' ' + $scope.addASubCategory);	
		
		var r = $window.confirm("Alert: Do You Want To Add This SubCategory!");
		
		if (r == true) {
		
			var keyValue = JSON.stringify({categoryId: $scope.category1.id, subCategory: $scope.addASubCategory});
			
			$http.post('addASubCategory', keyValue, {headers: {'Content-Type': 'application/json'} }).success(function(data) {			
				
				$scope.addSubCategoryMessage = data.response;
			});
		
		}//if
		
	};
	
	
	
	
								/***************************************************************************/
	
	$scope.hideAll = function() {   		
		$scope.productApproval = false;
		$scope.dashboard = false;
		$scope.editFranchise = false;
		$scope.bookedProductsStatus = false;
		$scope.approveSeller = false;
		$scope.addACategory = false;
		$scope.addSubCategory = false;
	};
	
	$scope.redirectToUserRegistration = function() {
		
		$window.open('FranchiseRegistration', '_blank');
	};
	
	$scope.redirectToOfferedProductsSelection = function() {
		
		$window.open('OfferedProductsSelection', '_blank');
	};
	
	$scope.approveSellerRegistrationPage = function(id) {   
		
		$window.open('ApproveSellerRegistrationPage?id=' + id);  //jsp_Administration/ApproveSellerRegistration.jsp
	};
	
}) ;

admin.controller('DeliveredController', function($scope, $http, $window) {
	
	$scope.payment   = false;
	$scope.delivered = false;
	
	$scope.checkDelivery = function(deliveredDate) {		
		
		var dateParts = deliveredDate.split("-"); 
		
		var month = parseInt(dateParts[1]) - 1;
		
		var dateDeliveredOn = new Date(dateParts[0], month, dateParts[2]);		
		
		var currentDate = new Date();		
		
		if (dateDeliveredOn < currentDate) { 
			$scope.payment = true;
		}
		else
			$scope.delivered = true;
	};
	
	
});

admin.controller('SetPinCommissionController', function($scope, $http, $window) {
	
	$scope.setPin = function(position) {	
		
		//$scope.emptyMessage();
		
		var r = $window.confirm("Alert: Do You Want To Set This Pin!");
		
		if (r == true) { 
		
			if (position == 0) {			
				pin = $scope.item.pin1;
			} else if (position == 1) {			
				pin = $scope.item.pin2;
			} else if (position == 2) {			
				pin = $scope.item.pin3;
			} else if (position == 3) {			
				pin = $scope.item.pin4;
			} else if (position == 4) {		
				pin = $scope.item.pin5;
			}		
			
			//$window.alert($scope.item.id);
				
			var keyValue = JSON.stringify({position: position, pin: pin, id: $scope.item.id});
			
			$http.post('SetPin', keyValue, {headers: {'Content-Type': 'application/json'} }).success( function(data) {						
				
				if (data.success) {
					
					$scope.message1 = data.success;	
					
				} else {
					
					$scope.message1 = data.failed;	
				}
			});	
			
		} // if
		
	};  // setPin
	
	$scope.setCommission = function() {		
		
		//$window.alert($scope.item.id);
			
		var keyValue = JSON.stringify({commission: $scope.item.commission, id: $scope.item.id});
		
		$http.post('SetCommission', keyValue, {headers: {'Content-Type': 'application/json'} }).success( function(data) {						
			
			if (data.success) {
				
				$scope.message1 = data.success;	
				
			} else {
				
				$scope.message1 = data.failed;	
			}
		});			
		
	};  // set
	
	$scope.setAdditionalBalance = function() {
		
		var keyValue = JSON.stringify({addtionalBalance: $scope.addtionalBalance, id: $scope.item.id});
		
		$http.post('FranchiseAdditionalBalance', keyValue, {headers: {'Content-Type': 'application/json'} }).success( function(data) {						
			
			if (data.success) {
				
				$scope.message1      = data.success;
				$scope.item.balance  = data.balance;
				
			} else {
				
				$scope.message1 = data.failed;	
			}
		});		
	};
});

admin.directive('ngRemoveItem', function($http, $window) {	
	
	return {
		
		link: function(scope, element, attr) {
		
			scope.approveProduct = function() {
				
				var r = $window.confirm("Alert: Do You Want To Approve This Product!");
				
				if (r == true) { 
				
					var productId              = scope.item.productId;
					
					var discount               = scope.item.discount;
					var salePriceToCustomer    = scope.item.salePriceCustomer;					
					var markupPercentage       = scope.item.markupPercentage;
					var salePriceToAdmin       = scope.item.salePriceToAdmin
					var profitMarginPercentage = scope.item.profitMarginPercentage;	
					
					var franComm               = scope.item.fCommissionPercentage;
					var drisComm               = scope.item.dCommissionPercentage;
					
					var weight                 = scope.item.weight;
					var warranty               = scope.item.warranty;
					var cancellationAfterBooked = scope.item.calcellationAfterBookedInDays;
					
					var keyValue = JSON.stringify({productId: productId, discount: discount, salePriceToCustomer: salePriceToCustomer,
						markupPercentage: markupPercentage, salePriceToAdmin: salePriceToAdmin,
						profitMarginPercentage: profitMarginPercentage, franComm: franComm, drisComm: drisComm,
						weight: weight, warranty: warranty, cancellationAfterBooked: cancellationAfterBooked});
					
					$http.post('ApproveProduct', keyValue, {headers: {'Content-Type': 'application/json'} }).success( function(data) {						
						
						if (data.success) {
							
							element.remove();
						}
					});
					
				}
			};
		} // link close
	};  // return close
	
});



/************************ Registration For Franchise *****************************/


admin.controller('CreateEditUserController', function($scope, $http, $window) {  

	//$scope.message = "";
	
	$scope.userId = "1"; $scope.password = "2"; $scope.fName = "3"; $scope.lName = "4"; $scope.sex = "Male"; 
	$scope.company = "6"; $scope.mobile1 = "7"; $scope.mobile2 = "8"; $scope.email1 = "9"; $scope.email2 = "10"; $scope.phone1 = "11";
	$scope.phone2 = "12"; $scope.fax1 = "13"; $scope.fax2 = "14"; $scope.address = "15"; $scope.city = "16"; $scope.state = "17";
	$scope.pin = "18"; $scope.pan = "19"; $scope.voterId = "20";
	
	$scope.createFranchise = function() {
		
		var myEl = angular.element( document.querySelector( '#message' ) );
		myEl.empty(); 
		
		var r = $window.confirm("Alert: Do You Want To Add this user as Franchise!");
		
		if (r == true) { 
			
			var keyValue = JSON.stringify({userId: $scope.userId, password: $scope.password, fName: $scope.fName, lName: $scope.lName, sex: $scope.sex, 
				company: $scope.company, mobile1: $scope.mobile1, mobile2: $scope.mobile2, email1: $scope.email1, email2: $scope.email2, phone1: $scope.phone1,
				phone2: $scope.phone2, fax1: $scope.fax1, fax2: $scope.fax2, address: $scope.address, city: $scope.city, state: $scope.state,
				pin: $scope.pin, pan: $scope.pan, voterId: $scope.voterId});
			
			$http.post('AddFranchise', keyValue, {headers: {'Content-Type': 'application/json'} }).success( function(data) {						
				
				if (data.message) {
					
					myEl.html(data.message);					
				}
			});
		} // if close
	};
	
});  // CreateEditUserController



/************************ Offered Hot Selection *****************************/

admin.controller('OfferedHotSelection', function($scope, $http, $window) {
	
	
	
}); //OfferedHotSelection

/***************************    jQuery   *******************************/

$(function() {
	
	$("form#data").submit(function(event) { 
		
		//disable the default form submission
	  	event.preventDefault();	  	
	  	
	  	$('#message').empty();
	
		var r = confirm("Alert: Do You Really Want To Approve The Seller !");
		
		if (r == true) {  
	 
			  //grab all form data  
			  var formData = new FormData($(this)[0]);   
			 
			  $.ajax({
			    url: 'ApproveSeller',
			    type: 'POST',
			    data: formData,
			    async: false,
			    cache: false,
			    contentType: false,
			    processData: false,
			    success: function (data) {   
			    	$('#message').empty();
			    	
			    	if (data.status)
			    		$('#message').append("Seller Approved.");
			    },
			  	error: function() {
			  		$('#message').empty();
			  		$('#message').append("Seller Not Approved.");
			  	}
			  });
		} 
	 
	  return false;
	

	});
	
	
	
	
});

//var discount = ((parseInt(listPrice) - parseInt(salePriceToAdmin)) / parseInt(listPrice)) * 100;


