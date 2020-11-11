<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Display All Loans</title>
</head>
<body>
	<!-- write code to display all the loan details 
             which are received from the admin controllers' listall method
	--> 
<jsp:include page="userhome1.jsp"/>
		
		<h3>Loan List</h3>
		<c:choose>
			<c:when test="${loanList==null || LoanList.isEmpty() }">
				<p>No Record Found!
			</c:when>
			<c:otherwise>
				<table>
					<thead>
						<tr>
							<th>Application#</th>
							<th>amountRequest</th>
							<th>doa</th>
							<th>bstructure</th>
							<th>bindicator</th>
							<th>address</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="b" items="${loanListList }">
							<tr>
								<td>${b.Application }</td>
								<td>${b.amountRequest }</td>
								<td>${b.doa }</td>
								<td>${b.bstructure }</td>
								<td>${b.bindicator }</td>
								<td>${b.address }</td>
								<td>
									<a href="editBook?bcode=${b.application }">EDIT</a>
									<span>|</span>
									<a href="deleteBook?bcode=${b.application }">DELETE</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:otherwise>
		</c:choose>
</body>
</html>