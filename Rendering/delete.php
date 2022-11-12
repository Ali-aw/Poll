<?php

require_once('connect.php');

	
	$query_search="DELETE FROM survey" ;
	
	$result=mysqli_query($l,$query_search) or die(mysqli_error());

?>