jQuery(document).ready(
		function() {

			$.ajax({
				url : 'currentChart',
				data : "",
				dataType : 'json',
				type : 'GET',
				cache : false,
				error : function() {
					alert('error');
				},
				async : false

			}).done(
					function(risposta) {
							
						var temperatura = risposta[0];
						var umidita = risposta[1];

						var oreMisurazioniTemp = [];
						var valoreMisurazioniTemp = [];
						
						var oreMisurazioniUmid = [];
						var valoreMisurazioniUmid = [];

						var giorno = temperatura[0].giorno.split(" ", 3);

						for (i in temperatura) {
							var str = temperatura[i].ora;
							var res = str.split(" ");

							oreMisurazioniTemp.push(res[3]);
							valoreMisurazioniTemp.push(temperatura[i].valore);
						}
						
						for (i in umidita) {
							var str = umidita[i].ora;
							var res = str.split(" ");

							oreMisurazioniUmid.push(res[3]);
							valoreMisurazioniUmid.push(umidita[i].valore);
						}
						
						

						var ctx = document.getElementById("canvas");

						var myChart = new Chart(ctx, {
							type : 'line',
							data : {
								labels : oreMisurazioniTemp,
								datasets : [ {
									label : giorno,
									data : valoreMisurazioniTemp,
									backgroundColor : [
										'rgba(255,69,0,0.3)',],
									borderColor : [ 'rgba(178,34,34,1)',],
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
								legend: {
						            labels: {
						                // This more specific font property overrides the global property
						                fontColor: 'black',
						                defaultFontFamily: "'Helvetica Neue', 'Helvetica', 'Arial', sans-serif"
						            }
						        }
							}
						});
						
						var ctx1 = document.getElementById("canvas1");

						var myChart1 = new Chart(ctx1, {
							type : 'line',
							data : {
								labels : oreMisurazioniUmid,
								datasets : [ {
									label : giorno,
									data : valoreMisurazioniUmid,
									backgroundColor : [
											'rgba(0,191,255,0.2)',],
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
								legend: {
						            labels: {
						                // This more specific font property overrides the global property
						                fontColor: 'black',
						                defaultFontFamily: "'Helvetica Neue', 'Helvetica', 'Arial', sans-serif"
						            }
						        }
							}
						});

					});

		});