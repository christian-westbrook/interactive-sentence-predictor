Language Model Predictor README

Repository: language-model-predictor
Developers: Renae Fischer, Zachary Rowton, Christian Westbrook

1. RUNNING PREPROCESSOR

	To run the preprocessor first place any training data in the
	directory ./preprocessor/tokenizer/training-data.

	Next, execute the run.sh shell script with the command 'bash run.sh'.

	The output data is accessible by the runtime application.

2. RUNNING RUNTIME APPLICATION

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
        ├── Load.java
        ├── NatLangPredGUI.java
        ├── Node.java
        ├── Predictor.java
        ├── SpeechInterface.java
        ├── SpeechRecognizer.java
        ├── SpeechSynthesizer.java
        ├── TestLoad.java
        └── Utils.java

7 directories, 23 files
