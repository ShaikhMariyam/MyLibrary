    <link rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.0.0-beta3/css/bootstrap.min.css"
      integrity="sha512-N415hCJJdJx+1UBfULt+i+ihvOn42V/kOjOpp1UTh4CZ70Hx5bDlKryWaqEKfY/8EYOu/C2MuyaluJryK1Lb5Q=="
      crossorigin="anonymous" />
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container-fluid">
    <a class="navbar-brand" href="#"><img src="https://upload.wikimedia.org/wikipedia/commons/c/cb/UTM-LOGO-FULL.png" alt="" width="227" height="65"></a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
              <li class="nav-item">
 <div class="d-inline-flex">
  <form class="form-inline my-2 my-lg-0" action="http://localhost:8086/MyLibrary/search" method="POST">
   <div style="display: flex; justify-content: space-between;">
    <input class="form-control mr-sm-2" type="search" name="search" placeholder="Search" aria-label="Search">
    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </div>
  </form>
  
</div>
               </li>
        <li class="nav-item">
          <a class="nav-link" aria-current="page" href="StudentDashboard.jsp">Dashboard</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="http://localhost:8086/MyLibrary/books">My books</a>
        </li>
       <li class="nav-item">
          <a id="profile" class="nav-link active" href="http://localhost:8086/MyLibrary/profile">Profile</a>
        </li>

<li class="nav-item"><div class="p-3"></div></li>
       
        <li class="nav-item">

        
          <button class="btn btn-default shadow px-3 pb-2 mb-1 bg-body rounded" onclick="document.location='LoginStudent.jsp'"  tabindex="-1" aria-disabled="true">Log out</button>
        </li>


      </ul>
    </div>
  </div>
</nav>
