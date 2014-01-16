
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


$Year=$_POST['Conf_year'];
$EventName=$_POST['event_name'];
$Authoer=$_POST['author_name'];
$Description=$_POST['event_description'];
$Category=$_POST['event_category'];
$survey=$_POST['survey'];

include 'dbconnect.php';
/*
$username="root";
$password="";
$database="test";
// add the event information in the event table
// add the session information in the session table with event id 
mysql_connect('localhost',$username,$password);
@mysql_select_db($database) or die( "Unable to select database");
*/

$query = "INSERT INTO event (Conf_year, event_name,author_name,event_description,event_category, survey)
 VALUES ('$Year', '$EventName','$Authoer','$Description','$Category', '$survey')";
mysql_query($query);

$Event_id = " SELECT event_id FROM event WHERE event_name = '$EventName'";

$result= mysql_query($Event_id );

while($row = mysql_fetch_array($result))
  { 
$ID = $row['event_id'];
 } 
 $i = 1;
 $count = $_POST['no_of_Session'];
 while ($i<= $count)
 {
 $Location=$_POST['Location_'.$i];
 $Date=$_POST['Date_'.$i];
 $Time = $_POST['Time_'.$i];
 
$query2 = "INSERT INTO  session (Location,Date,Time, event_id)  VALUES 
('$Location','$Date','$Time','$ID')";
mysql_query($query2);
$i++;
}
 
 


    echo "<p> Added event<p>";

 
 
mysql_close();


?>
</div>
			<div id="footer">
				<p>&copy; 2013 Group Nine </p>
			</div>	
		</div>
	</body>
</html>