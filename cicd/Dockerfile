FROM gradle:jdk17-jammy as builder
USER root

COPY --from=src . /workspace

RUN chown -R gradle:gradle /workspace/* && chown -R gradle:gradle /workspace/.
USER gradle
RUN cd /workspace && gradle build

FROM amazoncorretto:17.0.11
WORKDIR /usr/src/app
COPY --from=builder /workspace/app/build/libs/app.jar ./app.jar
COPY --from=builder /workspace/app/build/runtimeDependencies/applicationinsights-agent*.jar applicationinsights.jar
COPY run.sh /usr/src/app/run.sh
CMD  ["sh", "-c", "./run.sh"]
EXPOSE 8080/tcp