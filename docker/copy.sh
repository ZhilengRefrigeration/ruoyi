#!/bin/sh

# 复制项目的文件到对应docker路径，便于一键生成镜像。
usage() {
	echo "Usage: sh copy.sh"
	exit 1
}


# copy sql
echo "begin copy sql "
cp ../sql/ry_20210908.sql ./mysql/db
cp ../sql/ry_config_20220114.sql ./mysql/db

# copy html
echo "begin copy html "
cp -r ../project-ui/dist/** ./nginx/html/dist


# copy jar
echo "begin copy project-gateway "
cp ../project-gateway/target/project-gateway.jar ./ruoyi/gateway/jar

echo "begin copy project-auth "
cp ../project-auth/target/project-auth.jar ./ruoyi/auth/jar

echo "begin copy project-visual "
cp ../project-visual/project-monitor/target/project-visual-monitor.jar  ./ruoyi/visual/monitor/jar

echo "begin copy project-modules-system "
cp ../project-modules/project-system/target/project-modules-system.jar ./ruoyi/modules/system/jar

echo "begin copy project-modules-file "
cp ../project-modules/project-file/target/project-modules-file.jar ./ruoyi/modules/file/jar

echo "begin copy project-modules-job "
cp ../project-modules/project-job/target/project-modules-job.jar ./ruoyi/modules/job/jar

echo "begin copy project-modules-gen "
cp ../project-modules/project-gen/target/project-modules-gen.jar ./ruoyi/modules/gen/jar

