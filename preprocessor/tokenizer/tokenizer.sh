set -e

cd ./tokenizer/
mkdir data
cd ..

cp ./training-data/* ./tokenizer/data

cd ./tokenizer/

javac PS2.jj

if [ -d $2 ]
then
	rm -r $2
fi

javacc PS2.jj
javac PS2Tokenizer.java

echo Tokenizer run time
time java PS2Tokenizer $1 $2
echo

cat $2/*.out > ./$2/tokens.out

tr -sc 'A-Za-z0-9' '\n' < ./$2/tokens.out | tr A-Z a-z | sort | uniq -c | sort -n -r > ./$2/frequency.txt

echo Top 20 words by frequency
echo $(head -n 20 ./$2/frequency.txt)
echo
echo Bottom 20 words by frequency
echo $(tail -n 20 ./$2/frequency.txt)
cd ..
