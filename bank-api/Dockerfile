FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8443
ADD target/bank-api-0.0.1-SNAPSHOT.jar bank-api.jar
ENTRYPOINT ["java","-jar","bank-api.jar"]