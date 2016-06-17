<%@page import="ecom.model.CartWishlist"%>
<%@page import="ecom.beans.TransientData"%>
<%@page import="ecom.model.TwoObjects"%>
<%@page import="java.util.List"%>
<%@page import="ecom.DAO.User.UserDAO"%>
<%@page import="ecom.model.Product"%>
<%@page import="ecom.common.FrequentUse"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>WishList</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="keywords" content="First Trades" />
	<!-- start menu -->
	<link href="css/megamenu.css" rel="stylesheet" type="text/css" media="all" />	
	<link rel="stylesheet" href="css/flexslider.css" type="text/css" media="screen" />
	<link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
	
	<link href="<%=FrequentUse.style %>" rel='stylesheet' type='text/css' />	
	<script type="text/javascript" src="<%=FrequentUse.jQuery%>"></script>		
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
    padding: 0px 4px;
    /* text-transform: uppercase; */
    background-color: #ff6978;
    font-size: 15px;
    color: #ffffff;
}
.btn-orange:hover{
    background: #00405d;
    text-decoration: none;
    color: #ffffff;
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

%>

<div class="container">
<div class="cart_details">
<div id="tabs-container">
    <ul class="tabs-menu">
        <li class="current"><a href="#tab-1">WishList(1)</a></li>
        
    </ul>
    <div class="tab">
        <div id="tab-1" class="tab-content">
            <table width="100%" cellpadding="0" cellspacing="0" class="table table-border" style="border:1px solid #ccc; margin-bottom: 0px;">
				<thead class="cartpage">
					<tr>
 						<td class="item-cell" colspan="2"><span class="lpadding10">Item</span></td> 
						<td class="price-cell head-cell lborder">Price</td>
						<td class="head-cell delivery-cell lborder">Delivery Details</td>
						<td class="head-cell subtotal-cell lborder">Subtotal</td>
					</tr>
				</thead>
				<tbody class="cart-body">
				<% for (TwoObjects<Product, CartWishlist> productBeanAndQty : productBeanAndCW) { 
				
						UserDAO userDAO = UserDAO.getInstance();
						String sellerCompany = userDAO.getSellerCompany(productBeanAndQty.getObj1().getSellerId());
						
						int stock = TransientData.getStock(productBeanAndQty.getObj1().getProductId());
				%>
 					<tr class="item-row">
 						<td colspan="2" class="product-info" style="width: 45%;">
 							<div class="carty-image">
								<a href="CompleteProductDetails?subCategory=<%=productBeanAndQty.getObj1().getSubCategory() %>&productId=<%=productBeanAndQty.getObj1().getProductId() %>">
									<img src="IconImageFromProduct?productId=<%=productBeanAndQty.getObj1().getProductId() %>" alt="Face Cream"/>
								</a>
 							</div>
 							<a href="CompleteProductDetails?subCategory=<%=productBeanAndQty.getObj1().getSubCategory() %>&productId=<%=productBeanAndQty.getObj1().getProductId() %>">
 								<span class="fk-bold"><%=productBeanAndQty.getObj1().getProductName() %>(<%=productBeanAndQty.getObj1().getCompanyName() %>)</span> <br>
 							</a>
 							<span class="fk-bold" style="float: left;"><%=sellerCompany %></span><br>
 							<span class="fk-bold">Product ID: <%=productBeanAndQty.getObj1().getProductId() %></span>
 							<% if (!productBeanAndQty.getObj2().getSize().equals("0")) { %>
 							<br><span class="fk-bold">Item Size: <%=productBeanAndQty.getObj2().getSize() %></span>
 							<% } %>
 							
							<a href="#" class="remove wishlist <%=productBeanAndQty.getObj1().getProductId() %>"  
									title="Remove Item" style="float: right; margin-top:51px; color: #FF6978;"> Remove </a> 
							<% if (stock == 0) { %>
							<span style='color:red;float:left;margin-top: 51px;position: relative;right: 202px;'>OUT OF STOCK</span>
 							<% } %>
 						</td>
 						
 						<td class="cell price-cell">
 							<!-- Printing all delivery details here -->
							Rs. <%=productBeanAndQty.getObj1().getPrice().getSalePriceCustomer() %>	
 						</td>
 						<td class="cell delivery-cell">
 							<div class="fk-fontlight">
 								<strong class="price fk-font-14">Rs. 100</strong>
 							</div>
 							<p class="fk-font-11 fk-fontlight tmargin5">Delivered in 4-5 business days.</p> 
 						</td>
 						<td>
 							Rs. <%=productBeanAndQty.getObj1().getPrice().getSalePriceCustomer() %> <br>
 							<div class="place_order" style="margin-top:39px;"> 
 							
 								<form action="AddToCartOrWishlist" method="post">
 								
 									<input type="hidden" name="move" value="move"/>
 									<input type="hidden" name="productId"      value="<%=productBeanAndQty.getObj1().getProductId() %>"/>
 									<input type="hidden" name="cartOrWishlist" value="wishlist"/>
 									<input type="hidden" name="size" value="<%=productBeanAndQty.getObj2().getSize() %>"/>
 									<input type="submit" class="btn btn-orange" style="" value="Add to Cart" />
 								</form>
  								
							</div> 
 						</td>
 					</tr>
 				<%  } %>
				</tbody>
			</table>

 
 
 <div class="add_cart">
 	<div class="price_add">
	
	<input type="text" name="pincode" class="input_text" placeholder="Enter Your pin Code" style="float:left;">	                        
	 <div class="btn_form">
	<a href="#" style="padding:6px 20px;">CHECK</a>
	</div>
</div>

<div class="unitExt"> 
<div class="Shopping">
	<a href="BuyerMainPanel"><button class="btn btn-primary">&lt; Continue Shopping</button> </a>
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