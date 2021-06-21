#!/bin/bash

if [ $# != 2 ]; then
	echo "Run: $ bash draw.bash fileGeo OutputClassName"

else
	antlr4-build

	cd src/Geometrics

	java GeometricsMain ../../examples/$1.geo $2

	cd ../Beaver

	javac $2.java

	java $2
fi