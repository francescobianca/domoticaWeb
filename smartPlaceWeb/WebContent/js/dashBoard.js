jQuery(document).ready(function() {

	$("#cancello_left").on('click', function(event) {
		var stanza = $(this).prop('id').substring(12);
		$.ajax({
			url : 'ApriCancello',
			data : "stanza=casa&operation=down",
			type : 'POST',
			cache : false,
			error : function() {
				alert('error');
				event.preventDefault();
			},
			async : false

		}).done(function(risposta) {
			if (risposta != "errore") {
				var field=risposta.split("-");
				var value = $("#cancello").attr('aria-valuenow');
				var newValue = parseInt(field[1]);
				$("#cancello").attr('aria-valuenow', newValue);
				var css = 100 / 60 * newValue;
				var a = css + '%';
				$("#cancello").css('width', a);
				console.log($("#cancello").attr('aria-valuenow'));
			} else {
				// caso in cui va bene la modifica dello stato
			}
		});
	});

	$("#cancello_right").on('click', function(event) {
		var stanza = $(this).prop('id').substring(12);
		$.ajax({
			url : 'ApriCancello',
			data : "stanza=casa&operation=up",
			type : 'POST',
			cache : false,
			error : function() {
				alert('error');
				event.preventDefault();
			},
			async : false

		}).done(function(risposta) {
			if (risposta != "errore") {
				var field=risposta.split("-");
				var value = $("#cancello").attr('aria-valuenow');
				var newValue = parseInt(field[1]);
				$("#cancello").attr('aria-valuenow', newValue);
				var css = 100 / 60 * newValue;
				var a = css + '%';
				$("#cancello").css('width', a);
				console.log($("#cancello").attr('aria-valuenow'));
			} else {
				// caso in cui va bene la modifica dello stato
			}
		});
	});

	$("#ventilatore").on('click', function(event) {
		var stato
		var statoPrecedente;
		var s;
		if ($(this).prop('checked')) {
			stato = true;
			statoPrecedente = false;
			s="1";
		} else {
			stato = false;
			statoPrecedente = true;
			s="0"
		}
		console.log(statoPrecedente);
		var a=$(this);
		$.ajax({
			url : 'MonitoraTemperatura',
			data : "stanza=casa&stato=" + s,
			type : 'POST',
			cache : false,
			error : function() {
				alert('error');
				event.preventDefault();
			},
			async : false

		}).done(function(risposta) {
			if (risposta != "ok") {
				a.prop('checked', statoPrecedente);
			} else {
				// caso in cui va bene la modifica dello stato
			}
		});
	});

	$(".checkbox-luci").on('click', function(event) {
		var stato;
		var statoPrecedente;
		var s;
		console.log($(this).prop('checked'));
		if ($(this).prop('checked')) {
			stato = true;
			s="1";
			console.log("checked");
			statoPrecedente = false;
		} else {
			console.log("unchecked");
			stato = false;
			s="0";
			statoPrecedente = true;
		}
		var a=$(this);
		var stanza = $(this).prop('id').substring(5);
		console.log(stato)
		console.log(statoPrecedente)
		$.ajax({
			url : 'AccendiLuci',
			data : "stanza=" + stanza + "&stato=" + s,
			type : 'POST',
			cache : false,
			error : function() {
				alert('error');
				event.preventDefault();
			},
			async : false

		}).done(function(risposta) {
			if (risposta != "ok") {
				console.log(statoPrecedente)
				a.prop('checked', statoPrecedente);
			} else {
				// caso in cui va bene la modifica dello stato
			}
		});
	});

	$(".apri-finestra").on('click', function() {
		var stanza = $(this).prop('id').substring(12);

		$.ajax({
			url : 'ApriFinestre',
			data : "stanza=" + stanza + "&stato=up",
			type : 'POST',
			cache : false,
			error : function() {
				alert('error');
				event.preventDefault();
			},
			async : false

		}).done(function(risposta) {
			if (risposta != "errore") {
				// caso in cui va bene la modifica dello stato
				var value = $("#finestra_" + stanza).attr('aria-valuenow');
				var newValue = parseInt(risposta);
				$("#finestra_" + stanza).attr('aria-valuenow', newValue);
				var css = 100 / 180 * newValue;
				var a = css + '%';
				$("#finestra_" + stanza).css('width', a);
				console.log($("#finestra_" + stanza).attr('aria-valuenow'));
			} else {
				
			}
		});
	});

	$(".chiudi-finestra").on('click', function() {
		var stanza = $(this).prop('id').substring(14);
		$.ajax({
			url : 'ApriFinestre',
			data : "stanza=" + stanza + "&stato=down",
			type : 'POST',
			cache : false,
			error : function() {
				alert('error');
				event.preventDefault();
			},
			async : false

		}).done(function(risposta) {
			if (risposta != "errore") {
				// caso in cui va bene la modifica dello stato
				var value = $("#finestra_" + stanza).attr('aria-valuenow');
				var newValue = parseInt(risposta);
				$("#finestra_" + stanza).attr('aria-valuenow', newValue);
				var css = 100 / 180 * newValue;
				var a = css + '%';
				$("#finestra_" + stanza).css('width', a);
				console.log($("#finestra_" + stanza).attr('aria-valuenow'));
			} else {
				
			}
		});
	});

});

function leggiTemperatura() {
	$.ajax({
		url : 'LeggiTemperatura',
		data : "stanza=casa",
		type : 'POST',
		cache : false,
		error : function() {
			alert('error');
			event.preventDefault();
		},
		async : false

	}).done(function(risposta) {
		if (risposta != "errore") {
			var fields = risposta.split("/");
			var celsius = fields[0];
			var fahrenheit = fields[1];
			$("#celsius").text(celsius);
			$("#fahrenheit").text(fahrenheit);
		}
	});
}

function leggiUmidita() {
	$.ajax({
		url : 'LeggiUmidita',
		data : "stanza=casa",
		type : 'POST',
		cache : false,
		error : function() {
			alert('error');
			event.preventDefault();
		},
		async : false

	}).done(function(risposta) {
		if (risposta != "errore") {
			$("#umidita").text(risposta);
		}
	});
}
