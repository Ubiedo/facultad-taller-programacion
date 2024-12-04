VOLANDO.UY

 Plataforma de ofertas de vuelos a través de Internet.

Servidor Web:
 - Web:
    - para agruegar el header y footer a un html, asi no lo tenemos que copiar y pegar en cada archivo, es solo agregar el link a header.js y footer.js, y luego en el body poner las etiquetas:
        - < header-component ></ header-component >
        - < footer-component ></ footer-component >
        
 Estacion de trabajo:
 - Menu Bar:
    - Sistema:
        - Comprar paquetes
        - Reservar vuelos
        - Salir (cierra app)
    - Crear:
        - Nuev@ ______ (los casos de uso alta de ..., nuevo paquete, los que creen contenido)
    - Editar:
        - Editar _____ (los de modificar archivos)
        - Agregar a __ (los que sean agregar cosas a instancias)
    - Ver:
        - consultar __ (los que sean consultar o que pidan informacion)
        - ...
    - ...

    Bibliotecas externas que se utilizan: 
    - OpenCSV versión 5.9 (descargar archivo JAR en: (https://mvnrepository.com/artifact/com.opencsv/opencsv/5.9))
    - Apache Commons Langs versión 3.17.0 (descargar archivo JAR en: https://mvnrepository.com/artifact/org.apache.commons/commons-lang3/3.17.0) 
    
    Agregar los archivos JAR: 
    - click derecho en el proyecto > "Build Path" > Libraries > Classpath > Add JARs (si están guardados en una carpeta dentro del proyecto) o Add External JARs (en caso contrario) y navegar hasta el archivo