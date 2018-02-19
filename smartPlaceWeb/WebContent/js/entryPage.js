function apriDashBoard() {
	$.ajax({
		url : 'leggiStatoDispositivi',
		data : '',
		type : 'POST',
		cache : false,
		error : function() {
			alert('error');
		},
		async : false

	}).done(function(risposta) {
	});
}

function logout() {
	$.ajax({
		url : 'checkLogin',
		data : '',
		type : 'GET',
		cache : false,
		error : function() {
			alert('error');
		},
		async : false

	}).done(function(risposta) {
	});
}