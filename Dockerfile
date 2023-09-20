FROM openjdk:11
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
COPY src/main/resources/templates/ /app/templates/
ENTRYPOINT ["java","-jar", "-Dspring.profiles.active=${active}", "-Duser.timezone=Asia/Seoul","/app.jar"]