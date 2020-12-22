#!/bin/bash
docker rm -f nginx-latest
docker run --name=nginx-latest --restart=always -p 80:80 -v /opt/pscada-online/nginx-conf/:/etc/nginx/ -v /var/logs/nginx:/var/logs/nginx -v /opt/pscada-online/www:/opt/pscada-online/www -e TZ="Asia/Shanghai"  -d nginx

