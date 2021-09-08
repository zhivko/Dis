mvn package -P localhost-ubuntu -DskipTests
cp ./Dis-server/target/Dis-server-1.0-SNAPSHOT.war /home/klemen/apache-tomcat-8.5.57/webapps/Dis.war
