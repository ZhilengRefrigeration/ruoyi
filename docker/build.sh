#!/bin/sh

# 项目打包
cd .. && mvn clean package -Dmaven.test.skip=true -P DockerCompose

cd ./ruoyi-ui && npm install && npm run build:prod