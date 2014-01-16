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
				 
				 <!-- php code connect the web to the database -->
				 <!-- Get all the events from the event table -->
				<?php
				$username="root";
                $password="";
                $database="test";
					 mysql_connect('localhost',$username,$password);

					@mysql_select_db($database) or die( "Unable to select database");
					$query="SELECT event_id, event_name FROM  event  " ;
                     			
					$result = mysql_query($query);

					echo "<table>
					<tr>
						<th>  &nbsp  Event &nbsp    </th>
						<th>  &nbsp  Edit  &nbsp    </th>
						<th>  &nbsp  Delete &nbsp  </th>
                       						
					</tr>";

					while($row = mysql_fetch_array($result))
					{
						echo "<tr>"; 
						echo "\n\n";  
						echo "<td>" .$row['event_name']."</td>";
					    echo  '<td><a href="sql_edit_event.php?id=' .$row['event_id'].'">Edit</a> </td>';
						echo  '<td><a href="sql_delete_event.php?id=' .$row['event_id'].'">Delete</a> </td>';
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