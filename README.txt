Interactive Sentence Predictor 
README

Repository: https://github.com/christian-westbrook/interactive-sentence-predictor.git
Developers: Renae Fisher, Zachary Rowton, Christian Westbrook

1. PREPROCESSOR

	To run the preprocessor first place any training data in the
	directory ./preprocessor/tokenizer/training-data.

	Next, execute the run.sh shell script with the command 'bash run.sh'.

	The output data is accessible by the runtime application.

2. RUNTIME

	To load the runtime application, find run.sh in the predictor directory and execute it. 
	If you only want to compile all files in the predictor directory, execute build.sh.

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
	│   ├── WSJ_8gau_13dCep_16k_40mel_130Hz_6800Hz.jar
        ├── Load.java
        ├── NatLangPredGUI.java
        ├── Node.java
        ├── Predictor.java
        ├── SpeechInterface.java
        ├── SpeechRecognizer.java
        ├── SpeechSynthesizer.java
        ├── TestLoad.java
        ├── Utils.java
	├── build.sh
	└── run.sh

8 directories, 32 files


