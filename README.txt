Interactive Sentence Predictor
Copyright © Renae Fisher, Zachary Rowton, and Christian Westbrook 2018. All rights reserved.

Repository: https://github.com/christian-westbrook/interactive-sentence-predictor.git

1. PREPROCESSOR

	To run the preprocessor first place any training data in the
	directory ./preprocessor/tokenizer/training-data.

	Next, execute the run.sh shell script with the command 'bash run.sh'.

	The output data is accessible by the runtime application.

2. RUNTIME APPLICATION

	To load the runtime application, find run.sh in the predictor directory and execute it.

	The run.sh script will launch a GUI. Press the 'listen' button to start SST recognition. 
	Once the application recognizes a long enough sentence, it will begin to build a sentence.

3. FILE MANIFEST

.
├── preprocessor
│   ├── Builder.class
│   ├── Builder.java
│   ├── run.sh
│   └── tokenizer
│       ├── output
│       │   └── tokens.out
│       ├── PS3.jj
│       └── training-data
│           ├── 1.html
│           ├── 2.html
│           ├── 3.html
│           └── 4.html
├── README.txt
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
        ├── CLTest.java
	├── GUI.java
	├── Load.java
        ├── Node.java
        ├── Predictor.java
	└── run.sh

10 directories, 37 files
