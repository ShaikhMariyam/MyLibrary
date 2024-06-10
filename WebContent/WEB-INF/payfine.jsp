<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="style.css" />
<title>Pay Fine</title>

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

#box {
	background-color: white;
	border: 1px solid #DCDCDC;
	height: 685px;
	width: 400px;
	margin: auto;
	margin-top: 30px;
	padding: 50px;
	border-radius: 25px;
}

img {
	border-radius: 100px;
}

input {
	background-color: white;
	width: 100%;
	padding-bottom: 9px;
	padding-top: 9px;
	padding-left: 0px;
	padding-right: 0px;
	margin-bottom: 8px;
	margin-top: 8px;
	margin-left: 0PX;
	margin-right: 0PX;
	border: 1px solid lightgrey;
}

button {
	border: none;
	width: 100%;
	padding-bottom: 12px;
	padding-top: 12px;
	padding-left: 0px;
	padding-right: 0px;
	margin-bottom: 10px;
	margin-top: 10px;
	margin-left: 0PX;
	margin-right: 0PX;
	background-color: black;
	color: white;
	text-align: center;
	font-size: 15px;
	transform: scale(0.98);
	box-shadow: 3px 2px 22px 1px rgba(0, 0, 0, 0.24);
	border-radius: 25px;
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
</style>
</head>
<body>
	<nav class="navbar">
		<!-- LOGO -->
		<div class="logo">
			<img
				src="https://upload.wikimedia.org/wikipedia/commons/c/cb/UTM-LOGO-FULL.png"
				width="90" height="30">
		</div>
		<!-- NAVIGATION MENU -->
		<ul class="nav-links">
			<!-- USING CHECKBOX HACK -->

			<!-- NAVIGATION MENUS -->
			<div class="menu">
				<li><a href="/">Dashboard</a></li>
				<li><a href="/">Add book</a></li>
				<li><a href="/">Log out</a></li>
			</div>
		</ul>
	</nav>
    <h1>Pay Fine</h1>
    <form action="http://localhost:8086/MyLibrary/updateFine" method="post">
        <label for="studentId">Student ID:</label>
        <input type="text" id="studentId" name="studentId" required>
        <br>
        <label for="bookId">Book ID:</label>
        <input type="text" id="bookId" name="bookId" required>
        <br>
        <label for="paidAmount">Paid Amount:</label>
        <input type="text" id="paidAmount" name="paidAmount" required>
        <br>
        <input type="submit" value="Submit">
    </form>
</body>
</html>