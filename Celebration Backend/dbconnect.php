<?php
$server="cci-vm4-11.uncc.edu";
$username="starsapp";
$password="starsapp123";
$database="test";

mysql_connect('localhost',$username,$password);

@mysql_select_db($database) or die( "Unable to select database");

?>