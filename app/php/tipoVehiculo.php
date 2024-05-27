<?php
// Configuración de la conexión a la base de datos
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "itvpruebadev2";

// Crear conexión
$conn = new mysqli($servername, $username, $password, $dbname);

// Verificar si el usuario existe
$sql = "SELECT * FROM tipo_vehiculo";

$result = $conn->query($sql);

if ($result->num_rows > 0) {
    // Mostrar los resultados de la consulta
    while ($row = $result->fetch_assoc()) {
        echo $row["Nombre"].",".$row["id"].",";
    }
} else {
    // No hay resultados
    echo "No hay tipo de vehiculos en la base de datos.";
}

// Cerrar la conexión
$conn->close();
?>
