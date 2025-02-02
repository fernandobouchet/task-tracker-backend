# Etapa de construcción con Maven y OpenJDK 21
FROM maven:3.9.8-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa de ejecución con OpenJDK 21
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=build /app/target/tasks-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "app.jar"]
