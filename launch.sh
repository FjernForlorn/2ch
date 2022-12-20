#!/bin/bash


SERVICE_NAME=discovery-service
echo $SERVICE_NAME: BUILDING
cd $SERVICE_NAME
mvn clean install
java -jar target/$SERVICE_NAME-1.0.0-SNAPSHOT.jar &
cd ..

SERVICE_NAME=gateway
echo $SERVICE_NAME: BUILDING
cd $SERVICE_NAME
mvn clean install
java -jar target/$SERVICE_NAME-1.0.0-SNAPSHOT.jar &
cd ..

SERVICE_NAME=user-service
echo $SERVICE_NAME: BUILDING
cd $SERVICE_NAME
mvn clean install
java -jar app-$SERVICE_NAME/target/app-$SERVICE_NAME-1.0.0-SNAPSHOT.jar &
cd ..

SERVICE_NAME=post-service
echo $SERVICE_NAME: BUILDING
cd $SERVICE_NAME
mvn clean install
java -jar app-$SERVICE_NAME/target/app-$SERVICE_NAME-1.0.0-SNAPSHOT.jar &
cd ..




