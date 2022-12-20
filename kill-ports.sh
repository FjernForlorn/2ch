#!/bin/bash
for i in 8081 8082 8083 8761
do
  npx kill-port $i
done