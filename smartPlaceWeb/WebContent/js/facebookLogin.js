window.fbAsyncInit = function() {
	FB.init({
		appId : '170325916938363',
		cookie : true,
		xfbml : true,
		version : 'v2.8'
	});

	FB.getLoginStatus(function(response) {
		statusChangeCallback(response);
	});

};

(function(d, s, id) {
	var js, fjs = d.getElementsByTagName(s)[0];
	if (d.getElementById(id)) {
		return;
	}
	js = d.createElement(s);
	js.id = id;
	js.src = "https://connect.facebook.net/en_US/sdk.js";
	fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));

function statusChangeCallback(response) {
	if (response.status === 'connected') {
		console.log('Logged and authenticated');
		testAPI();
	} else {
		console.log('Not authenticated');
	}
}

function checkLoginState() {
	FB.getLoginStatus(function(response) {
		statusChangeCallback(response);
	});
}

function testAPI() {
	FB.api('/me?fields=email,birthday,first_name,last_name',function(response){
		if (response && !response.error) {
			buildProfile(response);
		}
	})
}

function buildProfile(user) {
	var email = user.email;
	var name = user.first_name;
	var surname = user.last_name;
	var birthday = user.birthday; //data formato mm/gg/aaaa
	
	$.ajax({
		url : 'checkFacebookLogin',
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
