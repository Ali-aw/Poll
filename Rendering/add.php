<?php

require_once('connect.php');

if(isset($_GET['password']) && isset($_GET['type']) && isset($_GET['email']))
{
	$pass=$_GET['password'];
	$type=$_GET['type'];
	$email=$_GET['email'];
	
	$query_insert="INSERT INTO ul(email,password,type) VALUES ('".$email."','".$pass."','".$type."')";
	
	$result=mysqli_query($l,$query_insert) or die(mysqli_error());
	
	if($result)
	{
		echo " new user add succe" ;
	}
	else
		echo "erroe not add";
}
?>