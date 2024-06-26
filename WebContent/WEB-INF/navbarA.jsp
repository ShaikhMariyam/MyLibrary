    <link rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.0.0-beta3/css/bootstrap.min.css"
      integrity="sha512-N415hCJJdJx+1UBfULt+i+ihvOn42V/kOjOpp1UTh4CZ70Hx5bDlKryWaqEKfY/8EYOu/C2MuyaluJryK1Lb5Q=="
      crossorigin="anonymous" />
      <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container-fluid">
    <a class="navbar-brand" href="#"><img src="https://upload.wikimedia.org/wikipedia/commons/c/cb/UTM-LOGO-FULL.png" alt="" width="227" height="65"></a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
       
        <li class="nav-item">
          <a class="nav-link active" href="http://localhost:8086/MyLibrary/fine">Fine Management</a>
        </li>
         <li class="nav-item">
          <a class="nav-link active" href="AddBook.jsp">Add Book</a>
        </li>
        <li class="nav-item">
          <a class="nav-link active" href="IssueBookList.jsp">Borrow Requests</a>
        </li>
       <li class="nav-item">
          <a class="nav-link active" href="/MyLibrary/report">Report</a>
        </li>
        <li class="nav-item">
          <a class="nav-link active" href="AdminRegistration.jsp">Add Admin</a>
        </li>
               <li class="nav-item">
          <button class="btn btn-default shadow px-3 pb-2 mb-1 bg-body rounded" onclick="document.location='AdminLogin.jsp'" tabindex="-1" aria-disabled="true">Log out</button>
      
      </ul>
    </div>
  </div>
</nav>
