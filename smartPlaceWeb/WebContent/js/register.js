//Controlla che non sia già presente sul db questa email contattando la servlet
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
				alert("email già presente");
				$("#Avanti").prop("disabled",true);
			} else {
				alert("va benissimo");
				$("#Avanti").prop("disabled",false);
			}
		});
	} else {
		alert("inserisci un valore");
	}
}

// Controllo che le password inserite siano uguali
function checkPassword() {
	if ($("#passwordBox").val() != "" && $("#confermaPasswordBox").val() != "") {
		if ($("#passwordBox").val() === $("#confermaPasswordBox").val()) {
			alert("uguali");
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
		} else {
			alert("non sono uguali");
		}
	}
}

// controlla che il nome non contenga numeri
function checkNome() {

	var valore = $("#nomeBox").val();
	var errore = valore.match(/\d+/g);
	if (errore != null || valore == "") {
		//alert("errore");
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
			;
		});
	}

}

// controlla che il cognome non contenga numeri
function checkCognome() {

	var valore = $("#cognomeBox").val();
	var errore = valore.match(/\d+/g);
	if (errore != null || valore == "") {
		//alert("errore");
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
			;
		});
	}

}

function sendDate() {
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
		;
	});
}

function checkIndirizzoIP() {
	
	var pattern=/^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/;
	var valore = $("#indirizzoIP").val();
	if(pattern.test(valore)){
		$.ajax({
			url : 'iscriviUtente',
			data : "indirizzoIP=" + $("#indirizzoIP").val() + "&checkIndirizzoIP=check",
			type : 'POST',
			cache : false,
			error : function() {
				alert('error');
			},
			async : false

		}).done(function(risposta) {
			;
		});
	}else{
		alert("errore");
	}
}

function checkPorta() {
	var pattern=/^\d+$/;
	var valore = $("#porta").val();
	if(pattern.test(valore)){
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
	}else{
		alert("errore");
	}
}

jQuery(document).ready(function() {
	$("#Avanti").prop("disabled",true);
	$("#registratiButton").prop("disabled",true);
	$("#containerDispositivo").hide();
	$("#Avanti").on("click", function() {
		$("#containerRegistrazione").hide();
		$("#containerDispositivo").show();
	});
});