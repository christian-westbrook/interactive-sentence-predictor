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

public class Builder 
{
    private HashMap<String, Integer> unigrams;
	private HashMap<String, Integer> bigrams;
	private HashMap<String, Integer> trigrams;
	
	private int n;
	private int v;
	
	private int[] ugrams;
	private int[] bgrams;
	private int[] tgrams;

    public Builder() 
    {

        nGramBuilder();

        // Initialize HashMaps
        unigrams = new HashMap<String, Integer>();
        bigrams = new HashMap<String, Integer>();
        trigrams = new HashMap<String, Integer>();
			
        // Functions
        ugrams = mapBuilder(unigrams,"./ngrams/unigrams_freq.txt");
        bgrams = mapBuilder(bigrams,"./ngrams/bigrams_freq.txt");
        tgrams = mapBuilder(trigrams,"./ngrams/trigrams_freq.txt");
			
        computeMetrics();
        write();
        
    }

    private void nGramBuilder()
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

    private int[] mapBuilder(HashMap<String,Integer> map, String filename)
	{
        BufferedReader br;
		String read;
		String[] splt;
		int res[] = new int[2]; // N, V
		int freq;
    
		try {
		
            br = new BufferedReader(new FileReader(filename));
		
            while((read = br.readLine())!=null) {
		
            splt = read.split(",");
            freq = Integer.parseInt(splt[1]);
            
            if(map.get(splt[0]) == null) {
            
                map.put(splt[0],freq);

            }
            
            // Since we've already consolidated them, we can just add them up.
            res[0] += freq;
            res[1]++;
		
		}
		
		} catch(Exception ex) {
		
            ex.printStackTrace();
			System.exit(1);
		}
		
		return res;
		
	}
	
	private void computeMetrics()
	{
		n = ugrams[0] + bgrams[0] + tgrams[0];
		v = ugrams[1] + bgrams[1] + tgrams[1];
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
			uFile.createNewFile();
			FileOutputStream ufos = new FileOutputStream(uFile);
			ObjectOutputStream uoos = new ObjectOutputStream(ufos);
			uoos.writeObject(unigrams);
			uoos.close();
			
			// Write the bigrams table
			bFile.createNewFile();
			FileOutputStream bfos = new FileOutputStream(bFile);
			ObjectOutputStream boos = new ObjectOutputStream(bfos);
			boos.writeObject(bigrams);
			boos.close();
			
			// Write the trigrams table
			tFile.createNewFile();
			FileOutputStream tfos = new FileOutputStream(tFile);
			ObjectOutputStream toos = new ObjectOutputStream(tfos);
			toos.writeObject(trigrams);
			toos.close();
			
			// Write metrics to disk
			File mFile = new File("./data/metrics.dat");
			mFile.createNewFile();
			FileWriter mfw = new FileWriter(mFile);
			BufferedWriter mbw = new BufferedWriter(mfw);
			mbw.write(n + "," + v + "," + ugrams[0] + "," + ugrams[1] + ","
				      + bgrams[0] + "," + bgrams[1] + "," + tgrams[0] + ","
					  + tgrams[1] + "\n");
			mbw.close();
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
