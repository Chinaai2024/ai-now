FROM registry-vpc.us-west-1.aliyuncs.com/acegear/jdk:21
RUN mkdir /app && echo 'Asia/Shanghai' >/etc/timezone
ENV TZ='Asia/Shanghai'
WORKDIR /app
ARG STABILITYAIKEY
ARG OPENAIKEY
ENV StabilityAIKEY=$STABILITYAIKEY
ENV OpenAIKey=$OPENAIKEY
CMD java -jar ${JAVA_OPTS} app.jar
COPY target/image-rest.jar app.jar
