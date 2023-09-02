# Use a base image with Java 17
FROM openjdk:17-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the Spring Boot application JAR file into the container
COPY target/emailNotificationService-0.0.1-SNAPSHOT.jar  /app/emailNotificationService-0.0.1-SNAPSHOT.jar

# Expose the port your Spring Boot application will run on (adjust as needed)
EXPOSE 7820

# Command to run your Spring Boot application
CMD ["java", "-jar", "emailNotificationService-0.0.1-SNAPSHOT.jar"]
