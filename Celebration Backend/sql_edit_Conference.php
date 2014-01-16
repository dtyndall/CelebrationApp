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
			    
				    // get the conferense information that has the same id selected in edit_conference.php
					 mysql_connect('localhost',$username,$password);

					@mysql_select_db($database) or die( "Unable to select database");
			
                    $Id = $_GET['id'];
                    $Q1 = "Select Conf_Name, Conf_year, Conf_location, Conf_phone, Conf_map FROM conference WHERE  Conf_Id = ".$_GET['id'];
					

                    $result= mysql_query($Q1 );

                       while($row = mysql_fetch_array($result))
                          { 
                         
						  $Name = $row['Conf_Name'];
					      $Year = $row['Conf_year'];
					      $Location = $row['Conf_location'];
				          $Phone = $row['Conf_phone'];
					      $Map = $row['Conf_map'];
                          } 
					 
					 
                    mysql_close();
				?>
				<!-- Display the conference information so the user will be able to do any change in it -->
				<h2>Add New Conference</h2>
				<form name = "sql_edit_Conference.php" action = "sql_edit2_Conference.php" method = "post">
					<ul id="metadata">
					    <li>Id: <input type="text" name="Conference_Id"  value="<?php echo $Id; ?>" ></li><br><br> 
						<li>Name: <input type="text" name="Conference_name" value="<?php echo $Name; ?>"></li><br><br> 
						<li>Year: <input type="text" name="Conference_Year" value="<?php echo $Year; ?>"  ></li><br><br> 
						<li>Location: <input type="text" name="Conference_Location" value="<?php echo $Location; ?>" ></li><br><br> 
						<li>Phone: <input type="text" name="Conference_Phone" value="<?php echo $Phone; ?>" ></li><br><br> 
						<li>Map: <input type="text" name="Conference_Map" value="<?php echo $Map; ?>"  ></li><br><br>  
						
					</ul>
					<ul id="options">
						
						<br><br> <li><input type="submit" value="Edit Conference" /></li>
					</ul>
					
				</form>
			</div>
			<div id="footer">
				<p>&copy; 2013 Group Nine </p>
			</div>	
		</div>
	</body>
</html>