function newActionTemperatura() {

	var element = document.getElementById("slct");
	var selectedItem = element.options[element.selectedIndex].value;

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
		leggiActivity('Temperatura');
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
		leggiRegole('Temperatura');
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
		leggiActivity('Luce');
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
		leggiActivity('Cancello');
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
		leggiActivity('Finestra');
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
		leggiActivity('Umidità');
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
		leggiRegole('Umidità');
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
		leggiActivity('Sicurezza');
	}

}

var regoleList = new Array();
var activityList = new Array();

function leggiActivity(categoria) {

	activityList = new Array();

	var element = $("#slctActivity" + categoria);

	$.ajax({
		url : 'LeggiAttivita',
		data : "tipo=" + categoria.toLowerCase(),
		dataType : 'json',
		type : 'POST',
		cache : false,
		error : function() {
			alert('error');
		},
		async : false
	}).done(function(risposta) {
		if (risposta != "nessunaAttivitaMemorizzata") {
			for (i in risposta) {
				var fields = risposta[i].giornoInizio.split(" ");
				var annoInizio = fields[5];
				var giornoInizio = fields[2];
				var meseInizio = getMese(fields[1]);
				var dInizio = "";
				var fieldsOraInizio = risposta[i].orarioInizio.split(" ");
				var oraInizio = fieldsOraInizio[3].split(":");

				var fieldsFine = risposta[i].giornoFine.split(" ");
				var annoFine = fieldsFine[5];
				var giornoFine = fieldsFine[2];
				var meseFine = getMese(fieldsFine[1]);
				var dFine = new Date();

				var fieldsOraFine = risposta[i].orarioFine.split(" ");
				var oraFine = fieldsOraFine[3].split(":");

				dInizio = giornoInizio + "-" + meseInizio + "-" + annoInizio;
				dFine = giornoFine + "-" + meseFine + "-" + annoFine;

				var inizio = oraInizio[0] + ":" + oraInizio[1];
				var fine = oraFine[0] + ":" + oraFine[1];

				var tipo = risposta[i].sensore.tipo;
				var stanza = risposta[i].sensore.stanza;

				var device = tipo + " " + stanza;

				var attivita = {
					id : risposta[i].id,
					title : risposta[i].nome,
					dataInizio : dInizio,
					dataFine : dFine,
					oraInizio : inizio,
					oraFine : fine,
					device : device
				}
				activityList.push(attivita);
			}

			$.each(activityList, function(index, value) {

				element.append($('<option/>', {
					value : activityList[index].id,
					text : activityList[index].title
				}));
			});
		}
	});

}

function leggiRegole(categoria) {
	regoleList = new Array();

	var element = $("#slctRegola" + categoria);
	$.ajax({
		url : 'LeggiRegole',
		data : "tipo=" + categoria.toLowerCase(),
		dataType : 'json',
		type : 'POST',
		cache : false,
		error : function() {
			alert('error');
		},
		async : false
	}).done(function(risposta) {
		console.log(risposta);
		if (risposta != "nessunaRegolaMemorizzata") {
			for (i in risposta) {
				var id = risposta[i].id;
				var nome = risposta[i].nome;
				var condizione = risposta[i].condizione;
				var valore = risposta[i].valoreMisurazione;
				var device = risposta[i].sensore.tipo;
				var regola = {
					id : id,
					title : nome,
					condizione : condizione,
					valore : valore,
					device : device
				}
				console.log(regola);
				regoleList.push(regola);
			}

			$.each(regoleList, function(index, value) {

				element.append($('<option/>', {
					value : regoleList[index].id,
					text : regoleList[index].title
				}));
			});
		}
	});
}

function getMese(mese) {
	var m;
	switch (mese) {
	case "Jan":
		m = 0;
		break;
	case "Feb":
		m = 1;
		break;
	case "Mar":
		m = 2;
		break;
	case "Apr":
		m = 3;
		break;
	case "May":
		m = 4;
		break;
	case "Jun":
		m = 5;
		break;
	case "Jul":
		m = 6;
		break;
	case "Aug":
		m = 7;
		break;
	case "Sep":
		m = 8;
		break;
	case "Oct":
		m = 9;
		break;
	case "Nov":
		m = 10;
		break;
	case "Dec":
		m = 11;
		break;
	default:
		m = 0;
	}
	return m;
}

var selectedItem = "-1"; // Elemento che poi vogliamo andare ad eliminare
var selectedItemRegola = "-1";

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

function eliminaAttivita(categoria) {
	if (selectedItem != -1) {
		$
				.ajax({
					url : 'EliminaAttivita',
					data : "id=" + selectedItem,
					type : 'POST',
					cache : false,
					error : function() {
						alert('error');
					},
					async : false

				})
				.done(
						function(risposta) {
							if (risposta == "eliminata") {
								$("#attivitaEliminata" + categoria).css(
										'display', 'block');
								setTimeout(function() {
									$("#attivitaEliminata" + categoria).css(
											'display', 'none');
								}, 4000);
								$("#containerEliminaActivity" + categoria).css(
										'display', 'none');
								$('#slctActivity' + categoria)
										.find('option')
										.remove()
										.end()
										.append(
												'<option value="-1">Quale attivit&agrave; eliminare?</option>');
								leggiActivity(categoria);

							}
						});
	}
}

function eliminaRegola(categoria) {
	if (selectedItemRegola != -1) {
		$
				.ajax({
					url : 'EliminaRegola',
					data : "id=" + selectedItemRegola,
					type : 'POST',
					cache : false,
					error : function() {
						alert('error');
					},
					async : false

				})
				.done(
						function(risposta) {
							if (risposta == "eliminata") {
								$("#regolaEliminata" + categoria).css(
										'display', 'block');
								setTimeout(function() {
									$("#regolaEliminata" + categoria).css(
											'display', 'none');
								}, 4000);
								$("#containerEliminaRegola" + categoria).css(
										'display', 'none');
								$('#slctRegola' + categoria)
										.find('option')
										.remove()
										.end()
										.append(
												'<option value="-1">Quale attivit&agrave; eliminare?</option>');
								leggiRegole(categoria);

							}
						});
	}
}

jQuery(document).ready(
		function() {

			// funzione che controlla che il giorno di inizio è successivo al
			// giorno
			// odierno
			$(".giornoInizioBox").on(
					'blur',
					function() {
						var id = $(this).prop("id");
						var categoria = id.substring(15);
						var today = new Date();
						var day = new Date($(this).val());
						var endDay = new Date($("#giornoFineBox" + categoria)
								.val());
						day.setMinutes(today.getMinutes());
						day.setHours(today.getHours());
						day.setSeconds(today.getSeconds());
						day.setMilliseconds(today.getMilliseconds());
						if (day.getTime() < today.getTime()) {
							$("#giornoInizioBoxErrore" + categoria).css(
									'display', 'block');
						} else {
							$("#giornoInizioBoxErrore" + categoria).css(
									'display', 'none');
						}
						if ($("#giornoFineBox" + categoria).val() != "") {
							if (endDay.getTime() < day.getTime()) {
								$("#giornoFineBoxErrore" + categoria).css(
										'display', 'block');
							} else {
								$("#giornoFineBoxErrore" + categoria).css(
										'display', 'none');
							}
						}
					});

			// funzione che controlla che il giorno di fine è successivo a
			// quello di
			// inizio
			$(".giornoFineBox").on(
					'blur',
					function() {
						var id = $(this).prop("id");
						var categoria = id.substring(13);
						var endDay = new Date($(this).val());
						var day = new Date($("#giornoInizioBox" + categoria)
								.val());
						if (endDay.getTime() < day.getTime()) {
							$("#giornoFineBoxErrore" + categoria).css(
									'display', 'block');
						} else {
							$("#giornoFineBoxErrore" + categoria).css(
									'display', 'none');
						}
					});

			// funzione che controlla che l'orario di fine è successivo a quello
			// di
			// inizio
			$(".oraFineBox").on(
					'blur',
					function() {
						var id = $(this).prop('id');
						var categoria = id.substring(10);
						if ($("#oraInizioBox" + categoria).val() != ""
								&& $(this).val() != "") {
							var startHour = new Date();
							var endHour = new Date(startHour);
							var start = $("#oraInizioBox" + categoria).val()
									.split(':');
							startHour.setHours(start[0]);
							startHour.setMinutes(start[1]);
							var end = $(this).val().split(':');
							endHour.setHours(end[0]);
							endHour.setMinutes(end[1]);
							if (endHour.getTime() <= startHour.getTime()) {
								$("#oraFineBoxErrore" + categoria).css(
										'display', 'block');
							} else {
								$("#oraFineBoxErrore" + categoria).css(
										'display', 'none');
							}
						}
					});

			$(".oraInizioBox").on(
					'blur',
					function() {
						var id = $(this).prop('id');
						var categoria = id.substring(12);
						if ($("#oraFineBox" + categoria).val() != ""
								&& $(this).val() != "") {
							var startHour = new Date();
							var endHour = new Date(startHour);
							var end = $("#oraFineBox" + categoria).val().split(
									':');
							endHour.setHours(end[0]);
							endHour.setMinutes(end[1]);
							var start = $(this).val().split(':');
							startHour.setHours(start[0]);
							startHour.setMinutes(start[1]);
							if (endHour.getTime() <= startHour.getTime()) {
								$("#oraFineBoxErrore" + categoria).css(
										'display', 'block');
							} else {
								$("#oraFineBoxErrore" + categoria).css(
										'display', 'none');
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

			$(".deleteBoxActivity").on(
					'change',
					function() {
						selectedItem = $(this).val();
						var id = $(this).prop('id');
						var categoria = id.substring(12);
						console.log(selectedItem);
						if (selectedItem != -1) {
							$("#containerEliminaActivity" + categoria).css(
									'display', 'block');
							for (i in activityList) {
								if (activityList[i].id == selectedItem) {
									$("#giornoInizioText" + categoria).text(
											activityList[i].dataInizio);
									$("#giornoFineText" + categoria).text(
											activityList[i].dataFine);
									$("#oraInizioText" + categoria).text(
											activityList[i].oraInizio);
									$("#oraFineText" + categoria).text(
											activityList[i].oraFine);
									$("#dispositivoText" + categoria).text(
											activityList[i].device);
								}
							}

						} else {
							$("#containerEliminaActivity" + categoria).css(
									'display', 'none');
						}

					});

			$(".deleteBoxRegola").on(
					'change',
					function() {
						selectedItemRegola = $(this).val();
						var id = $(this).prop('id');
						var categoria = id.substring(10);
						console.log(selectedItemRegola);
						if (selectedItem != -1) {
							$("#containerEliminaRegola" + categoria).css(
									'display', 'block');
							for (i in regoleList) {
								if (regoleList[i].id == selectedItemRegola) {
									$("#versoText" + categoria).text(
											regoleList[i].condizione);
									$("#valoreText" + categoria).text(
											regoleList[i].valore);
									$("#dispositivoTextRegola" + categoria)
											.text(regoleList[i].device);
								}
							}

						} else {
							$("#containerEliminaRegola" + categoria).css(
									'display', 'none');
						}

					});

		})