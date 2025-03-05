FROM eclipse-temurin:23-jdk-alpine

WORKDIR /Revies

COPY ./target/*.jar app.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "app.jar"]