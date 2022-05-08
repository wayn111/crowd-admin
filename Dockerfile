FROM docker.io/tomcat:9.0.60-jdk8-openjdk
MAINTAINER waynaqua
COPY crowd-web/target/crowd.war   /usr/local/tomcat/webapps
CMD ["catalina.sh", "run"]
