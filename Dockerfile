FROM eclipse-temurin:23-jdk-alpine

WORKDIR /jobs

COPY ./target/*.jar app.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "app.jar"]