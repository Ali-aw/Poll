<?php

require_once('connect.php');

if(isset($_GET['email']) && isset($_GET['title']))
{
	$title=$_GET['title'];
	$email=$_GET['email'];

	$query_insert="INSERT INTO vote(Email,Title) VALUES ('".$email."','".$title."')";
	
	$result=mysqli_query($l,$query_insert) or die("you can't vote ");
	
	if($result)
	{
		echo "aa" ;
	}
	else
		echo "erroe not add";
}
?>