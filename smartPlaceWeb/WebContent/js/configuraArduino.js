
function checkIndirizzoIP() {
	var pattern = /^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/;
	var valore = $("#indirizzoIP").val();
	if (pattern.test(valore)) {
		$.ajax(
				{
					url : 'ConfiguraArduino',
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
			url : 'ConfiguraArduino',
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
