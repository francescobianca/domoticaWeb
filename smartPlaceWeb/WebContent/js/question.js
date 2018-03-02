jQuery(document).ready(function() {
	
	function pulisciFormDomanda(){
		console.log("sto pulendo la form")
		$("textarea#text").val("");
		$("input#title").val("");
		$("#slct").val("-1");
	}
	
	function pulisciFormRisposta(){
		$("#textRisposta").val("");
	}
	
	
	$("#inviaRisposta").on('click',function(){
		if($("#textRisposta").val()!=""){
			$('#noRisp').css('display','none');

			$.ajax({
				url : 'NuovaRisposta',
				data : "text="+$("#textRisposta").val()+"&id="+domandaSelezionata,
				type : 'POST',
				cache : false,
				error : function() {
					alert('error');
				},
				async : false

			}).done(function(risposta) {
				
				if (risposta!="errore") {
					pulisciFormRisposta();
					$('#okRisp').css('display','block');
					setTimeout(function(){
						$('#okRisp').css('display','none');
					},4000)
				}
			})
			
		}else{
			$('#noRisp').css('display','block');
		}
	});

	$('#inviaDomanda').on('click',function(){
		
		if ($('input#title').val()!="" && $("#slct").val()!=-1) {
			$('#noTitle').css('display','none');
			var testo=$("textarea#text").val();
			console.log(testo)
			$.ajax({
				url : 'NuovaDomanda',
				data : "text="+testo+"&title="+$('input#title').val()+"&categoria="+$("#slct").val(),
				type : 'POST',
				cache : false,
				error : function() {
					alert('error');
				},
				async : false

			}).done(function(risposta) {
				
				if (risposta!="errore") {
					pulisciFormDomanda();
					$('#okDomanda').css('display','block');
					setTimeout(function(){
						$('#okDomanda').css('display','none');
					},4000)
				}
				
				
			})
			

		} else {
			$('#noTitle').css('display','block');
		}
		
		
		
		
	});
	
	$('#ask').on('click',function(){
		
		$('#slct').find('option').remove().end().append(
		'<option value="-1">Scegli la categoria!</option>');

		var element = $("#slct");
	
		$.ajax({
			url : 'AllCategories',
			data : "",
			type : 'GET',
			dataType: 'json',
			cache : false,
			error : function() {
				alert('error');
			},
			async : false

		}).done(function(risposta) {
			
			var categorie = [];
			for (i in risposta)
				categorie.push(risposta[i].nome);
			
			$.each(categorie, function(index, value) {

				element.append($('<option/>', {
					text : categorie[index]
				}));
			});
			
		})
		
	});
		
});

function cercaDomande(){
	
	$("#ultimeDomande").css('display','none');
	$("#risultatoRicerca").css('display','none');
	$("#noRisultati").css('display','none');
	
	var text=$("#searchBox").val();
	if(text!=""){
		$.ajax({
			url : 'ApriQuestion',
			data : "text="+text,
			type : 'POST',
			dataType: 'json',
			cache : false,
			error : function() {
				alert('error');
			},
			async : false

		}).done(function(risposta) {
				
			
				$("#lastQuestion").remove();
				
				$("div#listQuestion").append( "<div class=\"row\" id=\"lastQuestion\"></div>");
				
				var ciSonoDomande=false;
				
				for (i in risposta) {
					
					ciSonoDomande = true;
					
					$("div#lastQuestion").append( 
							"<div class=\"col-md-6 col-lg-6\" style=\"width: 50%\">"
							+"	<label id=\"nomeActivity\" for=\"nomeActivity\">"+risposta[i].categoria.nome+"</label>"
							+"	</div>"

							+"	<div class=\"col-md-6 col-lg-6\">"
							+"		<div class=\"form-group form-control\">"
							+"			<a href=\"#response\" id=\"question"+risposta[i].id+"\" class=\"aStyle portfolio-item\""
							+"		style=\"color: #07C; margin-bottom:0px;\"  "
							+"		onclick=\"visualizzaDomanda(\'"+risposta[i].titolo+"\',\'"+risposta[i].testo+"\',\'"+risposta[i].id+"\',\'"+risposta[i].utente.email+"\')\">"+risposta[i].titolo+"</a>"
							+"		</div>"
							+"	</div>");			
				}

				if (ciSonoDomande)
					$("#risultatoRicerca").css('display','block');
				else 
					$("#noRisultati").css('display','block');

				
		})
	}
}

var domandaSelezionata;

function visualizzaDomanda(titolo,testo,id,autore){
	
	$("#provaBottone").click();
	domandaSelezionata=id;
	console.log(titolo)
	console.log(testo)
	console.log(id)
	console.log(autore)
	$("#titoloDellaDomanda").text(titolo);
	$("#testoDellaDomanda").text(testo);
	$("#autoreDomanda").text(autore);
	$("#listResponse").remove();
	$("div#containerResponse").append( "<div class=\"row\" id=\"listResponse\"></div>");
	$.ajax({
		url : 'RestituisciRisposte',
		data : "id="+id,
		type : 'POST',
		dataType: 'json',
		cache : false,
		error : function() {
			alert('error');
		},
		async : false

	}).done(function(risposta) {
		for(i in risposta){
			$("div#listResponse").append("<div class=\"container row-justify-center\">"
								+"<h5>"+risposta[i].utente.email+"</h5>"
								+"<div class=\"form-group form-control\">"
								+"	<p class=\"aStyle\">"
								+risposta[i].testo+"</a>"
								+"</div>"+
								"</div>");
		}
		
	});
}

