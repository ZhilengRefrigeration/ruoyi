#!/bin/bash

## 编译每个 服务

for gfile in $(find . -name "build.gradle")
do
	
    gradle -b $gfile clean build -x test -DbuildProduct=true
    echo -e "\033[32m----------------- $gfile 模块编译成功 ---------------------- \033[0m"
done




