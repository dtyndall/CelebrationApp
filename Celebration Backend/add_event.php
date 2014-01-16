<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" >
		<meta name="viewport" content="user-scalable=no">
		<meta name="description" content="Stars Celebration"/>
		<!--<meta name="author" content=""/>-->
		<title>Stars Celebration - Add New Event</title>
		<link type="text/css" href="./default.css" rel="stylesheet" />
		<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
		<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
 		<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
		<script type="text/javascript">
		<!-- addSessionfield add fields dynamically on the html page every time the user click on add fields -->
			function addSessionField()
			{
				var currentNumber = document.getElementsByClassName("SessionNameBox").length;
				currentNumber++;
				//alert(currentNumber);
				document.getElementById("SessionCount").value = "" + currentNumber;
				var newLocation = document.createElement("LI");
				var newDate = document.createElement("LI");
				var newTime = document.createElement("LI");
				var br = document.createElement("br");
				var ul = document.getElementById("session_info");
			
			
		        ul.appendChild(br);
				ul.appendChild(newLocation);
				ul.appendChild(br);
		
				ul.appendChild(newDate);
			    ul.appendChild(br);
				ul.appendChild(newTime);
			    
				//create the new fields 
				newLocation.innerHTML = "Location: <input type=\"text\" name=\"Location_" + currentNumber + "\" class=\"SessionNameBox\" /> ";
				newDate.innerHTML = "Date: <input type=\"text\" class=\"newDateInput\" name=\"Date_" + currentNumber + "\"/> ";
				newTime.innerHTML = "Time: <input type=\"text\" name=\"Time_" + currentNumber + "\"/>";
			}
			
			 $(function() {
    			$( ".dateInput" ).datepicker({ dateFormat: 'yy-mm-dd' });
 			 $("#addSessionButton").click(function() {
				$( ".newDateInput" ).delay(600).datepicker({ dateFormat: 'yy-mm-dd' });
				

 			 })

 			 });
 			 

		</script>
	
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
						<li class="navigation"><a href="./add_event.php">Add Event</a></li>
						<li class="navigation"><a href="./all_event.php">All Events</a></li>
						<li class="navigation"><a href="./all_Sessions.php">All Sessions</a></li>
				
				</ul>
			</div>
			<div class="content">
				<h2>Add New Event</h2>
					<!-- post the information the user entered to sql_add_event.php -->
				<form name = "add_event" action = "sql_add_event.php" method = "post">
					<ul id="metadata">
						<li>Event: <input type="text" name="event_name"></li><br><br>
                        <li>Conf. Year: <input type="text" name="Conf_year"></li><br><br> 						
						<li>Author: <input type="text" name="author_name"></li><br><br>  
						<li>Description: <input type="text" name="event_description"></li><br><br> 
						<li>Category: 
							<select name="event_category">
								<option value="Presentation">Presentation</option>
								<option value="Workshop">Workshop</option>
								<option value="Poster">Poster</option>
							</select>
							<li>Survey: <input type="text" name="survey"></li><br><br> 
						</li><br><br>  
					</ul>
					<ul id="session_info">
						<li>Location: <input type="text" name="Location_1" class="SessionNameBox"/></li><br/>
						<li>Date: <input type="text" class="dateInput" name="Date_1" /></li><br/>
						<li>Time: <input type="text" name="Time_1" /></li><br/> 
					</ul>
					<ul id="options">
					<!-- call addSessionField() everytime the user need to add more fields -->
						<li><input type="button" value="Add Field" id="addSessionButton" onclick="addSessionField()" /></li><br><br>
						<li>Session Count: <input type="text" name="no_of_Session" id="SessionCount" value="1" /></li><br><br>
						<br><br> <li><input type="submit"  value="Add Event" /></li>
					</ul>
				</form>
			</div>
			<div id="footer">
				<p>&copy; 2013 Group Nine </p>
			</div>	
		</div>
	</body>
</html>