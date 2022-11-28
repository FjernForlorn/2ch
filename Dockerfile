FROM openjdk:17-alpine

COPY /2ch-web-app/target/2ch-web-app-0.0.1-SNAPSHOT.jar /app.jar

CMD ["java", "-jar", "/app.jar"]


