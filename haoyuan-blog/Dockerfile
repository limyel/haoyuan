FROM hub.atomgit.com/amd64/amazoncorretto:17-alpine-jdk
ADD ./blog-startup/target/blog-startup-*.jar app.jar
ADD ~/config/blog.yml application.yml
ADD ~/config/blog-pro.yml application-pro.yml
ENTRYPOINT java -jar ${JAVA_OPTS} app.jar