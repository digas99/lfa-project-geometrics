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
	fi
done

if [ $isInPath = false ]; then
	{ echo ""; echo "export CLASSPATH=\"\${CLASSPATH}.:`pwd`/lib:\"";} >> ~/.bashrc
	exec bash # reload .bashrc within the same terminal
fi