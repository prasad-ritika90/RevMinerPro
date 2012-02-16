// use ajax to load the content with given display ID
function loadAjax(displayID, PHPpage, key){
	if (window.XMLHttpRequest){// code for IE7+, Firefox, Chrome, Opera, Safari
	   xmlhttp=new XMLHttpRequest();
	}else{// code for IE6, IE5
	   xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange=function(){
	   if (xmlhttp.readyState==4 && xmlhttp.status==200){
		 //document.getElementById(displayID).innerHTML=xmlhttp.responseText;
		display(xmlhttp);
	   }
	}
	xmlhttp.open("GET",PHPpage+"?q="+key,true);
	xmlhttp.send();
}