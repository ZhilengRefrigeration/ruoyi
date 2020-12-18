#!/bin/bash
currentDir=`pwd`
shortName=${currentDir##*/}
rm -rf temp  build-product-${shortName}.gradle
echo "dependencies { ">> build-product-${shortName}.gradle

find . -name "build.gradle" | xargs cat |grep -E "compile group|compile '|implementation " | egrep -v 'gridnt' >> temp
sort temp |uniq >> build-product-${shortName}.gradle
rm -rf temp

echo "} ">> build-product-${shortName}.gradle

rm -rf temp  gridnt-jar-${shortName}.md
find . -name "build.gradle" | xargs cat |grep -E "compile group|compile '|implementation " | grep -E 'gridnt' >> temp
sort temp |uniq >> gridnt-jar-${shortName}.md
rm -rf temp
