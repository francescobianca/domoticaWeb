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