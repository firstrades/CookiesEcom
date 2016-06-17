var franchise = angular.module('franchise', []);

/********************** FranchisePanel.js *********************************/

franchise.controller('ViewController', function($scope, $http, $window) {  
	
	$scope.dashboard = true;	
	
	$scope.getPinAreaCommission = function(user_id) { 		
		
		$scope.hideAll();
		$scope.pinAreaCommission = true;
		
		var keyValue = JSON.stringify({user_id: user_id});
		
		$http.post('RetrievePinAreaCommission', keyValue, {headers: {'Content-Type': 'application/json'} }).success(function(data) {
			
			$scope.items = data.items;
			
			$scope.pin1  = data.pin1;   
			$scope.pin2  = data.pin2;
			$scope.pin3  = data.pin3;
			$scope.pin4  = data.pin4;
			$scope.pin5  = data.pin5;
		});
		
		
	};		
	
	$scope.hideAll = function() {   
		
		$scope.pinAreaCommission = false;
		$scope.dashboard = false;
		
	};
	
	
	$scope.redirectToDistributorRegistration = function() {
		
		$window.open('DistributorRegistration', '_blank');
	};
	
	
}) ;



franchise.controller('SetPinAreaCommissionController', function($scope, $http, $window) { 
	
	$scope.setPin = function() {	
		
		var keyValue = JSON.stringify({d_id: $scope.item.id, pin: $scope.savePin});
		
		$http.post('SavePin', keyValue, {headers: {'Content-Type': 'application/json'} }).success(function(data) {
			
			if (data.message) {
				
				$scope.item.pin = data.updatedPin;
				$scope.message1 = data.message;
			}
		});
	};
	
	$scope.setArea = function() {  
		
		var keyValue = JSON.stringify({d_id: $scope.item.id, area: $scope.item.area});
		
		$http.post('SaveArea', keyValue, {headers: {'Content-Type': 'application/json'} }).success(function(data) {
			
			if (data.message) {
				
				$scope.item.area = data.updatedArea;     
				$scope.message1  = data.message;
			}
		});
	};
	
	$scope.setCommission = function() {  
		
		var keyValue = JSON.stringify({d_id: $scope.item.id, commission: $scope.item.commission});
		
		$http.post('SaveCommission', keyValue, {headers: {'Content-Type': 'application/json'} }).success(function(data) {
			
			if (data.message) {
				
				$scope.item.commission = data.updatedCommission;
				$scope.message1        = data.message;
			}
		});
	};
	
	$scope.setAdditionalBalance = function() {
		
		var keyValue = JSON.stringify({addtionalBalance: $scope.addtionalBalance, id: $scope.item.id});
		
		$http.post('DistributorAdditionalBalance', keyValue, {headers: {'Content-Type': 'application/json'} }).success( function(data) {						
			
			if (data.success) {
				
				$scope.message1      = data.success;   
				$scope.item.balance  = data.balance;  
				
			} else {
				
				$scope.message1 = data.failed;	
			}
		});	
	};
	
});

/************************ Registration For Franchise *****************************/


franchise.controller('CreateEditUserController', function($scope, $http, $window) {  
	
	
	$scope.userId = "1"; $scope.password = "2"; $scope.fName = "3"; $scope.lName = "4"; $scope.sex = "Male"; 
	$scope.company = "6"; $scope.mobile1 = "7"; $scope.mobile2 = "8"; $scope.email1 = "9"; $scope.email2 = "10"; $scope.phone1 = "11";
	$scope.phone2 = "12"; $scope.fax1 = "13"; $scope.fax2 = "14"; $scope.address = "15"; $scope.city = "16"; $scope.state = "17";
	$scope.pin = "18"; $scope.pan = "19"; $scope.voterId = "20", $scope.chooseArea = "Dumdum";
	
	$scope.createDistributor = function() {
		
		var myEl = angular.element( document.querySelector( '#message' ) );
		myEl.empty(); 
		
		var r = $window.confirm("Alert: Do You Want To Add this user as Franchise!");
		
		if (r == true) { 
			
			var keyValue = JSON.stringify({userId: $scope.userId, password: $scope.password, fName: $scope.fName, lName: $scope.lName, sex: $scope.sex, 
				company: $scope.company, mobile1: $scope.mobile1, mobile2: $scope.mobile2, email1: $scope.email1, email2: $scope.email2, phone1: $scope.phone1,
				phone2: $scope.phone2, fax1: $scope.fax1, fax2: $scope.fax2, address: $scope.address, city: $scope.city, state: $scope.state,
				pin: $scope.pin, pan: $scope.pan, voterId: $scope.voterId, choosePin: $scope.choosePin, area: $scope.chooseArea});
			
			$http.post('AddDistributor', keyValue, {headers: {'Content-Type': 'application/json'} }).success( function(data) {						
				
				if (data.message) {
					
					myEl.html(data.message);					
				}
			});
		} // if close
	};
	
});  // CreateEditUserController