function showRating(rating) {
	var stars = document.all['star'];
	var count = 0;
	while (count < rating) {
		stars[count].src = "starred.png";
		count++;
	}
}