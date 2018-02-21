jQuery(document).ready(function() {
	
	$("#cancello_left").on('click',function(event){
		var stanza=$(this).prop('id').substring(12);
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
			if (risposta != "ok") {
				
			} else {
				// caso in cui va bene la modifica dello stato
			}
		});
	});
	
	$("#cancello_right").on('click',function(event){
		var stanza=$(this).prop('id').substring(12);
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
			if (risposta != "ok") {
				
			} else {
				// caso in cui va bene la modifica dello stato
			}
		});
	});
	
	$("#ventilatore").on('change',function(event){
		var stato
		var statoPrecedente;
		if ($(this).prop('checked')) {
			stato = "1";
			statoPrecedente = "0";
		} else {
			stato = "0";
			statoPrecedente = "1"
		}
		console.log(statoPrecedente);
		var stanza = $(this).prop('id').substring(5);
		$.ajax({
			url : 'MonitoraTemperatura',
			data : "stanza=casa&stato=" + stato,
			type : 'POST',
			cache : false,
			error : function() {
				alert('error');
				event.preventDefault();
			},
			async : false

		}).done(function(risposta) {
			if (risposta != "ok") {
				$(this).prop('checked',statoPrecedente);
			} else {
				// caso in cui va bene la modifica dello stato
			}
		});
	});
	
	$(".checkbox-luci").on('change', function(event) {
		var stato;
		var statoPrecedente;
		if ($(this).prop('checked')) {
			stato = "1";
			statoPrecedente = "0";
		} else {
			stato = "0";
			statoPrecedente = "1"
		}
		console.log(statoPrecedente);
		var stanza = $(this).prop('id').substring(5);
		$.ajax({
			url : 'AccendiLuci',
			data : "stanza=" + stanza + "&stato=" + stato,
			type : 'POST',
			cache : false,
			error : function() {
				alert('error');
				event.preventDefault();
			},
			async : false

		}).done(function(risposta) {
			if (risposta != "ok") {
				$(this).prop('checked',statoPrecedente);
			} else {
				// caso in cui va bene la modifica dello stato
			}
		});
	});
	
	$(".apri-finestra").on('click',function(){
		var stanza=$(this).prop('id').substring(12);
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
			if (risposta != "ok") {
				
			} else {
				// caso in cui va bene la modifica dello stato
			}
		});
	});
	
	$(".chiudi-finestra").on('click',function(){
		var stanza=$(this).prop('id').substring(14);
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
			if (risposta != "ok") {
				
			} else {
				// caso in cui va bene la modifica dello stato
			}
		});
	});

});


function leggiTemperatura(){
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
			var fields=risposta.split("/");
			var celsius=fields[0];
			var fahrenheit=fields[1];
			$("#celsius").text(celsius);
			$("#fahrenheit").text(fahrenheit);
		} 
	});
}

function leggiUmidita(){
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
