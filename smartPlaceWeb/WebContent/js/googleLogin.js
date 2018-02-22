function onSignIn(googleUser) {
  var profile = googleUser.getBasicProfile();
  
  var email = profile.getEmail();
  var name = profile.getGivenName();
  var surname = profile.getFamilyName();
  
  console.log(email);
  console.log(name);
  console.log(surname);
  
  $.ajax({
		url : 'checkGoogleLogin',
		data : "email=" + email+ "&nome="+ name +"&cognome="+surname,
		type : 'POST',
		cache : false,
		error : function() {
			alert('error');
		},
		async : false

	}).done(function(risposta) {
		
	});
  
}

/* SIGN OUT
function signOut() {
  var auth2 = gapi.auth2.getAuthInstance();
  auth2.signOut().then(function () {
    console.log('User signed out.');
  });
}
*/