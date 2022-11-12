<?php

require_once('connect.php');

if(isset($_GET['title']) && isset($_GET['choice']))
{
	$title=$_GET['title'];
	$choice=$_GET['choice'];
	
	$query_search="DELETE FROM survey where Title ='".$title."' AND choice='".$choice."'" ;
	
	$result=mysqli_query($l,$query_search) or die(mysqli_error());

}