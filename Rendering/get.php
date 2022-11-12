<?php

require_once('connect.php');

if(isset($_GET['id']))
{
	$id=$_GET['id'];
	
	$query_search="select * from ul where id ='".$id."'";
	
	$result=mysqli_query($l,$query_search) or die(mysqli_error());
	
	if(mysqli_num_rows($result)>0)
	{
		while(@$row=mysqli_fetch_array($result))
			echo $row['fN']." ".$row['lN']." ".$row['phone']." ".$row['email'];
	}
	else
		echo "erroe not ";
}
?>