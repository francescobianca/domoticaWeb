$(document).ready(function() {
    
    $.ajax({
    		url: 'LeggiAttivita',
    		data: "",
    		dataType : 'json',
			type : 'POST',
			cache : false,
			error : function() {
				alert('error');
			},
			async : false
    }).done(function(risposta){
    	var events=new Array();
    	for(i in risposta){
    		var fields=risposta[i].giornoInizio.split(" ");
    		var annoInizio=fields[5];
    		var giornoInizio=fields[2];
    		var meseInizio=getMese(fields[1]);
    		var dInizio=new Date();
    		var fieldsOraInizio=risposta[i].orarioInizio.split(" ");
    		var oraInizio=fieldsOraInizio[3].split(":");
    		dInizio.setHours(oraInizio[0]);
    		dInizio.setMinutes(oraInizio[1]);
    		dInizio.setSeconds(oraInizio[2]);
    		
    		var fieldsFine=risposta[i].giornoFine.split(" ");
    		var annoFine=fieldsFine[5];
    		var giornoFine=fieldsFine[2];
    		var meseFine=getMese(fieldsFine[1]);
    		var dFine=new Date();
    	/*	var fieldsOraFine=risposta[i].orarioFine.split(" ");
    		var OraFine=fieldsOraFine[3].split(":");
    		dFine.setHours(oraFine[0]);
    		dFine.setMinutes(oraFine[1]);
    		dFine.setSeconds(oraFine[2]);*/
    		dInizio.setFullYear(annoInizio,meseInizio,giornoInizio);
    		dFine.setFullYear(annoFine,meseFine,giornoFine);
    		var eventColor=getColor(risposta[i].sensore.tipo);
    		console.log(eventColor)
    		var event={
    				id: risposta[i].id,
    				title: risposta[i].nome,
    				start: dInizio,
    				end: dFine,
    				backgroundColor: eventColor
    				}
    		events.push(event);
    	}
    	$('#calendar').fullCalendar({
    	        // put your options and callbacks here
    	       header: { center: 'month,basicWeek,basicDay' }, // buttons for
			   navLinks: true,
    	       eventLimit: true,
    	       displayEventEnd: true,
			   events: events
    	});
    });    
});

function getMese(mese){
	var m;
	switch(mese) {
    case "Jan":
        m=0;
        break;
    case "Feb":
        m=1;
        break;
    case "Mar":
    	m=2;
    	break;
    case "Apr":
		m=3;
		break;
    case "May":
		m=4;
		break;
    case "Jun":
		m=5;
		break;
    case "Jul":
		m=6;
		break;
    case "Aug":
		m=7;
		break;
    case "Sep":
		m=8;
		break;
    case "Oct":
		m=9;
		break;
    case "Nov":
		m=10;
		break;
    case "Dec":
		m=11;
		break;	
    default:
    	m=0;
	}
	return m;
}

function getColor(tipo){
	var colore;
	switch(tipo){
	case "ventilatore":
		colore='rgb(255, 117, 26)';
		break;
	case "luce":
		colore='rgb(230, 230, 0)';
		break;
	case "finestra":
		colore='rgb(77, 184, 255)';
		break;
	case "cancello":
		colore="rgb(77, 255, 77)";
		break;
	default :
		colore="";
	}
	return colore;
}
