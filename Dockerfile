FROM eclipse-temurin:17-jdk-jammy AS base

# Use the base image to build a layer with the gradle wrapper. This means we dont need to
# download the wrapper every time we build the project as the gradle layer is cached by docker
FROM base as gradle

WORKDIR /app

COPY gradle/ gradle
COPY gradlew .
COPY settings.gradle .

RUN ./gradlew tasks

# Use the gradle layer built above as the basis to build the java project. This layer should only
# get build if the previous layer is build or the files COPYed in are changed
FROM gradle as builder

WORKDIR /app

COPY build.gradle .
COPY src/ src

RUN ./gradlew build

# Use the base image and copy in the jar file built in the layer above, meaning we get an image built
# using the project source code, but with just the packaged code in it.
FROM base AS runner

WORKDIR /app

ARG JAR_FILE=/app/build/libs/showcase.jar

COPY --from=builder ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","app.jar"]