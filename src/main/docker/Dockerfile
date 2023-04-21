FROM openjdk:8
VOLUME /tmp
ADD KaoYanIEP-0.0.1-SNAPSHOT.jar /app.jar
EXPOSE 8080/tcp
ENTRYPOINT java -Djava.security.egd=file:/dev/./urandom -jar /app.jar