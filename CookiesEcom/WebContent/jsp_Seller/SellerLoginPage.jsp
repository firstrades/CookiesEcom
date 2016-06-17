<%@page import="ecom.common.FrequentUse"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<title>Seller Login</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="keywords" content="First Trades" />
	<!-- start menu -->
	<link href="css/megamenu.css" rel="stylesheet" type="text/css" media="all" />	
	<link rel="stylesheet" href="css/flexslider.css" type="text/css" media="screen" />
	<link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
	<link href="<%=FrequentUse.style %>" rel='stylesheet' type='text/css' />
	
	<script type="text/javascript" src="<%=FrequentUse.jQuery%>"></script>		
	<!-- Custom Theme files -->
	<!--//theme-style-->	
	<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>	
	<script type="text/javascript" src="js/megamenu.js"></script>
	<script>$(document).ready(function(){$(".megamenu").megamenu();});</script>
	<script src="js/menu_jquery.js"></script>

</head>
<body>

<header id="navbar" role="banner" class="navbar navbar-default">
  <div class="container">
    <div class="navbar-header">
    <a href="/" title="Home" class="logo navbar-btn pull-left"><img src="images/logoFirstTrade.png" alt="Home"></a><!-- .btn-navbar is used as the toggle for collapsed navbar content-->
      
    </div>
    <div class="navbar-collapse collapse">
      <nav role="navigation">
        <ul class="menu nav navbar-nav">
          <li class="first leaf"><a href="index.jsp" title="" class="active">Home</a></li>
          <li class="last leaf"><a href="#">Sell Smart</a></li>
          <li><a href="#">pricing</a></li>
          <li><a href="#">faqs</a></li>
          <li><a href="#">benefits</a></li>
          <li><a href="#" target="_blank">Firstrades.com</a></li>
        </ul>
        <div class="region region-navigation">
          <div id="forgotPasswordModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" class="modal fade">
            <div class="modal-dialog">
              <div class="modal-content">
                <div class="modal-header">
                  <button type="button" data-dismiss="modal" class="close"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
                  <h4 id="myModalLabel" class="modal-title">Forgot your password ?</h4>
                </div>
                <div class="modal-body">
                  <div id="forgotPasswordError" style="display:none;" class="alert alert-danger"></div>
                  <div id="forgotPasswordSuccess" style="display:none;" class="alert alert-success"></div>
                  <p>Enter your email address to receive a link for creating a new password</p>
                  
                  
                  <form id="forgotPasswordForm" method="post" action="#" class="form-horizontal" novalidate="novalidate">
                    <div class="control-group forgot-field">
                      <label style="text-align:right;" class="control-label">Your email address:</label>
                      <div class="controls">
                        <input id="emailId" type="text" name="forgotPasswordEmail">
                        <div class="helpMsgLeft small">Please provide the same email address with which you registered on Flipkart Markeplace</div>
                      </div>
                    </div>
                    <div class="control-group">
                      <div class="controls">
                        <button id="forgotPasswordButton" type="submit" class="btn btn-primary fixed-width-btn">Submit</button>
                        
                      </div>
                     
                      
                    </div>
                  </form>
                  
                  
                </div>
              </div>
            </div>
          </div>
          <section id="block-slp-blocks-slp-login-form" class="block block-slp-blocks clearfix">
            <h2 class="block-title">Seller Login</h2>
            
            
            <form id="slp_login_form" action="SellerMainPanel" method="post" class="form-inline" novalidate="novalidate">
              <div>
                <input type="hidden" name="authName" value="flipkart">
                <div class="forgot-password"><a href="#">Forgot password?</a></div>
                <div class="form-type-textfield form-item-name form-item form-group">
                  <input id="username" type="text" placeholder="Username" name="userId" class="form-control form-text required" value="seller1">
                </div>
                <div class="form-type-textfield form-item-pass form-item form-group">
                  <input id="userpass" type="password" placeholder="Password" name="password" class="form-control form-text required" value="12345">
                </div>
                <button id="edit-submit" type="submit"  class="btn btn-default form-submit">Log in</button>
                
                 <a href="SellerRegistrationPage" > Register  </a>
              
              </div>
            </form>
            
            
          </section>
          <!-- /.block--></div>
      </nav>
    </div>
  </div>
</header>


<div id="banner" class="banner container-full">
  <div class="container">
    <div class="row">
      <div class="col-md-8 banner-desc">
        <h1 id="typed">WELCOME TO FIRSTRADES SELLER</h1>
        <span class="typed-cursor">|</span></div>
      <div class="col-md-4">
        <div class="form-login animated bounceIn">
          <h4>Register Today</h4>
          <form id="" action="">
            <div>
              <div class="form-type-textfield form-item-email form-item form-group">
                <input id="useremail" type="text" placeholder="Email ID" name="email" class="form-control form-text required">
              </div>
              <div class="form-type-textfield form-item-phone form-item form-group">
                <input id="userphone" type="text" placeholder="Phone Number" name="phone" class="form-control form-text required">
              </div>
      <button id="edit-submit--2" value="Start Selling" type="submit"  class="btn btn-default form-submit">Start Selling</button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</div>

  <div class="container">
<div class="splitlayout container">
  <h5 class="every-day-block">Grow your business with the leader in Indian e-commerce</h5>
  <div class="chapters row">
  <img src="images/icon_seller.png">
    <div class="chapter chapter-one col-md-4">
      <div class="side side-left">
        <div class="copy">
          <div class="chapter-img animated"><!-- <img src="css/images/1.png" />--><span class="chapter-imgage chapter-img-1"></span></div>
          <div class="chap-no-text">
            <h3>4.5 Crore +</h3>
            <p class="normal-txt">customers looking to buy your products</p>
            
        </div>
      </div>
    </div>
    </div>
    <div class="chapter chapter-two col-md-4">
      <div class="side side-left">
        <div class="copy"><!-- <h2 class="every-day-title">Everyday @</h2>-->
          <div class="chapter-img animated"><!-- <img src="css/images/3.png" />--><span class="chapter-imgage chapter-img-3"></span></div>
          <div class="chap-no-text">
            <h3>30,000 +</h3>
            <p class="normal-txt">businesses growing rapidly with us</p>
            
        </div>
      </div>
    </div>
    </div>
    <div class="chapter chapter-three col-md-4">
      <div class="side side-left">
        <div class="copy"><!-- <h2 class="every-day-title">Everyday @</h2>-->
          <div class="chapter-img animated"><span class="chapter-imgage chapter-img-2"></span></div>
          <div class="chap-no-text">
            <h3>5-7 days</h3>
            <p class="normal-txt">to process your payments</p>
            
        </div>
      </div>
    </div>
  </div>
  </div>
</div>
</div>

<!-- -------------------------------------Footer------------------------------------------------------ -->
<%@ include file="../jsp_Buyer/Footer.jsp"%>
<!-- -------------------------------------End Footer-------------------------------------------------- -->

<script type="text/javascript" src="<%=FrequentUse.angular %>"></script> <div>Test</div>

</body>
</html>