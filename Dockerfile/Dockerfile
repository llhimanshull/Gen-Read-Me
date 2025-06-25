# Start with an OpenJDK base image
FROM eclipse-temurin:17-jdk

# Set the working directory
WORKDIR /app

# Copy everything and build the app
COPY ../src/main/java .

# Build the project using Maven Wrapper
RUN ./mvnw clean package -DskipTests

# Expose the port Spring Boot runs on
EXPOSE 8080

# Run the JAR
CMD ["java", "-jar", "target/*.jar"]

