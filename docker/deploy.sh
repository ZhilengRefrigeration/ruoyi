#!/bin/sh

# 使用说明，用来提示输入参数
usage() {
	echo "Usage: sh 执行脚本.sh [port|copy|base|modules|stop|rm]"
	exit 1
}

# 开启所需端口
port(){
	firewall-cmd --add-port=80/tcp --permanent
	firewall-cmd --add-port=8080/tcp --permanent
	firewall-cmd --add-port=8848/tcp --permanent
	firewall-cmd --add-port=9848/tcp --permanent
	firewall-cmd --add-port=9849/tcp --permanent
	firewall-cmd --add-port=6379/tcp --permanent
	firewall-cmd --add-port=3306/tcp --permanent
	firewall-cmd --add-port=9100/tcp --permanent
	firewall-cmd --add-port=9200/tcp --permanent
	firewall-cmd --add-port=9201/tcp --permanent
	firewall-cmd --add-port=9202/tcp --permanent
	firewall-cmd --add-port=9203/tcp --permanent
	firewall-cmd --add-port=9300/tcp --permanent
	service firewalld restart
}

copy(){
    #删除各模块下的jar文件
    find ./lynn-cloud/*/jar/ -name "*.jar" -exec rm -f {} \;
    find ./lynn-cloud/*/*/jar/ -name "*.jar" -exec rm -f {} \;

    #将各个模块的jar文件复制到指定的docker容器文件夹中
    cp -f ../ruoyi-gateway/target/*.jar ./lynn-cloud/gateway/jar/
    cp -f ../ruoyi-auth/target/*.jar ./lynn-cloud/auth/jar/
    cp -f ../ruoyi-modules/ruoyi-system/target/*.jar ./lynn-cloud/modules/system/jar/
    cp -f ../ruoyi-modules/ruoyi-file/target/*.jar ./lynn-cloud/modules/file/jar/
    cp -f ../ruoyi-modules/ruoyi-gen/target/*.jar ./lynn-cloud/modules/gen/jar/
    cp -f ../ruoyi-modules/ruoyi-job/target/*.jar ./lynn-cloud/modules/job/jar/
    cp -f ../ruoyi-modules/lynn-magic-api/target/*.jar ./lynn-cloud/modules/magic-api/jar/

    #删除all文件夹下的所有jar文件
    find ./lynn-cloud/all/jar/ -name "*.jar" -exec rm -f {} \;

    #将各模块下的jar文件复制到all/jar文件夹内
    cp -f ../*/target/*.jar ./lynn-cloud/all/jar/
    cp -f ../*/*/target/*.jar ./lynn-cloud/all/jar/
}

# 启动基础环境（必须）
base(){
	docker-compose up -d lynn-mysql lynn-redis lynn-nacos lynn-nginx
}

# 启动程序模块（必须）
modules(){
    copy
	docker-compose up -d lynn-gateway lynn-auth lynn-modules-system lynn-magic-api
}

# 启动程序模块（opt）
opts(){
	docker-compose up -d lynn-modules-file lynn-modules-gen ruoyi-modules-job
}

# 关闭所有环境/模块
stop(){
	docker-compose stop
}

# 删除所有环境/模块
rm(){
	docker-compose rm
}

# 根据输入参数，选择执行对应方法，不输入则执行使用说明
case "$1" in
"port")
	port
;;
"copy")
	copy
;;
"base")
	base
;;
"modules")
	modules
;;
"opts")
	opts
;;
"stop")
	stop
;;
"rm")
	rm
;;
*)
	usage
;;
esac
