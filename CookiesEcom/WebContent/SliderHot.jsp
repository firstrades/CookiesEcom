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


<%
	AdminDAO adminDAO1 = AdminDAO.getInstance();
	//List<Product> offeredProducts = adminDAO.getOfferedHot(OfferedHot.OFFERED);
	List<Product> hotProducts     = adminDAO1.getOfferedHot(OfferedHot.HOT);

%>


<ul id="mycarousel2" class="jcarousel-skin-tango">
  
    <li style="position:relative;">
    <img src="images/hotdeal.png" style="position:absolute;z-index:2;margin:auto;height:80px;margin-left: 2%;"/>
    <a href="CompleteProductDetails?subCategory=<%=hotProducts.get(0).getSubCategory() %>&productId=<%=hotProducts.get(0).getProductId() %>">
		<img src="IconImageFromProduct?productId=<%=hotProducts.get(0).getProductId() %>" class="img-responsive" alt="" style="height: 200px;width: 280px;"/>
	</a>
						<div class="special-info grid_1 simpleCart_shelfItem">
							<h5 style="height: 65px;"> 
								<a style="font-size: 14px;font-weight: bold;"  href="CompleteProductDetails?subCategory=<%=hotProducts.get(0).getSubCategory() %>&productId=<%=hotProducts.get(0).getProductId() %>"> <%=hotProducts.get(0).getProductName() %>  (<%=hotProducts.get(0).getCompanyName() %> )  </a> 
							</h5>
						
	                    	
	                    	<div class="item_add"><h6 style="height: 60px;"><span class="item_price"> <small class="over_flow"> Rs.<%=hotProducts.get(0).getPrice().getListPrice() %> </small> </br> <small class="item_price"> (<%=hotProducts.get(0).getPrice().getDiscount() %>% Off) </small> <br> <strong class="main_value">Rs <%=hotProducts.get(0).getPrice().getSalePriceCustomer() %></strong> </span></h6></div>
							<div class="item_add" style="height: 32px;">
								<span class="item_price">
									<a href="CompleteProductDetails?subCategory=<%=hotProducts.get(0).getSubCategory() %>&productId=<%=hotProducts.get(0).getProductId() %>">More Details</a>
								</span>
							</div>
						</div>
						</li>
						
			<li style="position:relative;">
    		<img src="images/hotdeal.png" style="position:absolute;z-index:2;margin:auto;height:80px;margin-left: 2%;"/>
    		<a href="CompleteProductDetails?subCategory=<%=hotProducts.get(1).getSubCategory() %>&productId=<%=hotProducts.get(1).getProductId() %>">
				<img src="IconImageFromProduct?productId=<%=hotProducts.get(1).getProductId() %>" class="img-responsive" alt="" style="height: 200px;width: 280px;"/>
			</a>
						<div class="special-info grid_1 simpleCart_shelfItem">
							<h5 style="height: 65px;"> 
								<a style="font-size: 14px;font-weight: bold;"  href="CompleteProductDetails?subCategory=<%=hotProducts.get(1).getSubCategory() %>&productId=<%=hotProducts.get(1).getProductId() %>"> <%=hotProducts.get(1).getProductName() %>  (<%=hotProducts.get(1).getCompanyName() %> )  </a> 
							</h5>
						
	                    	
	                    	<div class="item_add"><h6 style="height: 60px;"><span class="item_price"> <small class="over_flow"> Rs.<%=hotProducts.get(1).getPrice().getListPrice() %> </small> </br> <small class="item_price"> (<%=hotProducts.get(1).getPrice().getDiscount() %>% Off) </small> <br> <strong class="main_value">Rs <%=hotProducts.get(1).getPrice().getSalePriceCustomer() %></strong> </span></h6></div>
							<div class="item_add" style="height: 32px;">
								<span class="item_price">
									<a href="CompleteProductDetails?subCategory=<%=hotProducts.get(1).getSubCategory() %>&productId=<%=hotProducts.get(1).getProductId() %>">More Details</a>
								</span>
							</div>
						</div>
						</li>
						<li style="position:relative;">
    						<img src="images/hotdeal.png" style="position:absolute;z-index:2;margin:auto;height:80px;margin-left: 2%;"/>
    						<a href="CompleteProductDetails?subCategory=<%=hotProducts.get(2).getSubCategory() %>&productId=<%=hotProducts.get(2).getProductId() %>">
								<img src="IconImageFromProduct?productId=<%=hotProducts.get(2).getProductId() %>" class="img-responsive" alt="" style="height: 200px;width: 280px;"/>
							</a>
						<div class="special-info grid_1 simpleCart_shelfItem">
							<h5 style="height: 65px;"> 
								<a style="font-size: 14px;font-weight: bold;"  href="CompleteProductDetails?subCategory=<%=hotProducts.get(2).getSubCategory() %>&productId=<%=hotProducts.get(2).getProductId() %>"> <%=hotProducts.get(2).getProductName() %>  (<%=hotProducts.get(2).getCompanyName() %> )  </a> 
							</h5>
						
	                    	
	                    	<div class="item_add"><h6 style="height: 60px;"><span class="item_price"> <small class="over_flow"> Rs.<%=hotProducts.get(2).getPrice().getListPrice() %> </small> </br> <small class="item_price"> (<%=hotProducts.get(2).getPrice().getDiscount() %>% Off) </small> <br> <strong class="main_value">Rs <%=hotProducts.get(2).getPrice().getSalePriceCustomer() %></strong> </span></h6></div>
							<div class="item_add" style="height: 32px;">
								<span class="item_price">
									<a href="CompleteProductDetails?subCategory=<%=hotProducts.get(2).getSubCategory() %>&productId=<%=hotProducts.get(2).getProductId() %>">More Details</a>
								</span>
							</div>
						</div>
						</li>
						<li style="position:relative;">
    						<img src="images/hotdeal.png" style="position:absolute;z-index:2;margin:auto;height:80px;margin-left: 2%;"/>
    						<a href="CompleteProductDetails?subCategory=<%=hotProducts.get(3).getSubCategory() %>&productId=<%=hotProducts.get(3).getProductId() %>">
								<img src="IconImageFromProduct?productId=<%=hotProducts.get(3).getProductId() %>" class="img-responsive" alt="" style="height: 200px;width: 280px;"/>
							</a>
						<div class="special-info grid_1 simpleCart_shelfItem">
							<h5 style="height: 65px;"> 
								<a style="font-size: 14px;font-weight: bold;"  href="CompleteProductDetails?subCategory=<%=hotProducts.get(3).getSubCategory() %>&productId=<%=hotProducts.get(3).getProductId() %>"> <%=hotProducts.get(3).getProductName() %>  (<%=hotProducts.get(3).getCompanyName() %> )  </a> 
							</h5>
						
	                    	
	                    	<div class="item_add"><h6 style="height: 60px;"><span class="item_price"> <small class="over_flow"> Rs.<%=hotProducts.get(3).getPrice().getListPrice() %> </small> </br> <small class="item_price"> (<%=hotProducts.get(3).getPrice().getDiscount() %>% Off) </small> <br> <strong class="main_value">Rs <%=hotProducts.get(3).getPrice().getSalePriceCustomer() %></strong> </span></h6></div>
							<div class="item_add" style="height: 32px;">
								<span class="item_price">
									<a href="CompleteProductDetails?subCategory=<%=hotProducts.get(3).getSubCategory() %>&productId=<%=hotProducts.get(3).getProductId() %>">More Details</a>
								</span>
							</div>
						</div>
						</li>
						<li style="position:relative;">
    						<img src="images/hotdeal.png" style="position:absolute;z-index:2;margin:auto;height:80px;margin-left: 2%;"/>
    						<a href="CompleteProductDetails?subCategory=<%=hotProducts.get(4).getSubCategory() %>&productId=<%=hotProducts.get(4).getProductId() %>">
								<img src="IconImageFromProduct?productId=<%=hotProducts.get(4).getProductId() %>" class="img-responsive" alt="" style="height: 200px;width: 280px;"/>
							</a>
						<div class="special-info grid_1 simpleCart_shelfItem">
							<h5 style="height: 65px;"> 
								<a style="font-size: 14px;font-weight: bold;"  href="CompleteProductDetails?subCategory=<%=hotProducts.get(4).getSubCategory() %>&productId=<%=hotProducts.get(4).getProductId() %>"> <%=hotProducts.get(4).getProductName() %>  (<%=hotProducts.get(4).getCompanyName() %> )  </a> 
							</h5>
						
	                    	
	                    	<div class="item_add"><h6 style="height: 60px;"><span class="item_price"> <small class="over_flow"> Rs.<%=hotProducts.get(4).getPrice().getListPrice() %> </small> </br> <small class="item_price"> (<%=hotProducts.get(4).getPrice().getDiscount() %>% Off) </small> <br> <strong class="main_value">Rs <%=hotProducts.get(0).getPrice().getSalePriceCustomer() %></strong> </span></h6></div>
							<div class="item_add" style="height: 32px;">
								<span class="item_price">
									<a href="CompleteProductDetails?subCategory=<%=hotProducts.get(4).getSubCategory() %>&productId=<%=hotProducts.get(4).getProductId() %>">More Details</a>
								</span>
							</div>
						</div>
						</li>
						<li style="position:relative;">
    						<img src="images/hotdeal.png" style="position:absolute;z-index:2;margin:auto;height:80px;margin-left: 2%;"/>
    						<a href="CompleteProductDetails?subCategory=<%=hotProducts.get(5).getSubCategory() %>&productId=<%=hotProducts.get(5).getProductId() %>">
								<img src="IconImageFromProduct?productId=<%=hotProducts.get(5).getProductId() %>" class="img-responsive" alt="" style="height: 200px;width: 280px;"/>
							</a>
						<div class="special-info grid_1 simpleCart_shelfItem">
							<h5 style="height: 65px;"> 
								<a style="font-size: 14px;font-weight: bold;"  href="CompleteProductDetails?subCategory=<%=hotProducts.get(5).getSubCategory() %>&productId=<%=hotProducts.get(5).getProductId() %>"> <%=hotProducts.get(5).getProductName() %>  (<%=hotProducts.get(5).getCompanyName() %> )  </a> 
							</h5>
						
	                    	
	                    	<div class="item_add"><h6 style="height: 60px;"><span class="item_price"> <small class="over_flow"> Rs.<%=hotProducts.get(5).getPrice().getListPrice() %> </small> </br> <small class="item_price"> (<%=hotProducts.get(5).getPrice().getDiscount() %>% Off) </small> <br> <strong class="main_value">Rs <%=hotProducts.get(5).getPrice().getSalePriceCustomer() %></strong> </span></h6></div>
							<div class="item_add" style="height: 32px;">
								<span class="item_price">
									<a href="CompleteProductDetails?subCategory=<%=hotProducts.get(5).getSubCategory() %>&productId=<%=hotProducts.get(5).getProductId() %>">More Details</a>
								</span>
							</div>
						</div>
						</li>
   
    
  </ul>