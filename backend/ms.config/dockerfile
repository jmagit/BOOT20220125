# mvnw clean package
FROM openjdk:17
COPY target/ms.config-0.0.1-SNAPSHOT.jar /usr/app.jar
EXPOSE 8888
ENTRYPOINT ["java","-jar","/usr/app.jar"]
# docker build -t ms-config-server .
# docker run -d --name ms-config-server -p 8888:8888 ms-config-server