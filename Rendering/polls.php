<?php

require_once('connect.php');

	$query_search="select Title from survey group by Title";
	
	$result=mysqli_query($l,$query_search) or die(mysqli_error());
	
	if(mysqli_num_rows($result)>0)
	{
		while(@$row=mysqli_fetch_array($result))
			echo $row['Title'].",";
	}
	else
		echo "erroe not ";
?>