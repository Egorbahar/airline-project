FROM maven:3.6.0-jdk-11-slim AS build
ADD ./pom.xml pom.xml
ADD ./src src/
RUN mvn clean package

FROM adoptopenjdk/openjdk11:alpine-jre
ARG JAR_FILE=/target/airline-service-0.0.1.jar
COPY --from=build ${JAR_FILE} airline-service.jar
ENTRYPOINT ["java","-jar","/airline-service.jar"]