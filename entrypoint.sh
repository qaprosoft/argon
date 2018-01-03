#!/bin/bash

cd $CATALINA_HOME/temp

# Place WAR to webapps
cp argon-ws.war $CATALINA_HOME/webapps/argon-ws.war

# Clear temp
rm -rf argon*

# Run Tomcat
echo argon.url=$ARGON_URL >> $CATALINA_HOME/conf/catalina.properties
echo argon.db.jdbc.url=$ARGON_JDBC_URL >> $CATALINA_HOME/conf/catalina.properties
echo argon.db.jdbc.user=$ARGON_JDBC_USER >> $CATALINA_HOME/conf/catalina.properties
echo argon.db.jdbc.password=$ARGON_JDBC_PASS >> $CATALINA_HOME/conf/catalina.properties
echo argon.smtp.user=$ARGON_SMTP_USER >> $CATALINA_HOME/conf/catalina.properties
echo argon.smtp.pass=$ARGON_SMTP_PASS >> $CATALINA_HOME/conf/catalina.properties

catalina.sh run
