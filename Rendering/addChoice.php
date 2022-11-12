<?php

require_once('connect.php');

if(isset($_GET['title']) && isset($_GET['choice']) && isset($_GET['date']))
{
	$title=$_GET['title'];
	$choice=$_GET['choice'];
	$date=$_GET['date'];
	
	$query_insert="INSERT INTO survey(choice,date,Number,Title) VALUES ('".$choice."','".$date."',0,'".$title."')";
	
	$result=mysqli_query($l,$query_insert) or die(mysqli_error());
	
	if($result)
	{
		echo " new choice add succe" ;
	}
	else
		echo "erroe not add";
}
?>