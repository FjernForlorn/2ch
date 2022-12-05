FROM maven:3.8.6-eclipse-temurin-17-alpine AS MAVEN_BUILD

COPY ./ ./

RUN mvn clean package

FROM openjdk:17-alpine

COPY --from=MAVEN_BUILD /skipper-web-app/target/skipper-web-app-0.0.1-SNAPSHOT.jar /app.jar

CMD ["java", "-jar", "-DenvTarget=dev", "/app.jar"]

EXPOSE 8082

