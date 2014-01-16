

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
				<ul id="topRow" class="navigationRow">
				<!-- user has two option in the first row (Conference and Event) that control the second row view -->
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

$EventId= (int)$_POST['event_id'];
$Year=mysql_real_escape_string($_POST['Conf_year']);
$EventName= mysql_real_escape_string($_POST['event_name']);
$AuthorName= mysql_real_escape_string ($_POST['author_name']);
$EventDescription= mysql_real_escape_string ($_POST['event_description']);
$EventCategory= mysql_real_escape_string ($_POST['event_category']);
$survey= mysql_real_escape_string ($_POST['survey']);


// save the new event information after the change 
mysql_connect('localhost',$username,$password);
@mysql_select_db($database) or die( "Unable to select database");
$query = "UPDATE event  SET Conf_year = '$Year', event_name = '$EventName' ,author_name = '$AuthorName' ,event_description = '$EventDescription' ,event_category = '$EventCategory', survey = '$survey' 
 WHERE event_id= $EventId";

 

mysql_query($query)or die(mysql_error());
if(mysql_affected_rows()>=1){
    echo "<p>($EventName) Record Updated<p>";
}else{
    echo "<p>($EventName) Not Updated<p>";
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