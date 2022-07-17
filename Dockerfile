FROM openjdk:17-alpine
WORKDIR /app
COPY ./ build/libs/gatitoLiniersBot-0.0.1-SNAPSHOT.jar /app
ENTRYPOINT ["java","-jar", "gatitoLiniersBot-0.0.1-SNAPSHOT.jar"]