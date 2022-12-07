
FROM maven:3.8.6-eclipse-temurin-17-alpine AS BUILD

ENV WORKDIR=/opt/app
WORKDIR $WORKDIR

COPY pom.xml pom.xml

COPY app-user-service/pom.xml app-user-service/pom.xml
COPY app-user-service/src app-user-service/src

COPY client-user-service/pom.xml client-user-service/pom.xml
COPY client-user-service/src client-user-service/src

COPY common-user-service/pom.xml common-user-service/pom.xml
COPY common-user-service/src common-user-service/src

COPY printDeps.sh printDeps.sh

RUN mvn clean package
RUN ./printDeps.sh

RUN apk add --no-cache binutils
RUN $JAVA_HOME/bin/jlink \
    --verbose \
    --add-modules @${WORKDIR}/deps.txt \
    --strip-debug \
    --no-man-pages \
    --no-header-files \
    --compress=2 \
    --output ${WORKDIR}/customjre


FROM alpine:latest

ENV WORKDIR=/opt/app
WORKDIR $WORKDIR


ENV JAVA_HOME=/jre
ENV PATH="${JAVA_HOME}/bin:${PATH}"

COPY --from=BUILD $WORKDIR/customjre $JAVA_HOME
COPY --from=BUILD $WORKDIR/app-user-service/target/app-user-service-0.0.1-SNAPSHOT.jar /app.jar

CMD ["java", "-jar", "-DenvTarget=dev", "/app.jar"]

EXPOSE 8082

