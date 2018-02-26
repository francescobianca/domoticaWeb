var tipoGraficoTemp = 'line';
var tipoGraficoUmid = 'line';

var caricaGraficoTemp = false;
var caricaGraficoUmid = false;

var temperatura = [];
var umidita = [];

var oreMisurazioniTemp = [];
var valoreMisurazioniTemp = [];

var oreMisurazioniUmid = [];
var valoreMisurazioniUmid = [];

var giorno = "";

var labelsTemp = [];
var dataSetsTemp = [];

var labelsUmid = [];
var dataSetsUmid = [];


jQuery(document).ready(function() {
	loadFirstGraph();
});

function loadFirstGraph() {

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

						var datiTemp = false;
						var datiUmid = false;

						temperatura = risposta[0];
						umidita = risposta[1];

						oreMisurazioniTemp = [];
						valoreMisurazioniTemp = [];

						oreMisurazioniUmid = [];
						valoreMisurazioniUmid = [];

						giorno = "";

						for (i in temperatura) {
							var str = temperatura[i].ora;
							var res = str.split(" ");

							oreMisurazioniTemp.push(res[3]);
							valoreMisurazioniTemp.push(temperatura[i].valore);

							datiTemp = true;
						}

						for (i in umidita) {
							var str = umidita[i].ora;
							var res = str.split(" ");

							oreMisurazioniUmid.push(res[3]);
							valoreMisurazioniUmid.push(umidita[i].valore);

							datiUmid = true;
						}

						labelsTemp = [];
						dataSetsTemp = [];

						labelsUmid = [];
						dataSetsUmid = [];

						if (datiTemp) {
							labelsTemp = oreMisurazioniTemp;
							dataSetsTemp = valoreMisurazioniTemp;
							giorno = temperatura[0].giorno.split(" ", 3);
						} else {
							labelsTemp = new Array();
							dataSetsTemp = new Array();
							giorno = 'Non ci sono dati per la giornata odierna';
						}

						if (datiUmid) {
							labelsUmid = oreMisurazioniUmid;
							dataSetsUmid = valoreMisurazioniUmid;
							giorno = umidita[0].giorno.split(" ", 3);
						} else {
							labelsUmid = new Array();
							dataSetsUmid = new Array();
							giorno = 'Non ci sono dati per la giornata odierna'
						}

						var ctx = document.getElementById("canvasTemperatura");

						var myChart = new Chart(
								ctx,
								{
									type : tipoGraficoTemp,
									data : {
										labels : labelsTemp,
										datasets : [ {
											label : giorno,
											data : dataSetsTemp,
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

						var ctx1 = document.getElementById("canvasUmidita");

						var myChart1 = new Chart(
								ctx1,
								{
									type : tipoGraficoUmid,
									data : {
										labels : labelsUmid,
										datasets : [ {
											label : giorno,
											data : dataSetsUmid,
											backgroundColor : [
													'rgba(0,191,255,0.2)', ],
											borderColor : [ 'rgba(0,0,205,1)', ],
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

}

function loadGraph(categoria) {
	
	var backgroundColor = null;
	var borderColor = null;

	var type = '';
	
	var labels = [];
	var dataSets = [];

	if (categoria == "Temperatura") {
		backgroundColor = [ 'rgba(255,69,0,0.3)', ];
		borderColor = [ 'rgba(178,34,34,1)', ];
		type = tipoGraficoTemp;
		labels = labelsTemp;
		dataSets = dataSetsTemp;
	} else if (categoria == "Umidita") {
		backgroundColor = [ 'rgba(0,191,255,0.2)', ];
		borderColor = [ 'rgba(0,0,205,1)', ];
		type = tipoGraficoUmid;
		labels = labelsUmid;
		dataSets = dataSetsUmid;
	}
	
	var ctx = document.getElementById("canvas"+categoria);

	var myChart = new Chart(
			ctx,
			{
				type : type,
				data : {
					labels : labels,
					datasets : [ {
						label : giorno,
						data : dataSets,
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
}

function kindChart(categoria) {


	var element = document.getElementById("selectChart" + categoria);
	var selectedItem = element.options[element.selectedIndex].value;


	if (selectedItem == 1) {
		if (tipoGraficoTemp == 'bar')
			if (categoria == "Temperatura") {
				tipoGraficoTemp = 'line';
				
				if (!caricaGraficoTemp)
					loadGraph('Temperatura');
				else  
					cambiaPeriodo("Temperatura");
				
			}
		if (tipoGraficoUmid == 'bar')
			if (categoria == 'Umidita') {
				tipoGraficoUmid = 'line';

				if (!caricaGraficoUmid)
					loadGraph('Umidita');
				else
					cambiaPeriodo('Umidita');

			}
	} else if (selectedItem == 2) {
		if (tipoGraficoTemp == 'line')
			if (categoria == "Temperatura") {
				tipoGraficoTemp = 'bar';

				if (!caricaGraficoTemp)
					loadGraph('Temperatura');
				else
					cambiaPeriodo('Temperatura');

			}

		if (tipoGraficoUmid == 'line')
			if (categoria == 'Umidita') {
				tipoGraficoUmid = 'bar';

				if (!caricaGraficoUmid)
					loadGraph('Umidita');
				else
					cambiaPeriodo('Umidita');

			}
	}

}

function cambiaPeriodo(categoria) {
		
	if (categoria == "Temperatura")
		caricaGraficoTemp = true;

	else if (categoria == "Umidita")
		caricaGraficoUmid = true;

	if ($("#giornoInizioBox" + categoria).val() != ""
			&& $("#giornoFineBox" + categoria).val() != ""
			&& $("#giornoFineBoxErrore" + categoria).css('display') == "none") {
		$("#completaFormBox" + categoria).css('display', 'none');

		var giornoInizio = $("#giornoInizioBox" + categoria);
		var giornoFine = $("#giornoFineBox" + categoria);
		var tipo = categoria;

		console.log(giornoInizio.val())
		console.log(giornoFine.val())

		$
				.ajax(
						{
							url : 'ottieniGrafici',
							data : "giornoInizio=" + giornoInizio.val()
									+ "&giornoFine=" + giornoFine.val()
									+ "&tipo=" + tipo,
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
							console.log(risposta)

							var orariMisurazioni = [];
							var giorniMisurazioni = [];
							var valoriMisurazioni = [];

							for (i in risposta) {

								var res = risposta[i].ora.split(" ");

								var res1 = risposta[i].giorno.split(" ", 3);

								orariMisurazioni.push(res[3] + " " + res1); // compongo
								// la
								// stringa
								// della
								// data
								valoriMisurazioni.push(risposta[i].valore);
							}

							var ctxNew = document
									.getElementById("canvasNuovoPeriodo"
											+ categoria);

							var backgroundColor = null;
							var borderColor = null;

							var type = '';

							if (categoria == "Temperatura") {
								backgroundColor = [ 'rgba(255,69,0,0.3)', ];
								borderColor = [ 'rgba(178,34,34,1)', ];
								type = tipoGraficoTemp;
							} else if (categoria == "Umidita") {
								backgroundColor = [ 'rgba(0,191,255,0.2)', ];
								borderColor = [ 'rgba(0,0,205,1)', ];
								type = tipoGraficoUmid;
							}

							var myChartNew = new Chart(
									ctxNew,
									{
										type : type,
										data : {
											labels : orariMisurazioni,
											datasets : [ {
												label : "dal "
														+ giornoInizio.val()
														+ " al "
														+ giornoFine.val(),
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
							$("#canvasNuovoPeriodo" + categoria).css('display',
									'block');
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
		//console.log('pulisci')
		var id = $(this).prop('id');
		var categoria = id.substring(11);
		//pulisciForm(categoria);
	});

})
