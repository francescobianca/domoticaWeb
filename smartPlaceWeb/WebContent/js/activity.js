function newActionTemperatura() {

	var element = document.getElementById("slct");
	var selectedItem = element.options[element.selectedIndex].value;

	console.log(selectedItem)
	if (selectedItem == 1) {
		pulisciFormActivity("Temperatura");
		$("#nuovaRegola").css('display', 'none');
		$("#nuovaActivity").css('display', 'block');
		$("#eliminaActivity").css('display', 'none');
		$("#eliminaRegola").css('display', 'none');
	} else if (selectedItem == 2) {
		$("#nuovaRegola").css('display', 'none');
		$("#nuovaActivity").css('display', 'none');
		$("#eliminaActivity").css('display', 'block');
		$("#eliminaRegola").css('display', 'none');
	} else if (selectedItem == 3) {
		pulisciFormRegola("Temperatura");
		$("#nuovaActivity").css('display', 'none');
		$("#nuovaRegola").css('display', 'block');
		$("#eliminaActivity").css('display', 'none');
		$("#eliminaRegola").css('display', 'none');
	} else if (selectedItem == 4) {
		$("#nuovaActivity").css('display', 'none');
		$("#nuovaRegola").css('display', 'none');
		$("#eliminaActivity").css('display', 'none');
		$("#eliminaRegola").css('display', 'block');
	}

}

function newActionLuci() {

	var element = document.getElementById("slct");
	var selectedItem = element.options[element.selectedIndex].value;

	if (selectedItem == 1) {
		$("#nuovaActivity").css('display', 'block');
		$("#eliminaActivity").css('display', 'none');
	} else if (selectedItem == 2) {
		$("#nuovaActivity").css('display', 'none');
		$("#eliminaActivity").css('display', 'block');
	}

}

function newActionCancello() {

	var element = document.getElementById("slct");
	var selectedItem = element.options[element.selectedIndex].value;

	if (selectedItem == 1) {
		$("#nuovaActivity").css('display', 'block');
		$("#eliminaActivity").css('display', 'none');
	} else if (selectedItem == 2) {
		$("#nuovaActivity").css('display', 'none');
		$("#eliminaActivity").css('display', 'block');
	}

}

function newActionFinestre() {

	var element = document.getElementById("slct");
	var selectedItem = element.options[element.selectedIndex].value;

	if (selectedItem == 1) {
		$("#nuovaActivity").css('display', 'block');
		$("#eliminaActivity").css('display', 'none');
	} else if (selectedItem == 2) {
		$("#nuovaActivity").css('display', 'none');
		$("#eliminaActivity").css('display', 'block');
	}

}

function newActionUmidita() {

	var element = document.getElementById("slct");
	var selectedItem = element.options[element.selectedIndex].value;

	if (selectedItem == 1) {
		pulisciFormActivity("Umidita")
		$("#nuovaRegola").css('display', 'none');
		$("#nuovaActivity").css('display', 'block');
		$("#eliminaActivity").css('display', 'none');
		$("#eliminaRegola").css('display', 'none');
	} else if (selectedItem == 2) {
		$("#nuovaRegola").css('display', 'none');
		$("#nuovaActivity").css('display', 'none');
		$("#eliminaActivity").css('display', 'block');
		$("#eliminaRegola").css('display', 'none');
	} else if (selectedItem == 3) {
		pulisciFormRegola("Umidita");
		$("#nuovaActivity").css('display', 'none');
		$("#nuovaRegola").css('display', 'block');
		$("#eliminaActivity").css('display', 'none');
		$("#eliminaRegola").css('display', 'none');
	} else if (selectedItem == 4) {
		$("#nuovaActivity").css('display', 'none');
		$("#nuovaRegola").css('display', 'none');
		$("#eliminaActivity").css('display', 'none');
		$("#eliminaRegola").css('display', 'block');
	}

}

function newActionSicurezza() {

	var element = document.getElementById("slct");
	var selectedItem = element.options[element.selectedIndex].value;

	if (selectedItem == 1) {
		$("#nuovaActivity").css('display', 'block');
		$("#eliminaActivity").css('display', 'none');
	} else if (selectedItem == 2) {
		$("#nuovaActivity").css('display', 'none');
		$("#eliminaActivity").css('display', 'block');
	}

}

function pulisciFormRegola(categoria) {
	$("#nomeRegolaBox" + categoria).val("");
	$("#valoreRegolaBox" + categoria).val("");
	$("#completaFormBoxRegola" + categoria).css('display', 'none');
	$("#salvaFormBoxRegola" + categoria).css('display', 'none');
	$("#sensoreNonEsisteRegola" + categoria).css('display', 'none');
	$("#regolaStessoNome" + categoria).css('display', 'none');
	$("#erroreServerRegola" + categoria).css('display', 'none');
}

function pulisciMessaggiErroreRegola(categoria) {
	$("#completaFormBoxRegola" + categoria).css('display', 'none');
	$("#sensoreNonEsisteRegola" + categoria).css('display', 'none');
	$("#regolaStessoNome" + categoria).css('display', 'none');
	$("#erroreServerRegola" + categoria).css('display', 'none');
}

function pulisciFormActivity(categoria) {
	$("#nomeActivityBox" + categoria).val("");
	$("#giornoInizioBox" + categoria).val("");
	$("#giornoFineBox" + categoria).val("");
	$("#oraInizioBox" + categoria).val("");
	$("#oraFineBox" + categoria).val("");
	$("#giornoInizioBoxErrore" + categoria).css('display', 'none');
	$("#giornoFineBoxErrore" + categoria).css('display', 'none');
	$("#oraFineBoxErrore" + categoria).css('display', 'none');
	$("#completaFormBox" + categoria).css('display', 'none');
	$("#salvaFormBox" + categoria).css('display', 'none');
	$("#sensoreNonEsiste" + categoria).css('display', 'none');
	$("#attivitaIncoerente" + categoria).css('display', 'none');
	$("#attivitaStessoNome" + categoria).css('display', 'none');
	$("#erroreServer" + categoria).css('display', 'none');
}

function pulisciMessaggiErrore(categoria) {
	$("#completaFormBox" + categoria).css('display', 'none');
	$("#sensoreNonEsiste" + categoria).css('display', 'none');
	$("#attivitaIncoerente" + categoria).css('display', 'none');
	$("#attivitaStessoNome" + categoria).css('display', 'none');
	$("#erroreServer" + categoria).css('display', 'none');
}

function registraAttivita(categoria) {
	if ($("#nomeActivityBox" + categoria).val() != ""
			&& $("#giornoInizioBox" + categoria).val() != ""
			&& $("#giornoFineBox" + categoria).val() != ""
			&& $("#oraInizioBox" + categoria).val() != ""
			&& $("#oraFineBox" + categoria).val() != ""
			&& $("#giornoInizioBoxErrore" + categoria).css('display') == "none"
			&& $("#giornoFineBoxErrore" + categoria).css('display') == "none"
			&& $("#oraFineBoxErrore" + categoria).css('display') == "none") {
		$("#completaFormBox" + categoria).css('display', 'none');
		var nomeAttivita = $("#nomeActivityBox" + categoria);
		var giornoInizio = $("#giornoInizioBox" + categoria);
		var giornoFine = $("#giornoFineBox" + categoria);
		var oraInizio = $("#oraInizioBox" + categoria);
		var oraFine = $("#oraFineBox" + categoria);
		var tipo = categoria.toLowerCase();
		var stanza = $(
				"#selectDeviceActivityBox" + categoria + " option:selected")
				.text().toLowerCase();
		if (stanza == "camera letto") {
			stanza = "cameraLetto";
		}
		if (tipo == "temperatura" || tipo == "cancello" || tipo == "umidita"
				|| tipo == "sicurezza") {
			tipo = stanza;
			stanza = "casa";
		}
		$
				.ajax(
						{
							url : 'PianificaAttivita',
							data : "nome=" + nomeAttivita.val()
									+ "&giornoInizio=" + giornoInizio.val()
									+ "&giornoFine=" + giornoFine.val()
									+ "&oraInizio=" + oraInizio.val()
									+ "&oraFine=" + oraFine.val() + "&tipo="
									+ tipo + "&stanza=" + stanza,
							type : 'POST',
							cache : false,
							error : function() {
								alert('error');
							},
							async : false

						})
				.done(
						function(risposta) {
							if (risposta != "errore") {
								console.log(risposta)
								// caso in cui salvo l'attività correttamente
								if (risposta == "salvata") {
									pulisciFormActivity(categoria);
									$("#salvaFormBox" + categoria).css(
											'display', 'block');
									console.log("ok")
									setTimeout(function() {
										$("#salvaFormBox" + categoria).css(
												'display', 'none');
									}, 4000);
								} else if (risposta == "sensoreNonEsiste") {
									pulisciMessaggiErrore(categoria);
									$("#sensoreNonEsiste" + categoria).css(
											'display', 'block');
								} else if (risposta == "attivitaIncoerente") {
									pulisciMessaggiErrore(categoria);
									$("#attivitaIncoerente" + categoria).css(
											'display', 'block');
								} else if (risposta == "attivitaGiaPresenteConLoStessoNome") {
									pulisciMessaggiErrore(categoria);
									$("#attivitaStessoNome" + categoria).css(
											'display', 'block');
								}
							} else {
								pulisciMessaggiErrore(categoria);
								$("#erroreServer" + categoria).css('display',
										'block');
							}
						});
	} else {
		$("#completaFormBox" + categoria).css('display', 'block');
	}
}

function registraRegola(categoria) {
	if ($("#nomeRegolaBox" + categoria).val() != ""
			&& $("#valoreRegolaBox" + categoria).val() != "") {
		var nomeRegola = $("#nomeRegolaBox" + categoria);
		var valoreRegola = $("#valoreRegolaBox" + categoria);
		var condizione = $(
				"#selectCondizioneRegola" + categoria + " option:selected")
				.text().toLowerCase();
		condizione = condizione.substring(0, condizione.length - 4);
		var sensore = $("#selectDeviceRegola" + categoria + " option:selected")
				.text().toLowerCase();
		console.log(nomeRegola.val());
		console.log(valoreRegola.val());
		console.log(condizione);
		console.log(sensore);
		$
				.ajax(
						{
							url : 'PianificaRegola',
							data : "nome=" + nomeRegola.val() + "&valore="
									+ valoreRegola.val() + "&sensore="
									+ sensore + "&condizione=" + condizione,
							type : 'POST',
							cache : false,
							error : function() {
								alert('error');
							},
							async : false

						})
				.done(
						function(risposta) {
							if (risposta != "errore") {
								console.log(risposta)
								// caso in cui salvo l'attività correttamente
								if (risposta == "salvata") {
									pulisciFormRegola(categoria);
									$("#salvaFormBoxRegola" + categoria).css(
											'display', 'block');
									console.log("ok")
									setTimeout(function() {
										$("#salvaFormBoxRegola" + categoria)
												.css('display', 'none');
									}, 4000);
								} else if (risposta == "sensoreNonEsiste") {
									pulisciMessaggiErroreRegola(categoria);
									$("#sensoreNonEsisteRegola" + categoria)
											.css('display', 'block');
								} else if (risposta == "regolaGiaPresenteConLoStessoNome") {
									pulisciMessaggiErroreRegola(categoria);
									$("#regolaStessoNome" + categoria).css(
											'display', 'block');
								}
							} else {
								pulisciMessaggiErroreRegola(categoria);
								$("#erroreServerRegola" + categoria).css(
										'display', 'block');
							}
						});
	} else {
		$("#completaFormBoxRegola" + categoria).css('display', 'block');
	}
}

jQuery(document).ready(function() {

	// funzione che controlla che il giorno di inizio è successivo al giorno
	// odierno
	$(".giornoInizioBox").on('blur', function() {
		var id = $(this).prop("id");
		var categoria = id.substring(15);
		var today = new Date();
		var day = new Date($(this).val());
		var endDay = new Date($("#giornoFineBox" + categoria).val());
		day.setMinutes(today.getMinutes());
		day.setHours(today.getHours());
		day.setSeconds(today.getSeconds());
		day.setMilliseconds(today.getMilliseconds());
		if (day.getTime() < today.getTime()) {
			$("#giornoInizioBoxErrore" + categoria).css('display', 'block');
		} else {
			$("#giornoInizioBoxErrore" + categoria).css('display', 'none');
		}
		if ($("#giornoFineBox" + categoria).val() != "") {
			if (endDay.getTime() < day.getTime()) {
				$("#giornoFineBoxErrore" + categoria).css('display', 'block');
			} else {
				$("#giornoFineBoxErrore" + categoria).css('display', 'none');
			}
		}
	});

	// funzione che controlla che il giorno di fine è successivo a quello di
	// inizio
	$(".giornoFineBox").on('blur', function() {
		var id = $(this).prop("id");
		var categoria = id.substring(13);
		var endDay = new Date($(this).val());
		var day = new Date($("#giornoInizioBox" + categoria).val());
		if (endDay.getTime() < day.getTime()) {
			$("#giornoFineBoxErrore" + categoria).css('display', 'block');
		} else {
			$("#giornoFineBoxErrore" + categoria).css('display', 'none');
		}
	});

	// funzione che controlla che l'orario di fine è successivo a quello di
	// inizio
	$(".oraFineBox").on('blur', function() {
		var id = $(this).prop('id');
		var categoria = id.substring(10);
		if ($("#oraInizioBox" + categoria).val() != "" && $(this).val() != "") {
			var startHour = new Date();
			var endHour = new Date(startHour);
			var start = $("#oraInizioBox" + categoria).val().split(':');
			startHour.setHours(start[0]);
			startHour.setMinutes(start[1]);
			var end = $(this).val().split(':');
			endHour.setHours(end[0]);
			endHour.setMinutes(end[1]);
			if (endHour.getTime() <= startHour.getTime()) {
				$("#oraFineBoxErrore" + categoria).css('display', 'block');
			} else {
				$("#oraFineBoxErrore" + categoria).css('display', 'none');
			}
		}
	});

	$(".oraInizioBox").on('blur', function() {
		var id = $(this).prop('id');
		var categoria = id.substring(12);
		if ($("#oraFineBox" + categoria).val() != "" && $(this).val() != "") {
			var startHour = new Date();
			var endHour = new Date(startHour);
			var end = $("#oraFineBox" + categoria).val().split(':');
			endHour.setHours(end[0]);
			endHour.setMinutes(end[1]);
			var start = $(this).val().split(':');
			startHour.setHours(start[0]);
			startHour.setMinutes(start[1]);
			if (endHour.getTime() <= startHour.getTime()) {
				$("#oraFineBoxErrore" + categoria).css('display', 'block');
			} else {
				$("#oraFineBoxErrore" + categoria).css('display', 'none');
			}
		}
	});

	// funzione che pulisce le schermate
	$(".closeButton").on('click', function() {
		var id = $(this).prop('id');
		var categoria = id.substring(11);
		pulisciFormActivity(categoria);
		pulisciFormRegola(categoria);
	});

})