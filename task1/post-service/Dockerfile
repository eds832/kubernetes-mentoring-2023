FROM openjdk:11
EXPOSE 8082
ARG JAR_FILE=target/post-service-1.0-SNAPSHOT.jar
COPY ${JAR_FILE} /post.jar
ENTRYPOINT ["java", "-jar", "post.jar"]