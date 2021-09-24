FROM maven:3-openjdk-8-slim


EXPOSE 389
EXPOSE 8080

ENTRYPOINT "/usr/local/bin/mvn-entrypoint.sh"
