<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.Book" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.nio.file.Files" %>
<%@ page import="org.apache.commons.io.*" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="org.apache.commons.codec.binary.Base64" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<title>Books</title>
</head>
<body class="bg-image " style="background-image: url('https://i.ibb.co/9nfv7Dm/bg.png');
            height: 100%; background-repeat: no-repeat; background-size: 100% 100%;
            ">

   <jsp:include page="navbar.jsp" />
<div class="container" >

  <div class="row" >

  <%
  ArrayList<Book> book = (ArrayList<Book>) request.getAttribute("book");
%>

<%
for(Book item : book) {
    InputStream inputStream = item.getBook_Image();//retrieve the InputStream object
    byte[] imageBytes = IOUtils.toByteArray(inputStream);
    String base64EncodedString = Base64.encodeBase64String(imageBytes);
%>
      <div class="col-sm-5 col-md-3">
        <div class="card mt-5" >
 
      <img class="card-img-top" src="data:image/png;base64,<%=base64EncodedString%>" alt="Card image" style="width:100%">
    <div class="card-body">
      <h5 class="card-title" ><%= item.getBook_Name() %></h5>
      
<form action="/MyLibrary/BorrowBook.jsp" method="post">
   <input type="hidden" name="bookid" value="<%= item.getBook_ID() %>">
   <input type="hidden" name="action" value="issue">
   <input type="submit" value="Borrow Book" class="btn btn-primary">
</form>
    </div>
      </div>
    </div>
<%
}
%>





  </div>
<div class="row" >
<div class="col-sm-5 col-md-3">
<div style="height:50px;"></div>
</div>
</div>
</div>
</body>

  <script>

 
      var button = document.getElementById("profile");
     
      button.addEventListener("click", function () {
        var xhr = new XMLHttpRequest();
        xhr.open("GET", "http://localhost:8086/MyLibrary/profile", true);
        xhr.send();
      });




  </script>
</html>
