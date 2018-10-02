#!/bin/sh

# Configures this shell script to exit immediately if an error occurs.
# This includes any program returning an exit code other than 0
set -e

# Move into the tokenizer directory and compile the JavaCC tokenizer
cd ./tokenizer
javacc PS2.jj

# Move back into the root directory of this script and check if the data
# folder exists. If the data folder exists, remove it. Either way, create
# a new data file.
cd ..

if [ -d data ]
then
	rm -r data
fi

mkdir data

# Move into the tokenizer directory and compile the Java source code generated 
# by the JavaCC tokenizer
cd ./tokenizer
javac PS2Tokenizer.java

# After the script completes execution, remove each of the files generated when compiling the
# tokenizer.

cd ./tokenizer

rm ParseException.java
rm ParseException.class

rm PS2TokenizerConstants.java
rm PS2TokenizerConstants.class

rm PS2TokenizerTokenManager.java
rm PS2TokenizerTokenManager.class

rm TokenMgrError.java
rm TokenMgrError.class

rm PS2Tokenizer.java
rm PS2Tokenizer.class

rm SimpleCharStream.java
rm SimpleCharStream.class

rm Token.java
rm Token.class