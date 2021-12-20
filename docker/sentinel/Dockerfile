FROM  openjdk:8-jre

MAINTAINER ruoyi

ADD https://hub.fastgit.org/alibaba/Sentinel/releases/download/1.8.2/sentinel-dashboard-1.8.2.jar app.jar

EXPOSE 8858 8719

VOLUME /logs/sentinel

ENTRYPOINT [  \
"java","-Djava.security.egd=file:/dev/./urandom","-Dserver.port=8858", \
"-Dcsp.sentinel.api.port=8719","-Dcsp.sentinel.dashboard.server=localhost:8858",  \
"-Dlogging.file=/logs/sentinel/sentinel-dashboard.log", "-Dcsp.sentinel.log.dir=/logs/sentinel",  \
"-Dproject.name=sentinel-dashboard","-jar","app.jar"  \
]
