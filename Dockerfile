# ---- Stage 1: Build ----
FROM maven:3.9-eclipse-temurin-24-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# ---- Stage 2: Run ----
FROM eclipse-temurin:24-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

RUN adduser -D spring
USER spring

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
