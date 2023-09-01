#!/bin/sh

# 清除文件
usage() {
	echo "Usage: sh clean.sh"
	exit 1
}


# clean sql
echo "begin clean sql "
# docker-compose-config.sql 容器中网络配置与本地有区别
rm -rf ./mysql/db/ry_20230223.sql
rm -rf ./mysql/db/ry_seata_20210128.sql

rm -rf ./mysql/data/*

rm -rf ./nacos/logs/*

# clean html
echo "begin clean html "
rm -rf ./nginx/html/dist/*
rm -rf ./nginx/logs/*

# clean jar
echo "begin clean ruoyi-gateway "
rm -rf ./ruoyi/gateway/jar/*.jar

echo "begin clean ruoyi-auth "
rm -rf ./ruoyi/auth/jar/*.jar

echo "begin clean ruoyi-visual "
rm -rf ./ruoyi/visual/monitor/jar/*.jar

echo "begin clean ruoyi-modules-system "
rm -rf ./ruoyi/modules/system/jar/*.jar

echo "begin clean ruoyi-modules-file "
rm -rf ./ruoyi/modules/file/jar/*.jar

echo "begin clean ruoyi-modules-job "
rm -rf ./ruoyi/modules/job/jar/*.jar

echo "begin clean ruoyi-modules-gen "
rm -rf ./ruoyi/modules/gen/jar/*.jar
