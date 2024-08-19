FROM hub.atomgit.com/amd64/amazoncorretto:17-alpine-jdk
ADD ./blog-startup/target/blog-startup-*.jar app.jar
ADD /tmp/blog.yml application.yml
ADD /tmp/blog-pro.yml application-pro.yml
ENTRYPOINT java -jar ${JAVA_OPTS} app.jar