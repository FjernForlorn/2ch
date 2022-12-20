
FROM maven:3.8.6-eclipse-temurin-17-alpine AS BUILD

ENV WORKDIR=/opt/app
ENV SERVICE_NAME=post-service
WORKDIR $WORKDIR

COPY pom.xml pom.xml

COPY app-${SERVICE_NAME}/pom.xml app-${SERVICE_NAME}/pom.xml
COPY app-${SERVICE_NAME}/src app-${SERVICE_NAME}/src

COPY client-${SERVICE_NAME}/pom.xml client-${SERVICE_NAME}/pom.xml
COPY client-${SERVICE_NAME}/src client-${SERVICE_NAME}/src

COPY common-${SERVICE_NAME}/pom.xml common-${SERVICE_NAME}/pom.xml
COPY common-${SERVICE_NAME}/src common-${SERVICE_NAME}/src

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

ENV SERVICE_NAME=post-service
ENV WORKDIR=/opt/app
WORKDIR $WORKDIR


ENV JAVA_HOME=/jre
ENV PATH="${JAVA_HOME}/bin:${PATH}"

COPY --from=BUILD $WORKDIR/customjre $JAVA_HOME
COPY --from=BUILD $WORKDIR/app-${SERVICE_NAME}/target/app-${SERVICE_NAME}-0.0.1-SNAPSHOT.jar /app.jar

CMD ["java", "-jar", "-DenvTarget=dev", "/app.jar"]

EXPOSE 8082

