<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="model.Student" %>
    <%@ page import="model.Book" %>
    <%@ page import="model.borrowersList" %>
    <%@ page import="java.util.ArrayList" %>
<%@ page import="java.nio.file.Files" %>
<%@ page import="org.apache.commons.io.*" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="org.apache.commons.codec.binary.Base64" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
let btnSend = document.querySelector('button');
let message = document.querySelector('h1');
btnSend.addEventListener('click', () =>{
    btnSend.innerText = 'Sending...';
    setTimeout(()=>{
        btnSend.style.display = "none";
        message.innerText = 'Message Sent';
    },5000);
});
	</script>
<meta charset="ISO-8859-1">
<title>People who booked list</title>
<style type="text/css">
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}

body {
	font-family: Alice;
}

a {
	text-decoration: none;
}

li {
	list-style: none;
}

.navbar {
	display: flex;
	align-items: center;
	justify-content: space-between;
	padding: 20px;
	background-color: white;
	color: #fff;
	box-shadow: 0 2px 4px 0 rgba(0, 0, 0, .2);
}

.nav-links a {
	color: black;
}
/* LOGO */
.logo {
	background: #fff;
	padding: 0.2rem;
	border-radius: 0.2rem;
	margin: 0 0.3rem;
}
/* NAVBAR MENU */
.menu {
	display: flex;
	gap: 1em;
	font-size: 18px;
}

.menu li:hover {
	background-color: Gainsboro;
	border-radius: 5px;
	transition: 0.3s ease;
}

.menu li {
	padding: 5px 14px;
}

h1 {
	text-align: center;
}

img {
	border-radius: 100px;
}

button:hover {
	background-color: gray;
}

.bg-container {
	background-image: url("https://i.postimg.cc/HsB68NsF/image-7.png");
	width: 100%;
	height: 100%;
	border: 1px solid white;
	box-sizing: border-box;
	background-size: cover;
	background-repeat: no-repeat;
	background-position: center center;
}

html, body {
	margin: 0;
	height: 100%;
}

#customers {
	font-family: Arial, Helvetica, sans-serif;
	border-collapse: collapse;
	width: 80%;
}

.center {
	margin-left: auto;
	margin-right: auto;
}

#customers td, #customers th {
	border: 1px solid #ddd;
	padding: 8px;
}

#customers tr:nth-child(even) {
	background-color: #f2f2f2;
}

#customers tr:nth-child(odd) {
	background-color: white;
}

#customers tr: {
	background-color: #ddd;
}

#customers th {
	padding-top: 12px;
	padding-bottom: 12px;
	text-align: left;
	background-color: #8B4513;
	color: white;
}
</style>
</head>
<body>
	<nav class="navbar">
    <!-- LOGO -->
    <div class="logo">
        <img src="https://upload.wikimedia.org/wikipedia/commons/c/cb/UTM-LOGO-FULL.png" width="90" height="30">
    </div>
    <!-- NAVIGATION MENU -->
    <ul class="nav-links">
        <!-- USING CHECKBOX HACK -->

        <!-- NAVIGATION MENUS -->
        <div class="menu">
            <li><a href="/">Dashboard</a></li>
            <li><a href="/">Add book</a></li>
            <li><a href="payfine.jsp">Pay Fine</a></li>
            <li><a href="/">Log out</a></li>
        </div>
    </ul>
</nav>


<div class="bg-container">
    <h1 style="color: black;">Fine Management</h1>
    <form action="http://localhost:8086/MyLibrary/receipt" method="get">
        <table id="customers" class="center">
            <tr>
                <th>User Name</th>
                <th>Book Serial Number</th>
                <th>Book Name</th>
                <th>Borrow Date</th>
                <th>Due Data </th>
                <th>Days overdue </th>
                <th>Fine (RM) </th>
              
            </tr>
            <%
                ArrayList<Student> students = (ArrayList<Student>) request.getAttribute("student");
                ArrayList<Book> books = (ArrayList<Book>) request.getAttribute("book");
                ArrayList<borrowersList> borrowersList = (ArrayList<borrowersList>) request.getAttribute("borrowersList");
                for (int i = 0; i < borrowersList.size(); i++) {
            %>
                <tr>
                    <td> <%= students.get(i).getFirstName() %> </td>
                    <td> <%= books.get(i).getBook_SerialNo() %> </td>
                    <td> <%= books.get(i).getBook_Name() %> </td>
                    <td> <%= borrowersList.get(i).getBorrowDate() %> </td>
                    <td> <%= borrowersList.get(i).getBorrowReturnDate() %> </td>
                    <td> <%= borrowersList.get(i).calculateOverdueDays() %> </td>
                    <td> <%= borrowersList.get(i).getFine() %> </td>
                   
                </tr>
            <% } %>
        </table>
      
    </form>
</div>




</body>
</html>