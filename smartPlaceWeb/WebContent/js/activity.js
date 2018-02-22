function newActionTemperatura() {

	var element = document.getElementById("slct");
	var selectedItem = element.options[element.selectedIndex].value;

	if (selectedItem == 1) {
		$("#nuovaRegola").css('display', 'none');
		$("#nuovaActivity").css('display', 'block');
	} else if (selectedItem == 2) {
		$("#nuovaRegola").css('display', 'none');
		$("#nuovaActivity").css('display', 'none');
	} else if (selectedItem == 3) {
		$("#nuovaActivity").css('display', 'none');
		$("#nuovaRegola").css('display', 'block');
	} else if (selectedItem == 4) {
		$("#nuovaActivity").css('display', 'none');
		$("#nuovaRegola").css('display', 'none');
	}

}

function newActionLuci() {

	var element = document.getElementById("slct");
	var selectedItem = element.options[element.selectedIndex].value;

	if (selectedItem == 1) {
		$("#nuovaActivity").css('display', 'block');
	} else if (selectedItem == 2) {
		$("#nuovaActivity").css('display', 'none');
	}

}

function newActionCancello() {

	var element = document.getElementById("slct");
	var selectedItem = element.options[element.selectedIndex].value;

	if (selectedItem == 1) {
		$("#nuovaActivity").css('display', 'block');
	} else if (selectedItem == 2) {
		$("#nuovaActivity").css('display', 'none');
	}

}

function newActionFinestre() {

	var element = document.getElementById("slct");
	var selectedItem = element.options[element.selectedIndex].value;

	if (selectedItem == 1) {
		$("#nuovaActivity").css('display', 'block');
	} else if (selectedItem == 2) {
		$("#nuovaActivity").css('display', 'none');
	}

}

function newActionUmidita() {

	var element = document.getElementById("slct");
	var selectedItem = element.options[element.selectedIndex].value;

	if (selectedItem == 1) {
		$("#nuovaRegola").css('display', 'none');
		$("#nuovaActivity").css('display', 'block');
	} else if (selectedItem == 2) {
		$("#nuovaRegola").css('display', 'none');
		$("#nuovaActivity").css('display', 'none');
	} else if (selectedItem == 3) {
		$("#nuovaActivity").css('display', 'none');
		$("#nuovaRegola").css('display', 'block');
	} else if (selectedItem == 4) {
		$("#nuovaActivity").css('display', 'none');
		$("#nuovaRegola").css('display', 'none');
	}

}

function newActionSicurezza() {

	var element = document.getElementById("slct");
	var selectedItem = element.options[element.selectedIndex].value;

	if (selectedItem == 1) {
		$("#nuovaActivity").css('display', 'block');
	} else if (selectedItem == 2) {
		$("#nuovaActivity").css('display', 'none');
	}

}

function registraAttivita(categoria) {
		var today=new Date();
		var day=new Date($("#giornoInizioBox"+categoria).val());
		var endDay=new Date($("#giornoFineBox"+categoria).val());
		day.setMinutes(today.getMinutes());
		day.setHours(today.getHours());
		day.setSeconds(today.getSeconds());
		day.setMilliseconds(today.getMilliseconds());
		endDay.setMinutes(today.getMinutes());
		endDay.setHours(today.getHours());
		endDay.setSeconds(today.getSeconds());
		endDay.setMilliseconds(today.getMilliseconds());
		if(day.getTime() < today.getTime()){
			$("#giornoInizioBoxErrore"+categoria).css('display','block');
		}
		if(endDay.getTime() < day.getTime()){
			$("#giornoFineBoxErrore"+categoria).css('display','block');
		}
		var nome = $("#nomeActivityBox"+categoria).val();
		var giornoInizio = $("#giornoInizioBox"+categoria).val();
		var giornoFine = $("#giornoFineBox"+categoria).val();
		var oraInizio = $("#oraInizioBox"+categoria).val();
		var oraFine = $("#oraFineBox"+categoria).val();
		var device=$("#selectDeviceActivityBox"+categoria+" option:selected").text().toLowerCase();
		console.log("nome: "+nome);
		console.log("giornoInizio: "+giornoInizio);
		console.log("giornoFine: "+giornoFine);
		console.log("oraInizio: "+oraInizio);
		console.log("oraFine: "+oraFine);
		console.log("device: "+device)
}


jQuery(document).ready(function(){
	
	$(".giornoInizioBox").on('blur',function(){
		var id=$(this).prop("id");
		var today=new Date();
		var day=new Date($("#giornoInizioBoxTemperatura").val());
		var endDay=new Date($("#giornoFineBoxTemperatura").val());
		day.setMinutes(today.getMinutes());
		day.setHours(today.getHours());
		day.setSeconds(today.getSeconds());
		day.setMilliseconds(today.getMilliseconds());
		var categoria=id.substring(15);
		console.log(categoria)
		if(day.getTime() < today.getTime()){
			$("#giornoInizioBoxErrore"+categoria).css('display','block');
		}else{
			$("#giornoInizioBoxErrore"+categoria).css('display','none');
		}
	});
	
	
})