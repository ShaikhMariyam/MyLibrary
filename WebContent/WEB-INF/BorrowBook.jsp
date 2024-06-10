<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="model.*" %>
    <%@ page import="controller.*" %>
    <%@ page import="java.util.List" %>
    <%@ page import="org.apache.commons.io.*" %>
	<%@ page import="java.io.InputStream" %>
	<%@ page import="org.apache.commons.codec.binary.Base64" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.0.0-beta3/css/bootstrap.min.css" integrity="sha512-N415hCJJdJx+1UBfULt+i+ihvOn42V/kOjOpp1UTh4CZ70Hx5bDlKryWaqEKfY/8EYOu/C2MuyaluJryK1Lb5Q==" crossorigin="anonymous" />
<title>Borrow Book</title>
</head>
<style>
img{
  display: block;
  margin-left: auto;
  margin-right: auto;
}
input[type=radio] {
  display: inline;
}
</style>
<div class="bg-image"
	style="background-image: url('https://i.ibb.co/9nfv7Dm/bg.png'); background-repeat: no-repeat; background-size: cover; height: 100vh">

<body>
		
<jsp:include page="navbar.jsp" />
<div class="container mt-5 mx-auto w-50 d-flex justify-content-center" style="max-width: 100%; width: 80%; box-sizing: border-box;"> 
  <div class="card" style="width:30rem; margin-left:auto; margin-right:auto;" >
  <%
  int bookId = 1;
  bookId = Integer.parseInt(request.getParameter("bookid"));

%>
  
  <% Book book = BorrowBookController.BookDB(bookId);
     InputStream inputStream = book.getBook_Image();
    byte[] imageBytes = IOUtils.toByteArray(inputStream);
    String base64EncodedString = Base64.encodeBase64String(imageBytes);%>
  
  <% Student borrowers = BorrowBookController.BorrowDB(1); %>
<%		if (borrowers != null){

		if (book != null){

			%>
			
			<% if (book.getBook_Copy() == 0) { %>

<% response.sendRedirect("/MyLibrary/books"); %> 
<script>
alert("There are no copies in the library.");
</script>
<% } else if(borrowers.getFine()>20){
  
%>
<script>
alert("Please, pay the fine first before borrowing a book.");
</script>
<%} else{ %>
<form action="/MyLibrary/BorrowBookController" method="POST">
	<img src="data:image/png;base64,<%=base64EncodedString%>" alt="Book1" width="150" height="200">    <div class="card-body ">
      <h6 class="card-title">Name</h6>
      		<p class="card-text"> <%= book.getBook_Name()%></p>
      <h6 class="card-title">Description</h6>
	  		<p class="card-text"><%=book.getBook_Description()%></p>
	  <h6 class="card-title">Serial Number</h6>
	  		<p class="card-text"><%=book.getBook_SerialNo()%></p>
	  <h6 class="card-title">Borrow Date</h6>
	  		 <input type="date" id="Bdate" name="Bdate">
	  		 <br><br>
	  <h6 class="card-title">Location</h6>
	  		<% if(book.getBook_Location().equals("KL")){ %>
	  		  <input type="radio" id="UTM_KL" name="Campus" value="KL" checked>
  			  <label for="UTM_KL">UTM KL</label>
			<% } else if(book.getBook_Location().equals("JB")){%>
  			  <input type="radio" id="UTM_JB" name="Campus" value="JB">
  			  <label for="UTM_JB">UTM JB</label>
			<% }else if(book.getBook_Location().equals("Both")){%>
			  <input type="radio" id="UTM_KL" name="Campus" value="KL" checked>
  			  <label for="UTM_KL">UTM KL</label>
  			  <input type="radio" id="UTM_JB" name="Campus" value="JB">
  			  <label for="UTM_JB">UTM JB</label>
    </div>
    <%}    bookId = book.getBook_ID();
}}}%>
    
         <div class="card-footer text-center">
                <input type="hidden" name="bookId" value="<%= bookId %>">
      <input type="submit" value="Borrow" class="btn btn-default bg-dark text-white shadow rounded px-5 me-auto"  tabindex="-1" aria-disabled="true">
 </div> 
  </form>
  </div>
  </div>
</div>
</body>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.0.0-beta3/js/bootstrap.min.js" integrity="sha512-mp3VeMpuFKbgxm/XMUU4QQUcJX4AZfV5esgX72JQr7H7zWusV6lLP1S78wZnX2z9dwvywil1VHkHZAqfGOW7Nw==" crossorigin="anonymous"></script>
</html>