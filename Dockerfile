#FROM openjdk:11
#ARG JAR_FILE=build/libs/*.jar
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar", "-Dspring.profiles.active=${active}", "-Duser.timezone=Asia/Seoul", "/app.jar"]

# Multi-Stage
# Build Stage
FROM gradle:6.9.2-jdk11 AS BUILD

ENV APP_HOME=/app
WORKDIR $APP_HOME

COPY gradle $APP_HOME/gradle
COPY build.gradle settings.gradle gradlew $APP_HOME/
COPY src $APP_HOME/src

USER root
RUN chmod +x gradlew

RUN ./gradlew clean build

# Final Stage
FROM openjdk:11-jre-slim
ENV APP_HOME=/app
ENV JAR_FILE=/app/build/libs/*.jar

WORKDIR $APP_HOME

COPY --from=BUILD ${JAR_FILE} /app.jar

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=${active}", "-Duser.timezone=Asia/Seoul", "/app.jar"]