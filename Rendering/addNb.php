<?php

require_once('connect.php');

if(isset($_GET['title']) && isset($_GET['choice']))
{
	$title=$_GET['title'];
	$choice=$_GET['choice'];

	$query_insert="update survey set Number=Number+1 where Title= '".$title."' AND choice='".$choice."'";
	
	$result=mysqli_query($l,$query_insert) or die(mysqli_error());
	if($result)
	{
		echo " new nb add succe" ;
	}
	else
		echo "erroe not add";
}
	

?>