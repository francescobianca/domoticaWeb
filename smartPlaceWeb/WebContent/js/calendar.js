$(document).ready(function() {

    // page is now ready, initialize the calendar...
	
	var event={id:1 , title: 'New event', start:  new Date() };

    $('#calendar').fullCalendar({
        // put your options and callbacks here
        header: { center: 'month,basicWeek,basicDay' }, // buttons for switching between views

    })
    

    
    $('#calendar').fullCalendar( 'renderEvent', event, true);
  
});