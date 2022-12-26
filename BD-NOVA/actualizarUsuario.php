<?php

include('Conexion/conexion.php');

$id_cliente=$_GET['id_cliente'];
$codigo=$_GET['codigo'];

$resultSet = "UPDATE usuarios SET codigo='$codigo' WHERE id='$id_cliente'";

$resultado=mysqli_query($con,$resultSet);

mysqli_close($con);
?>