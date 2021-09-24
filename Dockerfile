FROM openjdk:8u111-jdk-alpine

ENV PATH=$PATH:/opt/maven/bin MAVEN_HOME=/opt/apache-maven-3.8.2

RUN apk add --no-cache curl tar bash && \
  curl -SsL -o /tmp/maven.tar.gz https://apache.si/maven/maven-3/3.8.2/binaries/apache-maven-3.8.2-bin.tar.gz && \
  mkdir -p /opt && \
  tar xzf /tmp/maven.tar.gz -C /opt/ && \
  ln -s /opt/apache-maven-3.3.9 /opt/maven && \
  rm /tmp/maven.tar.gz


EXPOSE 389
EXPOSE 8080

ENTRYPOINT "/usr/local/bin/mvn-entrypoint.sh"
