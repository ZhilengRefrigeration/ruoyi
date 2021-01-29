#!/bin/bash
docker network rm fastdfs-net
docker network create fastdfs-net

docker run -dit --network=fastdfs-net --restart=always --name=fdfs-tracker \
	-v /var/fdfs/tracker:/var/fdfs \
	ygqygq2/fastdfs-nginx:latest tracker

docker run -dit --network=fastdfs-net --restart=always --name=fdfs-storage0 \
	-e TRACKER_SERVER=tracker:22122 -v /var/fdfs/storage0:/var/fdfs \
	ygqygq2/fastdfs-nginx:latest storage

docker run -dit --network=fastdfs-net --restart=always --name=fdfs-storage1 \
	-e TRACKER_SERVER=tracker:22122 -v /var/fdfs/storage1:/var/fdfs \
	ygqygq2/fastdfs-nginx:latest storage



