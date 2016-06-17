<%@page import="ecom.common.Conversions"%>
<%@page import="ecom.DAO.Buyer.BuyerSearchDAO"%>
<%@page import="ecom.model.WholeSaleOffer"%>
<%@page import="ecom.model.CartWishlist"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="ecom.beans.TransientData"%>
<%@page import="ecom.model.TwoObjects"%>
<%@page import="ecom.DAO.User.UserDAO"%>
<%@page import="ecom.model.Product"%>
<%@page import="java.util.List"%>
<%@page import="ecom.common.FrequentUse"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Cart</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="keywords" content="First Trades" />
	<!-- start menu -->
	<link href="css/megamenu.css" rel="stylesheet" type="text/css" media="all" />	
	<link rel="stylesheet" href="css/flexslider.css" type="text/css" media="screen" />
	<link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
	
	<link href="<%=FrequentUse.style %>" rel='stylesheet' type='text/css' />	
	<script src="<%=FrequentUse.jQuery%>" type="text/javascript"></script>	
	<script type="text/javascript" src="js_Buyer/CartWishlist.js"></script>
		
	<!-- Custom Theme files -->
	<!--//theme-style-->	
	<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>	
	<script type="text/javascript" src="js/megamenu.js"></script>
	<script>$(document).ready(function(){$(".megamenu").megamenu();});</script>
	<script src="js/menu_jquery.js"></script>
	
	
	
	<style>
		.details{
			font-size:12px;
			color: #999;
			margin-right:13px;
		}
		hr{
			border-top:1px solid #EEE;
			margin-top: 10px;
		    margin-bottom: 10px;
		}
	
	   

.btn-primary {
    color: #fff;
    background-color: #31708f;
    border-color: #357ebd;
}
.btn-orange{

    
    background-color: #ff6978;
    font-size: 13px;
    color: #ffffff;
}
.btn-orange:hover{
    background: #00405d;
    text-decoration: none;
    color: #ffffff;
}
.bd{
float: left;
}

	</style>
</head>

<%
	User user = (User) session.getAttribute("user");
%>

<body>

<!-- -------------------------------------Header------------------------------------------------------ -->
<% if (user == null) { %>

	<!-- Header -->
	<%@ include file="HeaderAllUser.jsp" %>
	<!-- End Header -->

<% } else { %>

	<!-- Header -->
	<%@ include file="Header.jsp" %>
	<!-- End Header -->

<% } %>
<!-- -------------------------------------End Header-------------------------------------------------- -->

<%
	@SuppressWarnings("all")
	List<TwoObjects<Product, CartWishlist>> productBeanAndCW = (List<TwoObjects<Product, CartWishlist>>) request.getAttribute("productBeanAndCW");
	@SuppressWarnings("all")
	List<TwoObjects<BigDecimal, String>> apiDataList = (List<TwoObjects<BigDecimal, String>>) request.getAttribute("apiDataList");
%>
<div class="container">
<div class="cart_details">
<div id="tabs-container">
    <ul class="tabs-menu">
        <li class="current"><a href="#tab-1">Cart(1)</a></li>        
    </ul>
    <div class="tab">
        <div id="tab-1" class="tab-content">
            <table width="100%" cellpadding="0" cellspacing="0" class="table table-border" style="border:1px solid #ccc; margin-bottom: 0px;">
				<thead class="cartpage">
					<tr>
 						<td class="item-cell" colspan="2"><span class="lpadding10">Item</span></td> 
						 <td class="qty-cell head-cell lborder">Qty</td>
						 <td class="price-cell head-cell lborder">Unit Price</td>
						 <td class="head-cell delivery-cell lborder">Delivery Details</td>
						 <td class="head-cell subtotal-cell lborder">Subtotal</td>
					</tr>
				</thead>
				<tbody class="cart-body">
				
				<% 
					int i = 0;
				
					for (TwoObjects<Product, CartWishlist> productBeanAndQty : productBeanAndCW) { 
				
						TwoObjects<BigDecimal, String> apiData = apiDataList.get(i);
						long productId = productBeanAndQty.getObj1().getProductId();
						double salePriceCustomer = productBeanAndQty.getObj1().getPrice().getSalePriceCustomer();
						long sellerId = productBeanAndQty.getObj1().getSellerId();
						int qty = productBeanAndQty.getObj2().getQty();						
						
						String sellerCompany = UserDAO.getInstance().getSellerCompany(sellerId);
						int    stock         = TransientData.getStock(productId);    
											
						WholeSaleOffer wholeSaleOffer = WholeSaleOffer.getWholeSaleOffer(productId);
						
						double subtotal = 0;
						if (wholeSaleOffer != null && wholeSaleOffer.getQty() <= qty) {
							subtotal = ((salePriceCustomer * qty) * (1 - (wholeSaleOffer.getDiscount() / 100))) + apiData.getObj1().doubleValue(); 
						} else {
							subtotal = (salePriceCustomer * qty) + apiData.getObj1().doubleValue();
						}
						subtotal = Conversions.round(subtotal, 2);
						
						
				%>
 					<tr class="item-row">
 						<td colspan="2" class="product-info" style="width: 45%;">
 							<div class="carty-image">
 								<a href="CompleteProductDetails?subCategory=<%=productBeanAndQty.getObj1().getSubCategory() %>&productId=<%=productId %>">
									<img src="IconImageFromProduct?productId=<%=productBeanAndQty.getObj1().getProductId() %>" alt="Face Cream"/>
								</a>
 							</div>
 							<a href="CompleteProductDetails?subCategory=<%=productBeanAndQty.getObj1().getSubCategory() %>&productId=<%=productId %>">
 								<span class="fk-bold"><%=productBeanAndQty.getObj1().getProductName() %>(<%=productBeanAndQty.getObj1().getCompanyName() %>)</span> <br>
 								
 							</a>
 							<span class="fk-bold"><%=sellerCompany %></span><br>
 							<!-- --------------------------------------------------------------------------------- -->
 							<span class="fk-bold">Product ID: <%=productId %></span>
 							
 							<!-- ---- Size------- -->
 							<% if (!productBeanAndQty.getObj2().getSize().equals("")) { %>
 								<br><span class="fk-bold">Size: <%=productBeanAndQty.getObj2().getSize() %></span>
 							<% } %>
 							
 							<!-- WholeSale Qty and Discount -->
 							<% if (wholeSaleOffer != null) { %>
 								<br><span class="fk-bold">WholeSale Discount <%=wholeSaleOffer.getDiscount() %>% on Qty  <%=wholeSaleOffer.getQty() %> or more.</span>
 							<% } %>
 							
 							<!-- Customer gets wholeSale Price -->
 							<% if (wholeSaleOffer != null && wholeSaleOffer.getQty() <= qty) { %>
 								<div style="display: display;color:green;" class="wholeSale">You are qualified for discount <%=wholeSaleOffer.getDiscount() %> %</div>
 							<% } else { %>
 								<div style="color:green;" class="wholeSale"><!-- String from jQuery --></div>
 							<% } %> 
 							
 							<!-- ---- Stock------- -->
 							<% if (stock == 0) { %>
 								<span class="stock" style="position: relative;top: 47px;right: 207px;color: red; font-size: 12px; margin-top: -13px;">OUT OF STOCK </span>
 							<% } else {%>
 								<span class="stock" style="position: relative;top: 47px;right: 207px;color: red; font-size: 12px; margin-top: -13px;"> </span>
 							<% } %>
 							<!-- --------------------------------------------------------------------------------- -->
							<a href="#" class="remove cart <%=productBeanAndQty.getObj1().getProductId() %>"  
									title="Remove Item" style="float: right; margin-top:47px; color: #FF6978;"> Remove </a>							
 						</td>
 						<td class="cell qty-cell carty-changeQuantity" style="width: 4.50%;">
 						
 							<input type="text" class="qty" value="<%=productBeanAndQty.getObj2().getQty() %>" style="width: 100%; padding: 0px 5px;"/>
 							<input type="hidden" value="<%=productBeanAndQty.getObj1().getPrice().getSalePriceCustomer() %>" class="salePriceChange"/>
 							<input type="hidden" name="itemNo" value="<%=i %>" />
 							<input type="hidden" name="id" value="<%=productBeanAndQty.getObj2().getId() %>" />
 							<a style="font-size: 12px;display: none; cursor: pointer;" class="save">save </a>
 						</td>
 						<td class="cell price-cell">			 				
							Rs. <%=productBeanAndQty.getObj1().getPrice().getSalePriceCustomer() %>	
	 					</td>
 						<td class="cell delivery-cell api">
 							<div class="fk-fontlight">
 								<% if (apiData.getObj1().doubleValue() == 0) { %>
 									<strong class="price fk-font-14">Free Shipping</strong>
 								<% } else { %>
 									<strong class="price fk-font-14">Rs. <%=apiData.getObj1() %></strong>
 								<% } %>
 								<br><p class="fk-font-11 fk-fontlight tmargin5 delivery">Delivery: &nbsp;&nbsp; <%=apiData.getObj2() %></p> 
 							</div>
 							
 						</td>
 						<td>
 						<div class="bd"> 							
 							Rs. <%=subtotal %>  							
 						</div>
 						<br>
 							<div class="place_order" style="margin-top:39px;"> 
 							
 								<form action="AddToCartOrWishlist" method="post">
 								
 									<input type="hidden" name="move" value="move"/>
 									<input type="hidden" name="productId"      value="<%=productBeanAndQty.getObj1().getProductId() %>"/>
 									<input type="hidden" name="cartOrWishlist" value="cart"/>
 									<input type="hidden" name="size" value="<%=productBeanAndQty.getObj2().getSize() %>"/>
 									<input type="submit" class="btn btn-orange"  value="Add to wishlist" style="padding: 2px 5px; float: right;"/>
 								</form>
  								
							</div> 
 						</td>
 					</tr>
 					
 				<%
 						i++;
					} 
				%>
				</tbody>
			</table>

			<div class="cart-item-summary padding10 text_right fk-font-21"> 
				<span class="cart_light_grey lmargin80" style="color: #999;">Estimated Total:</span>
				<%
					CartAttributesBean cartAttributesBean = CartAttributesBean.getInstance();
					String userId = request.getRemoteAddr();
					if (user != null) {
						userId = String.valueOf(user.getUserInfo().getId());
					} 
					int totalQty       = cartAttributesBean.getTotalQty(userId); 
					double totalAmount1 = cartAttributesBean.getTotalAmount(userId);
				%>	
			 	<span class="fk-bold" style="font-size:24px; font-weight: bold; color: #555;" id="totalsun">Rs. <%=totalAmount1 %></span> 
			 	<div class="fk-font-11 fk-italic">
			 		<a class="find-del-charge"> + Delivery Charge?</a> 
			 	</div> 
			</div>
 
 
			<div class="add_cart">
			 	<div class="price_add">	
					<input type="text" name="pincode" class="input_text" placeholder="Enter Your pin Code" style="float:left;"/>	                        
				 	<div class="btn_form">
						<a href="#" style="padding:6px 20px;">CHECK</a>
					</div>
				</div>
			
				<div class="unitExt"> 
					<div class="Shopping">
			 			<a href="BuyerMainPanel"><button class="btn btn-primary">&lt; Continue Shopping</button></a> 
					</div>
			  		<div class="place_order"> 
			  			<a href="OrderReview_Cart?cartOrWishlist=cart"><button type="submit" class="btn btn-orange">Place Order</button> </a>
					</div> 
				</div>			 
			</div>
        </div>        
    </div>
</div>
</div>
</div>
<!-- -------------------------------------Footer------------------------------------------------------ -->
<%@ include file="Footer.jsp" %>
<!-- -------------------------------------End Footer-------------------------------------------------- -->
</body>
</html>