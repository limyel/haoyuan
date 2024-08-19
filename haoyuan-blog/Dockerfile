FROM hub.atomgit.com/amd64/amazoncorretto:17-alpine-jdk
ADD ./blog-startup/target/blog-startup-*.jar app.jar
ADD /home/ubuntu/config/blog.yml application.yml
ADD /home/ubuntu/config/blog-pro.yml application-pro.yml
ENTRYPOINT java -jar ${JAVA_OPTS} app.jar