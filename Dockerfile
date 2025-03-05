FROM eclipse-temurin:23-jdk-alpine

WORKDIR /companies

COPY ./target/*.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]