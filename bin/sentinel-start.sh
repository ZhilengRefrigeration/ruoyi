#!/bin/bash
docker rm -f sentinel-dashboard

docker run --name=sentinel-dashboard --restart=always -e TZ="Asia/Shanghai" -p 9002:8858 -d bladex/sentinel-dashboard:latest 


