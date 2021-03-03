# lsof -ti tcp:9876 | xargs kill

ssh -i ~/.ssh/id_rsa zivkovick@erender-test.ts.telekom.si 'chmod a+w /app/render/DocMan/*'
ssh -i ~/.ssh/id_rsa zivkovick@erender-test.ts.telekom.si 'chmod a+w /app/render/DocMan-TEST/*'
ssh -i ~/.ssh/id_rsa zivkovick@erender-test.ts.telekom.si 'chmod a+w /app/render/DocMan-DEV/*'

##################################
mvn package -P prod -DskipTests

echo "copying profiles ..."
scp -i ~/.ssh/id_rsa -r /app/render/DocMan/* zivkovick@erender-test.ts.telekom.si:/app/render/DocMan
echo "deleting ..."
ssh -i ~/.ssh/id_rsa zivkovick@erender-test.ts.telekom.si 'rm /app/render/apache-tomcat-8.5.45/webapps/WebUi.war'
ssh -i ~/.ssh/id_rsa zivkovick@erender-test.ts.telekom.si 'rm /app/render/apache-tomcat-8.5.45/webapps/Dis.war'
echo "deleting ...Done."


echo "copying prod..."
scp -i ~/.ssh/id_rsa ./Dis-server/target/Dis-server-1.0-SNAPSHOT.war zivkovick@erender-test.ts.telekom.si:/app/render/apache-tomcat-8.5.45/webapps/Dis.war



#####################################
mvn package -P test -DskipTests -Dgwt.skipCompilation=true

echo "copying profiles ..."
scp -i ~/.ssh/id_rsa -r /app/render/DocMan/* zivkovick@erender-test.ts.telekom.si:/app/render/DocMan-TEST
echo "deleting ..."
ssh -i ~/.ssh/id_rsa zivkovick@erender-test.ts.telekom.si 'chmod a+w /app/render/DocMan-TEST/*'

scp -i ~/.ssh/id_rsa -r /app/render/DocMan/profile*.xml zivkovick@erender-test.ts.telekom.si:/app/render/DocMan-TEST
ssh -i ~/.ssh/id_rsa zivkovick@erender-test.ts.telekom.si 'rm /app/render/apache-tomcat-8.5.45/webapps/WebUi-test.war'
ssh -i ~/.ssh/id_rsa zivkovick@erender-test.ts.telekom.si 'rm /app/render/apache-tomcat-8.5.45/webapps/Dis-test.war'
echo "deleting ...Done."

echo "copying test..."
scp -i ~/.ssh/id_rsa ./Dis-server/target/Dis-server-1.0-SNAPSHOT.war zivkovick@erender-test.ts.telekom.si:/app/render/apache-tomcat-8.5.45/webapps/Dis-test.war



############################################
mvn package -P dev -DskipTests -Dgwt.skipCompilation=true

echo "copying profiles ..."
scp -i ~/.ssh/id_rsa -r /app/render/DocMan/* zivkovick@erender-test.ts.telekom.si:/app/render/DocMan-DEV
echo "deleting ..."
ssh -i ~/.ssh/id_rsa zivkovick@erender-test.ts.telekom.si 'rm /app/render/apache-tomcat-8.5.45/webapps/WebUi-dev.war'
ssh -i ~/.ssh/id_rsa zivkovick@erender-test.ts.telekom.si 'rm /app/render/apache-tomcat-8.5.45/webapps/Dis-dev.war'
echo "deleting ...Done."

echo "copying dev..."
scp -i ~/.ssh/id_rsa ./Dis-server/target/Dis-server-1.0-SNAPSHOT.war zivkovick@erender-test.ts.telekom.si:/app/render/apache-tomcat-8.5.45/webapps/Dis-dev.war

mvn package -P prod -DskipTests -Dgwt.skipCompilation=true
