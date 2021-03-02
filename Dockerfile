FROM openjdk:8
ADD target/blog-application.jar blog-application.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "blog-application.jar"]