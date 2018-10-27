FROM openjdk:8 AS build-env

ADD . /src
WORKDIR /src
RUN /src/gradlew build -x test

FROM frolvlad/alpine-oraclejdk8:latest

MAINTAINER hotterd@gmail.com

COPY --from=build-env /src/build/libs/dlmonkey-*.jar dlmonkey.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "dlmonkey.jar"]