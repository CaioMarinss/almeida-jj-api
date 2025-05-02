# Fase de execução
FROM maven:3.9.6-eclipse-temurin-17 AS build

COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim

COPY --from=build /target/almeidaPresenca-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

# Rodar o script wait-for-it.sh para aguardar o banco de dados e depois iniciar a aplicação
ENTRYPOINT [ "java", "-jar", "app.jar"]
