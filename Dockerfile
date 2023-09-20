FROM openjdk:11
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
COPY src/main/resources/templates/index.mustache resources/templates/index.mustache
ENTRYPOINT ["java","-jar", "-Dspring.profiles.active=${active}", "-Duser.timezone=Asia/Seoul","/app.jar"]