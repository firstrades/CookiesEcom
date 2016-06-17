<%@page import="ecom.common.FrequentUse"%>
<%@page import="ecom.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error Message</title>
	<link href="font-awesome/css/font-awesome.css" rel='stylesheet' type='text/css' />
	<link href="font-awesome/css/font-awesome.min.css" rel='stylesheet' type='text/css' />	
	<link href="css/bootstrap.css" rel='stylesheet' type='text/css' />	
	<link href="css/megamenu.css" rel="stylesheet" type="text/css" media="all" />
	<link href="<%=FrequentUse.style %>" rel='stylesheet' type='text/css' />	
	
	<meta name="viewport" content="width=device-width, initial-scale=1"> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="keywords" content="First Trades Online Shoppin" />	
	
	<script type="text/javascript" src="<%=FrequentUse.jQuery %>"></script>	
	<script type="text/javascript" src="js/megamenu.js"></script>
	<script>	
		$(document).ready(function(){$(".megamenu").megamenu();});
	</script>
	<script src="js/menu_jquery.js"></script>
	<script src="js/simpleCart.min.js"> </script>
	<script src="js/bootstrap.min.js"></script>
	
</head>
<body>
<%
	String errorMessage = (String) request.getAttribute("errorMessage");
	User user = (User) session.getAttribute("user");
%>

<% if (user == null) { %>

	<!-- Header -->
	<%@ include file="../jsp_Buyer/HeaderAllUser.jsp" %>
	<!-- End Header -->

<% } else { %>

	<!-- Header -->
	<%@ include file="../jsp_Buyer/Header.jsp" %>
	<!-- End Header -->

<% } %>


<!-- Navigation -->
<%@ include file="../jsp_Buyer/Navigation.jsp" %>
<!-- End Navigation -->



<div class="container">
<div style="margin:75px;background: #F4CB7A;color: #EE162C;line-height: 35px;border-radius: 8px;">	
	<%=errorMessage %>
</div>
</div>







<%@ include file="../jsp_Buyer/Footer.jsp" %>



</body>
</html>