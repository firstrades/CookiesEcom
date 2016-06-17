<%@page import="ecom.model.OfferedHot"%>
<%@page import="java.util.Map"%>
<%@page import="ecom.DAO.administration.AdminDAO"%>
<%@page import="ecom.model.Product"%>
<%@page import="java.util.List"%>
<%@page import="ecom.common.FrequentUse"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html data-ng-app="admin">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Offered Hot Selection</title>
	<script type="text/javascript" src="<%=FrequentUse.angular %>"></script>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
	<!-- <script type="text/javascript" src="js_Administration/Admin.js"></script> -->
	<style type="text/css">
	.field
{
  display          : inline-block;
  width            : 0.875em;
  height           : 0.875em;
  margin           : 0.25em 0.5em 0.25em 0.25em;
  border           : 0.0625em solid rgb(192,192,192);
  border-radius    : 0.25em;
  background       : rgb(224,224,224);
  background-image :    -moz-linear-gradient(rgb(240,240,240),rgb(224,224,224));
  background-image :     -ms-linear-gradient(rgb(240,240,240),rgb(224,224,224));
  background-image :      -o-linear-gradient(rgb(240,240,240),rgb(224,224,224));
  background-image : -webkit-linear-gradient(rgb(240,240,240),rgb(224,224,224));
  background-image :         linear-gradient(rgb(240,240,240),rgb(224,224,224));
  vertical-align   : bottom;
}
	</style>
</head>
<body data-ng-controller="OfferedHotSelection">

<%
	@SuppressWarnings("all")
	List<Product> products = (List<Product>) request.getAttribute("products");
	
	AdminDAO adminDAO = AdminDAO.getInstance();
	Map<Long, OfferedHot> offeredHotMap = (Map<Long, OfferedHot>) adminDAO.getHotOffered();

%>

		
	<div style="position:absolute;top:8%;margin:auto;width:99%;border:1px solid #ccc;max-height:auto; overflow: auto; overflow-x: none;">
		
			<h1 style="margin-left: 1%;background-color: #eee;padding: 8px 14px; font-size: 23px;width: 98%;"> Products For Approval </h1>
			
		<div data-ng-bind="noData" style="color: red;margin-left: 15px;"></div>
		
		<div class="container" style="width: 99%;border: 1px solid #ccc; margin:auto;">
				
		
		<form action="OfferedProductsSelected" method="post">
		
				<div class="row">
					<div class="col-md-12">
						<div class="col-md-offset-11">
							<input type="submit" class="btn-primary" value="Submit" style="padding:10px;"/>							
						</div>
					</div>
				</div>
				
				
	<% 
		for (Product product : products) { 
		
			OfferedHot offeredHot = (OfferedHot) offeredHotMap.get(product.getProductId());
			
			boolean isOffered = false;
			boolean isHot     = false;
			if (offeredHot != null) {
				isOffered = offeredHot.isOffered();
				isHot     = offeredHot.isHot();
			}
	%>
		
			<!-- ------------------ Row Starts -------------------- -->	
			
		
		
			<div class="row">
				<div class="col-md-12">						
					<div class="col-md-12" style="width:100%;border:1px solid #ddd;margin-bottom:10px;box-shadow: 1px 1px 1px #f5f5f5;background-color: #FFFFF5;">
						<div class="col-md-2" style="border: 1px solid #EAEAEA; box-shadow: 1px 1px 1px #e7e7e7; margin-top:10px; margin-bottom: 11px;">
							<img alt="image" src="IconImageFromProduct?productId=<%=product.getProductId() %>" width="150" height="150">
						</div>
						<div class="col-md-7">
							<div class="col-md-6">
								<h3 style="margin-bottom: 7px;margin-top: 8px;color: #337ab7; font-size: 16px;" class="ng-binding">  Order Id : J20160513152537537  </h3> <hr id="hr">
								<span class="ng-binding">   Id :  7  </span>  											
								<hr id="hr">					
								<span style="margin-right:15px;" class="ng-binding">Product Id : 15 </span> <br> <hr id="hr">
								<span class="ng-binding">  Each Price : 100 </span> 
								<span style="margin-left: 43px;" class="ng-binding">  Total Price : 100 </span><br> <hr id="hr">
								<span class="ng-binding">Warranty  : 1 year warranty is applicable</span>
								
							</div>					
							<div class="col-md-5" style="margin-top: 6px;">
							
								<span class="ng-binding">Shipping Cost : Rs. 0</span>
								<hr id="hr">
								<span class="ng-binding">Quantity : 1</span>
								<hr id="hr">
								<span class="ng-binding">Order  Booked On : 2016-05-13 15:25:37</span>
								<hr id="hr">						
								<span class="ng-binding">PaymentType : COD</span>
								<!-- ngIf: item.orderTableData.size != 0 --><span data-ng-if="item.orderTableData.size != 0" style="margin-left: 28px;" class="ng-binding ng-scope">Size : M</span><!-- end ngIf: item.orderTableData.size != 0 -->						
								<hr id="hr">
								<div>
									<a class="tooltip" style="color:blue;float:left;">
										Customer
										<span>
											<span class="ng-binding">CustomerId: 1</span><br>
											<span class="ng-binding">Contact:    7890000443</span><br>
											<span class="ng-binding">Address:    623 (OLD-592)</span><br>
											<span class="ng-binding">Address1:   Purba Sinthee Road</span><br>
											<span class="ng-binding">City:       Kolkata</span><br>
											<span class="ng-binding">State:      WB</span><br>
											<span class="ng-binding">Pin:        700030</span><br>
											<span class="ng-binding">FirstName:  Dipankar</span><br>
											<span class="ng-binding">LastName:   Das</span><br>
											<span class="ng-binding">Email:      jewel.skypoint@gmail.com</span><br>
											<span class="ng-binding">Country:    Individual</span><br>
											<span class="ng-binding">Company:    </span>
										</span>
									</a>
									
									<a class="tooltip" style="margin-left: 83px;color:#A94442;float:left;">
										Stockist
										<span>
											<span class="ng-binding">StockistId: 2</span><br>
											<span class="ng-binding">UserType:   SELLER</span><br>
											<span class="ng-binding">FirstName:  Dipankar</span><br>
											<span class="ng-binding">LastName:   Das</span><br>
											<span class="ng-binding">Company:    Surakha E Commerce Pvt Ltd</span><br>
											<span class="ng-binding">Address:    151 Dum Dum Road</span><br>
											<span class="ng-binding">Address1:   LP 1/14</span><br>
											<span class="ng-binding">Pin:        700074</span><br>
											<span class="ng-binding">City:       Kolkata</span><br>
											<span class="ng-binding">State:      WB</span><br>
											<span class="ng-binding">Country:    IN</span><br>
											<span class="ng-binding">Sex:        male</span><br>
											<span class="ng-binding">Mobile:     7890000443</span><br>
											<span class="ng-binding">Email:      jewel.skypoint@gmail.com</span><br>
											<span class="ng-binding">Balance:    0</span>									
										</span>
									</a>		
									
									<a class="tooltip" style="margin-left: 14px;color:green;float:right;">
										Courier
										<span>
											<span class="ng-binding">TrackNumber: </span><br>
											<span class="ng-binding">DeleveredDate: </span><br>
											<span class="ng-binding">PickedDate: </span><br>
											<span class="ng-binding">CancellationTime: 7 Days</span><br>
											<span class="ng-binding">CourierName: Not Set Yet</span>
										</span>	
									</a>						
								</div>				
							</div>					
						</div>				
				
						<div class="col-md-3" style="margin-top:100px;">
						<div class="row">
							<div class="col-md-12">
							<span style="float: left;
										margin-top: 9px;    
								    	margin-top: -24px;
								    	margin-left: 270px;
								    	font-family: fantasy;
								    	font-size: large;
								    	color: green;">
								Offer
							</span>
							<% if (isOffered) { %>
								<input type="checkbox" checked="checked" class="field" name="offered" value="<%=product.getProductId() %>" style="float: right;zoom: 2.0;margin-top: -13px; margin-left: 20px;" />
							<% } else { %>
								<input type="checkbox" class="field" name="offered" value="<%=product.getProductId() %>" style="float: right;zoom: 2.0;margin-top: -13px; margin-left: 20px;" />
							<% } %>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
							<span style="float: left;
										margin-top: 9px;    
								    	margin-top: 25px;
								    	margin-left: 270px;
								    	font-family: fantasy;
								    	font-size: large;
								    	color: green;">
								Hot
							</span>
							<% if (isHot) { %>
								<input type="checkbox" checked="checked" class="field" name="hot" value="<%=product.getProductId() %>" style="float: right;zoom: 2.0;margin-top: 14px; margin-left: 20px;" />
							<% } else { %>
								<input type="checkbox"  class="field" name="hot" value="<%=product.getProductId() %>" style="float: right;zoom: 2.0;margin-top: 14px; margin-left: 20px;" />
							<% } %>
							</div>
						</div>
						
						</div>
						<div class="col-md-3 ng-hide" style="margin-top:100px;" data-ng-show="item.orderTableData.orderState == 'Pickup'">
							<span style="float: left;
										margin-top: 9px;    
										float: left;
								    	margin-top: -24px;
								    	margin-left: 96px;
								    	font-family: fantasy;
								    	font-size: large;
								    	color: #31708F;">
								Track Number Generated <br>
								(Pickup Required)
							</span>
						</div>
						<div class="col-md-3 ng-hide" style="margin-top:100px;" data-ng-show="item.orderTableData.orderState == 'Picked'">
							<span style="float: left;
										margin-top: 9px;    
										float: left;
								    	margin-top: -24px;
								    	margin-left: 96px;
								    	font-family: fantasy;
								    	font-size: large;
								    	color: #F0AD4E;">
								Item Picked by Courier
							</span>
						</div>
						<div class="col-md-3 ng-hide" style="margin-top:100px;" data-ng-show="item.orderTableData.orderState == 'Cancel'">
							<span style="float: left;
										margin-top: 9px;    
										float: left;
								    	margin-top: -24px;
								    	margin-left: 96px;
								    	font-family: fantasy;
								    	font-size: large;
								    	color: #EA150F;">
								Order Cancelled By Customer <br>
								(Cancel pending by Stockist)
							</span>
						</div>
						<div class="col-md-3 ng-hide" style="margin-top:100px;" >
							<span style="float: left;
										margin-top: 9px;    
										float: left;
								    	margin-top: -24px;
								    	margin-left: 96px;
								    	font-family: fantasy;
								    	font-size: large;
								    	color: #EA150F;">
								Order Returned By Customer
							</span>
						</div>			
				
						<div class="col-md-3 ng-scope ng-hide" style="margin-top:100px;">							
							<span style="float: left;
										margin-top: 9px;    
										float: left;
								    	margin-top: -24px;
								    	margin-left: 96px;
								    	font-family: fantasy;
								    	font-size: large;" class="ng-hide">
								Order Delivered <br>
								(Payment to Stockist pending)
							</span>
							<span data-ng-show="payment" style="float: left;
																margin-top: 9px;    
																float: left;
														    	margin-top: -24px;
														    	margin-left: 96px;												    	
														    	font-size: large;" class="ng-hide">
								<button class="btn btn-success">Payment to Stockist</button>
							</span>
						</div>
				
				
				
					</div>	
				</div>
				
			</div>	
			
		
			
	<% } %>	
		
		</form>			
			<!-- ------------------ End Row Starts -------------------- -->		
			
			
						
						
		</div>		
    </div>   
    
    	





</body>
</html>