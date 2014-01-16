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
					<li class="navigation"><a href="./conference.php">Conference</a></li>
					<li class="navigation"><a href="./event.php">Event</a></li>
				
				</ul>
				<ul id="bottomRow" class="navigationRow">
					<li class="navigation"><a href="./add_event.php">Add Event</a></li>
					<li class="navigation"><a href="./all_event.php">All Events</a></li>
					<li class="navigation"><a href="./all_event.php">All Sessions</a></li>
					
				</ul>
				
			</div>
		<div class="content">
		         <br><br>
				  <!-- php code connect the web to the database -->
				 <!-- select all the sessions information from the sessions table -->
				<?php
include 'dbconnect.php';
/*
				$username="root";
                $password="";
                $database="test";
					 mysql_connect('localhost',$username,$password);

					@mysql_select_db($database) or die( "Unable to select database");
*/
					$query="SELECT e.event_id, e.event_name,s.Location, s.Date, s.Time, s.session_id FROM  event as e JOIN session as s ON e.event_id = s.event_id " ;
                     			
					$result = mysql_query($query);

					echo "<table>
					<tr>
						<th>  &nbsp  Event  &nbsp    </th>
						<th>  &nbsp  Location &nbsp    </th>
						<th>  &nbsp  Date &nbsp    </th>
						<th>  &nbsp  Time &nbsp    </th>
						<th>  &nbsp  Edit  &nbsp    </th>
						<th>  &nbsp  Delete &nbsp  </th>
                       						
					</tr>";
                  
				    // Display all the information in a table 
					// Return session_id if edit or delete selected
					while($row = mysql_fetch_array($result))
					{
						echo "<tr>"; 
						echo "\n\n";  
						echo "<td>" .$row['event_name']."</td>";
						echo "<td>" .$row['Location']."</td>";
						echo "<td>" .$row['Date']."</td>";
						echo "<td>" .$row['Time']."</td>";
					    echo  '<td><a href="sql_edit_session.php?id=' .$row['session_id'].'">Edit</a> </td>';
						echo  '<td><a href="sql_delete_session.php?id=' .$row['session_id'].'">Delete</a> </td>';
						echo "\n\n";  
						echo "</tr>";
					}
					echo "</table>";

					mysql_close();
				?>
			</div>
			<div id="footer">
				<p>&copy; 2013 Group Nine </p>
			</div>	
		</div>
	</body>
</html>