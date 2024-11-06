FROM openjdk:17-alpine
COPY target/payment-services-0.1.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]