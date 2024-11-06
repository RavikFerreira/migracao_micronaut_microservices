FROM openjdk:17-alpine
COPY target/table-services-0.1.jar app.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "app.jar"]