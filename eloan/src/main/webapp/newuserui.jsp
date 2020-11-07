<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>NewUserForm</title>
</head>
<body>
	<!-- read user name and password from user to create account
	     and send to usercontrollers registeruser method
	-->
	
	<nav>
	<jsp:include page="header.jsp"/>
		
		<h3>${isNew?'New':'Edit' } NewUserForm</h3>
		<form action="${isNew?'addContact':'saveContact' }" method="POST">
		
		<a href="index.jsp">Home</a> 
		<span>|</span>

		<%-- <c:choose>
			<c:when test="${user==null }">
				<a href="login">LogIn</a>
				<span>|</span>
				<a href="register">Register</a>
			</c:when>
			<c:when test="${user.role=='ADMIN' }">
				<a href="listContacts">All Contacts</a>
				<span>|</span> 
				<a href="newContact">New Contact</a>
				<span>|</span> 
				<a href="logout">LogOut</a>
			</c:when>
			<c:when test="${user.role=='USER' }">
				<a href="listContacts">All Contacts</a>
				<span>|</span> 
				<a href="logout">LogOut</a>	
			</c:when>
		</c:choose> --%>
	</nav>
</head>
<body>
<h1>Bank Registeration Form</h1>
<form action="UserController" method="post">
			<table style="with: 50%">
				<tr>
					<td>First Name</td>
					<td><input type="text" name="first_name" required/></td>
				</tr>
				<tr>
					<td>Last Name</td>
					<td><input type="text" name="last_name" required/></td>
				</tr>
				<tr>
					<td>UserName</td>
					<td><input type="text" name="username" required/></td>
				</tr>
					<tr>
					<td>Password</td>
					<td><input type="password" name="password" required/></td>
				</tr>
				<tr>
					<td>Address</td>
					<td><input type="text" name="address" required/></td>
				</tr>
				<tr>
					<td>Contact No</td>
					<td><input type="text" name="contact" required/></td>
				</tr></table>
			<input type="submit" value="Submit" /></form>
</body>
</html>



