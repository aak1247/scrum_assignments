FROM openjdk:11-jdk-alpine
MAINTAINER aak1247
RUN mvn install
ADD ./target/todolist.jar /home/
WORKDIR /home/
EXPOSE 80
CMD ["java","-jar","todolist.jar"]