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
				<?php
				$username="root";
                $password="";
                $database="test";
			    
				    
					 mysql_connect('localhost',$username,$password);

					  // get the event information that has the same id selected in edit_event.php
					@mysql_select_db($database) or die( "Unable to select database");
			
                    $Id = $_GET['id'];
                    $Q1 = "Select Conf_year, event_name, author_name, event_description, event_category, survey from event  WHERE  event_id = ".$_GET['id'];
					

                    $result= mysql_query($Q1 );

                       while($row = mysql_fetch_array($result))
                          { 
                         
						  $EventName = $row['event_name'];
					      $Author = $row['author_name'];
						  $Year=$row['Conf_year'];
					      $Description = $row['event_description'];
				          $Category = $row['event_category'];
						  $survey=$row['survey'];
					      
                          } 
					 
					 
                    mysql_close();
				?>
				<!-- Display the event information so the user will be able to do any change in it -->
				<h2>Add New Conference</h2>
				<form name = "sql_edit_event.php" action = "sql_edit2_event.php" method = "post">
					<ul id="metadata">
					    <li>Id: <input type="text" name="event_id"  value="<?php echo $Id; ?>" ></li><br><br> 
						<li>Name: <input type="text" name="event_name" value="<?php echo  $EventName; ?>" ></li><br><br> 
						<li>Author Name: <input type="text" name="author_name" value="<?php echo $Author; ?>" ></li><br><br> 
						<li>Conf. Year: <input type="text" name="Conf_year" value="<?php echo $Year; ?>" ></li><br><br> 
						<li>Description: <input type="text" name="event_description" value="<?php echo $Description; ?>" ></li><br><br> 
						<li>Category: <input type="text" name="event_category" value="<?php echo $Category ; ?>" ></li><br><br> 
						<li>Survey: <input type="text" name="survey" value="<?php echo $survey ; ?>" ></li><br><br> 
						
						
					</ul>
					<ul id="options">
						
						<br><br> <li><input type="submit" value="Edit Event" /></li>
					</ul>
					
				</form>
			</div>
			<div id="footer">
				<p>&copy; 2013 Group Nine </p>
			</div>	
		</div>
	</body>
</html>