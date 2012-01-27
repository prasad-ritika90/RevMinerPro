<!DOCTYPE html>
<html lang="en">
	<head>
		<title> Review Miner </title>
		<link rel="stylesheet" href="main.css" type="text/css" />
		<script src="main.js" type="text/javascript"></script>
		<header class="body"> 
			<img id="logo" src="logo4.png" alt="We mine your reviews" />
		</header>
		<div id="searchBoxOne">
			<form id="searchbox" action="results.html">
				<input id="search" type="text" placeholder="Search for restaurant">
				<input id="submit" type="submit" value="Search">
			</form>
		</div>
		<div>
			<form method="LINK" action="decider.html">
				<input id="decider" type="submit" value="Decider">
			</form>
			<span id="boldText">Need Help Deciding? Use our decider!</span>
		</div>
	</head>
	<hr class="divider"/>
	<div id="tvFrame">
		<div id="imageBox">
			<?php
				$dirname = "./featuredRestaurants/";
				$images = scandir($dirname);
				$ignore = Array(".", "..", "info.txt");
				foreach($images as $curimg){ 
					if(!in_array($curimg, $ignore)) {
						echo "<a href=\"restaurant.html\"><img id=\"tvShow\" src='featuredRestaurants/$curimg' /></a>\n";
					}
				}
			?>
		</div>
		<div id="messageBox">
			<a href="restaurant.html" id="tvTitle">Featured Restaurant:</a><br/>
			<?php
				$files = glob("./featuredRestaurants/*.txt");
				$info_file = $files[0];
				$fh = fopen($info_file, 'r');
				while(! feof($fh)) {
					$line = fgets($fh);
					echo "<a href=\"restaurant.html\" id=\"messageShow\"> $line </a>\n";
				}
				fclose($fh);			
			?>
		</div>
	</div>
</html>