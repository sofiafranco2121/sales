FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/sales-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} sales.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "sales.jar"]