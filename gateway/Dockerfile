FROM amazoncorretto:11-alpine-jdk
COPY target/*.jar app.jar
#COPY --chown=node:node ./package*.json ./
ENTRYPOINT ["java","-jar","/app.jar"]