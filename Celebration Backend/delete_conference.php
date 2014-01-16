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
				//open database connection 
include 'dbconnect.php';
/*
				$username="root";
                $password="";
                $database="test";
					 mysql_connect('localhost',$username,$password);
                     
					 //select the conferance information and display it in a table. 
					@mysql_select_db($database) or die( "Unable to select database");
*/
					$query="SELECT Conf_Id, Conf_Name, Conf_year FROM  conference " ;
				
                     			
					$result = mysql_query($query);

					echo "<table>
					<tr>
						<th>  &nbsp  Conferance Name &nbsp    </th>
						<th>  &nbsp  Conferance Year &nbsp    </th>
						<th>  &nbsp  Delete &nbsp  </th>
                       						
					</tr>";
					// return conference id when delete conference selected 
					while($row = mysql_fetch_array($result))
					{
						echo "<tr>"; 
						echo "\n\n";  
						echo "<td>" .$row['Conf_Name']."</td>";
						echo "<td>" .$row['Conf_year']."</td>";
						echo  '<td><a href="sql_delete_Conference.php?id=' .$row['Conf_Id'].'">Delete</a> </td>';
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