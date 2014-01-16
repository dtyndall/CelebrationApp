
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
$result = mysql_query("SELECT * FROM event WHERE
                        Conf_year=( SELECT max(Conf_year) FROM event)") or die(mysql_error());
 
// check for empty result
if (mysql_num_rows($result) > 0) {
    // looping through all results
    // room node
    $response["events"] = array();
 
    while ($row = mysql_fetch_array($result)) {
        // temp event array
        $event = array();
      
        $event["event_id"] = $row["event_id"];
        $event["Conf_year"] = $row["Conf_year"];
        $event["event_name"] = $row["event_name"];
        $event["author_name"] = $row["author_name"];
        $event["event_description"] = $row["event_description"];
        $event["event_category"] = $row["event_category"];
        $event["survey"] = $row["survey"];
        $event["track"] = $row["track"];
        // push single product into final response array
        array_push($response["events"], $event);
  
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