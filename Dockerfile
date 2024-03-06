FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} cartApi-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","/cartApi-0.0.1-SNAPSHOT.jar"]