//=================================================================================================
// Program		: Interactive Sentence Predictor Preprocessor
// Class		: Build.java
// Developer		: Christian Westbrook, Renae Fisher
// Abstract		: This program inputs token and frequency data and builds a series of ngram tables
//                along with calculating certain metrics. All of the generated information is stored
//                to disk to be used by the Language Model Predictor's runtime application.
//=================================================================================================

import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.File;
import java.util.HashMap;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.Map;
import java.util.Iterator;

public class Builder {

    private HashMap<String, Integer> unigrams;
	private HashMap<String, Integer> bigrams;
	private HashMap<String, Integer> trigrams;
	
	private HashMap<String, PriorityQueue> bigramsPredict;
	private HashMap<String, PriorityQueue> trigramsPredict;
	private PriorityQueue<GramFreq> pq;
	
	private int n;
	private int v;
	
	private int unigramsN;
	private int unigramsV;
	
	private int bigramsN;
	private int bigramsV;
	
	private int trigramsN;
	private int trigramsV;
	
	boolean debug = false;
	
	public Builder()
	{
        // Initialize HashMaps
        unigrams = new HashMap<String, Integer>();
        bigrams = new HashMap<String, Integer>();
        trigrams = new HashMap<String, Integer>();
        
        bigramsPredict = new HashMap<String, PriorityQueue>();
        trigramsPredict = new HashMap<String, PriorityQueue>();

        unigramsN = 0;
        unigramsV = 0;
        bigramsN = 0;
        bigramsV = 0;
        trigramsN = 0;
        trigramsV = 0;
        
        // Functions
        mapBuilder();
        makePredictMap(bigrams,bigramsPredict,1);
        makePredictMap(trigrams,trigramsPredict,2);
        computeMetrics();
        write();
        
	}

    private void mapBuilder()
	{
		try
		{
			// Open the tokens file
			File tokens = new File("./tokenizer/output/tokens.out");
			FileReader fr = new FileReader(tokens);
			BufferedReader br = new BufferedReader(fr);
			
			BufferedWriter bw1 = null;
			BufferedWriter bw2 = null;
			BufferedWriter bw3 = null;
			
			if(debug) {
                bw1 = new BufferedWriter(new FileWriter("./ngrams/unigrams.txt"));
                bw2 = new BufferedWriter(new FileWriter("./ngrams/bigrams.txt"));
                bw3 = new BufferedWriter(new FileWriter("./ngrams/trigrams.txt"));
			}
            
			// Create three word variables for building ngrams
			String w1 = "", w2 = "", w3 = "";
			
			// Used for temporary storage
			String ngram;
			int freq;
		
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
				{
                    ngram = w1 + " " + w2 + " " + w3;

					if(trigrams.get(ngram) == null) 
					{
                         trigrams.put(ngram,1);
                         trigramsV++;
					
					} else 
					{
                        freq = trigrams.get(ngram);
                        freq++;
                        trigramsN++;
                        
                        trigrams.put(ngram,freq);
					
					}
					
					if(debug)
                        bw3.write(ngram + "\n");

                }
			
				// If the last two words are filled, add a bigram
				if(!w2.equals("") && !w3.equals("")) 
				{
					ngram = w2 + " " + w3;
					
					if(bigrams.get(ngram) == null)
					{
                        bigrams.put(ngram,1);
                        bigramsV++;
                        
					} else {
					
                        freq = bigrams.get(ngram);
                        freq++;
                        bigramsN++;
                        
                        bigrams.put(ngram,freq);
					}
					
					if(debug)
                        bw2.write(ngram + "\n");
                }
			
				// If the last word is filled, add a unigram
				if(!w3.equals("")) {
				
                    ngram = w3;
                    
                    if(unigrams.get(ngram) == null) {
                    
                        unigrams.put(ngram,1);
                        unigramsV++;
                    
                    } else {
                        
                        freq = unigrams.get(ngram);
                        freq++;
                        unigramsN++;
                        
                        unigrams.put(ngram,freq);
                    }
				
					if(debug) 
                        bw1.write(ngram + "\n");

                }
			}
		
			br.close();
			
			if(debug) {
			
                bw3.close();
                bw2.close();
                bw1.close();
			
			}
		
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
	
	private void computeMetrics()
	{
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
			
			File bpFile = new File("./data/bigramsPredict.map");
			File tpFile = new File("./data/trigramsPredict.map");
			
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
			mbw.write(n + "," + v + "," + unigramsN + "," + unigramsV + ","
				      + bigramsN + "," + bigramsV + "," + trigramsN + ","
					  + trigramsV + "\n");
			mbw.close();
			
			// Write bigramsPredict and TrigramsPredict to disk.
			bpFile.createNewFile();
			FileOutputStream bpos = new FileOutputStream(bpFile);
			ObjectOutputStream bpoos = new ObjectOutputStream(bpos);
			bpoos.writeObject(bigramsPredict);
			bpoos.close();
			
			tpFile.createNewFile();
			FileOutputStream tpos = new FileOutputStream(tpFile);
			ObjectOutputStream tpoos = new ObjectOutputStream(tpos);
			tpoos.writeObject(trigramsPredict);
			tpoos.close();
			
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
			System.exit(1);
		}
	}
	
	public void makePredictMap( HashMap<String,Integer> hm, HashMap<String,PriorityQueue> hmPQ, int n) {
	
        Iterator i = hm.entrySet().iterator();
        StringBuilder sb;
        Map.Entry m;
        String[] tmp;

        while (i.hasNext()) {

            m = (Map.Entry) i.next();

            tmp = m.getKey().toString().split(" ");
            
            sb = new StringBuilder();
            
            for(int s = 0; s < n; s++) {
                sb.append(tmp[s]);
                
                if(s < n-1) {
                    sb.append(" ");
                }
            }
            
            if (!hmPQ.containsKey(sb.toString())) {

                pq = new PriorityQueue(1, new GramComparator());
                pq.add(new GramFreq(tmp[tmp.length-1], (int) m.getValue()));

                hmPQ.put(sb.toString(), pq);

            } else {

                pq = hmPQ.get(sb.toString());
                pq.add(new GramFreq(tmp[tmp.length-1], (int) m.getValue()));

                hmPQ.put(sb.toString(), pq);

            }

        }
	
	}
	
	public static void main(String[] args)
	{
		new Builder();
	}

}
