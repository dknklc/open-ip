FROM openjdk:17-jdk-slim AS build

COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN ./mvnw dependency:resolve

COPY src src
RUN ./mvnw package

FROM openjdk:17-jdk-slim
WORKDIR open-ip
COPY --from=build target/*.jar open-ip.jar
ENTRYPOINT ["java", "-jar", "open-ip.jar"]