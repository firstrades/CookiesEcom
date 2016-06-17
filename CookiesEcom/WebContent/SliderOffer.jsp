<%@page import="ecom.common.OfferedHot"%>
<%@page import="ecom.model.Product"%>
<%@page import="java.util.List"%>
<%@page import="ecom.DAO.administration.AdminDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

</body>
</html> -->
<!-- <script type="text/javascript" src="indexjs/jquery-1.2.3.pack.js"></script> -->
<!--
  jCarousel library
-->
<script type="text/javascript" src="indexjs/jquery.jcarousel.pack.js"></script>
<script type="text/javascript">
function mycarousel_initCallback(carousel)
{
    // Disable autoscrolling if the user clicks the prev or next button.
    carousel.buttonNext.bind('click', function() {
        carousel.startAuto(0);
    });

    carousel.buttonPrev.bind('click', function() {
        carousel.startAuto(0);
    });

    // Pause autoscrolling if the user moves with the cursor over the clip.
    carousel.clip.hover(function() {
        carousel.stopAuto();
    }, function() {
        carousel.startAuto();
    });
};

jQuery(document).ready(function() {
    jQuery('#mycarousel').jcarousel({
        auto: 3,
        wrap: 'last',
		scroll: 2,
		initCallback: mycarousel_initCallback
    });
});
function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}

function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}

function mycarousel_initCallback(carousel)
{
    // Disable autoscrolling if the user clicks the prev or next button.
    carousel.buttonNext.bind('click', function() {
        carousel.startAuto(0);
    });

    carousel.buttonPrev.bind('click', function() {
        carousel.startAuto(0);
    });

    // Pause autoscrolling if the user moves with the cursor over the clip.
    carousel.clip.hover(function() {
        carousel.stopAuto();
    }, function() {
        carousel.startAuto();
    });
};

jQuery(document).ready(function() {
    jQuery('#mycarousel2').jcarousel({
        auto: 3,
        wrap: 'last',
		scroll: 2,
		initCallback: mycarousel_initCallback
    });
});
function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}

function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}
</script>


<%
AdminDAO adminDAO = AdminDAO.getInstance();
List<Product> offeredProducts = adminDAO.getOfferedHot(OfferedHot.OFFERED);
//List<Product> hotProducts     = adminDAO.getOfferedHot(OfferedHot.HOT);

%>

<!-- ---------------------------------------slider---------------------------------------------------------------------- -->

  <ul id="mycarousel" class="jcarousel-skin-tango" style="float: left;margin-left: 5%;">
  
    <li style="position:relative;">
    <img src="images/offer.png" style="position:absolute;z-index:2;margin:auto;height:90px;margin-left: 2%;"/>
    <a href="CompleteProductDetails?subCategory=<%=offeredProducts.get(0).getSubCategory() %>&productId=<%=offeredProducts.get(0).getProductId() %>">
							<img src="IconImageFromProduct?productId=<%=offeredProducts.get(0).getProductId() %>" class="img-responsive" alt="" style="height: 200px;width: 280px;"/>
						</a>
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
						</li>
						
			<li style="position:relative;">
    			<img src="images/offer.png" style="position:absolute;z-index:2;margin:auto;height:90px;margin-left: 2%;"/>
    			<a href="CompleteProductDetails?subCategory=<%=offeredProducts.get(1).getSubCategory() %>&productId=<%=offeredProducts.get(1).getProductId() %>">
							<img src="IconImageFromProduct?productId=<%=offeredProducts.get(1).getProductId() %>" class="img-responsive" alt="" style="height: 200px;width: 280px;"/>
						</a>
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
						</li>
						<li style="position:relative;">
    						<img src="images/offer.png" style="position:absolute;z-index:2;margin:auto;height:90px;margin-left: 2%;"/>
    						<a href="CompleteProductDetails?subCategory=<%=offeredProducts.get(2).getSubCategory() %>&productId=<%=offeredProducts.get(2).getProductId() %>">
								<img src="IconImageFromProduct?productId=<%=offeredProducts.get(2).getProductId() %>" class="img-responsive" alt="" style="height: 200px;width: 280px;"/>
							</a>
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
						</li>
						<li style="position:relative;">
    						<img src="images/offer.png" style="position:absolute;z-index:2;margin:auto;height:90px;margin-left: 2%;"/>
    						<a href="CompleteProductDetails?subCategory=<%=offeredProducts.get(3).getSubCategory() %>&productId=<%=offeredProducts.get(3).getProductId() %>">
								<img src="IconImageFromProduct?productId=<%=offeredProducts.get(3).getProductId() %>" class="img-responsive" alt="" style="height: 200px;width: 280px;"/>
							</a>
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
						</li>
						<li style="position:relative;">
    						<img src="images/offer.png" style="position:absolute;z-index:2;margin:auto;height:90px;margin-left: 2%;"/>
    						<a href="CompleteProductDetails?subCategory=<%=offeredProducts.get(4).getSubCategory() %>&productId=<%=offeredProducts.get(4).getProductId() %>">
								<img src="IconImageFromProduct?productId=<%=offeredProducts.get(4).getProductId() %>" class="img-responsive" alt="" style="height: 200px;width: 280px;"/>
							</a>
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
						</li>
						<li style="position:relative;">
    						<img src="images/offer.png" style="position:absolute;z-index:2;margin:auto;height:90px;margin-left: 2%;"/>
    						<a href="CompleteProductDetails?subCategory=<%=offeredProducts.get(5).getSubCategory() %>&productId=<%=offeredProducts.get(5).getProductId() %>">
								<img src="IconImageFromProduct?productId=<%=offeredProducts.get(5).getProductId() %>" class="img-responsive" alt="" style="height: 200px;width: 280px;"/>
							</a>
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
						</li>
   
    
  </ul>
  
  <!-- ---------------------------------------end  slider---------------------------------------------------------------------- -->