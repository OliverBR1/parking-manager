# ============================
# BUILD STAGE
# ============================
FROM eclipse-temurin:21-jdk AS build

WORKDIR /app

# Copia o Maven wrapper + config
COPY mvnw .
COPY .mvn .mvn

# Dá permissão ao Maven wrapper
RUN chmod +x mvnw

# Copia o código fonte
COPY pom.xml .
COPY src src

# Compila o projeto
RUN ./mvnw -q -DskipTests package

# ============================
# RUNTIME STAGE
# ============================
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copia o JAR gerado no stage anterior
COPY --from=build /app/target/*.jar app.jar

EXPOSE 3003

# Ativa automaticamente o profile "docker" quando rodar no Docker
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.profiles.active=docker"]
