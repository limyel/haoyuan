FROM atomhub.openatom.cn/amd64/openjdk:21-jdk-slim
ADD ./blog-startup/target/blog-startup-*.jar app.jar
ENTRYPOINT java -jar ${JAVA_OPTS} app.jar