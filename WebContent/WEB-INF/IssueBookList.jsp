<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
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
    <title>Issue Book List</title>
  </head>

  <div class="bg-image" style="background-image: url('https://i.ibb.co/9nfv7Dm/bg.png');
            height: 100%; background-repeat: no-repeat; background-size: cover;
            ">

    <body>

      <jsp:include page="navbarA.jsp" />
      <div class="container mt-5 mx-auto w-50">
        <div class="card shadow">
          <div class="card-header text-center bg-white border-0">
            <h1>Borrow Requests</h1>
          </div>
          <div class="card-body ">
<% List<borrowersList> borrowers = IssueBookController.borrowrequests();
if (borrowers != null){
%>
<div class="row">
<%
for(borrowersList borrower : borrowers){
	int BID = borrower.getBookID();
	Book book = IssueBookController.BookfromDB(BID);
    InputStream inputStream = book.getBook_Image();
    byte[] imageBytes = IOUtils.toByteArray(inputStream);
    String base64EncodedString = Base64.encodeBase64String(imageBytes);
	int SID = borrower.getStudentID();
	Student person = IssueBookController.StudentfromDB(SID);
	if (person != null){
%>
  <div class="col-sm-6">
<div class="card">
  <h5 class="card-header"><img src="data:image/png;base64,<%=base64EncodedString%>" style="margin-left: auto; margin-right: auto; display: block;" alt="Book1" width="150"></h5>
  <div class="card-body">
    <h5 class="card-title"><%= book.getBook_Name() %></h5>
    <p class="card-text">Status: <%= borrower.getBookStatus() %></p>
    <p class="card-text">Student Name: <%= person.getFirstName() + " " + person.getLastName() %></p>
    <p class="card-text">Matric: <%= person.getMatricNo() %></p>
    <p class="card-text">Date: <%= borrower.getBorrowDate() %></p>
<div style="text-align:center;">
<form action="/MyLibrary/IssueBookController" method="post">
   <input type="hidden" name="studentid" value="<%= borrower.getStudentID() %>">
   <input type="hidden" name="bookid" value="<%= borrower.getBookID() %>">
   <input type="hidden" name="action" value="issue">
   <input type="submit" value="Issue Book" class="btn btn-primary" tabindex="-1" aria-disabled="true">
</form>
<br>
<form action="/MyLibrary/IssueBookController" method="post">
   <input type="hidden" name="studentid" value="<%= borrower.getStudentID() %>">
   <input type="hidden" name="bookid" value="<%= borrower.getBookID() %>">
   <input type="hidden" name="action" value="reject">
   <input type="submit" value="Reject Request" class="btn btn-primary" tabindex="-1" aria-disabled="true">
</form>
</div>
</div>
</div>
  </div>
<%}}}else{
	%>
<script>
alert("There are no borrow requests in the system please come back later.");
</script>
	<%
}%>
</div>

          </div>
          <div class="card-footer text-center bg-white border-0">
          </div>
        </div>
      </div>
       <div style="height:15px;"></div>
    </body>
  </div>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.0.0-beta3/js/bootstrap.min.js"
    integrity="sha512-mp3VeMpuFKbgxm/XMUU4QQUcJX4AZfV5esgX72JQr7H7zWusV6lLP1S78wZnX2z9dwvywil1VHkHZAqfGOW7Nw=="
    crossorigin="anonymous"></script>

  </html>