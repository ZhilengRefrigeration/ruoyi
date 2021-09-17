#!/bin/bash

# 命令后加入 & ，保持程序后台持续运行
nohup java -jar ./ruoyi-gateway.jar &
nohup java -jar ./ruoyi-auth.jar &
nohup java -jar ./ruoyi-modules-gen.jar &
nohup java -jar ./ruoyi-modules-file.jar &
nohup java -jar ./ruoyi-modules-job.jar &
nohup java -jar ./ruoyi-magicapi.jar &
java -jar ./ruoyi-modules-system.jar