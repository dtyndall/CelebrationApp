
<?php
 
/*
 * Following code will list all the room
 */
 
// array for JSON response
$response = array();
 
// include db connect class
 
mysql_connect('localhost', 'root', 'root') or die("Did not connect");
mysql_select_db("test") or die("Did not find DB");
 
// get all room from room table
$result = mysql_query("SELECT * FROM session") or die(mysql_error());
 
// check for empty result
if (mysql_num_rows($result) > 0) {
    // looping through all results
    // room node
    $response["session"] = array();
 
    while ($row = mysql_fetch_array($result)) {
        // temp  session array
        $session = array();
      
        $session["Location"] = $row["Location"];
        $session["Date"] = $row["Date"];
        $session["Time"] = $row["Time"];
        $session["event_id"] = $row["event_id"];
        $session["session_id"] = $row["session_id"];
 
        // push single product into final response array
        array_push($response["session"], $session);
  
    }
    // success
    $response["success"] = 1;
 
    // echoing JSON response
    echo json_encode($response);
} else {
    // no room found
    $response["success"] = 0;
    $response["message"] = "No room found";
 
    // echo no users JSON
    echo json_encode($response);
}
?>