# Start with a base image containing Java runtime
FROM java:8

# Make port 8081 available to the world outside this container
EXPOSE 8080

ADD target/sms-ui.jar sms-ui.jar

# Run the jar file
ENTRYPOINT ["java","-jar","sms-ui.jar"]