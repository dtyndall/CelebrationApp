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
			   <br><br>
				<?php
include 'dbconnect.php';
/*
				$username="root";
                $password="";
                $database="test";
			    
				     // get the session information that has the same id selected in edit_session.php
					 mysql_connect('localhost',$username,$password);

					@mysql_select_db($database) or die( "Unable to select database");
*/
			
                    $Id = $_GET['id'];
                    $Q1 = "Select Location, Date, Time FROM session  WHERE  session_id = ".$_GET['id'];
					
  
                    $result= mysql_query($Q1 );

                       while($row = mysql_fetch_array($result))
                          { 
                         
						  $Location = $row['Location'];
					      $Date = $row['Date'];
					      $Time = $row['Time'];
				        
					      
                          } 
					 
					 
                    mysql_close();
				?>
				<h2>Add New Conference</h2>
				<!-- Display the session information so the user will be able to do any change in it -->
				<form name = "sql_edit_session.php" action = "sql_edit2_session.php" method = "post">
					<ul id="metadata">
					      <li>Id: <input type="text" name="session_id"  value="<?php echo $Id; ?>" ></li><br><br> 
						<li>Location: <input type="text" name="Location" value="<?php echo  $Location; ?>" ></li><br><br><br> 
						<li>Date: <input type="text" name="Date" value="<?php echo $Date; ?>" ></li><br><br><br>
						<li>Time: <input type="text" name="Time" value="<?php echo $Time; ?>" ></li><br><br><br> 
						
						
						
					</ul>
					<ul id="options">
						
						<br><br> <li><input type="submit" value="Edit Session" /></li>
					</ul>
					
				</form>
			</div>
			<div id="footer">
				<p>&copy; 2013 Group Nine </p>
			</div>	
		</div>
	</body>
</html>