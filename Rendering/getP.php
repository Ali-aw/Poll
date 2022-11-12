<?php

require_once('connect.php');

if(isset($_GET['title']))
{
	$title=$_GET['title'];
	
	$query_search="select Email from vote where Title ='".$title."'";
	
	$result=mysqli_query($l,$query_search) or die(mysqli_error());
	
	if(mysqli_num_rows($result)>0)
	{
		while(@$row=mysqli_fetch_array($result))
			echo $row['email'].",";
	}
	else
		echo "erroe not ";
}
?>