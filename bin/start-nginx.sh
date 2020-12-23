#!/bin/bash
docker rm -f nginx-latest
#项目程序部署目录
project=$1 
docker run --name=nginx-latest --restart=always -p 80:80 -v ${project}/nginx-conf/:/etc/nginx/ -v /var/logs/nginx:/var/logs/nginx -v ${project}/www:/opt/pscada-online/www -e TZ="Asia/Shanghai"  -d nginx

