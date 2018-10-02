Language Model Predictor README

Repository: language-model-predictor
Developers: Renae Fischer, Zachary Rowton, Christian Westbrook

1. RUNNING PREPROCESSOR

	To run the preprocessor first place any training data in the
	directory ./preprocessor/tokenizer/training-data.

	Next, execute the run.sh shell script with the command 'bash run.sh'.

	The output data will be stored in ./runtime/predictor/data and can
	be accessed with the Load class.

2. FILE MANIFEST

.
├── preprocessor
│   ├── Builder.class
│   ├── Builder.java
│   ├── run.sh
│   └── tokenizer
│       ├── output
│       │   ├── 1.html.out
│       │   ├── 2.html.out
│       │   ├── 3.html.out
│       │   ├── 4.html.out
│       │   └── tokens.out
│       ├── PS3.jj
│       └── training-data
│           ├── 1.html
│           ├── 2.html
│           ├── 3.html
│           └── 4.html
├── README.md
└── runtime
    ├── predictor
    │   ├── data
    │   │   ├── bigrams.map
    │   │   ├── metrics.dat
    │   │   ├── trigrams.map
    │   │   └── unigrams.map
    │   ├── Load.java
    │   ├── Node.java
    │   └── Predictor.java
    └── voce
        ├── config
        │   └── voce.config.xml
        ├── gram
        │   └── final.gram
        ├── SpeechInterface.java
        ├── SpeechRecognizer.java
        ├── SpeechSynthesizer.java
        └── Utils.java

10 directories, 27 files
