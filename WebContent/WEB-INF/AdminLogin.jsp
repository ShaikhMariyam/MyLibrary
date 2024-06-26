<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.0.0-beta3/css/bootstrap.min.css"
	integrity="sha512-N415hCJJdJx+1UBfULt+i+ihvOn42V/kOjOpp1UTh4CZ70Hx5bDlKryWaqEKfY/8EYOu/C2MuyaluJryK1Lb5Q=="
	crossorigin="anonymous" />
<title>Admin Login</title>
</head>
<style>
.center {
	text-align: center;
		color: white;
	
}

.mb-auto {
	margin-right: 10%;
	margin-left: 10%;
}


.ForgotPass{
text-align: right;
}
#Login {
	color: white;
}
</style>
<div class="bg-image"
	style="background-image: url('https://i.ibb.co/9nfv7Dm/bg.png'); background-repeat: no-repeat; background-size: cover; height: 100vh">
	<body>
		<jsp:include page="navbarA2.jsp" />

		<div class="container mt-5 mx-auto w-50">
			<div class="card shadow">
				<h1 style="text-align:center;">Admin Login</h1>
				<form action="LoginAdminController" method="POST">
					<div class="col-auto">
						<div class="mb-auto">
							<label for="Uname" class="form-label">Username</label>
							<input type="text" class="form-control" name="email" id="Uname">
						</div>
						<div class="mb-auto">
							<label for="Uname" class="form-label">Password</label> <input
								type="password" class="form-control" name="pass" id="pass">
						</div>
					</div>
					<br><br>
					<div class="center">
						<button type="submit" class="btn btn-warning btn-lg" id="Login">Login</button><br><br>
				</form>
			</div>
		</div>
</div>
</div>
</body>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.0.0-beta3/js/bootstrap.min.js"
	integrity="sha512-mp3VeMpuFKbgxm/XMUU4QQUcJX4AZfV5esgX72JQr7H7zWusV6lLP1S78wZnX2z9dwvywil1VHkHZAqfGOW7Nw=="
	crossorigin="anonymous">
	
</script>
</html>
