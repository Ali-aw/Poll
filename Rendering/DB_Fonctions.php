<?php
function connectServer($host,$log,$pass,$mess)
{ 
	$dbc=@mysqli_connect($host,$log,$pass) 
	  or die("connection error:".@mysqli_errno($dbc).
	         ": ".@mysqli_error($dbc)
			 );
	
	if($mess == 1)	print '<p>Successfully connected to MySQL!</p>';
	return $dbc;
}

function selectDB($dbc, $db, $mess)
{
	mysqli_select_db($dbc ,$db) 
	 or die ('<p style="color: red;">'.
			 "Could not select the database ".$db.
			 "because:<br/>".mysqli_error($dbc).
			 ".</p>");
	
	if ($mess == 1) echo "<p>The database $db has been selected.</p>";
}

function createDB($dbc,$db)
{
	$query= "CREATE DATABASE ".$db;
	mysqli_query($dbc,$query) 
	 or die('<p style="color: red;">'.
	        "Could not create the database ".
			$db." because:<br>".mysqli_error($dbc).
			".</p>");
		
	echo "<p>The database $db has been created!</p>";
}

function deleteDB($dbc,$db)
{
	$query= "DROP DATABASE IF EXISTS ".$db;
	mysqli_query($dbc,$query) 	 
     or die("DB Error: Could not delete the data base ".
		    $db."! <br>".@mysqli_error($dbc));
	
	print "<p> Data base $db deleted.</p>";
}

function createTable($dbc,$query)
{
	//selectDB($dbc, $db); select base deja fait
	// Execute the query:
	if (@mysqli_query($dbc,$query))
	{
		print "<p> The table has been created.</p>";
	}
	else
	{
		$str='<p style="color: red;">';
		$str.="Could not create the table because:<br>";
		$str.=mysqli_error($dbc);
		$str.=".</p><p>The query being run was:".$query."</p>";
		print $str;		    
	}
}

function deleteDataFromTab($dbc, $Tab)
{
	$query = "DELETE FROM ".$Tab;
    @mysqli_query($dbc,$query) 
    or die ("DB Error: Could not delete data from table $Tab! <br>".
		     @mysqli_error($dbc));
	
	print "<p> All data are deleted inside table $Tab.</p>";
}

function deleteTable($dbc, $Tab)
{
	$query = "DROP TABLE IF EXISTS ".$Tab;
    @mysqli_query($dbc,$query) 
      or die ("DB Error: Could not delete table person! <br>".
	          @mysqli_error($dbc));
	
	print "<p> Table $Tab deleted.</p>";
}

function insertDataToTab($dbc, $query)
{
    @mysqli_query($dbc,$query) 
      or die ("DB Error: Could not insert ! <br>".
			  @mysqli_error($dbc));
   
    print ("<h2 style = 'color: blue'> The was added successfully! </h2>");	
}

function executeQuery($dbc, $query)
{
    @mysqli_query($dbc,$query) 
      or die ("DB Error: Could not execute the query! <br>".
			  @mysqli_error($dbc));
   
    print ("<h2 style = 'color: blue'> The query was executed successfully! </h2>");	
}
?>