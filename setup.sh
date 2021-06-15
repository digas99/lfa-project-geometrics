#!/bin/bash

javac ./lib/*/*.java

d=`echo $CLASSPATH`
readarray -d / -t split <<< "$d"

isInPath=false
for (( n=0; n < ${#split[*]}; n++ ))
do
	s="${split[n]}"
	if [ $s = "geometrics-lfa-14" ]; then
		echo "The java CLASSPATH already contains the libs!"
		isInPath=true
		break
	fi
done

if [ $isInPath = false ]; then
	echo -n "This will add content to the end of your .bashrc file, proceed?(y/n) "
	read proceed
	targetPath=~/.bashrc
	setNewPath="n"
	if [ $proceed = n ]; then
		echo -n "Set a different path?(y/n) "
		read setNewPath
		if [ $setNewPath = y ]; then
			echo -n "Path: "
			read targetPath
		fi
	fi

	if [[ $proceed = y || $setNewPath = y ]]; then
		{ echo ""; echo "# CLASSPATH setup for CT-LFA-g14 project Geometrics 2021"; echo "export CLASSPATH=\"\${CLASSPATH}.:`pwd`/lib:.:`pwd`/src:\"";} >> $targetPath
		exec bash # reload .bashrc within the same terminal
	else
		echo "Aborted. No changes were made."
	fi
fi