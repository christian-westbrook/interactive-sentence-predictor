//=================================================================================================
// Program		: Interactive Sentence Predictor Preprocessor
// Class		: Build.java
// Developer	: Christian Westbrook
// Abstract		: This program inputs token and frequency data and builds a series of ngram tables
//                along with calculating certain metrics. All of the generated information is stored
//                to disk to be used by the Language Model Predictor's runtime application.
//=================================================================================================

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class NGramBuilder
{
    public NGramBuilder()
	{
			ngramBuilder();
	}
	
	private void ngramBuilder()
	{
		try
		{
			// Open the tokens file
			File tokens = new File("./tokenizer/output/tokens.out");
			FileReader fr = new FileReader(tokens);
			BufferedReader br = new BufferedReader(fr);
			
			BufferedWriter bw1 = new BufferedWriter(new FileWriter("./ngrams/unigrams.txt"));
			BufferedWriter bw2 = new BufferedWriter(new FileWriter("./ngrams/bigrams.txt"));
			BufferedWriter bw3 = new BufferedWriter(new FileWriter("./ngrams/trigrams.txt"));
		
			// Create three word variables for building ngrams
			String w1 = "", w2 = "", w3 = "";
		
			// Iterate through lines in the tokens file
			String line = "";
			while((line = br.readLine()) != null)
			{
				// Shift words back through the variables
				w1 = w2;
				w2 = w3;
				w3 = line.toLowerCase();
			
				//If all three words are filled, add a trigram
				if(!w1.equals("") && !w2.equals("") && !w3.equals(""))
					bw3.write(w1 + " " + w2 + " " + w3 + "\n");
			
				// If the last two words are filled, add a bigram
				if(!w2.equals("") && !w3.equals(""))
					bw2.write(w2 + " " + w3 + "\n");
			
				// If the last word is filled, add a unigram
				if(!w3.equals(""))
					bw1.write(w3 + "\n");
			}
		
			br.close();
			bw3.close();
			bw2.close();
			bw1.close();
		
		}
		catch (FileNotFoundException ex)
		{
			ex.printStackTrace();
			System.exit(1);
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
			System.exit(1);
		}
	}
	
	public static void main(String[] args)
	{
		new NGramBuilder();
	}
}
