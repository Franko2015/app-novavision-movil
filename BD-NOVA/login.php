<?php

include('Conexion/conexion.php');

$array = array();

if ($resultSet = getSQLResultSet("SELECT id, codigo FROM usuarios")) {

    while ($row=mysqli_fetch_assoc($resultSet)) {

        $e = array();

        $e['id'] = $row['id'];
        $e['codigo'] = $row['codigo'];

        array_push($array,$e);

    }

    $codigo1 = base64_encode(json_encode($array));
    $codigo2 = base64_encode($codigo1);
    echo base64_encode($codigo2);
}

?>