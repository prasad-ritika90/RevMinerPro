<?php

// load database
mysql_connect("localhost", "root", "1990") or die ("Connection failed");
$db = "revminer";
mysql_select_db($db) or die ("Could not select the database '" .$db. "'. Are you sure it exists?"); 

//echo("HELLO\n");
$attributes = array();
$attributes["Category"] =$_GET["cuis"];
//$attributes["Good_For"] = $_GET["m"];
$attributes["City"] = $_GET["city"];
$attributes["Alcohol"] = $_GET["alc"];
$attributes["Wheelchair_Accessible"] = $_GET["wheel"];
//$attributes["Price_Range"] = $_GET["price"];
$attributes["Parking"] = $_GET["park"];
$attributes["Wi_Fi"] = $_GET["wifi"];

$sql = "SELECT * FROM restaurants WHERE ";
$count = 0; 
foreach (array_keys($attributes)as $key) {
 //  echo(" Attribute = ".$key);
 //  echo(" Value".$attributes[$key]."\n");
   if (strlen($attributes[$key]) > 0 && $count == 0) {
       $sql .= $key."="."'".$attributes[$key]."'"; 
       $count++;
       continue; 
   }
   if(strlen($attributes[$key])>0){
      $sql .= " AND "; 
      $sql .= $key."="."'".$attributes[$key]."'";
   }

}
//echo("SQL: ".$sql);
$query = "$sql";
$result = mysql_query($query); 

while($restaurant = mysql_fetch_array($result, MYSQL_ASSOC)){
	$name = $restaurant['Name'];
	echo('['.$restaurant["Business_Name"].':'.$restaurant["Name"].'],');
}
mysql_close();


/*

function getRestaurant($name){
	//$name = "Thai";
	$query = "SELECT * FROM restaurants where Name LIKE '%".str_replace(" ", "-", $name)."%'";
	$result = mysql_query($query);
	//echo('<div>Here!</div>');
	while($restaurant = mysql_fetch_array($result, MYSQL_ASSOC)){
		$name = $restaurant['Name'];
		//echo('<div>Here Inside!</div>');
		echo('['.$restaurant["Business_Name"].':'.$restaurant["Name"].'],');
	}
	mysql_close();
} */
 ?> 