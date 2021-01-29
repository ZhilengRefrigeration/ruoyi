#!/bin/bash
# 远程机器上程序发布目录  user@ip:/dir
remote_dist=$1
if [[ $remote_dist ]];then

	scp -r ./auth-boot/build/libs/project-auth-boot-*.jar              $remote_dist/auth &
	scp -r ./file-boot/build/libs/project-file-boot-*.jar              $remote_dist/file &
	scp -r ./gateway-boot/build/libs/project-gateway-boot-*.jar			$remote_dist/gateway &
	scp -r ./gen-boot/build/libs/project-gen-boot-*.jar					$remote_dist/gen &
	scp -r ./job-boot/build/libs/project-job-boot-*.jar					$remote_dist/job &
	scp -r ./monitor-boot/build/libs/project-monitor-boot-*.jar			$remote_dist/monitor &
	scp -r ./system-boot/build/libs/project-system-boot-*.jar			$remote_dist/system &
fi


