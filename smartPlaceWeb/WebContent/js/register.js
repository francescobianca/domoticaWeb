//Controlla che tutte le form della prima registrazione siano soddisfatte
function checkAllFirstForm() {
	var green_color = "rgba(111, 214, 111, 0.75) none repeat scroll 0% 0% / auto padding-box border-box";
	var emailColor = $("#emailBox").css('background');
	var nomeColor = $("#nomeBox").css('background');
	var cognomeColor = $("#cognomeBox").css('background');
	var passwordColor = $("#confermaPasswordBox").css('background');
	var dataColor = $("#dataBox").css('background');
	if (emailColor == green_color && nomeColor == green_color
			&& cognomeColor == green_color && passwordColor == green_color
			&& dataColor == green_color) {
		$("#Avanti").prop("disabled", false);
	}else{
		$("#Avanti").prop("disabled", true);
	}
}

//Controlla che tutte le form della seconda fase della registrazione siano soddisfatte
function checkAllSecondForm(){
	var green_color = "rgba(111, 214, 111, 0.75) none repeat scroll 0% 0% / auto padding-box border-box";
	var indirizzoColor= $("#indirizzoIP").css('background');
	var portaColor=$("#porta").css('background');
	if(indirizzoColor==green_color && portaColor==green_color){
		$("#registratiButton").prop("disabled", false);
	}else{
		$("#registratiButton").prop("disabled", true);
	}
}
// Controlla che non sia già presente sul db questa email contattando la servlet
function checkEmail() {
	if ($("#emailBox").val() != "") {
		$.ajax({
			url : 'iscriviUtente',
			data : "email=" + $("#emailBox").val() + "&checkEmail=check",
			type : 'POST',
			cache : false,
			error : function() {
				alert('error');
			},
			async : false

		}).done(function(risposta) {
			if (risposta != "ok") {
				$("#erroreEmail").css('display', 'block');
				$("#emailBox").removeClass("validFormBox");
				$("#emailBox").addClass("errorFormBox");
				$("#Avanti").prop("disabled", true);
			} else {
				$("#emailBox").removeClass("errorFormBox");
				$("#emailBox").addClass("validFormBox");
				$("#erroreEmail").css('display', 'none');
				checkAllFirstForm();
			}
		});
	} else {
		alert("inserisci un valore");
	}
}

function passwordOk() {
	$("#ErrorePassword").css('display', 'none');
	$("#passwordBox").removeClass("errorFormBox");
	$("#passwordBox").addClass("validFormBox");
}

function errorePassword() {
	$("#ErrorePassword").css('display', 'block');
	$("#passwordBox").removeClass("validFormBox");
	$("#passwordBox").addClass("errorFormBox");
}
// controlla che la password rispetti i requisiti
// contiene almeno un numero
// contiene almeno una lettera minuscola
// contiene almeno una lettera maiuscola
// deve essere lunga tra gli 8 e i 16 caratteri
function controllaRequisitiPassword() {
	if ($("#passwordBox").val() != "") {
		var password = $("#passwordBox").val();
		if (password.length < 8 || password.length > 16) {
			errorePassword();
			return;
		}
		var check = false;
		// controllo se la password contiene almeno un numero
		for (var i = 0; i < password.length; i++) {
			if (password.charAt(i) >= "0" && password.charAt(i) <= "9")
				check = true;
		}
		if (!check) {
			errorePassword();
			return;
		}
		check = false;
		// controllo se la password contiene almeno una lettera minuscola
		for (var i = 0; i < password.length; i++) {
			if (password.charAt(i) >= "a" && password.charAt(i) <= "z")
				check = true;
		}
		if (!check) {
			errorePassword();
			return;
		}
		check = false;
		// controllo se la password contiene almeno una lettera maiuscola
		for (var i = 0; i < password.length; i++) {
			if (password.charAt(i) >= "A" && password.charAt(i) <= "Z")
				check = true;
		}
		if (!check) {
			errorePassword();
			return;
		}
		passwordOk();
	}
}

// Controllo che le password inserite siano uguali
function checkPassword() {
	var green_color = "rgba(111, 214, 111, 0.75) none repeat scroll 0% 0% / auto padding-box border-box";
	var passwordColor = $("#passwordBox").css('background');
	if (green_color == passwordColor) {
		if ($("#passwordBox").val() != ""
				&& $("#confermaPasswordBox").val() != "") {
			if ($("#passwordBox").val() == $("#confermaPasswordBox").val()) {
				$.ajax(
						{
							url : 'iscriviUtente',
							data : "password=" + $("#passwordBox").val()
									+ "&checkPassword=check",
							type : 'POST',
							cache : false,
							error : function() {
								alert('error');
							},
							async : false

						}).done(function(risposta) {
					;
				});
				$("#confermaPasswordBox").removeClass("errorFormBox");
				$("#confermaPasswordBox").addClass("validFormBox");
				$("#passwordBox").removeClass("errorFormBox");
				$("#passwordBox").addClass("validFormBox");
				$("#ErroreConfermaPassword").css('display','none');
				checkAllFirstForm();
			} else {
				$("#passwordBox").removeClass("validFormBox");
				$("#passwordBox").addClass("errorFormBox");
				$("#confermaPasswordBox").removeClass("validFormBox");
				$("#confermaPasswordBox").addClass("errorFormBox");
				$("#ErroreConfermaPassword").css('display','block');
			}
		}
	}
}

// controlla che il nome non contenga numeri
function checkNome() {
	var valore = $("#nomeBox").val();
	var errore = valore.match(/\d+/g);
	if (errore != null || valore == "") {
		// alert("errore");
		$("#nomeBox").removeClass("validFormBox");
		$("#nomeBox").addClass("errorFormBox");
	} else {
		$.ajax({
			url : 'iscriviUtente',
			data : "nome=" + $("#nomeBox").val() + "&checkNome=check",
			type : 'POST',
			cache : false,
			error : function() {
				alert('error');
			},
			async : false

		}).done(function(risposta) {
			$("#nomeBox").removeClass("errorFormBox");
			$("#nomeBox").addClass("validFormBox");
			checkAllFirstForm();
		});
	}

}

// controlla che il cognome non contenga numeri
function checkCognome() {
	var valore = $("#cognomeBox").val();
	var errore = valore.match(/\d+/g);
	if (errore != null || valore == "") {
		// alert("errore");
		$("#cognomeBox").removeClass("validFormBox");
		$("#cognomeBox").addClass("errorFormBox");
	} else {
		$.ajax({
			url : 'iscriviUtente',
			data : "cognome=" + $("#cognomeBox").val() + "&checkCognome=check",
			type : 'POST',
			cache : false,
			error : function() {
				alert('error');
			},
			async : false

		}).done(function(risposta) {
			$("#cognomeBox").removeClass("errorFormBox");
			$("#cognomeBox").addClass("validFormBox");
			checkAllFirstForm();
		});
	}

}

function sendDate() {
	if ($("#dataBox").val() != "") {
		$.ajax({
			url : 'iscriviUtente',
			data : "dataNascita=" + $("#dataBox").val() + "&checkData=check",
			type : 'POST',
			cache : false,
			error : function() {
				alert('error');
			},
			async : false

		}).done(function(risposta) {
			$("#dataBox").removeClass("errorFormBox");
			$("#dataBox").addClass("validFormBox");
			checkAllFirstForm();
		});
	} else {
		$("#dataBox").removeClass("validFormBox");
		$("#dataBox").addClass("errorFormBox");
	}
}

function checkIndirizzoIP() {
	var pattern = /^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/;
	var valore = $("#indirizzoIP").val();
	if (pattern.test(valore)) {
		$.ajax(
				{
					url : 'iscriviUtente',
					data : "indirizzoIP=" + $("#indirizzoIP").val()
							+ "&checkIndirizzoIP=check",
					type : 'POST',
					cache : false,
					error : function() {
						alert('error');
					},
					async : false

				}).done(function(risposta) {
			;
		});
		$("#ErroreIndirizzo").css('display','none');
		$("#indirizzoIP").removeClass("errorFormBox");
		$("#indirizzoIP").addClass("validFormBox");
		checkAllSecondForm();
	} else {
		$("#ErroreIndirizzo").css('display','block');
		$("#indirizzoIP").removeClass("validFormBox");
		$("#indirizzoIP").addClass("errorFormBox");
	}
}

function checkPorta() {
	var pattern = /^\d+$/;
	var valore = $("#porta").val();
	if (pattern.test(valore)) {
		$.ajax({
			url : 'iscriviUtente',
			data : "porta=" + $("#porta").val() + "&checkPorta=check",
			type : 'POST',
			cache : false,
			error : function() {
				alert('error');
			},
			async : false

		}).done(function(risposta) {
			;
		});
		$("#ErrorePorta").css('display','none');
		$("#porta").removeClass("errorFormBox");
		$("#porta").addClass("validFormBox");
		checkAllSecondForm()
	} else {
		$("#ErrorePorta").css('display','block');
		$("#porta").removeClass("validFormBox");
		$("#porta").addClass("errorFormBox");
	}
}

jQuery(document).ready(function() {
	$("#ErrorePassword").hide();
	$("#Avanti").prop("disabled", true);
	$("#registratiButton").prop("disabled", true);
	$("#containerDispositivo").hide();
	$("#Avanti").on("click", function() {
		$("#containerRegistrazione").hide();
		$("#containerDispositivo").show();
	});
	$("#indietroButton").on("click", function() {
		$("#containerDispositivo").hide();
		$("#containerRegistrazione").show();
	});
});