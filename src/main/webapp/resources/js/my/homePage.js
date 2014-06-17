
$('#initializeDb').click(function(event) {
	$.post( "/initializeDb" );      
});
$('#cleanDb').click(function(event) {
	$.post( "/cleanDb" );      
});
