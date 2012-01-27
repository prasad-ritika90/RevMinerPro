function changeBgPicture(button) {
	var image = button.style.backgroundImage;
	image = image.split("/");
	image = image[image.length - 1];
	if (image != 'decisionBgChecked.png)') {
		button.style.backgroundImage = "url('decisionBgChecked.png')";
	} else {
		button.style.backgroundImage = "url('decisionBgUnChecked.png')";
	}
}

function redirect() {
	window.location = "./cuisine.html"
}