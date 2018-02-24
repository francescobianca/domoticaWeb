jQuery(document)
		.ready(
				function() {

					$
							.ajax({
								url : 'currentChart',
								data : "",
								dataType : 'json',
								type : 'GET',
								cache : false,
								error : function() {
									alert('error');
								},
								async : false

							})
							.done(
									function(risposta) {

										var temperatura = risposta[0];
										var umidita = risposta[1];

										var oreMisurazioniTemp = [];
										var valoreMisurazioniTemp = [];

										var oreMisurazioniUmid = [];
										var valoreMisurazioniUmid = [];

										var giorno = temperatura[0].giorno
												.split(" ", 3);

										for (i in temperatura) {
											var str = temperatura[i].ora;
											var res = str.split(" ");

											oreMisurazioniTemp.push(res[3]);
											valoreMisurazioniTemp
													.push(temperatura[i].valore);
										}

										for (i in umidita) {
											var str = umidita[i].ora;
											var res = str.split(" ");

											oreMisurazioniUmid.push(res[3]);
											valoreMisurazioniUmid
													.push(umidita[i].valore);
										}

										var ctx = document
												.getElementById("canvasTemperatura");

										var myChart = new Chart(
												ctx,
												{
													type : 'line',
													data : {
														labels : oreMisurazioniTemp,
														datasets : [ {
															label : giorno,
															data : valoreMisurazioniTemp,
															backgroundColor : [
																	'rgba(255,69,0,0.3)', ],
															borderColor : [
																	'rgba(178,34,34,1)', ],
															borderWidth : 1
														} ]
													},
													options : {
														scales : {
															yAxes : [ {
																ticks : {
																	beginAtZero : true
																}
															} ]
														},
														legend : {
															labels : {
																// This more
																// specific font
																// property
																// overrides the
																// global
																// property
																fontColor : 'black',
																defaultFontFamily : "'Helvetica Neue', 'Helvetica', 'Arial', sans-serif"
															}
														}
													}
												});

										var ctx1 = document
												.getElementById("canvasUmidita");

										var myChart1 = new Chart(
												ctx1,
												{
													type : 'line',
													data : {
														labels : oreMisurazioniUmid,
														datasets : [ {
															label : giorno,
															data : valoreMisurazioniUmid,
															backgroundColor : [
																	'rgba(0,191,255,0.2)', ],
															borderColor : [
																	'rgba(0,0,205,1)', ],
															borderWidth : 1
														} ]
													},
													options : {
														scales : {
															yAxes : [ {
																ticks : {
																	beginAtZero : true
																}
															} ]
														},
														legend : {
															labels : {
																// This more
																// specific font
																// property
																// overrides the
																// global
																// property
																fontColor : 'black',
																defaultFontFamily : "'Helvetica Neue', 'Helvetica', 'Arial', sans-serif"
															}
														}
													}
												});

									});

				});

function kindChart(categoria) {
	
/*	var element = document.getElementById("selectChart"+categoria);
	var selectedItem = element.options[element.selectedIndex].value;
	
	console.log(typeTemp);
	console.log(typeUmid);
	
	
	if (selectedItem==1) {
		
		if (categoria=="Temperatura") 
			typeTemp='line';
		else if (categoria=='Umidita')
			typeUmid='line';
		
	}
	else if (selectedItem==2) {
		
		if (categoria=="Temperatura") {
			typeTemp='bar';
			location.reload();
		}
		else if (categoria=='Umidita')
			typeUmid='bar';
	}
	*/
	
}

function cambiaPeriodo(categoria) {
	if ($("#giornoInizioBox" + categoria).val() != ""
			&& $("#giornoFineBox" + categoria).val() != ""
			&& $("#giornoFineBoxErrore" + categoria).css('display') == "none") {
		$("#completaFormBox" + categoria).css('display', 'none');

		var giornoInizio = $("#giornoInizioBox" + categoria);
		var giornoFine = $("#giornoFineBox" + categoria);
		var tipo = categoria;

		console.log(giornoInizio.val())
		console.log(giornoFine.val())

		$.ajax(
				{
					url : 'ottieniGrafici',
					data : "giornoInizio=" + giornoInizio.val()
							+ "&giornoFine=" + giornoFine.val() + "&tipo="
							+ tipo,
					dataType : 'json',
					type : 'GET',
					cache : false,
					error : function() {
						alert('error');
					},
					async : false

				}).done(function(risposta) {
			console.log(risposta)

			var orariMisurazioni = [];
			var giorniMisurazioni = [];
			var valoriMisurazioni = [];

			for (i in risposta) {
			
				var res = risposta[i].ora.split(" ");
				
				var res1 = risposta[i].giorno.split(" ",3);
				
				orariMisurazioni.push(res[3]+" "+res1); //compongo la stringa della data
				valoriMisurazioni.push(risposta[i].valore);
			}

			var ctxNew = document.getElementById("canvasNuovoPeriodo"+categoria);

			var backgroundColor = null;
			var borderColor = null;

			if (categoria=="Temperatura") {
				backgroundColor=['rgba(255,69,0,0.3)', ];
				borderColor = ['rgba(178,34,34,1)', ];
				console.log('ok')
			}
			else if (categoria=="Umidita") {
				backgroundColor = ['rgba(0,191,255,0.2)', ];
				borderColor = ['rgba(0,0,205,1)', ];
			}
			
			var myChartNew = new Chart(
					ctxNew,
					{
						type : 'line',
						data : {
							labels : orariMisurazioni,
							datasets : [ {
								label : "dal "+giornoInizio.val()+" al "+giornoFine.val(),
								data : valoriMisurazioni,
								backgroundColor : backgroundColor,
								borderColor : borderColor,
								borderWidth : 1
							} ]
						},
						options : {
							scales : {
								yAxes : [ {
									ticks : {
										beginAtZero : true
									}
								} ]
							},
							legend : {
								labels : {
									fontColor : 'black',
									defaultFontFamily : "'Helvetica Neue', 'Helvetica', 'Arial', sans-serif"
								}
							}
						}
					});
			
			
			$("#canvas" + categoria).css('display', 'none');
			$("#canvasNuovoPeriodo" + categoria).css('display', 'block');
			$(".closeButton").click();
		});

	} else {
		$("#completaFormBox" + categoria).css('display', 'block');
	}
}

function pulisciForm(categoria) {
	$("#giornoInizioBox" + categoria).val("");
	$("#giornoFineBox" + categoria).val("");
	$("#giornoFineBoxErrore" + categoria).css('display', 'none');
	$("#completaFormBox" + categoria).css('display', 'none');
}

jQuery(document).ready(function() {

	// funzione che controlla che il giorno di fine è successivo a quello di
	// inizio
	$(".giornoFineBox").on('blur', function() {
		var id = $(this).prop("id");
		var categoria = id.substring(13);
		var endDay = new Date($(this).val());
		var day = new Date($("#giornoInizioBox" + categoria).val());
		if (endDay.getTime() < day.getTime()) {
			$("#giornoFineBoxErrore" + categoria).css('display', 'block');
		} else {
			$("#giornoFineBoxErrore" + categoria).css('display', 'none');
		}
	});

	// funzione che pulisce le schermate
	$(".closeButton").on('click', function() {
		console.log('pulisci')
		var id = $(this).prop('id');
		var categoria = id.substring(11);
		pulisciForm(categoria);
	});

})


