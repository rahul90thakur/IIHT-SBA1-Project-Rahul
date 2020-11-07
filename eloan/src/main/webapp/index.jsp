<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>eLoan system</title>
</head>
<body>
	<!-- write the html code to read user credentials and send it to validateservlet
	    to validate and user servlet's registernewuser method if create new user
	    account is selected
	-->
		<h3><%=LocalDate.now() %></h3>

		<h1>IBS BANK</h1>
		<a href="index.jsp">Home</a>
	<span>|</span>
	<a href="newuserui.jsp">Register New User</a>
	<span>|</span>
	<a href="register.jsp">User Sign In</a>
	<span>|</span>
	<a href="register.jsp">Admin Sign In</a>
	<span>|</span>
		<%-- <jsp:include page="userhome1.jsp"/> --%>

		

		<h2>Welcome to IBS Bank</h2>

		<p>

		 IBS Bank provides features like easy fund transfer, services provided for bills etc..		
				
		 </p>
		 <!-- <p style="border:2px; border-style:solid;height:50px;width:1400px;float:left; border-color:#0000A0; font-weight:bold;padding: 1em;"><br/>New User Sign up</p>

		<p style="border:2px; border-style:solid;height:50px;width:140px;float:right;margin-right:900px; border-color:#0000A0;font-weight:bold; padding: 1em;"><br/>Admin Sign In</p>
		
		 <p style="border:2px; border-style:solid;height:50px;width:140px;float:left; border-color:#0000A0; font-weight:bold;padding: 1em;"><br/>Existing User Sign In</p>
 -->
		
		
</body>
</html>