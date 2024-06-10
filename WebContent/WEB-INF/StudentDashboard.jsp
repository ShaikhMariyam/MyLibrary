<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ page import="controller.*" %>
<%@ page import="model.*" %>
<%@ page import="java.util.List" %>
<%@ page import="org.apache.commons.io.*" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="org.apache.commons.codec.binary.Base64" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.0.0-beta3/css/bootstrap.min.css"
	integrity="sha512-N415hCJJdJx+1UBfULt+i+ihvOn42V/kOjOpp1UTh4CZ70Hx5bDlKryWaqEKfY/8EYOu/C2MuyaluJryK1Lb5Q=="
	crossorigin="anonymous" />
<title>Student Dashboard</title>
</head>
<div class="bg-image"
	style="background-image: url('https://i.ibb.co/9nfv7Dm/bg.png'); height: 100%; background-repeat: no-repeat; background-size: cover;">

	<body>

		<jsp:include page="navbar.jsp" />
		<div class="container mt-5 mx-auto w-50">
			<div class="card shadow">
				<div class="card-header text-center bg-white border-0">
					<h1>My books</h1>
				</div>
				<div class="card-body ">
					<div class="row">
<% 
String email = (String) request.getSession().getAttribute("email");
List<borrowersList> borrowers = studentDashboardController.allbooks(email);
if (borrowers == null){
	%>
	  <p class="text-center">No books in the database</p>
	
	<%
} else{
for(borrowersList borrower : borrowers){
	int BID = borrower.getBookID();
	Book book = IssueBookController.BookfromDB(BID);
%>
					<%
    InputStream inputStream = book.getBook_Image();
    byte[] imageBytes = IOUtils.toByteArray(inputStream);
    String base64EncodedString = Base64.encodeBase64String(imageBytes);
%>
							<div class="col-md-4">
								<div class="card">
									<h5 class="card-header">
										
									</h5>
									<form action="/MyLibrary/IssueBookController" method="post">
									<img src="data:image/png;base64,<%=base64EncodedString%>" style="margin-left: auto; margin-right: auto; display: block;" alt="Book1" width="150">
									<div class="card-body">
										<h5 class="card-title">Name: <%=book.getBook_Name()%></h5>
										<p class="card-text">Serial Number: <%=book.getBook_SerialNo()%></p>
										<p class="card-text">Status: <%=borrower.getBookStatus()%></p>
									</div>
									</form>
								</div>
							</div>
<%
}
} 
%>

</div></div></div></div>			<div style="height: 15px;"></div>
	</body>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.0.0-beta3/js/bootstrap.min.js"
	integrity="sha512-mp3VeMpuFKbgxm/XMUU4QQUcJX4AZfV5esgX72JQr7H7zWusV6lLP1S78wZnX2z9dwvywil1VHkHZAqfGOW7Nw=="
	crossorigin="anonymous"></script>

</html>