FROM hseeberger/scala-sbt:8u181_2.12.7_1.2.6

MAINTAINER Franklin Chou

STOPSIGNAL SIGTERM

ARG service_root=/opt/service

ARG app_name=csv-2-cassandra

ARG app_root=${service_root}/${app_name}

RUN if [ ! -d "${app_root}" ]; then mkdir -p $app_root; fi

WORKDIR $app_root

COPY ./${app_name}/app ./app
COPY ./${app_name}/conf ./conf
COPY ./${app_name}/lib ./lib
COPY ./${app_name}/project ./project
COPY ./${app_name}/build.sbt ./build.sbt

RUN sbt clean compile
