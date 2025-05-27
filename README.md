# Volando.uy - Taller programación – Facultad de Ingeniería, UDELAR

Este repositorio contiene la web y aplicacion de escritorio pedida para la materia **Taller de programación** de la Facultad de Ingeniería de la Universidad de la República (UDELAR).

**Tutor:** Martin Rubio

## 📂 Contenido

Este repositorio contiene la aplicacion pedida para la tarea, organizadas en dispositivo movil, servidor web, estacion de trabajo:

- **Dispositivo movil**: Web de acceso especifico para dispositivos moviles, donde usuarios pueden consultar sobre sus vuelos:
  - Aplicacion Web.
  - Servlets.
  - XML.
- **Servidor web 2**: Plataforma de ofertas de vuelos a través de internet:
  - Aplicacion Web.
  - Servlets.
  - XML.
- **Estacion de trabajo**: Aplicacion de escritorio para gestion de sistema:
  - Publicar e interactuar con los objetos del sistema.
  - Publicar el servidor web.
  - Logica de negocio.
  - Tests de la logica de negocio.

> El código está desarrollado principalmente en **Java**, con algunos módulos webs en **CSS** y **JavaScript**.  

## 💻 Tecnologías utilizadas

- Lenguajes: Java, CSS, JavaScript
- Frameworks y herramientas: Jakarta EE, Apache Tomcat, JUnit, Bootstrap
- Entorno de desarrollo: Eclipse IDE
- Sistema operativo: multiplataforma (desarrollado en Linux, compatible con Windows y MacOS)

## 🐧 Requisitos

Para compilar y ejecutar este proyecto, se recomienda tener instalado:
- Java JDK 17 (o compatible)
- Eclipse IDE for Enterprise Java and Web Developers
- Apache Tomcat (versión 9 o superior)
- Las bibliotecas necesarias configuradas en el classpath

### 📚 Bibliotecas externas utilizadas

- **OpenCSV** versión 5.9
- [Descargar JAR](https://mvnrepository.com/artifact/com.opencsv/opencsv/5.9)

- **Apache Commons Lang** versión 3.17.0
- [Descargar JAR](https://mvnrepository.com/artifact/org.apache.commons/commons-lang3/3.17.0)

### 🔧 Agregar los archivos JAR en Eclipse

1. Hacer clic derecho sobre el proyecto → **Build Path** → **Configure Build Path**
2. Ir a la pestaña **Libraries**
3. Elegir una de estas opciones:
   - **Add JARs** (si los archivos están dentro del proyecto)
   - **Add External JARs** (si están fuera del proyecto)
4. Navegar hasta el archivo `.jar` correspondiente y agregarlo.

## ▶️ Cómo compilar y ejecutar

1. Clonar o descargar el repositorio.
2. Abrir el proyecto en Eclipse como un proyecto Java o Dynamic Web Project.
3. Configurar el servidor Tomcat desde Eclipse.
4. Ejecutar la aplicación desde el IDE o desplegar en Tomcat.

## 👤 Autores

- [Federico Javier González Ubiedo](https://github.com/Ubiedo)
- Juan Pablo Otero
- Joaquin Javier Vila Pacheco
- Valentina Beron
- Manuel Martinez
