#! /bin/bash
# 发布之前，必须先提交 !!!!  发布之前，必须先提交 !!!!  发布之前，必须先提交 !!!!
# 使用方法:  ./publish.sh 版本号.
# git pull; git add . ;git commit -m " 发布版本 $*" ; git push

gradle --daemon --parallel -DbuildProduct=true clean publish -DreleaseVersion=$1

