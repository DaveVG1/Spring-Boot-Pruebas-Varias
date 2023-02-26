#
# Build stage
#
FROM maven:3.8.5-openjdk-18 AS build
#COPY src /home/app/src
#COPY pom.xml /home/app
RUN mvn -f pom.xml clean package

#
# Package stage
#
FROM openjdk:18-alpine
#COPY --from=build /home/app/target/demo-0.0.1-SNAPSHOT.jar /usr/local/lib/demo.>
EXPOSE 5000
ENTRYPOINT ["java","-jar","./target/demo-0.0.1-SNAPSHOT.jar"]