FROM tomcat:7-jre8

ENV ARGON_URL=http://localhost:8080
ENV ARGON_JDBC_URL=jdbc:mysql://localhost:3306/argon
ENV ARGON_JDBC_USER=argon
ENV ARGON_JDBC_PASS=argon
ENV ARGON_SMTP_USER=test@qaprosoft.com
ENV ARGON_SMTP_PASS=qwerty

RUN apt-get update && apt-get install zip

COPY sources/argon-ws/target/argon-ws.war ${CATALINA_HOME}/temp/argon-ws.war
COPY entrypoint.sh /

EXPOSE 8080

ENTRYPOINT /entrypoint.sh
