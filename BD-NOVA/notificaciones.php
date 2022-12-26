<?php

header('Content-Type: text/html;charset=utf-8');

include('Conexion/conexion.php');

$array = array();

$id_cliente=$_GET['id_cliente'];

if ($resultSet = getSQLResultSet("SELECT id, enviado, ndestino, stado, mensaje FROM smsmensajes WHERE user='$id_cliente' ORDER BY id DESC limit 15;")) {

    while ($row=mysqli_fetch_assoc($resultSet)) {

        $e = array();

        $e['id'] = $row['id'];
        $e['enviado'] = $row['enviado'];
        $e['ndestino'] = $row['ndestino'];
        $e['stado'] = $row['stado'];
        $e['mensaje'] = $row['mensaje'];

        #Se agregan los datos de la variable array "e" a la variable "array"
        array_push($array,$e);

    }
    #Se escribe el array como código JSON
    $codigo1 = base64_encode(json_encode($array));
    $codigo2 = base64_encode($codigo1);
    echo base64_encode($codigo2);
}
?>
