<%@page import="ecom.model.User"%>
<%@page import="ecom.beans.CartAttributesBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<%
	User user1 = (User) session.getAttribute("user");
%>
<!-- -------------------------------------Header--------------------------------------------------- -->
<div class="top_bg">
	<div class="container">
		<div class="header_top">
			<div class="top_right">
				<ul>
					<li><a href="#">Help</a></li>|
					<li><a href="#">Contact</a></li>|
					<li><a href="#">Delivery information</a></li>
				</ul>
			</div>
			<div class="top_left">
				<h2><span></span> Call us :  (+91) - 7890000448 / 9831016768 /7890020448 &nbsp;;
				<a  style="color:#ffffff;"> mail:  suport@firstrades.com  &nbsp; info@firstrades.com 
</a> </h2>
			</div>
				<div class="clearfix"> </div>
		</div>
	</div>
</div>
<!-- header -->
<div class="header_bg">
	<div class="container">
		<div class="header">
			<div class="head-t">
				<div class="logo">
					<a href="BuyerMainPanel"> <img src="images/logoFirstTrade.png" /> </a>
				</div>
				<!-- start header_right -->
				<div class="header_right">
					<div class="rgt-bottom">
						<div class="log">
							<div class="login" >
								<div id="loginContainer">
								<ul class="menu">
								   <li class="no-border greeting-link"><a class="no-border" href="#">Hi <%=user1.getPerson().getFirstName() %>!</a>
						             <ul class="dropdown">
						                <li><a href="#">Account</a></li>
						                <li><a href="AddToCartOrWishlist?cartOrWishlist=cart&size=0">Cart</a></li>
						                <li><a href="OrderHistroy">Orders</a></li>
						                <li><a href="#">Wallet</a></li>
						                <li><a href="AddToCartOrWishlist?cartOrWishlist=wishlist&size=0">Wishlist</a></li>                
						                <li><a href="index.jsp">Log Out</a></li>
						             </ul>
						           </li>
            </ul>
								</div>
							</div>
						</div>
						
						<!-- ----------Cart INR and Qty------------- -->
						<div class="cart box_1" style="float: left;margin-left: 38px;">
							<%
								CartAttributesBean cartAttributesBean = CartAttributesBean.getInstance();
								String userId = request.getRemoteAddr();
								if (user1 != null) {
									userId = String.valueOf(user1.getUserInfo().getId());
								} 
								int totalQty       = cartAttributesBean.getTotalQty(userId); 
								double totalAmount = cartAttributesBean.getTotalAmount(userId);
							%>
								<h3>
									<a href="AddToCartOrWishlist?cartOrWishlist=cart">
										<span id="amount">CART INR <%=totalAmount %></span> <br>(<span id="qty" class=""><%=totalQty %></span> items)<img src="images/bag.png" alt="">
									</a>
								</h3>								
								<div class="clearfix"> </div>
						</div>
						<!-- ----------End Cart INR and Qty-------- - -->
						<div class="create_btn">
							<a href="index.jsp">CHECKOUT</a>
						</div>
						<div class="clearfix"> </div>
					</div>
					<div class="search">
					    <form method="post" action="searchProductsWithKeyWords">
					    	<input type="text" name="keyWords" placeholder="search..." id="tags">
							<input type="submit" value="">
						</form>
					</div>
					<div class="clearfix"> </div>
				</div>
				<div class="clearfix"> </div>
			</div>
    	</div>
    </div>
</div>
<!-- -------------------------------------End Header------------------------------------------ -->

