#!/bin/bash


docker-compose -d up
mvn clean package

rm -r logs
mkdir logs

nohup java -jar eureka-server/target/server-0.0.1-SNAPSHOT.jar > logs/eureka-server.log &
nohup java -jar eureka-gateway/target/eureka-gateway-0.0.1-SNAPSHOT.jar > logs/eureka-gateway.log &
nohup java -jar authorization/target/authorization-0.0.1-SNAPSHOT.jar > logs/authorization.log &
nohup java -jar trips/target/trips-0.0.1-SNAPSHOT.jar > logs/trips.log &
nohup java -jar reports/target/reports-0.0.1-SNAPSHOT.jar > logs/reports.log &
nohup java -jar email/target/email-0.0.1-SNAPSHOT.jar > logs/email.log &
