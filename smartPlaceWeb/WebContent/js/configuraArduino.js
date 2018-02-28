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
					if(risposta!="indirizzoGiaEsistente"){
						$("#IndirizzoEsistente").css('display','none');
						$("#ErroreIndirizzo").css('display', 'none');
						$("#indirizzoIP").removeClass("errorFormBox");
						$("#indirizzoIP").addClass("validFormBox");
						checkAllSecondForm();
					}else{
						$("#ErroreIndirizzo").css('display','none')
						$("#IndirizzoEsistente").css('display','block');
						$("#indirizzoIP").removeClass("validFormBox");
						$("#indirizzoIP").addClass("errorFormBox");
					}
		});
	} else {
		$("#ErroreIndirizzo").css('display', 'block');
		$("#IndirizzoEsistente").css('display','none');
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
		$("#ErrorePorta").css('display', 'none');
		$("#porta").removeClass("errorFormBox");
		$("#porta").addClass("validFormBox");
		checkAllSecondForm()
	} else {
		$("#ErrorePorta").css('display', 'block');
		$("#porta").removeClass("validFormBox");
		$("#porta").addClass("errorFormBox");
	}
}

// Controlla che tutte le form della seconda fase della registrazione siano
// soddisfatte
function checkAllSecondForm() {
	var green_color = "rgba(111, 214, 111, 0.75) none repeat scroll 0% 0% / auto padding-box border-box";
	var indirizzoColor = $("#indirizzoIP").css('background');
	var portaColor = $("#porta").css('background');
	if (indirizzoColor == green_color && portaColor == green_color) {
		$("#registratiButton").prop("disabled", false);
	} else {
		$("#registratiButton").prop("disabled", true);
	}
}

function inviaDati() {
	if ($("#registratiButton").prop('disabled') == false) {
		$.ajax({
			url : 'ConfiguraArduino',
			data : "indirizzoIP=" + $("#indirizzoIP").val() + "&porta="+$("#porta").val(),
			type : 'POST',
			cache : false,
			error : function() {
				alert('error');
			},
			async : false

		}).done(function(risposta) {
			if(risposta=="ok"){
				window.location.replace("entryPage.jsp");
			}
		});
	
	}
}

jQuery(document).ready(function() {
	$("#registratiButton").prop("disabled", true);
})
