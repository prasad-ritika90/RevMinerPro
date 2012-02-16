window.onload = function() {
	fetchImages();
};

var step = 0;
function fetchImages() {
	var imageHolder = document.all['tvShow'];
	var messageHolder = document.all['messageShow'];
	var images = [];
	slideImages(images, imageHolder, messageHolder);
	setInterval(function() {
		var imageHolder = document.all['tvShow'];
		var messageHolder = document.all['messageShow'];
		var images = [];
		slideImages(images, imageHolder, messageHolder);
	},4000);
};

function slideImages(images, imageHolder, messageHolder) {
	var count = 0;
	for (var i = 0; i < imageHolder.length; i++) {
		images[i] = imageHolder[i].src;
		imageHolder[i].style.height = '100%';
		imageHolder[i].style.width = '100%';
		if (count != step) {
			imageHolder[i].style.display = 'none';
			messageHolder[i].style.display = 'none';
		} else {
			imageHolder[i].style.display = '';
			messageHolder[i].style.display = '';
		}
		count++;
	}
	if (step < count - 1) {
		step++;
	} else {
		step = 0;
	}
};

function search(){
	document.getElementById("display").innerHTML="";
	var key = document.getElementById("search").value;
	window.location = "results.html?key=" + key;
}

// display information of the given restaurant
function display(restaurants){
	var toAppend = document.getElementById("display");
	var tvFrame = document.getElementById("tvFrame");
	tvFrame.style.display = 'none';

	//toAppend.innerHTML="<div style='position:absolute;left:600px;top:200px;'><img src='loading.gif' /></div>";
	var restaurantList = restaurants.responseText.split(',');
	var appendTo = document.getElementById("content");
	for (var i = 0; i < restaurantList.length; i++) {
		var link = document.createElement('a');
		link.setAttribute('href', 'restaurant.html');
		link.setAttribute('class', 'restaurantName');
		link.setAttribute('onClick', 'restaurantChosen()');
		link.innerHTML = restaurantList[i];
		toAppend.appendChild(link);
		var line = document.createElement('hr');
		toAppend.appendChild(line);
	}

}

function favoriteClicked(e) {
	window.location = e.href;
}