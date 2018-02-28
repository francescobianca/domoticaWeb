function checkEmail(){
	var pattern=/^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	if (pattern.test($("#emailBox").val())) {
		$.ajax({
			url : 'checkLogin',
			data : "email=" + $("#emailBox").val() + "&checkEmail=check",
			type : 'POST',
			cache : false,
			error : function() {
				alert('error');
			},
			async : false

		}).done(function(risposta) {
			if (risposta != "ok") {
				$("#ErroreEmail").css('display', 'block');
				$("#emailBox").removeClass("validFormBox");
				$("#emailBox").addClass("errorFormBox");
				$("#loginButton").prop("disabled", true);
			} else {
				$("#emailBox").removeClass("errorFormBox");
				$("#emailBox").addClass("validFormBox");
				$("#ErroreEmail").css('display', 'none');
				$("#loginButton").prop("disabled", false);
			}
		});
	} else {
		$("#emailBox").removeClass("validFormBox");
		$("#emailBox").addClass("errorFormBox");
		$("#erroreEmail").css('display', 'none');
	}
}

jQuery(document).ready(function(){
	//$("#loginButton").prop("disabled",true);
});