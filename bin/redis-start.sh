#! /bin/bash
docker rm -f redis-6.0
docker run --name=redis-6.0 --restart=always -itd -v /opt/pscada-online/redis-conf/redis.conf:/etc/redis/redis.conf -p 6379:6379 redis
