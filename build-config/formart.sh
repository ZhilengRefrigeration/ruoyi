#!/bin/bash

## 格式化 jar 引用

find . -name "build.gradle" | xargs sed -r  -i "s/'/\"/g"
find . -name "build.gradle" | xargs sed -r  -i 's/compile group: /compile /g'
find . -name "build.gradle" | xargs sed -r  -i 's/compileOnly group: /compileOnly /g'
find . -name "build.gradle" | xargs sed -r  -i 's/implementation group: /implementation /g'
find . -name "build.gradle" | xargs sed -r  -i 's/runtime group: /runtime /g'
find . -name "build.gradle" | xargs sed -r  -i 's/testRuntime group: /testRuntime /g'
find . -name "build.gradle" | xargs sed -r  -i 's/testImplementation group: /testImplementation /g'
find . -name "build.gradle" | xargs sed -r  -i 's/", name: "|", version: "/:/g'

