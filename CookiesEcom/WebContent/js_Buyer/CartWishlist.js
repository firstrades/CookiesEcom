$(function() {
	
		$('.remove').click(function() {
			
				var tr             = $(this).closest('.item-row');          // find parent with specific class			
				var cartOrWishlist = $(this).attr('class').split(' ')[1];   // take the other class name 'cart or wishlist'			
				var productId      = $(this).attr('class').split(' ')[2];   // take the other class name 'productId'		
			
				$.getJSON('DeleteFromCartWishlistTable', {cartOrWishlist: cartOrWishlist, productId: productId}, function(object) {
					
						if (object.status == true) {   
							
							tr.remove();
							$('#amount').html('CART INR ' + object.totalSum);
							$('#qty').html(object.totalQty);
							$('#totalsun').html('Rs. ' + object.totalSum);
						}						
					
				});  // $.getJSON close			
			
		});  // $('.remove') close
		
		//----------------------------------------------------------------------------------------
		
		$('.qty').click(function() {
			
				var td = $(this).closest('td.carty-changeQuantity');  // find parent
				$('.save').hide();                                    // hide all save
				td.find('.save').show();                              // show this save
		});
		
		$('.save').click(function() {  
			
				var td    = $(this).closest('td.carty-changeQuantity');             // find parent
				var qty   = td.find('input.qty').val();                             // find value of sibling 
				var salePriceElement = td.find('input.salePriceChange');
				var salePrice = salePriceElement.val();                     // find value of sibling				
				
				var tr         = $(this).closest('.item-row');          // find parent with specific class	
				var tdSubTotal = tr.find('.bd');				
				var productId  = tr.find('.remove').attr('class').split(' ')[2];  
				
				tr.find('span.stock').html('');
				tr.find('div.wholeSale').html('');
				var itemNo = tr.find('input[name=itemNo]').val();  
				
				var cartWishlistID = tr.find('input[name=id]').val();    
				
				$.getJSON('InsertQtyToCart', {qty: qty, productId: productId, itemNo: itemNo, cartWishlistID: cartWishlistID }, function(object) {
					
						td.find('input.qty').val(object.qty);
						
						if(object.rate) {  
						    tr.find('.price').html('Rs. ' + object.rate);     
						}
						
						tr.find('.delivery').html('Delivery: &nbsp;&nbsp; ' + object.delivery);
						
						if (object.stock) {
							tr.find('span.stock').html(object.stock);
						}
						
						if (object.qty == 0) {							
							tr.find('span.stock').html('OUT OF STOCK');
						}
						
						if (object.wholeSaleQty && object.wholeSaleDiscount) { 
							tr.find('div.wholeSale').css('display', 'block').css('color', 'green');
							tr.find('div.wholeSale').html('You are qualified for discount ' + object.wholeSaleDiscount + '%');
						}
						
						
						td.find('.save').hide();
						$('#amount').html('CART INR ' + object.totalSum);
						$('#qty').html(object.totalQty);
						$('#totalsun').html('Rs. ' + object.totalSum);
						
						//var subTotal = object.qty * parseFloat(salePrice);						
						tdSubTotal.html('Rs. ' + object.subTotal);
						
						
						
						
						 
				});
		});
		
		
		
		
		
});  // $(function() close