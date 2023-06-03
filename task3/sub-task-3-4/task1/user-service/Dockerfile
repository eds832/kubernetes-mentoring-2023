FROM openjdk:11
ARG JAR_FILE=target/user-service-1.0-SNAPSHOT.jar
COPY ${JAR_FILE} /user.jar
ENTRYPOINT ["java", "-jar", "user.jar"]