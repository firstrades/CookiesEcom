<%@page import="ecom.SERVLET.seller.SellerServlet"%>
<%@page import="ecom.common.FrequentUse"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="ecom.model.*" %>
<!DOCTYPE html>
<html data-ng-app="Jewel">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>View Product List</title>
	<script type="text/javascript" src="<%=FrequentUse.jQuery %>"></script>
	<script type="text/javascript" src="<%=FrequentUse.angular %>"></script>
	<script type="text/javascript" src="js_Seller/ViewProductList_Angular.js"></script>
	<script type="text/javascript" src="js_Seller/ViewProductList_Jquery.js"></script>
	<link type="text/css" href="css/bootstrap.css" rel="stylesheet">
	<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<style type="text/css">

.row{
    margin-left:0px !important;
    margin-right:0px !important;
    margin-bottom: 16px;
}
label{
font-weight: normal !important; 
color:#337AB7;
}
hr {
 
    border-top: 1px solid #eee;
    margin-top:4px;
    margin-bottom: 4px;
}
.key_feature{
padding: 0px;
margin: 0px;
}
.key_feature li{
    font-size: 11px;
    color: #777;
    margin-left: 14px;


}
.clear_fix{
clear: both;
}
</style>
</head>
<body id="top">
 
<%

	final int MAX = (Integer) request.getAttribute("MAX");
	session.setAttribute("MAX", MAX);
	
	/*******************************************************
					*  Get Session  *
	*******************************************************/
	@SuppressWarnings("unchecked")
	List<Product> productList = (List<Product>) session.getAttribute("productList");   
	//System.out.println(productList);
%>


	<h3 style="font-size: 25px; color:#337AB7; margin-left: 20px; float: left;">View All Product</h3>  
	
	<!-- Search Box -->
	<ul class="nav" id="side-menu" style="width: 15%; float: right; margin-top:18px;">
    	<li class="sidebar-search">
	        <div class="input-group custom-search-form">
	            <input type="text" class="form-control" placeholder="Product Id..." data-ng-model="productId" id="searchValue">
	            <span class="input-group-btn">
	          	<a class="btn btn-default" data-ng-href="{{'#'+productId}}" id="search">
	                <i class="fa fa-search"></i>
	                <script type="text/javascript">
	                	$('#search').click(function() {
	                		$('#searchValue').val('');
	                	});	                	
	                </script>
	            </a> 
	        </span>
	        </div>                           
    	</li>
    </ul>
    <!-- Search Box End -->
  
    <div class="clear_fix"></div>
	<hr>
	
<!-- ------------------------------LOOP STARTS--------------------------------------- -->

<section data-ng-controller="ViewController">

<%  
	for (int i = 0; i < MAX; i++) {					
		
%>

		<div class="row" id="<%=productList.get(i).getProductId() %>"> 
			<div class="col-md-12" style="width:100%;border:1px solid #ddd;margin-bottom:-12px;box-shadow: 1px 1px 1px #f5f5f5;background-color: #FFFFF5;">
				<div class="col-md-2" style="border: 1px solid #EAEAEA; box-shadow: 1px 1px 1px #e7e7e7; margin-top:10px; margin-bottom: 11px;">
					<img alt="image" src="IconImageFromProduct?productId=<%=productList.get(i).getProductId() %>" width="150" height="150" />
				</div>
				<div class="col-md-6">
					<div class="col-md-6">
						<h3 style="margin-bottom: 7px;margin-top: 8px;color: #337ab7;"><%=productList.get(i).getProductName() %></h3> <hr>
						<span>  Product Id : <%=productList.get(i).getProductId() %>  </span>  <hr>
						<span style=""><%=productList.get(i).getCompanyName() %></span>					
						<hr>					
						<span style="margin-right:15px;">List Price: <%=productList.get(i).getPrice().getListPrice() %></span><hr>
						<span style="font-size: 13px; color: #999;">Discount: <%=productList.get(i).getPrice().getDiscount() %>%</span><hr>
						<span style="font-size: 20px; color: #EF413C;">Price: <%=productList.get(i).getPrice().getSalePriceToAdmin() %></span>
						<span style="font-size: 20px; color: blue;margin-left: 34px;">Markup: <%=productList.get(i).getPrice().getMarkup() %>%</span>
					</div>					
					<%-- <div class="col-md-6" style="margin-top:40px;">
						<span style="font-size: 18px;color: #4FAA4F;">Key Features</span> <hr>
						<ul class="key_feature">
							<li><%=productList.get(i).getKeyFeatures().getKf1() %></li>
							<li><%=productList.get(i).getKeyFeatures().getKf2() %></li>
							<li><%=productList.get(i).getKeyFeatures().getKf3() %></li>
							<li><%=productList.get(i).getKeyFeatures().getKf4() %></li>
						</ul>
					</div>	 --%>				
				</div>
				<!-- ---------------------------------------------------------- -->	
				<div class="col-md-4" style="margin-top:100px;">
				<div class="row">
				<div class="col-md-6">
					<span style="margin-top: 9px;">
						<a href="EditProduct?productId=<%=productList.get(i).getProductId()%>" 
							style="width: 50% !important;  padding:5px;background:linear-gradient(#54b4eb, #2fa4e7 60%, #1d9ce5);
							border: 1px solid #0098fe;color:#ffffff;">Edit Product</a>
					</span>	
					</div>
					<div class="col-md-6">
					<span style="margin-top: 9px;">
						<a href="ProductAdvanceFeatures?productId=<%=productList.get(i).getProductId()%>" 
							style="width: 50% !important;  padding: 5px;background:linear-gradient(#54b4eb, #2fa4e7 60%, #1d9ce5);
							border: 1px solid #0098fe;color:#ffffff;">Advance Features</a>
					</span>	
					</div>				
					<input type="hidden" class="productId"   value="<%=productList.get(i).getProductId() %>"/>
					<input type="hidden" class="category"    value="<%=productList.get(i).getCategory() %>"/>
					<input type="hidden" class="subCategory" value="<%=productList.get(i).getSubCategory() %>"/>
					<div class="col-md-4">				
					<span>		
						<input type="submit" value="Delete" class="delete" onclick="deleteProduct(this)"
							style="width: 90% !important;
							    padding: 5px 10px;
							    background: linear-gradient(#5cb85c, #429C42 60%, #4D9C4D);
							    border: 1px solid #53A253;
							    color: #ffffff;
							    float: left;
							    margin-top: 52px;margin-left: 94px;"/>
					</span>	
					</div>                                                              
				<!-- ---------------------------------------------------------- -->						
					<br>
					<% if (i > 2) { %>
					<span style="float: right;padding-top: 37px;">  <a href="#top">Top</a> </span>
					<% } %>
				</div>
				</div>
			</div>
		</div>

<%  } %>

		<!-- --------------------------Angular-------------------------------------------------- -->
		
		<div class="row" data-ng-repeat="item in items" id="{{item.productId}}"  data-ng-remove-item> 
			<div class="col-md-12" style="width:100%;border:1px solid #ddd;margin-bottom:-12px;box-shadow: 1px 1px 1px #f5f5f5;background-color: #FFFFF5;">
				<div class="col-md-2" style="border: 1px solid #EAEAEA; box-shadow: 1px 1px 1px #e7e7e7; margin-top:10px; margin-bottom: 11px;">
					<img alt="image" src="IconImageFromProduct?productId={{item.productId}}" width="150" height="150" />
				</div>
				<div class="col-md-8">
					<div class="col-md-6">
						<h3 style="margin-bottom: 7px;margin-top: 8px;color: #337ab7;">{{item.productName}}</h3> <hr>
						<span>  Product Id : {{item.productId}}  </span>  <hr>
						<span style="">{{item.companyName}}</span>					
						<hr>					
						<span style="margin-right:15px;">List Price: {{item.listPrice}}</span><hr>
						<span style="font-size: 13px; color: #999;">Discount: {{item.discount}}%</span><hr>
						<span style="font-size: 20px; color: #EF413C;">Price: {{item.salePrice}}</span>
						<span style="font-size: 20px; color: blue;margin-left: 34px;">Markup: {{item.markup}}%</span>
					</div>					
					<!-- <div class="col-md-6" style="margin-top:40px;">
						<span style="font-size: 18px;color: #4FAA4F;">Key Features</span> <hr>
						<ul class="key_feature">
							<li>{{item.kf1}}</li>
							<li>{{item.kf2}}</li>
							<li>{{item.kf3}}</li>
							<li>{{item.kf4}}</li>
						</ul>
					</div>	 -->				
				</div>
				<!-- -------------------------------------------------------------- -->
				<div class="col-md-2" style="margin-top:100px;">
					<span style="float: left;margin-top: 9px;">
						<a href="EditProduct?productId={{item.productId}}" 
							style="width: 50% !important;  padding: 7px 14px;background:linear-gradient(#54b4eb, #2fa4e7 60%, #1d9ce5);
							border: 1px solid #0098fe;color:#ffffff;">Edit</a>
					</span>					
					<input type="hidden" class="productId"   value="{{item.productId}}"/>
					<input type="hidden" class="category"    value="{{item.category}}"/>
					<input type="hidden" class="subCategory" value="{{item.subCategory}}"/>				
					<span>		
						<input type="submit" value="Delete" class="delete" data-ng-click="deleteProduct()"
							style="width: 36% !important;
							    padding: 5px 10px;
							    background: linear-gradient(#5cb85c, #429C42 60%, #4D9C4D);
							    border: 1px solid #53A253;
							    color: #ffffff;
							    float: left;
							    margin-top: 3px;
							    margin-left: 6px;"/>
					</span>	                                                                
				<!-- -------------------------------------------------------------- -->					
					<br>
					<span style="float: right;padding-top: 37px;">  <a href="#top">Top</a> </span>
				</div>
			</div>
		</div>
		
		<!-- ----------------------------------------------------------------------------------- -->

</section>


</body>
</html>