<%@page import="ecom.model.UserAndPickupAddress"%>
<%@page import="ecom.common.FrequentUse"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html >
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Seller Registration Page</title>
	<script type="text/javascript" src="<%=FrequentUse.angular %>"></script>	
	<script type="text/javascript" src="<%=FrequentUse.jQuery %>"></script>
	<script type="text/javascript" src="js_Administration/Admin.js"></script>
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

<%
	UserAndPickupAddress userAndPickupAddress = (UserAndPickupAddress) request.getAttribute("userAndPickupAddress");

	long   id             = userAndPickupAddress.getUser().getUserInfo().getId       ();
	String userId         = userAndPickupAddress.getUser().getLogin()   .getUserId   ();
	String firstName      = userAndPickupAddress.getUser().getPerson()  .getFirstName();
	String lastName       = userAndPickupAddress.getUser().getPerson()  .getLastName ();
	String company        = userAndPickupAddress.getUser().getUserInfo().getCompany  ();
	String addressLine1   = userAndPickupAddress.getUser().getAddress() .getAddress  ();
	String addressLine2   = userAndPickupAddress.getUser().getAddress() .getAddress1 ();
	String pin            = userAndPickupAddress.getUser().getAddress() .getPin      ();
	String city           = userAndPickupAddress.getUser().getAddress() .getCity     ();
	String state          = userAndPickupAddress.getUser().getAddress() .getState    ();
	String country        = userAndPickupAddress.getUser().getAddress() .getCountry  ();
	String sex            = userAndPickupAddress.getUser().getPerson()  .getSex      ();
	String mobile1        = userAndPickupAddress.getUser().getContact() .getMobile1  ();
	String mobile2        = userAndPickupAddress.getUser().getContact() .getMobile2  ();
	String email1         = userAndPickupAddress.getUser().getContact() .getEmail1   ();
	String email2         = userAndPickupAddress.getUser().getContact() .getEmail2   ();
	String phone1         = userAndPickupAddress.getUser().getContact() .getPhone1   ();
	String phone2         = userAndPickupAddress.getUser().getContact() .getPhone2   ();
	String fax1           = userAndPickupAddress.getUser().getContact() .getFax1     ();
	String fax2           = userAndPickupAddress.getUser().getContact() .getFax2     ();
	String pan            = userAndPickupAddress.getUser().getUserInfo().getPan      ();
	String voterId        = userAndPickupAddress.getUser().getUserInfo().getVoterId  ();
	
	
	long   daId           = userAndPickupAddress.getDeliveryAddress().getId      ();	
	String daFirstName    = userAndPickupAddress.getDeliveryAddress().getfName   ();
	String daLastName     = userAndPickupAddress.getDeliveryAddress().getlName   ();
	String daCompany      = userAndPickupAddress.getDeliveryAddress().getCompany ();
	String daAddressLine1 = userAndPickupAddress.getDeliveryAddress().getAddress ();
	String daAddressLine2 = userAndPickupAddress.getDeliveryAddress().getAddress1();
	String daCity         = userAndPickupAddress.getDeliveryAddress().getCity    ();
	String daPin          = userAndPickupAddress.getDeliveryAddress().getPin     ();
	String daState        = userAndPickupAddress.getDeliveryAddress().getState   ();
	String daMobile       = userAndPickupAddress.getDeliveryAddress().getContact ();
	String daEmail        = userAndPickupAddress.getDeliveryAddress().getEmail   ();
	String daCountry      = userAndPickupAddress.getDeliveryAddress().getCountry ();
	
	
	
	String state1   = getStateName(state);
	String daState1 = getStateName(daState);
%>


<body >

<div class="register_pannel">

		<div class="logo_pannel">
		 	<a href="index.html"> <img src="images/logoFirstTrade.png" /> </a>
		</div>

		<div class="register_page">


			<div class="tag_pannel"> <h1> APPROVAL - SELLER REGISTRATION </h1> </div>
			<hr>
			
			
			
			
		<form method="post" enctype="multipart/form-data" id="data">
		
		
			<input type="hidden" name="id" value="<%=id %>">
			
			<!-- ----------------------- Main Data ------------------------------------------ -->

			<div class="row">
				<div class="col-md-3 col-sm-6 col-xs-12">
					<label> *User Id  </label>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" name="userId" class="form-control" value="<%=userId %>" placeholder="User Id" required />
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<label>	*Password </label>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="password" name="" class="form-control"  placeholder="Password" readonly  />
				</div>
			</div>	
			
			
			
			<div class="row">
				<div class="col-md-3 col-sm-6 col-xs-12">
					<label> *First name </label>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" name="firstName" class="form-control" value="<%=firstName %>" placeholder="Enter First Name" required />
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<label>	*Last Name </label>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" name="lastName" class="form-control" value="<%=lastName %>" placeholder="Enter Last Name"  required  />
				</div>
			</div>		


			<div class="row">
				<div class="col-md-3 col-sm-6 col-xs-12">
					<label> *Sex </label>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<select name="sex" id="" class="form-control" style="text-transform: uppercase;">						
						<option value="<%=sex  %>"><%=sex  %></option>
						<% if (sex.equalsIgnoreCase("male")) { %>
						<option value="female">Female</option>
						<% } %>
						<% if (sex.equalsIgnoreCase("female")) { %>
						<option value="male">Male</option>
						<% } %>
					</select>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<label>	*Company  </label>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" name="company" class="form-control" value="<%=company %>" placeholder="Enter Company Name"  required  />
				</div>
			</div>	


			<div class="row">
				<div class="col-md-3 col-sm-6 col-xs-12">
					<label> *Mobile Number1 </label>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" name="mobile1" class="form-control" value="<%=mobile1 %>" placeholder="Enter Mobile Number" required />
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<label>	Mobile Number2 </label>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" name="mobile2" class="form-control" value="<%=mobile2 %>" placeholder="Enter Last Name"  />
				</div>
			</div>	



			<div class="row">
				<div class="col-md-3 col-sm-6 col-xs-12">
					<label> *E-mail 1 </label>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" name="email1" class="form-control" value="<%=email1 %>" placeholder="Enter E-mail Id1" required />
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<label>	E-mail 2</label>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" name="email2" class="form-control" value="<%=email2 %>" placeholder="Enter E-mail Id2"   />
				</div>
			</div>	
			
			
			
			<div class="row">
				<div class="col-md-3 col-sm-6 col-xs-12">
					<label> *Phone 1 </label>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" name="phone1" class="form-control" value="<%=phone1 %>" placeholder="Enter Phone Number1" required />
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<label>	Phone 2</label>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" name="phone2" class="form-control" value="<%=phone2 %>" placeholder="Enter Phone Number2"  />
				</div>
			</div>	
			
			
			<div class="row">
				<div class="col-md-3 col-sm-6 col-xs-12">
					<label> Fax 1 </label>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" name="fax1" class="form-control" value="<%=fax1 %>" placeholder="Enter Fax  Number1"  />
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<label>	Fax 2</label>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" name="fax2" class="form-control" value="<%=fax2 %>" placeholder="Enter Fax Number2"  />
				</div>
			</div>			
			
			
			
			<div class="row">
				<div class="col-md-3 col-sm-6 col-xs-12">
					<label>*Address Line 1 </label>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" name="addressLine1" class="form-control" value="<%=addressLine1 %>" placeholder="Enter Address" required />
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<label>	Address Line 2</label>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" name="addressLine2" class="form-control" value="<%=addressLine2 %>" placeholder="Enter City Name"   />
				</div>
			</div>	
			
			
			<div class="row">
				<div class="col-md-3 col-sm-6 col-xs-12">
					<label>*City </label>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" name="city" class="form-control" value="<%=city %>" placeholder="Enter State" required />
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<label> *State </label>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<select name="state" id="state1" class="form-control" style="text-transform: uppercase;">						
						<option value="<%=state %>"><%=state1 %></option>						
					</select>
				</div>
			</div>	
			
			
			
			<div class="row">
				<div class="col-md-3 col-sm-6 col-xs-12">
					<label>*Pin </label>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" name="pin" class="form-control" value="<%=pin %>" placeholder="Enter Pan card" required />
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<label> Country </label>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<select name="country" id="country1" class="form-control" style="text-transform: uppercase;">								
						<option value="<%=country %>">India</option>								
					</select>
				</div>
			</div>	
			
			
			<div class="row">
				<div class="col-md-3 col-sm-6 col-xs-12">
					<label>Pan card  </label>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" name="pan" class="form-control" value="<%=pan %>" placeholder="Enter Pan card"  />
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<label>	Voter id</label>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" name="voterId" class="form-control" value="<%=voterId %>" placeholder="Enter Voter id"   />
				</div>
			</div>	
			
			
			<div class="row">
				<div class="col-md-3 col-sm-6 col-xs-12">
					<label>Demo----  </label>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" name="" class="form-control"  placeholder="Enter Pan card"  />
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<label>	Demo----</label>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" name="" class="form-control" placeholder="Enter Voter id"   />
				</div>
			</div>	
			
			
			
			
			
			<!-- ------------------------------- End Main Data ------------------------------------------ -->
			
			
			
			
			
			
			
			
			
			<!-- ----------------------- CheckBox for Aux Data ------------------------------------------ -->
			
				<div class="row">
			
				<div class="col-md-12 col-sm-6 col-xs-12" style="font-size: 17px;color: #00405d;font-weight: bold;margin-bottom: 34px;margin-top: 16px;">
					
					&nbsp; Product Pickup Address
				</div>
				
			</div>	
			
			<!-- ----------------------- End CheckBox for Aux Data ------------------------------------------ -->
			
			
			
			
			
			
			
			
			
			
			<!-- ---------------------------------- Aux Data -------------------------------------------------- -->
			
			<div id="aux" style="display: block;">
			
					<div class="row">
						<div class="col-md-3 col-sm-6 col-xs-12">
							<label>*First Name  </label>
						</div>
						<div class="col-md-3 col-sm-6 col-xs-12">
							<input type="text" name="daFirstName" class="form-control" value="<%=daFirstName %>" placeholder="Enter Pan card" required />
						</div>
						<div class="col-md-3 col-sm-6 col-xs-12">
							<label>	*Last Name</label>
						</div>
						<div class="col-md-3 col-sm-6 col-xs-12">
							<input type="text" name="daLastName" class="form-control" value="<%=daLastName %>" placeholder="Enter Voter id"  required  />
						</div>
					</div>	
					
					
					
					<div class="row">
						<div class="col-md-3 col-sm-6 col-xs-12">
							<label>*Company  </label>
						</div>
						<div class="col-md-3 col-sm-6 col-xs-12">
							<input type="text" name="daCompany" class="form-control" value="<%=daCompany %>" placeholder="Enter Pan card" required />
						</div>
						<div class="col-md-3 col-sm-6 col-xs-12">
							<label>	*Contact</label>
						</div>
						<div class="col-md-3 col-sm-6 col-xs-12">
							<input type="text" name="daContact" class="form-control" value="<%=daMobile %>" placeholder="Enter Voter id"  required />
						</div>
					</div>	
					
					
					
					<div class="row">
						<div class="col-md-3 col-sm-6 col-xs-12">
							<label>*Address Line 1 </label>
						</div>
						<div class="col-md-3 col-sm-6 col-xs-12">
							<input type="text" name="daAddressLine1" class="form-control" value="<%=daAddressLine1 %>" placeholder="Enter Pan card" required />
						</div>
						<div class="col-md-3 col-sm-6 col-xs-12">
							<label>	Address Line 2</label>
						</div>
						<div class="col-md-3 col-sm-6 col-xs-12">
							<input type="text" name="daAddressLine2" class="form-control" value="<%=daAddressLine2 %>" placeholder="Enter Voter id"   />
						</div>
					</div>	
					
					
					
					<div class="row">
						<div class="col-md-3 col-sm-6 col-xs-12">
							<label>*City  </label>
						</div>
						<div class="col-md-3 col-sm-6 col-xs-12">
							<input type="text" name="daCity" class="form-control" value="<%=daCity %>" placeholder="Enter Pan card" required />
						</div>
						<div class="col-md-3 col-sm-6 col-xs-12">
							<label>	*Pin</label>
						</div>
						<div class="col-md-3 col-sm-6 col-xs-12">
							<input type="text" name="daPin" class="form-control" value="<%=daPin %>" placeholder="Enter Voter id"  required  />
						</div>
					</div>	
					
					
					
					<div class="row">
						<div class="col-md-3 col-sm-6 col-xs-12">
							<label> *State </label>
						</div>
						<div class="col-md-3 col-sm-6 col-xs-12">
							<select name="daState" id="" class="form-control" style="text-transform: uppercase;">
								<option value="<%=daState %>"><%=daState1 %></option>
							</select>
						</div>
						<div class="col-md-3 col-sm-6 col-xs-12">
							<label> Country </label>
						</div>
						<div class="col-md-3 col-sm-6 col-xs-12">
							<select name="daCountry" id="country2" class="form-control" style="text-transform: uppercase;">								
								<option value="<%=daCountry %>">India</option>								
							</select>
						</div>
					</div>	
					
					
					<div class="row">
						<div class="col-md-3 col-sm-6 col-xs-12">
							<label>*Email  </label>
						</div>
						<div class="col-md-3 col-sm-6 col-xs-12">
							<input type="text" name="daEmail" class="form-control" value="<%=daEmail %>" placeholder="Enter Pan card" required />
						</div>
						<div class="col-md-3 col-sm-6 col-xs-12">
							<label>	Demo----</label>
						</div>
						<div class="col-md-3 col-sm-6 col-xs-12">
							<input type="text" name="product" class="form-control" placeholder="Enter Voter id"    />
						</div>
					</div>	
					
					
					
					
			</div>
			
			<!-- ---------------------------------- End Aux Data -------------------------------------------------- -->
			
			
			
			<!-- ---------------------------------- Submit -------------------------------------------------- -->
			
			
			<div class="row">				
				<div class="col-md-3 col-sm-6 col-xs-12" style="float: right;">
					<input type="submit" value="Approve" 
						style="width: 64% !important;padding: 5px 1px;background:linear-gradient(#54b4eb, #2fa4e7 60%, #1d9ce5);
						border: 1px solid #0098fe;color:#ffffff;margin-top:25px; margin-left:98px; font-size: 20px; margin-bottom: 15px;" />
				</div>
			</div>
			
			<!-- ---------------------------------- End Submit -------------------------------------------------- -->
			
			
			
			
		</form>	
			
			
			<!-- ---------------------------------- Message -------------------------------------------------- -->
			
			<div id="message" style="margin-top: -44px;
				margin-right: 535px;float: right;font-size: 30px;color: red;">
				<!-- Ajax Data -->
			</div>
			
			<!-- ---------------------------------- End Message -------------------------------------------------- -->
			
			
		</div>  <!-- register_page -->


</div>  <!-- register_pannel -->


</body>  <!-- CreateEditUserController -->


<%!

	private String getStateName(String achronym) {
	
		String stateName = null;
		
		if (achronym.equals("AN"))
			stateName = "Andaman and Nicobar Islands";
		
		if (achronym.equals("AP"))
			stateName = "Andhra Pradesh";
		
		if (achronym.equals("AR"))
			stateName = "Arunachal Pradesh";
		
		if (achronym.equals("AS"))
			stateName = "Assam";
		
		if (achronym.equals("BR"))
			stateName = "Bihar";
		
		if (achronym.equals("CH"))
			stateName = "Chandigarh";
		
		if (achronym.equals("CG"))
			stateName = "Chhattisgarh";
		
		if (achronym.equals("DH"))
				stateName = "Dadra and Nagar Haveli";
		
		if (achronym.equals("DD"))
			stateName = "Daman and Diu";
		
		if (achronym.equals("DL"))
			stateName = "Delhi";
		
		if (achronym.equals("GA"))
			stateName = "Goa";
		
		if (achronym.equals("GJ"))
			stateName = "Gujarat";
		
		if (achronym.equals("HR"))
			stateName = "Haryana";
		
		if (achronym.equals("HP"))
			stateName = "Himachal Pradesh";
		
		if (achronym.equals("JK"))
			stateName = "Jammu And Kashmir";
		
		if (achronym.equals("JH"))
			stateName = "Jharkhand";
		
		if (achronym.equals("KA"))
			stateName = "Karnataka";
		
		if (achronym.equals("KL"))
			stateName = "Kerala";
		
		if (achronym.equals("LD"))
			stateName = "Lakshadweep";
		
		if (achronym.equals("MP"))
			stateName = "Madhya Pradesh";
		
		if (achronym.equals("MH"))
			stateName = "Maharashtra";
		
		if (achronym.equals("MN"))
			stateName = "Manipur";
		
		if (achronym.equals("ML"))
			stateName = "Meghalaya";
		
		if (achronym.equals("MZ"))
			stateName = "Mizoram";
		
		if (achronym.equals("NL"))
			stateName = "Nagaland";
		
		if (achronym.equals("OR"))
			stateName = "Orissa";
		
		if (achronym.equals("PY"))
			stateName = "Pondicherry";
		
		if (achronym.equals("PB"))
			stateName = "Punjab";
		
		if (achronym.equals("RJ"))
			stateName = "Rajasthan";
		
		if (achronym.equals("SK"))
			stateName = "Sikkim";
		
		if (achronym.equals("TN"))
			stateName = "Tamilnadu";
		
		if (achronym.equals("TR"))
			stateName = "Tripura";
		
		if (achronym.equals("UK"))
			stateName = "Uttarakhand (Uttaranchal)";
		
		if (achronym.equals("UP"))
			stateName = "Uttar Pradesh";
		
		if (achronym.equals("WB"))
			stateName = "West Bengal";
		
		
		return stateName;
	}



%>


</html>
