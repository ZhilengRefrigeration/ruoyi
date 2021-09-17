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
    cp -f ../ruoyi-gateway/target/ruoyi-gateway.jar ./ruoyi/gateway/jar/ruoyi-gateway.jar
    cp -f ../ruoyi-auth/target/ruoyi-auth.jar ./ruoyi/auth/jar/ruoyi-auth.jar
    cp -f ../ruoyi-modules/ruoyi-system/target/ruoyi-modules-system.jar ./ruoyi/modules/system/jar/ruoyi-modules-system.jar
    cp -f ../ruoyi-modules/ruoyi-file/target/ruoyi-modules-file.jar ./ruoyi/modules/file/jar/ruoyi-modules-file.jar
    cp -f ../ruoyi-modules/ruoyi-gen/target/ruoyi-modules-gen.jar ./ruoyi/modules/gen/jar/ruoyi-modules-gen.jar
    cp -f ../ruoyi-modules/ruoyi-job/target/ruoyi-modules-job.jar ./ruoyi/modules/job/jar/ruoyi-modules-job.jar

    cp -f ../ruoyi-gateway/target/ruoyi-gateway.jar ./ruoyi/all/jar/ruoyi-gateway.jar
    cp -f ../ruoyi-auth/target/ruoyi-auth.jar ./ruoyi/all/jar/ruoyi-auth.jar
    cp -f ../ruoyi-modules/ruoyi-system/target/ruoyi-modules-system.jar ./ruoyi/all/jar/ruoyi-modules-system.jar
    cp -f ../ruoyi-modules/ruoyi-file/target/ruoyi-modules-file.jar ./ruoyi/all/jar/ruoyi-modules-file.jar
    cp -f ../ruoyi-modules/ruoyi-gen/target/ruoyi-modules-gen.jar ./ruoyi/all/jar/ruoyi-modules-gen.jar
    cp -f ../ruoyi-modules/ruoyi-job/target/ruoyi-modules-job.jar ./ruoyi/all/jar/ruoyi-modules-job.jar
    cp -f ../ruoyi-modules/ruoyi-magicapi/target/ruoyi-magicapi.jar ./ruoyi/all/jar/ruoyi-magicapi.jar
}

# 启动基础环境（必须）
base(){
	docker-compose up -d ruoyi-mysql ruoyi-redis ruoyi-nacos ruoyi-nginx
}

# 启动程序模块（必须）
modules(){
	docker-compose up -d ruoyi-gateway ruoyi-auth ruoyi-modules-system
}

# 启动程序模块（opt）
opts(){
	docker-compose up -d ruoyi-modules-file ruoyi-modules-gen ruoyi-modules-job
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
