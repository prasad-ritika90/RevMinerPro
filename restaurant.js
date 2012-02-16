window.onload = function() {
	var key = window.location.href.split('?')[1].split('=')[1];
	key = key.split('%20').join('-');

	loadAjax("results", "http://ec2-23-21-229-182.compute-1.amazonaws.com/getRestaurantData.php", key);
}

var setArray = new Array("Business_Name", "Address", "Hours", "Price_Range", "Neighborhood", "Attire", "Alcohol", "Good_for_Groups", "Waiter_Service", "Good_for_Kids", "Phone_number", "Category");
var attributeArray = new Array("Business_Name", "Address", "Hours", "Price_Range", "Neighborhood", "Attire", "Alcohol", "Good_for_Groups", "Waiter_Service", "Good_for_Kids", "Wi_Fi", "Good_for", "Takes_Reservations", "Accepts_Credits_Cards", "Parking", "Latitude","Longitude","Business_type","Category","Phone_number","Ambience","Delivery","Take_out","City","Noise_level","Outdoor_Seating","Wheelchair_Accessible","Has_TV","Dogs_Allowed","Caters","Drive_Thru","Coat_Check","Smoking","Best_Neights","Happy_Hour","Good_For_Dacing");
function display(data) {
	var attributes = data.responseText.split('`');
	var toAppend = document.getElementById('restaurantName');

	// add restaurant name
	var name = document.createElement('span');
	name.innerHTML = getAttribute(attributes, 'Business_Name');
	toAppend.insertBefore(name, toAppend.firstChild);

	var bodyTag = document.getElementById('page');

	// add the top data
	var content = createContentDiv(attributes, "Cuisine", "Category", "Address", "Address", "Phone Number", "Phone_number", "", "");
	bodyTag.appendChild(content);
	bodyTag.appendChild(document.createElement('hr'));
	content = createContentDiv(attributes, "Hours", "Hours", "Price Range", "Price_Range", "Neighborhood", "Neighborhood", "Attire", "Attire");
	bodyTag.appendChild(content);
	bodyTag.appendChild(document.createElement('hr'));
	content = createContentDiv(attributes, "Alcohol", "Alcohol", "Good For Groups", "Good_for_Groups", "Waiter Service", "Waiter_Service", "Good For Kids", "Good_for_Kids");
	bodyTag.appendChild(content);
	bodyTag.appendChild(document.createElement('hr'));
	for (var i = 0; i < attributeArray.length; i++) {
		if (setArray.indexOf(attributeArray[i]) == -1) {
			content1S = attributeArray[i];
			content2S = "";
			content3S = "";
			content4S = "";
			if (i + 1 < attributeArray.length - 1) {
				content2S = attributeArray[i+1];
			}
			if (i + 2 < attributeArray.length - 1) {
				content3S = attributeArray[i+2];
			}
			if (i + 3 < attributeArray.length - 1) {
				content4S = attributeArray[i+3];
			}			
			i = i + 4;
			content = createContentDiv(attributes, content1S.split("_").join(" "), content1S, content2S.split("_").join(" "), content2S, content3S.split("_").join(" "), content3S, content4S.split("_").join(" "), content4S);
			bodyTag.appendChild(content);
			bodyTag.appendChild(document.createElement('hr'));
		} 
	}

}

function createContentDiv(attributes, content1N, content1S, content2N, content2S, content3N, content3S, content4N, content4S) {
	var content = document.createElement('div');
	content.setAttribute('id', 'content');
	addAttributeToContent(content, attributes, content1N, content1S);
	addAttributeToContent(content, attributes, content2N, content2S);
	addAttributeToContent(content, attributes, content3N, content3S);
	addAttributeToContent(content, attributes, content4N, content4S);
	return content;
}

function addAttributeToContent(content, attributes, contentN, contentS) {
	if (contentN != "") {
		spaceDiv = createAttribute(attributes, contentN, contentS);
		if (spaceDiv != "") {
			content.appendChild(spaceDiv);
		}
	}
}

function createAttribute(attributes, typeName, typeSig) {
	var spaceDiv = document.createElement('div');
	spaceDiv.setAttribute('class', 'space');
	var attribute = document.createElement('span');
	attribute.setAttribute('class', 'attribute');
	attribute.innerHTML = typeName + ": ";
	var value = document.createElement('span');
	value.setAttribute('class', 'value');
	var text = getAttribute(attributes, typeSig) + " ";
	if (text == " ") {
		return "";
	}
	value.innerHTML = getAttribute(attributes, typeSig) + " ";
	spaceDiv.appendChild(attribute);
	spaceDiv.appendChild(value);
	if (typeName == "Address") {
		var mapLink = document.createElement('a');
		mapLink.setAttribute('class', 'mapLink');
		mapLink.setAttribute('href', 'map.html');
		mapLink.innerHTML = "View Map";
		spaceDiv.appendChild(mapLink);
	}
	return spaceDiv;
}

function getAttribute(attributes, type) {
	var index = attributeArray.indexOf(type);
	return attributes[index];
}

function showRating(rating) {
	var stars = document.all['star'];
	var count = 0;
	while (count < rating) {
		stars[count].src = "starred.png";
		count++;
	}
}