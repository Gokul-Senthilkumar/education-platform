
FROM eclipse-temurin:21-jre

WORKDIR /app

RUN mkdir -p /app/tmp \
    && chown -R 10001:10001 /app

COPY --chown=10001:10001 target/*.jar app.jar

USER 10001:10001

EXPOSE 8080

ENTRYPOINT ["java", "-Djava.io.tmpdir=/app/tmp", "-jar", "/app/app.jar"]
