<?php

include('Conexion/conexion.php');

$array = array();

$id_cliente=$_GET['id_cliente'];

if ($resultSet = getSQLResultSet("SELECT f.id, f.idcliente, f.emitido, f.vencimiento, f.pago, f.total, f.forma, f.estado, f.cobrado, f.iva_igv, f.sub_total, f.impuesto, fi.descripcion, fi.idfactura, fi.impuesto FROM facturas f INNER JOIN facturaitems fi WHERE f.idcliente='$id_cliente' and f.id = fi.idfactura ORDER BY f.id DESC limit 15")) {
    
    while ($row = $resultSet-> fetch_array(MYSQLI_NUM)) {

        $e = array();

        $e['id'] = $row[0];
        $e['id_cliente'] = $row[1];
        $e['f_emitido'] = $row[2];
        $e['f_vencimiento'] = $row[3];
        $e['f_pagado'] = $row[4];
        $e['total'] = $row[5];
        $e['forma'] = $row[6];
        $e['estado'] = $row[7];
        $e['cobrado'] = $row[8];
        $e['iva_igv'] = $row[9];
        $e['sub_total'] = $row[10];
        $e['impuesto'] = $row[11];
        $e['descripcion'] = $row[12];
        $e['boletaId'] = $row[13];
        $e['impuesto'] = $row[14];

        array_push($array,$e);

    }

    $codigo1 = base64_encode(json_encode($array));
    $codigo2 = base64_encode($codigo1);
    echo base64_encode($codigo2);
    
}
?>
