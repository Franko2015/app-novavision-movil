<?php
header('Content-Type: text/html;charset=utf-8');

$con = mysqli_connect('192.168.191.251:3306','nova','nova','novavision');

function ejecutarSQLCommand($commando){
    $mysqli = mysqli_connect('192.168.191.251:3306','nova','nova','novavision') or die('No fue posible conectar');
    mysqli_set_charset($mysqli, 'utf8');

    if ($mysqli->connect_errno) {
        echo "Conexión fallida: %s\n",$mysqli->connect_error;
        exit();
    }
    if ($mysqli) {
        if ($resultSet = $mysqli->store_result()) {
            while ($row = $resultSet -> fetch_array(MYSQLI_BOTH)) {
            }
            $resultSet->free();
        }
    }
    $mysqli->close();
}
function getSQLResultSet($commando){
    $mysqli = mysqli_connect('192.168.191.251:3306','nova','nova','novavision') or die('No fue posible conectar');
    mysqli_set_charset($mysqli, 'utf8');

    if ($mysqli->connect_errno) {
        echo "Conexión fallida: %s\n", $mysqli->connect_error;
        exit();
    }
    if ($mysqli->multi_query($commando)) {
        return $mysqli->store_result();
    }
    $mysqli->close();
}
?>
