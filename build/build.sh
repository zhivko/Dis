ln -s /src/main/filters/test.properties /Dis-shared/src/main/filters/test.properties
ln -s /src/main/filters/dev.properties /Dis-shared/src/main/filters/dev.properties
ln -s /src/main/filters/prod.properties /Dis-shared/src/main/filters/prod.properties

ln -s /src/main/filters/test.properties /Dis-client/src/main/filters/test.properties
ln -s /src/main/filters/dev.properties /Dis-client/src/main/filters/dev.properties
ln -s /src/main/filters/prod.properties /Dis-client/src/main/filters/prod.properties

ln -s /src/main/filters/test.properties /Dis-server/src/main/filters/test.properties
ln -s /src/main/filters/dev.properties /Dis-server/src/main/filters/dev.properties
ln -s /src/main/filters/prod.properties /Dis-server/src/main/filters/prod.properties

mvn clean package -P test -Dgwt.skipCompilation=true

