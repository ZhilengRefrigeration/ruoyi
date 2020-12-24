#!/bin/bash

## 编译每个服务

for gfile in $(find . -name "build.gradle")
do
    if [[ $1 = cl ]];then 
        
        gradle -b $gfile clean &
        echo -e "\033[32m----------------- $gfile 模块 清理 成功 ---------------------- \033[0m"
    else
        gradle -b $gfile clean build -x test -DbuildProduct=true -DreleaseVersion=$1 &
        
        if [ 0 -ne $? ] ; then 
		echo -e "\033[31m-----------------$gfile 模块编译失败导致产品编译失败! ------------ \033[0m";
	       continue; 
        fi
        echo -e "\033[32m----------------- $gfile 模块编译成功 ---------------------- \033[0m"
    fi
  
    
done

 
 
