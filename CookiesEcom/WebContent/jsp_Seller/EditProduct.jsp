<%@page import="ecom.model.Size"%>
<%@page import="ecom.model.KeyFeature"%>
<%@page import="java.util.List"%>
<%@page import="ecom.model.Product"%>
<%@page import="ecom.common.FrequentUse"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Product</title>
<script type="text/javascript" src="<%=FrequentUse.jQuery %>"></script>
<script type="text/javascript" src="js_Seller/Product_Add.js"></script>
<script type="text/javascript" src="js_Seller/CommonForEditPages.js"></script>
<script type="text/javascript" src="js_Seller/EditProductFeatures.js"></script>
<link type="text/css" href="css/bootstrap.css" rel="stylesheet">
<script type="text/javascript">

</script>
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

	Product product              = (Product) request.getAttribute("product");
	List<KeyFeature> keyFeatures = product.getKeyFeatures();                         System.out.println(keyFeatures.size());
	List<Size> sizes             = product.getSizes();   
	
	String key1 = "",   key2 = "",   key3 = "",   key4 = "",   key5 = "",   key6 = "",   key7 = "",   key8 = "";
	String keyH1 = "",  keyH2 = "",  keyH3 = "",  keyH4 = "",  keyH5 = "",  keyH6 = "",  keyH7 = "",  keyH8 = "";
	String value1 = "", value2 = "", value3 = "", value4 = "", value5 = "", value6 = "", value7 = "", value8 = "";
	
	try { // This try catch is necessary for working of this page.
	
		key1   = keyFeatures.get(0).getKey();          //  System.out.println(key1);
		value1 = keyFeatures.get(0).getValue();         // System.out.println(value1);
		keyH1  = String.valueOf(keyFeatures.get(0).getId());
		
		key2   = keyFeatures.get(1).getKey();           // System.out.println(key2);
		value2 = keyFeatures.get(1).getValue();         // System.out.println(value2);
		keyH2  = String.valueOf(keyFeatures.get(1).getId());
		
		key3   = keyFeatures.get(2).getKey();            //System.out.println(key3);
		value3 = keyFeatures.get(2).getValue();         // System.out.println(value3);
		keyH3  = String.valueOf(keyFeatures.get(2).getId());
		
		key4   = keyFeatures.get(3).getKey();           // System.out.println(key4);
		value4 = keyFeatures.get(3).getValue();         // System.out.println(value4);
		keyH4  = String.valueOf(keyFeatures.get(3).getId());
		
		key5   = keyFeatures.get(4).getKey();           // System.out.println(key5);
		value5 = keyFeatures.get(4).getValue();         // System.out.println(value5);
		keyH5  = String.valueOf(keyFeatures.get(4).getId());
		
		key6   = keyFeatures.get(5).getKey();           // System.out.println(key6);
		value6 = keyFeatures.get(5).getValue();         // System.out.println(value6);
		keyH6  = String.valueOf(keyFeatures.get(5).getId());
		
		key7   = keyFeatures.get(6).getKey();           // System.out.println(key7);
		value7 = keyFeatures.get(6).getValue();         // System.out.println(value7);
		keyH7  = String.valueOf(keyFeatures.get(6).getId());
		
		key8   = keyFeatures.get(7).getKey();           // System.out.println(key8);
		value8 = keyFeatures.get(7).getValue();         // System.out.println(value8);
		keyH8  = String.valueOf(keyFeatures.get(7).getId());
	
	} catch (IndexOutOfBoundsException e) {}
	
	
	
	String size1 = "",  size2 = "",  size3 = "",  size4 = "",  size5 = "",  size6 = "",  size7 = "",  size8 = "",  size9 = "",  size10 = "",  size11 = "",  size12 = "";
	String sizeH1 = "", sizeH2 = "", sizeH3 = "", sizeH4 = "", sizeH5 = "", sizeH6 = "", sizeH7 = "", sizeH8 = "", sizeH9 = "", sizeH10 = "", sizeH11 = "", sizeH12 = "";
	String count1 = "", count2 = "", count3 = "", count4 = "", count5 = "", count6 = "", count7 = "", count8 = "", count9 = "", count10 = "", count11 = "", count12 = "";
	
	try { // This try catch is necessary for working of this page.
	
		size1   = sizes.get(0).getSize();                       // System.out.println(size1);
		count1  = String.valueOf(sizes.get(0).getCount());     //  System.out.println(count1);
		sizeH1  = String.valueOf(sizes.get(0).getId());
		
		size2   = sizes.get(1).getSize();                      //  System.out.println(size2);
		count2  = String.valueOf(sizes.get(1).getCount());     //  System.out.println(count2);
		sizeH2  = String.valueOf(sizes.get(1).getId());
		
		size3   = sizes.get(2).getSize();                       // System.out.println(size3);
		count3  = String.valueOf(sizes.get(2).getCount());      // System.out.println(count3);
		sizeH3  = String.valueOf(sizes.get(2).getId());
		
		size4   = sizes.get(3).getSize();                      //  System.out.println(size4);
		count4  = String.valueOf(sizes.get(3).getCount());     //  System.out.println(count4);
		sizeH4  = String.valueOf(sizes.get(3).getId());
		
		size5   = sizes.get(4).getSize();                      //  System.out.println(size5);
		count5  = String.valueOf(sizes.get(4).getCount());      // System.out.println(count5);
		sizeH5  = String.valueOf(sizes.get(4).getId());
		
		size6   = sizes.get(5).getSize();                      //  System.out.println(size6);
		count6  = String.valueOf(sizes.get(5).getCount());     //  System.out.println(count6);
		sizeH6  = String.valueOf(sizes.get(5).getId());
		
		size7   = sizes.get(6).getSize();                       // System.out.println(size7);
		count7  = String.valueOf(sizes.get(6).getCount());     //  System.out.println(count7);
		sizeH7  = String.valueOf(sizes.get(6).getId());
		
		size8   = sizes.get(7).getSize();                       // System.out.println(size8);
		count8  = String.valueOf(sizes.get(7).getCount());      // System.out.println(count8);
		sizeH8  = String.valueOf(sizes.get(7).getId());
		
		size9   = sizes.get(8).getSize();                      //  System.out.println(size9);
		count9  = String.valueOf(sizes.get(8).getCount());     //  System.out.println(count9);
		sizeH9  = String.valueOf(sizes.get(8).getId());
		
		size10   = sizes.get(9).getSize();                      // System.out.println(size10);
		count10  = String.valueOf(sizes.get(9).getCount());     // System.out.println(count10);
		sizeH10  = String.valueOf(sizes.get(9).getId());
		
		size11   = sizes.get(10).getSize();                    //  System.out.println(size11);
		count11  = String.valueOf(sizes.get(10).getCount());    // System.out.println(count11);
		sizeH11  = String.valueOf(sizes.get(10).getId());
		
		size12   = sizes.get(11).getSize();                     // System.out.println(size12);
		count12  = String.valueOf(sizes.get(11).getCount());    // System.out.println(count12);
		sizeH12  = String.valueOf(sizes.get(11).getId());
	
	} catch (IndexOutOfBoundsException e) {}
%>

<section>
 
    <div class="container">    
		
		<form method="post" id="form1"> 
		
			<input type="hidden" name="productId" value="<%=product.getProductId() %>" />
			
			<h3 style="font-size: 25px; color:#337AB7;">Edit Product <span style="margin-left: 62%;font-size: 68%;color: blue;">Product Id: <%=product.getProductId() %></span></h3>  
			
			<div id="msg1" style="color:red;"></div>
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
					<label><span style="color:red;">*</span> Brand </label>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" name="companyOfTheProduct" class="form-control" required placeholder="Samsung" value="<%=product.getCompanyName() %>" />
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<label>	<span style="color:red;">*</span> Product Name </label>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" name="productName" class="form-control" required placeholder="Galaxy S4" value="<%=product.getProductName() %>" />
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
				<% if (key1 != "" && value1 != "") { %>		
					<input type="hidden" name="keyH1" value="<%=keyH1 %>" />
				<% } else { %>	
					<input type="hidden" name="keyH1" value="nil" />
				<% } %>					
					<input type="text" name="key1" class="form-control" placeholder="Key Feature 1" value = "<%=key1 %>" style="margin-bottom:2px;" />					
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" name="value1" class="form-control" placeholder="Feature" value = "<%=value1 %>" style="margin-bottom:10px;" />
				</div>	
				
				<div class="col-md-2 col-sm-6 col-xs-12  col-md-offset-2">	
				<% if (key2 != "" && value2 != "") { %>		
					<input type="hidden" name="keyH2" value="<%=keyH2 %>" />
				<% } else { %>	
					<input type="hidden" name="keyH2" value="nil" />
				<% } %>				
					<input type="text" name="key2" size="6" class="form-control" placeholder="Key Feature 2" value = "<%=key2 %>" />					
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" name="value2" class="form-control" placeholder="Feature" value="<%=value2 %>" />
				</div>			
			</div>	
					
			<div class="row">
				<div class="col-md-2 col-sm-6 col-xs-12">
				<% if (key3 != "" && value3 != "") { %>		
					<input type="hidden" name="keyH3" value="<%=keyH3 %>" />
				<% } else { %>	
					<input type="hidden" name="keyH3" value="nil" />
				<% } %>						
					<input type="text" name="key3" class="form-control" placeholder="Key Feature 3" value = "<%=key3 %>" style="margin-bottom:2px;" />				
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" name="value3" class="form-control" placeholder="Feature" value="<%=value3 %>" style="margin-bottom:10px;" />
				</div>	
				
				<div class="col-md-2 col-sm-6 col-xs-12  col-md-offset-2">	
				<% if (key4 != "" && value4 != "") { %>		
					<input type="hidden" name="keyH4" value="<%=keyH4 %>" />
				<% } else { %>	
					<input type="hidden" name="keyH4" value="nil" />
				<% } %>					
					<input type="text" name="key4" class="form-control" placeholder="Key Feature 4" value = "<%=key4 %>" />					
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" name="value4" class="form-control" placeholder="Feature" value="<%=value4 %>" />
				</div>			
			</div>	
			
			<div class="row">
				<div class="col-md-2 col-sm-6 col-xs-12">	
				<% if (key5 != "" && value5 != "") { %>		
					<input type="hidden" name="keyH5" value="<%=keyH5 %>" />
				<% } else { %>	
					<input type="hidden" name="keyH5" value="nil" />
				<% } %>					
					<input type="text" name="key5" class="form-control" placeholder="Key Feature 5" value = "<%=key5 %>" style="margin-bottom:2px;" />				
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" name="value5" class="form-control" placeholder="Feature" value="<%=value5 %>" style="margin-bottom:10px;" />
				</div>	
				
				<div class="col-md-2 col-sm-6 col-xs-12  col-md-offset-2">
				<% if (key6 != "" && value6 != "") { %>		
					<input type="hidden" name="keyH6" value="<%=keyH6 %>" />
				<% } else { %>	
					<input type="hidden" name="keyH6" value="nil" />
				<% } %>						
					<input type="text" name="key6" class="form-control" placeholder="Key Feature 6" value = "<%=key6 %>" />					
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" name="value6" class="form-control" placeholder="Feature" value="<%=value6 %>" />
				</div>			
			</div>	
			
			<div class="row">
				<div class="col-md-2 col-sm-6 col-xs-12">
				<% if (key7 != "" && value7 != "") { %>		
					<input type="hidden" name="keyH7" value="<%=keyH7 %>" />
				<% } else { %>	
					<input type="hidden" name="keyH7" value="nil" />
				<% } %>						
					<input type="text" name="key7" class="form-control" placeholder="Key Feature 7" value = "<%=key7 %>" style="margin-bottom:2px;" />				
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" name="value7" class="form-control" placeholder="Feature" value="<%=value7 %>" style="margin-bottom:10px;" />
				</div>	
				
				<div class="col-md-2 col-sm-6 col-xs-12  col-md-offset-2">	
				<% if (key8 != "" && value8 != "") { %>		
					<input type="hidden" name="keyH8" value="<%=keyH8 %>" />
				<% } else { %>	
					<input type="hidden" name="keyH8" value="nil" />
				<% } %>					
					<input type="text" name="key8" class="form-control" placeholder="Key Feature 8" value = "<%=key8 %>" />					
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" name="value8" class="form-control" placeholder="Feature" value="<%=value8 %>" />
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
				<% if (size1 != "" && count1 != "") { %>		
					<input type="hidden" name="sizeH1" value="<%=sizeH1 %>" />
				<% } else { %>	
					<input type="hidden" name="sizeH1" value="nil" />
				<% } %>						
					<input type="text" name="size1" class="form-control" placeholder="Size 1" value = "<%=size1 %>" style="margin-bottom:2px;" />				
				</div>
				<div class="col-md-2 col-sm-6 col-xs-12">
					<input type="text" name="count1" class="form-control" placeholder="Count" value="<%=count1 %>" style="margin-bottom:10px;  border: 1px solid orange;" />
				</div>	
				
				
				<div class="col-md-2 col-sm-6 col-xs-12">
				<% if (size2 != "" && count2 != "") { %>		
					<input type="hidden" name="sizeH2" value="<%=sizeH2 %>" />
				<% } else { %>	
					<input type="hidden" name="sizeH2" value="nil" />
				<% } %>						
					<input type="text" name="size2"  class="form-control" placeholder="Size 2" value = "<%=size2 %>" style="margin-bottom:2px;"/>				
				</div>
				<div class="col-md-2 col-sm-6 col-xs-12">
					<input type="text" name="count2" class="form-control" placeholder="Count" value="<%=count2 %>" style="margin-bottom:10px;  border: 1px solid orange;" />
				</div>			
			
			
				<div class="col-md-2 col-sm-6 col-xs-12">
				<% if (size3 != "" && count3 != "") { %>		
					<input type="hidden" name="sizeH3" value="<%=sizeH3 %>" />
				<% } else { %>	
					<input type="hidden" name="sizeH3" value="nil" />
				<% } %>						
					<input type="text" name="size3"  class="form-control" placeholder="Size 3" value = "<%=size3 %>" style="margin-bottom:2px;" />				
				</div>
				<div class="col-md-2 col-sm-6 col-xs-12">
					<input type="text" name="count3" class="form-control" placeholder="Count" value="<%=count3 %>" style="margin-bottom:10px;  border: 1px solid orange;" />
				</div>	
			 </div><!-- row -->
			
			<div class="row">
				<div class="col-md-2 col-sm-6 col-xs-12">	
				<% if (size4 != "" && count4 != "") { %>		
					<input type="hidden" name="sizeH4" value="<%=sizeH4 %>" />
				<% } else { %>	
					<input type="hidden" name="sizeH4" value="nil" />
				<% } %>					
					<input type="text" name="size4"  class="form-control" placeholder="Size 4" value = "<%=size4 %>" style="margin-bottom:2px;" />				
				</div>
				<div class="col-md-2 col-sm-6 col-xs-12">
					<input type="text" name="count4" class="form-control" placeholder="Count" value="<%=count4 %>" style="margin-bottom:10px;  border: 1px solid orange;" />
				</div>	
				
				
				<div class="col-md-2 col-sm-6 col-xs-12">	
				<% if (size5 != "" && count5 != "") { %>		
					<input type="hidden" name="sizeH5" value="<%=sizeH5 %>" />
				<% } else { %>	
					<input type="hidden" name="sizeH5" value="nil" />
				<% } %>					
					<input type="text" name="size5" class="form-control" placeholder="Size 5" value = "<%=size5 %>" style="margin-bottom:2px;"/>				
				</div>
				<div class="col-md-2 col-sm-6 col-xs-12">
					<input type="text" name="count5" class="form-control" placeholder="Count" value="<%=count5 %>" style="margin-bottom:10px;  border: 1px solid orange;" />
				</div>			
			
			
				<div class="col-md-2 col-sm-6 col-xs-12">	
				<% if (size6 != "" && count6 != "") { %>		
					<input type="hidden" name="sizeH6" value="<%=sizeH6 %>" />
				<% } else { %>	
					<input type="hidden" name="sizeH6" value="nil" />
				<% } %>					
					<input type="text" name="size6" class="form-control" placeholder="Size 6" value = "<%=size6 %>" style="margin-bottom:2px;" />				
				</div>
				<div class="col-md-2 col-sm-6 col-xs-12">
					<input type="text" name="count6" class="form-control" placeholder="Count" value="<%=count6 %>" style="margin-bottom:10px;  border: 1px solid orange;" />
				</div>	
			 </div><!-- row -->
			 
			 <div class="row">
				<div class="col-md-2 col-sm-6 col-xs-12">	
				<% if (size7 != "" && count7 != "") { %>		
					<input type="hidden" name="sizeH7" value="<%=sizeH7 %>" />
				<% } else { %>	
					<input type="hidden" name="sizeH7" value="nil" />
				<% } %>					
					<input type="text" name="size7" class="form-control" placeholder="Size 7" value = "<%=size7 %>" style="margin-bottom:2px;" />				
				</div>
				<div class="col-md-2 col-sm-6 col-xs-12">
					<input type="text" name="count7" class="form-control" placeholder="Count" value="<%=count7 %>" style="margin-bottom:10px;  border: 1px solid orange;" />
				</div>	
				
				
				<div class="col-md-2 col-sm-6 col-xs-12">	
				<% if (size8 != "" && count8 != "") { %>		
					<input type="hidden" name="sizeH8" value="<%=sizeH8 %>" />
				<% } else { %>	
					<input type="hidden" name="sizeH8" value="nil" />
				<% } %>					
					<input type="text" name="size8" class="form-control" placeholder="Size 8" value = "<%=size8 %>" style="margin-bottom:2px;"/>				
				</div>
				<div class="col-md-2 col-sm-6 col-xs-12">
					<input type="text" name="count8" class="form-control" placeholder="Count" value="<%=count8 %>" style="margin-bottom:10px;  border: 1px solid orange;" />
				</div>			
			
			
				<div class="col-md-2 col-sm-6 col-xs-12">	
				<% if (size9 != "" && count9 != "") { %>		
					<input type="hidden" name="sizeH9" value="<%=sizeH9 %>" />
				<% } else { %>	
					<input type="hidden" name="sizeH9" value="nil" />
				<% } %>					
					<input type="text" name="size9" class="form-control" placeholder="Size 9" value = "<%=size9 %>" style="margin-bottom:2px;" />				
				</div>
				<div class="col-md-2 col-sm-6 col-xs-12">
					<input type="text" name="count9" class="form-control" placeholder="Count" value="<%=count9 %>" style="margin-bottom:10px;  border: 1px solid orange;" />
				</div>	
			 </div><!-- row -->
			 
			 <div class="row">
				<div class="col-md-2 col-sm-6 col-xs-12">	
				<% if (size10 != "" && count10 != "") { %>		
					<input type="hidden" name="sizeH10" value="<%=sizeH10 %>" />
				<% } else { %>	
					<input type="hidden" name="sizeH10" value="nil" />
				<% } %>					
					<input type="text" name="size10" class="form-control" placeholder="Size 10" value = "<%=size10 %>" style="margin-bottom:2px;" />				
				</div>
				<div class="col-md-2 col-sm-6 col-xs-12">
					<input type="text" name="count10" class="form-control" placeholder="Count" value="<%=count10 %>" style="margin-bottom:10px;  border: 1px solid orange;" />
				</div>	
				
				
				<div class="col-md-2 col-sm-6 col-xs-12">	
				<% if (size11 != "" && count11 != "") { %>		
					<input type="hidden" name="sizeH11" value="<%=sizeH11 %>" />
				<% } else { %>	
					<input type="hidden" name="sizeH11" value="nil" />
				<% } %>					
					<input type="text" name="size11" class="form-control" placeholder="Size 11" value = "<%=size11 %>" style="margin-bottom:2px;"/>				
				</div>
				<div class="col-md-2 col-sm-6 col-xs-12">
					<input type="text" name="count11" class="form-control" placeholder="Count" value="<%=count11 %>" style="margin-bottom:10px;  border: 1px solid orange;" />
				</div>			
			
			
				<div class="col-md-2 col-sm-6 col-xs-12">	
				<% if (size12 != "" && count12 != "") { %>		
					<input type="hidden" name="sizeH12" value="<%=sizeH12 %>" />
				<% } else { %>	
					<input type="hidden" name="sizeH12" value="nil" />
				<% } %>					
					<input type="text" name="size12" class="form-control" placeholder="Size 12" value = "<%=size12 %>" style="margin-bottom:2px;" />				
				</div>
				<div class="col-md-2 col-sm-6 col-xs-12">
					<input type="text" name="count12" class="form-control" placeholder="Count" value="<%=count12 %>" style="margin-bottom:10px;  border: 1px solid orange;" />
				</div>	
			 </div><!-- row -->
			
			
			
			<hr>	<!-- ----------------------------------------------------------------------------------- -->
			
			<div class="row">
					<div class="col-md-3 col-sm-6 col-xs-12">
						<label>	<span style="color:red;">*</span> Manufacturing Cost </label>
					</div>
					<div class="col-md-3 col-sm-6 col-xs-12">
						<input type="text" name="manufacturingCost" id="manufacturingCost1" class="form-control" required value="<%=product.getPrice().getManufacturingCost() %>" />
					</div>
					
					<div class="col-md-3 col-sm-6 col-xs-12">
						<label><span style="color:red;">*</span> Profit Margin % </label>
					</div>
					<div class="col-md-3 col-sm-6 col-xs-12">
						<input type="text" name="profitMarginPercentage" id="profitMarginPercentage1" class="form-control" required value="<%=product.getPrice().getProfitMarginPercentage() %>"/>
					</div> 
				</div>		
				
				
				<div class="row">
					<div class="col-md-3 col-sm-6 col-xs-12">
						<label>	Sale Price To Admin </label>
					</div>
					<div class="col-md-3 col-sm-6 col-xs-12">
						<input type="text" name="salePriceToAdmin" id="salePriceToAdmin1" class="form-control" readonly value="<%=product.getPrice().getSalePriceToAdmin() %>" />
					</div>
					<div class="col-md-3 col-sm-6 col-xs-12">
						<label>Profit Margin </label>
					</div>
					<div class="col-md-3 col-sm-6 col-xs-12">
						<input type="text" name="profitMargin1" id="profitMargin1" class="form-control" readonly value=""/>
					</div> 
				</div>	
				
				<div class="row">
					<div class="col-md-3 col-sm-6 col-xs-12">
						<label>	Sale Price To Customer </label>
					</div>
					<div class="col-md-3 col-sm-6 col-xs-12">
						<input type="text" name="salePriceToCustomer" id="salePriceToCustomer1" class="form-control" readonly value="<%=product.getPrice().getSalePriceCustomer() %>" />
					</div>
					<div class="col-md-3 col-sm-6 col-xs-12">
						<label>Markup % </label>
					</div>
					<div class="col-md-3 col-sm-6 col-xs-12">
						<input type="text" name="markupPercentage" id="markupPercentage1" class="form-control" readonly value="<%=product.getPrice().getMarkup() %>"/>
					</div> 
				</div>	
				
				
				<div class="row">
					<div class="col-md-3 col-sm-6 col-xs-12">
						<label> <span style="color:red;">*</span> List Price </label>
					</div>
					<div class="col-md-3 col-sm-6 col-xs-12">
						<input type="text" name="listPrice" id="listPrice1" class="form-control" required value="<%=product.getPrice().getListPrice() %>" />
					</div>
					<div class="col-md-3 col-sm-6 col-xs-12">
						<label> Discount %</label>
					</div>
					<div class="col-md-3 col-sm-6 col-xs-12">
						<input type="text" name="discount" id="discount1" class="form-control" required readonly value="<%=product.getPrice().getDiscount() %>"/>
					</div>					
				</div>
			
					
			
			<hr>	<!-- ----------------------------------------------------------------------------------- -->	
			
			<div class="row">				
					<div class="col-md-3 col-sm-6 col-xs-12">
						<label><span style="color:red;">*</span> Stock </label>
					</div>
					<div class="col-md-3 col-sm-6 col-xs-12">
						<input type="text" name="stock" class="form-control" required placeholder="50" value="<%=product.getStock() %>"/>
					</div> 
					<div class="col-md-3 col-sm-6 col-xs-12">
						<label><span style="color:red;">*</span> Weight In KG</label>
					</div>
					<div class="col-md-3 col-sm-6 col-xs-12">
						<input type="text" name="weight" class="form-control" required placeholder="0.2" value="<%=product.getWeight() %>" />
					</div> 
				</div>			
							
				<div class="row">
					<div class="col-md-3 col-sm-6 col-xs-12">
						<label>	<span style="color:red;">*</span> Warranty </label>
					</div>
					<div class="col-md-3 col-sm-6 col-xs-12">
						<input type="text" name="warranty" class="form-control" required placeholder="1 year warranty" value="<%=product.getWarranty() %>"/>
					</div>
					<div class="col-md-3 col-sm-6 col-xs-12">
						<label>	<span style="color:red;">*</span> Cancellation Period In DAYS</label>
					</div>
					<div class="col-md-3 col-sm-6 col-xs-12">
						<input type="text" name="cancellationPeriod" class="form-control" required placeholder="7" value="<%=product.getCancellationAfterBooked() %>" />
					</div>				
				</div>
				
				<div class="row">
					<div class="col-md-3 col-sm-6 col-xs-12">
						<label>	<span style="color:red;">*</span> Shipping Cost </label>
					</div>
					<div class="col-md-3 col-sm-6 col-xs-12">
						<input type="text" name="shipping" class="form-control" required placeholder="100" value="<%=product.getShippingCost() %>"/>
					</div>
					<div class="col-md-3 col-sm-6 col-xs-12">
						<label>	<span style="color:red;">*</span> Delivery(No. of Days)</label>
					</div>
					<div class="col-md-3 col-sm-6 col-xs-12">
						<input type="text" name="delivery" class="form-control" required placeholder="7" value="<%=product.getDeliveryTime() %>" />
					</div>				
				</div>
							
						
			<hr>	<!-- ----------------------------------------------------------------------------------- -->
					
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="submit" value="Submit" 
						style="width: 50% !important;  padding: 7px 1px;background:linear-gradient(#54b4eb, #2fa4e7 60%, #1d9ce5);border: 1px solid #0098fe;color:#ffffff;margin-top:18px;
						margin-left: 385%; margin-bottom: 11%;" />
				</div>
			
		</form>
		
	</div><!-- container -->	
</section>
			
			
			<!-- ------------------------- Edit Images ------------------------------------------ -->
			
			<div class="row">
				<div class="col-md-4 col-sm-6 col-xs-12">
					<form method="post" enctype="multipart/form-data" id="iconImage">	
						<input type="hidden" id="img1" value="<%=product.getProductId() %>" />	
						<label>	Icon Image </label>
						<input type="file" name="iconImage" required />						
						<br>
						<input type="hidden" name="productId" value="<%=product.getProductId()%>" />
						<input type="hidden" name="sellerId" value="<%=product.getSellerId()%>" />
						<input type="submit" value="Change" />	
						<br>				
						<div id="img2" style="margin-top: 10px;">
							<img alt="image" src="IconImageFromProduct?productId=<%=product.getProductId() %>" width="70" height="70" id="showIconImage"/>
						</div>			
					</form>
				</div>
				
				<div class="col-md-4 col-sm-6 col-xs-12">
					<form method="post" enctype="multipart/form-data" id="image1">	
						<input type="hidden" id="img3" value="<%=product.getProductId() %>" />					
						<label> Image1 </label>
						<input type="file" name="image1" required /> <br>
						<input type="hidden" name="productId" value="<%=product.getProductId()%>" />
						<input type="hidden" name="sellerId" value="<%=product.getSellerId()%>" />
						<input type="submit" value="Change" />
						<br>					
						<div id="img4" style="margin-top: 10px;">
							<img alt="image" src="Image1FromProduct?productId=<%=product.getProductId() %>" width="70" height="70" id="showImage1"/>
						</div>			
					</form>
				</div>
			
				<div class="col-md-4 col-sm-6 col-xs-12">
					<form method="post" enctype="multipart/form-data" id="image2">	
						<input type="hidden" id="img5" value="<%=product.getProductId() %>" />		
						<label>Image2</label>
						<input type="file" name="image2" required /> <br>
						<input type="hidden" name="productId" value="<%=product.getProductId()%>" />
						<input type="hidden" name="sellerId" value="<%=product.getSellerId()%>" />
						<input type="submit" value="Change" />		 
						<br>		
						<div id="img6" style="margin-top: 10px;">
							<img alt="image" src="Image2FromProduct?productId=<%=product.getProductId() %>" width="70" height="70" id="showImage2"/>
						</div>				
					</form>
				</div>
			
			</div>
			
		


</body>
</html>