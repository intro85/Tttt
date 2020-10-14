FROM maven:3.6.3-jdk-8-slim

COPY ./target/Proxify-1.0-SNAPSHOT-jar-with-dependencies.jar /usr/app/
WORKDIR /usr/app
ENTRYPOINT ["java", "-jar", "Proxify-1.0-SNAPSHOT-jar-with-dependencies.jar"]
