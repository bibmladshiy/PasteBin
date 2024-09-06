FROM eclipse-temurin:22
ARG JAR_FILE=target/*.jar
COPY ./target/pastebin-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app.jar"]
