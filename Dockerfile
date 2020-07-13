FROM openjdk:14
FROM maven:3.6.3-jdk-14

# image layer
WORKDIR /app
# Image layer: with the application
COPY . /app
RUN mvn clean install
RUN mvn clean package

CMD ["java", "-jar", "/app/target/Demo-0.0.1-SNAPSHOT.jar"]

EXPOSE 8080
