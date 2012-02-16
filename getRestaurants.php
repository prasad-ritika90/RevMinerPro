<?php

// load database
mysql_connect("localhost", "root", "1990") or die ("Connection failed");
$db = "revminer";
mysql_select_db($db) or die ("Could not select the database '" .$db. "'. Are you sure it exists?"); 

// get the search key
$q=$_GET["q"];

// if the search key is requestd, then get all the restaurants
if($q){
	getRestaurant($q);
}

// get all the restaurants from the search key
function getRestaurant($name){
	$query = "SELECT * FROM restaurants where Name LIKE '%".str_replace(" ", "-", $name)."%'";
	$result = mysql_query($query);
	while($restaurant = mysql_fetch_array($result, MYSQL_ASSOC)){
		$name = $restaurant['Name'];
		echo('['.$restaurant["Business_Name"].':'.$restaurant["Name"].'],');
	}
	mysql_close();
}
 ?> 

