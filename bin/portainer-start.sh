#!/bin/bash
docker rm -f prtainer-latest
docker run -itd -p 9000:9000 -e TZ="Asia/Shanghai" \
    --name prtainer-latest --restart=always \
    -v /var/run/docker.sock:/var/run/docker.sock \
    portainer/portainer-ce



