FROM openjdk:17
EXPOSE 8080
Add / hack-project.jar
ENTRYPOINT ["java", "-jar", "/hack-project.jar"]