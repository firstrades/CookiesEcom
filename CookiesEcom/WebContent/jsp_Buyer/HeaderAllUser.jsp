<%@page import="ecom.model.User"%>
<%@page import="ecom.beans.CartAttributesBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


    
<!-- -------------------------------------Header--------------------------------------------------- -->

<div class="top_bg">
	<div class="container">
		<div class="header_top">
			<div class="top_right">
				<ul>
					<li><a href="SellerLoginPage">Stockist</a></li>|
					<li><a href="#" data-toggle="modal" data-target="#myModal">Administration</a></li>|
					<li><a href="#">24X7 Customer Care </a></li> |
					<li><a href="#">Track Order</a></li>
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
					<a href="index.jsp"> <img src="images/logoFirstTrade.png" /> </a>
				</div>
				<!-- start header_right -->
				<div class="header_right">
					<div class="rgt-bottom">
						<div class="log">
							<div class="login" >
								<div id="loginContainer"><a href="#"  data-toggle="modal" data-target="#myModal1"><span class="btn btn-success">Login</span></a>
								    <div class="modal fade" id="myModal1">
    	<div class="modal-dialog" style="width:360px;">    
      		<!-- Modal content-->
      		<div class="modal-content" style="width:330px;">
        		<div class="modal-header">
          			<button type="button" class="close" data-dismiss="modal">&times;</button>               
								        <form id="loginForm" action="CustomerLogin" method="post">
								                <fieldset id="body">
								                	<fieldset>
								                          <label for="email">Enter Registered Mobile</label>
								                          <input type="text" name="userId" id="email" placeholder="Enter Mobile" value="9007785663" />
								                    </fieldset>
								                    <fieldset>
								                            <label for="password">Password</label>
								                            <input type="password" name="password" id="password" placeholder="Enter Password" value="12345" />
								                     </fieldset>
								                    <input type="submit" id="login" value="Sign in">
								                	<label for="checkbox"><input type="checkbox" id="checkbox"> <i>Remember me</i></label>
								            	</fieldset>
								            <span><a href="#">Forgot your password?</a></span>
										</form>
									</div>
									
								</div>
							</div>
						</div>
						</div>
						</div>
						</div>
						
						<div class="reg">
							
							<div id="loginContainer">
								<!-- <a href="CustomerRegistrationPage"  id="loginButton" style="background-color: #099C0C;color: #fff;padding: 1px 0px;"> -->
								<a href="#"  data-toggle="modal" data-target="#myModal2">
									<span class="btn btn-warning">REGISTER</span>
								</a>
								
								<div class="modal fade" id="myModal2">
    	<div class="modal-dialog" style="width:360px;">    
      		<!-- Modal content-->
      		<div class="modal-content" style="width:330px;">
      		<h4 style="font-size: 21px;color: #7B3F28; text-decoration: underline;">Register Panel</h4>
        		<div class="modal-header">
          			<button type="button" class="close" data-dismiss="modal">&times;</button>               
								        <form id="loginForm" action="customerMinimumRegistration" method="post">
								                <fieldset id="body">
								                	<fieldset>
								                          <label for="email">Enter Mobile for Registration</label>
								                          <input type="text" name="userId" id="email" placeholder="Enter Mobile"  />
								                    </fieldset>
								                    <fieldset>
								                          <label for="email">Your Name</label>
								                          <input type="text" name="name" id="name" placeholder="Your Name"  />
								                    </fieldset>								                    
								                    <fieldset>
								                            <label for="password">Password</label>
								                            <input type="password" name="password" id="password" placeholder="Enter Password"  />
								                     </fieldset>
								                    <input type="submit" id="login" value="Register" style="background:green;margin-left: 33%;">
								                	
								            	</fieldset>
								            
										</form>
									</div>
									
								</div>
							</div>
						</div> 
							</div>
						</div>
						<div class="cart box_1">
							<%								
							
								CartAttributesBean cartAttributesBean = CartAttributesBean.getInstance();
								String userId = request.getRemoteAddr();								
								int totalQty       = cartAttributesBean.getTotalQty(userId); 
								double totalAmount = cartAttributesBean.getTotalAmount(userId);
							%>
							<h3>
								<a href="AddToCartOrWishlist?cartOrWishlist=cart">
									<span id="amount">CART INR <%=totalAmount %></span> <br>(<span id="qty" class=""><%=totalQty %></span> items)<img src="images/bag.png" alt="">
									<!-- <i class="fa fa-shopping-cart fa-2x"></i> -->
								</a>
							</h3>	
							
							<div class="clearfix"> </div>
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



<!-- ------------------------------------------Administration----------------------------------------------- -->


<div class="container">  
	<!-- Modal -->
	<div class="modal fade" id="myModal">
    	<div class="modal-dialog">    
      		<!-- Modal content-->
      		<div class="modal-content">
        		<div class="modal-header">
          			<button type="button" class="close" data-dismiss="modal">&times;</button>
          			<h4 class="modal-title">ADMINISTRATION &nbsp;LOGIN</h4>
        		</div>
        		<!-- -------------------------------------------------------------- -->
        		<form action="Administration" method="post">
	        		<div class="modal-body">
	       				<div class="new-login-form">           
	            			<div class="login-input-wrap">
	                			<input type="text" name="userId" class="fk-input login-form-input user-email" autocomplete="on" placeholder="Enter email/mobile" value="admin">               
	            			</div>         
	            			<div class="tmargin10 login-input-wrap">
	                			<input type="password" name="password" class="fk-input login-form-input user-pwd" autocomplete="off" placeholder="Enter password" value="12345">           
	            			</div>
	            			<div class="tmargin20 login-btn-wrap">
	                			<input type="submit" class="btn btn-success" value="Login" style="padding: 6px 20px;text-transform: uppercase;background-color: #E2B757;
	    								border-color: #B18E3F;"> &nbsp;&nbsp;&nbsp;
	                			<a class="frgt-pswd fk-font-12 lpadding20" href="javascript:void(0)" style="font-size:10px;text-transform: uppercase;">forgot password?</a>
	            			</div>
	        			</div>
	        			<div class="modal-footer">
	          				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        			</div>
	      			</div>   
      			</form>  
      			<!-- -------------------------------------------------------------- --> 
    		</div>
  		</div>  
	</div>
</div>


<!-- ------------------------------------------End Administration----------------------------------------------- -->

    
<!-- -------------------------------------End Header------------------------------------------ -->






