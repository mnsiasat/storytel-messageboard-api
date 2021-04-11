#
# Build stage
#
FROM maven:3.6.3-openjdk-15-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean install spring-boot:repackage

#
# Package stage
#
FROM adoptopenjdk:15-jre-hotspot
COPY --from=build /home/app/target/messageboard-api-1.0.0-SNAPSHOT.jar /usr/src/app/target/messageboard-api.jar
CMD ["sh", "-c", "java --enable-preview -jar /usr/src/app/target/messageboard-api.jar"]