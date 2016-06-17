<%@page import="ecom.common.FrequentUse"%>
<%@page import="ecom.model.WholeSaleOffer"%>
<%@page import="ecom.model.Product"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Product Advance Features</title>
<link type="text/css" href="css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="<%=FrequentUse.jQuery %>"></script>
<script type="text/javascript" src="js_Seller/EditProductFeatures.js"></script>
<style type="text/css">

.row {
    margin-left:0px !important;
    margin-right:0px !important;
    margin-bottom: 16px;
}
label{
font-weight: normal !important; 
color:#337AB7;
}
hr {
    margin-top: 20px;
    margin-bottom: 20px;
    border: 0;
    border-top: 1px solid #eee;
}
</style>
</head>
<body>

<%
	Product product = (Product) request.getAttribute("product");
	WholeSaleOffer wholeSaleOffer = (WholeSaleOffer) request.getAttribute("wholeSaleOffer");
	String searchKeyword = (String) request.getAttribute("searchKeyword");
%>

<hr>

	<!-- -------------------------------- Product Description --------------------------------------- -->			
			
			<h3 style="font-size: 25px; color:#337AB7;">
				Advance Features 
				<span style="margin-left: 62%;font-size: 68%;color: blue;">
					Product Id: <%=product.getProductId() %>
				</span>
			</h3>  			
			<hr>
			<!-- -------------------------------------------------- -->
			<div class="row">					
					<div class="col-md-3 col-sm-6 col-xs-12">
						<label>	Category </label>
					</div>
					<div class="col-md-3 col-sm-6 col-xs-12">
						<input type="text" name="category" readonly class="form-control" value="<%=product.getCategory() %>" />
					</div>						
					<div class="col-md-3 col-sm-6 col-xs-12">
						<label>	Sub Category </label>
					</div>
					<div class="col-md-3 col-sm-6 col-xs-12">
						<input type="text" name="subCategory" class="form-control" readonly value="<%=product.getSubCategory() %>" />
					</div>
				</div>
			<!-- -------------------------------------------------- -->
			<div class="row">
				<div class="col-md-3 col-sm-6 col-xs-12">
					<label> Company Name </label>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" name="companyOfTheProduct" class="form-control" readonly="readonly"  value="<%=product.getCompanyName() %>" />
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<label>	 Product Name </label>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" name="productName" class="form-control" readonly="readonly" value="<%=product.getProductName() %>" />
				</div>
			</div>	
			
	<!-- -------------------------------- End Product Description --------------------------------------- -->
			
	<hr>
	
	<!-- -------------------------------- Wholesale Offer -------------------------------------------- -->
	
	<form method="post" id="form2"> 
		
			<input type="hidden" name="productId" value="<%=product.getProductId() %>" />
			
			<h3 style="font-size: 25px; color:#337AB7;text-align:center;">Wholesale Offer </h3>  
			
			<div id="wholeSaleMessage" style="color:red;"></div>
			<hr>
					<!-- -------------------------------------------------- -->
					<div class="row">					
						<div class="col-md-3 col-sm-6 col-xs-12">
							<label>	<span style="color:red;">*</span> Quantity </label>
						</div>
						<div class="col-md-3 col-sm-6 col-xs-12">
						<% if (wholeSaleOffer != null) { %>
							<input type="text" name="quantity" required class="form-control" value="<%=wholeSaleOffer.getQty() %>" />
						<% } else { %>
							<input type="text" name="quantity" required class="form-control"  value="" />
						<% } %>
						</div>						
						<div class="col-md-3 col-sm-6 col-xs-12">
							<label>	<span style="color:red;">*</span> Discount% </label>
						</div>
						<div class="col-md-3 col-sm-6 col-xs-12">
							<% if (wholeSaleOffer != null) { %>
							<input type="text" name="discount" required class="form-control" value="<%=wholeSaleOffer.getDiscount() %>" />
						<% } else { %>
							<input type="text" name="discount" required class="form-control"  value="" />
						<% } %>
						</div>
					</div>
			
			
					<!-- ---------------------- Submit ---------------------------- -->
			
			<div class="row">
					<div class="col-md-3 col-sm-6 col-xs-12">
						<input type="submit" value="Submit" 
							style="width: 50% !important;  padding: 7px 1px;
							background:linear-gradient(#54b4eb, #2fa4e7 60%, #1d9ce5);
							border: 1px solid #0098fe;color:#ffffff;margin-top:18px;
							margin-left: 385%; " />
					</div>
					</div>
					
			<div class="col-md-12">
			<hr>
			</div>	
					<!-- ---------------------- End Submit ---------------------------- -->
	</form>
	
	<!-- -------------------------------- End Wholesale Offer -------------------------------------------- -->
	
	<hr>
	
	<!-- --------------------------------  Search Key -------------------------------------------- -->
	
	<form method="post" id="form3"> 
		
			<input type="hidden" name="productId" value="<%=product.getProductId() %>" />
			
			<h3 style="font-size: 25px; color:#337AB7;text-align:center;">Product Search Key Word for easy searching </h3>  
			
			<div id="searchKeywordMessage" style="color:red;"></div>
			<hr>
					<!-- -------------------------------------------------- -->
					<div class="row">					
						<div class="col-md-3 col-sm-6 col-xs-12">
							<label>	 Product Search Key </label>
						</div>
						<div class="col-md-3 col-sm-6 col-xs-12">
						<% if (searchKeyword != null) { %>
							<input type="text" name=searchKeyword required class="form-control" value="<%=searchKeyword %>" />
						<% } else { %>
							<input type="text" name="searchKeyword" required class="form-control"  value="" />
						<% } %>
						</div>						
						<div class="col-md-3 col-sm-6 col-xs-12">
							<label>	Demo </label>
						</div>
						<div class="col-md-3 col-sm-6 col-xs-12">							
							<input type="text" name=""  class="form-control"  value="" />						
						</div>
					</div>
			
			
					<!-- ---------------------- Submit ---------------------------- -->
			
			
					<div class="col-md-3 col-sm-6 col-xs-12">
						<input type="submit" value="Submit" 
							style="width: 50% !important;  padding: 7px 1px;
							background:linear-gradient(#54b4eb, #2fa4e7 60%, #1d9ce5);
							border: 1px solid #0098fe;color:#ffffff;margin-top:18px;
							margin-left: 385%; " />
					</div>
					
			<div class="col-md-12">
			<hr>
			</div>	
					<!-- ---------------------- End Submit ---------------------------- -->
	</form>
	
	<!-- --------------------------------  End Search Key -------------------------------------------- -->
	
	
	
	
	
	

</body>
</html>