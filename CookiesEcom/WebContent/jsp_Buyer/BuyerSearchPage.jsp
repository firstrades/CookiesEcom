<%@page import="ecom.beans.CartAttributesBean"%>
<%@page import="ecom.common.FrequentUse"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %> 
<%@ page import="ecom.model.*" %>  

<!DOCTYPE HTML>
<html>
<head>
	<title> Buyer's Search Page </title>
	
	<link href="css/bootstrap.css" rel='stylesheet' type='text/css' />		
	<link href="css/megamenu.css" rel="stylesheet" type="text/css" media="all" />	
	<link href="<%=FrequentUse.style %>" rel='stylesheet' type='text/css' />
	
	<meta name="viewport" content="width=device-width, initial-scale=1"> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="keywords" content="First Trades Online Shoppin" />	
	
	<script type="text/javascript" src="<%=FrequentUse.jQuery %>"></script>
	<script type="text/javascript" src="js/megamenu.js"></script>
	<script>
		$(document).ready(function(){$(".megamenu").megamenu();});
	</script>
	<script src="js/menu_jquery.js"></script>
	<script src="js/simpleCart.min.js"> </script>
	<script src="js/bootstrap.min.js"></script>	
	<script type="text/javascript" src="js_Buyer/BuyerProductSearchResult.js"></script>
	
	<!-- -------- Search -------------- -->
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">	  
	<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>  
	<script type="text/javascript" src="js_Buyer/autocomplete.js"></script>
	
	<style type="text/css">
	.clear_fix{
		clear: both;
	} 
	
	</style>
	

</head>
<body>

<%    //--------------------From Servlet - ecom.SERVLET.buyer.BuyerServlet  /SearchBySubCategory------------------------         %>

<%
	User user = (User) session.getAttribute("user"); 
	
%>

<%
	@SuppressWarnings("all")
	List<Product> productBeanList = (List<Product>) request.getAttribute("productBeanList");
	Integer MAX = (Integer) request.getAttribute("MAX");
	
	int tempCount = productBeanList.size() / 5; 
	int productBeanList_DivCount = 0;
	if (productBeanList.size() % 5 == 0)
		productBeanList_DivCount =  tempCount;
	else
		productBeanList_DivCount =  tempCount + 1;
	
	System.out.println("productBeanList_DivCount: " + productBeanList_DivCount);
	
	//int productBeanList_DivCount = productBeanList.size() / 5;  
	
	int k = 0;
	int id = 0;
	
	//System.out.println(productBeanList_DivCount);
%>

<% if (user == null) { %>

	<!-- Header -->
	<%@ include file="HeaderAllUser.jsp" %>
	<!-- End Header -->

<% } else { %>

	<!-- Header -->
	<%@ include file="Header.jsp" %>
	<!-- End Header -->

<% } %>

<!-- -----------------------------Navigation-------------------------------------------------- -->  
    
    <div class="navigation" style="background-color: #F57D51;height: 39px;">
    	<div class="container">
		<!-- start header menu -->
			<ul class="megamenu skyblue" style="width: 107%;">
				<% if (user == null) { %>
				<li class="grid"><a class="color1" href="index.jsp">HOME</a>
				<% } else { %>
				<li class="grid"><a class="color1" href="BuyerMainPanel">HOME</a>
				<% } %>
				<% if (productBeanList.get(0).getCategory().equals("ELECTRONICS")) { %>
				<li class="active grid"><a class="color1" href="#">ELECTRONICS</a>
				<% } else { %> 
				<li class="grid"><a class="color1" href="#">ELECTRONICS</a>
				<% } %>
					<div class="megapanel">
						<div class="row">
							<div class="col1">
								<div class="h_nav">
									<h4>Commons</h4>
									<ul class="leftIndentOfProduct">										
										<li><a href="SearchBySubCategory?subCategory=Mobile">Mobile</a></li>
										<li><a href="SearchBySubCategory?subCategory=Laptop">Laptop</a></li>
										<li><a href="SearchBySubCategory?subCategory=Tablet">Tablet</a></li>
										<li><a href="SearchBySubCategory?subCategory=Camera">Camera</a></li>
										<li><a href="SearchBySubCategory?subCategory=Television">Television</a></li>										
									</ul>	
								</div>							
							</div>
							<div class="col1">
								<div class="h_nav">
									<h4>Accessories</h4>
									<ul>
										<li><a href="#">Mobile Accessories</a></li>
										<li><a href="#">Laptop Accessories</a></li>
										<li><a href="#">Tablet Accessories</a></li>
										<li><a href="#">Camera Accessories</a></li>
										<li><a href="#">Home Accessories</a></li>
										<li><a href="#">Television Accessories</a></li>
									</ul>	
								</div>							
							</div>



								<div class="col_images">
								<div class="h_nav">
									<img src="images/electronic.jpg" alt="Electronic">
									</div>
									</div>
													
							


						</div>
						<div class="row">
							<div class="col2"></div>
							<div class="col1"></div>
							<div class="col1"></div>
							<div class="col1"></div>
							<div class="col1"></div>
						</div>
    				</div>
				</li>
				<% if (productBeanList.get(0).getCategory().equals("MEN")) { %>
				<li class="active grid"><a class="color1" href="#">MEN</a>
				<% } else { %> 
				<li class="grid"><a class="color1" href="#">MEN</a>
				<% } %>
					<div class="megapanel">
						<div class="row">
							<div class="col1">
								<div class="h_nav">
									<h4>Clothing</h4>
									<ul class="leftIndentOfProduct">
										<li><a href="SearchBySubCategory?subCategory=MenTshirt">T-Shirt</a></li><!-- half complete -->
										<li><a href="SearchBySubCategory?subCategory=MenJeans">Jeans</a></li>
										<li><a href="SearchBySubCategory?subCategory=MenShirt">Shirt</a></li>
										<li><a href="SearchBySubCategory?subCategory=MenTrouser">Trouser</a></li>
										<li><a href="SearchBySubCategory?subCategory=MenShoes">Shoes</a></li>          <!-- complete -->
																				
									</ul>	
								</div>							
							</div>
							<div class="col1">
								<div class="h_nav">
									<h4>Accessories</h4>
									<ul class="leftIndentOfProduct">
										<li><a href="#">Shoes</a></li>     
										<li><a href="#">Watch</a></li>
										<li><a href="#">Wallet</a></li>
										<li><a href="#">Belt</a></li>
										<li><a href="#">Sunglasses</a></li>
										<li><a href="#">Deodrants</a></li>
										<li><a href="#">Perfumes</a></li>
									</ul>	
								</div>							
							</div>

								<div class="col_images">
								<div class="h_nav">
									<img src="images/men.jpg" alt="men">
									</div>
									</div>


						</div>
						<div class="row">
							<div class="col2"></div>
							<div class="col1"></div>
							<div class="col1"></div>
							<div class="col1"></div>
							<div class="col1"></div>
						</div>
    				</div>
				</li>								
				<% if (productBeanList.get(0).getCategory().equals("WOMEN")) { %>
				<li class="active grid"><a class="color1" href="#">WOMEN</a>
				<% } else { %> 
				<li class="grid"><a class="color1" href="#">WOMEN</a>
				<% } %>
					<div class="megapanel">
						<div class="row">
							<div class="col1">
								<div class="h_nav">
									<h4>Clothing</h4>
									<ul>
										<li><a href="SearchBySubCategory?subCategory=WomenShoe">Shoes</a></li>
										<li><a href="SearchBySubCategory?subCategory=WomenKurta">Kurti</a></li>
										<li><a href="SearchBySubCategory?subCategory=WomenSharee">Sarees</a></li>
										<li><a href="SearchBySubCategory?subCategory=WomenSalwar">Salwars</a></li>
										<li><a href="SearchBySubCategory?subCategory=WomenJeans">Jeans</a></li>
										<li><a href="SearchBySubCategory?subCategory=WomenLeggings">Leggings</a></li>
										
									</ul>	
								</div>							
							</div>
							<div class="col1">
								<div class="h_nav">
									<h4>kids</h4>
									<ul>
										<li><a href="#">Pools&#38;Tees</a></li>
										<li><a href="#">shirts</a></li>
										<li><a href="#">shorts</a></li>
										<li><a href="#">twinsets</a></li>
										<li><a href="#">kurts</a></li>
										<li><a href="#">jackets</a></li>
									</ul>	
								</div>							
							</div>


								<div class="col_images">
								<div class="h_nav">
									<img src="images/women.jpg" alt="women">
									</div>
									</div>

						</div>
						<div class="row">
							<div class="col2"></div>
							<div class="col1"></div>
							<div class="col1"></div>
							<div class="col1"></div>
							<div class="col1"></div>
						</div>
    				</div>
				</li>				
				<% if (productBeanList.get(0).getCategory().equals("HomeAndKitchen")) { %>
				<li class="active grid"><a class="color1" href="#">Home&amp;Kitchen</a>
				<% } else { %> 
				<li class="grid"><a class="color1" href="#">Home&amp;Kitchen</a>
				<% } %>
					<div class="megapanel">
						<div class="row">
							<div class="col1">
								<div class="h_nav">
									<h4>Required</h4>
									<ul>
										<li><a href="SearchBySubCategory?subCategory=Bedsheets">Bedsheets</a></li>
										<li><a href="SearchBySubCategory?subCategory=Curtains">Curtains</a></li>
										<li><a href="SearchBySubCategory?subCategory=SofaCovers">Sofa Covers</a></li>
										<li><a href="SearchBySubCategory?subCategory=PressureCookers">Pressure Cookers</a></li>
										<li><a href="SearchBySubCategory?subCategory=GasStoves">Gas Stoves</a></li>
									</ul>	
								</div>							
							</div>
							

							<div class="col_images">
								<div class="h_nav">
									<img src="images/herbal.jpg" alt="herbal">
								</div>
							</div>


						</div>
						<div class="row">
							<div class="col2"></div>
							<div class="col1"></div>
							<div class="col1"></div>
							<div class="col1"></div>
							<div class="col1"></div>
						</div>
	    			</div>
				</li>
				<% if (productBeanList.get(0).getCategory().equals("KIDS")) { %>
				<li class="active grid"><a class="color1" href="#">BABY&amp;KIDS</a>
				<% } else { %> 
				<li class="grid"><a class="color1" href="#">BABY&amp;KIDS</a>
				<% } %>
					<div class="megapanel">
						<div class="row">
							<div class="col1">
								<div class="h_nav">
									<h4>Boys</h4>
									<ul>
										<li><a href="SearchBySubCategory?subCategory=Boys_Shirt">Shirt</a></li>
										<li><a href="SearchBySubCategory?subCategory=Boys_Pant">Pant</a></li>
										<li><a href="SearchBySubCategory?subCategory=Baby_Diapers">Baby Diapers</a></li>
																			
									</ul>	
								</div>							
							</div>
							<div class="col1">
								<div class="h_nav">
									<h4>Girls</h4>
									<ul>
										<li><a href="SearchBySubCategory?subCategory=Girls_Top">Top</a></li>
										<li><a href="SearchBySubCategory?subCategory=Girls_Shorts">Shorts</a></li>	
									</ul>	
								</div>							
							</div>

								<div class="col_images">
								<div class="h_nav">
									<img src="images/baby.jpg" alt="baby">
									</div>
									</div>
	
		
						</div>
						<div class="row">
							<div class="col2"></div>
							<div class="col1"></div>
							<div class="col1"></div>
							<div class="col1"></div>
							<div class="col1"></div>
						</div>
	    				</div>
				</li>				
			
							
				<% if (productBeanList.get(0).getCategory().equals("FoodAndGrocery")) { %>
				<li class="active grid"><a class="color1" href="#">FOOD&amp;GROCERY</a>
				<% } else { %> 
				<li class="grid"><a class="color1" href="#">FOOD&amp;GROCERY</a>
				<% } %>
				<div class="megapanel">
						<div class="row">
							<div class="col1">
								<div class="h_nav">
									<h4>Food</h4>
									<ul>
										<li><a href="SearchBySubCategory?subCategory=Confectionery">Confectionery</a></li>
										<li><a href="SearchBySubCategory?subCategory=PowderProduct">PowderProduct</a></li>
										<li><a href="SearchBySubCategory?subCategory=Cakes">Cakes</a></li>	
										<li><a href="SearchBySubCategory?subCategory=Dairy">Dairy</a></li>
										<li><a href="SearchBySubCategory?subCategory=Rice">Rice</a></li>									
									</ul>	
								</div>							
							</div>					
					
						
							<div class="col_images">
								<div class="h_nav">
									<img src="images/food.jpg" alt="food">
								</div>
							</div>
					
						</div>
						<div class="row">
							<div class="col2"></div>
							<div class="col1"></div>
							<div class="col1"></div>
							<div class="col1"></div>
							<div class="col1"></div>
						</div>
	    				</div>
				
				</li>
				<% if (productBeanList.get(0).getCategory().equals("Herbal")) { %>
				<li class="active grid"><a class="color1" href="#">Herbal</a>
				<% } else { %> 
				<li class="grid"><a class="color1" href="#">Herbal</a>
				<% } %>				
				<div class="megapanel">
						<div class="row">
							<div class="col1">
								<div class="h_nav">
									<h4>Required</h4>
									<ul>
										<li><a href="SearchBySubCategory?subCategory=Sampoo">Sampoo</a></li>
										<li><a href="SearchBySubCategory?subCategory=Medicine">Medicine</a></li>
										<li><a href="SearchBySubCategory?subCategory=FatAnalyzer">Fat Analyzer</a></li>
										<li><a href="SearchBySubCategory?subCategory=Soap">Soap</a></li>
										<li><a href="SearchBySubCategory?subCategory=Moisturizer">Moisturizer</a></li>
									</ul>	
								</div>							
							</div>
							

							<div class="col_images">
								<div class="h_nav">
									<img src="images/herbal.jpg" alt="herbal">
								</div>
							</div>


						</div>
						<div class="row">
							<div class="col2"></div>
							<div class="col1"></div>
							<div class="col1"></div>
							<div class="col1"></div>
							<div class="col1"></div>
						</div>
	    			</div>
				</li>
				<li><a class="color5" href="#">BOND &amp; MUTUAL FUND</a>	
				<div class="megapanel">
						<div class="row">
							<div class="col1">
								<div class="h_nav">
									<h4>Clothing</h4>
									<ul>
										<li><a href="#">new arrivals</a></li>
										<li><a href="#">men</a></li>
										<li><a href="#">women</a></li>
										<li><a href="#">accessories</a></li>
										<li><a href="#">kids</a></li>
										<li><a href="#">brands</a></li>
									</ul>	
								</div>							
							</div>
							<div class="col1">
								<div class="h_nav">
									<h4>kids</h4>
									<ul>
										<li><a href="#">Pools&#38;Tees</a></li>
										<li><a href="#">shirts</a></li>
										<li><a href="#">shorts</a></li>
										<li><a href="#">twinsets</a></li>
										<li><a href="#">kurts</a></li>
										<li><a href="#">jackets</a></li>
									</ul>	
								</div>							
							</div>

								<div class="col_images">
								<div class="h_nav">
									<img src="images/matual.jpg" alt="matual">
									</div>
									</div>
	
		
						</div>
						<div class="row">
							<div class="col2"></div>
							<div class="col1"></div>
							<div class="col1"></div>
							<div class="col1"></div>
							<div class="col1"></div>
						</div>
	    				</div>
	    				</li>	
				<li><a class="color3" href="#">OFFER</a>	
				<div class="megapanel">
						<div class="row">
							<div class="col1">
								<div class="h_nav">
									<h4>Clothing</h4>
									<ul>
										<li><a href="#">new arrivals</a></li>
										<li><a href="#">men</a></li>
										<li><a href="#">women</a></li>
										<li><a href="#">accessories</a></li>
										<li><a href="#">kids</a></li>
										<li><a href="#">brands</a></li>
									</ul>	
								</div>							
							</div>
							<div class="col1">
								<div class="h_nav">
									<h4>kids</h4>
									<ul>
										<li><a href="#">Pools&#38;Tees</a></li>
										<li><a href="#">shirts</a></li>
										<li><a href="#">shorts</a></li>
										<li><a href="#">twinsets</a></li>
										<li><a href="#">kurts</a></li>
										<li><a href="#">jackets</a></li>
									</ul>	
								</div>							
							</div>

								<div class="col_images">
								<div class="h_nav">
									<img src="images/Offer-Banner.jpg" alt="Offer-Banner" style="height: 220px;">
									</div>
									</div>
	
		
						</div>
						<div class="row">
							<div class="col2"></div>
							<div class="col1"></div>
							<div class="col1"></div>
							<div class="col1"></div>
							<div class="col1"></div>
						</div>
	    				</div>
				</li>	
				<li><a class="color5" href="#">Hot</a></li>
		 	</ul> 
		</div>    
	</div>


<!-- ------------------------------------End Navigation--------------------------------------------- -->



<!-- ----------------------------------------------------BODY-------------------------------------------------------- -->

<% 

	if (request.getAttribute("errorMsg") != null) { System.out.println("Jewel 2");
		String errorMsg = (String) request.getAttribute("errorMsg"); 
		out.println("<div style=\"margin-left: 342px;color:red;\">"+errorMsg+"</div>");
	}
%>

<div class="special">
	<div class="container" id="append">		
		<h3>Search Results </h3>	
		
		<% for (int i = 0; i < productBeanList_DivCount; i++) { %>
			<div class="specia-top" id="<%=id%>">
				<ul class="grid_2">	
							
				<% for (int j = 0; j < 5; j++) { %>
					<li>
					 <div style="height:200px">
						<a href="CompleteProductDetails?subCategory=<%=productBeanList.get(k+j).getSubCategory() %>&productId=<%=productBeanList.get(k+j).getProductId() %>">
							<img src="IconImageFromProduct?productId=<%=productBeanList.get(k+j).getProductId() %>" class="img-responsive center-block" alt="">
						</a>
						</div>
						<div class="special-info grid_1 simpleCart_shelfItem">
						
							<div class="all_pannel">
								<p>ProductId: <%=productBeanList.get(k+j).getProductId() %></p>
								<h5>
									<a href="CompleteProductDetails?subCategory=<%=productBeanList.get(k+j).getSubCategory() %>&productId=<%=productBeanList.get(k+j).getProductId() %>"> <%=productBeanList.get(k+j).getProductName() %>  (<%=productBeanList.get(k+j).getCompanyName() %>) </a>
								</h5>                    				
								<div class="item_add"><h6><span class="item_price"> <small class="over_flow"> Rs.<%=productBeanList.get(k+j).getPrice().getListPrice() %> </small> <br> <small class="item_price"> (<%=productBeanList.get(k+j).getPrice().getDiscount() %>% Off) </small> <br> <strong class="main_value">Rs <%=productBeanList.get(k+j).getPrice().getSalePriceCustomer() %></strong> </span></h6></div>
								<div class="item_add">
									<span class="item_price">
										<a href="CompleteProductDetails?subCategory=<%=productBeanList.get(k+j).getSubCategory() %>&productId=<%=productBeanList.get(k+j).getProductId() %>">More Details</a>
									</span>
								</div>
							</div>
							
						</div>
						
					</li>
				<% 		
						if ((k+j) == productBeanList.size()-1) break;
				    } 
				%>
					
				</ul>
				<div class="clearfix"> </div>
			</div>
			
			
		<% 
				id++;
				k = k + 5;
		   } // productBeanList_DivCount loop ends 
		%>
		
		<!-- For jQuery calculation -->
		<div id="max" style="display:none;"><%=id %></div>
		<div id="subCat" style="display:none;"><%=productBeanList.get(0).getSubCategory() %></div>		
			
	</div>
</div>

<div id="loadMore">
	<div class="item_add"><span class="item_price" id="span"><a id="a" data-max="<%=MAX%>">Load More</a></span></div>
</div>

<!-- ------------------------------------------End Body----------------------------------------------- -->



<!-- -------------------------------------Footer------------------------------------------------------ -->
<%@ include file="Footer.jsp" %>
<!-- -------------------------------------End Footer-------------------------------------------------- -->


	<script type="text/javascript" src="js/bxslider.min.js"></script>
	<script type="text/javascript" src="js/script.slider.js"></script>

</body>
</html>