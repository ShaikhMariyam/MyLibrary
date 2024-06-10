<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="style.css" />
<title>Document</title>
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


	<div class="bg-container">	
		<form name="form1" onsubmit = "return required()" enctype="multipart/form-data" method="Get" action="http://localhost:8086/MyLibrary/create">
    <div id="box">
        <h1>Create Book</h1>
        <b>ID</b><input type ="text" name="book_id" id="book_id">
        <b>Upload image</b> <input type="file" id="myFile" name="image">
        <b>Title</b> <input type="text" class="input-box" name="book_name" id="book_name">
        <b>Description</b>
        <textarea id="Description" name="book_description" rows="3" cols="38"></textarea>
        <b> Serial number</b> <input type="tel" id="serial_num" name="serial_num">
        <b>Location</b> <input type="text" id="book_location" class="input-box" name="book_location">
        <b>Available copys</b> <input type="number" id="avail_copys" class="input-box" name="avail_copys"> 
        <b>Return duration</b> <input type="text" id="duration" class="input-box" name="duration">
        <b>Borrow cost</b> <input type="number" step="0.01" id="borrow_cost" name="borrow_cost">
        <button type="submit" onclick="required()">Add book</button>
    </div>
</form>
	</div>
	<script src="non-empty.js">
	function required(){
		var empt = document.form1.filename.value;
		if (empt === "")
		{
		alert("Please input a Value");
		return false;
		}

	</script>
</body>
</html>