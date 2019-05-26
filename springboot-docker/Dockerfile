FROM frolvlad/alpine-oraclejdk8:slim

MAINTAINER 20207075@qq.com

VOLUME /tmp

ADD ./target/springboot-docker-0.0.1-SNAPSHOT.jar springboot-docker.jar

RUN sh -c 'touch /springboot-docker.jar'

ENV JAVA_OPTS=""

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /springboot-docker.jar" ]