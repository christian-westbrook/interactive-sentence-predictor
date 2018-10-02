//=================================================================================================
// Program		: Language Model Predictor Preprocessor
// Class		: Build.java
// Developer	: Christian Westbrook
// Abstract		: This program inputs token and frequency data and builds a series of ngram tables
//                along with calculating certain metrics. All of the generated information is stored
//                to disk to be used by the Language Model Predictor's runtime application.
//=================================================================================================

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Builder
{
	private File frequency;
	
	private ArrayList<String> unigrams;
	private ArrayList<String> bigrams;
	private ArrayList<String> trigrams;
	
	public Builder()
	{
			// Input the  frequency file
			frequency = new File("./tokenizer/output/tokens.out");
		
			// Initialize ngram ArrayLists
			unigrams = new ArrayList<String>();
			bigrams = new ArrayList<String>();
			trigrams = new ArrayList<String>();
			
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
		
			// Create three word variables for building ngrams
			String w1 = "", w2 = "", w3 = "";
		
			// ========================= TEMPORARY ==================================
			System.out.println("Tokens");
			System.out.println("------------------------");
			// ========================= TEMPORARY ==================================
		
			// Iterate through lines in the tokens file
			String line = "";
			while((line = br.readLine()) != null)
			{
				// ========================= TEMPORARY ==================================
				System.out.println(line);
				// ========================= TEMPORARY ==================================
			
				// Shift words back through the variables
				w1 = w2;
				w2 = w3;
				w3 = line;
			
				//If all three words are filled, add a trigram
				if(!w1.equals("") && !w2.equals("") && !w3.equals(""))
					trigrams.add(new String(w1 + " " + w2 + " " + w3));
			
				if(!w2.equals("") && !w3.equals(""))
					bigrams.add(new String(w2 + " " + w3));
			
				if(!w3.equals(""))
					unigrams.add(new String(w3));
			}
		
			br.close();
		
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
		
		// ========================= TEMPORARY ==================================
		System.out.println();
		
		System.out.println("Unigrams");
		System.out.println("------------------------");
		for(String u : unigrams)
		{
				System.out.println(u);
		}
		System.out.println();
		
		System.out.println("Bigrams");
		System.out.println("------------------------");
		for(String b : bigrams)
		{
				System.out.println(b);
		}
		System.out.println();
		
		System.out.println("Trigrams");
		System.out.println("------------------------");
		for(String t : trigrams)
		{
				System.out.println(t);
		}
		System.out.println();
		// ========================= TEMPORARY ==================================
	}
	
	public static void main(String[] args)
	{
		new Builder();
	}
}