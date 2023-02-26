#
# Build stage
#
FROM maven:3.8.5-openjdk-18 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Unit Tests
#
WORKDIR "/home/app"
RUN ls -la
RUN mvn test

#
# Package stage
#
FROM openjdk:18-alpine
COPY --from=build /home/app/target/demo-0.0.1-SNAPSHOT.jar /usr/local/lib/demo.jar
EXPOSE 5000
ENTRYPOINT ["java","-jar","/usr/local/lib/demo.jar"]