FROM openjdk:8-jdk-alpine
EXPOSE 18089
ARG WAR_FILE=target/cpyd.war
ADD ${WAR_FILE} cpyd.war
ENTRYPOINT ["java","-jar","/cpyd.war"]
