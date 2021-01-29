#! /bin/bash
for bootJar  in $(find . -name "project-*.jar")

do 
./$bootJar &

done
