var application = angular.module('Jewel', []);

application.controller('LoopController', function($scope, $http, $window) {	
		
	
	/************ Generate TrackNumber COD **********/
	$scope.generateTrackNumberCOD = function(orderTableId) {	
		
		$scope.loader = true;
		$scope.msg1   = '';
		
		var data = $.param ({
			orderTableId: orderTableId
		});
		
		var config = {
                headers : {
                    'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
                }
        }
		
		$http.post('GenerateTrackNumberCOD', data, config).success(function(data) {			
			
			if (data.pickup) {				
				$scope.trackNumberCOD = false;
				$scope.pickupLabelCOD = true;
				$scope.loader = false;
			} 
			else {  
				$scope.loader = false;
				$scope.msg1 = "Error Occured!";
			}
		});	
		
	};
	
	
	/************ Generate TrackNumber Bank **********/
	$scope.generateTrackNumberBANK = function(orderTableId) {
		
		$scope.loader = true;
		
		var data = $.param ({
			orderTableId: orderTableId
		});
		
		var config = {
                headers : {
                    'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8;'
                }
        }
		
		$http.post('GenerateTrackNumberBANK', data, config).success(function(data) {
			
			if (data.pickup) {				
				$scope.trackNumberBANK = false;
				$scope.pickupLabelBANK = true;
				$scope.loader = false;
			}
		});	
		
	};
	
	
	/************ Change State Picked **********/
	$scope.date = '12-02-2016';	
	$scope.changeStateToPicked = function(orderTableId) {	 

		var date        = $scope.date;          
		var courierName = $scope.courierName;   
		
		var data = $.param ({
			orderTableId: orderTableId,
			date        : date,
			courierName : courierName
		});
		
		var config = {
                headers : {
                    'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8;'
                }
        }
		
		$http.post('SetPickedUp', data, config).success(function(data) {
			
			if (data.picked) {				
				
				$scope.trackParcel     = true;
				$scope.trackNumberBANK = false;
				$scope.trackNumberCOD  = false;
				$scope.pickupLabelBANK = false;				
				$scope.pickupLabelCOD  = false;
			}
		});	
		
		
	};
	
	/*********** Tracking Details ****************/
	$scope.trackingDetails = function(orderTableId) {		
		
		$scope.loader1 = true;
		$scope.trackDetails = "";
		
		var data = $.param ({
			orderTableId: orderTableId			
		});
		
		var config = {
                headers : {
                    'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8;'
                }
        }
		
		$http.post('GetTrackingDetails', data, config).success(function(data) {
			
			if (data.picked) {				
				
				$scope.trackDetails = "Hai, no details yet....";
				
				$scope.loader1 = false;
			}
		});	
	};
	
	
	/********** Generate PDF **************/
	$scope.generatePDF = function(orderTableId) {
		
		var data = $.param ({
			orderTableId: orderTableId			
		});
		
		var config = {
                headers : {
                    'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8;'
                }
        }
		
		$http.post('GeneratePDF', data, config).success(function(data) {
			
			if (data.base64) {				
				
				var unicode = $window.atob(data.base64);   
				
				$window.open("data:application/pdf," + escape(unicode));
			}
		});	
	};
	
	
	/********** Generate PDF Return **************/
	$scope.generatePDFRet = function(orderTableId) {
		
		var data = $.param ({
			orderTableId: orderTableId			
		});
		
		var config = {
                headers : {
                    'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8;'
                }
        }
		
		$http.post('GeneratePDFRet', data, config).success(function(data) {
			
			if (data.base64) {				
				
				var unicode = $window.atob(data.base64);   
				
				$window.open("data:application/pdf," + escape(unicode));
			}
		});	
	};
	
	
	
	
	
	/********************* Set Item Cancelled **************************/	
	$scope.setCancelledCOD = function(orderTableId) {			
		
		var data = $.param ({
			orderTableId: orderTableId			
		});
		
		var config = {
                headers : {
                    'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8;'
                }
        }
		
		$http.post('SetItemCancelled', data, config).success(function(data) {
			
			if (data.cancelled) {				
				
				$scope.cancelParcelCOD  = false;			
				$scope.cancelledCOD     = true;				
			}
		});	
	};
	
	$scope.setCancelledBANK = function(orderTableId) {			
		
		var data = $.param ({
			orderTableId: orderTableId			
		});
		
		var config = {
                headers : {
                    'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8;'
                }
        }
		
		$http.post('SetItemCancelled', data, config).success(function(data) {
			
			if (data.cancelled) {					
				
				$scope.cancelParcelBANK = false;				
				$scope.cancelledBANK    = true;
			}
		});	
	};
	
	
	/********************* End Set Item Cancelled **************************/
	
	
	//$window.alert(orderTableId);
	
	
	
		
	
});






