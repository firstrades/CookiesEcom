<%@page import="ecom.common.FrequentUse"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html data-ng-app="ProductAdd">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Product</title>
<script type="text/javascript" src="<%=FrequentUse.jQuery %>"></script>
<script type="text/javascript" src="<%=FrequentUse.angular %>"></script> 
<script type="text/javascript" src="js_Seller/Product_Add.js"></script>
<link type="text/css" href="css/bootstrap.css" rel="stylesheet">

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

<body data-ng-controller="ProductController">
<section>
 
    <div class="container">
		<form method="post" enctype="multipart/form-data" id="data"> 
		
			<h3 style="font-size: 25px; color:#337AB7;">Add New Product</h3>  
			<div id="msg" style="color:red;"></div>
			<hr>
			<!-- -------------------------------------------------- -->
			<div class="row">
				<div class="col-md-3 col-sm-6 col-xs-12">
					<label><span> <span style="color:red;">*</span> Category </span></label>
			   	</div>
			   	<div class="col-md-3 col-sm-6 col-xs-12">
			   		<select name="category" id="category" class="form-control" data-ng-change="getSubCategories()" data-ng-model="category2"
			  			data-ng-options="item.category for item in categoryItems track by item.category">			  							  			
			  			<option value="">--------- category ---------</option>
			  		</select>
					<!-- <select name="category" id="category" class="form-control">
						<option value="null">---category---</option>
						<option value="ELECTRONICS">ELECTRONICS</option>
						<option value="MEN">MEN</option>
						<option value="WOMEN">WOMEN</option>
						<option value="KIDS">KIDS</option>
						<option value="HomeAndKitchen">HomeAndKitchen</option>
						<option value="FoodAndGrocery">Food&Grocery</option>
						<option value="Herbal">Herbal</option>
					</select> -->
				</div>				
				<div class="col-md-3 col-sm-6 col-xs-12">
					<label> <span style="color:red;">*</span> Sub Category </label>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<select data-ng-model="deleteSubCategory" name="subCategory" id="subCategory" class="form-control"
			  			data-ng-options="item.subSategory for item in subCategoryItems track by item.subSategory">
			  			<option value="">--------- sub category ---------</option>				  			
			  		</select>
					<!-- <select name="subCategory" id="subCategory" class="form-control">
						<option value="null">--sub category--</option>								
					</select> -->						
				</div>
			</div>
			<!-- -------------------------------------------------- -->
			<div class="row">
				<div class="col-md-3 col-sm-6 col-xs-12">
					<label><span style="color:red;">*</span> Brand </label>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" name="company" class="form-control" required placeholder="Samsung" value="" />
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<label>	<span style="color:red;">*</span> Product Name </label>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" name="product" class="form-control" required placeholder="Galaxy S4" value="" />
				</div>
			</div>	
			
			<hr>	<!-- ------------------------------ Key Features ---------------------------------------------- -->
			
			 <div class="row">		
			<div class="col-md-12 col-sm-12 col-xs-12">			
			<div style="font-size: 149%; margin-bottom: 2%; color: #121277; text-align:center;">Key Features</div>			
			 </div>		
		 </div><!-- row -->	
		 				
			<div class="row">
				<div class="col-md-2 col-sm-6 col-xs-12">					
					<input type="text" name="key1" class="form-control" placeholder="Key Feature 1" value = "" style="margin-bottom:2px;" />				
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" name="value1" class="form-control" placeholder="Feature" value="" style="margin-bottom:10px;" />
				</div>	
				
				<div class="col-md-2 col-sm-6 col-xs-12  col-md-offset-2">					
					<input type="text" name="key2" size="6" class="form-control" placeholder="Key Feature 2" value = "" />					
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" name="value2" class="form-control" placeholder="Feature" value="" />
				</div>			
			</div>	
					
			<div class="row">
				<div class="col-md-2 col-sm-6 col-xs-12">					
					<input type="text" name="key3" class="form-control" placeholder="Key Feature 3" value = "" style="margin-bottom:2px;" />				
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" name="value3" class="form-control" placeholder="Feature" value="" style="margin-bottom:10px;" />
				</div>	
				
				<div class="col-md-2 col-sm-6 col-xs-12  col-md-offset-2">					
					<input type="text" name="key4" class="form-control" placeholder="Key Feature 4" value = "" />					
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" name="value4" class="form-control" placeholder="Feature" value="" />
				</div>			
			</div>	
			
			<div class="row">
				<div class="col-md-2 col-sm-6 col-xs-12">					
					<input type="text" name="key5" class="form-control" placeholder="Key Feature 5" value = "" style="margin-bottom:2px;" />				
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" name="value5" class="form-control" placeholder="Feature" value="" style="margin-bottom:10px;" />
				</div>	
				
				<div class="col-md-2 col-sm-6 col-xs-12  col-md-offset-2">					
					<input type="text" name="key6" class="form-control" placeholder="Key Feature 6" value = "" />					
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" name="value6" class="form-control" placeholder="Feature" value="" />
				</div>			
			</div>	
			
			<div class="row">
				<div class="col-md-2 col-sm-6 col-xs-12">					
					<input type="text" name="key7" class="form-control" placeholder="Key Feature 7" value = "" style="margin-bottom:2px;" />				
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" name="value7" class="form-control" placeholder="Feature" value="" style="margin-bottom:10px;" />
				</div>	
				
				<div class="col-md-2 col-sm-6 col-xs-12  col-md-offset-2">					
					<input type="text" name="key8" class="form-control" placeholder="Key Feature 8" value = "" />					
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" name="value8" class="form-control" placeholder="Feature" value="" />
				</div>			
			</div>	
			
			<hr>	<!-- ------------------------------  Sizes ----------------------------------------------------- -->
		  <div class="row">		
			<div class="col-md-12 col-sm-12 col-xs-12">			
			<div style="font-size: 149%; margin-bottom: 2%; color: #121277; text-align:center;">Sizes</div>			
			 </div>		
		 </div><!-- row -->	
		 					
			<div class="row">
				<div class="col-md-2 col-sm-6 col-xs-12">					
					<input type="text" name="size1" class="form-control" placeholder="Size 1" value = "" style="margin-bottom:2px;" />				
				</div>
				<div class="col-md-2 col-sm-6 col-xs-12">
					<input type="text" name="count1" class="form-control" placeholder="Count" value="" style="margin-bottom:10px;  border: 1px solid orange;" />
				</div>	
				
				
				<div class="col-md-2 col-sm-6 col-xs-12">					
					<input type="text" name="size2"  class="form-control" placeholder="Size 2" value = "" style="margin-bottom:2px;"/>				
				</div>
				<div class="col-md-2 col-sm-6 col-xs-12">
					<input type="text" name="count2" class="form-control" placeholder="Count" value="" style="margin-bottom:10px;  border: 1px solid orange;" />
				</div>			
			
			
				<div class="col-md-2 col-sm-6 col-xs-12">					
					<input type="text" name="size3"  class="form-control" placeholder="Size 3" value = "" style="margin-bottom:2px;" />				
				</div>
				<div class="col-md-2 col-sm-6 col-xs-12">
					<input type="text" name="count3" class="form-control" placeholder="Count" value="" style="margin-bottom:10px;  border: 1px solid orange;" />
				</div>	
			 </div><!-- row -->
			
			<div class="row">
				<div class="col-md-2 col-sm-6 col-xs-12">					
					<input type="text" name="size4"  class="form-control" placeholder="Size 4" value = "" style="margin-bottom:2px;" />				
				</div>
				<div class="col-md-2 col-sm-6 col-xs-12">
					<input type="text" name="count4" class="form-control" placeholder="Count" value="" style="margin-bottom:10px;  border: 1px solid orange;" />
				</div>	
				
				
				<div class="col-md-2 col-sm-6 col-xs-12">					
					<input type="text" name="size5" class="form-control" placeholder="Size 5" value = "" style="margin-bottom:2px;"/>				
				</div>
				<div class="col-md-2 col-sm-6 col-xs-12">
					<input type="text" name="count5" class="form-control" placeholder="Count" value="" style="margin-bottom:10px;  border: 1px solid orange;" />
				</div>			
			
			
				<div class="col-md-2 col-sm-6 col-xs-12">					
					<input type="text" name="size6" class="form-control" placeholder="Size 6" value = "" style="margin-bottom:2px;" />				
				</div>
				<div class="col-md-2 col-sm-6 col-xs-12">
					<input type="text" name="count6" class="form-control" placeholder="Count" value="" style="margin-bottom:10px;  border: 1px solid orange;" />
				</div>	
			 </div><!-- row -->
			 
			 <div class="row">
				<div class="col-md-2 col-sm-6 col-xs-12">					
					<input type="text" name="size7" class="form-control" placeholder="Size 7" value = "" style="margin-bottom:2px;" />				
				</div>
				<div class="col-md-2 col-sm-6 col-xs-12">
					<input type="text" name="count7" class="form-control" placeholder="Count" value="" style="margin-bottom:10px;  border: 1px solid orange;" />
				</div>	
				
				
				<div class="col-md-2 col-sm-6 col-xs-12">					
					<input type="text" name="size8" class="form-control" placeholder="Size 8" value = "" style="margin-bottom:2px;"/>				
				</div>
				<div class="col-md-2 col-sm-6 col-xs-12">
					<input type="text" name="count8" class="form-control" placeholder="Count" value="" style="margin-bottom:10px;  border: 1px solid orange;" />
				</div>			
			
			
				<div class="col-md-2 col-sm-6 col-xs-12">					
					<input type="text" name="size9" class="form-control" placeholder="Size 9" value = "" style="margin-bottom:2px;" />				
				</div>
				<div class="col-md-2 col-sm-6 col-xs-12">
					<input type="text" name="count9" class="form-control" placeholder="Count" value="" style="margin-bottom:10px;  border: 1px solid orange;" />
				</div>	
			 </div><!-- row -->
			 
			 <div class="row">
				<div class="col-md-2 col-sm-6 col-xs-12">					
					<input type="text" name="size10" class="form-control" placeholder="Size 10" value = "" style="margin-bottom:2px;" />				
				</div>
				<div class="col-md-2 col-sm-6 col-xs-12">
					<input type="text" name="count10" class="form-control" placeholder="Count" value="" style="margin-bottom:10px;  border: 1px solid orange;" />
				</div>	
				
				
				<div class="col-md-2 col-sm-6 col-xs-12">					
					<input type="text" name="size11" class="form-control" placeholder="Size 11" value = "" style="margin-bottom:2px;"/>				
				</div>
				<div class="col-md-2 col-sm-6 col-xs-12">
					<input type="text" name="count11" class="form-control" placeholder="Count" value="" style="margin-bottom:10px;  border: 1px solid orange;" />
				</div>			
			
			
				<div class="col-md-2 col-sm-6 col-xs-12">					
					<input type="text" name="size12" class="form-control" placeholder="Size 12" value = "" style="margin-bottom:2px;" />				
				</div>
				<div class="col-md-2 col-sm-6 col-xs-12">
					<input type="text" name="count12" class="form-control" placeholder="Count" value="" style="margin-bottom:10px;  border: 1px solid orange;" />
				</div>	
			 </div><!-- row -->
			
			
			
			<hr>	<!-- ----------------------------------------------------------------------------------- -->
			
			<div class="row">
				<div class="col-md-3 col-sm-6 col-xs-12">
					<label>	<span style="color:red;">*</span> Manufacturing Cost </label>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" name="manufacturingCost" id="manufacturingCost" class="form-control" required value="" />
				</div>
				
				<div class="col-md-3 col-sm-6 col-xs-12">
					<label><span style="color:red;">*</span> Profit Margin % </label>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" name="profitMarginPercentage" id="profitMarginPercentage" class="form-control" required value=""/>
				</div> 
			</div>		
			
			
			<div class="row">
				<div class="col-md-3 col-sm-6 col-xs-12">
					<label>	Sale Price To Admin </label>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" name="salePriceToAdmin" id="salePriceToAdmin" class="form-control" readonly value="" />
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<label>Profit Margin </label>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" name="profitMargin" id="profitMargin" class="form-control" readonly value=""/>
				</div> 
			</div>	
			
			
			<div class="row">
				<div class="col-md-3 col-sm-6 col-xs-12">
					<label> <span style="color:red;">*</span> List Price </label>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" name="listPrice" id="ListPrice" class="form-control" required value="" />
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<label> Discount %</label>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" name="discount" id="discount" class="form-control" required readonly value=""/>
				</div>					
			</div>
			
					
			
			<hr>	<!-- ----------------------------------------------------------------------------------- -->	
			
			<div class="row">				
				<div class="col-md-3 col-sm-6 col-xs-12">
					<label><span style="color:red;">*</span> Stock </label>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" name="stock" class="form-control" required placeholder="50" value=""/>
				</div> 
				<div class="col-md-3 col-sm-6 col-xs-12">
					<label><span style="color:red;">*</span> Weight In KG</label>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" name="weight" class="form-control" required placeholder="0.2" value="" />
				</div> 
			</div>			
						
			<div class="row">
				<div class="col-md-3 col-sm-6 col-xs-12">
					<label>	<span style="color:red;">*</span> Warranty </label>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" name="warranty" class="form-control" required placeholder="1 year warranty is applicable" value="1 year warranty is applicable"/>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<label>	<span style="color:red;">*</span> Cancellation Period In DAYS</label>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" name="cancellationPeriod" class="form-control" required placeholder="7" value="7" />
				</div>				
			</div>
			
			<div class="row">
				<div class="col-md-3 col-sm-6 col-xs-12">
					<label>	<span style="color:red;">*</span> Shipping Cost</label>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" name="shipping" class="form-control" required placeholder="100" value="100"/>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<label>	<span style="color:red;">*</span> Delivery(No. of Days)</label>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" name="delivery" class="form-control" required placeholder="7" value="7" />
				</div>				
			</div>
							
						
			<hr>	<!-- ----------------------------------------------------------------------------------- -->
					
			<div class="row">
				<div class="col-md-3 col-sm-6 col-xs-12">
					<label><span style="color:red;">*</span> Icon Image </label>				
					<input type="file" name="iconImage" required />
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<label> <span style="color:red;">*</span> Image 1 </label>
					<input type="file" name="image1" required />
				</div>			
				<div class="col-md-3 col-sm-6 col-xs-12">
					<label><span style="color:red;">*</span> Image 2 </label>					
					<input type="file" name="image2" required />
				</div>				
					
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="submit" value="Submit" 
						style="width: 50% !important;  padding: 7px 1px;background:linear-gradient(#54b4eb, #2fa4e7 60%, #1d9ce5);border: 1px solid #0098fe;color:#ffffff;margin-top:18px;" />
				</div>
			</div>			
			
		</form>
		
	</div><!-- container -->	
	</section>
</body>
</html>

