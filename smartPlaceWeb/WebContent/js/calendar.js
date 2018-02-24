$(document).ready(function() {

    // page is now ready, initialize the calendar...
	/*
	 * var event={id:1 , title: 'New event', start: new Date() };
	 * 
	 * $('#calendar').fullCalendar({ // put your options and callbacks here
	 * header: { center: 'month,basicWeek,basicDay' }, // buttons for switching
	 * between views
	 *  })
	 * 
	 * $('#calendar').fullCalendar( 'renderEvent', event, true);
	 */
    
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
    		var fieldsFine=risposta[i].giornoFine.split(" ");
    		var annoFine=fieldsFine[5];
    		var giornoFine=fieldsFine[2];
    		var meseFine=getMese(fieldsFine[1]);
    		var dFine=new Date();
    		dInizio.setFullYear(annoInizio,meseInizio,giornoInizio);
    		dFine.setFullYear(annoFine,meseFine,giornoFine);
    		var event={
    				id: risposta[i].id,
    				title: risposta[i].nome,
    				start: dInizio,
    				end: dFine
    				}
    		events.push(event);
    	}
    	$('#calendar').fullCalendar({
    	        // put your options and callbacks here
    	       header: { center: 'month,basicWeek,basicDay' }, // buttons for
			   navLinks: true,
    	       editable: true,
    	       eventLimit: true,
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