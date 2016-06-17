<%@page import="ecom.common.FrequentUse"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html lang="en" data-ng-app="admin">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Admin Panel</title>
    
	    <!-- jQuery -->    
	    <script type="text/javascript" src="<%=FrequentUse.jQuery %>"></script>	
	    <script type="text/javascript" src="<%=FrequentUse.angular %>"></script>    
	    <script type="text/javascript" src="js_Administration/Admin.js"></script>
	    
	    <link rel="stylesheet" type="text/css" href="<%=FrequentUse.style %>">
	    
	    <!-- Bootstrap Core JavaScript -->
	    <script src="js/bootstrap.min.js"></script>	
	    <!-- Metis Menu Plugin JavaScript -->
	    <script src="js/metisMenu.min.js"></script>	
	    <!-- Custom Theme JavaScript -->
	    <script src="js/sb-admin-2.js"></script>	    

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- MetisMenu CSS -->
    <link href="css/metisMenu.min.css" rel="stylesheet">
    <!-- Timeline CSS -->
    <link href="css/timeline.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="css/sb-admin-2.css" rel="stylesheet">
    <!-- Morris Charts CSS -->
    <link href="css/morris.css" rel="stylesheet">
    <!-- Custom Fonts -->
    <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

<style type="text/css">
.container{
    width: 98%;
    border: 1px solid #ccc;
    float: left;
    margin-left: 1%;
    background-color: #FFF2F2;
    padding-left: 0px;
    padding-right: 0px;
    margin-bottom: 5px;
}
.dots{
float: left;
margin-right: 24px;
    text-transform: uppercase;
}
#hr{margin-top:9px;margin-bottom:9px;border:0;border-top:1px solid #eee}
</style>

<style>
a.tooltip {
    outline: none;
    text-decoration: none;    
    position: relative;
    opacity: 1;
}

a.tooltip strong {
    line-height: 30px;
}

a.tooltip > span {
    width: 300px;
    padding: 10px 20px;
    margin-top: 0;
    margin-left: -120px;
    opacity: 0;
    visibility: hidden;
    z-index: 10;
    position: absolute;
    font-family: Arial;
    font-size: 12px;
    font-style: normal;
    border-radius: 3px;
    box-shadow: 2px 2px 2px #999;
    -webkit-transition-property: opacity, margin-top, visibility, margin-left;
    -webkit-transition-duration: 0.4s, 0.3s, 0.4s, 0.3s;
    -webkit-transition-timing-function: ease-in-out, ease-in-out, ease-in-out, ease-in-out;
    transition-property: opacity, margin-top, visibility, margin-left;
    transition-duration: 0.4s, 0.3s, 0.4s, 0.3s;
    transition-timing-function: 
        ease-in-out, ease-in-out, ease-in-out, ease-in-out;
}


a.tooltip:hover > span {
    opacity: 1;
    text-decoration: none;
    visibility: visible;
    overflow: visible;
    margin-top: 50px;
    display: inline;       
    margin-left: -90px;
}

a.tooltip span b {
    width: 15px;
    height: 15px;
    margin-left: 40px;
    margin-top: -19px;
    display: block;
    position: absolute;
    -webkit-transform: rotate(-45deg);
    -moz-transform: rotate(-45deg);
    -o-transform: rotate(-45deg);
    transform: rotate(-45deg);
    -webkit-box-shadow: inset -1px 1px 0 #fff;
    -moz-box-shadow: inset 0 1px 0 #fff;
    -o-box-shadow: inset 0 1px 0 #fff;
    box-shadow: inset 0 1px 0 #fff;
    display: none\0/;
    *display: none;
}    

a.tooltip > span {
	color: #000000; 
	background: #FBF5E6;
	background: -webkit-linear-gradient(top, #FBF5E6, #FFFFFF);
	background: linear-gradient(top, #FBF5E6, #FFFFFF);	    
	border: 1px solid #CFB57C;	     
}    
	  
a.tooltip span b {
	background: #FBF5E6;
	border-top: 1px solid #CFB57C;
	border-right: 1px solid #CFB57C;
}

</style>

</head>

<body>

<%
//Administration------------

	String company = (String) request.getAttribute("company");

%>

<div data-ng-controller="ViewController">

    <div id="wrapper">  <!-- Wrapper -->

		<!-- -------------------------- Flip Drop Down ------------------------------- -->

        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand">ADMIN MAIN PANEL (<%=company %>)</a>
            </div>
            <!-- /.navbar-header -->

            <ul class="nav navbar-top-links navbar-right">
                
                
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-user fa-fw"></i>  <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <li><a href="#"><i class="fa fa-user fa-fw"></i> User Profile</a>
                        </li>
                        <li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a>
                        </li>
                        <li class="divider"></li>
                        <li><a href="index.jsp"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
                        </li>
                    </ul>
                    <!-- /.dropdown-user -->
                </li>
                <!-- /.dropdown -->
            </ul>
            
            
            
            <!-- -------------------------------------------------------------------------------------------- -->

            <div class="navbar-default sidebar">
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">
                        <li class="sidebar-search">
                            <div class="input-group custom-search-form">
                                <input type="text" class="form-control" placeholder="Search...">
                                <span class="input-group-btn">
                                <button class="btn btn-default" type="button">
                                    <i class="fa fa-search"></i>
                                </button>
                            </span>
                            </div>
                            <!-- /input-group -->
                        </li>
                        <li>
                            <a href="#" data-ng-click="approveProductList()">
                            	<i class="fa fa-dashboard fa-fw"></i>
                            	PRODUCTS FOR APPROVAL
                            </a>
                        </li>
                        <li>
                            <a href="#" data-ng-click="statusForBookedProducts()">
                            	<i class="fa fa-dashboard fa-fw"></i>
                            	STATUS FOR BOOKED PRODUCTS
                            </a>
                        </li>
                        <li>
                            <a href="#" data-ng-click="newSellerApproval()">
                            	<i class="fa fa-dashboard fa-fw"></i>
                            	NEW SELLER APPROVAL
                            </a>
                        </li>
                        <li>
                            <a href="#" data-ng-click="redirectToOfferedProductsSelection()">
                            	<i class="fa fa-dashboard fa-fw"></i>
                            	Offered Products Selection
                            </a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-bar-chart-o fa-fw"></i> FRANCHISE<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">                            	
                                <li>
                                	<a href="#" data-ng-click="redirectToUserRegistration()">REGISTRATION</a>
                                </li>
                                <li>
                                    <a href="#" data-ng-click="getPinCommission()">SET PIN / COMMISSION</a>
                                </li>
                                <li>
                                    <a href="#">VIEW DETAILS</a>
                                </li>
                                <li>
                                    <a href="#">Camera</a>
                                </li>
                                <li>
                                    <a href="#">Television</a>
                                </li>
                                <li>
                                    <a href="#">Kitchen Appliance</a>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        
                        <li>
                            <a href="#"><i class="fa fa-sitemap fa-fw"></i> DISTRIBUTOR<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="#">VIEW DETAILS</a>
                                </li>
                                <li>
                                    <a href="#">Second Level Item</a>
                                </li>
                                <li>
                                    <a href="#">Third Level <span class="fa arrow"></span></a>
                                    <ul class="nav nav-third-level">
                                        <li>
                                            <a href="#">Third Level Item</a>
                                        </li>
                                        <li>
                                            <a href="#">Third Level Item</a>
                                        </li>
                                        <li>
                                            <a href="#">Third Level Item</a>
                                        </li>
                                        <li>
                                            <a href="#">Third Level Item</a>
                                        </li>
                                    </ul>
                                    <!-- /.nav-third-level -->
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                       
                        
                        <li>
                            <a href="#"><i class="fa fa-files-o fa-fw"></i> Accessories<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>                                   
                                    <a href="#">Product Category</a>
                                	<ul class="nav nav-third-level">
                                        <li><a href="#" data-ng-click="alterCategory()">Add Category</a></li>
                                        <li><a href="#" data-ng-click="alterSubCategory()">Add SubCategory</a></li>                                        
                                    </ul>
                                </li>
                                <li>
                                    <a href="#">Login Page</a>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                    </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
        </nav>
        
        <!-- -------------------------- End Flip Drop Down ------------------------------- -->
        
        
        
        <!-- -------------------------- Dashboard ------------------------------- -->

       	<div class="right_pannel" style="position:absolute;top:13%;left:19%;width:80%; height:580px;border:none;" data-ng-show="dashboard">
			<div class="initional">
				<div class="page-wrapper" >
            		<div class="row">
                		<div class="col-lg-12">
                    		<h1 class="page-header">Dashboard</h1>
                		</div>                		
            		</div>
            		<div class="auto">            			
            			<div class="row">
                			<div class="col-lg-3 col-md-6">
                    			<div class="panel panel-primary">
                        			<div class="panel-heading">
                            			<div class="row">
                                			<div class="col-xs-3">
                                    			<i class="fa fa-comments fa-5x"></i>
                                			</div>
                                			<div class="col-xs-9 text-right">
                                    			<div class="huge">26</div>
                                    			<div>New Comments!</div>
                                			</div>
                            			</div>
                        			</div>
                        			
                           			<div class="panel-footer">
                               			<a href="#"><span class="pull-left">View Details</span></a>
                               			<span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                               			<div class="clearfix"></div>
                           			</div>
                        			
                    			</div>
                			</div>
                			<div class="col-lg-3 col-md-6">
                    			<div class="panel panel-green">
                        			<div class="panel-heading">
                            			<div class="row">
                                			<div class="col-xs-3">
                                    			<i class="fa fa-tasks fa-5x"></i>
                               			 	</div>
                                			<div class="col-xs-9 text-right">
                                    			<div class="huge">12</div>
                                    			<div>New Tasks!</div>
                                			</div>
                            			</div>
                        			</div>
                        			
                           			<div class="panel-footer">
		                               <a href="#"> <span class="pull-left">View Details</span></a>
		                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
		                                <div class="clearfix"></div>
                           			</div>
                        			
                    			</div>
                			</div>
                			<div class="col-lg-3 col-md-6">
                    			<div class="panel panel-yellow">
                        			<div class="panel-heading">
                            			<div class="row">
                                			<div class="col-xs-3">
                                    			<i class="fa fa-shopping-cart fa-5x"></i>
                                			</div>
			                                <div class="col-xs-9 text-right">
			                                    <div class="huge">124</div>
			                                    <div>New Orders!</div>
			                                </div>
                            			</div>
                        			</div>
			                        
		                            <div class="panel-footer">
		                                <a href="#"><span class="pull-left">View Details</span> </a>
		                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
		                                <div class="clearfix"></div>
		                            </div>
			                       
                    			</div>
                			</div>
			                <div class="col-lg-3 col-md-6">
			                    <div class="panel panel-red">
			                        <div class="panel-heading">
			                            <div class="row">
			                                <div class="col-xs-3">
			                                    <i class="fa fa-support fa-5x"></i>
			                                </div>
			                                <div class="col-xs-9 text-right">
			                                    <div class="huge">13</div>
			                                    <div>Support Tickets!</div>
			                                </div>
			                            </div>
			                        </div>
			                        
		                            <div class="panel-footer">
		                                <a href="#"><span class="pull-left">View Details</span> </a>
		                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
		                                <div class="clearfix"></div>
		                            </div>
			                       
			                    </div>
			                </div>
            			</div>            			
         			</div>
        		</div>		
			</div>		
		</div>
		
		<!-- -------------------------- Dashboard ------------------------------- -->
		
		<!-- -------------- Approve Product ---------------------- -->
		
		<div data-ng-show="productApproval" style="position:absolute;top:8%;left:19%;width:80%;max-height: 700px;border:1px solid #ccc; overflow: auto; overflow-x: none;">
		
			<h1 style="margin-left: 1%;background-color: #eee;padding: 8px 14px; font-size: 23px;width: 98%;"> Products For Approval </h1>
			
			<div data-ng-bind="noData" style="color: red;margin-left: 15px;"></div>
		
			<div class="container" style="width: 96%;border: 1px solid #ccc; float: left;" data-ng-repeat="item in items" 
				data-ng-remove-item data-ng-show="productApprovalNoData">
				<div class="row">
					<div class="col-md-12">
						<div class="col-md-1" style="padding: 0px;">
							<img src="IconImageFromProduct?productId={{item.productId}}" style="width:100%; height: 124px;"/>		
						</div>
						
							<!-- ------------------------------------------------------------------------------------------------ -->
						
						<div class="col-md-11">
						
							<div class="row">
								<div class="col-md-11"> <h1 style="font-size: 18px; margin-top: 9px;margin-top: 6px;">  {{item.productName}} ({{item.companyName}})</h1>
								</div> 
							</div>
							
							<!-- ------------------------------------------------------------------------------------------------ -->
			
							<div class="row">
								<div class="col-md-2">
								<div class="row" >
									<div class="row" style="margin-bottom: 10px;">
										<div class="col-md-12"> 
											<span style="font-size: 12px;margin-top: 5px;color: #337AB7;">  Product Id : {{item.productId}} </span>
										</div> 
									</div>
									<div class="row" style="margin-bottom: 10px;">
										<div class="col-md-12"> 
											<span style="font-size: 12px;margin-top: 5px;color: #337AB7;">  Stock : {{item.stock}} </span>
										</div>
									</div> 
									<div class="row" style="margin-bottom: 10px;">
										<div class="col-md-12"> 
											<span style="font-size: 12px;margin-top: 5px;color: #337AB7;">  Category : {{item.category}} </span>
										</div> 
									</div>
									<div class="row" style="margin-bottom: 10px;">
										<div class="col-md-12"> 
											<span style="font-size: 12px;margin-top: 5px;color: #337AB7;">  SubCategory : {{item.sub_category}} </span>
										</div> 
									</div>
									</div>
								</div>
								
								<!-- ------------------------------------------------------------------------------------------------ -->
								
								<div class="col-md-3">
								<div class="row" >
									<div class="row" style="margin-bottom: 10px;">
										<div class="col-md-12"> 
											<span style="font-size: 12px;">  Stockist : {{item.sellerCompany}} (Id: {{item.sellerId}}) </span>
										</div> 
									</div>
									<div class="row" style="margin-bottom: 10px;">
										<div class="col-md-12"> 
											<span style="font-size: 12px; color: #d9534f;">  Weight : <input type="text" data-ng-model="item.weight"  size="3" style="margin-left: 17px;padding: 3px 5px;"/>  </span>
										</div>
									</div> 
									<div class="row" style="margin-bottom: 10px;">
										<div class="col-md-12"> 
											<span style="font-size: 12px; color: #d9534f;">  Warranty : <input type="text" data-ng-model="item.warranty"  size="25" style="margin-left: 6px;padding: 3px 5px;"/>  </span>
										</div> 
									</div>
									<div class="row" style="margin-bottom: 10px;">
										<div class="col-md-12"> 
											<span style="font-size: 12px; color: #d9534f;">  Cancellation After Booking In DAYS  : <input type="text" data-ng-model="item.calcellationAfterBookedInDays"  size="2" style="margin-left: 17px;padding: 3px 5px;"/>  </span>
										</div> 
									</div>
									</div>
								</div>
								
								<!-- ------------------------------------------------------------------------------------------------ -->
								
								<div class="col-md-2">
								<div class="row" >
									<div class="row" style="margin-bottom: 10px;">
										<div class="col-md-12"> 
											<span style="font-size: 12px; color: #d9534f;">  List Price : {{item.listPrice}}  </span>
										</div>
									</div>
									<div class="row" style="margin-bottom: 10px;">
										<div class="col-md-12"> 
											<span style="font-size: 12px; color: #d9534f;"> Discount :  <input type="text" data-ng-model="item.discount"  size="3" readonly style="padding: 3px 5px;"/>  </span>
										</div>
									</div>
									<div class="row" style="margin-bottom: 10px;">
										<div class="col-md-12"> 
											<span style="font-size: 12px; color: #d9534f;">  Price To Customer :  <input type="text" data-ng-model="item.salePriceCustomer"  size="3" readonly style="padding: 3px 5px;"/> </span>
										</div>
									</div>
									<div class="row" style="margin-bottom: 10px;">
										<div class="col-md-12"> 
											<span style="font-size: 12px; color: #d9534f;">  
												Markup : 
												<input type="text" data-ng-model="item.markupPercentage" 
													data-ng-keyup="markup = item.salePriceToAdmin * item.markupPercentage / 100; item.salePriceCustomer = markup + item.salePriceToAdmin; item.discount = (item.listPrice - item.salePriceCustomer) / item.listPrice * 100;" size="3" style="margin-left: 17px;padding: 3px 5px;">%  
													<input type="text" data-ng-model="markup"  size="3"  readonly/>
											</span>
											
										</div>
									</div>
									<div class="row" style="margin-bottom: 10px;">
										<div class="col-md-12"> 
											<span style="font-size: 12px; color: #d9534f;">  Price To Admin :  <input type="text" data-ng-model="item.salePriceToAdmin"  size="3" readonly style="padding: 3px 5px;"/>  </span>
										</div>
									</div>
									</div>
								</div>
								
								<!-- ------------------------------------------------------------------------------------------ -->
								
							<div class="col-md-2">
								<div class="row" >
									
									<div class="row" style="margin-bottom: 10px;">
										<div class="col-md-12"> 
											<span style="font-size: 12px; color: #d9534f;">  Manf Cost :  <span>{{item.manufacturingCost}}</span>  </span>
										</div>
									</div>									
									<div class="row" style="margin-bottom: 10px;">
										<div class="col-md-12"> 
											<span style="font-size: 12px; color: #d9534f;">  
												Profit Margin % :  
												<input type="text" data-ng-model="item.profitMarginPercentage" 
													data-ng-keyup="profitMargin = item.manufacturingCost * item.profitMarginPercentage / 100; item.salePriceToAdmin = item.manufacturingCost + profitMargin; item.salePriceCustomer = item.markup + item.salePriceToAdmin; item.discount = (item.listPrice - item.salePriceCustomer) / item.listPrice * 100;" size="2"  style="padding: 3px 3px; width: 14%;"/>  
											</span>
										</div>
									</div>
									<div class="row" style="margin-bottom: 10px;">
										<div class="col-md-12"> 
											<div style="display:none;" data-ng-init="profitMargin = item.manufacturingCost + item.profitMarginPercentage"></div>											
											<span style="font-size: 12px; color: #d9534f;">  Profit Margin  :  <input type="text" data-ng-model="profitMargin"  size="2" readonly style="padding: 3px 3px;"/>  </span>
										</div>
									</div>	
									<div class="row" style="margin-bottom: 10px;">
										<div class="col-md-12"> 
											<span style="font-size: 12px; color: #d9534f;">  FranComm : <input type="text" data-ng-model="item.fCommissionPercentage" data-ng-keyup="fCommission = item.salePriceToAdmin * item.fCommissionPercentage / 100" size="3" style="margin-left: 0px;padding: 3px 3px; width: 14%;"/>%
												<input type="text" data-ng-model="fCommission"  size="3" style="margin-left: 0px;" readonly style="padding: 3px 3px; width: 14%;"/>  </span>
										</div>
									</div>
									<div class="row" style="margin-bottom: 10px;">
										<div class="col-md-12"> 
											<span style="font-size: 12px; color: #d9534f;">  DrisComm : <input type="text" data-ng-model="item.dCommissionPercentage" data-ng-keyup="dCommission = item.salePriceToAdmin * item.dCommissionPercentage / 100" size="3" style="margin-left: 3px;padding: 3px 3px; width: 14%;"/>%
												<input type="text" data-ng-model="dCommission"  size="3" style="margin-left: 3px;" readonly style="padding: 3px 3px; width: 14%;"/>  </span>
										</div>
									</div>									
								</div>								
							</div>
								
								
								<!-- --------------------------------------------------------------------------------------------- -->
			
								<div class="col-md-1" style="margin-top: 60px;    margin-left: 80px;">
				
									<ul>
										<li class="dots"> 
											<a href="#" style="font-size: 12px;" data-ng-click="approveProduct()"> Approve </a> 
										</li> 
										<li class="dots"> 
											<a href="#" style="font-size: 12px;">Revice </a> 
										</li> 
										<li class="dots">  
											<a href="#" style="font-size: 12px;"> Remove </a> 
										</li> 
										<li class="dots">  
											<a href="#" style="font-size: 12px;"> Delete </a> 
										</li> 
									</ul>  
				
								</div>								
							</div>	
						</div>		
					</div>
				</div>		
			</div>		
    	</div>   
    
    	<!-- -------------- End Approve Product ---------------------- -->
    
    	<!-- -------------- Edit Franchise ---------------------- -->
		
		<div data-ng-show="editFranchise" style="position:absolute;top:8%;left:19%;width:80%;max-height: 700px;border:1px solid #ccc; overflow: auto; overflow-x: none;">
			
			<h1 style="margin-left: 1%;background-color: #eee;padding: 8px 14px; font-size: 23px;width: 98%;"> Set Pin And Commission </h1>
			
			<div class="container" data-ng-repeat="item in pinCommissions" data-ng-controller="SetPinCommissionController">
			
				<div class="row">
				
					<div class="col-md-4">	
							
						<div class="row" style="padding: 7px 10px;">
							<div class="col-md-12">
								<span style="font-size: 14px; color:#F10B0B;">  Company Name :  {{item.companyName}}  </span>			
							</div>
						</div>
			
						<div class="row" style="padding: 7px 10px;">
							<div class="col-md-12">
								<span style="font-size: 14px;color:#0B6BBD;">  Franchise Name :  {{item.firstName + " " + item.lastName}}  </span>			
							</div>
						</div>
						
						<div class="row" style="padding: 7px 10px;">
							<div class="col-md-12">
								<span style="font-size: 14px;color:#D67F03;"> ID :  {{item.id}}  </span>			
							</div>			
						</div>
			
					</div>		
		
					<div class="col-md-3">			
			
						<div class="row" style="padding: 7px 10px;">
							<div class="col-md-12">
								<span style="font-size: 14px;">  Pin1 :  <input type="text"  data-ng-model="item.pin1" placeholder="Set Pin1" size="16" style="padding: 3px 11px;"> 
									<a href="#" style="font-size: 14px;" data-ng-click="setPin(0)">save</a>
								</span>			
							</div>
						</div>			
			
			   			<div class="row" style="padding: 7px 10px;">
							<div class="col-md-12">
								<span style="font-size: 14px;">  Pin2 :  <input type="text"   data-ng-model="item.pin2"  placeholder="Set Pin2" size="16" style="padding: 3px 11px;"> 
									<a href="#" style="font-size: 14px;" data-ng-click="setPin(1)">save</a>
								</span>			
							</div>
						</div>
				
						<div class="row" style="padding: 7px 10px;">
							<div class="col-md-12">
								<span style="font-size: 14px;">  Pin3 :  <input type="text"   data-ng-model="item.pin3"  placeholder="Set Pin2" size="16" style="padding: 3px 11px;"> 
									<a href="#" style="font-size: 14px;" data-ng-click="setPin(2)">save</a>
								</span>			
							</div>
						</div>
			
					</div>		
			
					<div class="col-md-3">			
		
						<div class="row" style="padding: 7px 10px;">
							<div class="col-md-12">
								<span style="font-size: 14px;">  Pin4 :  <input type="text"   data-ng-model="item.pin4"  placeholder="Set Pin1" size="16" style="margin-left: 15%;padding: 3px 11px;"> 
									<a href="#" style="font-size: 14px;" data-ng-click="setPin(3)">save</a>
								</span>			
							</div>
						</div>
			
						<div class="row" style="padding: 7px 10px;">
							<div class="col-md-12">
								<span style="font-size: 14px;">  Pin5 :  <input type="text"   data-ng-model="item.pin5"  placeholder="Set Pin2" size="16" style="margin-left: 15%;padding: 3px 11px;"> 
									<a href="#" style="font-size: 14px;" data-ng-click="setPin(4)">save</a>
								</span>			
							</div>
						</div>
			
						<div class="row" style="padding: 7px 10px;">
							<div class="col-md-12">
								<span style="font-size: 14px;">  Commission :  <input type="text"   data-ng-model="item.commission"  placeholder="Commission" size="3" style="padding: 3px 11px;"> %
									<a href="#" style="font-size: 14px;" data-ng-click="setCommission()">save</a>
								</span>			
							</div>			
						</div>		
		
					</div>
		          
		          	<div class="col-md-2" style="margin-top:10px;">
			          	<div class="row">
							<div class="col-md-12">	
			          			<span style="font-size: 14px;">  Additional Balance :  
			          				<input type="text"   data-ng-model="addtionalBalance"  placeholder="Balance" size="8" style="margin-left: 0%;padding: 3px 11px;"> 
									<a href="#" style="font-size: 14px;" data-ng-click="setAdditionalBalance()">save</a>
									<span data-ng-bind="item.balance" style="margin-left: 9px;"></span>
								</span>	
							</div>
						</div>								
					</div>
					
					
					<div style="margin-top: 85px;color: red;" data-ng-bind="message1" data-js-empty><!-- message --></div>	
		
				</div>			
			
			</div>
			
		</div>
		<!-- -------------- End Edit Franchise ---------------------- -->	
		
		<!-- -------------- Status for booked products ---------------------- -->
		
	<div data-ng-show="bookedProductsStatus" style="position:absolute;top:8%;left:19%;width:80%;max-height: 700px; border:1px solid #ccc; overflow: auto; overflow-x: hidden;">
				
		<div class="col-md-12"> <h1 style="margin-left: 1%;background-color: #eee;padding: 8px 14px; font-size: 23px;width: 98%;"> Status For Booked Products </h1>  </div>	
		
		<div class="row" style="margin-bottom: 20px;" data-ng-repeat="item in bookedProducts"> 
			 
			<div class="col-md-12" style="width:100%;border:1px solid #ddd;margin-bottom:-12px;box-shadow: 1px 1px 1px #f5f5f5;background-color: #FFFFF5;">
				<div class="col-md-2" style="border: 1px solid #EAEAEA; box-shadow: 1px 1px 1px #e7e7e7; margin-top:10px; margin-bottom: 11px;">
					<img alt="image" src="IconImageFromProduct?productId={{item.orderTableData.productId}}" width="150" height="150" />
				</div>
				<div class="col-md-7">
					<div class="col-md-6">
						<h3 style="margin-bottom: 7px;margin-top: 8px;color: #337ab7; font-size: 16px;">  Order Id : {{item.orderTableData.orderId}}  </h3> <hr id="hr">
						<span>   Id :  {{item.orderTableData.id}}  </span>  											
						<hr id="hr">					
						<span style="margin-right:15px;">Product Id : {{item.orderTableData.productId}} </span> <br> <hr id="hr">
						<span>  Each Price : {{item.orderTableData.sellPrice}} </span> 
						<span style="margin-left: 43px;">  Total Price : {{item.orderTableData.sellPrice * item.orderTableData.qty}} </span><br> <hr id="hr">
						<span>Warranty  : {{item.orderTableData.warranty}}</span>
						
					</div>					
					<div class="col-md-5" style="margin-top: 6px;">
					
						<span>Shipping Cost : Rs. {{item.orderTableData.shippingCost * item.orderTableData.qty}}</span>
						<hr id="hr">
						<span>Quantity : {{item.orderTableData.qty}}</span>
						<hr id="hr">
						<span>Order  Booked On : {{item.orderTableData.bookedDateTime}}</span>
						<hr id="hr">						
						<span>PaymentType : {{item.orderTableData.paymentType}}</span>
						<span data-ng-if="item.orderTableData.size != 0" style="margin-left: 28px;">Size : {{item.orderTableData.size}}</span>						
						<hr id="hr">
						<div>
							<a class="tooltip" style="color:blue;float:left;">
								Customer
								<span>
									<span>CustomerId: {{item.orderTableData.customerId}}</span><br>
									<span>Contact:    {{item.customerDeliveryAddress.contact  }}</span><br>
									<span>Address:    {{item.customerDeliveryAddress.address  }}</span><br>
									<span>Address1:   {{item.customerDeliveryAddress.address1 }}</span><br>
									<span>City:       {{item.customerDeliveryAddress.city     }}</span><br>
									<span>State:      {{item.customerDeliveryAddress.state    }}</span><br>
									<span>Pin:        {{item.customerDeliveryAddress.pin      }}</span><br>
									<span>FirstName:  {{item.customerDeliveryAddress.firstName}}</span><br>
									<span>LastName:   {{item.customerDeliveryAddress.lastName }}</span><br>
									<span>Email:      {{item.customerDeliveryAddress.email    }}</span><br>
									<span>Country:    {{item.customerDeliveryAddress.company  }}</span><br>
									<span>Company:    {{item.customerDeliveryAddress.country  }}</span>
								</span>
							</a>
							
							<a class="tooltip" style="margin-left: 83px;color:#A94442;float:left;">
								Stockist
								<span>
									<span>StockistId: {{item.sellerData.id      }}</span><br>
									<span>UserType:   {{item.sellerData.userType}}</span><br>
									<span>FirstName:  {{item.sellerData.fName   }}</span><br>
									<span>LastName:   {{item.sellerData.lName   }}</span><br>
									<span>Company:    {{item.sellerData.company }}</span><br>
									<span>Address:    {{item.sellerData.address }}</span><br>
									<span>Address1:   {{item.sellerData.address1}}</span><br>
									<span>Pin:        {{item.sellerData.pin     }}</span><br>
									<span>City:       {{item.sellerData.city    }}</span><br>
									<span>State:      {{item.sellerData.state   }}</span><br>
									<span>Country:    {{item.sellerData.country }}</span><br>
									<span>Sex:        {{item.sellerData.sex     }}</span><br>
									<span>Mobile:     {{item.sellerData.mobile  }}</span><br>
									<span>Email:      {{item.sellerData.email   }}</span><br>
									<span>Balance:    {{item.sellerData.balance }}</span>									
								</span>
							</a>		
							
							<a class="tooltip" style="margin-left: 14px;color:green;float:right;">
								Courier
								<span>
									<span>TrackNumber: {{item.orderTableAccessories.trackNumber                 }}</span><br>
									<span>DeleveredDate: {{item.orderTableAccessories.deliveredDate             }}</span><br>
									<span>PickedDate: {{item.orderTableAccessories.pickedDate                   }}</span><br>
									<span>CancellationTime: {{item.orderTableAccessories.calcellationAfterBooked}} Days</span><br>
									<span>CourierName: {{item.orderTableAccessories.courier                     }}</span>
								</span>	
							</a>						
						</div>				
					</div>					
				</div>
				<!-- -------------------------------------------------------------- -->
				
				
				<div class="col-md-3" style="margin-top:100px;" data-ng-show="item.orderTableData.orderState == 'Booked'">
					<span style="float: left;
								margin-top: 9px;    
								float: left;
						    	margin-top: -24px;
						    	margin-left: 96px;
						    	font-family: fantasy;
						    	font-size: large;
						    	color: green;">
						Order Booked By Customer
					</span>
				</div>
				<div class="col-md-3" style="margin-top:100px;" data-ng-show="item.orderTableData.orderState == 'Pickup'">
					<span style="float: left;
								margin-top: 9px;    
								float: left;
						    	margin-top: -24px;
						    	margin-left: 96px;
						    	font-family: fantasy;
						    	font-size: large;
						    	color: #31708F;">
						Track Number Generated <br>
						(Pickup Required)
					</span>
				</div>
				<div class="col-md-3" style="margin-top:100px;" data-ng-show="item.orderTableData.orderState == 'Picked'">
					<span style="float: left;
								margin-top: 9px;    
								float: left;
						    	margin-top: -24px;
						    	margin-left: 96px;
						    	font-family: fantasy;
						    	font-size: large;
						    	color: #F0AD4E;">
						Item Picked by Courier
					</span>
				</div>
				<div class="col-md-3" style="margin-top:100px;" data-ng-show="item.orderTableData.orderState == 'Cancel'">
					<span style="float: left;
								margin-top: 9px;    
								float: left;
						    	margin-top: -24px;
						    	margin-left: 96px;
						    	font-family: fantasy;
						    	font-size: large;
						    	color: #EA150F;">
						Order Cancelled By Customer <br>
						(Cancel pending by Stockist)
					</span>
				</div>
				<div class="col-md-3" style="margin-top:100px;" data-ng-show="item.orderTableData.orderState == 'Return'">
					<span style="float: left;
								margin-top: 9px;    
								float: left;
						    	margin-top: -24px;
						    	margin-left: 96px;
						    	font-family: fantasy;
						    	font-size: large;
						    	color: #EA150F;">
						Order Returned By Customer
					</span>
				</div>			
				
				<div class="col-md-3" data-ng-show="item.orderTableData.orderState == 'Delivered'" data-ng-controller="DeliveredController" style="margin-top:100px;" >
					<div data-ng-if="item.orderTableData.orderState == 'Delivered'" data-ng-init="checkDelivery(item.orderTableAccessories.deliveredDate)"></div>
					<span data-ng-show="delivered" style="float: left;
														margin-top: 9px;    
														float: left;
												    	margin-top: -24px;
												    	margin-left: 96px;
												    	font-family: fantasy;
												    	font-size: large;">
						Order Delivered <br>
						(Payment to Stockist pending)
					</span>
					<span data-ng-show="payment" style="float: left;
														margin-top: 9px;    
														float: left;
												    	margin-top: -24px;
												    	margin-left: 96px;												    	
												    	font-size: large;">
						<button class="btn btn-success">Payment to Stockist</button>
					</span>
				</div>
				
				
				<!-- -------------------------------------------------------------- -->	
			</div>
		</div>			
			
			
	</div> <!-- Main -->
		<!-- -------------- End Status for booked products ---------------------- -->	
		
		
		
		
		<!-- -------------- Approve Seller ---------------------- -->
		
		<div data-ng-show="approveSeller" style="position:absolute;top:8%;left:19%;width:80%;max-height: 700px;border:1px solid #ccc; overflow: auto; overflow-x: none;">
		
			<h1 style="margin-left: 1%;background-color: #eee;padding: 8px 14px; font-size: 23px;width: 98%;"> Approve Seller </h1>
			
		
			<div class="container" style="width: 96%;border: 1px solid #ccc; float: left;" data-ng-repeat="item in items">
				<div class="row">
					<div class="col-md-12">
					
						<div class="col-md-12">
							<div class="row">
								<div class="col-md-11"> <h1 style="font-size: 18px; margin-top: 9px;margin-top: 6px;">  Company: {{item.company}} </h1>
								</div> 
							</div>
			
							<div class="row">
								<div class="col-md-4">
									<div class="row">
										<div class="col-md-12"> 
											<span style="font-size: 13px;margin-top: 5px;color: #f0ad4e;">  Id : {{item.id}} </span>
										</div> 
									</div>
									<div class="row">
										<div class="col-md-12"> 
											<span style="font-size: 13px;margin-top: 5px;color: #337AB7;">  User Id : {{item.userId}}</span>
										</div>
									</div> 
									<div class="row">
										<div class="col-md-12"> 
											<span style="font-size: 13px;margin-top: 5px;color: #337AB7;">  Owner Name :  {{item.firstName + " " + item.lastName}}</span>
										</div> 
									</div>
									<!-- <div class="row">
										<div class="col-md-12"> 
											<span style="font-size: 13px;margin-top: 5px;color: #337AB7;">  Demo :  </span>
										</div> 
									</div> -->
								</div>
								
								<div class="col-md-4">
									<div class="row">
										<div class="col-md-12"> 
											<span style="font-size: 13px; color: #3389CB;">  Mobile :   {{item.mobile1}}</span>
										</div>
									</div>
									<div class="row">
										<div class="col-md-12"> <span style="font-size: 13px; color: #3389CB;">  Land Phone :  {{item.phone1}} </span>
										</div>
									</div>
									<div class="row">
										<div class="col-md-12"> 
											<span style="font-size: 13px; color: #3389CB;">  Email :   {{item.email1}} </span>
										</div>
									</div>
									
								</div>
								
							
			
								<div class="col-md-4" style="margin-top: 30px;">
				
									<ul>
										<li class="dots" style="list-style: none;"> 
											<a href="#" style="font-size: 13px;" data-ng-click="approveSellerRegistrationPage(item.id)"> EDIT </a> 
										</li> 
										<li class="dots"> 
											<a href="#" style="font-size: 13px;">Cancel </a> 
										</li> 
										<li class="dots">  
											<a href="#" style="font-size: 13px;"> Remove </a> 
										</li> 
										<li class="dots">  
											<a href="#" style="font-size: 13px;"> Delete </a> 
										</li> 
									</ul>  
				
								</div>								
							</div>	
						</div>		
					</div>
				</div>		
			</div>		
			
			
			
    	</div>   <!-- End -->
		<!-- -------------- End Approve Seller ---------------------- -->
		
		
		<!-- -------------- Add Category  ---------------------- -->
		
		<div data-ng-show="addACategory" style="position:absolute;top:8%;left:19%;width:80%;max-height: 170px;border:1px solid #ccc; overflow: hidden; overflow-x: none;">
		
			<h1 style="margin-left: 1%;background-color: #eee;padding: 8px 14px; font-size: 23px;width: 98%;"> Add Category </h1>			
			
			<form id="category" method="post" enctype="multipart/form-data">
			
				  <div style="padding: 20px;  font-size: 18px; font-family: vardana; border: 1px solid #ccc;">Add New Category:
				  	<input type="text" name="category" required data-ng-model="category"/>
				  	<span style="margin-left: 8px;"><input type="submit" class="btn-md" value="submit" data-ng-click="addCategoryMethod()"/></span> 
				  	<span style="margin-left: 120px;">
				  		Category: 
				  		<select data-ng-model="deleteCategory" style="width: 25%;height: 32px;" 
				  			data-ng-options="item.category for item in categoryItems track by item.id">			  							  			
				  			<option value="">--------- category ---------</option>
				  		</select>
				  		<span style="margin-left: 8px;"><input type="button" class="btn-md" value="delete" data-ng-click="deleteCategoryMethod()" /></span>
				  		
				  	</span>
				  </div>
		  
			</form>
			
			<div data-ng-bind="categoryMessage" style="margin-left: 12px;margin-top: 5px;color:red;"><!-- Ajax Data --></div>
			<div data-ng-bind="deleteCategoryMessage" style="margin-left: 597px;margin-top: 5px;color:red;"><!-- Ajax Data  --></div>
		
		</div>
		
		<!-- -------------- Add  Sub - Category  ---------------------- -->
		
		<div data-ng-show="addSubCategory" style="position:absolute;top:8%;left:19%;width:80%;max-height: 266px;border:1px solid #ccc; overflow: hidden; overflow-x: none;">
		
			<h1 style="margin-left: 1%;background-color: #eee;padding: 8px 14px; font-size: 23px;width: 98%;"> Add SubCategory </h1>			
			
			<form id="addSubCategory">
			
				  <div style="padding: 20px;  font-size: 18px; font-family: vardana; border: 1px solid #ccc;">Select Category:
				  	<select data-ng-model="category1" style="width: 25%;height: 32px;" data-ng-change="setNullToStrings()"
			  			data-ng-options="item.category for item in categoryItems track by item.id">			  							  			
			  			<option value="">--------- category ---------</option>
			  		</select>				  	
				  	<span style="margin-left: 119px;">
				  		Add SubCategory: 
				  		<input type="text"  name="addASubCategory" data-ng-model="addASubCategory" required style="margin-left: 13px;width: 263px;"/>
				  		<span style="margin-left: 8px;">
				  			<input type="button" class="btn-md" value="add" data-ng-click="addSubCategoryMethod()" style="padding-right: 18px;padding-left: 18px;"/>
				  		</span>				  		
				  	</span>
				  </div>
		  
			</form>
			
			<form id="deleteSubCategory">
			
				  <div style="padding: 20px;  font-size: 18px; font-family: vardana; border: 1px solid #ccc;">Select Category:
				  	<select data-ng-model="category2" style="width: 25%;height: 32px;" data-ng-change="getSubCategories()"
			  			data-ng-options="item.category for item in categoryItems track by item.id">			  							  			
			  			<option value="">--------- category ---------</option>
			  		</select>
				  	<span style="margin-left: 120px;">
				  		Select SubCategory: 
				  		<select data-ng-model="deleteSubCategory" style="width: 25%;height: 32px;" 
				  			data-ng-options="item.subSategory for item in subCategoryItems track by item.id">
				  			<option value="">--------- sub category ---------</option>				  			
				  		</select>
				  		<span style="margin-left: 8px;">
				  			<input type="button" class="btn-md" value="delete" data-ng-click="deleteSubCategoryMethod()" style="padding-left: 9px;padding-right: 9px;"/>
				  		</span>
				  		
				  	</span>
				  </div>
		  
			</form>
			
			<div data-ng-bind="addSubCategoryMessage" style="margin-left: 12px;margin-top: 5px;color:red;"><!-- Ajax Data --></div>
			<div data-ng-bind="deleteSubCategoryMessage" style="margin-left: 597px;margin-top: 5px;color:red;"><!-- Ajax Data  --></div>
		
		</div>
		
		
		
		<!-- -------------- End Spare ---------------------- -->
		
		<!-- <div data-ng-show="editFranchise" style="position:absolute;top:8%;left:19%;width:83%;max-height: 700px;border:1px solid #ccc; overflow: auto; overflow-x: none;">
		
		sdkjfcscvjksdjds
		</div> -->
		<!-- -------------- End Spare ---------------------- -->
		
		<!-- <div data-ng-show="editFranchise" style="position:absolute;top:8%;left:19%;width:83%;max-height: 700px;border:1px solid #ccc; overflow: auto; overflow-x: none;">
		
		sdkjfcscvjksdjds
		</div> -->
		<!-- -------------- End Spare ---------------------- -->
		
		<!-- <div data-ng-show="editFranchise" style="position:absolute;top:8%;left:19%;width:83%;max-height: 700px;border:1px solid #ccc; overflow: auto; overflow-x: none;">
		
		sdkjfcscvjksdjds
		</div> -->
		<!-- -------------- End Spare ---------------------- -->
		
		<!-- <div data-ng-show="editFranchise" style="position:absolute;top:8%;left:19%;width:83%;max-height: 700px;border:1px solid #ccc; overflow: auto; overflow-x: none;">
		
		sdkjfcscvjksdjds
		</div> -->
		<!-- -------------- End Spare ---------------------- -->
  
</div> <!-- End Wrapper -->

</div> <!-- End ViewController -->

</body>

</html>
