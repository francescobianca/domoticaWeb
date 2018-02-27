function resetPassword(){
	$.ajax({
		url : 'ResetPassword',
		data : "email=" + $("#InputEmail").val(),
		type : 'POST',
		cache : false,
		error : function() {
			alert('error');
		},
		async : false

	}).done(function(risposta) {
		if(risposta!="utenteNonRegistrato"){
			$("#nuovaPassword").text("La tua nuova password è: "+risposta);
			$("#nuovaPassword").css('display','block');
		}else{
			$("#nuovaPassword").text("L'email inserita è inesistente");
			$("#nuovaPassword").css('display','block');
		}
	});
}