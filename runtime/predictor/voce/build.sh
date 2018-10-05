#!/bin/sh

cd voce

javac -classpath .:../lib/*: *.java
cd ..
jar cmvf ./voce/MANIFEST.MF ./lib/voce.jar ./voce/*.class
cd ./lib
jar uvf voce.jar ./gram/*.gram
jar i voce.jar
cd ..
