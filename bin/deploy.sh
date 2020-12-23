#!/bin/bash
# 远程机器上程序发布目录  user@ip:/dir
remote_dist=$1
if [[ $remote_dist ]];then

	scp -r ./ruoyi-auth/build/libs/project-ruoyi-auth-*.jar   							$remote_dist/auth &
	scp -r ./ruoyi-gateway/build/libs/project-ruoyi-gateway-*.jar						$remote_dist/gateway  &
	scp -r ./ruoyi-modules/ruoyi-file/build/libs/project-ruoyi-file-*.jar				$remote_dist/file &
	scp -r ./ruoyi-modules/ruoyi-gen/build/libs/project-ruoyi-modules-gen-*.jar		    $remote_dist/gen  &
	scp -r ./ruoyi-modules/ruoyi-job/build/libs/project-ruoyi-modules-job-*.jar			$remote_dist/job &
	scp -r ./ruoyi-modules/ruoyi-system/build/libs/project-ruoyi-modules-system-*.jar  $remote_dist/system &
	scp -r ./ruoyi-visual/ruoyi-monitor/build/libs/project-ruoyi-visual-monitor-*.jar  $remote_dist/monitor &

fi
