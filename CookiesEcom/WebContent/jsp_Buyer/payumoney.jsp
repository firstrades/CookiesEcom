<%@page import="ecom.common.FrequentUse"%>
<%@page import="ecom.model.Order"%>
<%@page import="ecom.model.User"%>
<%@page import="ecom.beans.BankTransaction"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Transfer to PayUMoney</title>
<script type="text/javascript" src="<%=FrequentUse.jQuery %>"></script>
</head>
<%

	BankTransaction bankTransaction = BankTransaction.getNewInstance();
	
    // Get Session
	User  user  = (User)  session.getAttribute("user" );
	Order order = (Order) session.getAttribute("order");
	
	// Get Request
	String orderId = (String) request.getAttribute("orderId");
	
	String hashString       = null;
	String key              = FrequentUse.payUMoneyKey;
	String salt             = FrequentUse.payUMoneySalt;
	String txnid            = orderId;  
	double productamount    = order.getTotalSellPrice();
	String productinfo      = "Various Products";
	String firstname        = order.getDeliveryAddress().getfName();
	String email            = order.getDeliveryAddress().getEmail();
	String phone            = order.getDeliveryAddress().getContact();	
	String service_provider = FrequentUse.payUMoneyServiceProvider;
	String surl             = getSuccessPath(request); //"http://localhost:8080/E-Commerce/" + "SuccessServlet";  
	String furl             = getFailurePath(request); //"http://localhost:8080/E-Commerce/" + "FailureServlet";  
	
	
	hashString = ""+key+"|"+txnid+"|"+productamount+"|"+productinfo+"|"+firstname+"|"+email+"|||||||||||"+salt+"";
	String hash = bankTransaction.hashCalculation("SHA-512", hashString);

%>
<body>

<div style="display: none;">
<form action="https://test.payu.in/_payment" method="post">

	<input type="hidden" name="key"         value="<%=key %>" />
	<input type="hidden" name="salt"        value="<%=salt %>" />
	<input type="hidden" name="txnid"       value="<%=txnid %>" />
	<input type="hidden" name="amount"      value="<%=productamount %>" />
	<input type="hidden" name="productinfo" value="<%=productinfo %>" />
	<input type="hidden" name="firstname"   value="<%=firstname %>" />
	<input type="hidden" name="email"       value="<%=email %>" />
	<input type="hidden" name="phone"       value="<%=phone %>" />
	<input type="hidden" name="surl"        value="<%=surl %>" />
	<input type="hidden" name="furl"        value="<%=furl %>" />
	<input type="hidden" name="service_provider" value="<%=service_provider %>" />
	<input type="hidden" name="hash"        value="<%=hash %>" />
	
	<input type="submit" name="Submit" id="submit" />

</form>
</div>


<script>
$(function() {
	$('#submit').trigger('click');
});

</script>


<%!


	public String getSuccessPath(HttpServletRequest request) {
	
		StringBuilder path = new StringBuilder();
		
		path.append("http://");
		path.append(request.getServerName());
		path.append(":");
		path.append(request.getServerPort());		
		path.append(request.getContextPath());
		path.append("/SuccessServlet");
		
		return path.toString();
	}


	public String getFailurePath(HttpServletRequest request) {
		
		StringBuilder path = new StringBuilder();
		
		path.append("http://");
		path.append(request.getServerName());
		path.append(":");
		path.append(request.getServerPort());		
		path.append(request.getContextPath()); System.out.println(request.getContextPath());
		path.append("/FailureServlet");
		
		return path.toString();
	}




%>

</body>
</html>