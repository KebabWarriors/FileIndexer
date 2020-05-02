FROM gradle:jdk14 as build-stage
WORKDIR /home/app/
COPY . /home/app/
RUN gradle runtime --no-daemon

FROM adoptopenjdk/openjdk14-openj9:jre-14.0.1_7_openj9-0.20.0-alpine AS deploy-stage
WORKDIR /opt/app/
COPY --from=build-stage /home/app/build/ /opt/app/

