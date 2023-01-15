FROM openjdk:17
EXPOSE 8080
Add target/hack-project.jar hack-project.jar
ENTRYPOINT ["java", "-jar", "/hack-project.jar"]