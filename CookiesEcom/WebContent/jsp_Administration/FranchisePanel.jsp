<%@page import="ecom.model.User"%>
<%@page import="ecom.common.FrequentUse"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html lang="en" data-ng-app="franchise">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Franchise Panel</title>
    
	    <!-- jQuery -->    
	    <script type="text/javascript" src="<%=FrequentUse.jQuery %>"></script>	
	    <script type="text/javascript" src="<%=FrequentUse.angular %>"></script>   
	    <script type="text/javascript" src="js_Administration/Franchise.js"></script> 
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
</style>

</head>

<body>

<%
//Administration------------

	User user = (User) session.getAttribute("user");
	long user_id = user.getUserInfo().getId();
	
	String company = (String) request.getAttribute("company");
	String fName   = (String) request.getAttribute("fName");
	String lName   = (String) request.getAttribute("lName");

%>

<div data-ng-controller="ViewController">

    <div id="wrapper">

        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand">FRANCHISE MAIN PANEL </a>
                <span style="margin-top: 14px;margin-left: 11px;float: left;">( <%=company + " - " +  fName + " " + lName%> )</span>
            </div>            

            <ul class="nav navbar-top-links navbar-right">
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-envelope fa-fw"></i>  <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-messages">
                        <li>
                            <a href="#">
                                <div>
                                    <strong>John Smith</strong>
                                    <span class="pull-right text-muted">
                                        <em>Yesterday</em>
                                    </span>
                                </div>
                                <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eleifend...</div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <strong>John Smith</strong>
                                    <span class="pull-right text-muted">
                                        <em>Yesterday</em>
                                    </span>
                                </div>
                                <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eleifend...</div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <strong>John Smith</strong>
                                    <span class="pull-right text-muted">
                                        <em>Yesterday</em>
                                    </span>
                                </div>
                                <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eleifend...</div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a class="text-center" href="#">
                                <strong>Read All Messages</strong>
                                <i class="fa fa-angle-right"></i>
                            </a>
                        </li>
                    </ul>
                    <!-- /.dropdown-messages -->
                </li>
                <!-- /.dropdown -->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-tasks fa-fw"></i>  <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-tasks">
                        <li>
                            <a href="#">
                                <div>
                                    <p>
                                        <strong>Task 1</strong>
                                        <span class="pull-right text-muted">40% Complete</span>
                                    </p>
                                    <div class="progress progress-striped active">
                                        <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 40%">
                                            <span class="sr-only">40% Complete (success)</span>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <p>
                                        <strong>Task 2</strong>
                                        <span class="pull-right text-muted">20% Complete</span>
                                    </p>
                                    <div class="progress progress-striped active">
                                        <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width: 20%">
                                            <span class="sr-only">20% Complete</span>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <p>
                                        <strong>Task 3</strong>
                                        <span class="pull-right text-muted">60% Complete</span>
                                    </p>
                                    <div class="progress progress-striped active">
                                        <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%">
                                            <span class="sr-only">60% Complete (warning)</span>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <p>
                                        <strong>Task 4</strong>
                                        <span class="pull-right text-muted">80% Complete</span>
                                    </p>
                                    <div class="progress progress-striped active">
                                        <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100" style="width: 80%">
                                            <span class="sr-only">80% Complete (danger)</span>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a class="text-center" href="#">
                                <strong>See All Tasks</strong>
                                <i class="fa fa-angle-right"></i>
                            </a>
                        </li>
                    </ul>
                    <!-- /.dropdown-tasks -->
                </li>
                <!-- /.dropdown -->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-bell fa-fw"></i>  <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-alerts">
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-comment fa-fw"></i> New Comment
                                    <span class="pull-right text-muted small">4 minutes ago</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-twitter fa-fw"></i> 3 New Followers
                                    <span class="pull-right text-muted small">12 minutes ago</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-envelope fa-fw"></i> Message Sent
                                    <span class="pull-right text-muted small">4 minutes ago</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-tasks fa-fw"></i> New Task
                                    <span class="pull-right text-muted small">4 minutes ago</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-upload fa-fw"></i> Server Rebooted
                                    <span class="pull-right text-muted small">4 minutes ago</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a class="text-center" href="#">
                                <strong>See All Alerts</strong>
                                <i class="fa fa-angle-right"></i>
                            </a>
                        </li>
                    </ul>
                    <!-- /.dropdown-alerts -->
                </li>
                <!-- /.dropdown -->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-user fa-fw"></i>  <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <li><a href="#"><i class="fa fa-user fa-fw"></i> User Profile</a></li>
                        <li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a></li>
                        <li class="divider"></li>
                        <li><a href="index.jsp"><i class="fa fa-sign-out fa-fw"></i> Logout</a></li>
                    </ul>
                    <!-- /.dropdown-user -->
                </li>
                <!-- /.dropdown -->
            </ul>
            <!-- /.navbar-top-links -->

            <div class="navbar-default sidebar" role="navigation">
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
                            <a href="#"><i class="fa fa-dashboard fa-fw"></i>Demo</a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-bar-chart-o fa-fw"></i> DISTRIBUTOR<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">                            	
                                <li>
                                	<a href="#" data-ng-click="redirectToDistributorRegistration()">REGISTRATION</a>
                                </li>
                                <li>
                                    <a href="#" data-ng-click="getPinAreaCommission(<%=user_id %>)">SET PIN / AREA / COMMISSION</a>
                                </li>
                                <li>
                                    <a href="#">Tablet</a>
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
                            <a href="#"><i class="fa fa-sitemap fa-fw"></i> Multi-Level Dropdown<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="#">Second Level Item</a>
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
                            <a href="#"><i class="fa fa-files-o fa-fw"></i> Sample Pages<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="#">Blank Page</a>
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
        
        <!-- ---------------- Dashboard -------------------------- -->

       	<div class="right_pannel" style="position:absolute;top:18%;left:19%;width:81%; height:580px;border:none;" data-ng-show="dashboard">
			<div class="initional">
				<div class="page-wrapper" >

		            <div class="row">
		                <div class="col-lg-12">
		                    <h1 class="page-header">Dashboard</h1>
		                </div>
		                <!-- /.col-lg-12 -->
		            </div>
	            	<div class="auto">
	            	<!-- /.row -->
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
	                        		<a href="#">
			                            <div class="panel-footer">
			                                <span class="pull-left">View Details</span>
			                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
			                                <div class="clearfix"></div>
			                            </div>
	                        		</a>
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
	                        		<a href="#">
			                            <div class="panel-footer">
			                                <span class="pull-left">View Details</span>
			                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
			                                <div class="clearfix"></div>
			                            </div>
	                        		</a>
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
			                        <a href="#">
			                            <div class="panel-footer">
			                                <span class="pull-left">View Details</span>
			                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
			                                <div class="clearfix"></div>
			                            </div>
			                        </a>
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
			                        <a href="#">
			                            <div class="panel-footer">
			                                <span class="pull-left">View Details</span>
			                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
			                                <div class="clearfix"></div>
			                            </div>
			                        </a>
			                    </div>
			                </div>
	            		</div>		
	            
	         		</div>
        		</div>			
			
			</div>
			
		</div>	
		
		<!-- ---------------- End Dashboard -------------------------- -->
		
		<!-- ------------------- Set Pin / Area / Commission -------------------- -->
		
		 <div data-ng-show="pinAreaCommission" class="right_pannel" style="position:absolute;top: 8%;left:16%;width:83%; max-height:700px;     overflow: auto;border: 1px solid #ccc;">
			
			<h1 style="margin-left: 1%;background-color: #eee;padding: 8px 14px; font-size: 23px;width: 98%;"> Set Pin, Area And Commission </h1>
			
			<div class="container" data-ng-repeat="item in items" data-ng-controller="SetPinAreaCommissionController">
			
				<div class="row">
				
					<div class="col-md-4">	
							
						<div class="row" style="padding: 7px 10px;">
							<div class="col-md-12">
								<span style="font-size: 14px; color:#F10B0B;">  Company Name :  {{item.company}} </span>			
							</div>
						</div>
			
						<div class="row" style="padding: 7px 10px;">
							<div class="col-md-12">
								<span style="font-size: 14px;color:#0B6BBD;">  Distributor Name : {{item.fName + " " + item.lName}}   </span>			
							</div>
						</div>
						
						<div class="row" style="padding: 7px 10px;">
							<div class="col-md-12">
								<span style="font-size: 14px;color:#D67F03;"> ID : {{item.id}}  </span>			
							</div>			
						</div>
			
					</div>						
			
					<div class="col-md-4">			
		
						<div class="row" style="padding: 7px 10px;">
							<div class="col-md-12">
								<span style="font-size: 14px; float: left;">  
									Pin :  
								</span>		
								<select class="form-control" data-ng-model="savePin"
									style="margin-left: 12%;padding: 3px 11px; width: 36%;float: left;">
									<option>----Select Pin----</option>
									<option value="{{pin1}}">{{pin1}}</option>
									<option value="{{pin2}}">{{pin2}}</option>
									<option value="{{pin3}}">{{pin3}}</option>
									<option value="{{pin4}}">{{pin4}}</option>
									<option value="{{pin5}}">{{pin5}}</option>
								</select>		
								<a href="#" style="font-size: 14px;float: left;margin-left: 14px;" data-ng-click="setPin()">save</a>
								<span style="margin-left: 14px;float: left;" data-ng-bind="item.pin"><!-- pin --></span> 									
							</div>
						</div>
			
						<div class="row" style="padding: 7px 10px;">
							<div class="col-md-12">
								<span style="font-size: 14px;">  Area :  <input type="text"   data-ng-model="item.area"  placeholder="Set Area" size="16" style="margin-left: 10%;padding: 3px 11px;"> 
									<a href="#" style="font-size: 14px;" data-ng-click="setArea()">save</a>
								</span>			
							</div>
						</div>
			
						<div class="row" style="padding: 7px 10px;">
							<div class="col-md-12">
								<span style="font-size: 14px;">  Commission :  <input type="text"   data-ng-model="item.commission"  placeholder="" size="3" style="padding: 3px 11px;"> %
									<a href="#" style="font-size: 14px;" data-ng-click="setCommission()">save</a>
								</span>			
							</div>			
						</div>		
		
					</div>
					
					<div class="col-md-3" style="margin-top:10px;">
		          		<div class="row">
							<div class="col-md-12">	
		          				<span style="font-size: 14px;">  Balance :  
		          					<input type="text"   data-ng-model="addtionalBalance"  placeholder="Balance" size="8" style="margin-left: 15%;padding: 3px 11px;"> 
									<a href="#" style="font-size: 14px;margin-left: 5px;" data-ng-click="setAdditionalBalance()">save</a>
									<span data-ng-bind="item.balance" style="margin-left: 9px;"></span>
								</span>	
							</div>
						</div>
								
								</div>
					<div style="margin-top: 85px;color: red;" data-ng-bind="message1"><!-- message1 --></div>	
		
				</div>			
			
			</div><!-- container -->
			
		</div>
		
		
		<!-- ------------------- End Set Pin / Area / Commission -------------------- -->

    </div>   <!-- wrapper -->
   
</div>  <!-- view controller -->

</body>

</html>
