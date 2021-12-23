# syntax=docker/dockerfile:1

FROM gradle:7.3.3-jdk17-alpine as base

WORKDIR /app

COPY gradle gradle
COPY gradlew build.gradle ./
COPY src ./src
RUN ./gradlew build

FROM base as test
RUN ./gradlew clean test --info

FROM base as development

EXPOSE 8080

CMD ["./gradlew", "bootRun", "-Dspring-boot.run.jvmArguments='-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8000'"]
