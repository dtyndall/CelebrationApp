<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" >
		<meta name="viewport" content="user-scalable=no">
		<meta name="description" content="Stars Celebration"/>
		<!--<meta name="author" content=""/>-->
		<title>Stars Celebration - All Events</title>
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
				<?php
include 'dbconnect.php';
/*
				$username="root";
                $password="";
                $database="test";
			    
				    
					 mysql_connect('localhost',$username,$password);

					 // delete all the events that selected in delete.php 
					 // delete all sessions that has the same event id from session table 
					@mysql_select_db($database) or die( "Unable to select database");
*/
					$query3 =" SELECT  event_name FROM  event WHERE  event_id = " .$_GET['id'];
					$name=  mysql_query($query3);
					$event_name = mysql_query($query3);
				    $query2 = "DELETE FROM session WHERE  event_id = " .$_GET['id'];
                     mysql_query($query2);
 
                    $query = "DELETE FROM event WHERE  event_id = ".$_GET['id'];
                   
					
					 mysql_query($query);
                     echo "<p> Deleted<p>";
                     
					

                    mysql_close();
				?>
			</div>
			<div id="footer">
				<p>&copy; 2013 Group Nine </p>
			</div>	
		</div>
	</body>
</html>