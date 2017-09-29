FROM alpine:edge AS build
RUN apk add --no-cache openjdk8 maven
WORKDIR /opt/outfitr_build/
ADD pom.xml .
ADD src/ src/
RUN mvn clean install
RUN apk del maven
WORKDIR /opt/outfitr/
RUN mv /opt/outfitr_build/target/outfitr-*.jar ./outfitr.jar
RUN rm -rf /opt/outfitr_builb
ENV JAVA_OPTS -server -Xms512m -Xmx512m -XX:+HeapDumpOnOutOfMemoryError

CMD exec /usr/bin/java $JAVA_OPTS -jar ./outfitr.jar
