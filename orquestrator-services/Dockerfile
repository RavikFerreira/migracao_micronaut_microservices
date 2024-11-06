FROM openjdk:17-alpine
COPY target/orquestrator-services-0.1.jar app.jar
EXPOSE 4000
ENTRYPOINT ["java", "-jar", "app.jar"]