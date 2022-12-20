#!/bin/bash
jdeps --print-module-deps \
 --ignore-missing-deps \
 --multi-release 17  \
 -recursive \
 --class-path="app-post-service/target/deps/*"  \
  "app-post-service/target/app-post-service-0.0.1-SNAPSHOT.jar"\
  > deps.txt
