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
import java.util.HashMap;

public class Builder
{	
	private ArrayList<String> unigramList;
	private ArrayList<String> bigramList;
	private ArrayList<String> trigramList;
	
	private HashMap<String, Integer> unigrams;
	private HashMap<String, Integer> bigrams;
	private HashMap<String, Integer> trigrams;
	
	private int n;
	private int v;
	
	private int unigramsN;
	private int unigramsV;
	
	private int bigramsN;
	private int bigramsV;
	
	private int trigramsN;
	private int trigramsV;
	
	public Builder()
	{
			// Initialize ngram ArrayLists
			unigramList = new ArrayList<String>();
			bigramList = new ArrayList<String>();
			trigramList = new ArrayList<String>();
			
			// Initialize HashMaps
			unigrams = new HashMap<String, Integer>();
			bigrams = new HashMap<String, Integer>();
			trigrams = new HashMap<String, Integer>();
			
			// Functions
			ngramBuilder();
			mapBuilder();
			computeMetrics();
			write();
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
					trigramList.add(new String(w1 + " " + w2 + " " + w3));
			
				// If the last two words are filled, add a bigram
				if(!w2.equals("") && !w3.equals(""))
					bigramList.add(new String(w2 + " " + w3));
			
				// If the last word is filled, add a unigram
				if(!w3.equals(""))
					unigramList.add(new String(w3));
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
	}
	
	private void mapBuilder()
	{
		// Build unigram map
		for(String u : unigramList)
		{
			if(unigrams.get(u) == null)
			{
				unigrams.put(u, 1);
			}
			else
			{
				int count = unigrams.get(u);
				count++;
				unigrams.put(u, count);
			}
		}
		
		// Build bigram map
		for(String b : bigramList)
		{
			if(bigrams.get(b) == null)
			{
				bigrams.put(b, 1);
			}
			else
			{
				int count = bigrams.get(b);
				count++;
				bigrams.put(b, count);
			}
		}
		
		// Build trigram map
		for(String t : trigramList)
		{
			if(trigrams.get(t) == null)
			{
				trigrams.put(t, 1);
			}
			else
			{
				int count = trigrams.get(t);
				count++;
				trigrams.put(t, count);
			}
		}
	}
	
	private void computeMetrics()
	{
		unigramsN = unigramList.size();
		bigramsN = bigramList.size();
		trigramsN = trigramList.size();
		
		unigramsV = unigrams.size();
		bigramsV = bigrams.size();
		trigramsV = trigrams.size();
		
		n = unigramsN + bigramsN + trigramsN;
		v = unigramsV + bigramsV + trigramsV;
	}
	
	private void write()
	{
		try
		{
			// Create three object output files
			File uFile 	= new File("./data/unigrams.map");
			File bFile 	= new File("./data/bigrams.map");
			File tFile 	= new File("./data/trigrams.map");
			
			// Write the unigrams table
			uFile.create();
			FileOutputStream ufos = new FileOutputStream(uFile);
			ObjectOutputStream uoos = new ObjectOutputStream(ufos);
			uoos.writeObject(unigrams);
			uoos.close();
			
			// Write the bigrams table
			bFile.create();
			FileOutputStream bfos = new FileOutputStream(bFile);
			ObjectOutputStream boos = new ObjectOutputStream(bfos);
			boos.writeObject(bigrams);
			boos.close();
			
			// Write the trigrams table
			tFile.create();
			FileOutputStream tfos = new FileOutputStream(tFile);
			ObjectOutputStream toos = new ObjectOutputStream(tfos);
			toos.writeObject(trigrams);
			toos.close();
			
			// Write metrics to disk
			File mFile = new File("./data/metrics.dat");
			mFile.create();
			FileWriter mfw = new FileWriter(mFile);
			BufferedWriter mbw = new BufferedWriter(mfw);
			mbw.write(n + "," + v + "," + unigramsN + "," + unigramsV + ","
				      + bigramsN + "," + bigramsV + "," + trigramsN + "m"
					  + trigramsV + "\n");
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
			System.exit(1);
		}
	}
	
	public static void main(String[] args)
	{
		new Builder();
	}
}