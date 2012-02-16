<?php

// get name
$name=$_GET["q"];

if($name){
	// load database
	mysql_connect("localhost", "root", "1990") or die ("Connection failed");
	$db = "revminer";
	mysql_select_db($db) or die ("Could not select the database '" .$db. "'. Are you sure it exists?"); 
	
	// display restaurant
	display($name);
}

// display restaurant information
function display($name){
	$query = "SELECT * FROM restaurants where Name='".$name."'";
	$result = mysql_query($query);
       /*  $restaurant = mysql_fetch_array($result, MYSQL_ASSOC)
        echo("CATEGORY: ".$restaurant['Name']); */
	while(list($Name, $Attire, $Accepts_Credits_Cards, $Phone_number, $Takes_Reservations, $Neighborhood, $Ambience, $Good_for, $Latitude, $Business_Name, $Good_for_Groups, $Outdoor_Seating, $Business_type, $Wi_Fi, $Alcohol, $Waiter_Service, $Delivery, $Good_for_Kids, $Parking, $Hours, $Take_out, $Price_Range, $City, $Categrory, $Noise_Level, $Wheelchair_Accessible, $Has_TV, $Address, $Longitude, $Dogs_Allowed, $Caters, $Drive_Thru, $Coat_Check, $Smoking, $Best_Neights, $Happy_Hour, $Good_For_Dacing) = mysql_fetch_array($result)){ 

		/*echo("CATEGORY: ".$restaurant['Name']); */
		//echo("<div>Check</div><br/>");
		echo($Business_Name.'`'.$Address.'`'.$Hours.'`'.$Price_Range.'`'.$Neighborhood.'`'.$Attire.'`'.$Alcohol.'`'.$Good_for_Groups.'`'.$Waiter_Service.'`'.$Good_for_Kids.'`'.$Wi_Fi.'`'.$Good_for.'`'.$Takes_Reservations.'`'.$Accepts_Credits_Cards.'`'.$Parking.'`'.$Latitude.'`'.$Longitude.'`'.$Business_type.'`'.$Category.'`'.$Phone_number.'`'.$Ambience.'`'.$Delivery.'`'.$Take_out.'`'.$City.'`'.$Noise_level.'`'.$Outdoor_Seating.'`'.$Wheelchair_Accessible.'`'.$Has_TV.'`'.$Dogs_Allowed.'`'.$Caters.'`'.$Drive_Thru.'`'.$Coat_Check.'`'.$Smoking.'`'.$Best_Neights.'`'.$Happy_Hour.'`'.$Good_For_Dacing); 
		
	} 
	mysql_close();
}

?>