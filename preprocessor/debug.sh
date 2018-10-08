#!/bin/sh

set -e

# Format the output of the NGramBuilder class.
sort ./ngrams/unigrams.txt | uniq -c | awk '{ print $2","$1 }' > ./ngrams/unigrams_freq.txt
sort ./ngrams/bigrams.txt | uniq -c | awk '{ print $2" "$3","$1 }' > ./ngrams/bigrams_freq.txt
sort ./ngrams/trigrams.txt | uniq -c | awk '{ print $2" "$3" "$4","$1 }' > ./ngrams/trigrams_freq.txt
# Alternate format : sort x.txt | uniq -c | sort -n -r | head -n 20000 | awk '{ print $1","$2" "$3 }' > bigrams_freq.txt

javac TestFreq.java
java TestFreq
