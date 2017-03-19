FROM frolvlad/alpine-oraclejdk8:latest

MAINTAINER hotterd@gmail.com

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "dlmonkey.jar"]

ADD build/libs/dlmonkey-*.jar dlmonkey.jar
