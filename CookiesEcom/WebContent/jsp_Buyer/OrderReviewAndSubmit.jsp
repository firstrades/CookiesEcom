<%@page import="ecom.model.WholeSaleOffer"%>
<%@page import="ecom.common.Conversions"%>
<%@page import="ecom.model.BookedProduct"%>
<%@page import="java.util.List"%>
<%@page import="ecom.model.Order"%>
<%@page import="ecom.common.FrequentUse"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Order Review And Submit</title>
	
	<link href="<%=FrequentUse.style %>" rel="stylesheet" type="text/css" media="all"/> 
	<script src="<%=FrequentUse.jQuery %>" type="text/javascript"></script>
	<link href="css/orderriview.css" rel="stylesheet" type="text/css" media="all"/>	
	<script src="js_Buyer/OrderReviewAndSubmit.js"></script>
	
	<link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all"/>	
	<script src="js/bootstrap.min.js"></script>
	 
	<script>
	$(document).ready(function () {
	    $(".address-edit-icon").click(function () {
	    	
	        $("#dialog1").fadeIn(100);
	    });
	    $(".closebutton").click(function () {
	
	        $("#dialog1").fadeOut(100);
	
	    });
	});
	</script>
	
	<script>
	$(document).ready(function () {
	    $(".add_address_btn").click(function () {
	    	
	        $("#dialog2").fadeIn(100);
	    });
	    $(".closebutton").click(function () {
	
	        $("#dialog2").fadeOut(100);
	
	    });
	});
	</script>

<style type="text/css">

.panel-heading {
     padding: 0px 0px !important; 
    border-bottom: 1px solid transparent;
    border-top-left-radius: 3px;
    border-top-right-radius: 3px;
}
h2, .h2 {
    font-size: 15px !important;
}
h1, .h1, h2, .h2, h3, .h3 {
     margin-top: 20px !important; 
    margin-bottom: 10px !important; 
}

</style>

<style type="text/css">
#overlay {
position: fixed;
top: 0;
left: 0;
width: 100%;
height: 100%;
background-color: #000;
filter:alpha(opacity=70);
-moz-opacity:0.7;
-khtml-opacity: 0.7;
opacity: 0.7;
z-index: 100;
display: none;
}

.popup{
width: 100%;
margin: 0 auto;
display: none;
position: fixed;
z-index: 101;
    margin-top: 130px;
}

.pop_main_bg{
	background: #FFFAF9;
    position: relative;
    z-index: 99;
    max-width: 376px;
    height:100%;
    margin: 0 auto;
    margin-top: 12px;
    overflow: hidden;
    border-radius: 6px;
}
.blanket_pop{
	 background:rgba(0,0,0,0.5);
	 position:fixed;
	 left:0;
	 top:0;
	 width:100%;
	 height:100%;
 }
 .pop_absolute{
	 display:none;
	 left:0;
	 top:0;
	 width:100%;
	 z-index:9;
	 position:fixed;
 }
 .formbox {
    width: 100%;
    margin: 10px 0px;
    padding: 0px;
}
.formbox label {
    font-size: 14px;
    color: #5A5A5A;
    float: left;
    /* margin: 6px 0px 0px; */
    width: 45%;
    margin-left: 22px;
}
.formbox input {
    width: 250px;
    padding: 5px;
    height: 31px;
    background: none repeat scroll 0% 0% #FFF;
    border: 1px solid #CECECE;
    margin: 0px 8px 0px 10px;
    color: #666;
    outline: medium none;
    background-image: none !important;
}
 
</style>

</head>

<body style="margin: 0px; padding: 0px; background-color: #ccc;">

<%

	Order order = (Order) session.getAttribute("order");

%>

	<!-- --------------------------- Header ------------------------------------------- -->

	<div class="co-header">
   		<div class="content">
    		<div class="pure-g-r">
       			<div class="pure-u-1-2"> 
       				<a href="#" title="Firstrades" style="float: left;">
       					<img src="images/logoFirstTrade.png" class="flipkart-logo pure-u" width="118" height="60">
       				</a>
        			<span class="csText">Need Help? call <i class="fk-font-12">7890000448</i></span> 
        		</div>
       			<div class="pure-u-1-2">
        			<div class="unitExt" style="width:90%; margin-top:0px;">
           				<ul class="unit trust-banners">
            				<li class="banner1 line begin"> 
            					<span class="trust-icon secure-icon unit fk-inline-block"></span>
               					<p class="lastUnit"> <span class="fk-uppercase highlightBold">Secure</span><br>
                				<span class="fk-font-9 fk-uppercase">Payments</span> </p>
             				</li>
				            <li class="banner1"> <span class="trust-icon op-icon unit fk-inline-block"></span>
				            	<p class="lastUnit"> <span class="fk-uppercase highlightBold">Original</span><br>
				                <span class="fk-font-10 fk-uppercase">Products</span> </p>
				            </li>
            				<li class="banner1"> <span class="trust-icon returns-icon unit fk-inline-block"></span>
               					<p class="lastUnit free-returns"> <span class="fk-font-8 fk-uppercase">Free &amp; easy</span><br>
                				<span class="fk-uppercase highlightBold">Returns</span> </p>
             				</li>
            				<li class="banner1 end"> <span class="trust-icon protection-icon unit fk-inline-block"></span>
               					<p class="lastUnit"> <span class="fk-font-9 fk-uppercase highlightBold">100% buyer</span><br>
                				<span class="fk-font-9 fk-uppercase">protection</span> </p>
             				</li>
          				</ul>
           				<div class="cls"></div>
         			</div>
      			</div>
     		</div>
  		</div>
 	</div>
 
 	<!-- --------------------------- End Header ------------------------------------------- -->

	<ul class="content">
	
	<!-- ---------------------------- 1. Login Summary ---------------------------------------->
	
 		<li id="loginSummary" >
  			<div class="co-panel login closed completed">
      			<div class="panel-heading">
        			
        			<div class="rposition">          
          				<div class="collapsed-state pure-g-r">
          					<div class="pure-u-4-5"> 
          						<span class="tick"></span>
            					<h2 class="fk-inline-block title pure-u-1-4 tmargin10"><span>1. Login ID </span></h2>
            					<span class="pure-u fk-font-19 font-color-med-dark tmargin5 ng-binding" >9804810958</span> 
            				</div>
          					<div class="pure-u-1-5"> 
          						<span class="btn btn-white tmargin5 right_btn lmargin20" id="changeLogin">Change Login</span> 
          					</div>
          				</div>
          				
        			</div>
      			</div>
      			
        	</div>
 		</li>
 		
 		<!-- -------------------------- 1. Login Change ----------------------------------- -->
 		
  		<li id="loginChange" style="display: none;">   
        	<div class="heading-bar" >  
        		<h2 class="fk-inline-block title">  
        			<span>1. Before you place your order! </span> <i  class="ng-binding"> &gt; Sign In</i> 
        		</h2> 
        		<div class="cls"></div> 
        	</div>
       		<div class="tmargin10 bpadding20 ng-hide color">         
         		<div class="pure-g-r">
           			<div class="pure-u-1" style="margin-top: 13px;padding: 5px 39px;">
             			<p class="bmargin10"> <span class="fk-font-17"> Logged in as <!-- ngIf: loginId!='' --><strong  class="ng-scope ng-binding">9804810958</strong><!-- end ngIf: loginId!='' --><!-- ngIf: !loginId && email!='' --> </span>. </p>
             			<div class="info1">Please note that upon clicking "Sign out" you will lose items in your cart and will be redirected to Flipkart home page.</div>
             			<a class="btn btn-large btn-orange login_btn tmargin20" >Sign out </a> 
             			<span class="fk-font-17 lmargin20">
             				<a  class="fk-underline" id="continueLogin">CONTINUE</a>
             			</span> 
             		</div>
         		</div>
       		</div> 
  		</li>
  		
  		<!-- -------------------------- 2. Delivery Address Summary --------------------------- -->
  		
  		
  		<li id="deliverySummary" style="display:none;">
    		<div class="co-panel address closed completed">
    			<div class="panel-heading">     
      				<div class="heading-bar"> <!-- ngIf: !flag.loggedIn -->        
        				<h2 class="title"><span class="ng-binding">2. Delivery Address</span></h2>
      				</div>
      				<div class="rposition">      
        				<div class="collapsed-state pure-g-r" >
	        				<div class="pure-u-4-5"> <span class="tick"></span>
	          					<h2 class="fk-inline-block title pure-u-1-4 tmargin10"><span>2. Delivery Address </span></h2>
	          					<div class="pure-u-2-3 font-color-med-dark"> 
	          						<span class="fk-font-19 rpadding10 selected_address" id="name1"><%=order.getDeliveryAddress().getfName() + " " + order.getDeliveryAddress().getlName() %></span>
	          						<span id="contact1"><%=order.getDeliveryAddress().getContact() %></span><br>
	            					<span id="address1"><%=order.getDeliveryAddress().getAddress() + " " +  order.getDeliveryAddress().getAddress() + " " + order.getDeliveryAddress().getCity() + "-" + order.getDeliveryAddress().getPin() + ", " + order.getDeliveryAddress().getState() %></span> 
	            				</div>
	        				</div>
	        				<div class="pure-u-1-5"> 
	        					<div style="display: none;" id="userId"><%=order.getDeliveryAddress().getUserId() %></div>
	        					<span class="btn btn-white tmargin5 right_btn lmargin20" id="changeAddress">Change Address</span> 
	        				</div>
        				</div>
        				<div class="tooltip top skip-address-msg" >
          					<div class="tooltip-arrow"></div>
          					<div class="tooltip-inner">We have pre-selected your address for you! <a >X</a></div>
        				</div>
      				</div>
      				<div class="cls"></div>
    			</div>
    		</div>  
		</li>
		
		
		<!-- -------------------- 2. If Address Exists ----------------------------------- -->
		
		
		<li id="deliveryAddress"  style="display: none;">
  			<div class="co-panel address open">
    			<div class="panel-heading">    
      				<div class="heading-bar"> <!-- ngIf: !flag.loggedIn -->      
        				<h2 class="title"><span class="ng-binding">2. Delivery Address</span></h2>
      				</div> 
      				<div class="cls"></div>
    			</div>
    			<div class="panel-body collapse in"  style="height: auto;">
      				<div class=""></div>
      					<div  class="select-address" id="edit">
        					<div class="address-carousel">
          						<div  class="ng-isolate-scope">           
            						<div>          
              							<div class="carousel-inner"> 
                							<div class="item text-center ng-isolate-scope active"> 
                  								<div  class="address_detail ng-scope">                   									
                    								<div class="name rposition"> 
                    									<span class="tick-icon tick-mark" ></span> 
                    									<span class="text-ellipsis ng-binding" title="Jewel" id="fullName">
                    										<%=order.getDeliveryAddress().getfName() + " " + order.getDeliveryAddress().getlName() %>
                    									</span> 
                    								</div>
                    								<div class="name rposition"> 
                    								<p style="margin-left: 32px;display: block;" id="fullAddress"><%=order.getDeliveryAddress().getAddress() %></p>
                    								
                    								<p style="margin-left: 32px;display: block;" id="fullAddress1"><%=order.getDeliveryAddress().getAddress1() %></p>
                    								
                    								<p style="margin-left: 32px;display: block;" id="pinCity"><%=order.getDeliveryAddress().getPin() %>, <%=order.getDeliveryAddress().getCity() %></p>
                    								<p style="margin-left: 32px;display: block;" id="fullState"><%=order.getDeliveryAddress().getState() %></p>
                    								</div>
                    								<div class="name rposition"> 
                    								<span class="phone ng-binding" style="margin-left: 32px;display: block;" id="fullContact"><%=order.getDeliveryAddress().getContact() %></span>
                    								</div>
                    								<p style="margin-left: 32px;display: block;"> 
                    									<span class="btn btn-orange btn-large btn-continue no-underline" id="continueInAddress">continue</span>
                    								</p>                    							
                    								<div class="address-icons"> 
                    									<a class="delete-icon margin5 ng-scope"></a> 
                    									<a class="address-edit-icon margin5"></a> 
                    								</div>
                  								</div>
                  							 </div>
                						</div>
            						</div>
          						</div>
          						<div class="cls"></div>
        					</div>
        					<div class="add-address" > 
        						<a class="collapsed-state fk-display-block padding15 fk-text-center" > <span class="add_address_btn btn btn-white-yellow">+ Add new Address</span> </a> 
        					</div>
        					<div class="cls"></div>
      					</div>  
      					            			    
      			</div>
  			</div>
		</li>
			
		
		<!-- -------------------- 2. If Address Not Present ----------------------------- -->
		
		
		<li style="display: none;" id="enterAddress">
		
			<% if (order.getDeliveryAddress().isExits() == false) { %>
				<div style="display: none;" id="addressNotExists">true</div>
			<% } else { %>
				<div style="display: none;" id="addressNotExists">false</div>
			<% } %>
  			<div class="co-panel address open">
    			<div class="panel-heading">    
      				<div class="heading-bar"> <!-- ngIf: !flag.loggedIn -->      
        				<h2 class="title"><span class="ng-binding">2. Delivery Address</span></h2>
      				</div> 
      				<div class="cls"></div>
    			</div>
    			<div class="panel-body collapse in"  style="height: auto;">
      				<div class=""></div>
      					  
      					   
      					<div id="mainbuspannel1" class="main_div" style="display: block;">		 
							<div class="left_contaner" style="float: left;width: 99%;margin-left: 5px;">
								<span style="text-align: center; display: block; margin-bottom: 10px;">Please Enter Delivery Address</span>
								<hr style="margin-bottom: 0px;margin-top: 0px; border-top:1px solid #ccc;"> 
		
							<form method="post" id="newAddress" enctype="multipart/form-data">								
								 
								<p class="add-address-title lpadding20 tmargin10 bmargin10 bpadding10 fk-font-17" > </p> 
		 						<div class="pure-g-r"> 
		 							<div class="pure-u-1">
		  								<table align="left" width="100%">
		   									<tbody>
		   										<tr> 
		   											<td width="25%" class="fk-label" align="right"><label for="fName1">First Name</label></td>
		    										<td width="75%"> 
		    											<input name="fName1" class="fk-input" type="text" required="required" id="fName1" /> 
		    										</td> 
		     									</tr>
		     									<tr> 
		   											<td width="25%" class="fk-label" align="right"><label for="lName1">Last Name</label></td>
		    										<td width="75%"> 
		    											<input id="lName1" name="lName1" class="fk-input" type="text" required="required" /> 
		    										</td> 
		     									</tr>
		     									<tr>
		      										<td class="fk-label" align="right"><label for="pincode1">Pincode </label></td> 
		      										<td>
		      											<input id="pincode1" name="pincode1" class="fk-input" type="text" required="required" />
		      										</td> 
		      									</tr>
		        								<tr > 
		        									<td class="fk-label vtop" align="right"> <div class="tpadding20 tmargin5"><label for="address1">Address Line 1 </label></div> </td>
		        									<td> <textarea name="address1" class="fk-input" required="required" id="address1"/></textarea> </td>
		        								</tr>
		        								<tr>
		        									<td class="fk-label vtop" align="right"> <div class="tpadding20 tmargin5"><label for="address2">Address Line 2 </label></div> </td>
		         									<td> <textarea name="address2" class="fk-input"  id="address1"/></textarea> </td> 
		          								</tr> 		         								
		           								<tr> 
		           									<td class="fk-label" align="right"><label for="city1">City </label></td>
		            								<td> <input id="city1" name="city1" class="fk-input" type="text" required="required" /></td> 
		             							</tr> 
		            							<tr> 
		            								<td class="fk-label" align="right"><label for="state1">State </label></td> 
		            								<td> 
		            									<select id="state1" name="state1" class="fk-input" required="required">
		             										<option value="">--Select State--</option>
		              										<option value="Andaman and Nicobar Islands">Andaman and Nicobar</option>
		               										<option value="Andhra Pradesh">Andhra Pradesh</option> 
											               	<option value="Arunachal Pradesh">Arunachal Pradesh</option> 
											               	<option value="Assam">Assam</option> 
											               	<option value="Bihar">Bihar</option> 
											               	<option value="Chandigarh">Chandigarh</option> 
											               	<option value="Chhattisgarh">Chhattisgarh</option> 
											               	<option value="Dadra and Nagar Haveli">Dadra and Nagar Haveli</option>
		                									<option value="Daman and Diu">Daman and Diu</option>
		                 									<option value="Delhi">Delhi</option> 
		                 									<option value="Goa">Goa</option>
		                  									<option value="Gujarat">Gujarat</option>
		                   									<option value="Haryana">Haryana</option> 
		                   									<option value="Himachal Pradesh">Himachal Pradesh</option> 
		                   									<option value="Jammu and Kashmir">Jammu and Kashmir</option> 
		                   									<option value="Jharkhand">Jharkhand</option>
		                    								<option value="Karnataka">Karnataka</option>
		                     								<option value="Kerala">Kerala</option> 
		                     								<option value="Lakshadweep">Lakshadweep</option> 
		                     								<option value="Madhya Pradesh">Madhya Pradesh</option> 
		                     								<option value="Maharashtra">Maharashtra</option> 
		                     								<option value="Manipur">Manipur</option> 
		                     								<option value="Meghalaya">Meghalaya</option>
		                      								<option value="Mizoram">Mizoram</option> 
		                      								<option value="Nagaland">Nagaland</option>
		                      								<option value="Odisha">Odisha</option> 
		                      								<option value="Pondicherry">Pondicherry</option> 
		                      								<option value="Punjab">Punjab</option>
		                       								<option value="Rajasthan">Rajasthan</option> 
		                       								<option value="Sikkim">Sikkim</option> 
		                       								<option value="Tamil Nadu">Tamil Nadu</option> 
		                       								<option value="Tripura">Tripura</option> 
		                       								<option value="Uttar Pradesh">Uttar Pradesh</option> 
		                       								<option value="Uttrakhand">Uttarakhand</option> 
		                       								<option value="West Bengal">West Bengal</option> 
		                       								<option value="Army Post Office">Army Post Office</option>
		                        							<option value="Telangana">Telangana</option> 
		                        						</select>
		                          					</td> 
		                          				</tr>		                          				 
		                           				<tr > 
		                           					<td class="fk-label" align="right"><label for="contact1">Phone</label></td> 
		                           					<td class="line"> 
		                           						<input type="text" value="+91" class="unit fk-input phone_code" style="width: 13%;" /> 
		                           						<input  id="contact1" name="contact1" class="fk-input" type="text" maxlength="10" maxlength="10"  style="width: 46%;" required="required" /> 
		                            				</td> 
		                            			</tr>
		                            			<tr> 
		                            				<td>&nbsp;</td> 
		                            				<td class="tpadding15"> 
		                            					<input type="submit" class="btn btn-large btn-orange address_submit" value="Save &amp; Continue" style="margin-bottom: 20px;"> 
		                            				</td> 
		                            			</tr> 
		                            		</tbody>
		                            	</table> 
		                            </div> 
		                   		</div>
		                   		
		             		</form>
		             		
		             		
           					</div>				
							<div class="clear_fix"></div>			
              			</div>              			     
      			</div>
  			</div>
		</li>
		
		
		
		<!-- ---------------------- 3. Order Summary -------------------------- -->
		
		
		<li class="ng-scope" id="orderSummary" style="display: none;"> 
			<div class="co-panel order-summary open" >
				<div class="panel-heading">
					<div class="rposition"> 
         				
         					<div class="pure-u-4-5" style="float: left;">
          					<span class="tick"></span>
           					<h2 class="fk-inline-block title pure-u-1-4 tmargin10">
           					<span>3. Order Summary  </span> <span class="rpadding10 ng-binding" style="font-size: 12px;"><%=order.getBookedProductList().size() %> items</span> </h2>
            				<div class="pure-u font-color-med-dark"> 
            					<div class="tmargin10 fk-font-19" style="margin-top: 14px;"> 
            						
            						<span>Total <strong class="ng-binding"><%=order.getTotalSellPrice() %></strong></span> </div> </div> </div> 
            						<div class="pure-u-1-5"> 
            							<span class="btn btn-white tmargin5 right_btn lmargin20" style="    margin-top: 14px;    margin-bottom: 14px;">Review Order</span> 
            						</div> 
            			
            		</div>
             		<div class="cls"></div> 
             	</div> 
			</div>
		</li>			
		
		<!-- ------------------- 3. Order List ------------------------------- -->
		
		<li  class="ng-scope" id="orderList" style="display:block;"> 
			<div class="co-panel order-summary open"  >
       			<div class="heading-bar"> 
       				<div  class=""></div>
        			<h2 class="fk-inline-block title">
        			<span>3. Order Summary</span> 
        				<i  class="ng-scope ng-binding"><%=order.getBookedProductList().size() %> items</i></h2> 
        		</div>
             	<div class="panel-body collapse in"  style="height: auto;"> 
             		<div  class=""></div>
             		<table width="100%" cellpadding="0" cellspacing="0" class="cart-table ng-scope" > 
	             		<thead class="fk-uppercase cart-head">
	              			<tr> 
	              				<td class="image-cell head-cell">&nbsp;</td>
	               				<td class="product-cell head-cell" colspan="3"> 
	               					<table width="100%" cellspacing="0" cellpadding="0"> 
	               						<tbody>
	               							<tr> 
	               								<td class="item-cell head-cell"><span class="lpadding10">Item</span></td>
	                							<td class="qty-cell head-cell">Qty</td>
	                 							<td class="price-cell fk-text-center head-cell">Price</td> 
	                 						</tr> 
	                 					</tbody>
	                 				</table> 
	                 			</td> 
	                 			<td class="head-cell delivery-cell fk-text-center">Delivery Details</td> 
	                 			<td class="head-cell subtotal-cell fk-text-center">Subtotal</td> 
	                 			<td class="head-cell remove-cell">&nbsp;</td> 
	                 		</tr> 
	                 	</thead>
	                  	<tbody class="cart-body"> 
	                  	
                  		<%
                  			for (BookedProduct bookedProduct : order.getBookedProductList()) {
                  				
                  				if (bookedProduct.getStock() == 0) continue;
                  				
                  				WholeSaleOffer wholeSaleOffer = WholeSaleOffer.getWholeSaleOffer(bookedProduct.getProductId());
                  		%>
	                  	
	                  		<tr  class="ng-scope">
	                   			<td colspan="7"> 
	                   				<table width="100%" cellpadding="0" cellspacing="0"> 
	                   					<tbody>
	                   						<tr  class="ng-scope">
	                    						<td class="cell image-cell"> 
	                    							<div class="carty-image fk-text-center">
	                    								<img  src="IconImageFromProduct?productId=<%=bookedProduct.getProductId() %>" class="product-image ng-scope" alt=""  /> 
	                    							</div> 
	                    							<span class="gift-icon ng-hide" ></span>
	                     							
	                    						</td>
	                     						<td colspan="3" class="product-cell">
	                      							<table width="100%" height="100%" cellspacing="0" cellpadding="0" class="product-table">
	                       								<tbody>
	                        								<tr> 
	                        									<td class="cell item-cell">
	                         										
	                          										<span class="title ng-binding"><%=bookedProduct.getProductBean().getProductName() %> (<%=bookedProduct.getProductBean().getCompanyName() %>)</span><p class="tmargin5 fk-font-11 font-color-medium ng-scope ng-binding" ><%=bookedProduct.getProductBean().getSubCategory() %> - <%=bookedProduct.getProductBean().getCategory() %></p><p class="tmargin5 fk-font-11 font-color-medium ng-scope ng-binding" ></p> <span class="ff-icon tmargin5 ng-hide"  title="Flipkart First"></span> 
	                          										<div class="tmargin10">
	 																	Seller: <%=bookedProduct.getProductBean().getSellerCompany() %>  
	 																	<div class="conditional-offer tmargin10 ng-hide" > <a class="conditional-offer-count ng-binding"  ><%=bookedProduct.getProductBean().getWarranty() %></a>  
	 																	</div> 
	 																</div>
	 																
	 																<!-- --------------------------------------------------------------------------------- -->
										 							<span class="fk-bold">Product ID: <%=bookedProduct.getProductId() %></span>
										 							
										 							<!-- ---- Size------- -->
										 							<% if (!bookedProduct.getGarmentOrder().getSize().equals("")) { %>
										 								<br><span class="fk-bold">Size: <%=bookedProduct.getGarmentOrder().getSize() %></span>
										 							<% } %>
										 							
										 							<!-- WholeSale Qty and Discount -->
										 							<% if (wholeSaleOffer != null) { %>
										 								<br><span class="fk-bold">WholeSale Discount <%=wholeSaleOffer.getDiscount() %>% on Qty  <%=wholeSaleOffer.getQty() %> or more.</span>
										 							<% } %>
										 							
										 							<!-- Customer gets wholeSale Price -->
										 							<% if (wholeSaleOffer != null && wholeSaleOffer.getQty() <= bookedProduct.getQty()) { %>
										 								<div style="display: display;color:green;" class="wholeSale">You are qualified for discount <%=wholeSaleOffer.getDiscount() %> %</div>
										 							<% } else { %>
										 								<div style="color:green;" class="wholeSale"><!-- String from jQuery --></div>
										 							<% } %> 
										 							
										 							<!-- ---- Stock------- -->
										 							<% if (bookedProduct.getStock() == 0) { %>
										 								<span class="stock" style="font-size: 12px; margin-top: -13px;">OUT OF STOCK </span>
										 							<% } else {%>
										 								<span class="stock" style="font-size: 12px; margin-top: -13px;">Stock: <%=bookedProduct.getStock() %> </span>
										 							<% } %>
										 							<!-- --------------------------------------------------------------------------------- -->
	 																
	 															</td> 
	 															<td class="cell fk-text-center qty-cell carty-changeQuantity">
	  																<form action="insertQtyInOrderReviewAndSubmit" method="post">
	  																	<input type="hidden" name="productId" value="<%=bookedProduct.getProductId() %>" />
	  																	<input type="hidden" name="cartWishlistID" value="<%=bookedProduct.getCartWishlistId() %>" />
	  																	<input type="text" name="qty" maxlength="3" value="<%=bookedProduct.getQty() %>" class="qty-box fk-input ng-scope ng-pristine ng-valid ng-valid-pattern" ><br>
	  																	<input type="submit" class="qty-save fk-font-small ng-hide" value="Save"><!-- data-ng-show="showSave" -->
	  																</form> 
	  															</td> 
	  															<td class="cell price-cell"> 
	  																<div class="carty-price text_right"> 
	  																	<div class="fk-bold price ng-scope"> 	  																		
	  																		<div class="tmargin20 fk-bold price ng-binding ng-hide" > Rs.<%=Conversions.round(bookedProduct.getSellPrice(), 2) %></div> 
	  																	</div> 
	  																</div>
	  															</td> 
	  														</tr> 
	  													</tbody> 
	  												</table> 
	  											</td> 
	  											<td class="cell delivery-cell">
	  												<div  class="ng-scope"> 
	  													<div class="shipping-options-container bpadding10 fk-font-13 ng-scope" > 
	  														<div class="lpadding20 rpadding10 ng-scope"> 
	  															<div class="bmargin5"> 
	  																<% if (bookedProduct.getRate().doubleValue() == 0) { %>
		  																<div style="font-size: 140%" class="lmargin20 bmargin5 ng-hide">
		  																	Free Shipping
		  																</div> 
	  																<% } else { %>
		  																<div  style="font-size: 140%" class="lmargin20 bmargin5 ng-hide">
		  																	+ Shipping  Rs. <%=bookedProduct.getRate() %>
		  																</div> 
	  																<% } %>
	  																<label>
	  																	
	  																	<span  class="ng-scope"><strong class="ng-binding">Delivery: &nbsp;&nbsp; <%=bookedProduct.getDelivery() %></strong>	  																		
	  																	</span>
	  																</label> 
	  															</div> 
	  															
	 														</div>
	 													</div>
	 												</div>
	 											</td> 
	 											<td class="cell subtotal-cell fk-font-17 fk-bold price text_right ng-binding">
	 												Rs.<%=Conversions.round((bookedProduct.getSellPrice() * bookedProduct.getQty()) + bookedProduct.getRate().doubleValue(), 2) %></td>
	  											<td class="cell remove-cell">
	   												<a class="remove fk-inline-block"  title="Remove Item"> 
	   													<span class="icon"></span> 
	   												</a> 
	   											</td> 
	   										</tr>
	   									</tbody>
	   								</table>
	    						</td>
	     					</tr>
	     					
	     					
     					<%
                  			}
                  		%>
	     					
						</tbody> 
					</table>
				</div> 
				<div class="tpadding10 os-footer" >
 					<form name="alertForm" class="ng-pristine ng-valid ng-valid-required">
  						<div class="pure-g summary-mobile bmargin10 bpadding10"> 
  							<div class="pure-u-1"> 
   								<div>Order confirmation email will be sent to <strong class="ng-binding">jewel.skypoint@gmail.com</strong> </div> 
   							</div> 
   						</div>
  						<div class="nav-bar ng-isolate-scope" >
   							<div class="pure-g-r"> 
   								<div class="pure-u-2-3 text_right"> 
   									<span  class="rpadding20 ng-hide">Your Total Savings: <i class="offer-color fk-bold ng-binding">Rs.<%=Conversions.round( order.getTotalSavings(), 2 ) %></i></span> 
   									<span class="total fk-font-15 rpadding10">Amount Payable: <i class="fk-font-26 ng-binding">Rs. <%=order.getTotalSellPrice() %></i></span> 
   								</div> 
   							</div> 
   						</div> 
   					</form> 
  				</div> 
   			</div>  
   		</li>
   		
   		<!-- ------------------- Payment GateWay ----------------------------- -->
   		
		<li id="payment" style="margin-bottom: 40px;"> 
			<div class="co-panel order-summary open" >
				<div class="panel-heading">
         			<div class="rposition">         	
         				<div class="collapsed-state pure-g-r ng-hide" style="width: 79%; float: left;"> 
         					<div class="pure-u-4-5">
          						<span class="tick"></span>
           						<h2 class="fk-inline-block title pure-u-1-4 tmargin10">
           							<span>4. Payment Methode </span></h2>
            					<div class="pure-u font-color-med-dark"> </div> 
           					</div> 
            			</div>
           				<div class="pure-u-1-5"> 
           					<div class="pure-u-1-3" style="margin-bottom: 6px; margin-top: 6px;"> <!-- Order -->       						
   								<a href="#" data-toggle="modal" data-target="#myModal" class="btn btn-orange btn-large btn-continue no-underline" id="toPayment">Continue</a>    								
   							</div>  
   						</div>
             		</div>
             		<div class="cls"></div> 
             	</div> 
			</div>
		</li>
	</ul>	 
	
	
	<!-- -------------------------- Add Address Pop-Up ---------------------------------- -->  	
	
	<div id="dialog2" class="pop_absolute">
    	<div class="pop_main_bg" style="max-width: 535px;"> 
    		<a class="closebutton" style="float: right;font-size: 16px;margin-right: 13px; color: #ccc; cursor: pointer;margin-top: 3px;margin-bottom: 11px;">X</a>
            	<div class="main_popup" id="main_popupmenu">              
              		<div id="main_bus_pannel" class="main_div">		 
						<div class="left_contaner" style="float: left;width: 99%;margin-left: 5px;">
							<span style="text-align: center; display: block; margin-bottom: 10px;">Enter a new shipping address</span>
							<hr style="margin-bottom: 0px;margin-top: 0px; border-top:1px solid #ccc;"> 		
							<form name="address.form" id="createAddress"  method="post" class="ng-valid ng-valid-required ng-dirty"> 
								<input id="address_id" type="hidden" name="address_id" value="" class="ng-pristine ng-valid"> 
								<input id="autofill_modified" type="hidden" name="autofill_modified"  value="" class="ng-pristine ng-valid"> 								 
		 						<div class="pure-g-r"> 
		 							<div class="pure-u-1">
		  								<table align="left" width="100%">
		   									<tbody>
		   										<tr> 
		   											<td width="25%" class="fk-label" align="right"><label for="name">Name</label></td>
		    										<td width="75%"> <input id="name" minlength="3"  name="name" class="fk-input ng-valid-minlength ng-valid-pattern ng-valid ng-valid-required ng-dirty" type="text" value=""  required="required"> </td> 
		     									</tr>
		     									<tr>
		      										<td class="fk-label" align="right"><label for="pincode">Pincode </label></td> 
		      										<td><input  id="pincode" name="pincode" maxlength="6"  class="fk-input ng-scope ng-pristine ng-valid ng-valid-pattern ng-valid-required" type="text" value=""  required="required"></td> 
		       									</tr>
		        								<tr > 
		        									<td class="fk-label vtop" align="right"> 
		        										<div class="tpadding20 tmargin5"><label for="address">Address Line 1 </label></div> 
		        									</td>
		         									<td> <textarea  name="address" id="address" minlength="10" maxlength="216"   class="fk-input ng-pristine ng-valid ng-valid-required ng-valid-maxlength ng-valid-minlength"  required="required"></textarea></td> 
		          								</tr> 
		          								<tr > 
		        									<td class="fk-label vtop" align="right"> 
		        										<div class="tpadding20 tmargin5"><label for="address">Address Line 2 </label></div> 
		        									</td>
		         									<td> <textarea  name="address" id="address" minlength="10" maxlength="216"   class="fk-input ng-pristine ng-valid ng-valid-required ng-valid-maxlength ng-valid-minlength"  required="required"></textarea></td> 
		          								</tr> 
		         								<tr> 
		         									<td class="fk-label" align="right"><label for="landmark">Landmark</label></td>
		          									<td><input id="landmark" name="landmark"  class="fk-input ng-pristine ng-valid ng-valid-maxlength" type="text" value="" placeholder="(Optional)"  maxlength="60" ></td> 
		          								</tr>
		           								<tr> 
		           									<td class="fk-label" align="right"><label for="city">City </label></td>
		            								<td> <input id="city" name="city"   class="fk-input ng-pristine ng-valid-pattern ng-valid ng-valid-required" type="text" value=""  required="required"> </td> 
		             							</tr> 		            
		                          				<tr> 
		                          					<td class="fk-label country_field" align="right">Country</td>
		                           					<td class="tpadding15 bpadding5"><b class="ng-binding">India</b> <span class="co_note ng-binding">(Service available only in India)</span></td> 
		                           				</tr> 
		                           				<tr> 
		                           					<td class="fk-label" align="right"><label for="phone">Phone</label></td> 
		                           					<td class="line"> 
		                           						<input type="text" value="+91"  class="unit fk-input phone_code" style="width: 13%;"> <!-- ngIf: model.totalAddress==0 --> <!-- ngIf: model.totalAddress!=0 -->
		                           						<input  id="phone"  name="phone" class="fk-input phone_field ng-scope ng-pristine ng-valid ng-valid-maxlength ng-valid-pattern ng-valid-required" type="text" value="" maxlength="10" maxlength="10" required="required" style="width: 46%;">
		                           					</td> 
		                            			</tr>
		                            			<tr> 	
		                            				<td>&nbsp;</td> 
		                            				<td class="tpadding15"> 
		                            					<input type="submit" class="btn btn-large btn-orange address_submit" 
		                            						value="Save &amp; Continue" style="margin-bottom: 20px;"> 
		                            				</td> 
		                            			</tr> 
		                            		</tbody>
		                            	</table> 
		                            </div> 
		                     	</div>
		             		</form>
           				</div>				
						<div class="clear_fix"></div>				
              		</div>            
          		</div>
        </div>
        <div class="blanket_pop"></div>
 	</div> 


	<!-- -------------------------- Edit Address Pop-Up ---------------------------------- -->

	<div id="dialog1" class="pop_absolute">
    	<div class="pop_main_bg" style="max-width: 535px;"> 
    		<a class="closebutton" style="float: right;font-size: 16px;margin-right: 13px; color: #ccc; cursor: pointer;margin-top: 3px;margin-bottom: 11px;">X</a>
            	<div class="main_popup" id="main_popupmenu">              
              		<div id="main_bus_pannel" class="main_div">		 
						<div class="left_contaner" style="float: left;width: 99%;margin-left: 5px;">
							<span style="text-align: center; display: block; margin-bottom: 10px;">Edit the shipping address</span>
							<hr style="margin-bottom: 0px;margin-top: 0px; border-top:1px solid #ccc;">	
								
							<form method="post" id="editAddress" enctype="multipart/form-data">
								
								 
								<p class="add-address-title lpadding20 tmargin10 bmargin10 bpadding10 fk-font-17" > </p> 
		 						<div class="pure-g-r"> 
		 							<div class="pure-u-1">
		  								<table align="left" width="100%">
		   									<tbody>
		   										<tr> 
		   											<td width="25%" class="fk-label" align="right"><label for="fName">First Name</label></td>
		    										<td width="75%"> 
		    											<input name="fName" class="fk-input" type="text" required="required" id="fName" /> 
		    										</td> 
		     									</tr>
		     									<tr> 
		   											<td width="25%" class="fk-label" align="right"><label for="lName">Last Name</label></td>
		    										<td width="75%"> 
		    											<input id="lName" name="lName" class="fk-input" type="text" required="required" /> 
		    										</td> 
		     									</tr>
		     									<tr>
		      										<td class="fk-label" align="right"><label for="pincode">Pincode </label></td> 
		      										<td>
		      											<input id="pincode" name="pincode" class="fk-input" type="text" required="required" />
		      										</td> 
		      									</tr>
		        								<tr > 
		        									<td class="fk-label vtop" align="right"> <div class="tpadding20 tmargin5"><label for="address">Address Line 1 </label></div> </td>
		         									<td> <textarea name="address" class="fk-input" required="required" id="address"></textarea> </td> 
		          								</tr> 
		          								<tr > 
		        									<td class="fk-label vtop" align="right"> <div class="tpadding20 tmargin5"><label for="address1">Address Line 2</label></div> </td>
		         									<td> <textarea name="address1" class="fk-input"  id="address1"></textarea> </td> 
		          								</tr>		         								
		           								<tr> 
		           									<td class="fk-label" align="right"><label for="city">City </label></td>
		            								<td> <input id="city" name="city" class="fk-input" type="text" required="required" /></td> 
		             							</tr> 
		            							<tr> 
		            								<td class="fk-label" align="right"><label for="state">State </label></td> 
		            								<td> 
		            									<select id="state" name="state" class="fk-input" required="required">
		             										<option value="">--Select State--</option>
		              										<option value="Andaman and Nicobar Islands">Andaman and Nicobar</option>
		               										<option value="Andhra Pradesh">Andhra Pradesh</option> 
											               	<option value="Arunachal Pradesh">Arunachal Pradesh</option> 
											               	<option value="Assam">Assam</option> 
											               	<option value="Bihar">Bihar</option> 
											               	<option value="Chandigarh">Chandigarh</option> 
											               	<option value="Chhattisgarh">Chhattisgarh</option> 
											               	<option value="Dadra and Nagar Haveli">Dadra and Nagar Haveli</option>
		                									<option value="Daman and Diu">Daman and Diu</option>
		                 									<option value="Delhi">Delhi</option> 
		                 									<option value="Goa">Goa</option>
		                  									<option value="Gujarat">Gujarat</option>
		                   									<option value="Haryana">Haryana</option> 
		                   									<option value="Himachal Pradesh">Himachal Pradesh</option> 
		                   									<option value="Jammu and Kashmir">Jammu and Kashmir</option> 
		                   									<option value="Jharkhand">Jharkhand</option>
		                    								<option value="Karnataka">Karnataka</option>
		                     								<option value="Kerala">Kerala</option> 
		                     								<option value="Lakshadweep">Lakshadweep</option> 
		                     								<option value="Madhya Pradesh">Madhya Pradesh</option> 
		                     								<option value="Maharashtra">Maharashtra</option> 
		                     								<option value="Manipur">Manipur</option> 
		                     								<option value="Meghalaya">Meghalaya</option>
		                      								<option value="Mizoram">Mizoram</option> 
		                      								<option value="Nagaland">Nagaland</option>
		                      								<option value="Odisha">Odisha</option> 
		                      								<option value="Pondicherry">Pondicherry</option> 
		                      								<option value="Punjab">Punjab</option>
		                       								<option value="Rajasthan">Rajasthan</option> 
		                       								<option value="Sikkim">Sikkim</option> 
		                       								<option value="Tamil Nadu">Tamil Nadu</option> 
		                       								<option value="Tripura">Tripura</option> 
		                       								<option value="Uttar Pradesh">Uttar Pradesh</option> 
		                       								<option value="Uttrakhand">Uttarakhand</option> 
		                       								<option value="WB">West Bengal</option> 
		                       								<option value="Army Post Office">Army Post Office</option>
		                        							<option value="Telangana">Telangana</option> 
		                        						</select>
		                          					</td> 
		                          				</tr>		                          				 
		                           				<tr > 
		                           					<td class="fk-label" align="right"><label for="contact">Phone</label></td> 
		                           					<td class="line"> 
		                           						<input type="text" value="+91" class="unit fk-input phone_code" style="width: 13%;" /> 
		                           						<input  id="contact" name="contact" class="fk-input" type="text" maxlength="10" maxlength="10"  style="width: 46%;" required="required" /> 
		                            				</td> 
		                            			</tr>
		                            			<tr> 
		                            				<td>&nbsp;</td> 
		                            				<td class="tpadding15"> 
		                            					<input type="submit" class="btn btn-large btn-orange address_submit" value="Save &amp; Continue" style="margin-bottom: 20px;"> 
		                            				</td> 
		                            			</tr> 
		                            		</tbody>
		                            	</table> 
		                            </div> 
		                   		</div>
		             		</form>
		             		
           				</div>				
						<div class="clear_fix"></div>			
              	</div>            
          	</div>
        </div>
        <div class="blanket_pop"></div>
	</div>
	
	<!-- ----------------------------- Pop-Up for Payment Mode ------------------------------------------------ -->
	
	
	<div class="modal fade" id="myModal">
    	<div class="modal-dialog">    
      		<!-- Modal content-->
      		<div class="modal-content" style="width: 65%; margin-left: 15%;">
        		<div class="modal-header">
          			<button type="button" class="close" data-dismiss="modal">&times;</button>
          			<h4 class="modal-title" style="text-align: center; color: #FF6978">Payment Mode</h4>
        		</div>
        		<!-- -------------------------------------------------------------- -->
        		<form action="Administration" method="post">
	        		<div class="modal-body">
	       				<div class="new-login-form">           
	            			
	            			<div class="tmargin20 login-btn-wrap" style="margin-left: 20%;">
	                			
	                			<a href="OrderCOD" class="frgt-pswd fk-font-12 lpadding20"  style="font-size: 13px;padding: 6px 40px;background-color: #09A0E4;color: #fff;border-radius: 2px;border-bottom: 2px solid #008CCC;">COD</a>
	                			<a href="OrderBank" class="frgt-pswd fk-font-12 lpadding20"  style="font-size: 13px;padding: 6px 40px;background-color: #fc8332;color: #fff;border-radius: 2px;border-bottom: 2px solid #DA6212;">BANK</a>
	            			</div>
	        			</div>
	        		<!-- 	<div class="modal-footer">
	          				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        			</div> -->
	      			</div>   
      			</form>  
      			<!-- -------------------------------------------------------------- --> 
    		</div>
  		</div>  
	</div>	
	
	
	<!-- ------------------------------------------------------------------------------------------------- --> 
	
	
	
	
	
</body>
</html>