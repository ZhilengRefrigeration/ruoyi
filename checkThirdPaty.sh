#!/bin/bash
currentDir=`pwd`
shortName=${currentDir##*/}
rm -rf temp  ${shortName}-third-jar.gradle
echo "dependencies { ">> ${shortName}-third-jar.gradle

find . -name "build.gradle" | xargs cat |grep -E "compile group|compile 
'|implementation " | egrep -v 'gridnt' >> temp
sort temp |uniq >> ${shortName}-third-jar.gradle
rm -rf temp

echo "} ">> ${shortName}-third-jar.gradle

 
