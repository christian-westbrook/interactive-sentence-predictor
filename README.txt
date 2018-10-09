Interactive Sentence Predictor
Copyright (c) 2018 Renae Fisher, Zachary Rowton, and Christian Westbrook. All rights reserved.

Repository: https://github.com/christian-westbrook/interactive-sentence-predictor.git

1. INTRODUCTION

The Interactive Sentence Predictor (ISP) is a natural language processing system that generates likely continuations of partial sentences.

2. EXECUTION

	A. EXECUTE ENTIRE SYSTEM
	
	To run the whole system, find the run.sh script in the root directory and execute it.
	The training data located in ./preprocessor/tokenizer/training-data will be used to
	build the language models. The accuracy and speed of the system is largely dependent
	on the input training data.
	
	B. EXECUTE PREPROCESSOR ONLY

	To run the preprocessor first place any training data in the
	directory ./preprocessor/tokenizer/training-data.

	Next, execute the run.sh shell script with the command 'bash run.sh'.

	The output data is accessible by the runtime application.

	C. EXECUTE RUNTIME APPLICATION ONLY

	To execute the runtime application, find run.sh in the predictor directory and execute it.

	The run.sh script will launch a GUI. Press the 'listen' button to start SST recognition. 
	Once the application recognizes a long enough sentence, it will begin to build a sentence.

3. FILE MANIFEST

.
├── run.sh
├── README.txt
├── preprocessor
│   ├── Builder.class
│   ├── Builder.java
│   ├── GramComparator.java
│   ├── GramFreq.java
│   ├──TestFreq.java
│   ├── debug.sh
│   ├── run.sh
│   ├── ngrams
│   └── tokenizer
│       ├── output
│       │   └── tokens.out
│       ├── PS3.jj
│       └── training-data
│           ├── 1.html
│           ├── 2.html
│           ├── 3.html
│           └── 4.html
└── runtime
    └── predictor
        ├── data
        │   ├── bigrams.map
        │   ├── metrics.dat
        │   ├── trigrams.map
        │   └── unigrams.map
	├── lib
	│   ├── cmu_us_kal.jar
	│   ├── cmulex.jar
	│   ├── en_us.jar
	│   ├── freetts.jar
	│   ├── jsapi.jar
	│   ├── sphinx4.jar
	│   ├── voce.jar
	│   ├── readme.txt
	│   ├── voce.config.xml
	│   ├──WSJ_8gau_13dCep_16k_40mel_130Hz_6800Hz.jar
	│   └── gram
	│   	├── final.gram
	│   	└── digits.gram
	├── voce
	│   ├── MANIFEST.MF
	│   ├── SpeechInterface.java
	│   ├── SpeechRecognizer.java
	│   ├── SpeechSynthesizer.java
	│   ├── Utils.java
	│   ├── build.sh
	│   └── genMethodSignatures.bat
	├── GramComparator.java
	├── GramFreq.java
	├── CLTest.java
	├── GUI.java
	├── Load.java
	├── Node.java
	├── Predictor.java
	└── run.sh

11 directories, 46 files
