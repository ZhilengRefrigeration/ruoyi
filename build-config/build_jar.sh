#!/bin/bash
#echo " 本脚本参数说明，支持一个参数，当前模块目录"
bootModule=$1
echo "bootModule is :" $bootModule
# 准备 bootjar 依赖目录
rm -rf $bootModule/$bootModule-gui/libs $bootModule/$bootModule-server/libs
mkdir -p $bootModule/$bootModule-gui/libs $bootModule/$bootModule-server/libs

# 编译各个模块
for dir in $(ls .)
do
    if [[ -d $dir && -f $dir/build.gradle && $dir != 'build-config' && $dir != $bootModule  ]] ; then
        
        # 清理    
        if [[ -d $dir/$dir-pub ]] ; then rm -rf $dir/$dir-pub/build         ;fi
        if [[ -d $dir/$dir-gui ]] ; then rm -rf $dir/$dir-gui/build         ;fi
        if [[ -d $dir/$dir-server ]] ; then  rm -rf $dir/$dir-server/build  ;fi
        rm -rf $dir/.gradle $dir/.idea
        echo "gradle home is =" $PATH
        $GRADLE_HOME/bin/gradle  $gradleArgs --daemon --parallel  -x test -DbuildProduct=true -b $dir/build.gradle jar --s

        # 拷贝gui server公用jar到对应的目录。
        if [[ -d $dir/$dir-pub ]] ; then echo  $bootModule/$bootModule-gui/libs/ $bootModule/$bootModule-server/libs | xargs -n 1 cp -f $dir/$dir-pub/build/libs/*.jar ;fi
        if [[ -d $dir/$dir-gui ]] ; then cp -f $dir/$dir-gui/build/libs/*.jar  $bootModule/$bootModule-gui/libs;fi
        if [[ -d $dir/$dir-server ]] ; then  cp -f $dir/$dir-server/build/libs/*.jar  $bootModule/$bootModule-server/libs;fi

        if [ 0 -ne $? ] ; then echo "$dir build failed, please check the error log! "; exit 1  ; fi
        echo -e "\033[32m----------------- $dir 模块编译成功 ---------------------- \033[0m"
    fi
done
echo "BUILD SUCCESSFUL " `date`
