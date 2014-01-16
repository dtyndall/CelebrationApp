

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" >
		<meta name="viewport" content="user-scalable=no">
		<meta name="description" content="Stars Celebration"/>
		<!--<meta name="author" content=""/>-->
		<title>Stars Celebration - Add New Event</title>
		<link type="text/css" href="./default.css" rel="stylesheet" />

	
	</head>
	<body>
		<div id="container">
			<div id="navigation">
			<!-- user has two option in the first row (Conference and Event) that control the second row view -->
				<ul id="topRow" class="navigationRow">
					<li class="navigation"><a href="./conference.php">Conference</a></li>
					<li class="navigation"><a href="./event.php">Event</a></li>
				
				</ul>
				<ul id="bottomRow" class="navigationRow">
					<li class="navigation"><a href="./add_event.php">Add Event</a></li>
					<li class="navigation"><a href="./all_event.php">All Events</a></li>
					<li class="navigation"><a href="./all_Sessions.php">All Sessions</a></li>
				</ul>
			</div>
				<div class="content">
		         <br><br>
<?
$username="root";
$password="";
$database="test";

$SessionId= (int)$_POST['session_id'];
$Location= mysql_real_escape_string($_POST['Location']);
$Date= mysql_real_escape_string ($_POST['Date']);
$Time= mysql_real_escape_string ($_POST['Time']);


// save the new session information after the change 

mysql_connect('localhost',$username,$password);
@mysql_select_db($database) or die( "Unable to select database");
$query = "UPDATE session  SET Location = '$Location' , Date = '$Date' , Time = '$Time' 
 WHERE event_id= $SessionId";

 

mysql_query($query)or die(mysql_error());
if(mysql_affected_rows()>=1){
    echo "<p> Record Updated<p>";
}else{
    echo "<p> Not Updated<p>";
}
 
mysql_close();


?>
	</div>
			<div id="footer">
				<p>&copy; 2013 Group Nine </p>
			</div>	
		</div>
	</body>
</html>