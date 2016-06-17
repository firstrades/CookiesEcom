<%@page import="ecom.common.OfferedHot"%>
<%@page import="ecom.DAO.administration.AdminDAO"%>
<%@page import="java.util.Map"%>
<%@page import="ecom.DAO.Buyer.BuyerSearchDAO"%>
<%@page import="ecom.model.User"%>
<%@page import="ecom.beans.CartAttributesBean"%>
<%@page import="ecom.model.Product"%>
<%@page import="java.util.List"%>
<%@page import="ecom.common.FrequentUse"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML>
<html>
<head>
	<title> Customer Page </title>
	<link href="css/bootstrap.css" rel='stylesheet' type='text/css' />	
	<script type="text/javascript" src="<%=FrequentUse.jQuery %>"></script>
	<!-- Custom Theme files -->
	<link href="<%=FrequentUse.style %>" rel='stylesheet' type='text/css' />
	
	<!-- Custom Theme files -->
	<!--//theme-style-->
	<meta name="viewport" content="width=device-width, initial-scale=1"> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="keywords" content="First Trades Online Shoppin" />
	<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
	<!-- start menu -->
	<link href="css/megamenu.css" rel="stylesheet" type="text/css" media="all" />
	<script type="text/javascript" src="js/megamenu.js"></script>
	<script>	
		$(document).ready(function(){$(".megamenu").megamenu();});
	</script>
	<script src="js/menu_jquery.js"></script>
	<script src="js/simpleCart.min.js"> </script>
	
	<!-- -------- Search -------------- -->
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">	  
	<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>  
	<script type="text/javascript" src="js_Buyer/autocomplete.js"></script>



<link rel="stylesheet" type="text/css" href="indexcss/jquery.jcarousel.css" />
<!--
  jCarousel skin stylesheet
-->
<link rel="stylesheet" type="text/css" href="indexcss/skins.css" />

<style type="text/css">
		.login-form-input {
			width: 411px;
		    margin-bottom: 13px;
		    height: 45px;
		    font-size: 15px;
		    padding-left: 15px;
		    color: #333;
		    background: #fff;
		    border: 1px solid #c3c3c3; 
		}
		.fk-input {
		    font-size: 13px;
		    padding: 5px 6px;
		    border: 1px solid #ccc;
		    resize: none;
		    font-family: inherit;
		}
		.new-login-form {
		    width: 400px;
		} 
		
		
		   
	</style>
	
	

<style>
        
        /* jssor slider bullet navigator skin 03 css */
        /*
        .jssorb03 div           (normal)
        .jssorb03 div:hover     (normal mouseover)
        .jssorb03 .av           (active)
        .jssorb03 .av:hover     (active mouseover)
        .jssorb03 .dn           (mousedown)
        */
        .jssorb03 {
            position: absolute;
        }
        .jssorb03 div, .jssorb03 div:hover, .jssorb03 .av {
            position: absolute;
            /* size of bullet elment */
            width: 21px;
            height: 21px;
            text-align: center;
            line-height: 21px;
            color: white;
            font-size: 12px;
            background: url('img/b03.png') no-repeat;
            overflow: hidden;
            cursor: pointer;
        }
        .jssorb03 div { background-position: -5px -4px; }
        .jssorb03 div:hover, .jssorb03 .av:hover { background-position: -35px -4px; }
        .jssorb03 .av { background-position: -65px -4px; }
        .jssorb03 .dn, .jssorb03 .dn:hover { background-position: -95px -4px; }

        /* jssor slider arrow navigator skin 03 css */
        /*
        .jssora03l                  (normal)
        .jssora03r                  (normal)
        .jssora03l:hover            (normal mouseover)
        .jssora03r:hover            (normal mouseover)
        .jssora03l.jssora03ldn      (mousedown)
        .jssora03r.jssora03rdn      (mousedown)
        */
        .jssora03l, .jssora03r {
            display: block;
            position: absolute;
            /* size of arrow element */
            width: 55px;
            height: 55px;
            cursor: pointer;
            background: url('img/a03.png') no-repeat;
            overflow: hidden;
        }
        .jssora03l { background-position: -3px -33px; }
        .jssora03r { background-position: -63px -33px; }
        .jssora03l:hover { background-position: -123px -33px; }
        .jssora03r:hover { background-position: -183px -33px; }
        .jssora03l.jssora03ldn { background-position: -243px -33px; }
        .jssora03r.jssora03rdn { background-position: -303px -33px; }
    </style>


</head>
<body>
<script type="text/javascript" src="js/jssor.slider.min.js"></script>
 <script>
        jssor_1_slider_init = function() {
            
            var jssor_1_options = {
              $AutoPlay: true,
              $AutoPlaySteps: 4,
              $SlideDuration: 160,
              $SlideWidth: 335,
              $SlideSpacing: 3,
              $Cols: 4,
              $ArrowNavigatorOptions: {
                $Class: $JssorArrowNavigator$,
                $Steps: 4
              },
              $BulletNavigatorOptions: {
                $Class: $JssorBulletNavigator$,
                $SpacingX: 1,
                $SpacingY: 1
              }
            };
            
            var jssor_1_slider = new $JssorSlider$("jssor_1", jssor_1_options);
            
            //responsive code begin
            //you can remove responsive code if you don't want the slider scales while window resizing
            function ScaleSlider() {
                var refSize = jssor_1_slider.$Elmt.parentNode.clientWidth;
                if (refSize) {
                    refSize = Math.min(refSize, 809);
                    jssor_1_slider.$ScaleWidth(refSize);
                }
                else {
                    window.setTimeout(ScaleSlider, 50);
                }
            }
            ScaleSlider();
            $Jssor$.$AddEvent(window, "load", ScaleSlider);
            $Jssor$.$AddEvent(window, "resize", ScaleSlider);
            $Jssor$.$AddEvent(window, "orientationchange", ScaleSlider);
            //responsive code end
        };
    </script>
     
    <script>
        jssor_2_slider_init = function() {
            
            var jssor_2_options = {
              $AutoPlay: true,
              $AutoPlaySteps: 4,
              $SlideDuration: 160,
              $SlideWidth: 335,
              $SlideSpacing: 3,
              $Cols: 4,
              $ArrowNavigatorOptions: {
                $Class: $JssorArrowNavigator$,
                $Steps: 4
              },
              $BulletNavigatorOptions: {
                $Class: $JssorBulletNavigator$,
                $SpacingX: 1,
                $SpacingY: 1
              }
            };
            
            var jssor_2_slider = new $JssorSlider$("jssor_2", jssor_2_options);
            
            //responsive code begin
            //you can remove responsive code if you don't want the slider scales while window resizing
            function ScaleSlider() {
                var refSize = jssor_2_slider.$Elmt.parentNode.clientWidth;
                if (refSize) {
                    refSize = Math.min(refSize, 809);
                    jssor_2_slider.$ScaleWidth(refSize);
                }
                else {
                    window.setTimeout(ScaleSlider, 50);
                }
            }
            ScaleSlider();
            $Jssor$.$AddEvent(window, "load", ScaleSlider);
            $Jssor$.$AddEvent(window, "resize", ScaleSlider);
            $Jssor$.$AddEvent(window, "orientationchange", ScaleSlider);
            //responsive code end
        };
    </script>


<%
	User user = (User) session.getAttribute("user");	

	BuyerSearchDAO buyerSearchDAO = BuyerSearchDAO.getInstance();	
	Map<String,Product> map   = buyerSearchDAO.getFirstPageProducts();
	Product productBean       = null;
	
	AdminDAO adminDAO = AdminDAO.getInstance();
	List<Product> offeredProducts = adminDAO.getOfferedHot(OfferedHot.OFFERED);
	List<Product> hotProducts     = adminDAO.getOfferedHot(OfferedHot.HOT); 
%>


<!-- Header -->
<%@ include file="Header.jsp" %>
<!-- End Header -->


<!-- Navigation -->
<%@ include file="Navigation.jsp" %>
<!-- End Navigation -->





<!-- ------------------------------------Banner----------------------------------------------------- -->

<div class="row">
	<div class="col-md-2">
    
    <div class="">
    
    <div class="surakasha">
 <a href="http://www.multisimrecharge.co.in/" target="_blank">   <img src="images/multisim.png" style="width: 100%;">  Multisim Recharge   </a>
    
    </div>
     <div class="">
    
    
 <a href="#" target="_blank">   <img src="images/ad12.jpg" style="width: 100%; margin-top: 17%;">     </a>
    
    
      
<!-- <script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script> -->
<!-- www.charge-it-up.com -->


    
    </div> 

    
    </div>
    
   </div>
    
    <!-- -------------------slider-------------------------------------------------- -->
    <div class="col-md-8">
    
	<div class="slider-area">
       	<!-- Slider -->
		<div class="block-slider block-slider4">
			<ul class="" id="bxslider-home4">
			  <li><img src="images/baner5.jpg"></li>
              
               
               <li> <img src="images/baner3.jpg"> </li>
               <li> <img src="images/newbaner.jpg"> </li>
               <li>  <img src="images/baner2.jpg">  </li>
                <li> <img src="images/baner7.jpg"> </li>
                 <li> <img src="images/baner9.jpg"> </li>
                  <li> <img src="images/baner10.jpg"> </li>
                   <li> <img src="images/baner11.jpg"> </li>
				
			</ul>
		</div>
		<!-- ./Slider -->
    </div> <!-- End slider area -->
    </div>
    <!-- ------------------end slider---------------------------------------------------- -->
    <div class="col-md-2">
    
    <div class="">
    
    <div class="surakasha">
 <a href="http://firstrades.com/a2z/" target="_blank">   <img src="images/add_link.png" style="width: 100%;">  Suraksha travel portal   </a>
    
    </div>
     <div class="">
    
    
 <a href="#" target="_blank">   <img src="images/ad14.png" style="width: 100%; margin-top: 17%;">     </a>
    
    
      
<!-- <script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script> -->
<!-- www.charge-it-up.com -->


    
    </div> 

    
    </div>
    
   </div>
   </div>

    <div class="clearfix"></div>
        
	<!-- ----------------------------------------------------------------------------------------------------------------------------- -->		
			
		
	
	
<div class="row">
<div class="col-md-2">

<div class="" >
    
    
 <a href="#" target="_blank">   <img src="images/ads2.jpg" style="width: 100%; margin-top:50px;">     </a>
    
    
      

    
    </div>
  </div>
  
  
<%-- <%@ include file="SliderOffer.jsp" %>  --%> 

<div class="col-md-8">
<div class="special">
		<h3><a href="#" style="color: #FF8F00;">OFFER CATEGORY</a></h3>
		</div>

<div id="jssor_1" style="position: relative; margin: 0 auto; top: 0px; left: 0%; width: 1188px; height: 500px; overflow: hidden; visibility: hidden;">
        <!-- Loading Screen -->
        <div data-u="loading" style="position: absolute; top: 0px; left: 0px;">
            <div style="filter: alpha(opacity=70); opacity: 0.7; position: absolute; display: block; top: 0px; left: 0px; width: 100%; height: 100%;"></div>
            <div style="position:absolute;display:block;background:url('img/loading.gif') no-repeat center center;top:0px;left:0px;width:100%;height:100%;"></div>
        </div>
        <div data-u="slides" style="cursor: default; position: relative; top: 0px; left: 88px; width: 1013px; height: 500px; overflow: hidden;">
            <div style="display: none;">
                <img src="images/offer.png" style="position:absolute;z-index:2;margin:auto;height:90px;margin-left: 2%;"/>
               <div style="height:200px">
    			<a href="CompleteProductDetails?subCategory=<%=offeredProducts.get(0).getSubCategory() %>&productId=<%=offeredProducts.get(0).getProductId() %>">
					<img src="IconImageFromProduct?productId=<%=offeredProducts.get(0).getProductId() %>" class="img-responsive center-block" alt="" />
				</a>
				</div>
				<div class="special-info grid_1 simpleCart_shelfItem">
					<h5 style="height: 65px;"> 
						<a style="font-size: 14px;font-weight: bold;"  href="CompleteProductDetails?subCategory=<%=offeredProducts.get(0).getSubCategory() %>&productId=<%=offeredProducts.get(0).getProductId() %>"> <%=offeredProducts.get(0).getProductName() %>  (<%=offeredProducts.get(0).getCompanyName() %> )  </a> 
					</h5>
				
                   	
                   	<div class="item_add"><h6 style="height: 60px;"><span class="item_price"> <small class="over_flow"> Rs.<%=offeredProducts.get(0).getPrice().getListPrice() %> </small> <br/> <small class="item_price"> (<%=offeredProducts.get(0).getPrice().getDiscount() %>% Off) </small> <br> <strong class="main_value">Rs <%=offeredProducts.get(0).getPrice().getSalePriceCustomer() %></strong> </span></h6></div>
					<div class="item_add" style="height: 32px;">
						<span class="item_price">
							<a href="CompleteProductDetails?subCategory=<%=offeredProducts.get(0).getSubCategory() %>&productId=<%=offeredProducts.get(0).getProductId() %>">More Details</a>
						</span>
					</div>
				</div>
            </div>
            <div style="display: none;">
               <img src="images/offer.png" style="position:absolute;z-index:2;margin:auto;height:90px;margin-left: 2%;"/>
    			<div style="height:200px">
    			<a href="CompleteProductDetails?subCategory=<%=offeredProducts.get(1).getSubCategory() %>&productId=<%=offeredProducts.get(1).getProductId() %>">
							<img src="IconImageFromProduct?productId=<%=offeredProducts.get(1).getProductId() %>" class="img-responsive center-block" alt="" />
						</a>
						</div>
						<div class="special-info grid_1 simpleCart_shelfItem">
							<h5 style="height: 65px;"> 
								<a style="font-size: 14px;font-weight: bold;"  href="CompleteProductDetails?subCategory=<%=offeredProducts.get(1).getSubCategory() %>&productId=<%=offeredProducts.get(1).getProductId() %>"> <%=offeredProducts.get(1).getProductName() %>  (<%=offeredProducts.get(1).getCompanyName() %> )  </a> 
							</h5>
						
	                    	
	                    	<div class="item_add"><h6 style="height: 60px;"><span class="item_price"> <small class="over_flow"> Rs.<%=offeredProducts.get(1).getPrice().getListPrice() %> </small> </br> <small class="item_price"> (<%=offeredProducts.get(1).getPrice().getDiscount() %>% Off) </small> <br> <strong class="main_value">Rs <%=offeredProducts.get(1).getPrice().getSalePriceCustomer() %></strong> </span></h6></div>
							<div class="item_add" style="height: 32px;">
								<span class="item_price">
									<a href="CompleteProductDetails?subCategory=<%=offeredProducts.get(1).getSubCategory() %>&productId=<%=offeredProducts.get(1).getProductId() %>">More Details</a>
								</span>
							</div>
						</div>
            </div>
            <div style="display: none;">
           
                <img src="images/offer.png" style="position:absolute;z-index:2;margin:auto;height:90px;margin-left: 2%;"/>
    						 <div style="height:200px">
    						<a href="CompleteProductDetails?subCategory=<%=offeredProducts.get(2).getSubCategory() %>&productId=<%=offeredProducts.get(2).getProductId() %>">
								<img src="IconImageFromProduct?productId=<%=offeredProducts.get(2).getProductId() %>" class="img-responsive center-block" alt="" />
							</a>
							</div>
						<div class="special-info grid_1 simpleCart_shelfItem">
							<h5 style="height: 65px;"> 
								<a style="font-size: 14px;font-weight: bold;"  href="CompleteProductDetails?subCategory=<%=offeredProducts.get(2).getSubCategory() %>&productId=<%=offeredProducts.get(2).getProductId() %>"> <%=offeredProducts.get(2).getProductName() %>  (<%=offeredProducts.get(2).getCompanyName() %> )  </a> 
							</h5>
						
	                    	
	                    	<div class="item_add"><h6 style="height: 60px;"><span class="item_price"> <small class="over_flow"> Rs.<%=offeredProducts.get(2).getPrice().getListPrice() %> </small> </br> <small class="item_price"> (<%=offeredProducts.get(2).getPrice().getDiscount() %>% Off) </small> <br> <strong class="main_value">Rs <%=offeredProducts.get(2).getPrice().getSalePriceCustomer() %></strong> </span></h6></div>
							<div class="item_add" style="height: 32px;">
								<span class="item_price">
									<a href="CompleteProductDetails?subCategory=<%=offeredProducts.get(2).getSubCategory() %>&productId=<%=offeredProducts.get(2).getProductId() %>">More Details</a>
								</span>
							</div>
						</div>
            </div>
            <div style="display: none;">
                <img src="images/offer.png" style="position:absolute;z-index:2;margin:auto;height:90px;margin-left: 2%;"/>
    						 <div style="height:200px">
    						<a href="CompleteProductDetails?subCategory=<%=offeredProducts.get(3).getSubCategory() %>&productId=<%=offeredProducts.get(3).getProductId() %>">
								<img src="IconImageFromProduct?productId=<%=offeredProducts.get(3).getProductId() %>" class="img-responsive center-block" alt="" />
							</a>
							</div>
						<div class="special-info grid_1 simpleCart_shelfItem">
							<h5 style="height: 65px;"> 
								<a style="font-size: 14px;font-weight: bold;"  href="CompleteProductDetails?subCategory=<%=offeredProducts.get(3).getSubCategory() %>&productId=<%=offeredProducts.get(3).getProductId() %>"> <%=offeredProducts.get(3).getProductName() %>  (<%=offeredProducts.get(3).getCompanyName() %> )  </a> 
							</h5>
						
	                    	
	                    	<div class="item_add"><h6 style="height: 60px;"><span class="item_price"> <small class="over_flow"> Rs.<%=offeredProducts.get(3).getPrice().getListPrice() %> </small> </br> <small class="item_price"> (<%=offeredProducts.get(3).getPrice().getDiscount() %>% Off) </small> <br> <strong class="main_value">Rs <%=offeredProducts.get(3).getPrice().getSalePriceCustomer() %></strong> </span></h6></div>
							<div class="item_add" style="height: 32px;">
								<span class="item_price">
									<a href="CompleteProductDetails?subCategory=<%=offeredProducts.get(3).getSubCategory() %>&productId=<%=offeredProducts.get(3).getProductId() %>">More Details</a>
								</span>
							</div>
						</div>
            </div>
            <div style="display: none;">
                <img src="images/offer.png" style="position:absolute;z-index:2;margin:auto;height:90px;margin-left: 2%;"/>
    						 <div style="height:200px">
    						<a href="CompleteProductDetails?subCategory=<%=offeredProducts.get(4).getSubCategory() %>&productId=<%=offeredProducts.get(4).getProductId() %>">
								<img src="IconImageFromProduct?productId=<%=offeredProducts.get(4).getProductId() %>" class="img-responsive center-block" alt="" />
							</a>
							</div>
						<div class="special-info grid_1 simpleCart_shelfItem">
							<h5 style="height: 65px;"> 
								<a style="font-size: 14px;font-weight: bold;"  href="CompleteProductDetails?subCategory=<%=offeredProducts.get(4).getSubCategory() %>&productId=<%=offeredProducts.get(4).getProductId() %>"> <%=offeredProducts.get(4).getProductName() %>  (<%=offeredProducts.get(4).getCompanyName() %> )  </a> 
							</h5>
						
	                    	
	                    	<div class="item_add"><h6 style="height: 60px;"><span class="item_price"> <small class="over_flow"> Rs.<%=offeredProducts.get(4).getPrice().getListPrice() %> </small> </br> <small class="item_price"> (<%=offeredProducts.get(4).getPrice().getDiscount() %>% Off) </small> <br> <strong class="main_value">Rs <%=offeredProducts.get(4).getPrice().getSalePriceCustomer() %></strong> </span></h6></div>
							<div class="item_add" style="height: 32px;">
								<span class="item_price">
									<a href="CompleteProductDetails?subCategory=<%=offeredProducts.get(4).getSubCategory() %>&productId=<%=offeredProducts.get(4).getProductId() %>">More Details</a>
								</span>
							</div>
						</div>
            </div>
            <div style="display: none;">
               <img src="images/offer.png" style="position:absolute;z-index:2;margin:auto;height:90px;margin-left: 2%;"/>
    						 <div style="height:200px">
    						<a href="CompleteProductDetails?subCategory=<%=offeredProducts.get(5).getSubCategory() %>&productId=<%=offeredProducts.get(5).getProductId() %>">
								<img src="IconImageFromProduct?productId=<%=offeredProducts.get(5).getProductId() %>" class="img-responsive center-block" alt="" />
							</a>
							</div>
						<div class="special-info grid_1 simpleCart_shelfItem">
							<h5 style="height: 65px;"> 
								<a style="font-size: 14px;font-weight: bold;"  href="CompleteProductDetails?subCategory=<%=offeredProducts.get(5).getSubCategory() %>&productId=<%=offeredProducts.get(5).getProductId() %>"> <%=offeredProducts.get(5).getProductName() %>  (<%=offeredProducts.get(5).getCompanyName() %> )  </a> 
							</h5>
						
	                    	
	                    	<div class="item_add"><h6 style="height: 60px;"><span class="item_price"> <small class="over_flow"> Rs.<%=offeredProducts.get(5).getPrice().getListPrice() %> </small> </br> <small class="item_price"> (<%=offeredProducts.get(5).getPrice().getDiscount() %>% Off) </small> <br> <strong class="main_value">Rs <%=offeredProducts.get(5).getPrice().getSalePriceCustomer() %></strong> </span></h6></div>
							<div class="item_add" style="height: 32px;">
								<span class="item_price">
									<a href="CompleteProductDetails?subCategory=<%=offeredProducts.get(5).getSubCategory() %>&productId=<%=offeredProducts.get(5).getProductId() %>">More Details</a>
								</span>
							</div>
						</div>
            </div>
            
           
          
           
            
        
        </div>
        <!-- Bullet Navigator -->
        <div data-u="navigator" class="jssorb03" style="bottom:10px;right:10px;">
            <!-- bullet navigator item prototype -->
            <div data-u="prototype" style="width:21px;height:21px;">
                <div data-u="numbertemplate"></div>
            </div>
        </div>
        <!-- Arrow Navigator -->
        <span data-u="arrowleft" class="jssora03l" style="top:0px;left:8px;width:55px;height:55px;" data-autocenter="2"></span>
        <span data-u="arrowright" class="jssora03r" style="top:0px;right:8px;width:55px;height:55px;" data-autocenter="2"></span>
    </div>
    <script>
        jssor_1_slider_init();
    </script>

    <!-- #endregion Jssor Slider End -->
    </div>

  
  
  <div class="col-md-2">
  
 
 

<div class="" >
    
    
 <a href="#" target="_blank">   <img src="images/ad18.jpg" style="width: 100%; margin-top:50px;">     </a>
    
    
      

    
    </div>
  

</div>
  </div>
  
   
	
				
			
				
<!-- ------------------------------------End Banner--------------------------------------------- -->
				
						
					
				
				
				
				
		

<!-- ----------------------------------------------------BODY-------------------------------------------------------- -->


<div class="row">
<div class="col-md-2">
<div style="">

<div class="">
    
    
 <a href="#" target="_blank">   <img src="images/ad1.png" style="width: 100%; margin-top: 30%;height:400px;">     </a>
    
    
      
<!-- <script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script> -->
<!-- www.charge-it-up.com -->


    
    </div>
    
    
</div>
</div>



	<div class="col-md-8">
	<div class="special">
		<h3><a href="#" style="color: #FF8F00;">ELECTRONICS</a></h3>
		</div>
			<div class="specia-top">
				<ul class="grid_2">
					<% 
						productBean = map.get("Mobile");
					%>
					<li>
					<div style="height:200px;">
						<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>">
							<img src="IconImageFromProduct?productId=<%=productBean.getProductId() %>" class="img-responsive center-block" alt="">
						</a>
						</div>
						<div class="special-info grid_1 simpleCart_shelfItem">
							<h5> 
								<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>"> <%=productBean.getProductName() %>  (<%=productBean.getCompanyName() %>)  </a> 
							</h5>
						
	                    	
	                    	<div class="item_add"><h6><span class="item_price"> <small class="over_flow"> Rs.<%=productBean.getPrice().getListPrice() %> </small> </br> <small class="item_price"> (<%=productBean.getPrice().getDiscount() %>% Off) </small> <br> <strong class="main_value">Rs <%=productBean.getPrice().getSalePriceCustomer() %></strong> </span></h6></div>
							<div class="item_add">
								<span class="item_price">
									<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>">More Details</a>
								</span>
							</div>
						</div>
					</li>
					<% 
						productBean = map.get("Laptop");
					%>
					<li>
					<div style="height:200px;">
						<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>">
							<img src="IconImageFromProduct?productId=<%=productBean.getProductId() %>" class="img-responsive center-block" alt="">
						</a>
						</div>
						<div class="special-info grid_1 simpleCart_shelfItem">
							<h5> 
								<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>"> <%=productBean.getProductName() %>  (<%=productBean.getCompanyName() %>)  </a> 
							</h5>
						
	                    	
	                    	<div class="item_add"><h6><span class="item_price"> <small class="over_flow"> Rs.<%=productBean.getPrice().getListPrice() %> </small> </br> <small class="item_price"> (<%=productBean.getPrice().getDiscount() %>% Off) </small> <br> <strong class="main_value">Rs <%=productBean.getPrice().getSalePriceCustomer() %></strong> </span></h6></div>
							<div class="item_add">
								<span class="item_price">
									<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>">More Details</a>
								</span>
							</div>
						</div>
					</li>
					<% 
						productBean = map.get("Tablet");
					%>
					<li>
					<div style="height:200px;">
						<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>">
							<img src="IconImageFromProduct?productId=<%=productBean.getProductId() %>" class="img-responsive center-block" alt="">
						</a>
						</div>
						<div class="special-info grid_1 simpleCart_shelfItem">
							<h5> 
								<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>"> <%=productBean.getProductName() %>  (<%=productBean.getCompanyName() %>)  </a> 
							</h5>
						
	                    	
	                    	<div class="item_add"><h6><span class="item_price"> <small class="over_flow"> Rs.<%=productBean.getPrice().getListPrice() %> </small> </br> <small class="item_price"> (<%=productBean.getPrice().getDiscount() %>% Off) </small> <br> <strong class="main_value">Rs <%=productBean.getPrice().getSalePriceCustomer() %></strong> </span></h6></div>
							<div class="item_add">
								<span class="item_price">
									<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>">More Details</a>
								</span>
							</div>
						</div>
					</li>
					<% 
						productBean = map.get("Camera");
					%>
					<li>
					<div style="height:200px;">
						<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>">
							<img src="IconImageFromProduct?productId=<%=productBean.getProductId() %>" class="img-responsive center-block" alt="">
						</a>
						</div>
						<div class="special-info grid_1 simpleCart_shelfItem">
							<h5> 
								<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>"> <%=productBean.getProductName() %>  (<%=productBean.getCompanyName() %>)  </a> 
							</h5>
						
	                    	
	                    	<div class="item_add"><h6><span class="item_price"> <small class="over_flow"> Rs.<%=productBean.getPrice().getListPrice() %> </small> </br> <small class="item_price"> (<%=productBean.getPrice().getDiscount() %>% Off) </small> <br> <strong class="main_value">Rs <%=productBean.getPrice().getSalePriceCustomer() %></strong> </span></h6></div>
							<div class="item_add">
								<span class="item_price">
									<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>">More Details</a>
								</span>
							</div>
						</div>
					</li>
					<% 
						productBean = map.get("Television");
					%>
					<li>
					<div style="height:200px;">
						<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>">
							<img src="IconImageFromProduct?productId=<%=productBean.getProductId() %>" class="img-responsive center-block" alt="">
						</a>
						</div>
						<div class="special-info grid_1 simpleCart_shelfItem">
							<h5> 
								<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>"> <%=productBean.getProductName() %>  (<%=productBean.getCompanyName() %>)  </a> 
							</h5>
						
	                    	
	                    	<div class="item_add"><h6><span class="item_price"> <small class="over_flow"> Rs.<%=productBean.getPrice().getListPrice() %> </small> </br> <small class="item_price"> (<%=productBean.getPrice().getDiscount() %>% Off) </small> <br> <strong class="main_value">Rs <%=productBean.getPrice().getSalePriceCustomer() %></strong> </span></h6></div>
							<div class="item_add">
								<span class="item_price">
									<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>">More Details</a>
								</span>
							</div>
						</div>
					</li>
				</ul>
				<div class="clearfix"> </div>
			</div>
			</div>
			
			<div class="col-md-2">
			
			
  
<a href="#" target="_blank">   <img src="images/ad9.png" style="width:100%;margin-top:30%; height:400px;">    </a>
         

       
 

</div>
</div>


<div class="row">
<div class="col-md-2">
<div style="">

<div class="">
    
    
 <a href="#" target="_blank">   <img src="images/ad3.png" style="width: 101%; margin-top: 30%;height: 400px;">     </a>
    
    
      
<!-- <script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script> -->
<!-- www.charge-it-up.com -->


    
    </div>
    
    
</div>

</div>

<div class="col-md-8">
		<div class="special">
			<h3><a href="#" style="color: #FF8F00;">MEN</a> </h3>
			</div>
			
				
				<div class="specia-top">
					<ul class="grid_2">
						<% 
							productBean = map.get("MenTshirt");
						%>
						<li>
						<div style="height:200px;">
							<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>">
								<img src="IconImageFromProduct?productId=<%=productBean.getProductId() %>" class="img-responsive center-block" alt="">
							</a>
							</div>
							<div class="special-info grid_1 simpleCart_shelfItem">
								<h5 style="font-size: 13px;"> 
									<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>"> <%=productBean.getProductName() %>  (<%=productBean.getCompanyName() %>)  </a> 
								</h5>
							
		                    	
		                    	<div class="item_add"><h6><span class="item_price"> <small class="over_flow"> Rs.<%=productBean.getPrice().getListPrice() %> </small> </br> <small class="item_price"> (<%=productBean.getPrice().getDiscount() %>% Off) </small> <br> <strong class="main_value">Rs <%=productBean.getPrice().getSalePriceCustomer() %></strong> </span></h6></div>
								<div class="item_add">
									<span class="item_price">
										<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>">More Details</a>
									</span>
								</div>
							</div>
						</li>
						<% 
							productBean = map.get("MenShirt");
						%>
						<li>
						<div style="height:200px;">
							<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>">
								<img src="IconImageFromProduct?productId=<%=productBean.getProductId() %>" class="img-responsive center-block" alt="">
							</a>
							</div>
							<div class="special-info grid_1 simpleCart_shelfItem">
								<h5> 
									<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>"> <%=productBean.getProductName() %>  (<%=productBean.getCompanyName() %>)  </a> 
								</h5>
							
		                    	
		                    	<div class="item_add"><h6><span class="item_price"> <small class="over_flow"> Rs.<%=productBean.getPrice().getListPrice() %> </small> </br> <small class="item_price"> (<%=productBean.getPrice().getDiscount() %>% Off) </small> <br> <strong class="main_value">Rs <%=productBean.getPrice().getSalePriceCustomer() %></strong> </span></h6></div>
								<div class="item_add">
									<span class="item_price">
										<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>">More Details</a>
									</span>
								</div>
							</div>
						</li>
						<% 
							productBean = map.get("MenJeans");
						%>
						<li>
						<div style="height:200px;">
							<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>">
								<img src="IconImageFromProduct?productId=<%=productBean.getProductId() %>" class="img-responsive center-block" alt="">
							</a>
							</div>
							<div class="special-info grid_1 simpleCart_shelfItem">
								<h5> 
									<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>"> <%=productBean.getProductName() %>  (<%=productBean.getCompanyName() %>)  </a> 
								</h5>
							
		                    	
		                    	<div class="item_add"><h6><span class="item_price"> <small class="over_flow"> Rs.<%=productBean.getPrice().getListPrice() %> </small> </br> <small class="item_price"> (<%=productBean.getPrice().getDiscount() %>% Off) </small> <br> <strong class="main_value">Rs <%=productBean.getPrice().getSalePriceCustomer() %></strong> </span></h6></div>
								<div class="item_add">
									<span class="item_price">
										<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>">More Details</a>
									</span>
								</div>
							</div>
						</li>
						<% 
							productBean = map.get("MenTrouser");
						%>
						<li>
						<div style="height:200px;">
							<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>">
								<img src="IconImageFromProduct?productId=<%=productBean.getProductId() %>" class="img-responsive center-block" alt="">
							</a>
							</div>
							<div class="special-info grid_1 simpleCart_shelfItem">
								<h5> 
									<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>"> <%=productBean.getProductName() %>  (<%=productBean.getCompanyName() %>)  </a> 
								</h5>
							
		                    	
		                    	<div class="item_add"><h6><span class="item_price"> <small class="over_flow"> Rs.<%=productBean.getPrice().getListPrice() %> </small> </br> <small class="item_price"> (<%=productBean.getPrice().getDiscount() %>% Off) </small> <br> <strong class="main_value">Rs <%=productBean.getPrice().getSalePriceCustomer() %></strong> </span></h6></div>
								<div class="item_add">
									<span class="item_price">
										<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>">More Details</a>
									</span>
								</div>
							</div>
						</li>
						<% 
							productBean = map.get("MenShoes");
						%>
						<li>
						<div style="height:200px;">
							<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>">
								<img src="IconImageFromProduct?productId=<%=productBean.getProductId() %>" class="img-responsive center-block" alt="">
							</a>
							</div>
							<div class="special-info grid_1 simpleCart_shelfItem">
								<h5> 
									<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>"> <%=productBean.getProductName() %>  (<%=productBean.getCompanyName() %>)  </a> 
								</h5>
							
		                    	
		                    	<div class="item_add"><h6><span class="item_price"> <small class="over_flow"> Rs.<%=productBean.getPrice().getListPrice() %> </small> </br> <small class="item_price"> (<%=productBean.getPrice().getDiscount() %>% Off) </small> <br> <strong class="main_value">Rs <%=productBean.getPrice().getSalePriceCustomer() %></strong> </span></h6></div>
								<div class="item_add">
									<span class="item_price">
										<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>">More Details</a>
									</span>
								</div>
							</div>
						</li>
					</ul>
					<div class="clearfix"> </div>
				</div>
				</div>
				
				<div class="col-md-2">
				
	 <div style="">
  
<a href="#" target="_blank">   <img src="images/ad4.jpg" style=" margin-top: 30%;height: 400px;">    </a>
         

       
 
</div>
</div>
	</div>			
				
				
				
				
				<div class="row">
				<div class="col-md-2">
				
				<div style="">

<div class="">
    
    
 <a href="#" target="_blank">   <img src="images/ad10.jpg" style="width:100%; margin-top: 30%;height: 400px;">     </a>
    
    
      
<!-- <script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script> -->
<!-- www.charge-it-up.com -->


    
    </div>
    
    
</div>

</div>

<div class="col-md-8">

<div class="special">
			<h3><a href="#" style="color: #FF8F00;">WOMEN</a></h3>
			</div>
				<div class="specia-top">
					<ul class="grid_2">
						<% 
							productBean = map.get("WomenShoe");
						%>
						<li>
						<div style="height:200px;">
							<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>">
								<img src="IconImageFromProduct?productId=<%=productBean.getProductId() %>" class="img-responsive center-block" alt="">
							</a>
							</div>
							<div class="special-info grid_1 simpleCart_shelfItem">
								<h5> 
									<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>"> <%=productBean.getProductName() %>  (<%=productBean.getCompanyName() %>)  </a> 
								</h5>
							
		                    	
		                    	<div class="item_add"><h6><span class="item_price"> <small class="over_flow"> Rs.<%=productBean.getPrice().getListPrice() %> </small> </br> <small class="item_price"> (<%=productBean.getPrice().getDiscount() %>% Off) </small> <br> <strong class="main_value">Rs <%=productBean.getPrice().getSalePriceCustomer() %></strong> </span></h6></div>
								<div class="item_add">
									<span class="item_price">
										<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>">More Details</a>
									</span>
								</div>
							</div>
						</li>
						<% 
							productBean = map.get("WomenKurta");
						%>
						<li>
						<div style="height:200px;">
							<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>">
								<img src="IconImageFromProduct?productId=<%=productBean.getProductId() %>" class="img-responsive center-block" alt="">
							</a>
							</div>
							<div class="special-info grid_1 simpleCart_shelfItem">
								<h5> 
									<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>"> <%=productBean.getProductName() %>  (<%=productBean.getCompanyName() %>)  </a> 
								</h5>
							
		                    	
		                    	<div class="item_add"><h6><span class="item_price"> <small class="over_flow"> Rs.<%=productBean.getPrice().getListPrice() %> </small> </br> <small class="item_price"> (<%=productBean.getPrice().getDiscount() %>% Off) </small> <br> <strong class="main_value">Rs <%=productBean.getPrice().getSalePriceCustomer() %></strong> </span></h6></div>
								<div class="item_add">
									<span class="item_price">
										<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>">More Details</a>
									</span>
								</div>
							</div>
						</li>
						<% 
							productBean = map.get("WomenSharee");
						%>
						<li>
						<div style="height:200px;">
							<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>">
								<img src="IconImageFromProduct?productId=<%=productBean.getProductId() %>" class="img-responsive center-block" alt="">
							</a>
							</div>
							<div class="special-info grid_1 simpleCart_shelfItem">
								<h5> 
									<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>"> <%=productBean.getProductName() %>  (<%=productBean.getCompanyName() %>)  </a> 
								</h5>
							
		                    	
		                    	<div class="item_add"><h6><span class="item_price"> <small class="over_flow"> Rs.<%=productBean.getPrice().getListPrice() %> </small> </br> <small class="item_price"> (<%=productBean.getPrice().getDiscount() %>% Off) </small> <br> <strong class="main_value">Rs <%=productBean.getPrice().getSalePriceCustomer() %></strong> </span></h6></div>
								<div class="item_add">
									<span class="item_price">
										<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>">More Details</a>
									</span>
								</div>
							</div>
						</li>
						<% 
							productBean = map.get("WomenSalwar");
						%>
						<li>
						<div style="height:200px;">
							<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>">
								<img src="IconImageFromProduct?productId=<%=productBean.getProductId() %>" class="img-responsive center-block" alt="">
							</a>
							</div>
							<div class="special-info grid_1 simpleCart_shelfItem">
								<h5> 
									<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>"> <%=productBean.getProductName() %>  (<%=productBean.getCompanyName() %>)  </a> 
								</h5>
							
		                    	
		                    	<div class="item_add"><h6><span class="item_price"> <small class="over_flow"> Rs.<%=productBean.getPrice().getListPrice() %> </small> </br> <small class="item_price"> (<%=productBean.getPrice().getDiscount() %>% Off) </small> <br> <strong class="main_value">Rs <%=productBean.getPrice().getSalePriceCustomer() %></strong> </span></h6></div>
								<div class="item_add">
									<span class="item_price">
										<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>">More Details</a>
									</span>
								</div>
							</div>
						</li>
						<% 
							productBean = map.get("WomenJeans");
						%>
						<li>
						<div style="height:200px;">
							<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>">
								<img src="IconImageFromProduct?productId=<%=productBean.getProductId() %>" class="img-responsive center-block" alt="">
							</a>
							</div>
							<div class="special-info grid_1 simpleCart_shelfItem">
								<h5> 
									<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>"> <%=productBean.getProductName() %>  (<%=productBean.getCompanyName() %>)  </a> 
								</h5>
							
		                    	
		                    	<div class="item_add"><h6><span class="item_price"> <small class="over_flow"> Rs.<%=productBean.getPrice().getListPrice() %> </small> </br> <small class="item_price"> (<%=productBean.getPrice().getDiscount() %>% Off) </small> <br> <strong class="main_value">Rs <%=productBean.getPrice().getSalePriceCustomer() %></strong> </span></h6></div>
								<div class="item_add">
									<span class="item_price">
										<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>">More Details</a>
									</span>
								</div>
							</div>
						</li>		
					</ul>
					<div class="clearfix"> </div>
				</div>	
				</div>
				
				<div class="col-md-2">
			 <div style="">
  
<a href="#" target="_blank">   <img src="images/ad19.jpg" style="width:100%; margin-top: 30%;height: 400px;">    </a>
         

       
 
</div>	
</div>
</div>


<!-- ----------------------------------- Home & Kitchen ----------------------------------- -->


<div class="row">
<div class="col-md-2">
				
	<div style="">

<div class="">
    
    
 <a href="#" target="_blank">   <img src="images/ad8.jpg" style="width: 100%; margin-top: 30%;height: 400px;">     </a>
    
    
      
<!-- <script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script> -->
<!-- www.charge-it-up.com -->


    
    </div>
    
    
</div>	
</div>
<div class="col-md-8">
		<div class="special">
			<h3><a href="#" style="color: #FF8F00;">HOME &amp; KITCHEN</a></h3>
			</div>
				<div class="specia-top">
					<ul class="grid_2">
						<% 
							productBean = map.get("Bedsheets");
						%>
						<li>
						<div style="height:200px;">
							<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>">
								<img src="IconImageFromProduct?productId=<%=productBean.getProductId() %>" class="img-responsive center-block" alt="">
							</a>
							</div>
							<div class="special-info grid_1 simpleCart_shelfItem">
								<h5> 
									<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>"> <%=productBean.getProductName() %>  (<%=productBean.getCompanyName() %>)  </a> 
								</h5>
							
		                    	
		                    	<div class="item_add"><h6><span class="item_price"> <small class="over_flow"> Rs.<%=productBean.getPrice().getListPrice() %> </small> </br> <small class="item_price"> (<%=productBean.getPrice().getDiscount() %>% Off) </small> <br> <strong class="main_value">Rs <%=productBean.getPrice().getSalePriceCustomer() %></strong> </span></h6></div>
								<div class="item_add">
									<span class="item_price">
										<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>">More Details</a>
									</span>
								</div>
							</div>
						</li>
						<% 
							productBean = map.get("Curtains");
						%>
						<li>
						<div style="height:200px;">
							<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>">
								<img src="IconImageFromProduct?productId=<%=productBean.getProductId() %>" class="img-responsive center-block" alt="">
							</a>
							</div>
							<div class="special-info grid_1 simpleCart_shelfItem">
								<h5> 
									<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>"> <%=productBean.getProductName() %>  (<%=productBean.getCompanyName() %>)  </a> 
								</h5>
							
		                    	
		                    	<div class="item_add"><h6><span class="item_price"> <small class="over_flow"> Rs.<%=productBean.getPrice().getListPrice() %> </small> </br> <small class="item_price"> (<%=productBean.getPrice().getDiscount() %>% Off) </small> <br> <strong class="main_value">Rs <%=productBean.getPrice().getSalePriceCustomer() %></strong> </span></h6></div>
								<div class="item_add">
									<span class="item_price">
										<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>">More Details</a>
									</span>
								</div>
							</div>
						</li>
						<% 
							productBean = map.get("SofaCovers");
						%>
						<li>
						<div style="height:200px;">
							<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>">
								<img src="IconImageFromProduct?productId=<%=productBean.getProductId() %>" class="img-responsive center-block" alt="">
							</a>
							</div>
							<div class="special-info grid_1 simpleCart_shelfItem">
								<h5> 
									<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>"> <%=productBean.getProductName() %>  (<%=productBean.getCompanyName() %>)  </a> 
								</h5>
							
		                    	
		                    	<div class="item_add"><h6><span class="item_price"> <small class="over_flow"> Rs.<%=productBean.getPrice().getListPrice() %> </small> </br> <small class="item_price"> (<%=productBean.getPrice().getDiscount() %>% Off) </small> <br> <strong class="main_value">Rs <%=productBean.getPrice().getSalePriceCustomer() %></strong> </span></h6></div>
								<div class="item_add">
									<span class="item_price">
										<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>">More Details</a>
									</span>
								</div>
							</div>
						</li>
						<% 
							productBean = map.get("PressureCookers");
						%>
						<li>
						<div style="height:200px;">
							<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>">
								<img src="IconImageFromProduct?productId=<%=productBean.getProductId() %>" class="img-responsive center-block" alt="">
							</a>
							</div>
							<div class="special-info grid_1 simpleCart_shelfItem">
								<h5> 
									<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>"> <%=productBean.getProductName() %>  (<%=productBean.getCompanyName() %>)  </a> 
								</h5>
							
		                    	
		                    	<div class="item_add"><h6><span class="item_price"> <small class="over_flow"> Rs.<%=productBean.getPrice().getListPrice() %> </small> </br> <small class="item_price"> (<%=productBean.getPrice().getDiscount() %>% Off) </small> <br> <strong class="main_value">Rs <%=productBean.getPrice().getSalePriceCustomer() %></strong> </span></h6></div>
								<div class="item_add">
									<span class="item_price">
										<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>">More Details</a>
									</span>
								</div>
							</div>
						</li>
						<% 
							productBean = map.get("GasStoves");
						%>
						<li>
						<div style="height:200px;">
							<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>">
								<img src="IconImageFromProduct?productId=<%=productBean.getProductId() %>" class="img-responsive centr-block" alt="">
							</a>
							</div>
							<div class="special-info grid_1 simpleCart_shelfItem">
								<h5> 
									<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>"> <%=productBean.getProductName() %>  (<%=productBean.getCompanyName() %>)  </a> 
								</h5>
							
		                    	
		                    	<div class="item_add"><h6><span class="item_price"> <small class="over_flow"> Rs.<%=productBean.getPrice().getListPrice() %> </small> </br> <small class="item_price"> (<%=productBean.getPrice().getDiscount() %>% Off) </small> <br> <strong class="main_value">Rs <%=productBean.getPrice().getSalePriceCustomer() %></strong> </span></h6></div>
								<div class="item_add">
									<span class="item_price">
										<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>">More Details</a>
									</span>
								</div>
							</div>
						</li>	
					</ul>
					<div class="clearfix"> </div>
				</div>
				</div>
				<div class="col-md-2">
 <div style="">
  
<a href="#" target="_blank">   <img src="images/ad6.jpg" style="width:100%; margin-top: 30%;height: 400px;">    </a>
         

       
 
</div>
</div>
		</div>	
		
<!-- ----------------------------------- End Home & Kitchen ----------------------------------- -->

<!-- ----------------------------------- Baby & Kids ----------------------------------- -->		
		
		<div class="row">
		<div class="col-md-2">	
					
<div style="">

<div class="">
    
    
 <a href="#" target="_blank">   <img src="images/ad5.jpg" style="width: 100%; margin-top: 30%;height: 400px;">     </a>
    
    
      
<!-- <script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script> -->
<!-- www.charge-it-up.com -->


    
    </div>
    
    
</div>
</div>

<div class="col-md-8">
<div class="special">
			<h3><a href="#" style="color: #FF8F00;">BABY &amp; KIDS</a></h3>
			</div>
				<div class="specia-top">
					<ul class="grid_2">
						<% 
							productBean = map.get("Baby_Diapers");
						%>
						<li>
						<div style="height:200px;">
							<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>">
								<img src="IconImageFromProduct?productId=<%=productBean.getProductId() %>" class="img-responsive center-block" alt="">
							</a>
							</div>
							<div class="special-info grid_1 simpleCart_shelfItem">
								<h5> 
									<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>"> <%=productBean.getProductName() %>  (<%=productBean.getCompanyName() %>)  </a> 
								</h5>
							
		                    	
		                    	<div class="item_add"><h6><span class="item_price"> <small class="over_flow"> Rs.<%=productBean.getPrice().getListPrice() %> </small> </br> <small class="item_price"> (<%=productBean.getPrice().getDiscount() %>% Off) </small> <br> <strong class="main_value">Rs <%=productBean.getPrice().getSalePriceCustomer() %></strong> </span></h6></div>
								<div class="item_add">
									<span class="item_price">
										<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>">More Details</a>
									</span>
								</div>
							</div>
						</li>
						<% 
							productBean = map.get("Boys_Pant");
						%>
						<li>
						<div style="height:200px;">
							<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>">
								<img src="IconImageFromProduct?productId=<%=productBean.getProductId() %>" class="img-responsive center-block" alt="">
							</a>
							</div>
							<div class="special-info grid_1 simpleCart_shelfItem">
								<h5> 
									<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>"> <%=productBean.getProductName() %>  (<%=productBean.getCompanyName() %>)  </a> 
								</h5>
							
		                    	
		                    	<div class="item_add"><h6><span class="item_price"> <small class="over_flow"> Rs.<%=productBean.getPrice().getListPrice() %> </small> </br> <small class="item_price"> (<%=productBean.getPrice().getDiscount() %>% Off) </small> <br> <strong class="main_value">Rs <%=productBean.getPrice().getSalePriceCustomer() %></strong> </span></h6></div>
								<div class="item_add">
									<span class="item_price">
										<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>">More Details</a>
									</span>
								</div>
							</div>
						</li>
						<% 
							productBean = map.get("Boys_Shirt");
						%>
						<li>
						<div style="height:200px;">
							<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>">
								<img src="IconImageFromProduct?productId=<%=productBean.getProductId() %>" class="img-responsive center-block" alt="">
							</a>
							</div>
							<div class="special-info grid_1 simpleCart_shelfItem">
								<h5> 
									<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>"> <%=productBean.getProductName() %>  (<%=productBean.getCompanyName() %>)  </a> 
								</h5>
							
		                    	
		                    	<div class="item_add"><h6><span class="item_price"> <small class="over_flow"> Rs.<%=productBean.getPrice().getListPrice() %> </small> </br> <small class="item_price"> (<%=productBean.getPrice().getDiscount() %>% Off) </small> <br> <strong class="main_value">Rs <%=productBean.getPrice().getSalePriceCustomer() %></strong> </span></h6></div>
								<div class="item_add">
									<span class="item_price">
										<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>">More Details</a>
									</span>
								</div>
							</div>
						</li>
						<% 
							productBean = map.get("Girls_Top");
						%>
						<li>
						<div style="height:200px;">
							<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>">
								<img src="IconImageFromProduct?productId=<%=productBean.getProductId() %>" class="img-responsive center-block" alt="">
							</a>
							</div>
							<div class="special-info grid_1 simpleCart_shelfItem">
								<h5> 
									<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>"> <%=productBean.getProductName() %>  (<%=productBean.getCompanyName() %>)  </a> 
								</h5>
							
		                    	
		                    	<div class="item_add"><h6><span class="item_price"> <small class="over_flow"> Rs.<%=productBean.getPrice().getListPrice() %> </small> </br> <small class="item_price"> (<%=productBean.getPrice().getDiscount() %>% Off) </small> <br> <strong class="main_value">Rs <%=productBean.getPrice().getSalePriceCustomer() %></strong> </span></h6></div>
								<div class="item_add">
									<span class="item_price">
										<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>">More Details</a>
									</span>
								</div>
							</div>
						</li>
						<% 
							productBean = map.get("Girls_Shorts");
						%>
						<li>
						<div style="height:200px;">
							<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>">
								<img src="IconImageFromProduct?productId=<%=productBean.getProductId() %>" class="img-responsive center-block" alt="">
							</a>
							</div>
							<div class="special-info grid_1 simpleCart_shelfItem">
								<h5> 
									<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>"> <%=productBean.getProductName() %>  (<%=productBean.getCompanyName() %>)  </a> 
								</h5>
							
		                    	
		                    	<div class="item_add"><h6><span class="item_price"> <small class="over_flow"> Rs.<%=productBean.getPrice().getListPrice() %> </small> </br> <small class="item_price"> (<%=productBean.getPrice().getDiscount() %>% Off) </small> <br> <strong class="main_value">Rs <%=productBean.getPrice().getSalePriceCustomer() %></strong> </span></h6></div>
								<div class="item_add">
									<span class="item_price">
										<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>">More Details</a>
									</span>
								</div>
							</div>
						</li>
					</ul>
					<div class="clearfix"> </div>
				</div>	
				</div>
				
				<div class="col-md-2">
				
			 <div style="">
  
<a href="#" target="_blank">   <img src="images/ad2.jpg" style=" width:100%;margin-top: 30%;height:400px;">    </a>
         

       
 
</div>	
</div>
</div>
<!-- ----------------------------------- End Baby & Kids ----------------------------------- -->

<!-- ----------------------------------- Food & Grocery ----------------------------------- -->

<div class="row">
	<div class="col-md-2">					
		<div style="">	
			<div class="">	    
	 			<a href="#" target="_blank">   
	 				<img src="images/ad8.jpg" style="width: 100%; margin-top: 30%;height: 400px;">     
	 			</a>
	    	</div>    
		</div>	
	</div>
	<div class="col-md-8">
		<div class="special">
			<h3><a href="#" style="color: #FF8F00;">FOOD &AMP; GROCERY</a></h3>
		</div>
		<div class="specia-top">
			<ul class="grid_2">
				<% 
					productBean = map.get("Confectionery");
				%>
				<li>
				<div style="height:200px;">
					<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>">
						<img src="IconImageFromProduct?productId=<%=productBean.getProductId() %>" class="img-responsive center-block" alt="">
					</a>
					</div>
					<div class="special-info grid_1 simpleCart_shelfItem">
						<h5> 
							<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>"> <%=productBean.getProductName() %>  (<%=productBean.getCompanyName() %>)  </a> 
						</h5>
					
                    	
                    	<div class="item_add"><h6><span class="item_price"> <small class="over_flow"> Rs.<%=productBean.getPrice().getListPrice() %> </small> </br> <small class="item_price"> (<%=productBean.getPrice().getDiscount() %>% Off) </small> <br> <strong class="main_value">Rs <%=productBean.getPrice().getSalePriceCustomer() %></strong> </span></h6></div>
						<div class="item_add">
							<span class="item_price">
								<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>">More Details</a>
							</span>
						</div>
					</div>
				</li>
				<% 
					productBean = map.get("PowderProduct");
				%>
				<li>
				<div style="height:200px;">
					<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>">
						<img src="IconImageFromProduct?productId=<%=productBean.getProductId() %>" class="img-responsive center-block" alt="">
					</a>
					</div>
					<div class="special-info grid_1 simpleCart_shelfItem">
						<h5> 
							<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>"> <%=productBean.getProductName() %>  (<%=productBean.getCompanyName() %>)  </a> 
						</h5>
					
                    	
                    	<div class="item_add"><h6><span class="item_price"> <small class="over_flow"> Rs.<%=productBean.getPrice().getListPrice() %> </small> </br> <small class="item_price"> (<%=productBean.getPrice().getDiscount() %>% Off) </small> <br> <strong class="main_value">Rs <%=productBean.getPrice().getSalePriceCustomer() %></strong> </span></h6></div>
						<div class="item_add">
							<span class="item_price">
								<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>">More Details</a>
							</span>
						</div>
					</div>
				</li>
				<% 
					productBean = map.get("Cakes");
				%>
				<li>
				<div style="height:200px;">
					<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>">
						<img src="IconImageFromProduct?productId=<%=productBean.getProductId() %>" class="img-responsive center-block" alt="">
					</a>
					</div>
					<div class="special-info grid_1 simpleCart_shelfItem">
						<h5> 
							<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>"> <%=productBean.getProductName() %>  (<%=productBean.getCompanyName() %>)  </a> 
						</h5>
					
                    	
                    	<div class="item_add"><h6><span class="item_price"> <small class="over_flow"> Rs.<%=productBean.getPrice().getListPrice() %> </small> </br> <small class="item_price"> (<%=productBean.getPrice().getDiscount() %>% Off) </small> <br> <strong class="main_value">Rs <%=productBean.getPrice().getSalePriceCustomer() %></strong> </span></h6></div>
						<div class="item_add">
							<span class="item_price">
								<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>">More Details</a>
							</span>
						</div>
					</div>
				</li>
				<% 
					productBean = map.get("Dairy");
				%>
				<li>
				<div style="height:200px;">
					<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>">
						<img src="IconImageFromProduct?productId=<%=productBean.getProductId() %>" class="img-responsive center-block" alt="">
					</a>
					</div>
					<div class="special-info grid_1 simpleCart_shelfItem">
						<h5> 
							<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>"> <%=productBean.getProductName() %>  (<%=productBean.getCompanyName() %>)  </a> 
						</h5>
					
                    	
                    	<div class="item_add"><h6><span class="item_price"> <small class="over_flow"> Rs.<%=productBean.getPrice().getListPrice() %> </small> </br> <small class="item_price"> (<%=productBean.getPrice().getDiscount() %>% Off) </small> <br> <strong class="main_value">Rs <%=productBean.getPrice().getSalePriceCustomer() %></strong> </span></h6></div>
						<div class="item_add">
							<span class="item_price">
								<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>">More Details</a>
							</span>
						</div>
					</div>
				</li>
				<% 
					productBean = map.get("Rice");
				%>
				<li>
				<div style="height:200px;">
					<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>">
						<img src="IconImageFromProduct?productId=<%=productBean.getProductId() %>" class="img-responsive centr-block" alt="">
					</a>
					</div>
					<div class="special-info grid_1 simpleCart_shelfItem">
						<h5> 
							<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>"> <%=productBean.getProductName() %>  (<%=productBean.getCompanyName() %>)  </a> 
						</h5>
					
                    	
                    	<div class="item_add"><h6><span class="item_price"> <small class="over_flow"> Rs.<%=productBean.getPrice().getListPrice() %> </small> </br> <small class="item_price"> (<%=productBean.getPrice().getDiscount() %>% Off) </small> <br> <strong class="main_value">Rs <%=productBean.getPrice().getSalePriceCustomer() %></strong> </span></h6></div>
						<div class="item_add">
							<span class="item_price">
								<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>">More Details</a>
							</span>
						</div>
					</div>
				</li>	
			</ul>
			<div class="clearfix"> </div>
		</div>
	</div>
	<div class="col-md-2">
			<div style="">  
			<a href="#" target="_blank">   
				<img src="images/ad6.jpg" style="width:100%; margin-top: 30%;height: 400px;">    
			</a>   
		</div>
	</div>
</div>	

<!-- ----------------------------------- End Food & Grocery ----------------------------------- -->

<!-- ----------------------------------- Herbal ----------------------------------- -->

<div class="row">
	<div class="col-md-2">					
		<div style="">	
			<div class="">	    
	 			<a href="#" target="_blank">   
	 				<img src="images/ad8.jpg" style="width: 100%; margin-top: 30%;height: 400px;">     
	 			</a>
	    	</div>    
		</div>	
	</div>
	<div class="col-md-8">
		<div class="special">
			<h3><a href="#" style="color: #FF8F00;">HERBAL</a></h3>
		</div>
		<div class="specia-top">
			<ul class="grid_2">
				<% 
					productBean = map.get("Sampoo");
				%>
				<li>
				<div style="height:200px;">
					<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>">
						<img src="IconImageFromProduct?productId=<%=productBean.getProductId() %>" class="img-responsive center-block" alt="">
					</a>
					</div>
					<div class="special-info grid_1 simpleCart_shelfItem">
						<h5> 
							<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>"> <%=productBean.getProductName() %>  (<%=productBean.getCompanyName() %>)  </a> 
						</h5>
					
                    	
                    	<div class="item_add"><h6><span class="item_price"> <small class="over_flow"> Rs.<%=productBean.getPrice().getListPrice() %> </small> </br> <small class="item_price"> (<%=productBean.getPrice().getDiscount() %>% Off) </small> <br> <strong class="main_value">Rs <%=productBean.getPrice().getSalePriceCustomer() %></strong> </span></h6></div>
						<div class="item_add">
							<span class="item_price">
								<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>">More Details</a>
							</span>
						</div>
					</div>
				</li>
				<% 
					productBean = map.get("Medicine");
				%>
				<li>
				<div style="height:200px;">
					<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>">
						<img src="IconImageFromProduct?productId=<%=productBean.getProductId() %>" class="img-responsive center-block" alt="">
					</a>
					</div>
					<div class="special-info grid_1 simpleCart_shelfItem">
						<h5> 
							<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>"> <%=productBean.getProductName() %>  (<%=productBean.getCompanyName() %>)  </a> 
						</h5>
					
                    	
                    	<div class="item_add"><h6><span class="item_price"> <small class="over_flow"> Rs.<%=productBean.getPrice().getListPrice() %> </small> </br> <small class="item_price"> (<%=productBean.getPrice().getDiscount() %>% Off) </small> <br> <strong class="main_value">Rs <%=productBean.getPrice().getSalePriceCustomer() %></strong> </span></h6></div>
						<div class="item_add">
							<span class="item_price">
								<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>">More Details</a>
							</span>
						</div>
					</div>
				</li>
				<% 
					productBean = map.get("FatAnalyzer");
				%>
				<li>
				<div style="height:200px;">
					<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>">
						<img src="IconImageFromProduct?productId=<%=productBean.getProductId() %>" class="img-responsive center-block" alt="">
					</a>
					</div>
					<div class="special-info grid_1 simpleCart_shelfItem">
						<h5> 
							<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>"> <%=productBean.getProductName() %>  (<%=productBean.getCompanyName() %>)  </a> 
						</h5>
					
                    	
                    	<div class="item_add"><h6><span class="item_price"> <small class="over_flow"> Rs.<%=productBean.getPrice().getListPrice() %> </small> </br> <small class="item_price"> (<%=productBean.getPrice().getDiscount() %>% Off) </small> <br> <strong class="main_value">Rs <%=productBean.getPrice().getSalePriceCustomer() %></strong> </span></h6></div>
						<div class="item_add">
							<span class="item_price">
								<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>">More Details</a>
							</span>
						</div>
					</div>
				</li>
				<% 
					productBean = map.get("Soap");
				%>
				<li>
				<div style="height:200px;">
					<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>">
						<img src="IconImageFromProduct?productId=<%=productBean.getProductId() %>" class="img-responsive center-block" alt="">
					</a>
					</div>
					<div class="special-info grid_1 simpleCart_shelfItem">
						<h5> 
							<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>"> <%=productBean.getProductName() %>  (<%=productBean.getCompanyName() %>)  </a> 
						</h5>
					
                    	
                    	<div class="item_add"><h6><span class="item_price"> <small class="over_flow"> Rs.<%=productBean.getPrice().getListPrice() %> </small> </br> <small class="item_price"> (<%=productBean.getPrice().getDiscount() %>% Off) </small> <br> <strong class="main_value">Rs <%=productBean.getPrice().getSalePriceCustomer() %></strong> </span></h6></div>
						<div class="item_add">
							<span class="item_price">
								<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>">More Details</a>
							</span>
						</div>
					</div>
				</li>
				<% 
					productBean = map.get("Moisturizer");
				%>
				<li>
				<div style="height:200px;">
					<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>">
						<img src="IconImageFromProduct?productId=<%=productBean.getProductId() %>" class="img-responsive centr-block" alt="">
					</a>
					</div>
					<div class="special-info grid_1 simpleCart_shelfItem">
						<h5> 
							<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>"> <%=productBean.getProductName() %>  (<%=productBean.getCompanyName() %>)  </a> 
						</h5>
					
                    	
                    	<div class="item_add"><h6><span class="item_price"> <small class="over_flow"> Rs.<%=productBean.getPrice().getListPrice() %> </small> </br> <small class="item_price"> (<%=productBean.getPrice().getDiscount() %>% Off) </small> <br> <strong class="main_value">Rs <%=productBean.getPrice().getSalePriceCustomer() %></strong> </span></h6></div>
						<div class="item_add">
							<span class="item_price">
								<a href="CompleteProductDetails?subCategory=<%=productBean.getSubCategory() %>&productId=<%=productBean.getProductId() %>">More Details</a>
							</span>
						</div>
					</div>
				</li>	
			</ul>
			<div class="clearfix"> </div>
		</div>
	</div>
	<div class="col-md-2">
			<div style="">  
			<a href="#" target="_blank">   
				<img src="images/ad6.jpg" style="width:100%; margin-top: 30%;height: 400px;">    
			</a>   
		</div>
	</div>
</div>	

<!-- ----------------------------------- End Herbal ----------------------------------- -->






             <div class="row">
             <div class="col-md-2">
				<div style="">

<div class="">
    
    
 <a href="#" target="_blank">   <img src="images/ad11.jpg" style="width: 100%; margin-top: 30%;height: 400px;">     </a>
    
    
      
<!-- <script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script> -->
<!-- www.charge-it-up.com -->


    
    </div>
    
    
</div>
</div>

<div class="col-md-8">
<div class="special">
				<h3 style="margin-bottom: 15px;">Hot Categori</h3>
				</div>
				<div id="wrap">



<%-- <%@ include file="SliderHot.jsp" %> --%>

<div id="jssor_2" style="position: relative; margin: 0 auto; top: 0px; left: 0%; width: 1188px; height: 500px; overflow: hidden; visibility: hidden;">
        <!-- Loading Screen -->
        <div data-u="loading" style="position: absolute; top: 0px; left: 0px;">
            <div style="filter: alpha(opacity=70); opacity: 0.7; position: absolute; display: block; top: 0px; left: 0px; width: 100%; height: 100%;"></div>
            <div style="position:absolute;display:block;background:url('img/loading.gif') no-repeat center center;top:0px;left:0px;width:100%;height:100%;"></div>
        </div>
        <div data-u="slides" style="cursor: default; position: relative; top: 0px; left: 88px; width: 1013px; height: 500px; overflow: hidden;">
            <div style="display: none;">
                <img src="images/hotdeal.png" style="position:absolute;z-index:2;margin:auto;height:90px;margin-left: 2%;"/>
    			<div style="height:200px">
    			<a href="CompleteProductDetails?subCategory=<%=offeredProducts.get(0).getSubCategory() %>&productId=<%=offeredProducts.get(0).getProductId() %>">
					<img src="IconImageFromProduct?productId=<%=offeredProducts.get(0).getProductId() %>" class="img-responsive center-block" alt="" />
				</a>
				</div>
				<div class="special-info grid_1 simpleCart_shelfItem">
					<h5 style="height: 65px;"> 
						<a style="font-size: 14px;font-weight: bold;"  href="CompleteProductDetails?subCategory=<%=offeredProducts.get(0).getSubCategory() %>&productId=<%=offeredProducts.get(0).getProductId() %>"> <%=offeredProducts.get(0).getProductName() %>  (<%=offeredProducts.get(0).getCompanyName() %> )  </a> 
					</h5>
				
                   	
                   	<div class="item_add"><h6 style="height: 60px;"><span class="item_price"> <small class="over_flow"> Rs.<%=offeredProducts.get(0).getPrice().getListPrice() %> </small> <br/> <small class="item_price"> (<%=offeredProducts.get(0).getPrice().getDiscount() %>% Off) </small> <br> <strong class="main_value">Rs <%=offeredProducts.get(0).getPrice().getSalePriceCustomer() %></strong> </span></h6></div>
					<div class="item_add" style="height: 32px;">
						<span class="item_price">
							<a href="CompleteProductDetails?subCategory=<%=offeredProducts.get(0).getSubCategory() %>&productId=<%=offeredProducts.get(0).getProductId() %>">More Details</a>
						</span>
					</div>
				</div>
            </div>
            <div style="display: none;">
               <img src="images/hotdeal.png" style="position:absolute;z-index:2;margin:auto;height:90px;margin-left: 2%;"/>
    			<div style="height:200px">
    			<a href="CompleteProductDetails?subCategory=<%=offeredProducts.get(1).getSubCategory() %>&productId=<%=offeredProducts.get(1).getProductId() %>">
							<img src="IconImageFromProduct?productId=<%=offeredProducts.get(1).getProductId() %>" class="img-responsive center-block" alt="" />
						</a>
						</div>
						<div class="special-info grid_1 simpleCart_shelfItem">
							<h5 style="height: 65px;"> 
								<a style="font-size: 14px;font-weight: bold;"  href="CompleteProductDetails?subCategory=<%=offeredProducts.get(1).getSubCategory() %>&productId=<%=offeredProducts.get(1).getProductId() %>"> <%=offeredProducts.get(1).getProductName() %>  (<%=offeredProducts.get(1).getCompanyName() %> )  </a> 
							</h5>
						
	                    	
	                    	<div class="item_add"><h6 style="height: 60px;"><span class="item_price"> <small class="over_flow"> Rs.<%=offeredProducts.get(1).getPrice().getListPrice() %> </small> </br> <small class="item_price"> (<%=offeredProducts.get(1).getPrice().getDiscount() %>% Off) </small> <br> <strong class="main_value">Rs <%=offeredProducts.get(1).getPrice().getSalePriceCustomer() %></strong> </span></h6></div>
							<div class="item_add" style="height: 32px;">
								<span class="item_price">
									<a href="CompleteProductDetails?subCategory=<%=offeredProducts.get(1).getSubCategory() %>&productId=<%=offeredProducts.get(1).getProductId() %>">More Details</a>
								</span>
							</div>
						</div>
            </div>
            <div style="display: none;">
                <img src="images/hotdeal.png" style="position:absolute;z-index:2;margin:auto;height:90px;margin-left: 2%;"/>
    						<div style="height:200px">
    						<a href="CompleteProductDetails?subCategory=<%=offeredProducts.get(2).getSubCategory() %>&productId=<%=offeredProducts.get(2).getProductId() %>">
								<img src="IconImageFromProduct?productId=<%=offeredProducts.get(2).getProductId() %>" class="img-responsive center-block" alt="" />
							</a>
							</div>
						<div class="special-info grid_1 simpleCart_shelfItem">
							<h5 style="height: 65px;"> 
								<a style="font-size: 14px;font-weight: bold;"  href="CompleteProductDetails?subCategory=<%=offeredProducts.get(2).getSubCategory() %>&productId=<%=offeredProducts.get(2).getProductId() %>"> <%=offeredProducts.get(2).getProductName() %>  (<%=offeredProducts.get(2).getCompanyName() %> )  </a> 
							</h5>
						
	                    	
	                    	<div class="item_add"><h6 style="height: 60px;"><span class="item_price"> <small class="over_flow"> Rs.<%=offeredProducts.get(2).getPrice().getListPrice() %> </small> </br> <small class="item_price"> (<%=offeredProducts.get(2).getPrice().getDiscount() %>% Off) </small> <br> <strong class="main_value">Rs <%=offeredProducts.get(2).getPrice().getSalePriceCustomer() %></strong> </span></h6></div>
							<div class="item_add" style="height: 32px;">
								<span class="item_price">
									<a href="CompleteProductDetails?subCategory=<%=offeredProducts.get(2).getSubCategory() %>&productId=<%=offeredProducts.get(2).getProductId() %>">More Details</a>
								</span>
							</div>
						</div>
            </div>
            <div style="display: none;">
                <img src="images/hotdeal.png" style="position:absolute;z-index:2;margin:auto;height:90px;margin-left: 2%;"/>
    						<div style="height:200px">
    						<a href="CompleteProductDetails?subCategory=<%=offeredProducts.get(3).getSubCategory() %>&productId=<%=offeredProducts.get(3).getProductId() %>">
								<img src="IconImageFromProduct?productId=<%=offeredProducts.get(3).getProductId() %>" class="img-responsive center-block" alt=""/>
							</a>
							</div>
						<div class="special-info grid_1 simpleCart_shelfItem">
							<h5 style="height: 65px;"> 
								<a style="font-size: 14px;font-weight: bold;"  href="CompleteProductDetails?subCategory=<%=offeredProducts.get(3).getSubCategory() %>&productId=<%=offeredProducts.get(3).getProductId() %>"> <%=offeredProducts.get(3).getProductName() %>  (<%=offeredProducts.get(3).getCompanyName() %> )  </a> 
							</h5>
						
	                    	
	                    	<div class="item_add"><h6 style="height: 60px;"><span class="item_price"> <small class="over_flow"> Rs.<%=offeredProducts.get(3).getPrice().getListPrice() %> </small> </br> <small class="item_price"> (<%=offeredProducts.get(3).getPrice().getDiscount() %>% Off) </small> <br> <strong class="main_value">Rs <%=offeredProducts.get(3).getPrice().getSalePriceCustomer() %></strong> </span></h6></div>
							<div class="item_add" style="height: 32px;">
								<span class="item_price">
									<a href="CompleteProductDetails?subCategory=<%=offeredProducts.get(3).getSubCategory() %>&productId=<%=offeredProducts.get(3).getProductId() %>">More Details</a>
								</span>
							</div>
						</div>
            </div>
            <div style="display: none;">
                <img src="images/hotdeal.png" style="position:absolute;z-index:2;margin:auto;height:90px;margin-left: 2%;"/>
    						<div style="height:200px">
    						<a href="CompleteProductDetails?subCategory=<%=offeredProducts.get(4).getSubCategory() %>&productId=<%=offeredProducts.get(4).getProductId() %>">
								<img src="IconImageFromProduct?productId=<%=offeredProducts.get(4).getProductId() %>" class="img-responsive center-block" alt="" />
							</a>
							</div>
						<div class="special-info grid_1 simpleCart_shelfItem">
							<h5 style="height: 65px;"> 
								<a style="font-size: 14px;font-weight: bold;"  href="CompleteProductDetails?subCategory=<%=offeredProducts.get(4).getSubCategory() %>&productId=<%=offeredProducts.get(4).getProductId() %>"> <%=offeredProducts.get(4).getProductName() %>  (<%=offeredProducts.get(4).getCompanyName() %> )  </a> 
							</h5>
						
	                    	
	                    	<div class="item_add"><h6 style="height: 60px;"><span class="item_price"> <small class="over_flow"> Rs.<%=offeredProducts.get(4).getPrice().getListPrice() %> </small> </br> <small class="item_price"> (<%=offeredProducts.get(4).getPrice().getDiscount() %>% Off) </small> <br> <strong class="main_value">Rs <%=offeredProducts.get(4).getPrice().getSalePriceCustomer() %></strong> </span></h6></div>
							<div class="item_add" style="height: 32px;">
								<span class="item_price">
									<a href="CompleteProductDetails?subCategory=<%=offeredProducts.get(4).getSubCategory() %>&productId=<%=offeredProducts.get(4).getProductId() %>">More Details</a>
								</span>
							</div>
						</div>
            </div>
            <div style="display: none;">
               <img src="images/hotdeal.png" style="position:absolute;z-index:2;margin:auto;height:90px;margin-left: 2%;"/>
    						<div style="height:200px">
    						<a href="CompleteProductDetails?subCategory=<%=offeredProducts.get(5).getSubCategory() %>&productId=<%=offeredProducts.get(5).getProductId() %>">
								<img src="IconImageFromProduct?productId=<%=offeredProducts.get(5).getProductId() %>" class="img-responsive center-block" alt="" />
							</a>
							</div>
						<div class="special-info grid_1 simpleCart_shelfItem">
							<h5 style="height: 65px;"> 
								<a style="font-size: 14px;font-weight: bold;"  href="CompleteProductDetails?subCategory=<%=offeredProducts.get(5).getSubCategory() %>&productId=<%=offeredProducts.get(5).getProductId() %>"> <%=offeredProducts.get(5).getProductName() %>  (<%=offeredProducts.get(5).getCompanyName() %> )  </a> 
							</h5>
						
	                    	
	                    	<div class="item_add"><h6 style="height: 60px;"><span class="item_price"> <small class="over_flow"> Rs.<%=offeredProducts.get(5).getPrice().getListPrice() %> </small> </br> <small class="item_price"> (<%=offeredProducts.get(5).getPrice().getDiscount() %>% Off) </small> <br> <strong class="main_value">Rs <%=offeredProducts.get(5).getPrice().getSalePriceCustomer() %></strong> </span></h6></div>
							<div class="item_add" style="height: 32px;">
								<span class="item_price">
									<a href="CompleteProductDetails?subCategory=<%=offeredProducts.get(5).getSubCategory() %>&productId=<%=offeredProducts.get(5).getProductId() %>">More Details</a>
								</span>
							</div>
						</div>
            </div>
            
           
          
           
            
        
        </div>
        <!-- Bullet Navigator -->
        <div data-u="navigator" class="jssorb03" style="bottom:10px;right:10px;">
            <!-- bullet navigator item prototype -->
            <div data-u="prototype" style="width:21px;height:21px;">
                <div data-u="numbertemplate"></div>
            </div>
        </div>
        <!-- Arrow Navigator -->
        <span data-u="arrowleft" class="jssora03l" style="top:0px;left:8px;width:55px;height:55px;" data-autocenter="2"></span>
        <span data-u="arrowright" class="jssora03r" style="top:0px;right:8px;width:55px;height:55px;" data-autocenter="2"></span>
    </div>
    <script>
        jssor_2_slider_init();
    </script>

    <!-- #endregion Jssor Slider End -->

</div>
</div>
<div class="col-md-2">

 <div style="">
  
<a href="#" target="_blank">   <img src="images/ad7.jpg" style="width:100%; margin-top: 30%;height: 400px;">    </a>
         

       
 
</div>
</div>
</div>
	
				
				
				
<!-- ------------------------------------End Banner--------------------------------------------- -->
		
			<!-- <h3>Shop By Branded</h3>	 -->	
	


<!-- ------------------------------------------End Body----------------------------------------------- -->

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



<!-- -------------------------------------Footer------------------------------------------------------ -->
<%@ include file="Footer.jsp" %>
<!-- -------------------------------------End Footer-------------------------------------------------- -->


	<script type="text/javascript" src="js/bxslider.min.js" ></script>
	<script type="text/javascript" src="js/script.slider.js"></script>
</body>


</html>