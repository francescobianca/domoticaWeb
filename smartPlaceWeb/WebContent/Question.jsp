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
<link rel="stylesheet" href="css/question.css">


</head>

<script type="text/javascript">
	document.querySelector('.searchbox [type="reset"]').addEventListener(
			'click', function() {
				this.parentNode.querySelector('input').focus();
			});
</script>

<body id="page-top">

	<!-- Serve per il logout con google -->
	<div class="g-signin2" style="display: none"></div>

	<!-- Navigation -->
	<nav
		class="navbar navbar-expand-lg bg-secondary fixed-top text-uppercase"
		id="mainNav">
	<div class="container">

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
					href="#portfolio">Questions</a></li>
				<li class="nav-item mx-0 mx-lg-1"><a
					class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger"
					href="#contact">Contact</a></li>
				<li class="nav-item mx-0 mx-lg-1"><a href="entryPage.jsp">
						<img id="home" class="img-fluid" src="images/homepage.png" alt=""
						style="padding-top: 20%">
				</a></li>
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
		<h2 class="text-center text-uppercase text-secondary mb-0">Questions</h2>
		<hr class="star-dark mb-5">

		<c:if test="${email!=null}">
			<div class="container row justify-content-center">
				<a class="portfolio-item d-block mx-auto" href="#newQuestion">
					<div class="portfolio-item-caption d-flex position-absolute h-100">
						<div
							class="portfolio-item-caption-content my-auto w-100 text-center text-white">
						</div>
					</div>
					<button class="button-secondary pure-button" id="ask">Ask
						Question</button>
				</a>
			</div>
		</c:if>

		<div class="container row justify-content-center"
			style="display: none">
			<a class="portfolio-item d-block mx-auto" href="#response">
				<div class="portfolio-item-caption d-flex position-absolute h-100">
					<div
						class="portfolio-item-caption-content my-auto w-100 text-center text-white">
					</div>
				</div>
				<button class="button-secondary pure-button" id="provaBottone"></button>
			</a>
		</div>



		<!-- Search box -->
		<div class="container row justify-content-center">
			<svg xmlns="http://www.w3.org/2000/svg" style="display:none"> <symbol
				xmlns="http://www.w3.org/2000/svg" id="sbx-icon-search-13"
				viewBox="0 0 40 40"> <path
				d="M26.804 29.01c-2.832 2.34-6.465 3.746-10.426 3.746C7.333 32.756 0 25.424 0 16.378 0 7.333 7.333 0 16.378 0c9.046 0 16.378 7.333 16.378 16.378 0 3.96-1.406 7.594-3.746 10.426l10.534 10.534c.607.607.61 1.59-.004 2.202-.61.61-1.597.61-2.202.004L26.804 29.01zm-10.426.627c7.323 0 13.26-5.936 13.26-13.26 0-7.32-5.937-13.257-13.26-13.257C9.056 3.12 3.12 9.056 3.12 16.378c0 7.323 5.936 13.26 13.258 13.26z"
				fill-rule="evenodd" /> </symbol> <symbol xmlns="http://www.w3.org/2000/svg"
				id="sbx-icon-clear-3" viewBox="0 0 20 20"> <path
				d="M8.114 10L.944 2.83 0 1.885 1.886 0l.943.943L10 8.113l7.17-7.17.944-.943L20 1.886l-.943.943-7.17 7.17 7.17 7.17.943.944L18.114 20l-.943-.943-7.17-7.17-7.17 7.17-.944.943L0 18.114l.943-.943L8.113 10z"
				fill-rule="evenodd" /> </symbol> </svg>

			<form novalidate="novalidate" onsubmit="return false;"
				class="searchbox sbx-google">
				<div role="search" class="sbx-google__wrapper">
					<input type="search" name="search"
						placeholder="Cosa stai cercando?" autocomplete="off"
						required="required" class="sbx-google__input" id="searchBox">
					<button type="button" title="Submit your search query."
						class="sbx-google__submit" onclick="cercaDomande()">
						<svg role="img" aria-label="Search"> <use
							xmlns:xlink="http://www.w3.org/1999/xlink"
							xlink:href="#sbx-icon-search-13"></use> </svg>
					</button>
					<button type="reset" title="Clear the search query."
						class="sbx-google__reset">
						<svg role="img" aria-label="Reset"> <use
							xmlns:xlink="http://www.w3.org/1999/xlink"
							xlink:href="#sbx-icon-clear-3"></use> </svg>
					</button>
				</div>
			</form>

		</div>



		<!-- Sezione domande -->
		<div class="container" id="listQuestion" style="padding-top: 3%">

			<div class="container row justify-content-center">
				<p id="ultimeDomande" class="aStyle">Last question</p>
				<p id="risultatoRicerca" class="aStyle" style="display:none">Risultati della ricerca:</p>
				<p id="noRisultati" class="aStyle errore" style="display:none">La ricerca non ha prodotto risultati</p>
			</div>

			<div class="row" id="lastQuestion">
				<c:forEach items="${domande}" var="item">
					<div class="col-md-6 col-lg-6" style="width: 50%">
						<label id="nomeActivity" for="nomeActivity">${item.categoria.nome}</label>
					</div>

					<div class="col-md-6 col-lg-6">
						<div class="form-group form-control">
							<a href="#response" id="question${item.id}"
								class="aStyle portfolio-item"
								style="color: #07C; margin-bottom: 0px;"
								onclick="visualizzaDomanda('${item.titolo}','${item.testo}','${item.id}','${item.utente.email}')">${item.titolo}</a>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>

	</div>
	</section>

	<br>
	<br>
	<br>

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
					Sviluppato da Bianca Francesco e Zarola Andrea per il corso di SIW
					</a>.
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

	<div class="portfolio-modal mfp-hide" id="newQuestion">

		<div class="portfolio-modal-dialog bg-white" style="min-height: 50vh;">
			<a
				class="close-button d-none d-md-block portfolio-modal-dismiss closeButton"
				id="closeButton" href="#"> <i class="fa fa-3x fa-times"></i>
			</a>
			<div class="container">
				<h2 class="text-center text-secondary text-uppercase mb-0">Fai
					la tua domanda</h2>
				<hr class="star-dark mb-5">
			</div>

			<div class="container">

				<div class="row justify-content-center">
					<div class="select" onclick="">
						<select name="slct" id="slct">
							<option value="-1">Scegli la categoria!</option>
						</select>
					</div>
				</div>

				<div class="row justify-content-center" id="title">
					<input name="Title" type="text" class="form-control" id="title"
						placeholder="Titolo" />
				</div>

				<div class="row justify-content-center" id="text">
					<textarea rows="4" cols="50" placeholder="Testo della domanda"
						style="resize: none;" id="text" class="form-control" maxlength="1000"></textarea>
				</div>

				<div class="row justify-content-center" id="text">

					<span class="errore card-Header" style="display: none" id="noTitle">Devi
						inserire il titolo!</span> <span class="success card-Header"
						style="display: none" id="okDomanda">Domanda salvata
						correttamente!</span>

				</div>

				<div class="row justify-content-center" id="inviaDomanda">
					<input name="inviaDati" type="button" id="inviaDomanda"
						value="Invia" class="btn btn-primary btn-block" />
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

	<div class="portfolio-modal mfp-hide" id="response">
		<div class="portfolio-modal-dialog bg-white" style="min-height: 50vh;">
			<a
				class="close-button d-none d-md-block portfolio-modal-dismiss closeButton"
				id="closeButton" href="#"> <i class="fa fa-3x fa-times"></i>
			</a>
			<div class="container">
				<h2 class="text-center text-secondary text-uppercase mb-0">Risposte</h2>
				<hr class="star-dark mb-5">

				<div class="container">

					<div class="row justify-content-center">
						<div class="form-group">
							<h3 id="titoloDellaDomanda"></h3>
						</div>
					</div>
					<h5 id="autoreDomanda"></h5>
					<div class="row justify-content-center">
						<div class="form-group form-control">
							<p id="testoDellaDomanda" class="aStyle"></p>
						</div>
					</div>

					<c:if test="${email!=null}">
						<div class="row justify-content-center" id="textRisp">
							<textarea rows="4" cols="50" placeholder="Inserisci la risposta"
								style="resize: none;" id="textRisposta" class="form-control" maxlength="1000"></textarea>
						</div>

						<div class="row justify-content-center" id="textRisp">

							<span class="errore card-Header" style="display: none"
								id="noRisp">Devi inserire una risposta!</span> <span
								class="success card-Header" style="display: none" id="okRisp">Risposta
								salvata correttamente!</span>

						</div>


						<div class="row justify-content-center" id="inviaRisposta">
							<input name="inviaDati" type="button" id="inviaRisposta"
								value="Invia" class="btn btn-primary btn-block" />
						</div>
					</c:if>


					<div class="container" id="containerResponse"
						style="padding-top: 3%">
						<div class="row" id="listResponse"></div>
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

	<!-- js for facebook -->
	<script src="js/facebookLogin.js"></script>
	<!-- js for google -->
	<script src="https://apis.google.com/js/platform.js" async defer></script>
	<script src="js/googleLogin.js"></script>

	<script src="js/question.js"></script>

</body>

</html>