# BUILD
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

# Maven wrapper
COPY mvnw .
COPY .mvn .mvn
RUN chmod +x mvnw

# Fonte
COPY pom.xml .
COPY src src

# Build
RUN ./mvnw -q -DskipTests package

# RUNTIME
FROM eclipse-temurin:21-jre
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 3000

ENTRYPOINT ["java","-jar","app.jar"]
