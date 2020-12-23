#!/bin/bash

if [[ $1 ]];then

	scp -r ./ruoyi-auth/build/libs/project-ruoyi-auth-dev-9-SNAPSHOT.jar   							$1/auth 
	scp -r ./ruoyi-gateway/build/libs/project-ruoyi-gateway-dev-9-SNAPSHOT.jar						$1/gateway 
	scp -r ./ruoyi-modules/ruoyi-file/build/libs/project-ruoyi-file-dev-9-SNAPSHOT.jar				$1/file
	scp -r ./ruoyi-modules/ruoyi-gen/build/libs/project-ruoyi-modules-gen-dev-9-SNAPSHOT.jar		$1/gen 
	scp -r ./ruoyi-modules/ruoyi-job/build/libs/project-ruoyi-modules-job-dev-9-SNAPSHOT.jar		$1/job 
	scp -r ./ruoyi-modules/ruoyi-system/build/libs/project-ruoyi-modules-system-dev-9-SNAPSHOT.jar  $1/system 
	scp -r ./ruoyi-visual/ruoyi-monitor/build/libs/project-ruoyi-visual-monitor-dev-9-SNAPSHOT.jar  $1/monitor 

fi
