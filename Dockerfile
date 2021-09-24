FROM openjdk:8u111-jdk-alpine

ENV PATH=$PATH:/opt/maven/bin:/opt/openshift MAVEN_HOME=/opt/apache-maven-3.8.2

RUN apk add --no-cache curl tar bash && \
  curl -SsL -o /tmp/maven.tar.gz https://apache.si/maven/maven-3/3.8.2/binaries/apache-maven-3.8.2-bin.tar.gz && \
  mkdir -p /opt && \
  tar xzf /tmp/maven.tar.gz -C /opt/ && \
  ln -s /opt/apache-maven-3.3.9 /opt/maven && \
  rm /tmp/maven.tar.gz

# openshift command line tools:
RUN curl -SsL -o /tmp/openshift-install-linux-4.7.0-0.okd-2021-09-19-013247.tar.gz https://github.com/openshift/okd/releases/download/4.7.0-0.okd-2021-09-19-013247/openshift-install-linux-4.7.0-0.okd-2021-09-19-013247.tar.gz && \
    tar xzf /tmp/openshift-install-linux-4.7.0-0.okd-2021-09-19-013247.tar.gz -C /opt/ && \
    ln -s /opt/openshift-install-linux-4.7.0-0.okd-2021-09-19-013247 /opt/openshift && \
    rm /tmp/openshift-install-linux-4.7.0-0.okd-2021-09-19-013247.tar.gz

EXPOSE 389
