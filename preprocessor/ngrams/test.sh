#!/bin/sh

# We could use these commands to reduce the size of the output, but I'm not sure if we should.
# I think we would want more data, rather than less, if we want it to recogize even the most infrequent ngrams.
#sort unigrams.txt | uniq -c | sort -n -r | awk '{ print $1","$2 }' > unigrams_freq.txt
#sort bigrams.txt | uniq -c | sort -n -r | head -n 1000 | awk '{ print $1","$2" "$3 }' > bigrams_freq.txt
#sort trigrams.txt | uniq -c | sort -n -r | head -n 1000 | awk '{ print $1","$2" "$3" "$4 }' > trigrams_freq.txt

sort unigrams.txt | uniq -c | sort -n -r | awk '{ print $1","$2 }' > unigrams_freq.txt
sort bigrams.txt | uniq -c | sort -n -r | awk '{ print $1","$2" "$3 }' > bigrams_freq.txt
sort trigrams.txt | uniq -c | sort -n -r | awk '{ print $1","$2" "$3" "$4 }' > trigrams_freq.txt
