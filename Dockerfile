# =========================
# Stage 1 — Build (Maven)
# =========================
FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

# Leverage Docker layer cache: first copy pom.xml and resolve dependencies
COPY pom.xml ./
RUN --mount=type=cache,target=/root/.m2 mvn -B -q -e -DskipTests dependency:go-offline

# Now copy the rest of the sources and build
COPY src ./src
RUN --mount=type=cache,target=/root/.m2 mvn -B -q -DskipTests clean package

# Spring Boot builds a fat jar; capture the filename
# (If your artifactId/version differ, adjust the pattern)
RUN JAR_FILE=$(ls target/*-SNAPSHOT.jar || ls target/*.jar) && \
    cp "$JAR_FILE" /app/app.jar


# ==================================
# Stage 2 — Runtime (slim JRE image)
# ==================================
# You can switch to distroless if you prefer (no shell). For convenience, use a slim JRE.
FROM eclipse-temurin:21-jre-alpine AS runtime

ENV TZ=UTC \
    JAVA_TOOL_OPTIONS="-XX:+UseG1GC -XX:MaxRAMPercentage=75 -XX:+HeapDumpOnOutOfMemoryError -Dfile.encoding=UTF-8" \
    SPRING_PROFILES_ACTIVE=prod \
    SERVER_PORT=8080

# Create a non-root user/group with consistent UID/GID
# 10014 is arbitrary but outside system range; adapt if your platform requires.
RUN addgroup -S app && adduser -S -G app -u 10014 app

WORKDIR /app
COPY --from=build /app/app.jar /app/app.jar

# Ensure minimal permissions: app user can read the jar, logs dir writable, everything else read-only
RUN mkdir -p /app/logs && chown -R app:app /app
USER app:app

EXPOSE 8080

# Optional: Container-level healthcheck (Kubernetes uses probes; Docker users get this too)
# The alpine image has /bin/sh and wget; if you switch to distroless, remove this.
HEALTHCHECK --interval=30s --timeout=3s --start-period=20s --retries=3 \
  CMD wget -qO- "http://127.0.0.1:${SERVER_PORT}/actuator/health/liveness" >/dev/null || exit 1

# Use exec form; pass any extra args with `docker run ... -- --spring.foo=bar`
ENTRYPOINT ["java","-jar","/app/app.jar"]