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

<!-- style for calendar -->
<link rel="stylesheet" href="fullcalendar-3.8.2/fullcalendar.css" />

<!-- custom style for this page -->
<link rel="stylesheet" href="css/activity.css">

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
					href="#portfolio">Programma Attivit&agrave;</a></li>
				<li class="nav-item mx-0 mx-lg-1"><a
					class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger"
					href="#calendar">Calendario</a></li>
				<li class="nav-item mx-0 mx-lg-1"><a
					class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger"
					href="#contact">Contact</a></li>
				<li class="nav-item mx-0 mx-lg-1"><a
					class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger"
					href="leggiStatoDispositivi">DashBoard</a></li>
				<li class="nav-item mx-0 mx-lg-1"><a
					class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger"
					href="Charts.jsp">Charts</a></li>
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

	<section class="portfolio" id="portfolio" style="padding-top:10%">
	<div class="container">
		<h2 class="text-center text-uppercase text-secondary mb-0">Programma
			Attivit&agrave;</h2>
		<hr class="star-dark mb-5">
		<div class="row">
			<div class="col-md-6 col-lg-4">
				<a class="portfolio-item d-block mx-auto"
					href="#categoria_temperatura">
					<div
						class="portfolio-item-caption d-flex position-absolute h-100 w-100">
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
							class="portfolio-item-caption-content my-auto w-100 text-center text-white">
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

	<!-- About Section -->
	<section class="bg-primary text-white mb-0" id="about">
	<div class="container">
		<h2 class="text-center text-uppercase text-white">Calendario
			attivit&agrave;</h2>


	</div>
	</section>

	<!-- Calendar section -->
	<section class="portfolio" id="calendar">
	<div class="container">

		<div id='calendar'></div>

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
			<a
				class="close-button d-none d-md-block portfolio-modal-dismiss closeButton"
				id="closeButtonTemperatura" href="#"> <i
				class="fa fa-3x fa-times"></i>
			</a>
			<div class="container">
				<h2 class="text-center text-secondary text-uppercase mb-0">Temperatura</h2>
				<hr class="star-dark mb-5">

				<div class="row justify-content-center">

					<div class="select" onclick="newActionTemperatura()">
						<select name="slct" id="slct">
							<option>Cosa desideri fare?</option>
							<option value="1">Nuova attivit&agrave; periodica</option>
							<option value="2">Elimina attivit&agrave; periodica</option>
							<option value="3">Nuova regola</option>
							<option value="4">Elimina regola</option>
						</select>
					</div>
				</div>

				<!-- Sezione programma attività -->
				<div class="container" id="nuovaActivity" style="display: none;">
					<div class="row">

						<!-- Sezione nome activity -->
						<div class="col-md-6 col-lg-6">
							<label id="nomeActivity" for="nomeActivity">Inserisci il
								nome dell'attività:</label>
						</div>

						<div class="col-md-6 col-lg-6">
							<div class="form-group">
								<input name="nomeActivity" type="text" class="form-control"
									id="nomeActivityBoxTemperatura" />
							</div>
						</div>

						<!-- Sezione giorno inizio activity -->
						<div class="col-md-6 col-lg-6">
							<label id="giornoInizio" for="giornoInizio">Scegli giorno
								di inizio:</label>
						</div>

						<div class="col-md-6 col-lg-6">
							<div class="form-group">
								<input name="giornoInizio" type="date"
									class="form-control giornoInizioBox"
									id="giornoInizioBoxTemperatura" /> <span
									id="giornoInizioBoxErroreTemperatura"
									class="card-Header errore" style="display: none">La data
									non è valida</span>
							</div>
						</div>

						<!-- Sezione giorno fine activity -->
						<div class="col-md-6 col-lg-6">
							<label id="giornoFine" for="giornoFine">Scegli giorno di
								fine:</label>
						</div>

						<div class="col-md-6 col-lg-6">
							<div class="form-group">
								<input name="giornoFine" type="date"
									class="form-control giornoFineBox"
									id="giornoFineBoxTemperatura" /> <span
									id="giornoFineBoxErroreTemperatura" class="card-Header errore"
									style="display: none">La data di fine deve essere
									successiva a quella di inizio</span>

							</div>
						</div>

						<!-- Sezione ora inizio activity -->
						<div class="col-md-6 col-lg-6">
							<label id="oraInizio" for="oraInizio">Scegli ora di
								inizio:</label>
						</div>

						<div class="col-md-6 col-lg-6">
							<div class="form-group">
								<input name="oraInizio" type="time"
									class="form-control oraInizioBox" id="oraInizioBoxTemperatura" />
							</div>
						</div>

						<!-- Sezione ora fine activity -->
						<div class="col-md-6 col-lg-6">
							<label id="oraFine" for="oraFine">Scegli ora di fine:</label>
						</div>

						<div class="col-md-6 col-lg-6">
							<div class="form-group">
								<input name="oraFine" type="time"
									class="form-control oraFineBox" id="oraFineBoxTemperatura" /><span
									id="oraFineBoxErroreTemperatura" class="card-Header errore"
									style="display: none">L'ora di fine deve essere
									succesiva a quella di inizio</span>
							</div>
						</div>

						<!-- Sezione dispositivo activity -->
						<div class="col-md-6 col-lg-6">
							<label id="dispositivo" for="dispositivo">Seleziona il
								dispositivo:</label>
						</div>

						<div class="col-md-6 col-lg-6">
							<div class="form-group">
								<div class="select">
									<select name="slct" id="selectDeviceActivityBoxTemperatura">
										<option value="1">Ventilatore</option>
										<option value="2">Riscaldamenti</option>
									</select>
								</div>
							</div>
						</div>
					</div>

					<div class="row justify-content-center">
						<span class="card-Header errore" style="display: none"
							id="completaFormBoxTemperatura">Completa la form</span> <span
							class="card-Header success" style="display: none"
							id="salvaFormBoxTemperatura">Attivit&agrave; salvata
							correttamente</span> <span class="card-Header errore"
							style="display: none" id="sensoreNonEsisteTemperatura">Il
							sensore scelto non &egrave; ancora installato nell'abitazione</span> <span
							class="card-Header errore" style="display: none"
							id="attivitaIncoerenteTemperatura">L'attivit&agrave;
							inserita non è coerente con quelle già create</span> <span
							class="card-Header errore" style="display: none"
							id="attivitaStessoNomeTemperatura">Esiste gi&agrave;
							un'attivit&agrave; con lo stesso nome</span> <span
							class="card-Header errore" style="display: none"
							id="erroreServerTemperatura">Si &egrave; verificato un
							errore durante il salvateggio dell'attivit&agrave;</span>



					</div>

					<div class="container row justify-content-center" id="invia">
						<input name="inviaDati" type="button" value="Invia"
							class="btn btn-primary" id="Invia"
							onclick="registraAttivita('Temperatura')" />
					</div>

				</div>

				<!-- Sezione programma regola -->
				<div class="container" id="nuovaRegola" style="display: none;">
					<div class="row">

						<!-- Sezione nome regola -->
						<div class="col-md-6 col-lg-6">
							<label id="nomeRegola" for="nomeRegola">Inserisci il nome
								della regola:</label>
						</div>

						<div class="col-md-6 col-lg-6">
							<div class="form-group">
								<input name="nomeRegola" type="text" class="form-control"
									id="nomeRegolaBoxTemperatura" />
							</div>
						</div>

						<!-- Sezione verso regola -->
						<div class="col-md-6 col-lg-6">
							<label id="versoRegola" for="versoRegola">Quando la
								temperatura è:</label>
						</div>

						<div class="col-md-6 col-lg-6">
							<div class="form-group">
								<div class="select">
									<select name="slct" id="selectCondizioneRegolaTemperatura">
										<option value="1">Maggiore (&gt;)</option>
										<option value="2">Minore (&lt;)</option>
									</select>
								</div>
							</div>
						</div>

						<!-- Sezione valore regola -->
						<div class="col-md-6 col-lg-6">
							<label id="valoreRegola" for="valoreRegola">Inserisci il
								valore:</label>
						</div>

						<div class="col-md-6 col-lg-6">
							<div class="form-group">
								<input name="valoreRegola" type="text" class="form-control"
									id="valoreRegolaBoxTemperatura"
									onkeypress='return (event.charCode >= 48 && event.charCode <= 57) || event.charCode == 45' />
							</div>
						</div>

						<!-- Sezione dispositivo regola -->
						<div class="col-md-6 col-lg-6">
							<label id="dispositivo" for="dispositivo">Accendi il
								dispositivo:</label>
						</div>

						<div class="col-md-6 col-lg-6">
							<div class="form-group">
								<div class="select">
									<select name="slct" id="selectDeviceRegolaTemperatura">
										<option value="1">Ventilatore</option>
										<option value="2">Riscaldamenti</option>
									</select>
								</div>
							</div>
						</div>
					</div>

					<div class="row justify-content-center">
						<span class="card-Header errore" style="display: none"
							id="completaFormBoxRegolaTemperatura">Completa la form</span> <span
							class="card-Header success" style="display: none"
							id="salvaFormBoxRegolaTemperatura">Regola salvata
							correttamente</span> <span class="card-Header errore"
							style="display: none" id="sensoreNonEsisteRegolaTemperatura">Il
							sensore scelto non &egrave; ancora installato nell'abitazione</span> <span
							class="card-Header errore" style="display: none"
							id="regolaStessoNomeTemperatura">Esiste gi&agrave; una
							regola con lo stesso nome</span> <span class="card-Header errore"
							style="display: none" id="erroreServerRegolaTemperatura">Si
							&egrave; verificato un errore durante il salvateggio della regola</span>
					</div>

					<div class="container row justify-content-center" id="invia">
						<input name="inviaDati" type="button" value="Invia"
							class="btn btn-primary" id="Invia"
							onclick="registraRegola('Temperatura')" />
					</div>

				</div>

			</div>

			<div class="container text-center">
				<div class="row">
					<div class="col-lg-8 mx-auto">
						<hr class="star-dark mb-5">
					</div>
				</div>
			</div>

		</div>

	</div>


	<!-- Categoria Luci -->
	<div class="portfolio-modal mfp-hide" id="categoria_luci">

		<div class="portfolio-modal-dialog bg-white">
			<a
				class="close-button d-none d-md-block portfolio-modal-dismiss closeButton"
				id="closeButtonLuce" href="#"> <i class="fa fa-3x fa-times"></i>
			</a>
			<div class="container">
				<h2 class="text-center text-secondary text-uppercase mb-0">Luci</h2>
				<hr class="star-dark mb-5">

				<div class="row justify-content-center">

					<div class="select" onclick="newActionLuci()">
						<select name="slct" id="slct">
							<option>Cosa desideri fare?</option>
							<option value="1">Nuova attivit&agrave; periodica</option>
							<option value="2">Elimina attivit&agrave; periodica</option>
						</select>
					</div>
				</div>

				<div class="container" id="nuovaActivity" style="display: none;">
					<!-- style="display: none;" Devo inserirlo dopo -->
					<div class="row">

						<!-- Sezione nome activity -->
						<div class="col-md-6 col-lg-6">
							<label id="nomeActivity" for="nomeActivity">Inserisci il
								nome dell'attività:</label>
						</div>

						<div class="col-md-6 col-lg-6">
							<div class="form-group">
								<input name="nomeActivity" type="text" class="form-control"
									id="nomeActivityBoxLuce" />
							</div>
						</div>

						<!-- Sezione giorno inizio activity -->
						<div class="col-md-6 col-lg-6">
							<label id="giornoInizio" for="giornoInizio">Scegli giorno
								di inizio:</label>
						</div>

						<div class="col-md-6 col-lg-6">
							<div class="form-group">
								<input name="giornoInizio" type="date"
									class="form-control giornoInizioBox" id="giornoInizioBoxLuce" />
								<span id="giornoInizioBoxErroreLuce" class="card-Header errore"
									style="display: none">La data non è valida</span>

							</div>
						</div>

						<!-- Sezione giorno fine activity -->
						<div class="col-md-6 col-lg-6">
							<label id="giornoFine" for="giornoFine">Scegli giorno di
								fine:</label>
						</div>

						<div class="col-md-6 col-lg-6">
							<div class="form-group">
								<input name="giornoFine" type="date"
									class="form-control giornoFineBox" id="giornoFineBoxLuce" /> <span
									id="giornoFineBoxErroreLuce" class="card-Header errore"
									style="display: none">La data di fine deve essere
									successiva a quella di inizio</span>
							</div>
						</div>

						<!-- Sezione ora inizio activity -->
						<div class="col-md-6 col-lg-6">
							<label id="oraInizio" for="oraInizio">Scegli ora di
								inizio:</label>
						</div>

						<div class="col-md-6 col-lg-6">
							<div class="form-group">
								<input name="oraInizio" type="time"
									class="form-control oraInizioBox" id="oraInizioBoxLuce" />
							</div>
						</div>

						<!-- Sezione ora fine activity -->
						<div class="col-md-6 col-lg-6">
							<label id="oraFine" for="oraFine">Scegli ora di fine:</label>
						</div>

						<div class="col-md-6 col-lg-6">
							<div class="form-group">
								<input name="oraFine" type="time"
									class="form-control oraFineBox" id="oraFineBoxLuce" /><span
									id="oraFineBoxErroreLuce" class="card-Header errore"
									style="display: none">L'ora di fine deve essere
									succesiva a quella di inizio</span>
							</div>
						</div>

						<!-- Sezione dispositivo activity -->
						<div class="col-md-6 col-lg-6">
							<label id="dispositivo" for="dispositivo">Seleziona il
								dispositivo:</label>
						</div>

						<div class="col-md-6 col-lg-6">
							<div class="form-group">
								<div class="select">
									<select name="slct" id="selectDeviceActivityBoxLuce">
										<option value="1">Bagno</option>
										<option value="2">Salone</option>
										<option value="3">Cucina</option>
										<option value="4">Camera letto</option>
									</select>
								</div>
							</div>
						</div>
					</div>

					<div class="row justify-content-center">
						<span class="card-Header errore" style="display: none"
							id="completaFormBoxLuce">Completa la form</span> <span
							class="card-Header success" style="display: none"
							id="salvaFormBoxLuce">Attivit&agrave; salvata
							correttamente</span> <span class="card-Header errore"
							style="display: none" id="sensoreNonEsisteLuce">Il sensore
							scelto non &egrave; ancora installato nell'abitazione</span> <span
							class="card-Header errore" style="display: none"
							id="attivitaIncoerenteLuce">L'attivit&agrave; inserita non
							è coerente con quelle già create</span> <span class="card-Header errore"
							style="display: none" id="attivitaStessoNomeLuce">Esiste
							gi&agrave; un'attivit&agrave; con lo stesso nome</span> <span
							class="card-Header errore" style="display: none"
							id="erroreServerLuce">Si &egrave; verificato un errore
							durante il salvateggio dell'attivit&agrave;</span>
					</div>

					<div class="container row justify-content-center" id="invia">
						<input name="inviaDati" type="button" value="Invia"
							onclick="registraAttivita('Luce')" class="btn btn-primary"
							id="Invia" />
					</div>

				</div>

			</div>

			<div class="container text-center">
				<div class="row">
					<div class="col-lg-8 mx-auto">
						<hr class="star-dark mb-5">
					</div>
				</div>
			</div>

		</div>


	</div>

	<!-- Categoria cancello-->
	<div class="portfolio-modal mfp-hide" id="categoria_cancello">
		<div class="portfolio-modal-dialog bg-white">
			<a
				class="close-button d-none d-md-block portfolio-modal-dismiss closeButton"
				id="closeButtonCancello" href="#"> <i class="fa fa-3x fa-times"></i>
			</a>
			<div class="container">
				<h2 class="text-center text-secondary text-uppercase mb-0">Ingressi</h2>
				<hr class="star-dark mb-5">

				<div class="row justify-content-center">

					<div class="select" onclick="newActionCancello()">
						<select name="slct" id="slct">
							<option>Cosa desideri fare?</option>
							<option value="1">Nuova attivit&agrave; periodica</option>
							<option value="2">Elimina attivit&agrave; periodica</option>
						</select>
					</div>
				</div>

				<div class="container" id="nuovaActivity" style="display: none;">
					<!-- style="display: none;" Devo inserirlo dopo -->
					<div class="row">

						<!-- Sezione nome activity -->
						<div class="col-md-6 col-lg-6">
							<label id="nomeActivity" for="nomeActivity">Inserisci il
								nome dell'attività:</label>
						</div>

						<div class="col-md-6 col-lg-6">
							<div class="form-group">
								<input name="nomeActivity" type="text" class="form-control"
									id="nomeActivityBoxCancello" />
							</div>
						</div>

						<!-- Sezione giorno inizio activity -->
						<div class="col-md-6 col-lg-6">
							<label id="giornoInizio" for="giornoInizio">Scegli giorno
								di inizio:</label>
						</div>

						<div class="col-md-6 col-lg-6">
							<div class="form-group">
								<input name="giornoInizio" type="date"
									class="form-control giornoInizioBox"
									id="giornoInizioBoxCancello" /> <span
									id="giornoInizioBoxErroreCancello" class="card-Header errore"
									style="display: none">La data non è valida</span>

							</div>
						</div>

						<!-- Sezione giorno fine activity -->
						<div class="col-md-6 col-lg-6">
							<label id="giornoFine" for="giornoFine">Scegli giorno di
								fine:</label>
						</div>

						<div class="col-md-6 col-lg-6">
							<div class="form-group">
								<input name="giornoFine" type="date"
									class="form-control giornoFineBox" id="giornoFineBoxCancello" /><span
									id="giornoFineBoxErroreCancello" class="card-Header errore"
									style="display: none">La data di fine deve essere
									successiva a quella di inizio</span>
							</div>
						</div>

						<!-- Sezione ora inizio activity -->
						<div class="col-md-6 col-lg-6">
							<label id="oraInizio" for="oraInizio">Scegli ora di
								inizio:</label>
						</div>

						<div class="col-md-6 col-lg-6">
							<div class="form-group">
								<input name="oraInizio" type="time"
									class="form-control oraInizioBox" id="oraInizioBoxCancello" />
							</div>
						</div>

						<!-- Sezione ora fine activity -->
						<div class="col-md-6 col-lg-6">
							<label id="oraFine" for="oraFine">Scegli ora di fine:</label>
						</div>

						<div class="col-md-6 col-lg-6">
							<div class="form-group">
								<input name="oraFine" type="time"
									class="form-control oraFineBox" id="oraFineBoxCancello" /><span
									id="oraFineBoxErroreCancello" class="card-Header errore"
									style="display: none">L'ora di fine deve essere
									succesiva a quella di inizio</span>
							</div>
						</div>

						<!-- Sezione dispositivo activity -->
						<div class="col-md-6 col-lg-6">
							<label id="dispositivo" for="dispositivo">Seleziona il
								dispositivo:</label>
						</div>

						<div class="col-md-6 col-lg-6">
							<div class="form-group">
								<div class="select">
									<select name="slct" id="selectDeviceActivityBoxCancello">
										<option value="1">Cancello</option>
										<option value="2">Garage</option>
									</select>
								</div>
							</div>
						</div>
					</div>

					<div class="row justify-content-center">
						<span class="card-Header errore" style="display: none"
							id="completaFormBoxCancello">Completa la form</span> <span
							class="card-Header success" style="display: none"
							id="salvaFormBoxCancello">Attivit&agrave; salvata
							correttamente</span> <span class="card-Header errore"
							style="display: none" id="sensoreNonEsisteCancello">Il
							sensore scelto non &egrave; ancora installato nell'abitazione</span> <span
							class="card-Header errore" style="display: none"
							id="attivitaIncoerenteCancello">L'attivit&agrave; inserita
							non è coerente con quelle già create</span> <span
							class="card-Header errore" style="display: none"
							id="attivitaStessoNomeCancello">Esiste gi&agrave;
							un'attivit&agrave; con lo stesso nome</span> <span
							class="card-Header errore" style="display: none"
							id="erroreServerCancello">Si &egrave; verificato un errore
							durante il salvateggio dell'attivit&agrave;</span>
					</div>

					<div class="container row justify-content-center" id="invia">
						<input name="inviaDati" type="button" value="Invia"
							onclick="registraAttivita('Cancello')" class="btn btn-primary"
							id="Invia" />
					</div>

				</div>

			</div>

			<div class="container text-center">
				<div class="row">
					<div class="col-lg-8 mx-auto">
						<hr class="star-dark mb-5">
					</div>
				</div>
			</div>

		</div>
	</div>

	<!-- Categoria finestre -->
	<div class="portfolio-modal mfp-hide" id="categoria_finestre">

		<div class="portfolio-modal-dialog bg-white">
			<a
				class="close-button d-none d-md-block portfolio-modal-dismiss closeButton"
				id="closeButtonFinestra" href="#"> <i class="fa fa-3x fa-times"></i>
			</a>
			<div class="container">
				<h2 class="text-center text-secondary text-uppercase mb-0">Finestre</h2>
				<hr class="star-dark mb-5">

				<div class="row justify-content-center">

					<div class="select" onclick="newActionFinestre()">
						<select name="slct" id="slct">
							<option>Cosa desideri fare?</option>
							<option value="1">Nuova attivit&agrave; periodica</option>
							<option value="2">Elimina attivit&agrave; periodica</option>
						</select>
					</div>
				</div>

				<div class="container" id="nuovaActivity" style="display: none;">
					<!-- style="display: none;" Devo inserirlo dopo -->
					<div class="row">

						<!-- Sezione nome activity -->
						<div class="col-md-6 col-lg-6">
							<label id="nomeActivity" for="nomeActivity">Inserisci il
								nome dell'attività:</label>
						</div>

						<div class="col-md-6 col-lg-6">
							<div class="form-group">
								<input name="nomeActivity" type="text" class="form-control"
									id="nomeActivityBoxFinestra" />
							</div>
						</div>

						<!-- Sezione giorno inizio activity -->
						<div class="col-md-6 col-lg-6">
							<label id="giornoInizio" for="giornoInizio">Scegli giorno
								di inizio:</label>
						</div>

						<div class="col-md-6 col-lg-6">
							<div class="form-group">
								<input name="giornoInizio" type="date"
									class="form-control giornoInizioBox"
									id="giornoInizioBoxFinestra" /> <span
									id="giornoInizioBoxErroreFinestra" class="card-Header errore"
									style="display: none">La data non è valida</span>

							</div>
						</div>

						<!-- Sezione giorno fine activity -->
						<div class="col-md-6 col-lg-6">
							<label id="giornoFine" for="giornoFine">Scegli giorno di
								fine:</label>
						</div>

						<div class="col-md-6 col-lg-6">
							<div class="form-group">
								<input name="giornoFine" type="date"
									class="form-control giornoFineBox" id="giornoFineBoxFinestra" /><span
									id="giornoFineBoxErroreFinestra" class="card-Header errore"
									style="display: none">La data di fine deve essere
									successiva a quella di inizio</span>
							</div>
						</div>

						<!-- Sezione ora inizio activity -->
						<div class="col-md-6 col-lg-6">
							<label id="oraInizio" for="oraInizio">Scegli ora di
								inizio:</label>
						</div>

						<div class="col-md-6 col-lg-6">
							<div class="form-group">
								<input name="oraInizio" type="time"
									class="form-control oraInizioBox" id="oraInizioBoxFinestra" />
							</div>
						</div>

						<!-- Sezione ora fine activity -->
						<div class="col-md-6 col-lg-6">
							<label id="oraFine" for="oraFine">Scegli ora di fine:</label>
						</div>

						<div class="col-md-6 col-lg-6">
							<div class="form-group">
								<input name="oraFine" type="time"
									class="form-control oraFineBox" id="oraFineBoxFinestra" /><span
									id="oraFineBoxErroreFinestra" class="card-Header errore"
									style="display: none">L'ora di fine deve essere
									succesiva a quella di inizio</span>
							</div>
						</div>

						<!-- Sezione dispositivo activity -->
						<div class="col-md-6 col-lg-6">
							<label id="dispositivo" for="dispositivo">Seleziona il
								dispositivo:</label>
						</div>

						<div class="col-md-6 col-lg-6">
							<div class="form-group">
								<div class="select">
									<select name="slct" id="selectDeviceActivityBoxFinestra">
										<option value="1">Bagno</option>
										<option value="2">Salone</option>
										<option value="3">Cucina</option>
										<option value="4">Camera letto</option>
									</select>
								</div>
							</div>
						</div>
					</div>

					<div class="row justify-content-center">
						<span class="card-Header errore" style="display: none"
							id="completaFormBoxFinestra">Completa la form</span> <span
							class="card-Header success" style="display: none"
							id="salvaFormBoxFinestra">Attivit&agrave; salvata
							correttamente</span> <span class="card-Header errore"
							style="display: none" id="sensoreNonEsisteFinestra">Il
							sensore scelto non &egrave; ancora installato nell'abitazione</span> <span
							class="card-Header errore" style="display: none"
							id="attivitaIncoerenteFinestra">L'attivit&agrave; inserita
							non è coerente con quelle già create</span> <span
							class="card-Header errore" style="display: none"
							id="attivitaStessoNomeFinestra">Esiste gi&agrave;
							un'attivit&agrave; con lo stesso nome</span> <span
							class="card-Header errore" style="display: none"
							id="erroreServerFinestra">Si &egrave; verificato un errore
							durante il salvateggio dell'attivit&agrave;</span>
					</div>

					<div class="container row justify-content-center" id="invia">
						<input name="inviaDati" type="button" value="Invia"
							onclick="registraAttivita('Finestra')" class="btn btn-primary"
							id="Invia" />
					</div>

				</div>

			</div>

			<div class="container text-center">
				<div class="row">
					<div class="col-lg-8 mx-auto">
						<hr class="star-dark mb-5">
					</div>
				</div>
			</div>

		</div>

	</div>


	<!-- Categoria umidita  -->
	<div class="portfolio-modal mfp-hide" id="categoria_umidita">

		<div class="portfolio-modal-dialog bg-white">
			<a
				class="close-button d-none d-md-block portfolio-modal-dismiss closeButton"
				id="closeButtonUmidita" href="#"> <i class="fa fa-3x fa-times"></i>
			</a>
			<div class="container">
				<h2 class="text-center text-secondary text-uppercase mb-0">Umidit&agrave;</h2>
				<hr class="star-dark mb-5">

				<div class="row justify-content-center">

					<div class="select" onclick="newActionUmidita()">
						<select name="slct" id="slct">
							<option>Cosa desideri fare?</option>
							<option value="1">Nuova attivit&agrave; periodica</option>
							<option value="2">Elimina attivit&agrave; periodica</option>
							<option value="3">Nuova regola</option>
							<option value="4">Elimina regola</option>
						</select>
					</div>
				</div>

				<div class="container" id="nuovaActivity" style="display: none;">

					<div class="row">

						<!-- Sezione nome activity -->
						<div class="col-md-6 col-lg-6">
							<label id="nomeActivity" for="nomeActivity">Inserisci il
								nome dell'attività:</label>
						</div>

						<div class="col-md-6 col-lg-6">
							<div class="form-group">
								<input name="nomeActivity" type="text" class="form-control"
									id="nomeActivityBoxUmidita" />
							</div>
						</div>

						<!-- Sezione giorno inizio activity -->
						<div class="col-md-6 col-lg-6">
							<label id="giornoInizio" for="giornoInizio">Scegli giorno
								di inizio:</label>
						</div>

						<div class="col-md-6 col-lg-6">
							<div class="form-group">
								<input name="giornoInizio" type="date"
									class="form-control giornoInizioBox"
									id="giornoInizioBoxUmidita" /> <span
									id="giornoInizioBoxErroreUmidita" class="card-Header errore"
									style="display: none">La data non è valida</span>
							</div>
						</div>

						<!-- Sezione giorno fine activity -->
						<div class="col-md-6 col-lg-6">
							<label id="giornoFine" for="giornoFine">Scegli giorno di
								fine:</label>
						</div>

						<div class="col-md-6 col-lg-6">
							<div class="form-group">
								<input name="giornoFine" type="date"
									class="form-control giornoFineBox" id="giornoFineBoxUmidita" /><span
									id="giornoFineBoxErroreUmidita" class="card-Header errore"
									style="display: none">La data di fine deve essere
									successiva a quella di inizio</span>
							</div>
						</div>

						<!-- Sezione ora inizio activity -->
						<div class="col-md-6 col-lg-6">
							<label id="oraInizio" for="oraInizio">Scegli ora di
								inizio:</label>
						</div>

						<div class="col-md-6 col-lg-6">
							<div class="form-group">
								<input name="oraInizio" type="time"
									class="form-control oraInizioBox" id="oraInizioBoxUmidita" />
							</div>
						</div>

						<!-- Sezione ora fine activity -->
						<div class="col-md-6 col-lg-6">
							<label id="oraFine" for="oraFine">Scegli ora di fine:</label>
						</div>

						<div class="col-md-6 col-lg-6">
							<div class="form-group">
								<input name="oraFine" type="time"
									class="form-control oraFineBox" id="oraFineBoxUmidita" /><span
									id="oraFineBoxErroreUmidita" class="card-Header errore"
									style="display: none">L'ora di fine deve essere
									succesiva a quella di inizio</span>
							</div>
						</div>

						<!-- Sezione dispositivo activity -->
						<div class="col-md-6 col-lg-6">
							<label id="dispositivo" for="dispositivo">Seleziona il
								dispositivo:</label>
						</div>

						<div class="col-md-6 col-lg-6">
							<div class="form-group">
								<div class="select">
									<select name="slct" id="selectDeviceActivityBoxUmidita">
										<option value="1">Deumidificatore</option>
									</select>
								</div>
							</div>
						</div>
					</div>

					<div class="row justify-content-center">
						<span class="card-Header errore" style="display: none"
							id="completaFormBoxUmidita">Completa la form</span> <span
							class="card-Header success" style="display: none"
							id="salvaFormBoxUmidita">Attivit&agrave; salvata
							correttamente</span> <span class="card-Header errore"
							style="display: none" id="sensoreNonEsisteUmidita">Il
							sensore scelto non &egrave; ancora installato nell'abitazione</span> <span
							class="card-Header errore" style="display: none"
							id="attivitaIncoerenteUmidita">L'attivit&agrave; inserita
							non è coerente con quelle già create</span> <span
							class="card-Header errore" style="display: none"
							id="attivitaStessoNomeUmidita">Esiste gi&agrave;
							un'attivit&agrave; con lo stesso nome</span> <span
							class="card-Header errore" style="display: none"
							id="erroreServerUmidita">Si &egrave; verificato un errore
							durante il salvateggio dell'attivit&agrave;</span>
					</div>

					<div class="container row justify-content-center" id="invia">
						<input name="inviaDati" type="button" value="Invia"
							onclick="registraAttivita('Umidita')" class="btn btn-primary"
							id="Invia" />
					</div>

				</div>

				<!-- Sezione programma regola -->
				<div class="container" id="nuovaRegola" style="display: none;">
					<div class="row">

						<!-- Sezione nome regola -->
						<div class="col-md-6 col-lg-6">
							<label id="nomeRegola" for="nomeRegola">Inserisci il nome
								della regola:</label>
						</div>

						<div class="col-md-6 col-lg-6">
							<div class="form-group">
								<input name="nomeRegola" type="text" class="form-control"
									id="nomeRegolaBoxUmidita" />
							</div>
						</div>

						<!-- Sezione verso regola -->
						<div class="col-md-6 col-lg-6">
							<label id="versoRegola" for="versoRegola">Quando
								l'umidit&agrave; è:</label>
						</div>

						<div class="col-md-6 col-lg-6">
							<div class="form-group">
								<div class="select">
									<select name="slct" id="selectCondizioneRegolaUmidita">
										<option value="1">Maggiore (&gt;)</option>
										<option value="2">Minore (&lt;)</option>
									</select>
								</div>
							</div>
						</div>

						<!-- Sezione valore regola -->
						<div class="col-md-6 col-lg-6">
							<label id="valoreRegola" for="valoreRegola">Inserisci il
								valore:</label>
						</div>

						<div class="col-md-6 col-lg-6">
							<div class="form-group">
								<input name="valoreRegola" type="text" class="form-control"
									id="valoreRegolaBoxUmidita"
									onkeypress='return (event.charCode >= 48 && event.charCode <= 57) || event.charCode == 45' />
							</div>
						</div>

						<!-- Sezione dispositivo regola -->
						<div class="col-md-6 col-lg-6">
							<label id="dispositivo" for="dispositivo">Accendi il
								dispositivo:</label>
						</div>

						<div class="col-md-6 col-lg-6">
							<div class="form-group">
								<div class="select">
									<select name="selct" id="selectDeviceRegolaUmidita">
										<option value="1">Deumidificatore</option>
									</select>
								</div>
							</div>
						</div>
					</div>

					<div class="row justify-content-center">
						<span class="card-Header errore" style="display: none"
							id="completaFormBoxRegolaUmidita">Completa la form</span> <span
							class="card-Header success" style="display: none"
							id="salvaFormBoxRegolaUmidita">Regola salvata
							correttamente</span> <span class="card-Header errore"
							style="display: none" id="sensoreNonEsisteRegolaUmidita">Il
							sensore scelto non &egrave; ancora installato nell'abitazione</span> <span
							class="card-Header errore" style="display: none"
							id="regolaStessoNomeUmidita">Esiste gi&agrave; una regola
							con lo stesso nome</span> <span class="card-Header errore"
							style="display: none" id="erroreServerRegolaUmidita">Si
							&egrave; verificato un errore durante il salvateggio della regola</span>
					</div>

					<div class="container row justify-content-center" id="invia">
						<input name="inviaDati" type="button" value="Invia"
							class="btn btn-primary" id="Invia"
							onclick="registraRegola('Umidita')" />
					</div>

				</div>

			</div>

			<div class="container text-center">
				<div class="row">
					<div class="col-lg-8 mx-auto">
						<hr class="star-dark mb-5">
					</div>
				</div>
			</div>

		</div>


	</div>


	<!--Categoria Sicurezza -->
	<div class="portfolio-modal mfp-hide" id="categoria_sicurezza">
		<div class="portfolio-modal-dialog bg-white">
			<a
				class="close-button d-none d-md-block portfolio-modal-dismiss closeButton"
				id="closeButtonSicurezza" href="#"> <i class="fa fa-3x fa-times"></i>
			</a>
			<div class="container">
				<h2 class="text-center text-secondary text-uppercase mb-0">Sicurezza</h2>
				<hr class="star-dark mb-5">

				<div class="row justify-content-center">

					<div class="select" onclick="newActionSicurezza()">
						<select name="slct" id="slct">
							<option>Cosa desideri fare?</option>
							<option value="1">Nuova attivit&agrave; periodica</option>
							<option value="2">Elimina attivit&agrave; periodica</option>
						</select>
					</div>
				</div>

				<div class="container" id="nuovaActivity" style="display: none;">
					<!-- style="display: none;" Devo inserirlo dopo -->
					<div class="row">

						<!-- Sezione nome activity -->
						<div class="col-md-6 col-lg-6">
							<label id="nomeActivity" for="nomeActivity">Inserisci il
								nome dell'attività:</label>
						</div>

						<div class="col-md-6 col-lg-6">
							<div class="form-group">
								<input name="nomeActivity" type="text" class="form-control"
									id="nomeActivityBoxSicurezza" />
							</div>
						</div>

						<!-- Sezione giorno inizio activity -->
						<div class="col-md-6 col-lg-6">
							<label id="giornoInizio" for="giornoInizio">Scegli giorno
								di inizio:</label>
						</div>

						<div class="col-md-6 col-lg-6">
							<div class="form-group">
								<input name="giornoInizio" type="date"
									class="form-control giornoInizioBox"
									id="giornoInizioBoxSicurezza" /> <span
									id="giornoInizioBoxErroreSicurezza" class="card-Header errore"
									style="display: none">La data non è valida</span>
							</div>
						</div>

						<!-- Sezione giorno fine activity -->
						<div class="col-md-6 col-lg-6">
							<label id="giornoFine" for="giornoFine">Scegli giorno di
								fine:</label>
						</div>

						<div class="col-md-6 col-lg-6">
							<div class="form-group">
								<input name="giornoFine" type="date"
									class="form-control giornoFineBox" id="giornoFineBoxSicurezza" /><span
									id="giornoFineBoxErroreSicurezza" class="card-Header errore"
									style="display: none">La data di fine deve essere
									successiva a quella di inizio</span>
							</div>
						</div>

						<!-- Sezione ora inizio activity -->
						<div class="col-md-6 col-lg-6">
							<label id="oraInizio" for="oraInizio">Scegli ora di
								inizio:</label>
						</div>

						<div class="col-md-6 col-lg-6">
							<div class="form-group">
								<input name="oraInizio" type="time"
									class="form-control oraInizioBox" id="oraInizioBoxSicurezza" />
							</div>
						</div>

						<!-- Sezione ora fine activity -->
						<div class="col-md-6 col-lg-6">
							<label id="oraFine" for="oraFine">Scegli ora di fine:</label>
						</div>

						<div class="col-md-6 col-lg-6">
							<div class="form-group">
								<input name="oraFine" type="time"
									class="form-control oraFineBox" id="oraFineBoxSicurezza" /><span
									id="oraFineBoxErroreSicurezza" class="card-Header errore"
									style="display: none">L'ora di fine deve essere
									succesiva a quella di inizio</span>
							</div>
						</div>

						<!-- Sezione dispositivo activity -->
						<div class="col-md-6 col-lg-6">
							<label id="dispositivo" for="dispositivo">Seleziona il
								dispositivo:</label>
						</div>

						<div class="col-md-6 col-lg-6">
							<div class="form-group">
								<div class="select">
									<select name="slct" id="selectDeviceActivityBoxSicurezza">
										<option value="1">Allarme</option>
									</select>
								</div>
							</div>
						</div>
					</div>

					<div class="row justify-content-center">
						<span class="card-Header errore" style="display: none"
							id="completaFormBoxSicurezza">Completa la form</span> <span
							class="card-Header success" style="display: none"
							id="salvaFormBoxSicurezza">Attivit&agrave; salvata
							correttamente</span> <span class="card-Header errore"
							style="display: none" id="sensoreNonEsisteSicurezza">Il
							sensore scelto non &egrave; ancora installato nell'abitazione</span> <span
							class="card-Header errore" style="display: none"
							id="attivitaIncoerenteSicurezza">L'attivit&agrave;
							inserita non è coerente con quelle già create</span> <span
							class="card-Header errore" style="display: none"
							id="attivitaStessoNomeSicurezza">Esiste gi&agrave;
							un'attivit&agrave; con lo stesso nome</span> <span
							class="card-Header errore" style="display: none"
							id="erroreServerSicurezza">Si &egrave; verificato un
							errore durante il salvateggio dell'attivit&agrave;</span>
					</div>

					<div class="container row justify-content-center" id="invia">
						<input name="inviaDati" type="button" value="Invia"
							onclick="registraAttivita('Sicurezza')" class="btn btn-primary"
							id="Invia" />
					</div>

				</div>

			</div>

			<div class="container text-center">
				<div class="row">
					<div class="col-lg-8 mx-auto">
						<hr class="star-dark mb-5">
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

	<!-- Custom scripts for this page -->
	<script src="js/activity.js"></script>

	<!-- Script for calendar -->
	<script src="fullcalendar-3.8.2/lib/jquery.min.js"></script>
	<script src="fullcalendar-3.8.2/lib/moment.min.js"></script>
	<script src="fullcalendar-3.8.2/fullcalendar.js"></script>
	<script src="js/calendar.js"></script>
	<script src="js/googleLogin.js"></script>
	<script src="js/facebookLogin.js"></script>
	<script src="https://apis.google.com/js/platform.js" async defer></script>

</body>

</html>