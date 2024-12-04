#!/bin/bash

# Lista de carpetas a procesar
folders=("DispositivoMovil" "ServidorWeb2" "EstacionDeTrabajo")

# Recorrer cada carpeta
for folder in "${folders[@]}"; do
    echo "Procesando carpeta: $folder"

    # Cambiar al directorio de la carpeta
    cd "$folder" || { echo "No se pudo acceder a $folder"; exit 1; }

    # Ejecutar mvn test
    echo "Ejecutando mvn test en $folder..."
    mvn test || { echo "Error al ejecutar mvn test en $folder"; exit 1; }

    # Ejecutar mvn install
    echo "Ejecutando mvn install en $folder..."
    mvn install || { echo "Error al ejecutar mvn install en $folder"; exit 1; }

    # Caso especial para la carpeta EstacionDeTrabajo
    if [[ "$folder" == "EstacionDeTrabajo" ]]; then
        echo "Entrando al directorio target de EstacionDeTrabajo..."
        cd target || { echo "No se pudo acceder al directorio target de $folder"; exit 1; }

        # Ejecutar el comando java -jar
        echo "Ejecutando jar en EstacionDeTrabajo..."
        java -jar EstacionDeTrabajo-0.0.1-SNAPSHOT-jar-with-dependencies.jar || { echo "Error al ejecutar el jar en $folder"; exit 1; }
	cd .. || { echo "n"; exit 1; }

    fi
    cd .. || { echo "n"; exit 1; }

done

# Volver a la carpeta base
echo "Procesamiento completado."

