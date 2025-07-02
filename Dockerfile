#
# Build del proyecto (Multi-Stage)
# --------------------------------
#
# Se usa una IMAGEN de Maven para hacer build de proyecto con Java
# A este sub-entorno de se denomina «build»
# Se COPIA todo el contenido del repositorio
# Se ejecuta el comando mvn clean package (Generara un archivo JAR para el despliegue)
FROM maven:3.9.6-eclipse-temurin-21 AS build
COPY . .
RUN mvn clean package

# Se usa una imagen de Openjdk
# Se expone el puerto que nuestro componente va a usar para escuchar peticiones
# Se copia desde «build» el JAR generado (la ruta de generación es la misma que veríamos en local) y lo movemos y renombramos en destino como 
# marcamos el punto de arranque de la imagen con el comando «java -jar app.jar» que ejecutará nuestro componente.
FROM openjdk:21
EXPOSE 8820
COPY --from=build /target/payments-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]