FROM gradle:8.8-jdk21-alpine AS builder

WORKDIR /app

COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle
RUN ./gradlew dependencies --no-daemon || return 0

COPY . .

RUN ./gradlew clean build -x test --no-daemon

FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY --from=builder /app/build/libs/tre-library-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
