#! /bin/bash
# 发布之前，必须先提交 !!!!  发布之前，必须先提交 !!!!  发布之前，必须先提交 !!!!
# 使用方法:  ./publish.sh 版本号.
# git pull; git add . ;git commit -m " 发布版本 $*" ; git push

gradleArgs="clean publish -DbuildProduct=true -DreleaseVersion=$1 -DdevVersion=$1"


# 发布公共模块，必须按照下列顺序 编译发布。
gradle $gradleArgs -b ./ruoyi-common/ruoyi-common-core/build.gradle -s
gradle $gradleArgs -b ./ruoyi-api/ruoyi-api-system/build.gradle -s 
gradle $gradleArgs -b ./ruoyi-common/ruoyi-common-redis/build.gradle -s 
gradle $gradleArgs -b ./ruoyi-common/ruoyi-common-security/build.gradle -s 
gradle $gradleArgs -b ./ruoyi-common/ruoyi-common-log/build.gradle -s 
gradle $gradleArgs -b ./ruoyi-common/ruoyi-common-datascope/build.gradle -s 
gradle $gradleArgs -b ./ruoyi-common/ruoyi-common-swagger/build.gradle -s 


### 六个 微服务依赖， monitor 不需要发布。
gradle $gradleArgs -b ./ruoyi-auth/build.gradle -s 
gradle $gradleArgs -b ./ruoyi-gateway/build.gradle -s 
gradle $gradleArgs -b ./ruoyi-modules/ruoyi-system/build.gradle -s 
gradle $gradleArgs -b ./ruoyi-modules/ruoyi-file/build.gradle -s
gradle $gradleArgs -b ./ruoyi-modules/ruoyi-gen/build.gradle -s 
gradle $gradleArgs -b ./ruoyi-modules/ruoyi-job/build.gradle -s 


