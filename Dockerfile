FROM graalvm-bullseye-maven as builder
RUN mkdir /app && echo 'Asia/Shanghai' >/etc/timezone
ENV TZ='Asia/Shanghai'
WORKDIR /app
COPY . .
RUN mvn -T 2 clean package -Dmaven.test.skip=true -pl ai.now:image-rest -am -Pnative





FROM bitnami/minideb:bullseye

COPY --from-builder /app/servers/Image/image-rest/image-rest app

ARG STABILITYAIKEY
ARG OPENAIKEY
ENV StabilityAIKEY=$STABILITYAIKEY
ENV OpenAIKey=$OPENAIKEY

ENTRYPOINT ["/app"]
