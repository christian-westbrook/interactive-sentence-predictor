#!/bin/sh

# Configures this shell script to exit immediately if an error occurs.
# This includes any program returning an exit code other than 0
set -e

# Move into the tokenizer directory and compile the JavaCC tokenizer
cd ./tokenizer
javacc PS2.jj