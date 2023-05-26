FROM maven:3.8.5-openjdk-11-slim AS build

COPY /src /app/src

COPY /pom.xml /app

RUN mvn -f /app/pom.xml clean package -Dmaven.test.skip

FROM openjdk:11

LABEL key="core.ics.mstravelai"

WORKDIR /usr/src/app

COPY --from=build /app/target/*.jar ms-travel-ai.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "ms-travel-ai.jar"]