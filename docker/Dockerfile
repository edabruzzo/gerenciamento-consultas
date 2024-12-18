FROM amazoncorretto:17.0.7-alpine

ARG JAR_FILE=../target/med.voll-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} /opt/java/app.jar
EXPOSE 8080

ENTRYPOINT ["java","-jar","/opt/java/app.jar"]