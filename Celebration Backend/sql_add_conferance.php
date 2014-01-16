

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
					<li class="navigation"><a href="./add_Conference.php">Add Conference</a></li>
					<li class="navigation"><a href="./edit_Conference.php">Edit Conference</a></li>
					<li class="navigation"><a href="./delete_Conference.php">Delete Conference</a></li>
				</ul>
			</div>
				<div class="content">
		         <br><br>

<?

$ConfName=$_POST['Conference_name'];
$ConfDate=$_POST['Conference_Year'];
$ConfLoaction=$_POST['Conference_Location'];
$ConfPhone=$_POST['Conference_Phone'];
$ConfMap= $_POST['Conference_Map'];

include 'dbconnect.php';
/*
$username="root";
$password="";
$database="test";
// insert the conference information in the database table 
mysql_connect('localhost',$username,$password);
@mysql_select_db($database) or die( "Unable to select database");
*/
$query = "INSERT INTO conference (Conf_Name,Conf_year,Conf_location,Conf_phone,Conf_map)
 VALUES ('$ConfName','$ConfDate','$ConfLoaction','$ConfPhone','$ConfMap')";

mysql_query($query)or die(mysql_error());
if(mysql_affected_rows()>=1){
    echo "<p> $ConfName Saved<p>";
}else{
    echo "<p> $ConfName Not Saved<p>";
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