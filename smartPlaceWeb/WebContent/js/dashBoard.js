jQuery(document).ready(function() {
	
	$(".checkbox-luci").on('click', function(event) {
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

});