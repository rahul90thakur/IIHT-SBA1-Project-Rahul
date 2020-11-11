<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Loan Application Form</title>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
</head>
<body onload="myFunction()">

<!--
	write the html code to accept laon info from user and send to placeloan servlet
-->
<jsp:include page="header.jsp"/>
		
		<h3>${(isNew?"New":"Edit") } LoanInfo</h3>
		
				<label>Loan information Code</label>
				<input type="number" name="applno" required 
				value="${LoanInfo.applno}" ${isNew?'':'readonly' }/>
			<div>
				<label>purpose</label>
				<input type="text" name="purpose" required value="${LoanInfo.purpose}"/>
			</div>
			<div>
				<label>Amount</label>
				<input type="number" step="0.01" name="amtrequest" required value="${LoanInfo.amtrequest}"/>
			</div>
			<div>
				<label>Doa</label>
				<input type="date" name="doa" required value="${LoanInfo.doa}"/>
			</div>
			<div>
				<label>Bank Structure</label>
				<input type="number" name="bstructure" required value="${LoanInfo.bstructure}"/>
			</div>
			<div>
				<label>Bank Indicator</label>
				<input type="number" name="bindicator" required value="${LoanInfo.bindicator}"/>
			</div>
			<div>
				<label>Address</label>
				<input type="number" name="address" required value="${LoanInfo.address}"/>
			</div>
			<div>
				<label>email</label>
				<input type="text" name="email" required value="${LoanInfo.email}"/>
			</div>
			<div>
				<label>mobile</label>
				<input type="text" name="email" required value="${LoanInfo.mobile}"/>
			</div>
			<div>
				<label>status</label>
				<input type="text" name="status" required value="${LoanInfo.status}"/>
			</div>
			<div>
				<button>SAVE</button>
			</div>
		</form>
</script>


</body>
</html>