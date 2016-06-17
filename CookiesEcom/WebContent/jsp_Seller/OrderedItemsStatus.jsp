<%@page import="ecom.common.State"%>
<%@page import="java.util.List"%>
<%@page import="ecom.model.OrderTable"%>
<%@page import="ecom.common.FrequentUse"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html data-ng-app="Jewel">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>View Product List</title>
	<script type="text/javascript" src="<%=FrequentUse.jQuery %>"></script>
	<script type="text/javascript" src="<%=FrequentUse.angular %>"></script>
	<script type="text/javascript" src="js_Seller/OrderedItemsStatus.js"></script>	
	<link type="text/css" href="css/bootstrap.css" rel="stylesheet">
	<link  rel="stylesheet" href="<%=FrequentUse.style %>" type="text/css" />
	<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	<script src="js/bootstrap.min.js"></script>
	
	 <link rel="stylesheet" href="css/reset.css" type="text/css"/>
<link rel="stylesheet" href="css/default.css" type="text/css"/>

	
<style type="text/css">

.row{
    margin-left:0px !important;
    margin-right:0px !important;
    margin-bottom: 16px;
}
label{
font-weight: normal !important; 
color:#337AB7;
}
hr {
 
    border-top: 1px solid #eee;
    margin-top:4px;
    margin-bottom: 4px;
}
.key_feature{
padding: 0px;
margin: 0px;
}
.key_feature li{
    font-size: 11px;
    color: #777;
    margin-left: 14px;


}
.clear_fix{
clear: both;
}

</style>


<!-- Tooltip -->
<style>
a.tooltip {
    outline: none;
    text-decoration: none;    
    position: relative;
    opacity: 1;
}

a.tooltip strong {
    line-height: 30px;
}

a.tooltip > span {
    width: 300px;
    padding: 10px 20px;
    margin-top: 0;
    margin-left: -120px;
    opacity: 0;
    visibility: hidden;
    z-index: 10;
    position: absolute;
    font-family: Arial;
    font-size: 12px;
    font-style: normal;
    border-radius: 3px;
    box-shadow: 2px 2px 2px #999;
    -webkit-transition-property: opacity, margin-top, visibility, margin-left;
    -webkit-transition-duration: 0.4s, 0.3s, 0.4s, 0.3s;
    -webkit-transition-timing-function: ease-in-out, ease-in-out, ease-in-out, ease-in-out;
    transition-property: opacity, margin-top, visibility, margin-left;
    transition-duration: 0.4s, 0.3s, 0.4s, 0.3s;
    transition-timing-function: 
        ease-in-out, ease-in-out, ease-in-out, ease-in-out;
}


a.tooltip:hover > span {
    opacity: 1;
    text-decoration: none;
    visibility: visible;
    overflow: visible;
    margin-top: 50px;
    display: inline;       
    margin-left: -90px;
}

a.tooltip span b {
    width: 15px;
    height: 15px;
    margin-left: 40px;
    margin-top: -19px;
    display: block;
    position: absolute;
    -webkit-transform: rotate(-45deg);
    -moz-transform: rotate(-45deg);
    -o-transform: rotate(-45deg);
    transform: rotate(-45deg);
    -webkit-box-shadow: inset -1px 1px 0 #fff;
    -moz-box-shadow: inset 0 1px 0 #fff;
    -o-box-shadow: inset 0 1px 0 #fff;
    box-shadow: inset 0 1px 0 #fff;
    display: none\0/;
    *display: none;
}    

a.tooltip > span {
	color: #000000; 
	background: #FBF5E6;
	background: -webkit-linear-gradient(top, #FBF5E6, #FFFFFF);
	background: linear-gradient(top, #FBF5E6, #FFFFFF);	    
	border: 1px solid #CFB57C;	     
}    
	  
a.tooltip span b {
	background: #FBF5E6;
	border-top: 1px solid #CFB57C;
	border-right: 1px solid #CFB57C;
}

</style>

</head>

<%
	@SuppressWarnings("all")
	List<OrderTable> orderTables = (List<OrderTable>) request.getAttribute("orderTables");

	State state = new State();

	
%>



<body id="top">


<!-- -----------------------------------    Loop     -------------------------------------------------- -->


<% 
	int i = 0;
	for (OrderTable orderTable : orderTables) { 
	    System.out.println(orderTable.getPaymentType());
	    
	    if ( orderTable.getOrderState().equals("Delivered") || orderTable.getOrderState().equals("Cancelled") )  continue;
%>

	<div class="row" data-ng-controller="LoopController"> 
			<div class="col-md-12" style="width:100%;border:1px solid #ddd;margin-bottom:-12px;box-shadow: 1px 1px 1px #f5f5f5;background-color: #FFFFF5;">
				<div class="col-md-2" style="border: 1px solid #EAEAEA; box-shadow: 1px 1px 1px #e7e7e7; margin-top:10px; margin-bottom: 11px;">
					<img alt="image" src="IconImageFromProduct?productId=<%=orderTable.getProductId() %>" width="150" height="150" />
				</div>
				<div class="col-md-7">
					<div class="col-md-6">
						<h3 style="margin-bottom: 7px;margin-top: 8px;color: #337ab7; font-size: 16px;">  Order Id : <%=orderTable.getOrderId() %>  </h3> <hr>
						<span>   Id : <%=orderTable.getId() %>   </span>  
						<span style=""></span>					
						<hr>					
						<span style="margin-right:15px;">Product Id : <%=orderTable.getProductId() %> </span> <br> <hr>
						<span>  Each Price : <%=orderTable.getSellPrice() %> </span> 
						<span style="margin-left:33px;">  Total Price : <%=orderTable.getSellPrice() * orderTable.getQty() %> </span> 
						<br> <hr>
						<span>Warranty  : <%=orderTable.getWarranty() %></span>
						
					</div>					
					<div class="col-md-5" style="margin-top: 20px;">
					
						<span>Shipping Cost : Rs <%=orderTable.getShippingCost() %></span>
						<hr>
						<span>Quantity : <%=orderTable.getQty() %></span>
						<% if (!orderTable.getSize().equals("0")) { %>
						<span style="margin-left: 74px;">Size : <%=orderTable.getSize() %></span>
						<% } %>
						<hr>
						<span>Order  Booked On : <%=orderTable.getBookedDateTime() %></span>
						<hr>						
						
						
						<a class="tooltip" style="font-size: medium;">
							Customer Details (Bring mouse on it)
							<span>
								 <span><%=orderTable.getDeliveryAddress().getfName()    %>                 </span> 
								 <span><%=orderTable.getDeliveryAddress().getlName()    %>                 </span><br>
								 <span><%=orderTable.getDeliveryAddress().getContact()  %>                 </span><br>
								 <span><%=orderTable.getDeliveryAddress().getAddress()  %>                 </span><br>
								 <% if (orderTable.getDeliveryAddress().getAddress1() != null) { %>
								 <span><%=orderTable.getDeliveryAddress().getAddress1() %>                 </span><br>
								 <% } %>									 
								 <span><%=orderTable.getDeliveryAddress().getCity()     %>                 </span>
								 <span>     -                                                              </span>
								 <span><%=orderTable.getDeliveryAddress().getPin()      %>                 </span><br>
								 <span><%=state.getStateName(orderTable.getDeliveryAddress().getState()) %></span><br>	
								 <span><%=orderTable.getDeliveryAddress().getEmail()    %>                 </span>						
							</span>
						</a>					
						
											
						<hr>
						
						
					</div>					
				
				
				
				<img data-ng-show="loader" alt="loader" src="images/loader1.gif" style="width: 40px; height: 40px; margin-top: 40px;" />
				
				</div>
				<!-- -----------------Generate Track ID (COD)--------------------------------------------- -->
				
				<% if (orderTable.getOrderState().equals("Booked") && orderTable.getPaymentType().equals("COD")) { %>
				<div data-ng-init="trackNumberCOD=true"></div>
				<% } else { %>
				<div data-ng-init="trackNumberCOD=false"></div>
				<% } %>
				<% if (orderTable.getOrderState().equals("Booked") && orderTable.getPaymentType().equals("COD")) { %>
				<div class="col-md-3" style="margin-top:58px;" data-ng-show="trackNumberCOD">
					<a data-ng-click="generateTrackNumberCOD(<%=orderTable.getId() %>)" style="width: 50% !important;  padding: 9px 10px;background: linear-gradient(#5cb85c, #5cb85c 60%, #5cb85c);border: 1px solid #0098fe;color:#ffffff;margin-top:18px;cursor: pointer;">Generate Track ID (COD)</a>
					<div data-ng-bind="msg1" style="margin-top: 12px;color: red;"></div>
				</div>
				<% } %>
								
				<!-- -----------------Generate Track ID (BANK)--------------------------------------------- -->
				
				<% if (orderTable.getOrderState().equals("Booked") && orderTable.getPaymentType().equals("BANK")) { %>
				<div data-ng-init="trackNumberBANK=true"></div>
				<% } else { %>
				<div data-ng-init="trackNumberBANK=false"></div>
				<% } %>
				<% if (orderTable.getOrderState().equals("Booked") && orderTable.getPaymentType().equals("BANK")) { %>
				<div class="col-md-3" style="margin-top:58px;" data-ng-show="trackNumberBANK">
					<a data-ng-click="generateTrackNumberBANK(<%=orderTable.getId() %>)" style="width: 50% !important;  padding: 9px 7px;background: linear-gradient(#5cb85c, #5cb85c 60%, #5cb85c);border: 1px solid #0098fe;color:#ffffff;margin-top:18px;cursor: pointer;">Generate Track ID (BANK)</a>
					<div data-ng-bind="msg1" style="margin-top: 12px;color: red;"></div>
				</div>
				<% } %>
				
				<!-- -------------------------------------------------------------------------------------------- -->
				
				
				
				
				
				
				                                                                
				<!-- -------------------Pick Up - Print Label - Delete Shipment    (COD)   -------------------------------- -->		
				
				<% if (orderTable.getOrderState().equals("Pickup") && orderTable.getPaymentType().equals("COD")) { %>
				<div data-ng-init="pickupLabelCOD=true"></div>
				<% } else { %>
				<div data-ng-init="pickupLabelCOD=false"></div>
				<% } %>
				
				<div class="col-md-3" style="margin-top:58px;" data-ng-show="pickupLabelCOD">
					<a data-toggle="modal" data-target="#myModal<%=i%>" 
						style="padding: 3px 0px;background:linear-gradient(#54b4eb, #2fa4e7 60%, #1d9ce5);border: 1px solid #0098fe;color:#ffffff;margin-top:18px;cursor: pointer;">PckupReq</a> 
					<a data-ng-click="generatePDF(<%=orderTable.getId() %>)"
						style="padding: 3px 2px;background:linear-gradient(#54b4eb, #2fa4e7 60%, #1d9ce5);border: 1px solid #0098fe;color:#ffffff;margin-top:18px;cursor: pointer;">PrtLblRet</a>
					<a data-ng-click="generatePDFRet(<%=orderTable.getId() %>)"
						style="padding: 3px 3px;background:linear-gradient(#54b4eb, #2fa4e7 60%, #1d9ce5);border: 1px solid #0098fe;color:#ffffff;margin-top:18px;cursor: pointer;">PrtLbl</a>  <br><br>					
					<a style="padding: 3px 12px;background:linear-gradient(#54b4eb, #2fa4e7 60%, #1d9ce5);border: 1px solid #0098fe;color:#ffffff;margin-top:18px;cursor: pointer;">Cancel Shipment (COD)</a>	
									
				</div>
				
				
				<!-- -------------------Pick Up - Print Label - Delete Shipment    (BANK)   ------------------------------ -->		
				
				<% if (orderTable.getOrderState().equals("Pickup") && orderTable.getPaymentType().equals("BANK")) { %>
				<div data-ng-init="pickupLabelBANK=true"></div>
				<% } else { %>
				<div data-ng-init="pickupLabelBANK=false"></div>
				<% } %>
				
				<div class="col-md-3" style="margin-top:58px;" data-ng-show="pickupLabelBANK">
					<a data-toggle="modal" data-target="#myModal<%=i%>" 
						style="padding: 3px 0px;background:linear-gradient(#54b4eb, #2fa4e7 60%, #1d9ce5);border: 1px solid #0098fe;color:#ffffff;margin-top:18px;cursor: pointer;">PckupReq</a> 
					<a data-ng-click="generatePDF(<%=orderTable.getId() %>)"
						style="padding: 3px 2px;background:linear-gradient(#54b4eb, #2fa4e7 60%, #1d9ce5);border: 1px solid #0098fe;color:#ffffff;margin-top:18px;cursor: pointer;">PrtLbl</a><br><br>
					<%-- <a data-ng-click="generatePDFRet(<%=orderTable.getId() %>)"
						style="padding: 3px 3px;background:linear-gradient(#54b4eb, #2fa4e7 60%, #1d9ce5);border: 1px solid #0098fe;color:#ffffff;margin-top:18px;cursor: pointer;">PrtLblRet</a>  <br><br> --%>
					<a style="padding: 3px 12px;background:linear-gradient(#54b4eb, #2fa4e7 60%, #1d9ce5);border: 1px solid #0098fe;color:#ffffff;margin-top:18px;cursor: pointer;">Cancel Shipment (BANK)</a>					
				</div>
				
				
				<!-- -------------------------------------------------------------------------------------------- -->
				
				
				
				
				
				
				
				<!-- -------------------  Picked Up    ------------------------------ -->		
				
				<% if (orderTable.getOrderState().equals("Picked")) { %>
				<div data-ng-init="trackParcel=true"></div>
				<% } else { %>
				<div data-ng-init="trackParcel=false"></div>
				<% } %>
				
				<div class="col-md-3" style="margin-top:58px;" data-ng-show="trackParcel">
					<a data-ng-click="trackingDetails(<%=orderTable.getId() %>)"
						data-toggle="modal" data-target="#myModalJ1<%=i%>" style="padding: 9px 79px;background:linear-gradient(#54b4eb, #2fa4e7 60%, #1d9ce5);border: 1px solid #0098fe;color:#ffffff;margin-top:18px;cursor: pointer;">Track Parcel</a><br><br>
					<a style="padding: 9px 35px;background:linear-gradient(#54b4eb, #2fa4e7 60%, #1d9ce5);border: 1px solid #0098fe;color:#ffffff;margin-top:18px;cursor: pointer;">Delete Shipment (BANK)</a>					
				</div>
				
				
				<!-- -------------------------------------------------------------------------------------------- -->
				
				
				
				
				
				
				
				<!-- ---------------------------- Pick Up ===== Pop Up ---------------------------------------------------- -->				
				
				
				<div class="modal fade pickpop" id="myModal<%=i%>">
			    	<div class="modal-dialog">    
			      		<!-- Modal content-->
			      		<div class="modal-content" style="width: 65%; margin-left: 15%;">
			        		<div class="modal-header">
			          			<button type="button" class="close" data-dismiss="modal">&times;</button>
			          			<h4 class="modal-title" style="text-align: center; color: #FF6978">Pickup Complete</h4>
			        		</div>
			        		<!-- -------------------------------------------------------------- -->
			        		
			        		<div class="modal-body">
			       				<div class="new-login-form">  			            			
			            			<div class="tmargin20 login-btn-wrap" style="margin-left: 20%;">
			                			<input type="text"  data-ng-model="date"
			                				class="span1"   placeholder="Pick a date" style="margin-bottom: 25px;"/> 
			                			<input type="text"  data-ng-model="courierName"
			                				class=""   placeholder="Courier Name" style="margin-bottom: 25px;"/>
			                			<br>
			                			<a data-dismiss="modal" data-ng-click="changeStateToPicked(<%=orderTable.getId() %>)"
			                				style="padding: 9px 35px;background:linear-gradient(#54b4eb, #2fa4e7 60%, #1d9ce5);border: 1px solid #0098fe;color:#ffffff;cursor: pointer;"> Ok  </a>
			            			</div>
			        			</div>			        		
			      			</div>   
			      			
			      			<!-- -------------------------------------------------------------- --> 
			    		</div>
			  		</div>  
				</div>					
				
			
			
				<!-- ------------------------------------------------------------------------------------------------------ -->	
				
				
				
				<!-- ---------------------------------- Pop Up - Track Item -------------------------------------------------------------------- -->
				
				
				<div class="modal fade pickpop" id="myModalJ1<%=i%>">
			    	<div class="modal-dialog">    
			      		<!-- Modal content-->
			      		<div class="modal-content" style="width: 100%; margin-left: 15%;">
			        		<div class="modal-header">
			          			<button type="button" class="close" data-dismiss="modal">&times;</button>
			          			<h4 class="modal-title" style="text-align: center; color: #FF6978">Tracking Details</h4>
			        		</div>
			        		<!-- -------------------------------------------------------------- -->
			        		
			        		<div class="modal-body">
			       				<div class="new-login-form"> 
			       					<img data-ng-show="loader1" alt="loader" src="images/loader1.gif" style="width: 40px; height: 40px; margin-top: -14px; margin-left: 251px;" /> 			            			
			            			<p data-ng-bind="trackDetails"></p>
			            			
			        			</div>			        		
			      			</div>   
			      			
			      			<!-- -------------------------------------------------------------- --> 
			    		</div>
			  		</div>  
				</div>				
				
				<!-- ------------------------------------------------------------------------------------------------------ -->
				
				
				
				
				
				<!-- --------------------------  Cancel (COD)    ------------------------------------------- -->		
				
				<% if (orderTable.getOrderState().equals("Cancel") && orderTable.getPaymentType().equals("COD")) { %>
				<div data-ng-init="cancelParcelCOD=true; cancelledCOD=false"></div>
				<% } else { %>
				<div data-ng-init="cancelParcelCOD=false; cancelledCOD=false"></div>
				<% } %>
				
				<div class="col-md-3" style="margin-top:58px;" data-ng-show="cancelParcelCOD">
					<a
						 style="padding: 9px 42px;background: linear-gradient(#d9534f, #d9534f 60%, #d9534f);border: 1px solid #0098fe;color:#ffffff;margin-top:18px;cursor: pointer;"
						 data-ng-click="setCancelledCOD(<%=orderTable.getId() %>)">
						 Cancel Shipment (COD)
					</a><br><br>										
				</div>	
				<div class="col-md-3" style="margin-top:58px;" data-ng-show="cancelledCOD">
					<a
						 style="padding: 9px 42px;background: linear-gradient(#d9534f, #d9534f 60%, #d9534f);border: 1px solid #0098fe;color:#ffffff;margin-top:18px;cursor: pointer;">
						 Cancelled
					</a><br><br>										
				</div>				
				
				<!-- -------------------------------------------------------------------------------------------- -->
				
				<!-- -------------------  Cancel (BANK)    ------------------------------------------------------ -->		
				
				<% if (orderTable.getOrderState().equals("Cancel") && orderTable.getPaymentType().equals("BANK")) { %>
				<div data-ng-init="cancelParcelBANK=true; cancelledBANK=false"></div>
				<% } else { %>
				<div data-ng-init="cancelParcelBANK=false; cancelledBANK=false"></div>
				<% } %>
				
				<div class="col-md-3" style="margin-top:58px;" data-ng-show="cancelParcelBANK">
					<a 
						 style="padding: 9px 42px;background: linear-gradient(#d9534f, #d9534f 60%, #d9534f);border: 1px solid #0098fe;color:#ffffff;margin-top:18px;cursor: pointer;"
						 data-ng-click="setCancelledBANK(<%=orderTable.getId() %>)">
						 Cancel Shipment (BANK)
					</a><br><br>										
				</div>	
				<div class="col-md-3" style="margin-top:58px;" data-ng-show="cancelledBANK">
					<a  
						 style="padding: 9px 42px;background: linear-gradient(#d9534f, #d9534f 60%, #d9534f);border: 1px solid #0098fe;color:#ffffff;margin-top:18px;cursor: pointer;">
						 Cancelled
					</a><br><br>										
				</div>				
				
				<!-- -------------------------------------------------------------------------------------------- -->
		
			</div>
	</div>
	
	
	

<% 
		i++;
	} %>

<!-- ---------------------        End  Loop    ------------------------------------------ -->




</body>
</html>
