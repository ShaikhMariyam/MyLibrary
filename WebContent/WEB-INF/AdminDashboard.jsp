<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="model.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="controller.*" %>
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
<title>Admin Dashboard</title>
<style>
.center {
	text-align: center;
	width: 100%;
	    
}
.card {
	min-height: 200px;
	min-width: 200px;
}
table {
	border: ridge 5px #FFF;
	background-color: #F6F6F6;
	color: #000000;
}
table th {
	border: inset 1px #000;
	background-color: #FFD700;
}
table td {
	border: inset 1px #000;
}
button {
	border: white;
}
</style>
</head>

<div class="bg-image"
	style="background-image: url('https://i.ibb.co/9nfv7Dm/bg.png'); height: 100vh; background-repeat: no-repeat; background-size: cover;">

	<body>
	<jsp:include page="navbarA.jsp" />

		</br>

		<table id="BorrowedB" class="center"
			style="width: 80%; margin-left: auto; margin-right: auto;">
			<tr>
				<th>Book</th>
				<th>Book ID</th>
				<th>Book Serial Number</th>
				<th>Book Name</th>
				<th>Campus</th>
				<th>Edit Book</th>
			</tr>        
		
<% 
List<Book> books = ADController.BookfromDB();
if (books != null){
for(Book book : books){
	 InputStream inputStream = book.getBook_Image();
	    byte[] imageBytes = IOUtils.toByteArray(inputStream);
	    String base64EncodedString = Base64.encodeBase64String(imageBytes);
%>
<tr>
    <td><img src="data:image/png;base64,<%=base64EncodedString%>" width="50" height="50"></td>
    <td><%= book.getBook_ID()%></td>
    <td><%=book.getBook_SerialNo() %></td>
    <td><%=book.getBook_Name() %></td>
    <td><%=book.getBook_Location() %></td>
<% request.setAttribute("id", book.getBook_ID()); %>

                <td>
                    <button id="UpdateB" onclick="updateBook(<%=book.getBook_ID()%>)" style="outline: none;">
  <iconify-icon icon="mdi:pencil-box-outline" style="color: gold;" width="35"></iconify-icon>
</button>

                   <button id="DeleteB" onclick="deleteBook(<%= book.getBook_ID()%>)"style="outline: none;">
   <iconify-icon icon="ic:round-delete" style="color: black;" width="35"></iconify-icon>
</button>
                     <button id="BorrowList" onclick="ViewBorrowerList(<%=book.getBook_ID()%>)">
                        <iconify-icon icon="ic:sharp-person" style="color: darkblue;" width="35"></iconify-icon>
                    </button>
                </td>
        </tr>
		
<%}%>
</table>
<%} %>

	</body>
</div>
<script>
function updateBook(bookId) {
    window.location.href = "EditBook.jsp?id=" + bookId;
}
function ViewBorrowerList(bookId) {
    window.location.href = "Borrowers?id=" + bookId;
}
function deleteBook(bookId) {
	  var result = confirm("Are you sure you want to delete this book?");
	  if (result) {
	    window.location.href = "deleteControllers?id=" + bookId;
	  }
	}

</script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.0.0-beta3/js/bootstrap.min.js"
	integrity="sha512-mp3VeMpuFKbgxm/XMUU4QQUcJX4AZfV5esgX72JQr7H7zWusV6lLP1S78wZnX2z9dwvywil1VHkHZAqfGOW7Nw=="
	crossorigin="anonymous"></script>
<script
	src="https://code.iconify.design/iconify-icon/1.0.2/iconify-icon.min.js"></script>