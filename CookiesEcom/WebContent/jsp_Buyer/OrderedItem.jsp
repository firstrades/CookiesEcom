<%@page import="ecom.model.OrderedItems"%>
<%@page import="ecom.model.User"%>
<%@page import="ecom.beans.CartAttributesBean"%>
<%@page import="ecom.model.Product"%>
<%@page import="java.util.List"%>
<%@page import="ecom.common.FrequentUse"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>POST ORDER</title>

<link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
	<script type="text/javascript" src="<%=FrequentUse.jQuery %>"></script>
	<!-- Custom Theme files -->
	<link href="<%=FrequentUse.style %>" rel='stylesheet' type='text/css' />
		<link href="css/megamenu.css" rel="stylesheet" type="text/css" media="all" />
	<script type="text/javascript" src="js/megamenu.js"></script>
	<script>$(document).ready(function(){$(".megamenu").megamenu();});</script>
<style type="text/css">
.order-details-MP {
    background-color: #fff;
    padding: 10px 24px;
    -webkit-box-shadow: 0 0 2px 0 #d3cbb8;
    -moz-box-shadow: 0 0 2px 0 #d3cbb8;
    box-shadow: 0 0 2px 0 #d3cbb8;
}

.util_icons {
    text-align: right;
    padding-bottom: 5px;
    padding-right: 5px;
}

.util_icons .invoice.disabled {
    opacity: .5;
    filter: alpha(opactiy=50);
    -ms-filter: "alpha(opacity=50)";
    -moz-opacity: .5;
    -khtml-opacity: .5;
    cursor: default;
    text-decoration: none;
}

.util_icons .bill {
    top: 2px;
    background: url('../prod/images/order-actions-ee666452.png') no-repeat -1px -171px;
    width: 11px;
    height: 13px;
}
.line, .lastUnit {
    overflow: hidden;
    _overflow: visible;
    _zoom: 1;
}
.NewOrderStatus .order-details-MP .leftMessage {
    padding: 0 30px 0 20px;
    border-right: 1px dotted #d3cbb8;
}

.size2of5 {
   width: 40%;
   margin-right: 53px;
}
.unit {
    float: left;
    _zoom: 1;
}
.order-details-MP .payment-header {
    color: #16a494;
    font-size: 24px;
    font-weight: bold;
}
.tpadding10 {
    padding-top: 10px;
    font-size: 13px;
}
.fk-font-17 {
    font-size: 17px;
}
.boldtext, .fk-font-bold, .bold_text {
    font-weight: bold;
}
.NewOrderStatus .order-details-MP .rightMessage {
    padding: 0 20px;
}
.line, .lastUnit {
    overflow: hidden;
    _overflow: visible;
    _zoom: 1;
}

.NewOrderStatus .order-details-MP .summary-inner {
    color: #565656; /* #order-infograph li */
    line-height: 17px;
}
.fk-alert-user {
    padding: 7px 10px;
    background-color: #fffed5;
    border: 1px solid #f9e2b2;
    margin: 10px 0;
}
.NewOrderStatus .order-details-MP .delivery-icon {
    background-position: 0 -286px;
    width: 39px;
    height: 17px;
    position: relative;
    top: 2px;
}
.infograph.confirm-page {
    border: dotted #d3cbb8;
    border-width: 1px 0 0 0;
    -moz-border-radius-topleft: 0;
    -webkit-border-top-left-radius: 0;
    border-top-left-radius: 0;
    -moz-border-radius-topright: 0;
    -webkit-border-top-right-radius: 0;
    border-top-right-radius: 0;
    -webkit-box-shadow: none;
    -moz-box-shadow: none;
    box-shadow: none;
    margin-bottom: 0;
    padding-bottom: 5px;
 
}
.infograph {
    background: #fff;
    margin: 10px 10px 20px 10px;
    padding: 10px 25px;
    border: 1px solid #e3e3e3;
    -moz-border-radius-topleft: 2px;
    -webkit-border-top-left-radius: 2px;
    border-top-left-radius: 2px;
    -moz-border-radius-topright: 2px;
    -webkit-border-top-right-radius: 2px;
    border-top-right-radius: 2px;
    -webkit-box-shadow: 0 0 2px 0 #d3cbb8;
    -moz-box-shadow: 0 0 2px 0 #d3cbb8;
    box-shadow: 0 0 2px 0 #d3cbb8;
    font-size: 16px;
}
.fk-display-block {
    display: block;
}
.infograph span.inf-cancel, .infograph span.inf-return, .infograph span.inf-track {
    text-transform: uppercase;
    font-size: 12px;
    color: #333;
    font-weight: bold;
}

.infograph span {
    color: #565656;
}
.infograph .btn.myorders {
    padding: 8px 25px;
    margin-left: 20px;
    color: #f9f9f9;
}
.btn.btn-blue {
    background: #007fb8;
    border: 1px solid #004b91;
    background: -moz-linear-gradient(top,#007fb8 1%,#6ebad5 3%,#007fb8 7%,#007fb8 100%);
    background: -webkit-linear-gradient(top,#007fb8 1%,#6ebad5 3%,#007fb8 7%,#007fb8 100%);
    background: -o-linear-gradient(top,#007fb8 1%,#6ebad5 3%,#007fb8 7%,#007fb8 100%);
    background: -ms-linear-gradient(top,#007fb8 1%,#6ebad5 3%,#007fb8 7%,#007fb8 100%);
    background: linear-gradient(to bottom,#007fb8 1%,#6ebad5 3%,#007fb8 7%,#007fb8 100%);
        color: #ffffff;
}

#order-infograph {
    margin: 20px 0;
    text-align: center;
    margin-left: 334px;
}
.line, .lastUnit {
    overflow: hidden;
    _overflow: visible;
    _zoom: 1;
}
#order-infograph li.arrow {
    padding: 0 30px 0 15px;
    position: relative;
    background: #f7f5ee;
    background: transparent;
    float: left;
}

#order-infograph li {
    font-size: 17px;
    color: #565656;
    padding: 0 0 0 15px;
}

.NewOrderStatus .order-details-MP {
    background-color: #fff;
    padding: 10px 24px;
    -webkit-box-shadow: 0 0 2px 0 #d3cbb8;
    -moz-box-shadow: 0 0 2px 0 #d3cbb8;
    box-shadow: 0 0 2px 0 #d3cbb8;
}
.tmargin20 {
    margin-top: 20px;
}

.fk-text-center {
    text-align: center;
        margin-top: 10px;
    margin-bottom: 15px;
}

.fk-font-21 {
    font-size: 21px;
}
.NewOrderStatus .order_item_row_0 {
    border-bottom: 0;
    font-size: 13px;
    color: #848484;
    line-height: 17px;
}
.NewOrderStatus .order_item_row_1 {
    background: #fff;
}

.order-details-MP .order-details-table tr.total-row td {
    padding: 10px;
    border-top: 1px solid #000;
}


#order-infograph li.arrow:before {
    border-left-color: #ccc;
    border-width: 26px;
    top: 50%;
    margin-top: -26px;
}
.table-head{
    background-color: #f2f2f2;
    border: 1px solid #e6e6e6;
    padding: 8px;
}

.btn-white {
    color: #333;
    background: #f9f9f9;
    border: 1px solid #c9c9c9;
    background: -moz-linear-gradient(top,#f9f9f9 1%,#fff 3%,#f9f9f9 7%,#f2f2f2 100%);
    background: -webkit-linear-gradient(top,#f9f9f9 1%,#fff 3%,#f9f9f9 7%,#f2f2f2 100%);
    background: -o-linear-gradient(top,#f9f9f9 1%,#fff 3%,#f9f9f9 7%,#f2f2f2 100%);
    background: -ms-linear-gradient(top,#f9f9f9 1%,#fff 3%,#f9f9f9 7%,#f2f2f2 100%);
    background: linear-gradient(to bottom,#f9f9f9 1%,#fff 3%,#f9f9f9 7%,#f2f2f2 100%);
}
.btn, .btn.btn-small {
       padding: 6px 12px 4px;
    font-size: 12px;
    text-transform: uppercase;
    border-radius: 0px;
}
.order-item-title a {
    color: #333;
    font-size: 13px;
}
.item-shipment{
    vertical-align: top;
    padding-left: 91px;
    font-size: 12px;
    font-weight: bold;
    padding-top: 20px;
}
.order-item-qty{
margin-bottom: 15px;
}
 


</style>


</head>

<%

@SuppressWarnings("all")
List<OrderedItems> orderedItemsList = (List<OrderedItems>) request.getAttribute("orderedItemsList");


%>


<body>


<!-- -------------------------------------Header------------------------------------------------------ -->
<%@ include file="Header.jsp" %>
<!-- -------------------------------------End Header-------------------------------------------------- -->

<!-- Navigation -->
<%@ include file="Navigation.jsp" %>
<!-- End Navigation -->



<div class="container">
	<div class="order-details-MP tmargin10" id="order-details-MP"> <!-- Contact us and print on right -->
    	<div class="util_icons"> 
    		<a rel="tooltip" class="invoice disabled" data-msg="You can get the invoice when the item is delivered. The soft copy of the invoice will also be sent as part of the delivery confirmation mail within 24 hours of delivery.">
    			<span class="bill"></span>Email Invoice
    		</a> &nbsp;&nbsp;|&nbsp;&nbsp; 
    		<a href="#" title="Contact Us">
    			<span class="email"></span>Contact Us
    		</a> &nbsp;&nbsp;|&nbsp;&nbsp; 
    		<a id="print-order"  alt="Print this page">
    			<span class="print"></span>Print
    		</a>    	
    	</div>
        <div class="line order-info tpadding10 confirmation">
        	<div class="unit size2of5 leftMessage">
            	<h2 class="payment-header"><b>Thank you for your order!</b></h2>
            	<div class="summary">
              		<div class="tpadding10">
              			Your order has been placed and is being processed. When the item(s) are shipped, you will receive an email with the details. You can track this order through 
              			<a class="fk-underline" href="#">My orders</a> page.
              		</div>
            	</div>
            	<div class="lastUnit tpadding10 order-status"> 
            		<span class="fk-font-17 fk-font-bold"> 
            			<i class="fk-inline-block successmsg"></i> Rs. 120 
            		</span> paid through Cash on delivery 
            	</div>
          	</div>
          	<!-- Order summary block -->
          	<div class="lastUnit rightMessage">
            	<div class="line">
              		<div class="summary-inner">
                		<ul>
                  			<li class="first"> <span class="fk-font-21">Jewel Saha </span> 9007785663 </li>
                  			<li class="tpadding10"> 33/4c, B B Ghosh Sarani , Tata Communication, Hadco <br>
                    			Kolkata,
                    			West Bengal -
                    			700067<br>
                  			</li>
                		</ul>
              		</div>
              		<div class="fk-alert-user"> <span class="fk-inline-block delivery-icon">
              			</span>Your complete order will be delivered by Feb 16, 2016 
              		</div>
            	</div>
          	</div>
        </div>
        
        <a href="#" class="login-required fk-display-block infograph confirm-page"> You can now <span class="inf-track"><i></i>Track</span> , <span class="inf-cancel"><i></i>Cancel</span> &amp; <span class="inf-return"><i></i>Return</span> ordered items from <span class="contactus-btn btn btn-blue btn-medium myorders">My orders</span> </a> 
 	</div>
 	
	<ul id="order-infograph" class="line">
		<li class="order arrow">Order Placed</li>
		<li class="processing arrow">Processing</li>
		<li class="transit arrow">In-transit</li>
		<li class="transit arrow">Delivery</li>
	</ul>    
      
      
	<div class="order-details-MP order-details-bottom tmargin20">
        <p class="fk-text-center tpadding20"> <span class="fk-font-21 fk-font-bold">YOUR ORDER SUMMARY </span>1 Item</p>
        <!-- Show Big Billion Banner --> <!-- Display list of orders -->
        <div class="orderDetails-tracking tmargin10 line" style="border: 1px solid #ccc;    background-color: #FBFBFB;">
        	<table cellspacing="0" cellpadding="0" width="100%" align="left" class="order-details-table order-confirmation">
	          	<tbody>
	          	
	          	 <% 
	          	 	int totalItemsAmount = 0;
	          	 
	          	 	for (OrderedItems orderedItems : orderedItemsList) {	
	          	 		
	          	 		totalItemsAmount += orderedItems.getSellPriceWithShipping() * orderedItems.getQty();
	          	 
	          	 %>
	            	<tr class="caption nondigital order_item_row_0" align="left">
		                <th colspan="3" class="vtop product-info" align="left"> <div class="table-head clearfix">
		                    <div class="heads prod" style="float: left;font-size: 12px;margin-top: 5px;margin-left: 8px;"> <span class="text"> Order ID <a class="link" title="OD205177896853770000" href="#"><%=orderedItems.getTranxId() %></a> </span> </div>
		                    <div class="heads sub" style="float: right;font-size: 12px;margin-top: 5px;margin-right: 8px;"> <span class="text"> Seller <a class="link" href="#" title="See seller Info"><%=orderedItems.getSellerCompany() %></a> </span> </div>
		                  </div>
		                </th>
	              	</tr>
	              	<!-- List of items to display -->
	              	<tr class="order_item_row order_item_row_1 promo" align="left"> <!-- Column 1 -->
	              		<td width="37%" class="vtop item-details">
		              		<div class="line">
			                	<div class="unit item-img-div fk-text-center"> 
			                		<a href="" target="__blank"> <img src="IconImageFromProduct?productId=<%=orderedItems.getProductId() %>" /> </a> 
			                	</div>
			                    <div class="lastUnit">
			                    	<div class="lmargin10">
			                        	<p class="order-item-title"> <a href="" target="__blank"> <%=orderedItems.getProductName() + "(" + orderedItems.getCompanyName() + ")" %></a> </p>
			                        	<p class="order-item-qty smallText tmargin5"> Qty: <%=orderedItems.getQty() %> </p>
			                        	<div class="tmargin10">
			                          		<div> <a class="fk-inline-block offer-applied btn btn-white btn-small"> <span class="offer-color fk-bold green">OFFERS:</span> 1
			                            		applied </a>  
			                            	</div>
			                        	</div>
			                      </div>
			                    </div>
		                  	 </div>
	                  		<div class="tmargin20"></div>
		                  		<ul class="fk-inline-block buttons-list clearfix" style="margin-left: 20px;list-style: none;margin-bottom: 10px;">
		                    		<li class="buttons"> <a class="btn-white disabled" style="cursor: pointer; padding: 6px;">Cancel</a> </li>
		                  		</ul>
	    				</td>
	                	<!-- Column 3 -->
	                	<td width="40%" class="vtop item-shipment"> Standard Delivery <span class=""> by <span class="date">Tue, 16th Feb</span> <a class="js-sla-msg ques-mark"></a> </span></td>
	                	<!-- Column 4 -->
	                	<td width="20%" class="vtop item-details"><span class="fk-label boldtext">Rs. <%=orderedItems.getSellPriceWithShipping() * orderedItems.getQty() %></span> <a id="priceInfoLink813661">[?]</a></td>
	  				</tr>
		            <tr class="buttons_row last">
		                <td colspan="3"><div class="button_container"></div></td>
		            </tr>
		            
		         <% } %>   
		         
		            <!-- Continue Shopping -->
		            <tr class="total-row">
		            	<td> <div class="line lpadding10 bpadding10"> <a class="btn btn-blue btn-medium" href="BuyerMainPanel">Continue Shopping</a> </div>  </td>
		                <td>  </td>
		                <td  align="right" class="padding10">
		                	<span class="order_total" style="color:#007FB8;"> Total Rs. <%=totalItemsAmount %></span>
		                </td>
		            </tr>
	            </tbody>
            </table>
        </div>
        
	</div>    
      
      
</div>  <!-- container -->
      
      
<!-- -------------------------------------Footer------------------------------------------------------ -->
<%@ include file="Footer.jsp" %>
<!-- -------------------------------------End Footer-------------------------------------------------- -->  


</body>
</html></html>