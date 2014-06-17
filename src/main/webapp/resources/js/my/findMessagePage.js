
$('.error').hide();

var date2  = new Date();
var date1  = new Date();
date1.setDate(date2.getDate()-5);
$('#date1').val(formatDate(date1));
$('#date2').val(formatDate(date2));
applyFilters(1);

function formatDate(now) {
    var year    = now.getFullYear();
    var month   = now.getMonth()+1; 
    var day     = now.getDate();
    var hour    = now.getHours();
    var minute  = now.getMinutes();
    var second  = now.getSeconds(); 
    if(month.toString().length == 1) {
        var month = '0'+month;
    }
    if(day.toString().length == 1) {
        var day = '0'+day;
    }   
    if(hour.toString().length == 1) {
        var hour = '0'+hour;
    }
    if(minute.toString().length == 1) {
        var minute = '0'+minute;
    }
    if(second.toString().length == 1) {
        var second = '0'+second;
    }   
    var dateTime = year+'/'+month+'/'+day+' '+hour+':'+minute+':'+second;   
    return dateTime;

}

function applyFilters(pageNumber){
	$(".tableRow").each(function(){
		$( this ).remove();
	});
	$.ajax({
		url : "/filterMessages",
		data: { userName: $("#userName").val(), dateFrom: $("#date1").val(), dateTo: $("#date2").val(), "pageNumber": pageNumber}
	}).done(function(data) {
		$.each(data,function(i,item){
			var creationDate = new Date(Number(item.creationDate));
			$("#tableBody").append(
					"<tr class=\"tableRow\">" +
					"<td class=\"col-sm-1\">" + formatDate(creationDate) + "</td>" +
					"<td class=\"col-sm-2\">" + item.userName + "</td>" +
					"<td class=\"col-sm-9\">" + item.message + "</td>" +
					"</tr>"		
			);
		});
	});
	buildPagination(pageNumber);
}

function buildPagination(pageNumber){
	$.ajax({
		url : "/getMessageNumber",
		data: { userName: $("#userName").val(), dateFrom: $("#date1").val(), dateTo: $("#date2").val()}
	}).done(function(data) {
		$(".pageLink").each(function(){
			$( this ).remove();
		});
		for ( var i = 0; i < Math.floor((data.messageNumber-1)/10) + 1; i++) {
			$("#pagination").append(
					"<li class=\""+(pageNumber==(i+1)?"active ":"")+"pageLink\"><a href=\"#\">"+(i+1)+"</a></li>"
			);			
		}
	});
}

function FiltersChanged()
{
	var doFilters = true;
	if (/\S/.test($('#date1').val())) {
		doFilters = doFilters & aDateHasErrors($('#date1').val(), $('#errorDate1'));
	}
	if (/\S/.test($('#date2').val())) {
		doFilters = doFilters & aDateHasErrors($('#date2').val(), $('#errorDate2'));
	}	
	if (doFilters)
		applyFilters(1);
}

function aDateHasErrors(dtValue, errorTag)
{
	if (ValidateDate(dtValue)) {
		errorTag.hide();
		return true;
	} else {
		errorTag.show();
		return false;
	}
}

function ValidateDate(dtValue)
{
     var regexString = 
     '\\b' +
     '\\d{4}' + '\\/' + '\\d{2}' + '\\/' + '\\d{2}' +
     '\\s' +
     '\\d{2}' + '\\:' + '\\d{2}' + '\\:' + '\\d{2}' +
     '\\b';
     var dtRegex = new RegExp(regexString);
     if (!dtRegex.test(dtValue))
        return false;
     
    var y = Number(dtValue.substring(0, 4));
    var m =  Number(dtValue.substring(5, 7)) - 1;
    var d = Number(dtValue.substring(8, 10));
    var hh = Number(dtValue.substring(11, 13));
    var mm = Number(dtValue.substring(14, 16));
    var ss = Number(dtValue.substring(17, 19));
    var date = new Date(y,m,d,hh,mm,ss);
    
    var result = date.getFullYear() == y && date.getMonth() == m && date.getDate() == d &&
        date.getHours() == hh && date.getMinutes() == mm && date.getSeconds() == ss
    return result;
}


var typingTimer;             
var doneTypingInterval = 1000;  
$('.filterField').keyup(function(){
    clearTimeout(typingTimer);
    typingTimer = setTimeout(FiltersChanged, doneTypingInterval);
});

$('.filterField').keydown(function(){
    clearTimeout(typingTimer);
});

$(document).on('click', ".pageLink", function() {
	event.preventDefault();
	applyFilters($(this).text());      
});
