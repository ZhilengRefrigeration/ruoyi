FROM openjdk:8-jdk
LABEL maintainer=huhongtao


#docker run -e PARAMS="--server.port 9090"
# server.port 启动端口
# 覆盖bootstrap。yml文件的内容，意思为去nacos中寻找 ruoyi-auth-prod.yml配置文件
# spring.cloud.nacos.discovery.server-addr=ruoyi-nacos.ruoyi:8848 注册中心地址
# spring.cloud.nacos.config.server-addr=ruoyi-nacos.ruoyi:8848 配置中心地址
# spring.cloud.nacos.config.namespace nacos配置文件所属命名空间
# spring.cloud.nacos.config.file-extension=yml 配置文件后缀名，类型
ENV PARAMS="--server.port=8080 --spring.profiles.active=prod --spring.cloud.nacos.discovery.server-addr=ruoyi-nacos.ruoyi:8848 --spring.cloud.nacos.config.server-addr=ruoyi-nacos.ruoyi:8848 --spring.cloud.nacos.config.namespace=prod --spring.cloud.nacos.config.file-extension=yml"
RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone

COPY target/*.jar /app.jar
EXPOSE 8080

#
ENTRYPOINT ["/bin/sh","-c","java -Dfile.encoding=utf8 -Djava.security.egd=file:/dev/./urandom -jar app.jar ${PARAMS}"]