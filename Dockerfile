FROM openjdk:11
MAINTAINER tunx
COPY target/port-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]