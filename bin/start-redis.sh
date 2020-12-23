#! /bin/bash
docker rm -f redis-6.0

#项目程序部署目录
project=$1 
docker run --name=redis-6.0 --restart=always -itd -v ${project}/redis-conf/redis.conf:/etc/redis/redis.conf -p 6379:6379 redis
