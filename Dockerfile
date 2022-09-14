FROM adoptopenjdk/openjdk11:alpine-jre

# Refer to Maven build -> finalName
ARG JAR_FILE=target/demo-0.0.1-SNAPSHOT.jar

# cd /opt/app
WORKDIR /mecha/demo

# cp target/spring-boot-web.jar /opt/app/app.jar
COPY ${JAR_FILE} github-auth.jar

# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","github-auth.jar"]