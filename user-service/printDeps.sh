#!/bin/bash
jdeps --print-module-deps \
 --ignore-missing-deps \
 --multi-release 17  \
 -recursive \
 --class-path="app-user-service/target/deps/*"  \
  "app-user-service/target/app-user-service-1.0.0-SNAPSHOT.jar"\
  > deps.txt
