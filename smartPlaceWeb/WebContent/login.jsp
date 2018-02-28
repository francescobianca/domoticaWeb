<%@ page language="java" contentType="text/html"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="google-signin-client_id"
	content="653927480756-gfvi4taakmfo42otuh7bu1drq1aqpfv0.apps.googleusercontent.com">

<title>SmartPlace</title>
<!-- Bootstrap core CSS-->
<link
	href="startbootstrap-sb-admin-gh-pages/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Custom fonts for this template-->
<link
	href="startbootstrap-sb-admin-gh-pages/vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<!-- Custom styles for this template-->
<link href="startbootstrap-sb-admin-gh-pages/css/sb-admin.css"
	rel="stylesheet">
<!-- Custom css for login -->
<link href="css/register.css" rel="stylesheet" type="text/css" />

</head>
<body class="bg-dark">
	<div class="container">
		<div class="card card-login mx-auto mt-5">
			<div class="card-header">Login</div>
			<div class="card-body">
				<form method="post" action="checkLogin">

					<div class="form-group">
						<c:if test="${email!=null}">
							<label for="email">Indirizzo email</label>
							<input name="email" value="${email}" type="text"
								class="form-control validFormBox" id="emailBox" onblur="checkEmail()" />
							<span class="card-Header errore" id="ErroreEmail"
								style="display: none">Indirizzo non registrato</span>
						</c:if>
						<c:if test="${email==null}">
							<label for="email">Indirizzo email</label>
							<input name="email" type="text" class="form-control"
								id="emailBox" onblur="checkEmail()" />
							<span class="card-Header errore" id="ErroreEmail"
								style="display: none" disabled="true">Indirizzo non registrato</span>
						</c:if>
					</div>
					<div class="form-group">
						<label for="password">Password</label> <input name="password"
							type="password" class="form-control" id="passwordBox" />
						<c:if test="${email!=null}">
							<span class="card-Header errore" id="ErroreConfermaPassword"
								style="display: block" disabled="false">Password errata</span>
						</c:if>
					</div>

					<div class="form-group">
						<div class="form-check">
							<label class="form-check-label"> <input
								class="form-check-input" type="checkbox"> Ricorda
								credenziali
							</label>
						</div>
					</div>


					<div class="form-group">
						<input name="inviaDati" type="submit" id="loginButton"
							value="Login" class="btn btn-primary btn-block" />
					</div>

					<!--  <div class="form-group">
						<fb:login-button scope="public_profile,email,user_birthday"
							onlogin="checkLoginState();">
						</fb:login-button>
					</div> -->

					<div class="form-group row justify-content-center">
						<div class="fb-login-button" data-max-rows="1" data-size="large"
							data-button-type="continue_with" data-show-faces="false"
							data-auto-logout-link="false" data-use-continue-as="false"
							scope="public_profile,email,user_birthday"
							onlogin="checkLoginState();"></div>
					</div>

					<div class="form-group row justify-content-center">
						<div class="g-signin2" data-onsuccess="onSignIn"></div>
					</div>

				</form>
				<div class="text-center">
					<a class="d-block small mt-3" href="register.html">Registra un
						Account</a> <a class="d-block small" href="forgot-password.html">Hai
						dimenticato la Password?</a>
				</div>
			</div>
		</div>
	</div>


	<!-- Bootstrap core JavaScript-->
	<script
		src="startbootstrap-sb-admin-gh-pages/vendor/jquery/jquery.min.js"></script>
	<script
		src="startbootstrap-sb-admin-gh-pages/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- Core plugin JavaScript-->
	<script
		src="startbootstrap-sb-admin-gh-pages/vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom js for login -->
	<script src="js/login.js"></script>

	<!-- Custom js for facebook -->
	<script src="js/facebookLogin.js"></script>

	<!-- js for google -->
	<script src="https://apis.google.com/js/platform.js" async defer></script>
	<script src="js/googleLogin.js"></script>

</body>
</html>