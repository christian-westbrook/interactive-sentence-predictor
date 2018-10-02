//=================================================================================================
// Program		: Language Model Predictor
// Class		: Load.java
// Developer	: Christian Westbrook
// Abstract		: This program loads a HashMap of unigrams, bigrams, and trigrams for use by the
//				  predictor. Miscellaneous metrics are also loaded.
//=================================================================================================

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.util.HashMap;

public class Load
{
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
	
	public Load()
	{
		try
		{
			// Load unigram table
			FileInputStream ufis = new FileInputStream("./data/unigrams.map");
			ObjectInputStream uois = new ObjectInputStream(ufis);
			unigrams = (HashMap<String, Integer>) uois.readObject();
			uois.close();
		
			// Load bigram table
			FileInputStream bfis = new FileInputStream("./data/bigrams.map");
			ObjectInputStream bois = new ObjectInputStream(bfis);
			bigrams = (HashMap<String, Integer>) bois.readObject();
			bois.close();
		
			// Load trigram table
			FileInputStream tfis = new FileInputStream("./data/trigrams.map");
			ObjectInputStream tois = new ObjectInputStream(tfis);
			trigrams = (HashMap<String, Integer>) tois.readObject();
			tois.close();
		
			// Load metrics
			File mFile = new File("./data/metrics.dat");
			FileReader mfr = new FileReader(mFile);
			BufferedReader bfr = new BufferedReader(mfr);
		
			String[] metrics = bfr.readLine().split(",");
		
			n = Integer.parseInt(metrics[0]);
			v = Integer.parseInt(metrics[1]);
		
			unigramsN = Integer.parseInt(metrics[2]);
			unigramsV = Integer.parseInt(metrics[3]);
		
			bigramsN = Integer.parseInt(metrics[4]);
			bigramsV = Integer.parseInt(metrics[5]);
		
			trigramsN = Integer.parseInt(metrics[6]);
			trigramsV = Integer.parseInt(metrics[7]);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			System.exit(1);
		}
	}
	
	// Getters
	private HashMap getUnigrams()
	{
		return unigrams;
	}
	
	private HashMap getBigrams()
	{
		return bigrams;
	}
	
	private HashMap getTrigrams()
	{
		return trigrams;
	}
	
	private int getN()
	{
		return n;
	}
	
	private int getV()
	{
		return v;
	}
	
	private int getUnigramsN()
	{
		return unigramsN;
	}
	
	private int getUnigramsV()
	{
		return unigramsV;
	}
	
	private int getBigramsN()
	{
		return bigramsN;
	}
	
	private int getBigramsV()
	{
		return bigramsV;
	}
	
	private int getTrigramsN()
	{
		return trigramsN;
	}
	
	private int getTrigramsV()
	{
		return trigramsV;
	}
}