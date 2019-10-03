## Start with a base image containing Java runtime
#FROM java:8
#
## Make port 8081 available to the world outside this container
#EXPOSE 8081
#
#ADD target/springdata-jpa-example.jar springdata-jpa-example.jar
#
## Run the jar file
#ENTRYPOINT ["java","-jar","springdata-jpa-example.jar"]
#
FROM openjdk:8
ADD target/springdata-jpa.jar springdata-jpa.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","springdata-jpa.jar"]