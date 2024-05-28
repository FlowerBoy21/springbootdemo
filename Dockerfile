# Use the Maven image to build the project
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Use the OpenJDK image to run the application
FROM openjdk:17.0.1-jdk-slim
WORKDIR /app
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar demo.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","demo.jar"]
