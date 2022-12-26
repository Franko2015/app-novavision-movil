<?php 

header('Content-Type: text/html;charset=utf-8');

include('Conexion/conexion.php');
#Se llama la consulta de conexion a la BD

$array = array();
#Crea un array

$id_cliente=$_GET['id_cliente'];
#ID del cliente del que se envía por GET

if ($resultSet = getSQLResultSet("SELECT id, nombre, estado, correo, movil, cedula, codigo, direccion_principal FROM usuarios WHERE id ='$id_cliente';")) {

    while ($row=mysqli_fetch_assoc($resultSet)) {

        #Mientras hay algún resultado desde la BD muestra los datos con la variable row
        $e = array();
        
        #El array se guarda como variable "e"
        $e['id'] = $row['id'];
        $e['nombre'] = $row['nombre'];
        $e['estado'] = $row['estado'];
        $e['correo'] = $row['correo'];
        $e['movil'] = $row['movil'];
        $e['cedula'] = $row['cedula'];
        $e['codigo'] = $row['codigo'];
        $e['direccion_principal'] = $row['direccion_principal'];

        #Se agregan los datos de la variable array "e" a la variable "array"
        array_push($array,$e);

    }
    #Se escribe el array como código JSON
    $codigo1 = base64_encode(json_encode($array));
    $codigo2 = base64_encode($codigo1);
    echo base64_encode($codigo2);
}

?>