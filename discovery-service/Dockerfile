
FROM maven:3.8.6-eclipse-temurin-17-alpine AS BUILD

ENV WORKDIR=/opt/app
WORKDIR $WORKDIR

COPY pom.xml pom.xml
COPY src src

COPY printDeps.sh printDeps.sh

RUN mvn clean install -X
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

ENV SERVICE_NAME=discovery-service
ENV WORKDIR=/opt/app
WORKDIR $WORKDIR


ENV JAVA_HOME=/jre
ENV PATH="${JAVA_HOME}/bin:${PATH}"

COPY --from=BUILD $WORKDIR/customjre $JAVA_HOME
COPY --from=BUILD $WORKDIR/target/${SERVICE_NAME}-1.0.0-SNAPSHOT.jar /app.jar

CMD ["java", "-jar", "-DenvTarget=dev", "/app.jar"]

EXPOSE 8761

