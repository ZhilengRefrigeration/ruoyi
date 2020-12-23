#!/bin/bash

## 编译每个 服务

for gfile in $(find . -name "build.gradle")
do
    if [[ $1 = cl ]];then 
        
        gradle -b $gfile clean;  
        echo -e "\033[32m----------------- $gfile 模块 清理 成功 ---------------------- \033[0m"
    else
        gradle -b $gfile clean build -x test -DbuildProduct=true -DreleaseVersion=$1
        echo -e "\033[32m----------------- $gfile 模块编译成功 ---------------------- \033[0m"
    fi
  
    
done

 
 
