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
				<li class="navigation"><a href="./add_Conference.php">Add Conference</a></li>
				<li class="navigation"><a href="./edit_Conference.php">Edit Conference</a></li>
				<li class="navigation"><a href="./delete_Conference.php">Delete Conference</a></li>
					
				</ul>
				
			</div>
		<div class="content">
		         <br><br>
				<?php
				$username="root";
                $password="";
                $database="test";
			    
				    
					 mysql_connect('localhost',$username,$password);

					@mysql_select_db($database) or die( "Unable to select database");
			        // delete the conference that selected in delete_Conference.php 
 
                    $query = "DELETE FROM conference WHERE  Conf_Id = ".$_GET['id'];
                    
					
					  mysql_query($query)or die(mysql_error());
                      if(mysql_affected_rows()>=1){
                       echo "<p> Deleted<p>";
                       }else{
                       echo "<p> Not Deleted<p>";
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