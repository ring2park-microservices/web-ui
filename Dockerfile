FROM openjdk:8-jre-alpine

ARG imageVersion=1.0
ARG javaOpts=
ARG eurekaHost=localhost
ARG eurekaPort=1111
ARG serverPort=8088

ENV JAVA_OPTS=$javaOpts
ENV EUREKA_HOST=$eurekaHost
ENV EUREKA_PORT=$eurekaPort
ENV SERVER_PORT=$serverPort

ADD target/web-ui-${imageVersion}.jar app.jar

RUN sh -c 'touch /app.jar'

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar --eurekaHost=$EUREKA_HOST --eurekaPort=$EUREKA_PORT --serverPort=$SERVER_PORT" ]
