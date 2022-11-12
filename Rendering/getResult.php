<?php

require_once('connect.php');

if(isset($_GET['title']))
{
	$title=$_GET['title'];
	
	$query_search="select choice,Number from survey where Title ='".$title."'";
	
	$result=mysqli_query($l,$query_search) or die(mysqli_error());
	
	if(mysqli_num_rows($result)>0)
	{
		while(@$row=mysqli_fetch_array($result))
			echo $row['choice']." take: ".$row['number'].",";
	}
	else
		echo "erroe not ";
}
?>