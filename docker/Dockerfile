FROM tomcat:9-jdk8-openjdk-buster
RUN rm -rf /usr/local/tomcat/webapps/*
WORKDIR /usr/local/tomcat/webapps/
COPY ./target/ElectionCommission.jar /usr/local/tomcat/webapps/ElectionCommission.jar
RUN sh -c 'touch /usr/local/tomcat/webapps/ElectionCommission.jar'
ENTRYPOINT ["sh", "-c", "java -Djava.security.egd=file:/dev/./urandom -jar /usr/local/tomcat/webapps/ElectionCommission.jar"]
EXPOSE 80