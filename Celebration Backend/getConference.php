
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
$result = mysql_query("SELECT * FROM conference WHERE 
    Conf_Id=( SELECT max(Conf_Id) FROM conference)") or die(mysql_error());
 
// check for empty result
if (mysql_num_rows($result) > 0) {
    // looping through all results
    // room node
    $response["conference"] = array();
 
    while ($row = mysql_fetch_array($result)) {
        // temp conference array
        $conference = array();
      
        $conference["Conf_Id"] = $row["Conf_Id"];
        $conference["Conf_Name"] = $row["Conf_Name"];
        $conference["Conf_year"] = $row["Conf_year"];
        $conference["Conf_location"] = $row["Conf_location"];
        $conference["Conf_map"] = $row["Conf_map"];

      
 
        // push single product into final response array
        array_push($response["conference"], $conference);
  
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