<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="google-signin-client_id"
	content="653927480756-gfvi4taakmfo42otuh7bu1drq1aqpfv0.apps.googleusercontent.com">
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

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
<link href='http://fonts.googleapis.com/css?family=Roboto'
	rel='stylesheet' type='text/css'>
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

<!-- custom style for this page -->
<link rel="stylesheet" href="css/charts.css">

</head>

<body id="page-top">

	<!-- Serve per il logout con google -->
	<div class="g-signin2" style="display: none"></div>

	<!-- Navigation -->
	<nav
		class="navbar navbar-expand-lg bg-secondary fixed-top text-uppercase"
		id="mainNav">
	<div class="container">

		<!-- <a href="entryPage.jsp"> <img id="logo" alt="Brand"
			src="images/Logo.png">
		</a> -->

		<button
			class="navbar-toggler navbar-toggler-right text-uppercase bg-primary text-white rounded"
			type="button" data-toggle="collapse" data-target="#navbarResponsive"
			aria-controls="navbarResponsive" aria-expanded="false"
			aria-label="Toggle navigation">
			Menu <i class="fa fa-bars"></i>
		</button>
		<div class="collapse navbar-collapse" id="navbarResponsive">

			<c:if test="${email!=null}">
				<a style="color: white; font-style: italic; font-family: 'roboto';"
					id="utente"
					class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger">
					${nome} ${cognome}</a>
			</c:if>
			<ul class="navbar-nav ml-auto">
				<li class="nav-item mx-0 mx-lg-1"><a
					class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger"
					href="#portfolio">Charts</a></li>
				<li class="nav-item mx-0 mx-lg-1"><a
					class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger"
					href="#contact">Contact</a></li>
				<li class="nav-item mx-0 mx-lg-1"><a
					class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger"
					href="leggiStatoDispositivi">Dashboard</a></li>
				<li class="nav-item mx-0 mx-lg-1"><a
					class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger"
					href="programmaActivity.jsp">Programma Attivit&agrave;</a></li>
				<li class="nav-item mx-0 mx-lg-1"><a href="entryPage.jsp"> <img
						id="home" class="img-fluid" src="images/homepage.png" alt=""
						style="padding-top: 20%"></a></li>
				<c:if test="${tipo=='normale'}">
					<li class="nav-item mx-0 mx-lg-1"><a href="checkLogin"> <img
							id="logout" class="img-fluid" src="images/logout2.png" alt=""
							style="padding-top: 20%"></a></li>
				</c:if>
				<c:if test="${tipo=='facebook'}">
					<li class="nav-item mx-0 mx-lg-1"><a
						onclick="logoutFacebook()" href=""><img id="logout"
							class="img-fluid" src="images/logout2.png" alt=""
							style="padding-top: 20%"></a></li>
				</c:if>
				<c:if test="${tipo=='google'}">
					<li class="nav-item mx-0 mx-lg-1"><a onclick="logoutGoogle()"
						href=""><img id="logout" class="img-fluid"
							src="images/logout2.png" alt="" style="padding-top: 20%"></a></li>
				</c:if>
			</ul>
		</div>
	</div>
	</nav>

	<!-- Header -->
	<header class="bg-primary text-white text-center">
	<div class="container">
		<img class="img-fluid" alt="">
	</div>
	</header>


	<!-- Portfolio Grid Section -->

	<section class="portfolio" id="portfolio">
	<div class="container">
		<h2 class="text-center text-uppercase text-secondary mb-0">Charts</h2>
		<hr class="star-dark mb-5">


		<div class="row justify-content-center" style="padding-top: 5%">
			<h3 class="text-center text-uppercase text-secondary mb-0">Temperatura</h3>

			<canvas id="canvasTemperatura" style="display:block"></canvas>

			<canvas id="canvasNuovoPeriodoTemperatura" style="display:none"></canvas>

			<div class="row" style="padding-top: 3%">

				<div class="col-md-6 col-lg-6">
					<a class="portfolio-item d-block mx-auto"
						href="#nuovoPeriodoTemperatura"> <label class="labelStyle"
						id="chartTemperature" for="chartTemperature"> Cambia il
							periodo di riferimento:</label>
					</a>
				</div>

				<div class="col-md-6 col-lg-6">
					<div class="form-group">
						<div class="select">
							<select name="slct" id="selectChartTemperatura"
								onclick="kindChart('Temperatura')">
								<option>Seleziona il tipo di grafico</option>
								<option value="1">Line chart</option>
								<option value="2">Bar chart</option>
							</select>
						</div>
					</div>
				</div>

			</div>

		</div>

		<div class="row justify-content-center" style="padding-top: 10%">
			<h3 class="text-center text-uppercase text-secondary mb-0">Umidit&agrave;</h3>

			<canvas id="canvasUmidita" style="display:block"></canvas>

			<canvas id="canvasNuovoPeriodoUmidita" style="display:none"></canvas>

			<div class="row" style="padding-top: 3%">

				<div class="col-md-6 col-lg-6">
					<a class="portfolio-item d-block mx-auto"
						href="#nuovoPeriodoUmidita"> <label class="labelStyle"
						id="chartHumidity" for="chartTemperature"> Cambia il
							periodo di riferimento:</label>
					</a>
				</div>

				<div class="col-md-6 col-lg-6">
					<div class="form-group">
						<div class="select">
							<select name="slct" id="selectChartUmidita"
								onclick="kindChart('Umidita')">
								<option>Seleziona il tipo di grafico</option>
								<option value="1">Line chart</option>
								<option value="2">Bar chart</option>
							</select>
						</div>
					</div>
				</div>

			</div>

		</div>
	</section>



	<!-- Footer -->
	<footer class="footer text-center" id="contact">
	<div class="container">
		<div class="row">
			<div class="col-md-4 mb-5 mb-lg-0">
				<h4 class="text-uppercase mb-4">Location</h4>
				<p class="lead mb-0">Universit&agrave; della Calabria, Via
					Pietro Bucci, 87036 Arcavacata, Rende CS</p>
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
			<small>Copyright &copy; SmartPlace 2018</small>
		</div>
	</div>



	<!-- Scroll to Top Button (Only visible on small and extra-small screen sizes) -->
	<div class="scroll-to-top d-lg-none position-fixed ">
		<a class="js-scroll-trigger d-block text-center text-white rounded"
			href="#page-top"> <i class="fa fa-chevron-up"></i>
		</a>
	</div>


	<!-- Nuovo periodo grafico temperatura -->
	<div class="portfolio-modal mfp-hide" id="nuovoPeriodoTemperatura">
		<div class="portfolio-modal-dialog bg-white" style="min-height: 50vh;">
			<a
				class="close-button d-none d-md-block portfolio-modal-dismiss closeButton"
				id="closeButtonTemperatura" href="#"> <i
				class="fa fa-3x fa-times"></i>
			</a>
			<div class="container">
				<div class="row">

					<div class="col-md-6 col-lg-6">
						<label class="labelStyle" id="giornoInizio" for="giornoInizio"
							style="height: 1.5em;">Scegli giorno di inizio:</label>
					</div>

					<div class="col-md-6 col-lg-6">
						<div class="form-group">
							<input name="giornoInizio" type="date"
								class="form-control giornoInizioBox"
								id="giornoInizioBoxTemperatura" />
						</div>
					</div>

					<div class="col-md-6 col-lg-6" style="padding-top: 5%;">
						<label class="labelStyle" id="giornoFine" for="giornoFine"
							style="height: 1.5em;">Scegli giorno di fine:</label>
					</div>

					<div class="col-md-6 col-lg-6" style="padding-top: 5%;">
						<div class="form-group">
							<input name="giornoFine" type="date"
								class="form-control giornoFineBox" id="giornoFineBoxTemperatura" />
							<span id="giornoFineBoxErroreTemperatura"
								class="card-Header errore" style="display: none">La data
								di fine deve essere successiva a quella di inizio</span>
						</div>
					</div>

					<div class="container row justify-content-center">
						<span class="card-Header errore" style="display: none"
							id="completaFormBoxTemperatura">Completa la form</span>
					</div>

					<div class="container row justify-content-center" id="invia">
						<input name="inviaDati" type="button" value="Invia"
							class="btn btn-primary" id="Invia"
							onclick="cambiaPeriodo('Temperatura')" />
					</div>

				</div>
			</div>
		</div>
	</div>

	<!-- Nuovo periodo grafico umidita -->
	<div class="portfolio-modal mfp-hide" id="nuovoPeriodoUmidita">
		<div class="portfolio-modal-dialog bg-white" style="min-height: 50vh;">
			<a
				class="close-button d-none d-md-block portfolio-modal-dismiss closeButton"
				id="closeButtonUmidita" href="#"> <i class="fa fa-3x fa-times"></i>
			</a>
			<div class="container">
				<div class="row">

					<div class="col-md-6 col-lg-6">
						<label class="labelStyle" id="giornoInizio" for="giornoInizio"
							style="height: 1.5em;">Scegli giorno di inizio:</label>
					</div>

					<div class="col-md-6 col-lg-6">
						<div class="form-group">
							<input name="giornoInizio" type="date"
								class="form-control giornoInizioBox" id="giornoInizioBoxUmidita" />
							<span id="giornoInizioBoxErroreUmidita"
								class="card-Header errore" style="display: none">La data
								non � valida</span>
						</div>
					</div>

					<div class="col-md-6 col-lg-6" style="padding-top: 5%;">
						<label class="labelStyle" id="giornoFine" for="giornoFine"
							style="height: 1.5em;">Scegli giorno di fine:</label>
					</div>

					<div class="col-md-6 col-lg-6" style="padding-top: 5%;">
						<div class="form-group">
							<input name="giornoFine" type="date"
								class="form-control giornoFineBox" id="giornoFineBoxUmidita" />
							<span id="giornoFineBoxErroreUmidita" class="card-Header errore"
								style="display: none">La data di fine deve essere
								successiva a quella di inizio</span>
						</div>
					</div>

					<div class="container row justify-content-center">
						<span class="card-Header errore" style="display: none"
							id="completaFormBoxUmidita">Completa la form</span>
					</div>

					<div class="container row justify-content-center" id="invia">
						<input name="inviaDati" type="button" value="Invia"
							class="btn btn-primary" id="Invia"
							onclick="cambiaPeriodo('Umidita')" />
					</div>

				</div>
			</div>
		</div>
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

	<!-- Custom script for charts -->
	<script src="Chart.js-2.7.1/dist/Chart.bundle.js"></script>
	<script src="Chart.js-2.7.1/samples/utils.js"></script>
	<script src="js/charts.js"></script>

	<!-- js for facebook -->
	<script src="js/facebookLogin.js"></script>
	<!-- js for google -->
	<script src="https://apis.google.com/js/platform.js" async defer></script>
	<script src="js/googleLogin.js"></script>

</body>

</html>