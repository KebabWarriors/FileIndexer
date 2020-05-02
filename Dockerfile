FROM gradle:jdk14 as build-stage
WORKDIR /home/app/
COPY . /home/app/
RUN gradle runtime --no-daemon

FROM debian:stretch-slim AS deploy-stage
WORKDIR /opt/app/
COPY --from=build-stage /home/app/build/ /opt/app/

