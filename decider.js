function changeBgPicture(button) {
	var image = button.style.backgroundImage;
	image = image.split("/");
	image = image[image.length - 1];
	var checked = "";
	if (image != 'decisionBgChecked.png)') {
		button.style.backgroundImage = "url('decisionBgChecked.png')";
		checked = "Yes"
	} else {
		button.style.backgroundImage = "url('decisionBgUnChecked.png')";
	}
	if (button.innerHTML == "Has Wifi") {
		setValue("Wifi", checked);
	} else if (button.innerHTML == "Open Now") {
		setValue("Open", checked);
	} else if (button.innerHTML == "Wheelchair Accessible") {
		setValue("Wheel_Chair_Accessible", checked);
	}
}

function showQuestion(button) {
	var buttonIndex = button.value;
	var questions = document.all['question'];
	for (var i = 0; i < questions.length; i++) {
		if (i == buttonIndex) {
			questions[i].className = "show";
		} else {
			questions[i].className = "hide";
		}	
	}	
}

var attributes = new Array("Cuisine", "Meal", "City", "Zip Code", "Alcohol", "Wheel_Chair_Accessible", "Parking", "Price", "Wifi", "Open");
var values = new Array("","Lunch:Dinner","","","","","","$:$$:$$$:$$$$","","");

function setValue(type, checked) {
	var index = attributes.indexOf(type);
	values[index] = checked;
}

function setAlcohol(e) {
	var index = attributes.indexOf("Alcohol");
	values[index] = e.value;
}

function setMeal(e) {
	var index = attributes.indexOf("Meal");
	var data = values[index];
	var dataA = data.split(':');
	if (!e.checked) {
		dataA[e.name] = "";
	} else {
		var str = (e.name == 0) ? "Lunch" : "Dinner";
		dataA[e.name] = str;
	}
	values[index] = dataA.join(":");
}

function setPrice(e) {
	var index = attributes.indexOf("Price");
	var str = values[index];
	var dollars = str.split(':');
	if (!e.checked) {
		dollars[e.name.length-1] = "";
	} else {
		dollars[e.name.length-1] = e.name;
	}
	values[index] = dollars.join(':');
}

function submit() {
	var textboxes = document.all['searchBoxOne'];
	var index = attributes.indexOf("Cuisine");
	values[index] = textboxes[0].value;
	index = attributes.indexOf("Zip Code");
	values[index] = textboxes[1].value;
	index = attributes.indexOf("City");
	values[index] = textboxes[2].value;
	window.location = "results.html?key=&cuis=" + values[0] + "&m=" + values[1] + "&city=" + values[2] + "&zip=" + values[3] + "&alc=" + values[4] + "&wheel=" + values[5]
				+ "&park=" + values[6] + "&price=" + values[7] + "&wifi=" + values[8] + "&open=" + values[9];
}