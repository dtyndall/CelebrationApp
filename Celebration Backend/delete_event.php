<!DOCTYPE html>
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
				<h2>Delete Event</h2>
				<form name = "edit_event" action = "sql_edit_event.php" method = "post">
					<ul id="metadata">
						<li>Event: <input type="text" name="event_name"></li><br><br> 
						
					</ul>
					<ul id="options">
						
						<br><br> <li><input type="submit" value="Search" /></li>
					</ul>
					
				</form>
			</div>
			<div id="footer">
				<p>&copy; 2013 Group Nine </p>
			</div>	
		</div>
	</body>
</html>