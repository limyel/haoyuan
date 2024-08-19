FROM hub.atomgit.com/amd64/amazoncorretto:17-alpine-jdk
ADD ./blog-startup/target/blog-startup-*.jar app.jar
ENTRYPOINT java -jar ${JAVA_OPTS} app.jar