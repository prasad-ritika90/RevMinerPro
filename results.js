window.onload = function() {
	//var key = window.location.href.split('?')[1].split('=')[1];
	var params = window.location.href.split('?')[1].split('&');
	key = params[0].split('=')[1].split('%20').join('-');
	var php = "http://ec2-23-21-229-182.compute-1.amazonaws.com/getRestaurants.php";
	if (key == "") {
		key = window.location.href.split('?')[1];
		php = "http://ec2-23-21-229-182.compute-1.amazonaws.com/getDeciderRestaurants.php";
	}
	loadAjax("results", php, key);
};

function display(restaurants){
	var toAppend = document.getElementById("display");
	//var display = document.getElementById("results");
	//display.style.display = 'none';

	//toAppend.innerHTML="<div style='position:absolute;left:600px;top:200px;'><img src='loading.gif' /></div>";
	
	var restaurantList = restaurants.responseText.split(',');
	var appendTo = document.getElementById("content");
	for (var i = 0; i < restaurantList.length; i++) {
		var restNames = restaurantList[i].split(':');
		restNames[0] = restNames[0].replace('[', '');
		restNames[1] = restNames[1].replace(']', '');
		var link = document.createElement('a');
		//link.setAttribute('href', 'restaurant.html');
		link.setAttribute('class', 'restaurantName');
		link.setAttribute('onClick', 'restaurantChosen(this)');
		link.setAttribute('name', restNames[1]);
		link.innerHTML = restNames[0];
		toAppend.appendChild(link);
		var line = document.createElement('hr');
		toAppend.appendChild(line);
	}

}

function restaurantChosen(event) {
	window.location = "restaurant.html?key=" + event.name;
}


