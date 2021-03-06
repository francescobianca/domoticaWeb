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


<link rel="stylesheet" href="css/dashBoardStyle.css">

</head>

<body id="page-top">

	<!-- Serve per il logout con google -->
	<div class="g-signin2" style="display: none"></div>

	<!-- Navigation -->
	<nav
		class="navbar navbar-expand-lg bg-secondary fixed-top text-uppercase"
		id="mainNav">
	<div class="container">

		<!-- 
		<a href="entryPage.jsp"> <img id="logo" alt="Brand"
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
					href="#portfolio">Dashboard</a></li>
				<li class="nav-item mx-0 mx-lg-1"><a
					class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger"
					href="#contact">Contact</a></li>
				<li class="nav-item mx-0 mx-lg-1"><a
					class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger"
					href="programmaActivity.jsp">Programma Attivit&agrave;</a></li>
				<li class="nav-item mx-0 mx-lg-1"><a
					class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger"
					href="Charts.jsp">Charts</a></li>
				<li class="nav-item mx-0 mx-lg-1"><a href="entryPage.jsp"><img
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
		<h2 class="text-center text-uppercase text-secondary mb-0">Dashboard</h2>
		<hr class="star-dark mb-5">
		<div class="row">
			<div class="col-md-6 col-lg-4">
				<a class="portfolio-item d-block mx-auto"
					href="#categoria_temperatura">
					<div
						class="portfolio-item-caption d-flex position-absolute h-100 w-100"
						onclick="leggiTemperatura()">
						<div
							class="portfolio-item-caption-content my-auto w-100 text-center text-white">
							<i class="fa fa-search-plus fa-3x"></i>
						</div>
					</div> <img class="img-fluid" src="images/temperatura.png" alt="">
				</a>
			</div>
			<div class="col-md-6 col-lg-4">
				<a class="portfolio-item d-block mx-auto" href="#categoria_luci">
					<div
						class="portfolio-item-caption d-flex position-absolute h-100 w-100">
						<div
							class="portfolio-item-caption-content my-auto w-100 text-center text-white">
							<i class="fa fa-search-plus fa-3x"></i>
						</div>
					</div> <img class="img-fluid" src="images/luce.png" alt="">
				</a>
			</div>
			<div class="col-md-6 col-lg-4">
				<a class="portfolio-item d-block mx-auto" href="#categoria_cancello">
					<div
						class="portfolio-item-caption d-flex position-absolute h-100 w-100">
						<div
							class="portfolio-item-caption-content my-auto w-100 text-center text-white">
							<i class="fa fa-search-plus fa-3x"></i>
						</div>
					</div> <img class="img-fluid" src="images/cancello.png" alt="">
				</a>
			</div>
			<div class="col-md-6 col-lg-4">
				<a class="portfolio-item d-block mx-auto" href="#categoria_finestre">
					<div
						class="portfolio-item-caption d-flex position-absolute h-100 w-100">
						<div
							class="portfolio-item-caption-content my-auto w-100 text-center text-white">
							<i class="fa fa-search-plus fa-3x"></i>
						</div>
					</div> <img class="img-fluid" src="images/finestra.png" alt="">
				</a>
			</div>
			<div class="col-md-6 col-lg-4">
				<a class="portfolio-item d-block mx-auto" href="#categoria_umidita">
					<div
						class="portfolio-item-caption d-flex position-absolute h-100 w-100">
						<div
							class="portfolio-item-caption-content my-auto w-100 text-center text-white"
							onclick="leggiUmidita()">
							<i class="fa fa-search-plus fa-3x"></i>
						</div>
					</div> <img class="img-fluid" src="images/umidita.png" alt="">
				</a>
			</div>
			<div class="col-md-6 col-lg-4">
				<a class="portfolio-item d-block mx-auto"
					href="#categoria_sicurezza">
					<div
						class="portfolio-item-caption d-flex position-absolute h-100 w-100">
						<div
							class="portfolio-item-caption-content my-auto w-100 text-center text-white">
							<i class="fa fa-search-plus fa-3x"></i>
						</div>
					</div> <img class="img-fluid" src="images/lock.png" alt="">
				</a>
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

	<!-- Elenco categorie -->

	<!-- Categoria temperatura-->
	<div class="portfolio-modal mfp-hide" id="categoria_temperatura">
		<div class="portfolio-modal-dialog bg-white">
			<a class="close-button d-none d-md-block portfolio-modal-dismiss"
				href="#"> <i class="fa fa-3x fa-times"></i>
			</a>
			<div class="container">
				<h2 class="text-center text-secondary text-uppercase mb-0">Temperatura</h2>
				<hr class="star-dark mb-5">
				
				<div class="row justify-content-center">
					<img class="img-fluid" src="images/refresh.png" href="" onclick="leggiTemperatura()">
				</div>
				
				<div class="row">

					<div class="col-md-6 col-lg-6">
						<a class="d-block mx-auto">
							<div
								class="portfolio-item-caption d-flex position-absolute h-100 w-100">
								<div
									class="portfolio-item-caption-content my-auto w-100 text-center text-white">
								</div>

							</div> <span class="thermometer"><span id="celsius"></span>&deg;C</span>
						</a>
					</div>
		

					<div class="col-md-6 col-lg-6">
						<a class="d-block mx-auto">
							<div
								class="portfolio-item-caption d-flex position-absolute h-100 w-100">
								<div
									class="portfolio-item-caption-content my-auto w-100 text-center text-white">
								</div>
							</div> <span class="thermometer"><span id="fahrenheit"></span>&deg;F</span>
						</a>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6 col-lg-6">
						<a class="d-block mx-auto">
							<div
								class="portfolio-item-caption d-flex position-absolute h-100 w-100">
								<div
									class="portfolio-item-caption-content my-auto w-100 text-center text-white">
								</div>
							</div> <img class="img-fluid" src="images/ventilatore.png" alt="">
						</a>
						<h3 class="text-center text-uppercase text-secondary mb-0">Ventilatore</h3>
						<c:if test="${ventilatore_casa.stato == 0}">
							<label class="switch"> <input type="checkbox"
								id="ventilatore"> <span class="slider round"></span>
							</label>
						</c:if>
						<c:if test="${ventilatore_casa.stato == 1}">
							<label class="switch"> <input type="checkbox"
								checked="checked" id="ventilatore"> <span
								class="slider round"></span>
							</label>
						</c:if>
						<c:if test="${ventilatore_casa==null}">
							<label class="switch"> <input type="checkbox"
								id="venitlatore" disabled="true"> <span
								class="slider round"></span>
							</label>
						</c:if>
						</label>
					</div>

					<div class="col-md-6 col-lg-6">
						<a class="d-block mx-auto">
							<div
								class="portfolio-item-caption d-flex position-absolute h-100 w-100">
								<div
									class="portfolio-item-caption-content my-auto w-100 text-center text-white">
								</div>
							</div> <img class="img-fluid" src="images/termosifoni.png" alt="">
						</a>
						<h3 class="text-center text-uppercase text-secondary mb-0">Riscaldamenti</h3>
						<c:if test="${riscaldamento_casa.stato == 0}">
							<label class="switch"> <input type="checkbox" unchecked
								id="riscaldamento"> <span class="slider round"></span>
							</label>
						</c:if>
						<c:if test="${riscaldamento_casa.stato == 1}">
							<label class="switch"> <input type="checkbox" checked
								id="riscaldamento"> <span class="slider round"></span>
							</label>
						</c:if>
						<c:if test="${riscaldamento_casa==null}">
							<label class="switch"> <input type="checkbox" unchecked
								id="riscaldamento" disabled="true"> <span
								class="slider round"></span>
							</label>
						</c:if>
					</div>
				</div>
			</div>

			<div class="container text-center">
				<div class="row">
					<div class="col-lg-8 mx-auto">
						<hr class="star-dark mb-5">
						<a
							class="btn btn-primary btn-lg rounded-pill portfolio-modal-dismiss"
							href="#"> <i class="fa fa-close"></i> Close
						</a>
					</div>
				</div>
			</div>
		</div>
	</div>


	<!-- Categoria Luci -->
	<div class="portfolio-modal mfp-hide" id="categoria_luci">
		<div class="portfolio-modal-dialog bg-white">
			<a class="close-button d-none d-md-block portfolio-modal-dismiss"
				href="#"> <i class="fa fa-3x fa-times"></i>
			</a>
			<div class="container">
				<h2 class="text-center text-uppercase text-secondary mb-0">Luci</h2>
				<hr class="star-dark mb-5">
				<div class="row">
					<div class="col-md-6 col-lg-6">
						<a class="d-block mx-auto">
							<div
								class="portfolio-item-caption d-flex position-absolute h-100 w-100">
								<div
									class="portfolio-item-caption-content my-auto w-100 text-center text-white">
								</div>
							</div> <img class="img-fluid" src="images/bagno.png" alt="">
						</a>
						<h3 class="text-center text-uppercase text-secondary mb-0">Bagno</h3>

						<c:if test="${luce_bagno.stato == 0}">
							<label class="switch"> <input type="checkbox"
								id="luce_bagno" class="checkbox-luci"> <span
								class="slider round"></span>
							</label>
						</c:if>
						<c:if test="${luce_bagno.stato == 1}">
							<label class="switch"> <input type="checkbox"
								checked="checked" id="luce_bagno" class="checkbox-luci">
								<span class="slider round"></span>
							</label>
						</c:if>
						<c:if test="${luce_bagno==null}">
							<label class="switch"> <input type="checkbox"
								id="luce_bagno" class="checkbox-luci" disabled="true"> <span
								class="slider round"></span>
							</label>
						</c:if>

					</div>
					<div class="col-md-6 col-lg-6">
						<a class="d-block mx-auto">
							<div
								class="portfolio-item-caption d-flex position-absolute h-100 w-100">
								<div
									class="portfolio-item-caption-content my-auto w-100 text-center text-white">
								</div>
							</div> <img class="img-fluid" src="images/cucina.png" alt="">
						</a>
						<h3 class="text-center text-uppercase text-secondary mb-0">Cucina</h3>
						<c:if test="${luce_cucina.stato == 0}">
							<label class="switch"> <input type="checkbox"
								id="luce_cucina" class="checkbox-luci"> <span
								class="slider round"></span>
							</label>
						</c:if>
						<c:if test="${luce_cucina.stato == 1}">
							<label class="switch"> <input type="checkbox"
								checked="checked" id="luce_cucina" class="checkbox-luci">
								<span class="slider round"></span>
							</label>
						</c:if>
						<c:if test="${luce_cucina==null}">
							<label class="switch"> <input type="checkbox"
								id="luce_cucina" class="checkbox-luci" disabled="true">
								<span class="slider round"></span>
							</label>
						</c:if>
					</div>

					<div class="col-md-6 col-lg-6">
						<a class="d-block mx-auto">
							<div
								class="portfolio-item-caption d-flex position-absolute h-100 w-100">
								<div
									class="portfolio-item-caption-content my-auto w-100 text-center text-white">
								</div>
							</div> <img class="img-fluid" src="images/letto.png" alt="">
						</a>
						<h3 class="text-center text-uppercase text-secondary mb-0">Camera
							da letto</h3>
						<c:if test="${luce_cameraLetto.stato == 0}">
							<label class="switch"> <input type="checkbox"
								id="luce_cameraLetto" class="checkbox-luci"> <span
								class="slider round"></span>
							</label>
						</c:if>
						<c:if test="${luce_cameraLetto.stato == 1}">
							<label class="switch"> <input type="checkbox"
								checked="checked" id="luce_cameraLetto" class="checkbox-luci">
								<span class="slider round"></span>
							</label>
						</c:if>
						<c:if test="${luce_cameraLetto==null}">
							<label class="switch"> <input type="checkbox"
								id="luce_cameraLetto" class="checkbox-luci" disabled="true">
								<span class="slider round"></span>
							</label>
						</c:if>

					</div>

					<div class="col-md-6 col-lg-6">
						<a class="d-block mx-auto">
							<div
								class="portfolio-item-caption d-flex position-absolute h-100 w-100">
								<div
									class="portfolio-item-caption-content my-auto w-100 text-center text-white">
								</div>
							</div> <img class="img-fluid" src="images/salotto.png" alt="">
						</a>
						<h3 class="text-center text-uppercase text-secondary mb-0">Salone</h3>
						<c:if test="${luce_salone.stato == 0}">
							<label class="switch"> <input type="checkbox"
								id="luce_salone" class="checkbox-luci"> <span
								class="slider round"></span>
							</label>
						</c:if>
						<c:if test="${luce_salone.stato == 1}">
							<label class="switch"> <input type="checkbox"
								checked="checked" id="luce_salone" class="checkbox-luci">
								<span class="slider round"></span>
							</label>
						</c:if>
						<c:if test="${luce_salone==null}">
							<label class="switch"> <input type="checkbox"
								id="luce_salone" class="checkbox-luci" disabled="true">
								<span class="slider round"></span>
							</label>
						</c:if>
					</div>

				</div>
			</div>
			<div class="container text-center">
				<div class="row">
					<div class="col-lg-8 mx-auto">
						<hr class="star-dark mb-5">
						<a
							class="btn btn-primary btn-lg rounded-pill portfolio-modal-dismiss"
							href="#"> <i class="fa fa-close"></i> Close
						</a>
					</div>
				</div>
			</div>

		</div>
	</div>

	<!-- Categoria cancello-->
	<div class="portfolio-modal mfp-hide" id="categoria_cancello">
		<div class="portfolio-modal-dialog bg-white">
			<a class="close-button d-none d-md-block portfolio-modal-dismiss"
				href="#"> <i class="fa fa-3x fa-times"></i>
			</a>
			<div class="container text-center">
				<h2 class="text-center text-uppercase text-secondary mb-0">Ingressi</h2>
				<hr class="star-dark mb-5">
				<div class="row justify-content-center">

					<div class="col-md-6 col-lg-6">
						<a class="d-block mx-auto">
							<div class="portfolio-item-caption d-flex h-100 w-100">
								<div
									class="portfolio-item-caption-content my-auto w-100 text-center text-white">
								</div>
							</div>

							<h3 class="text-center text-uppercase text-secondary mb-0">Cancello</h3>
							<img class="img-fluid" src="images/ingresso.png" alt="">

						</a>
						<div class="progress">
							<c:if test="${cancello_casa!=null}">
								<div class="progress-bar" role="progressbar"
									aria-valuenow="${cancello_casa.stato}" aria-valuemin="0"
									aria-valuemax="60"
									style="width: ${100/180*cancello_casa.stato}%" id="cancello"></div>
							</c:if>
							<c:if test="${cancello_casa==null}">
								<div class="progress-bar" role="progressbar" aria-valuenow="0"
									aria-valuemin="0" aria-valuemax="60" style="width: 0%"
									id="cancello"></div>
							</c:if>

						</div>

						<!-- Frecce per apertura cancello -->
						<div>
							<i id="cancello_left" class="fa fa-toggle-left"
								style="font-size: 36px"></i> <i id="cancello_right"
								class="fa fa-toggle-right" style="font-size: 36px"></i>
						</div>

					</div>

				</div>

				<div class="row justify-content-center" id="row_garage">

					<div class="col-md-6 col-lg-6">
						<a class="d-block mx-auto">
							<div class="portfolio-item-caption d-flex h-100 w-100">
								<div
									class="portfolio-item-caption-content my-auto w-100 text-center text-white">
								</div>
							</div>

							<h3 class="text-center text-uppercase text-secondary mb-0">Garage</h3>
							<img class="img-fluid" src="images/garage.png" alt="">

						</a>
						<div class="progress">
							<c:if test="${garage_casa!=null}">
								<div class="progress-bar" role="progressbar"
									aria-valuenow="${garage_casa.stato}" aria-valuemin="0"
									aria-valuemax="180" style="width: ${100/60*garage_casa.stato}%"
									id="garage"></div>
							</c:if>
							<c:if test="${garage_casa==null}">
								<div class="progress-bar" role="progressbar" aria-valuenow="0"
									aria-valuemin="0" aria-valuemax="180" style="width: 0%"
									id="garage"></div>
							</c:if>
						</div>

						<!-- Frecce per apertura finestra -->
						<div>
							<i id="garage_left" class="fa fa-toggle-left"
								style="font-size: 36px"></i> <i id="cancello_right"
								class="fa fa-toggle-right" style="font-size: 36px"></i>
						</div>

					</div>

				</div>

			</div>
			<div class="container text-center">
				<div class="row">
					<div class="col-lg-8 mx-auto">
						<hr class="star-dark mb-5">
						<a
							class="btn btn-primary btn-lg rounded-pill portfolio-modal-dismiss"
							href="#"> <i class="fa fa-close"></i> Close
						</a>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Categoria finestre -->
	<div class="portfolio-modal mfp-hide" id="categoria_finestre">
		<div class="portfolio-modal-dialog bg-white">
			<a class="close-button d-none d-md-block portfolio-modal-dismiss"
				href="#"> <i class="fa fa-3x fa-times"></i>
			</a>
			<div class="container">
				<h2 class="text-center text-uppercase text-secondary mb-0">Finestre</h2>
				<hr class="star-dark mb-5">
				<div class="row">
					<div class="col-md-6 col-lg-6">
						<a class="d-block mx-auto">
							<div class="portfolio-item-caption d-flex h-100 w-100">
								<div
									class="portfolio-item-caption-content my-auto w-100 text-center text-white">
								</div>
							</div>

							<h3 class="text-center text-uppercase text-secondary mb-0">Bagno</h3>
							<img class="img-fluid" src="images/bagno.png" alt="">

						</a>
						<div class="progress">

							<c:if test="${finestra_bagno!=null}">
								<div class="progress-bar" role="progressbar"
									aria-valuenow="${finestra_bagno.stato}" aria-valuemin="0"
									aria-valuemax="180"
									style="width: ${100/180*finestra_bagno.stato}%"
									id="finestra_bagno"></div>
							</c:if>
							<c:if test="${finestra_bagno==null}">
								<div class="progress-bar" role="progressbar" aria-valuenow="0"
									aria-valuemin="0" aria-valuemax="180" style="width: 0%"
									id="finestra_bagno"></div>
							</c:if>
						</div>

						<!-- Frecce per apertura finestra -->
						<div>
							<i id="finestra_up_bagno"
								class="up-finestra fa fa-toggle-up up-finestra apri-finestra"
								style="font-size: 36px"></i>
						</div>

						<div>
							<i id="finestra_down_bagno"
								class="fa fa-toggle-down down-finestra chiudi-finestra"
								style="font-size: 36px"></i>
						</div>

					</div>


					<div class="col-md-6 col-lg-6">
						<a class="d-block mx-auto">
							<div class="portfolio-item-caption d-flex h-100 w-100">
								<div
									class="portfolio-item-caption-content my-auto w-100 text-center text-white">
								</div>
							</div>
							<h3 class="text-center text-uppercase text-secondary mb-0">Cucina</h3>
							<img class="img-fluid" src="images/cucina.png" alt="">
						</a>
						<div class="progress">
							<c:if test="${finestra_cucina!=null}">
								<div class="progress-bar" role="progressbar"
									aria-valuenow="${finestra_cucina.stato}" aria-valuemin="0"
									aria-valuemax="180"
									style="width: ${100/180*finestra_cucina.stato}%"
									id="finestra_cucina"></div>
							</c:if>
							<c:if test="${finestra_cucina==null}">
								<div class="progress-bar" role="progressbar" aria-valuenow="0"
									aria-valuemin="0" aria-valuemax="180" style="width: 0%"
									id="finestra_cucina"></div>
							</c:if>
						</div>

						<!-- Frecce per apertura finestra -->
						<div>
							<i id="finestra_up_cucina"
								class="fa fa-toggle-up up-finestra apri-finestra"
								style="font-size: 36px"></i>
						</div>

						<div>
							<i id="finestra_down_cucina"
								class="fa fa-toggle-down down-finestra chiudi-finestra"
								style="font-size: 36px"></i>
						</div>


					</div>

					<div class="col-md-6 col-lg-6">
						<a class="d-block mx-auto">
							<div class="portfolio-item-caption d-flex h-100 w-100">
								<div
									class="portfolio-item-caption-content my-auto w-100 text-center text-white">
								</div>
							</div>
							<h3 class="text-center text-uppercase text-secondary mb-0">Camera
								da letto</h3> <img class="img-fluid" src="images/letto.png" alt="">
						</a>
						<div class="progress">
							<c:if test="${finestra_cameraLetto!=null}">
								<div class="progress-bar" role="progressbar"
									aria-valuenow="${finestra_cameraLetto.stato}" aria-valuemin="0"
									aria-valuemax="180"
									style="width: ${100/180*finestra_cameraLetto.stato}%"
									id="finestra_cameraLetto"></div>
							</c:if>
							<c:if test="${finestra_cameraLetto==null}">
								<div class="progress-bar" role="progressbar" aria-valuenow="0"
									aria-valuemin="0" aria-valuemax="180" style="width: 0%"
									id="finestra_cameraLetto"></div>
							</c:if>

						</div>

						<!-- Frecce per apertura finestra -->
						<div>
							<i id="finestra_up_cameraLetto"
								class="fa fa-toggle-up up-finestra apri-finestra"
								style="font-size: 36px"></i>
						</div>

						<div>
							<i id="finestra_down_cameraLetto"
								class="fa fa-toggle-down down-finestra chiudi-finestra"
								style="font-size: 36px"></i>
						</div>


					</div>

					<div class="col-md-6 col-lg-6">
						<a class="d-block mx-auto">
							<div class="portfolio-item-caption d-flex h-100 w-100">
								<div
									class="portfolio-item-caption-content my-auto w-100 text-center text-white">
								</div>
							</div>
							<h3 class="text-center text-uppercase text-secondary mb-0">Salone</h3>
							<img class="img-fluid" src="images/salotto.png" alt="">
						</a>
						<div class="progress">
							<c:if test="${finestra_salone!=null}">
								<div class="progress-bar" role="progressbar"
									aria-valuenow="${finestra_salone.stato}" aria-valuemin="0"
									aria-valuemax="180"
									style="width: ${100/180*finestra_salone.stato}%"
									id="finestra_salone"></div>
							</c:if>
							<c:if test="${finestra_salone==null}">
								<div class="progress-bar" role="progressbar" aria-valuenow="0"
									aria-valuemin="0" aria-valuemax="180" style="width: 0%"
									id="finestra_salone"></div>
							</c:if>
						</div>

						<!-- Frecce per apertura finestra -->
						<div>
							<i id="finestra_up_salone"
								class="fa fa-toggle-up up-finestra apri-finestra"
								style="font-size: 36px"></i>
						</div>

						<div>
							<i id="finestra_down_salone"
								class="fa fa-toggle-down down-finestra chiudi-finestra"
								style="font-size: 36px"></i>
						</div>

					</div>

				</div>
			</div>
			<div class="container text-center">
				<div class="row">
					<div class="col-lg-8 mx-auto">
						<hr class="star-dark mb-5">
						<a
							class="btn btn-primary btn-lg rounded-pill portfolio-modal-dismiss"
							href="#"> <i class="fa fa-close"></i> Close
						</a>
					</div>
				</div>
			</div>

		</div>
	</div>


	<!-- Categoria umidita  -->
	<div class="portfolio-modal mfp-hide" id="categoria_umidita">
		<div class="portfolio-modal-dialog bg-white">
			<a class="close-button d-none d-md-block portfolio-modal-dismiss"
				href="#"> <i class="fa fa-3x fa-times"></i>
			</a>
			<div class="container text-center">
				<h2 class="text-center text-uppercase text-secondary mb-0">Umidit&agrave;</h2>
				<hr class="star-dark mb-5">
				<div class="row justify-content-center">

					<div class="col-md-6 col-lg-6">
						<a class="d-block mx-auto">
							<div
								class="portfolio-item-caption d-flex position-absolute h-100 w-100">
								<div
									class="portfolio-item-caption-content my-auto w-100 text-center text-white">
								</div>
							</div>

							<div class="container" id="humidity">
								<img id="humidity" class="img-fluid"
									src="images/sfondoHumidity.png" alt="">

								<div class="centered" id="text_humidity">
									<span id="umidita">Valore umidita</span>%
								</div>

							</div>
						</a>
					</div>
				</div>

				<div class="row justify-content-center">

					<div class="col-md-6 col-lg-6">
						<a class="d-block mx-auto">

							<div
								class="portfolio-item-caption d-flex position-absolute h-100 w-100">
								<div
									class="portfolio-item-caption-content my-auto w-100 text-center text-white">
								</div>
							</div> <img class="img-fluid" src="images/deumidificatore.png" alt="">
							<h3 class="text-center text-uppercase text-secondary mb-0">Deumidificatore</h3>

							<c:if test="${deumidificatore_casa.stato == 0}">
								<label class="switch label-left"> <input type="checkbox"
									unchecked id="deumidificatore"> <span
									class="slider round"></span>
								</label>
							</c:if> <c:if test="${deumidificatore_casa.stato == 1}">
								<label class="switch label-left"> <input type="checkbox"
									checked id="deumidificatore"> <span
									class="slider round"></span>
								</label>
							</c:if> <c:if test="${deumidificatore_casa==null}">
								<label class="switch label-left"> <input type="checkbox"
									unchecked id="deumidificatore" disabled="true"> <span
									class="slider round"></span>
								</label>
							</c:if>

						</a>
					</div>
				</div>


			</div>
			<div class="container text-center">
				<div class="row">
					<div class="col-lg-8 mx-auto">
						<hr class="star-dark mb-5">
						<a
							class="btn btn-primary btn-lg rounded-pill portfolio-modal-dismiss"
							href="#"> <i class="fa fa-close"></i> Close
						</a>
					</div>
				</div>
			</div>
		</div>
	</div>


	<!--Categoria Sicurezza -->
	<div class="portfolio-modal mfp-hide" id="categoria_sicurezza">
		<div class="portfolio-modal-dialog bg-white">
			<a class="close-button d-none d-md-block portfolio-modal-dismiss"
				href="#"> <i class="fa fa-3x fa-times"></i>
			</a>

			<div class="container text-center">
				<h2 class="text-center text-uppercase text-secondary mb-0">Sicurezza</h2>
				<hr class="star-dark mb-5">
				<div class="row justify-content-center">

					<div class="col-md-6 col-lg-6">
						<a class="d-block mx-auto">
							<div
								class="portfolio-item-caption d-flex position-absolute h-100 w-100">
								<div
									class="portfolio-item-caption-content my-auto w-100 text-center text-white">
								</div>
							</div> <img class="img-fluid" src="images/alarm.png" alt="">
							<h3 class="text-center text-uppercase text-secondary mb-0">Allarme</h3>
							<c:if test="${allarme_casa.stato == 0}">
								<label class="switch label-left"> <input type="checkbox"
									unchecked id="allarme"> <span class="slider round"></span>
								</label>
							</c:if> <c:if test="${allarme_casa.stato == 1}">
								<label class="switch label-left"> <input type="checkbox"
									checked id="allarme"> <span class="slider round"></span>
								</label>
							</c:if> <c:if test="${allarme_casa==null}">
								<label class="switch label-left"> <input type="checkbox"
									unchecked id="allarme" disabled="true"> <span
									class="slider round"></span>
								</label>
							</c:if> </label>
						</a>
					</div>

				</div>

				<div class="row justify-content-center">
					<div class="col-md-6 col-lg-6">
						<a class="d-block mx-auto">
							<div
								class="portfolio-item-caption d-flex position-absolute h-100 w-100">
								<div
									class="portfolio-item-caption-content my-auto w-100 text-center text-white">
								</div>
							</div> <img class="img-fluid" src="images/videocamera.png" alt="">
							<h3 class="text-center text-uppercase text-secondary mb-0">VideoCamere</h3>
							<c:if test="${videocamere_casa.stato == 0}">
								<label class="switch label-left"> <input type="checkbox"
									unchecked id="videocamere"> <span class="slider round"></span>
								</label>
							</c:if> <c:if test="${videocamere_casa.stato == 1}">
								<label class="switch label-left"> <input type="checkbox"
									checked id="videocamere"> <span class="slider round"></span>
								</label>
							</c:if> <c:if test="${videocamere_casa==null}">
								<label class="switch label-left"> <input type="checkbox"
									unchecked id="videocamere" disabled="true"> <span
									class="slider round"></span>
								</label>
							</c:if>
						</a>
					</div>
				</div>

			</div>
			<div class="container text-center">
				<div class="row">
					<div class="col-lg-8 mx-auto">
						<hr class="star-dark mb-5">
						<a
							class="btn btn-primary btn-lg rounded-pill portfolio-modal-dismiss"
							href="#"> <i class="fa fa-close"></i> Close
						</a>
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

	<!-- JS usato per implementare le richieste ajax sui sensori-->
	<script src="js/dashBoard.js"></script>

	<script
		src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>

	<!-- js for facebook -->
	<script src="js/facebookLogin.js"></script>
	<!-- js for google -->
	<script src="https://apis.google.com/js/platform.js" async defer></script>
	<script src="js/googleLogin.js"></script>

</body>

</html>