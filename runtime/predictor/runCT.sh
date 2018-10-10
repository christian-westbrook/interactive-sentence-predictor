#!/bin/sh

set -e

chmod +x ./voce/build.sh
./voce/build.sh

javac -cp .:./lib/* *.java
java -cp .:./lib/* CLTest "$@"
