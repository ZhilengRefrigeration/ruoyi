FROM java:8-jdk

#作者
MAINTAINER awg

#系统编码和时区
ENV LANG C.UTF-8
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# 复制jar文件到镜像内，名字改为app.jar
ADD /target/*.jar app.jar

ENV JAVA_OPTS="-Xmx64M -Xms64M"

# 启动容器时的进程
ENTRYPOINT  java ${JAVA_OPTS} -jar /app.jar

# 暴露接口
EXPOSE 9203