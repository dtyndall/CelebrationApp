

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
					<li class="navigation"><a href="./add_Conference.php">Add Conference</a></li>
					<li class="navigation"><a href="./edit_Conference.php">Edit Conference</a></li>
					<li class="navigation"><a href="./delete_Conference.php">Delete Conference</a></li>
				</ul>
			</div>
				<div class="content">
		         <br><br>
<?

$ConfId= (int)$_POST['Conference_Id'];
$ConfName= mysql_real_escape_string($_POST['Conference_name']);
$ConfDate= mysql_real_escape_string ($_POST['Conference_Year']);
$ConfLoaction= mysql_real_escape_string ($_POST['Conference_Location']);
$ConfPhone= mysql_real_escape_string ($_POST['Conference_Phone']);
$ConfMap= mysql_real_escape_string ($_POST['Conference_Map']);

include 'dbconnect.php';
/*
$username="root";
$password="";
$database="test";

// save the new conference information after the change 
mysql_connect('localhost',$username,$password);
@mysql_select_db($database) or die( "Unable to select database");
*/

$query = "UPDATE conference  SET Conf_Name = '$ConfName' ,Conf_year = '$ConfDate' ,Conf_location = '$ConfLoaction' ,Conf_phone = '$ConfPhone' ,Conf_map = '$ConfMap' 
 WHERE Conf_Id = $ConfId";


mysql_query($query)or die(mysql_error());
if(mysql_affected_rows()>=1){
    echo "<p>($ConfName) Record Updated<p>";
}else{
    echo "<p>($ConfName) Not Updated<p>";
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