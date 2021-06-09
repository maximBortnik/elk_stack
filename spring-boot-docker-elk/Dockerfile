# For Java 8
# FROM openjdk:8-jdk-alpine

# For Java 11
FROM openjdk:11-jre

### install app
RUN mkdir /var/lib/spring-boot-app
WORKDIR /var/lib/spring-boot-app
ARG JAR_FILE=./build/libs/spring-boot-docker-elk.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080

### install Filebeat
ENV FILEBEAT_VERSION 7.13.1
ENV FILEBEAT_BASE_VERSION 7.13.1

RUN apt update && apt install curl

RUN curl -L -O https://artifacts.elastic.co/downloads/beats/filebeat/filebeat-${FILEBEAT_VERSION}-amd64.deb \
 && dpkg -i filebeat-${FILEBEAT_VERSION}-amd64.deb \
 && rm filebeat-${FILEBEAT_VERSION}-amd64.deb

### config Filebeat
COPY ./elk/filebeat.yml /etc/filebeat/filebeat.yml
RUN chmod 644 /etc/filebeat/filebeat.yml

### run app
RUN echo -e '#!/bin/sh \n service filebeat start \n java -jar -Dspring.profiles.active=docker app.jar' > run.sh
RUN chmod +x run.sh
CMD ./run.sh
