<?php

require_once('connect.php');

if(isset($_GET['Pass']) && isset($_GET['Email']))
{
	$pass=$_GET['Pass'];
	$Email=$_GET['Email'];
	
	$query_search="select * from ul where Email ='".$email."' AND Pass='".$pass."'" ;
	
	$result=mysqli_query($l,$query_search) or die(mysqli_error());
	
	if(mysqli_num_rows($result))
	{
		while(@$row=mysqli_fetch_array($result))
			echo $row['Type'];
	}
	else
		echo "erroe not ";

}