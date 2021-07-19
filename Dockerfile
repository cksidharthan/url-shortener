FROM openjdk:8-jre-alpine3.9

COPY shortener-api/target/shortener-api-0.0.1-SNAPSHOT.jar /shortener.jar

CMD ["java", "-jar", "/shortener.jar"]