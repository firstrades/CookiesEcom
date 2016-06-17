var application = angular.module('Jewel', []);

application.controller('ViewController', function($scope, $http) {	
		
		$http.post('ViewProductList_Ajax', {}).success(function(data) {
			
			$scope.items = data;
		});
	
});

application.directive('ngRemoveItem', function($http, $window) {	
	
	return {
		
		link: function(scope, element, attr) {
		
			scope.deleteProduct = function() {
				
				var r = $window.confirm("Alert: Do You Really Want To Delete This Product!");
				
				if (r == true) { 
				
					var productId   = scope.item.productId;
					var category    = scope.item.category;
					var subCategory = scope.item.subCategory;				
					
					var keyValue = JSON.stringify({productId: productId, category: category, subCategory: subCategory});
					
					$http.post('DeleteProduct', keyValue, {headers: {'Content-Type': 'application/json'} }).success( function(data) {						
						
						if (data.success) {
							
							element.remove();
						}
					});
					
				}
			};
		}
	};
	
});




