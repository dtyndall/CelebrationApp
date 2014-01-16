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
			<!-- user has two option on the first row (Conference and Event) that control the second row view -->
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
			<!-- post the conferecne information to sql_add_conference -->
				<h2>Add New Conference</h2>
				<form name = "add_Conference" action = "sql_add_conferance.php" method = "post">
					<ul id="metadata">
						<li>Name: <input type="text" name="Conference_name"></li><br><br> 
						<li>Year: <input type="text" name="Conference_Year"></li><br><br> 
						<li>Location: <input type="text" name="Conference_Location"></li><br><br> 
						<li>Phone: <input type="text" name="Conference_Phone"></li><br><br> 
						<li>Map: <input type="text" name="Conference_Map"></li><br><br>  
						
					</ul>
					<ul id="options">
						
						<br><br> <li><input type="submit" value="Add Conference" /></li>
					</ul>
					
				</form>
			</div>
			<div id="footer">
				<p>&copy; 2013 Group Nine </p>
			</div>	
		</div>
	</body>
</html>