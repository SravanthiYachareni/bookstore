FROM openjdk:17-jdk
EXPOSE 8080
COPY target/bookstore.jar .
ENTRYPOINT ["java","-jar","bookstore.jar"]
