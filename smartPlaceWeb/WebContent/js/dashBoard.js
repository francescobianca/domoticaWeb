jQuery(document).ready(function() {
	$(".checkbox-luci").on('change', function() {
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
			},
			async : false

		}).done(function(risposta) {
			if (risposta != "ok") {
				// caso in cui va male la modifica dello stato
				// devo resettare lo stato precedente
				console.log(statoPrecedente);
				if (statoPrecedente == "0") {
					$(this).prop('checked', false);
					console.log("ri-non cecco");
				} else {
					$(this).prop('checked', true);
					console.log("ri cecco");
				}
			} else {
				// caso in cui va bene la modifica dello stato
			}
		});
	});

});

