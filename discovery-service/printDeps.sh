#!/bin/bash
jdeps --print-module-deps \
 --ignore-missing-deps \
 --multi-release 17  \
 -recursive \
 --class-path="target/deps/*"  \
  "target/discovery-service-1.0.0-SNAPSHOT.jar"\
  > deps.txt
