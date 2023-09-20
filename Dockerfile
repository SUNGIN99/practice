FROM openjdk:11
ARG JAR_FILE=build/libs/practice-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar", "-Dspring.profiles.active=${active}", "-Duser.timezone=Asia/Seoul","/app.jar"]