$(function() {   
	
	$('#loadMore').click(function() {		
		
		//document.getElementById('a').style.color = "red";
		$('#a').css('color','red').html('Loading...');
		
		var max    = $('#max').html();
		var subCat = $('#subCat').html();		
		var MAX    = $('#a').data('max');      
				
				
		$.getJSON('SearchBySubCategory_Ajax', {subCategory: subCat, MAX1: MAX} , function(object) {			
			
			var tempCount = Math.floor(object.list.length / 5);   
			var productBeanList_DivCount = 0;
			
			if (object.residueProduct == true) {
			
				if (object.list.length % 5 == 0)
					productBeanList_DivCount =  tempCount;
				else
					productBeanList_DivCount =  tempCount + 1;
			
			} else {
			
				productBeanList_DivCount = Math.floor(object.list.length / 5);
			}
			
			var k = 0;
			var id = max;
			
			var $div = "";
			
			for (var i = 0; i < productBeanList_DivCount; i++) {
				
				$div += "<div class='specia-top' id='"+id+"'>"+
					 		"<ul class='grid_2'>";
				
				for (var j = 0; j < 5; j++) {  // object.list[k+j.subCategory
					
					$div += "<li>"+
								"<div style='height:200px'>" +
								"<a href='CompleteProductDetails?subCategory=" + object.list[k+j].subCategory + "&productId=" + object.list[k+j].productId + "'><img src='IconImageFromProduct?productId="+ object.list[k+j].productId +"' class='img-responsive center-block' alt=''></a></div>"+
								"<div class='sp clecial-info grid_1 simpleCart_shelfItem'>"+
									"<div class='all_pannel'>"+
										"<p>ProductId: "+object.list[k+j].productId+"</p>"+
										"<h5><a href='CompleteProductDetails?subCategory=" + object.list[k+j].subCategory + "&productId=" + object.list[k+j].productId + "'> "+ object.list[k+j].productName +" </a> (<a href='#'>"+ object.list[k+j].companyName +"</a>) </h5>"+											
										"<div class='item_add'><h6><span class='item_price'> <small class='over_flow'> Rs."+ object.list[k+j].listPrice +" </small> <br> <small class='item_price'> ("+ object.list[k+j].discount +"% Off) </small> <br> <strong class='main_value'>Rs "+ object.list[k+j].salePriceCustomer +"</strong> </span></h6></div>"+
										"<div class='item_add'><span class='item_price'><a href='CompleteProductDetails?subCategory=" + object.list[k+j].subCategory + "&productId=" + object.list[k+j].productId + "'>More Details</a></span></div>"+
									"</div>"+
								"</div>"+
							"</li>";
					
					if ((k+j) == object.list.length-1) { 						
					
						break; 
					}
				}  //  for close
				
					$div += "</ul>"+
					"<div class='clearfix'> </div>"+
				"</div>";
			
					id++;
					k = k + 5;
			
			}  // productBeanList_DivCount for loop ends
			
			
			$('#append').append($div);			
			$('#max').html(id);
			$('#loadMore a').css('color', 'white').html('Load More');
			
			$('#a').remove();
			$('#span').append('<a id="a" data-max="' + object.max + '">Load More</a>');	
			
			if (object.hideLoadMore == true) 				
				$('#loadMore').remove();
			
			
		});	// json end
	
	});	// click end
	
});  // document end



