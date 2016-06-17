<%@page import="ecom.common.FrequentUse"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html data-ng-app="franchise">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>User Registration</title>
	<script type="text/javascript" src="<%=FrequentUse.angular %>"></script>
	<script type="text/javascript" src="js_Administration/Franchise.js"></script>
	
	<link href="<%=FrequentUse.style %>" rel='stylesheet' type='text/css' />
	<link type="text/css" href="css/bootstrap.css" rel="stylesheet">
	
	<style type="text/css">
		.row {
		    padding: 5px 23px;
		}
		hr {
		    margin-top: 20px;
		    margin-bottom: 20px;
		    border: 0;
		    border-top: 1px solid #e5e5e5;c;
		}
		label {
		    display: inline-block;
		    margin-bottom: 5px;
		    font-weight: bold;
		    color: #8B2498;
		}
		html,body {
			background-color: #f5f5f5 !important;
		}
	</style>
</head>
<body data-ng-controller="CreateEditUserController">

<%
	String title          = (String) request.getAttribute("title");
	String submitFunction = (String) request.getAttribute("submitFunction");
	String pins           = (String) request.getAttribute("pins");

%>

<div class="register_pannel">

		<div class="logo_pannel">
		 	<a href="index.html"> <img src="images/logoFirstTrade.png" /> </a>
		</div>

		<div class="register_page">


			<div class="tag_pannel"> <h1><%=title %> </h1> </div>
			<hr>

			<div class="row">
				<div class="col-md-3 col-sm-6 col-xs-12">
					<label> <span style="color:red;">*</span> User Id  </label>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" class="form-control" required placeholder="User Id" data-ng-model="userId" />
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<label>	<span style="color:red;">*</span> Password </label>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="password" class="form-control" required data-ng-model="password" />
				</div>
			</div>	
			
			
			
			<div class="row">
				<div class="col-md-3 col-sm-6 col-xs-12">
					<label> <span style="color:red;">*</span> First name </label>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" class="form-control" required placeholder="Enter First Name" data-ng-model="fName" />
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<label>	<span style="color:red;">*</span> Last Name </label>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" class="form-control" placeholder="Enter Last Name"  required data-ng-model="lName" />
				</div>
			</div>		


			<div class="row">
				<div class="col-md-3 col-sm-6 col-xs-12">
					<label> Sex </label>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<select class="form-control" data-ng-model="sex">
						<option value="null">---Select Sex---</option>
						<option value="Male">Male</option>
						<option value="Female">Female</option>
					</select>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<label>	<span style="color:red;">*</span> Company  </label>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" class="form-control" placeholder="Enter Company Name"  required data-ng-model="company" />
				</div>
			</div>	


			<div class="row">
				<div class="col-md-3 col-sm-6 col-xs-12">
					<label> <span style="color:red;">*</span> Mobile Number1 </label>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" class="form-control" required placeholder="Enter Mobile Number" data-ng-model="mobile1" />
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<label>	Mobile Number2 </label>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" class="form-control" placeholder="Enter Mobile Number"  data-ng-model="mobile2" />
				</div>
			</div>	



			<div class="row">
				<div class="col-md-3 col-sm-6 col-xs-12">
					<label> <span style="color:red;">*</span> E-mail Id1 </label>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" class="form-control" required placeholder="Enter E-mail Id1" data-ng-model="email1" />
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<label>	E-mail Id2</label>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" class="form-control" placeholder="Enter E-mail Id2"  data-ng-model="email2" />
				</div>
			</div>	
			
			
			
			<div class="row">
				<div class="col-md-3 col-sm-6 col-xs-12">
					<label> Phone Number1 </label>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" class="form-control" placeholder="Enter Phone Number1" data-ng-model="phone1" />
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<label>	Phone Number2</label>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" class="form-control" placeholder="Enter Phone Number2"  data-ng-model="phone2" />
				</div>
			</div>	
			
			
			<div class="row">
				<div class="col-md-3 col-sm-6 col-xs-12">
					<label> Fax No1 </label>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" class="form-control" placeholder="Enter Fax  Number1" data-ng-model="fax1" />
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<label>	Fax No2</label>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" class="form-control" placeholder="Enter Fax Number2" data-ng-model="fax2" />
				</div>
			</div>			
			
			
			
			<div class="row">
				<div class="col-md-3 col-sm-6 col-xs-12">
					<label><span style="color:red;">*</span> Address </label>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" class="form-control" required placeholder="Enter Address" data-ng-model="address" />
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<label>	<span style="color:red;">*</span> City</label>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" class="form-control" placeholder="Enter City Name"  required data-ng-model="city" />
				</div>
			</div>	
			
			
			<div class="row">
				<div class="col-md-3 col-sm-6 col-xs-12">
					<label><span style="color:red;">*</span> State </label>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" class="form-control" required placeholder="Enter State" data-ng-model="state" />
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<label>	<span style="color:red;">*</span> Pin</label>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" class="form-control" placeholder="Enter pin"  required data-ng-model="pin" />
				</div>
			</div>	
			
			
			
			<div class="row">
				<div class="col-md-3 col-sm-6 col-xs-12">
					<label>Pan card  </label>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" class="form-control" required placeholder="Enter Pan card" data-ng-model="pan" />
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<label>	Voter id</label>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" class="form-control" placeholder="Enter Voter id"  required data-ng-model="voterId" />
				</div>
			</div>	
			<hr>
			<div class="row">
				<div class="col-md-3 col-sm-6 col-xs-12">
					<label>Choose Pin  </label>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<select class="form-control" data-ng-model="choosePin">						
						<%=pins %>
					</select>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<label>	Choose Area</label>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" class="form-control" placeholder="Enter Area"  required data-ng-model="chooseArea" />
				</div>
			</div>	
			
			
			<div class="row">
				<div class="col-md-3 col-sm-6 col-xs-12" style="float: left;">
					<span style="color: red;margin-top:41px; display: block;" id="message"><!-- message --></span>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12" style="float: right;">
					<input type="submit" value="Submit" 
						style="width: 64% !important;padding: 5px 1px;background:linear-gradient(#54b4eb, #2fa4e7 60%, #1d9ce5);
						border: 1px solid #0098fe;color:#ffffff;margin-top:25px; margin-left:98px; font-size: 20px; margin-bottom: 15px;" 
						data-ng-click="<%=submitFunction %>"/>
				</div>
			</div>
			
		</div>  <!-- register_page -->


</div>  <!-- register_pannel -->


</body>  <!-- CreateEditUserController -->
</html>