window.onload = function() {
	if (window.File && window.FileReader && window.FileList && window.Blob) {
	} else {
		alert("Fail!");
	}
	
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
