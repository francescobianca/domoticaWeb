<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SmartPlace</title>

<!-- Bootstrap core CSS -->

<link
	href="startbootstrap-freelancer-gh-pages/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom fonts for this template -->
<link
	href="startbootstrap-freelancer-gh-pages/vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700"
	rel="stylesheet" type="text/css">
<link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>
<link
	href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic"
	rel="stylesheet" type="text/css">

<!-- Plugin CSS -->
<link
	href="startbootstrap-freelancer-gh-pages/vendor/magnific-popup/magnific-popup.css"
	rel="stylesheet" type="text/css">

<!-- Custom styles for this template -->
<link href="startbootstrap-freelancer-gh-pages/css/freelancer.min.css"
	rel="stylesheet">

<link
	href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css"
	rel="stylesheet">

<link rel="stylesheet" href="css/entryPage.css">


</head>

<body id="page-top">

	<!-- Navigation -->
	<nav
		class="navbar navbar-expand-lg bg-secondary fixed-top text-uppercase"
		id="mainNav">
	<div class="container">

		<img id="logo" alt="Brand" src="images/Logo.png">

		<!-- <a class="navbar-brand js-scroll-trigger" href="#page-top">Start Bootstrap</a> -->

		<button
			class="navbar-toggler navbar-toggler-right text-uppercase bg-primary text-white rounded"
			type="button" data-toggle="collapse" data-target="#navbarResponsive"
			aria-controls="navbarResponsive" aria-expanded="false"
			aria-label="Toggle navigation">
			Menu <i class="fa fa-bars"></i>
		</button>
		<div class="collapse navbar-collapse" id="navbarResponsive">

			<c:if test="${utente==null}">

				<ul class="navbar-nav ml-auto">
					<li class="nav-item mx-0 mx-lg-1"><a
						class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger"
						href="register.html">Registrati</a></li>
					<li class="nav-item mx-0 mx-lg-1"><a
						class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger"
						href="login.jsp">Login</a></li>
					<li class="nav-item mx-0 mx-lg-1"><a
						class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger"
						href="aboutUS.html">About</a></li>
				</ul>

			</c:if>

			<c:if test="${utente!=null}">
				<a style="color:white;font-style:italic;font-family: 'roboto';" id="utente"
					class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger">
					${nome} ${cognome}</a>

				<ul class="navbar-nav ml-auto">
					<li class="nav-item mx-0 mx-lg-1"><a
						class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger"
						href="leggiStatoDispositivi">DashBoard</a></li>
					<li class="nav-item mx-0 mx-lg-1"><a
						class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger"
						href="programmaActivity.jsp">Programma Attivit&agrave;</a></li>
					<li class="nav-item mx-0 mx-lg-1"><a
						class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger" href="checkLogin">Logout</a></li>
					<li class="nav-item mx-0 mx-lg-1"><a
						class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger"
						href="aboutUS.html">About</a></li>
				</ul>
			</c:if>



		</div>
	</div>
	</nav>

	<div id="slideshow" class="carousel slide">
		<div class="carousel-inner">
			<div class="item active">
				<img src="images/house1.jpg" alt="..." id="house1">
				<div class="carousel-caption">
					<h2>SmartPlace</h2>
					<p>Il nuovo sistema per la gestione integrata delle automazioni
						della tua casa</p>
				</div>
			</div>
		</div>
	</div>

	<div id="slideshow1" class="carousel slide">
		<div class="carousel-inner">
			<div class="item active">
				<img src="images/phone3.jpg" alt="..." id="phone">
				<div class="carousel-caption">
					<h2 id="phone3">#OvunqueTuSia</h2>
					<p id="phone3">Con SmartPlace puoi tenere sotto controllo la
						tua casa con una semplice app.</p>
					<img src="images/playStore.png" id="playStore" alt="Google Play"
						title="Google Play">
				</div>
			</div>
		</div>
	</div>



	<!-- Footer -->
	<footer class="footer text-center">
	<div class="container">
		<div class="row">
			<div class="col-md-4 mb-5 mb-lg-0">
				<h4 class="text-uppercase mb-4">Location</h4>
				<p class="lead mb-0">Università della Calabria, Via Pietro
					Bucci, 87036 Arcavacata, Rende CS</p>
			</div>
			<div class="col-md-4 mb-5 mb-lg-0">
				<h4 class="text-uppercase mb-4">Around the Web</h4>
				<ul class="list-inline mb-0">
					<li class="list-inline-item"><a
						class="btn btn-outline-light btn-social text-center rounded-circle"
						href="#"> <i class="fa fa-fw fa-facebook"></i>
					</a></li>
					<li class="list-inline-item"><a
						class="btn btn-outline-light btn-social text-center rounded-circle"
						href="#"> <i class="fa fa-fw fa-google-plus"></i>
					</a></li>
					<li class="list-inline-item"><a
						class="btn btn-outline-light btn-social text-center rounded-circle"
						href="#"> <i class="fa fa-fw fa-twitter"></i>
					</a></li>
					<li class="list-inline-item"><a
						class="btn btn-outline-light btn-social text-center rounded-circle"
						href="#"> <i class="fa fa-fw fa-linkedin"></i>
					</a></li>
					<li class="list-inline-item"><a
						class="btn btn-outline-light btn-social text-center rounded-circle"
						href="#"> <i class="fa fa-fw fa-dribbble"></i>
					</a></li>
				</ul>
			</div>
			<div class="col-md-4">
				<h4 class="text-uppercase mb-4">Riconoscimenti</h4>
				<p class="lead mb-0">
					Sviluppato da Bianca Francesco e Zarola Andrea per il corso di SIW</a>.
				</p>
			</div>
		</div>
	</div>
	</footer>

	<div class="copyright py-4 text-center text-white">
		<div class="container">
			<small>Copyright &copy; SmartPlace2018</small>
		</div>
	</div>

	<!-- Scroll to Top Button (Only visible on small and extra-small screen sizes) -->
	<div class="scroll-to-top d-lg-none position-fixed ">
		<a class="js-scroll-trigger d-block text-center text-white rounded"
			href="#page-top"> <i class="fa fa-chevron-up"></i>
		</a>
	</div>

	<!-- Bootstrap core JavaScript -->
	<script
		src="startbootstrap-freelancer-gh-pages/vendor/jquery/jquery.min.js"></script>
	<script
		src="startbootstrap-freelancer-gh-pages/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Plugin JavaScript -->
	<script
		src="startbootstrap-freelancer-gh-pages/vendor/jquery-easing/jquery.easing.min.js"></script>
	<script
		src="startbootstrap-freelancer-gh-pages/vendor/magnific-popup/jquery.magnific-popup.min.js"></script>

	<!-- Contact Form JavaScript -->
	<script
		src="startbootstrap-freelancer-gh-pages/js/jqBootstrapValidation.js"></script>
	<script src="startbootstrap-freelancer-gh-pages/js/contact_me.js"></script>

	<!-- Custom scripts for this template -->
	<script src="startbootstrap-freelancer-gh-pages/js/freelancer.min.js"></script>

	<script
		src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>
	
</body>

</html>