
//=================================================================================================
// Program		: Interactive Sentence Predictor
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
import java.util.PriorityQueue;

public class Load {

    private HashMap<String, Integer> unigrams;
    private HashMap<String, Integer> bigrams;
    private HashMap<String, Integer> trigrams;

    private HashMap<String, PriorityQueue> bigramsPredict;
	private HashMap<String, PriorityQueue> trigramsPredict;
    
    private int n;
    private int v;

    private int unigramsN;
    private int unigramsV;

    private int bigramsN;
    private int bigramsV;

    private int trigramsN;
    private int trigramsV;

    public Load() {
        try {
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
            
            // Load predict maps
            FileInputStream bpis = new FileInputStream("./data/bigramsPredict.map");
            ObjectInputStream bpois = new ObjectInputStream(bpis);
            bigramsPredict = (HashMap<String, PriorityQueue>) bpois.readObject();
            bpois.close();
            
            FileInputStream tpfis = new FileInputStream("./data/trigramsPredict.map");
            ObjectInputStream tpois = new ObjectInputStream(tpfis);
            trigramsPredict = (HashMap<String, PriorityQueue>) tpois.readObject();
            tpois.close();

            String[] metrics = bfr.readLine().split(",");

            n = Integer.parseInt(metrics[0]);
            v = Integer.parseInt(metrics[1]);

            unigramsN = Integer.parseInt(metrics[2]);
            unigramsV = Integer.parseInt(metrics[3]);

            bigramsN = Integer.parseInt(metrics[4]);
            bigramsV = Integer.parseInt(metrics[5]);

            trigramsN = Integer.parseInt(metrics[6]);
            trigramsV = Integer.parseInt(metrics[7]);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }

    // Getters
    public HashMap getUnigrams() {
        return unigrams;
    }

    public HashMap getBigrams() {
        return bigrams;
    }

    public HashMap getTrigrams() {
        return trigrams;
    }
    
    public HashMap getBigramsPredict(){
        return bigramsPredict;
    }
    
    public HashMap getTrigramsPredict(){
        return trigramsPredict;
    }

    public int getN() {
        return n;
    }

    public int getV() {
        return v;
    }

    public int getUnigramsN() {
        return unigramsN;
    }

    public int getUnigramsV() {
        return unigramsV;
    }

    public int getBigramsN() {
        return bigramsN;
    }

    public int getBigramsV() {
        return bigramsV;
    }

    public int getTrigramsN() {
        return trigramsN;
    }

    public int getTrigramsV() {
        return trigramsV;
    }
}
