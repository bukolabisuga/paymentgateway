#
# Build target stage
#
FROM maven:3.6.3-jdk-8 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean install

#
# Build dockerimage stage
#
FROM openjdk:11-jre-slim
COPY --from=build /home/app/target/paymentgateway-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8888
ENTRYPOINT ["java","-jar","/app.jar"]