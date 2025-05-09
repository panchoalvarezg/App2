# **TICS200: App #2 – Gestión Agrícola en Java (POO)**

La aplicacion se ejecuta con los siguientes codigos:

Entra a la carpeta del proyecto App2_, donde está el código fuente y el archivo pom.xml.
$ cd ~/Documents/GitHub/App2_

Limpia los archivos compilados anteriores (clean) y luego compila el proyecto Java con Maven.
$ mvn clean compile

Limpia y compila el proyecto, y además genera un archivo .jar listo para ejecutar (dentro de target/).
$ mvn clean package

Ejecuta la aplicación Java empaquetada, usando como argumento el archivo CSV para cargar y guardar datos.
$ java -jar target/app2-1.0-SNAPSHOT.jar src/main/resources/cultivos.csv

**Integrantes**
Francisco Ignacio Alvarez Gajardo
franciscoialvarez@alumnos.uai.cl
