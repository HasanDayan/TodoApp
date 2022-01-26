FROM openjdk:8
WORKDIR /todoapp
ADD target/todoapp-1.0.jar todoapp-1.0.jar
ENTRYPOINT ["java", "-jar", "todoapp-1.0.jar"]