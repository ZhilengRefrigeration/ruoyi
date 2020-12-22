#! /bin/bash
for dir in $(ls .)
do 
	if [[ -f $dir/project-*.jar ]];then
		./$dir/project-*.jar &
	fi	

done
