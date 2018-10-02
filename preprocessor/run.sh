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