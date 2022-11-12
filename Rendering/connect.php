<?php
$h="localhost";
$database="db1forandroid";
$u="root";
$p="";


$l=mysqli_connect($h,$u,$p) or trigger_error(mysql_error(),E_USER_ERROR);

mysqli_select_db($l,$database);


?>